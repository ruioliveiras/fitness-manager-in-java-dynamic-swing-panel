package view.main.panel;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import view.main.PanelToolBar;
import core.FormUtils;
import core.FormUtils.FormAttr;
import core.FormUtils.FormButtons;



public class PanelProfile extends PanelToolBar {

	public enum FormButtonEnum implements FormButtons{
		EDITAR		("Editar","Salvar"),
		APAGAR		("ApagarConta","Certeza?");

		private String eText1;
		private String eText2;
		
		private FormButtonEnum(String s1,String s2) {
			eText1 = s1;
			eText2 = s2;
		}
		
		
		@Override
		public int getIndex() {return ordinal();}

		@Override
		public String text1() {return eText1;}

		@Override
		public String text2() {return eText2;}
		
	}
	
	public enum FormAttEnum implements FormAttr{
		EMAIL		("E-Mail",JTextField.class,26),
		PASS		("Password",JPasswordField.class,15),
		NAME    	("Nome", JTextField.class,30),
		SEXO      	("Genero", JTextField.class,15),
		ALTURA    	("Altura", JTextField.class,4),
		PESO      	("Peso", JTextField.class,4),
		FREQ      	("Fr/card.", JTextField.class,4),
		PREFERIDO 	("Desporto Favorito", JComboBox.class,15),
		NASCIMENTO	("Data Nascimento", JTextField.class,15),
		FORMA      	("Forma", JTextField.class,4);
		
		
		private String eName;
		private Class<? extends JComponent> eClass;
		private int eSize;
		
		FormAttEnum(String name,Class<? extends JComponent> _class,int tamanho){
			eName = name;
			eClass = _class;
			eSize = tamanho;
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

	
	
	
	public PanelProfile() {
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
//			mJComponets[FormAttEnum.NASCIMENTO.ordinal()] = FormAttEnum.NASCIMENTO.getComponetConstructor()
//					.newInstance(new SimpleDateFormat(FormUtils.DATA_PATTERM));
//		
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException
				| InvocationTargetException e1) {
			System.err.println("ERRO DE INSTANCIAção, ");
			e1.printStackTrace();
		}
	}




	
	
	
}
