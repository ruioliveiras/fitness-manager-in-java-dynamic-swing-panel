package view.main.panel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import view.main.PanelListToolBar;
import core.FormUtils.FormAttr;
import core.FormUtils.FormButtons;

public class PanelActivities extends PanelListToolBar {
	public enum FormButtonEnum implements FormButtons{
		CRIAR_SALVAR("Criar","Salvar"),
		EDITAR		("Editar","Salvar"),
		APAGAR		("Apagar","");

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
		COMBO		("Activividade", JComboBox.class,10),
		TIME		("Time",JTextField.class,10),
		HEARTH		("Pulsação",JTextField.class,7),
		TEMPO		("Tempo",JComboBox.class,15),
		DATE		("Date",JTextField.class,10), 
		CALORIAS	("Calorias",JTextField.class,10), 
		//DISTANCE:
		DISTANCE	("Distancia [m]",JTextField.class,4),
		MAXSPEED 	("Vel. Maxima",JTextField.class,4),
		//altemetria
		ASCENDENT	("Ascendente [m]",JTextField.class,4),
		DESCENDET	("              Descendente [m]",JTextField.class,4),
		MINALT		("Min Altura [m]",JTextField.class,4),
		MAXALT		("Max Altura [m]",JTextField.class,4),
		//CONTEST
		POINTRIVAL	("Equipa",JTextField.class,4),
		POINTTEAM	("           Oponente",JTextField.class,4),
		//SKILL
		MAXPOINT	("Max trik",JTextField.class,4),
		POINTS		("            Pontos",JTextField.class,4);
		
		
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

	
	

	public PanelActivities() {
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
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException
				| InvocationTargetException e1) {
			System.err.println("ERRO DE INSTANCIAção, ");
			e1.printStackTrace();
		}
	}

	





	
	
	
}
