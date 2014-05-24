package controller;

import java.util.Collection;

import javax.management.RuntimeErrorException;
import javax.swing.JOptionPane;

import controller.main.ControllerActivitys;
import controller.main.ControllerEvents;
import controller.main.ControllerProfile;
import controller.main.ControllerRecords;
import controller.main.ControllerStats;
import core.util.Manager.ObjectDontExistException;
import model.user.User;
import view.FormMain;
import view.FormMain.MainListener;

public class ControllerMain implements MainListener{
	private FormMain mViewMain;
	protected User mUserCopy;
	
	private ControllerProfile mControllerProfile;
	private ControllerActivitys mControllerActivitys;
	private ControllerEvents mControllerEvents;
	private ControllerRecords mControllerRecords;
	private ControllerStats mControllerStats;
	
	
	public ControllerMain(User user){
		
		Collection<String> co = user.amigosManager().collection();
		int size = co.size();
		mViewMain = new FormMain(user.getNome(),co.toArray(new String[size]));		
		mUserCopy = user;
		
		mControllerProfile = new ControllerProfile(mViewMain.getHandlerProfile(),mUserCopy);
		mControllerActivitys = new ControllerActivitys(mViewMain.getHandlerActivities());
		mControllerEvents = new ControllerEvents(mViewMain.getHandlerEvents());
		mControllerRecords = new ControllerRecords(mViewMain.getHandlerRecords());
	//	mControllerStats = new ControllerStats(mViewMain.getHandlerStats());
		
		initListeners();
	}
	
	@Override
	public void addFriend(String email) {
		try {
			Main.getDataSet().userManager().get(new User(email));
			Main.getDataSet().userManager().add(mUserCopy);
			throw new RuntimeErrorException(new Error("check if add subestitui"));
		} catch (ObjectDontExistException e) {
			JOptionPane.showMessageDialog(null, "Email não existe");
		}
		
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
