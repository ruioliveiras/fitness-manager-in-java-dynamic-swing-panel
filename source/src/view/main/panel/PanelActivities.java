package view.main.panel;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.text.JTextComponent;

import view.FormUtils.FormAttr;
import view.FormUtils.FormButtons;
import view.main.PanelListToolBar;

public class PanelActivities extends PanelListToolBar {
	private enum Buttons implements FormButtons{
		CRIAR_SALVAR("Criar","Salvar"),
		EDITAR		("Editar","Salvar"),
		APAGAR		("Apagar","");
		
		private String text1;
		private String text2;
		private JButton button;
		
		Buttons(String l1,String l2){
			text1 = l1;
			text2 = l2;
			button = new JButton(text1);
		}

		@Override
		public int size() 		  			{return Buttons.values().length;}

		@Override
		public void enable(int i,boolean a) {Buttons.values()[i].button.setEnabled(a);}

		@Override
		public void text1(int i) {Buttons.values()[i].button.setText(Buttons.values()[i].text1);}

		@Override
		public void text2(int i) {Buttons.values()[i].button.setText(Buttons.values()[i].text2);}
		
		@Override
		public JButton getButton(int i) {return Buttons.values()[i].button;}

	}
	
	private static enum Attr implements FormAttr{
		NOME(100,"E-Mail",new JTextField());
			
		private String label;
		private JTextComponent text;
		private int size;
		
		Attr(int si,String l,JTextComponent m){
			label = l;
			text = m;
			size = si;
		}

		@Override
		public String getLabel(int i)		 {return Attr.values()[i].label;}
		@Override
		public JTextComponent getField(int i){return Attr.values()[i].text;}
		@Override
		public int getWidth(int i)			{return Attr.values()[i].size;}
		@Override 
		public int size() 		  			{return Attr.values().length;}
		
		
		public String getText()				{return text.getText();}
		public void setText(String str)		{text.setText(str);}
	}
	
	

	public PanelActivities(JPanel panel,List<String> events) {
		super(Attr.NOME,Buttons.CRIAR_SALVAR);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton button =((JButton) (arg0.getSource()));
		int ButtonIndex;
		
		for(int i=0;i<Buttons.values().length;i++){
			if (Buttons.values()[i].button == button){
				ButtonIndex = i;
				break;
			}
		}
		
		switch (button.getText()) {
		case "Salvar":
			
			break;

		default:
			break;
		}
		
	}


	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
