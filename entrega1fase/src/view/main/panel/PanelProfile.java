package view.main.panel;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import view.FormUtils;
import view.Listeners;
import view.FormUtils.FormAttr;
import view.Listeners.ListenerPanelProfile;
import view.main.PanelToolBar;



public class PanelProfile extends PanelToolBar implements ActionListener{
	
	private static enum Attr implements FormUtils.FormAttr{
		EMAIL		("E-Mail",new JTextField()),
		PASS		("Password",new JPasswordField()),
		NAME      ("Nome",new JTextField()),
		SEXO      ("Genero",new JTextField()),
		ALTURA    ("Altura",new JTextField()),
		PESO      ("Peso",new JTextField()),
		NASCIMENTO("Data Nascimento",new JFormattedTextField(new SimpleDateFormat(FormUtils.DATA_PATTERM))),
		PREFERIDO ("Desporto Favorito",new JTextField());
 
		private String label;
		private JTextComponent text;
		
		Attr(String l,JTextComponent m){
			label = l;
			text = m;
		}
		
		@Override
		public String getLabel(int i)		 {return Attr.values()[i].label;}
		@Override
		public JTextComponent getField(int i){return Attr.values()[i].text;}
		@Override
		public int getWidth(int i)			{return	200;}
		@Override 
		public int size() 		  			{return Attr.values().length;}
		
		
		public String getText()				{return text.getText();}
		public void setText(String str)		{text.setText(str);}
		
	}
	
	private JPanel mWork;
	private ListenerPanelProfile mListener;
	
	public PanelProfile(ListenerPanelProfile listeners) {
		super();
		
		mListener = listeners;			
	}
	
	
	@Override
	protected JPanel builWorkSpace() {
		mWork = new JPanel();
		mWork.setLayout(new GridLayout(0,2,10,10));
		
		for (Attr a: Attr.values()){
			addLine(a.label,a.text,250);	
		}
		return mWork;
	}
	
	private void addLine(String label,JTextComponent inst,int colums){
		FormUtils.addLine(mWork, label, inst, colums);
		inst.setEnabled(false);
		
	}


	@Override
	protected JPanel builToolbar() {
		JPanel tool = new JPanel();
		
		JButton b = new JButton("unLock");
		b.addActionListener(this);
			
		tool.add(b);
		return tool;
	}


	@Override
	public void actionPerformed(ActionEvent obj) {
		
	}



	

	
	
	
}
