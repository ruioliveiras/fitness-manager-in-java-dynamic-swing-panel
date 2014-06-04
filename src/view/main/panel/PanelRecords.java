package view.main.panel;

import java.awt.Dimension;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import core.FormUtils.FormAttr;
import core.FormUtils.FormButtons;
import view.main.PanelListToolBar;


public class PanelRecords extends PanelListToolBar {
		
	public enum FormButtonEnum implements FormButtons{
		ACTUALIZAR		("Actualizar","");
		

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
		DATE1		("Data1",JTextField.class,23),
		DATE2		("Data2",JTextField.class,23),
		CALORIAS	("Calorias",JTextField.class,4),
		DISTANCIA	("Distancia",JTextField.class,4),
		WORKOUT		("Workout",JTextField.class,4),
		PONTOS		("Pontos",JTextField.class,4);
		
		
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
	
	
	

	
	public PanelRecords() {
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
