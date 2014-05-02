package view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

public class FormUtils {
	
	public static interface FormButtons {
		public void enable(int i,boolean a);
		public void text1(int i);
		public void text2(int i);
		public JButton getButton(int i);
		public int size();
	}
	
	public static interface FormAttr {
		public String getLabel(int i);
		public JTextComponent getField(int i);
		public int getWidth(int i);
		public int size();
		public void setText(String tex);
		public String getText();
		
	}
	
	public static enum Attr {
//		EMAIL(Arrays,new JTextField());
//	
//		private String label;
//		private JTextComponent text;
//		
//		Attr(String l[],JTextComponent m){
//			label = l;
//			text = m;
//		}
//


	}
	
	public static String[] stringFactory(String ...array){
		String res[] = new String[array.length];
		for (int i =0;i<array.length;i++) res[i] = array[i];
		return res;
	}
	
	
	public static void addLine(JPanel panel,String label,JTextComponent inst,int colums){
		JLabel jLabel = new JLabel(label);
		jLabel.setHorizontalAlignment(JLabel.CENTER);
		panel.add(jLabel);
		inst.setPreferredSize(new Dimension(colums,24));
		panel.add(inst);
	}
	
}
