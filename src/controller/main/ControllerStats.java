package controller.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.activity.Activity;
import model.user.User;
import view.main.panel.PanelProfile.FormAttEnum;
import view.main.panel.PanelProfile.FormButtonEnum;
import core.FormUtils.FormHandle;
import core.FormUtils.FormListHandle;

public class ControllerStats  implements ListSelectionListener {
	private FormListHandle mHandler;
	private User mUser;
	private Activity mSelected;
	private List<Activity> mActivitys;
	
	public ControllerStats(FormHandle handler,User user) {
		mHandler = (FormListHandle) handler;
		mUser = user;
		mActivitys = mUser.atividadesManager().collection();
		mHandler.addStringAll(mActivitys); 
		initListeners();
	}
	
	
	
	private void initListeners(){
		mHandler.addButtonListener(FormButtonEnum.EDITAR, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isEdit = mHandler.getTextIndex(FormButtonEnum.EDITAR) == 0; 
				setComponentsEnable(isEdit); // enable if isEdit
				if (isEdit){
					mHandler.setText2(FormButtonEnum.EDITAR);
				}else{
					mHandler.setText1(FormButtonEnum.EDITAR);
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
