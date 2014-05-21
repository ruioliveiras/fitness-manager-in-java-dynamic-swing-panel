package controller;

import model.Dataset;
import model.user.User;
import view.ViewMain;

public class ControllerMain {
	private ViewMain mViewMain;
	private Dataset mDataset;
	
	public ControllerMain(User u){
		mViewMain = new ViewMain(u.getNome(),u.amigosManager().collection().
				toArray(new String[u.amigosManager().collection().size()]));		
		mDataset = new Dataset();
		mDataset.userManager().add(u);
		initListeners();
	}
	
	public void start(){
		mViewMain.show();
	}


	private void initListeners(){
		
	}

	public static void main(String[] args) {
		ControllerLogin cl = new ControllerLogin();
		cl.start();
		
	}
}
