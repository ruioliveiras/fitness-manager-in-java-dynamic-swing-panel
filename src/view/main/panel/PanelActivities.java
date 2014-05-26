package view.main.panel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import model.activity.Contest;
import core.FormUtils.FormAttr;
import core.FormUtils.FormButtons;
import view.main.PanelListToolBar;

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
		COMBO		("E-Mail", JComboBox.class),
		TIME		("Time",JTextField.class),
		HEARTH		("Pulsação",JTextField.class),
		TEMPO		("Tempo",JComboBox.class),
		DATE		("Date",JTextField.class), 
		//DISTANCE:
		DISTANCE	("Distancia [m]",JTextField.class),
		MAXSPEED 	("Vel. Maxima",JTextField.class),
		//altemetria
		ASCENDENT	("Ascendente [m]",JTextField.class),
		DESCENDET	("Descendente [m]",JTextField.class),
		MINALT	("Min Altura [m]",JTextField.class),
		MAXALT	("Max Altura [m]",JTextField.class),
		//CONTEST
		POINTRIVAL	("Equipa",JTextField.class),
		POINTTEAM	("Oponente",JTextField.class),
		//SKILL
		MAXPOINT	("Max trik",JTextField.class),
		POINTS		("Pontos",JTextField.class);
		
		
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
