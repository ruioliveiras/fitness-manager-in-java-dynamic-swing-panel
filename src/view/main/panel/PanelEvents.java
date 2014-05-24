package view.main.panel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import core.FormUtils.FormAttr;
import core.FormUtils.FormButtons;
import view.main.PanelListToolBar;

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
		NUM_PARTI	("Nº participantes",JTextField.class),
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

	//new SimpleDateFormat("dd/MM/yyyy")
	
	
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
					.newInstance(new SimpleDateFormat("dd/MM/yyyy"));
		
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException
				| InvocationTargetException e1) {
			System.err.println("ERRO DE INSTANCIAção, ");
			e1.printStackTrace();
		}
	}



	

	
	


}
