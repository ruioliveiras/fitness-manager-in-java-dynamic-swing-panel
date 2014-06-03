package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.user.User;
import view.FormMain;
import view.FormMain.MainListener;
import controller.main.ControllerActivitys;
import controller.main.ControllerEvents;
import controller.main.ControllerProfile;
import controller.main.ControllerRecords;
import core.util.Manager.ObjectDontExistException;

public class ControllerMain implements MainListener,ListModel<String>{
	private FormMain mViewMain;
	protected User mUserCopy;
	
	private ControllerProfile mControllerProfile;
	private ControllerActivitys mControllerActivitys;
	private ControllerEvents mControllerEvents;
	private ControllerRecords mControllerRecords;
	private List<String> mFriend;
	private List<String> mInvite;
	private DefaultListModel<String> mListHelper;
	
	public ControllerMain(User user){
		mListHelper = new DefaultListModel<>();
		mFriend = new ArrayList<>( user.amigosManager().collection());
		mInvite  = new ArrayList<>( user.convitesManager().collection());
	
		mViewMain = new FormMain(user.getNome(),this);
		mViewMain.setListener(this);
		mUserCopy = user;
		
		mControllerProfile = new ControllerProfile(mViewMain.getHandlerProfile(),mUserCopy);
		mControllerActivitys = new ControllerActivitys(mViewMain.getHandlerActivities(),mUserCopy);
		mControllerEvents = new ControllerEvents(mViewMain.getHandlerEvents(),mUserCopy);
		mControllerRecords = new ControllerRecords(mViewMain.getHandlerRecords(),mUserCopy);

		initListeners();
	}
	
	@Override
	public void addFriend(String email) {
		try {
			
			User u = Main.getDataSet().userManager().get(new User(email));
			u.convitesManager().add(mUserCopy.getEmail());
			Main.getDataSet().userManager().edit(u);
			Main.save();
						
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

	public void changeUser(String email){
		try {
			User user = Main.getDataSet().userManager().get(new User(email));
			mControllerProfile.setUser(user);
			mControllerActivitys.setUser(user);
			mControllerEvents.setUser(user);
			mControllerRecords.setUser(user);		
		} catch (ObjectDontExistException e) {}
	}
	
	private void initListeners(){
		mViewMain.setFriendsListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = mViewMain.getFriendsSelected();
				if (index < mFriend.size()){
					changeUser(mFriend.get(index));
				}else{
					mInvite.get(index - mFriend.size());
				}
			}
		});
		
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		mListHelper.addListDataListener(l);
	}

	@Override
	public String getElementAt(int index) {
		if (index < mFriend.size())
			return mFriend.get(index);
		else
			return "Convite:" + mInvite.get(index - mFriend.size());
	}

	@Override
	public int getSize() {
		return mFriend.size() + mInvite.size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		mListHelper.removeListDataListener(l);
	}

	@Override
	public void acceptInvite(String email) {
		try {
			User other;
			
			other = Main.getDataSet().userManager().get(new User(email));
			other.amigosManager().add(mUserCopy.getEmail());
			
			mUserCopy = Main.getDataSet().userManager().get(new User(mUserCopy.getEmail()));
			mUserCopy.amigosManager().add(email);
			mUserCopy.convitesManager().remove(email);
			
		} catch (ObjectDontExistException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void refuseInvite(String email) {
		// TODO Auto-generated method stub
		
	}

	

}
