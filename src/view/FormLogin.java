package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import core.FormUtils.FormHandle;
import view.main.panel.PanelLogin;
//binding.list.SelectionInList



public class FormLogin {
	private final static int SIZE_WIDTH 	= 400;
	private final static int SIZE_HEIGHT 	= 200;
	private PanelLogin mLogin;
	private JFrame mFrame;
	
	public FormLogin(){
		 mLogin = new PanelLogin();
	}
	
	public void show(){
		mFrame = new JFrame();
		JPanel panel = new JPanel();
		
		mFrame.setTitle("Login |-----  Fitness UM -----|");
		mFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
		
		panel.setPreferredSize(new Dimension(SIZE_WIDTH, SIZE_HEIGHT));
		mLogin.loadIn(panel);
		mFrame.getContentPane().add(panel);
		
		mFrame.pack();
		mFrame.setVisible(true);
	}



	public static void main(String[] args){
		FormLogin f = new FormLogin();
		f.show();
	}

	public FormHandle getHandler(){
		return mLogin;
	}

	public void hide() {
		mFrame.setVisible(false);
	}

	


}
