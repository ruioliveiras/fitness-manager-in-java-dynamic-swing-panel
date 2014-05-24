package controller.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.main.panel.PanelEvents.FormAttEnum;
import view.main.panel.PanelEvents.FormButtonEnum;
import core.FormUtils.FormHandle;

public class ControllerEvents {
	private FormHandle mHandler;
	
	
	public ControllerEvents(FormHandle handler) {
		mHandler = handler; 
		setComponentsEnable(false);
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
}
