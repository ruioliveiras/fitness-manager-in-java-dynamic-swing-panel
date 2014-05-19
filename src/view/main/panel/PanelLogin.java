package view.main.panel;

import java.awt.GridLayout;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import core.FormUtils.FormAttr;
import core.FormUtils.FormButtons;
import view.main.PanelToolBar;


public class PanelLogin extends PanelToolBar {

	
	
	public enum FormButtonEnum implements FormButtons{
		ENTRAR		("Entrar",""),
		REGISTAR	("Registar","");
		
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
		EMAIL		("E-Mail",JTextField.class),
		PASS		("Password",JPasswordField.class);
				
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
	
	

	
	
	
	public PanelLogin() {
		super(FormAttEnum.values(),FormButtonEnum.values());
		init();
	}
	
	
	@Override
	public JPanel loadIn(JPanel in){
		return super.loadIn(in);
	}
	@Override
	protected JPanel builWorkSpace(JPanel actForm){
		return super.builWorkSpace(new JPanel(new GridLayout(0, 2, 10, 10)));
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

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Login------");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
		JPanel panel = new JPanel();
		
		PanelProfile pro = new PanelProfile();
		pro.loadIn(panel);
		
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
	}

	
	

}
