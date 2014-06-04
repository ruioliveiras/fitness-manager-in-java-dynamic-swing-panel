package view.main.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import model.activity.Weather;
import view.main.PanelListToolBar;
import core.FormUtils;
import core.FormUtils.FormAttr;
import core.FormUtils.FormButtons;
import core.FormUtils.SimpleListener;

public class PanelEvents extends PanelListToolBar{

	public enum FormButtonEnum implements FormButtons{
		ADERIR		("Aderir",""),
		CRIAR_SALVAR("Criar","Salvar"),
		APAGAR		("Apagar","Certeza?"),
		EDITAR		("Editar","Salvar"),
		INICIAR		("Iniciar","");

		private String eText1;
		private String eText2;
		
		private FormButtonEnum(String s1,String s2) {
			eText1 = s1;
			eText2 = s2;
		}
		
		
		@Override
		public int getIndex() {	return ordinal();}

		@Override
		public String text1() {	return eText1;}

		@Override
		public String text2() {	return eText2;}
		
	}

	public enum FormAttEnum implements FormAttr{
		NOME		("Nome",JTextField.class,20),
		ACTIVITY	("Acitividade",JComboBox.class,15),
		NUM_USER_ACT("Nº Users",JTextField.class,3),
		NUM_USER	("/",JTextField.class,3),
		RECORD_TYPE	("Tipo",JComboBox.class,15),
		REQUISITO	("Requisito",JTextField.class,3),
		DISTANCE	("Distancia",JTextField.class,3),
		NUM_JOGOS	("Nº jogos",JTextField.class,3),
		PONTOS_VIT	("Pontos Vitoria",JTextField.class,3),
		PONTOS_EMP	("Pontos Empate",JTextField.class,3),
		PONTOS_DER	("Pontos Derrota",JTextField.class,3),
		DATA_EVENT	("Data Evento",JFormattedTextField.class,3){
			@Override
			public Constructor<? extends JComponent> getComponetConstructor() {
				try {return JFormattedTextField.class.getConstructor(Format.class);} 
				catch (NoSuchMethodException | SecurityException e) 
				{e.printStackTrace();return null;}
			}
		},
		DATA_FIM	("Fim Inscrição",JFormattedTextField.class,15){
			@Override
			public Constructor<? extends JComponent> getComponetConstructor() {
				try {return JFormattedTextField.class.getConstructor(Format.class);} 
				catch (NoSuchMethodException | SecurityException e) 
				{e.printStackTrace();return null;}
			}
		};
		
		private String eName;
		private Class<? extends JComponent> eClass;
		private int eSize;
		
		FormAttEnum(String name,Class<? extends JComponent> _class,int size){
			eName = name;
			eClass = _class;
			eSize = size;
		}
		
		@Override
		public String getName() {return eName;}

		@Override
		public Class<? extends JComponent> getComponetClass() 
			{return eClass;}
		
		@Override
		public Constructor<? extends JComponent> getComponetConstructor() {
			try {return eClass.getConstructor();} 
			catch (NoSuchMethodException | SecurityException e) 
			{e.printStackTrace();return null;}
		}
	

		@Override
		public int getIndex() {return ordinal();}		
	}

	//new SimpleDateFormat(FormUtils.DATA_PATTERM)
	
	
	public PanelEvents() {
		super(FormAttEnum.values(),FormButtonEnum.values());
		init();
	}
	
	

	
	private void init(){
		mJButtons = new JButton[FormButtonEnum.values().length];
		for (FormButtonEnum b : FormButtonEnum.values()) {
			mJButtons[b.ordinal()] = new JButton();
		}
		
		mJComponets = new JComponent[FormAttEnum.values().length];
		try {
			/*General Cases*/
			for (FormAttEnum e : FormAttEnum.values()) {
				if (e.getComponetConstructor().getParameterTypes().length == 0){
					mJComponets[e.ordinal()] = e.getComponetConstructor().newInstance();
					try{
						((JTextField) mJComponets[e.ordinal()]).setColumns(e.eSize);
					}catch (Exception b){}
				}
			}
			/*Specific Cases*/
			mJComponets[FormAttEnum.DATA_FIM.ordinal()] = FormAttEnum.DATA_FIM.getComponetConstructor()
					.newInstance(new SimpleDateFormat(FormUtils.DATA_PATTERM));
			mJComponets[FormAttEnum.DATA_EVENT.ordinal()] = FormAttEnum.DATA_EVENT.getComponetConstructor()
					.newInstance(new SimpleDateFormat(FormUtils.DATA_PATTERM));
	
			
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException
				| InvocationTargetException e1) {
			System.err.println("ERRO DE INSTANCIAção, ");
			e1.printStackTrace();
		}
	}



	

	private JFrame mPopupInviteFrame; 
	public void showPopupEvent(final SimpleListener action){
		if (mPopupInviteFrame != null){
			mPopupInviteFrame.setVisible(false);
			mPopupInviteFrame.dispose();
		}
		
		String title = "Selecionar Tempo";
		mPopupInviteFrame = new JFrame(title);
		mPopupInviteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contentPane = mPopupInviteFrame.getContentPane();

		final JComboBox<Weather> weather = new JComboBox<>();
		weather.setModel(new DefaultComboBoxModel<Weather>(Weather.getWeatherArray()));
		contentPane.add(weather, BorderLayout.CENTER);

		JToggleButton accept = new JToggleButton("Iniciar");
		contentPane.add(accept, BorderLayout.SOUTH);

		accept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Weather a = (Weather) weather.getSelectedItem();
				action.action(a);
				mPopupInviteFrame.setVisible(false);
				mPopupInviteFrame.dispose();
			}
		});



		mPopupInviteFrame.setSize(300, 125);
		mPopupInviteFrame.setVisible(true);
	}
	


}
