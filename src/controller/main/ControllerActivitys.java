package controller.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import controller.Main;
import model.user.User;
import view.main.panel.PanelActivities.FormAttEnum;
import view.main.panel.PanelActivities.FormButtonEnum;
import core.FormUtils.FormHandle;
import core.FormUtils.FormListHandle;

public class ControllerActivitys {
	private FormListHandle mHandler;
	private User mUser;
	
	
	public ControllerActivitys(FormHandle handler,User user) {
		mHandler = (FormListHandle) handler;
		mUser = user;
		mHandler.addStringAll(mUser.atividadesManager().collection()); 
		initListeners();
	}
	
	
	@SuppressWarnings("rawtypes")
	private void initListeners(){
		mHandler.addButtonListener(FormButtonEnum.EDITAR, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isEdit = mHandler.getTextIndex(FormButtonEnum.EDITAR) == 1; 
				setComponentsEnable(isEdit); // enable if isEdit
				if (isEdit){
					mHandler.setText2(FormButtonEnum.EDITAR);
				}else{
					mHandler.setText1(FormButtonEnum.EDITAR);
					saveProfileChanges();
				}
			
			}

		});
		JComboBox<?> a = (JComboBox<?>) mHandler.getComponent(FormAttEnum.COMBO);
		a.setModel(new DefaultComboBoxModel(Main.getActivitiesNames()));
		a.setEnabled(true);
	}
	
	private void saveProfileChanges() {
		
	}
	
	protected void setComponentsEnable(boolean b){
		for(FormAttEnum e: FormAttEnum .values()){
			mHandler.getComponent(e).setEnabled(b);
		}
	}
}
