package view.main.panel;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.text.JTextComponent;

import view.FormUtils;
import view.FormUtils.FormButtons;
import view.main.PanelListToolBar;

public class PanelEvents extends PanelListToolBar{

	private enum Buttons implements FormButtons{
		ADERIR		("Aderir",""),
		CRIAR_SALVAR("Criar","Salvar"),
		EDITAR		("Editar","Salvar"),
		INICIAR		("Iniciar","");
		
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
	
	
	public enum Attr implements FormUtils.FormAttr{
		NOME		(500,"Nome",new JTextField()),
		NUM_PARTI	(50,"Nº participantes",new JTextField()),
		DATA_FIM	(100,"Fim Inscrição",new JFormattedTextField(new SimpleDateFormat(FormUtils.DATA_PATTERM)));
 
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
	

	
	public PanelEvents(JPanel panel,List<String> events) {
		super(Attr.NOME,Buttons.ADERIR);
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
