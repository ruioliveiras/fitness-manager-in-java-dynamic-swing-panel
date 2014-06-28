package controller.main;

import javax.swing.DefaultComboBoxModel;

import model.Activity;

public class ComboRecordModel extends DefaultComboBoxModel<Object>{
	private static final long serialVersionUID = 1L;
	
	public Activity mActivity;
	
	public ComboRecordModel(Activity a) {
		mActivity = a;
	}

	
	@Override
	public Object getElementAt(int index) {
		return mActivity.getRecord(index).getName();
	}

	@Override
	public int getSize() {
		return mActivity.getRecordSize();
	}
}
