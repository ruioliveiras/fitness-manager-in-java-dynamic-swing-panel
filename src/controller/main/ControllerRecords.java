package controller.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.activity.Activity;
import model.user.User;
import view.main.panel.PanelRecords.FormAttEnum;
import view.main.panel.PanelRecords.FormButtonEnum;
import core.FormUtils.FormHandle;
import core.FormUtils.FormListHandle;

public class ControllerRecords implements ListSelectionListener {
	private FormListHandle mHandler;
	private User mUser;
	private List<Activity> mActivitys;
	
	
	public ControllerRecords(FormHandle handler,User user) {
		mHandler = (FormListHandle) handler;
		mUser = user;
		mActivitys = mUser.getRecords();
		mHandler.addStringAll(mActivitys); 
		initListeners();
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
		int index = mHandler.getSelectIndex();
		mSelected = mActivitys.get(index);

		
	}
}
