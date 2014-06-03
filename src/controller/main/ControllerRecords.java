package controller.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.activity.Activity;
import model.user.Permissoes;
import model.user.User;
import view.main.panel.PanelRecords.FormAttEnum;
import view.main.panel.PanelRecords.FormButtonEnum;
import controller.Main;
import core.FormUtils.FormHandle;
import core.FormUtils.FormListHandle;
import core.FormUtils.OnPanelLoadLisneter;
import core.util.Manager.ObjectDontExistException;

public class ControllerRecords implements ListSelectionListener {
	private FormListHandle mHandler;
	private User mUser;
	private Map<Integer,Activity> mActivitys;
	private List<String> mActivitysString;
	private Permissoes mRight;
	
	public ControllerRecords(FormHandle handler,User user) {
		mHandler = (FormListHandle) handler;
		mUser = user;
		mActivitys = mUser.getRecords();
		setActivityString(mActivitys);
		mHandler.addStringAll(mActivitysString); 
		initListeners();
		mRight = user.getPermissoes();
		checkRight();
	}
	public void setUser(User user,Permissoes p){
		mUser = user;
		mActivitys = mUser.getRecords();
		setActivityString(mActivitys);
		mHandler.addStringAll(mActivitysString);
		mRight = p;
		checkRight();
	
	}
	
	private void setActivityString(Map<Integer,Activity> hash){
		mActivitysString = new ArrayList<String>(hash.size());
		Iterator<Integer> ite = hash.keySet().iterator();
		for (Activity activity : hash.values()) {
			mActivitysString.add(activity.getRecordToString(activity.getRecord(ite.next())));
		}
	}
	
	private void initListeners(){
		mHandler.addButtonListener(FormButtonEnum.ACTUALIZAR, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isEdit = mHandler.getTextIndex(FormButtonEnum.ACTUALIZAR) == 0; 
				setComponentsEnable(isEdit); // enable if isEdit
				if (isEdit){
					mHandler.setText2(FormButtonEnum.ACTUALIZAR);
				}else{
					mHandler.setText1(FormButtonEnum.ACTUALIZAR);
					saveProfileChanges();
				}			
			}

			
		});
		mHandler.addLoadLisnener(new OnPanelLoadLisneter() {
			
			@Override
			public void load() {
				try {
					mActivitys = Main.getDataSet().userManager().get(mUser).getRecords();
				} catch (ObjectDontExistException e) {
					JOptionPane.showMessageDialog(null, "Utilizador invalido");
				}
				setActivityString(mActivitys);
				mHandler.addStringAll(mActivitysString);
			}
		});

	}
	
	
	private void checkRight(){
		boolean edit;
		if (mRight == Permissoes.Admin || mRight == Permissoes.User){
			edit = true;
		}else{
			edit = false;
		}
		
		for(FormButtonEnum e: FormButtonEnum .values()){
			mHandler.getButton(e).setEnabled(edit);
		}
	}
	
	private void saveProfileChanges() {
		
	}
	

	protected void setComponentsEnable(boolean b){
		for(FormAttEnum e: FormAttEnum .values()){
			mHandler.getComponent(e).setEnabled(b);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		
	}
}
