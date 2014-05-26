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
		mViewMain.setListener(this);
		mUserCopy = user;
		
		mControllerProfile = new ControllerProfile(mViewMain.getHandlerProfile(),mUserCopy);
		mControllerActivitys = new ControllerActivitys(mViewMain.getHandlerActivities(),mUserCopy);
		mControllerEvents = new ControllerEvents(mViewMain.getHandlerEvents(),mUserCopy);
		mControllerRecords = new ControllerRecords(mViewMain.getHandlerRecords(),mUserCopy);
	//	mControllerStats = new ControllerStats(mViewMain.getHandlerStats());
		
		initListeners();
	}
	
	@Override
	public void addFriend(String email) {
		try {
			
			User u =Main.getDataSet().userManager().get(new User(email));
			mUserCopy.addAmigo(u.getEmail());
			Main.getDataSet().userManager().edit(mUserCopy);
			mUserCopy = Main.getDataSet().userManager().get(mUserCopy);
			
			Collection<String> co = mUserCopy.amigosManager().collection();
			int size = co.size();
			mViewMain.setFriends(co.toArray(new String[size]));
		} catch (ObjectDontExistException e) {
			JOptionPane.showMessageDialog(null, "Email não existe");
		}
		
	}
	
	public void start(){
		mViewMain.show();
	}


	private void initListeners(){
		
	}



}
