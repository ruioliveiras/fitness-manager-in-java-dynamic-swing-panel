package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import view.main.panel.PanelProfile;
import view.main.panel.PanelProfile.FormAttEnum;
import core.FormUtils.FormHandle;

public class FormRegister {
	private final static int SIZE_WIDTH 	= 600;
	private final static int SIZE_HEIGHT 	= 400;
	private PanelProfile mProfile;
	private JFrame mFrame;
	
	public FormRegister(){
		 mProfile = new PanelProfile();
	}
	
	public void show(){
		mFrame = new JFrame();
		JPanel panel = new JPanel();
		
		mFrame.setTitle("Register |-----  Fitness UM -----|");
		mFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
		
		panel.setPreferredSize(new Dimension(SIZE_WIDTH, SIZE_HEIGHT));
		mProfile.loadIn(panel);
		mFrame.getContentPane().add(panel);
		
		mFrame.pack();
		mFrame.setVisible(true);
		 setVisible(true);
	}

	public FormHandle getHandler(){
		return mProfile;
	}

	public void hide() {
		mFrame.setVisible(false);
		mFrame.dispose();
	}


	public void setVisible(boolean b){
		for(FormAttEnum a:PanelProfile.FormAttEnum.values()){
			mProfile.getComponent(a).setEnabled(b);
		}
		
	}
}
