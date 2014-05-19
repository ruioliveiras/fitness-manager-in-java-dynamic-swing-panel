package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import core.FormUtils.FormHandle;
import view.main.panel.PanelLogin;
//binding.list.SelectionInList



public class ViewLogin {
	private final static int SIZE_WIDTH 	= 400;
	private final static int SIZE_HEIGHT 	= 200;
	private PanelLogin mLogin;

	public ViewLogin(){
		 mLogin = new PanelLogin();
	}
	
	public void show(){
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		frame.setTitle("Login------");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
		
		panel.setPreferredSize(new Dimension(SIZE_WIDTH, SIZE_HEIGHT));
		mLogin.loadIn(panel);
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
	}



	public static void main(String[] args){
		ViewLogin f = new ViewLogin();
		f.show();
	}

	public FormHandle getHandler(){
		return mLogin;
	}

	


}
