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

import model.user.Permissoes;
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
	private Permissoes mUserPermissoes;
	
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

	
	private void initListeners(){
		
		mViewMain.setFriendsListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = mViewMain.getSelectedIndex();
				if (index == 0){
					loadUser(mUserCopy.getEmail(),Permissoes.User);
					return;
				}
				index--;
				if (index < mFriend.size()){
					Permissoes p = (mUserCopy.getPermissoes() == Permissoes.Admin) ? Permissoes.Admin : Permissoes.Guest;
					loadUser(mFriend.get(index),p);
				}else{
					index = index - mFriend.size();
					String email = mInvite.get(index);
					mViewMain.showPopupInvite(email);
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
		if (index == 0){
			return "Home";
		}
		index--;
		if (index < mFriend.size())
			return mFriend.get(index);
		else
			return "Convite:" + mInvite.get(index - mFriend.size());
	}

	@Override
	public int getSize() {
		return mFriend.size() + mInvite.size() + 1;
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		mListHelper.removeListDataListener(l);
	}

	
	private void loadUser(String string,Permissoes p) {
		try {
			User friend = Main.getDataSet().userManager().get(new User(string));
			mControllerProfile.setUser(friend,p);
			mControllerActivitys.setUser(friend,p);
			mControllerEvents.setUser(friend,p);
			mControllerRecords.setUser(friend,p);
		
		} catch (ObjectDontExistException e) {
			e.printStackTrace();
		}
		
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
			
			Main.getDataSet().userManager().edit(mUserCopy);
			Main.getDataSet().userManager().edit(other);
			Main.save();
			
		} catch (ObjectDontExistException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void refuseInvite(String email) {
		
		try {
			mUserCopy = Main.getDataSet().userManager().get(new User(mUserCopy.getEmail()));
			mUserCopy.convitesManager().remove(email);
			
			Main.getDataSet().userManager().edit(mUserCopy);
			Main.save();
		} catch (ObjectDontExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	

}
