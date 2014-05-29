package view.main.panel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

import view.main.PanelListToolBar;
import core.FormUtils;
import core.FormUtils.FormAttr;
import core.FormUtils.FormButtons;

public class PanelEvents extends PanelListToolBar{

	public enum FormButtonEnum implements FormButtons{
		ADERIR		("Aderir",""),
		CRIAR_SALVAR("Criar","Salvar"),
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
		NOME		("Nome",JTextField.class),
		ACTIVITY	("Acitividade",JComboBox.class),
		NUM_USER	("Nº Users",JTextField.class),
		RECORD_TYPE	("Tipo",JComboBox.class),
		REQUISITO	("Requisito",JTextField.class),
		DISTANCE	("Distancia",JTextField.class),
		NUM_JOGOS	("Nº jogos",JTextField.class),
		PONTOS_VIT	("Pontos Vitoria",JTextField.class),
		PONTOS_EMP	("Pontos Empate",JTextField.class),
		PONTOS_DER	("Pontos Derrota",JTextField.class),
		DATA_EVENT	("Data Evento",JFormattedTextField.class){
			@Override
			public Constructor<? extends JComponent> getComponetConstructor() {
				try {return JFormattedTextField.class.getConstructor(Format.class);} 
				catch (NoSuchMethodException | SecurityException e) 
				{e.printStackTrace();return null;}
			}
		},
		DATA_FIM	("Fim Inscrição",JFormattedTextField.class){
			@Override
			public Constructor<? extends JComponent> getComponetConstructor() {
				try {return JFormattedTextField.class.getConstructor(Format.class);} 
				catch (NoSuchMethodException | SecurityException e) 
				{e.printStackTrace();return null;}
			}
		};
		
		private String eName;
		private Class<? extends JComponent> eClass;
		
		FormAttEnum(String name,Class<? extends JComponent> _class){
			eName = name;
			eClass = _class;
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



	

	
	


}
