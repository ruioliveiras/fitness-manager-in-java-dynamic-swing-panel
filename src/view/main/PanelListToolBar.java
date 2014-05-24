package view.main;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionListener;

import core.FormUtils.FormAttr;
import core.FormUtils.FormButtons;
import core.FormUtils.FormListHandle;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class PanelListToolBar extends PanelToolBar implements ListModel<String>, FormListHandle {
		
	private ListSelectionListener mListListener;
	private ArrayList<ListDataListener> mListModelListeners = new ArrayList<ListDataListener>(); 
	private List<Object> mString;
		
	public PanelListToolBar(
			FormAttr[] attrs,
			FormButtons[] buttons){
		super(attrs,buttons);
		
		
	}
	
	public void addStringAll(Collection<?> str){
		mString = new ArrayList<Object>(str);
		callListDataListener_ActionAdd();
	}
	
	public void addString(Object str){
		mString.add(str);
		callListDataListener_ActionAdd();
	}
	
	public void editString(int index,String str){
		mString.set(index, str);
		callListDataListener_ActionAdd();
	}
	
	public JPanel load(JPanel panel){
		
		return super.loadIn(panel);
	}
	
	@Override
	public JPanel loadIn(JPanel panel){
		throw new NotImplementedException();
	}
	

	@Override
	protected JPanel builToolbar(JPanel p) {
		return super.builToolbar(p);
	}

	@Override
	protected JPanel builWorkSpace(JPanel panel) {
		JPanel actForm;
		JList<String> activities;
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		actForm = super.builWorkSpace(new JPanel());

		activities = new JList<String>(this);
		if (mListListener != null) activities.addListSelectionListener(mListListener);
		JScrollPane listScroller = new JScrollPane(activities);
        listScroller.setPreferredSize(new Dimension(mWidth,(int) (mHeight * 0.7)));
        listScroller.setBorder(BorderFactory.createLineBorder(Color.BLUE,1,true));
	    
        
        panel.add(actForm);
        panel.add(listScroller);
        
        return panel;
	}


	/** Implements ListSelectionListener (Activitie list click) ##################*/
	@Override
	public void addListListener(ListSelectionListener lis) {
		mListListener = lis;
	}



	/** Implements ListModel<String> (Activitie list provider) Start ##################*/
	@Override
	public int getSize() {
		return mString.size();
	}
	
	@Override
	public String getElementAt(int index) {
		return mString.get(index).toString();
	}
	
	@Override
	public void addListDataListener(ListDataListener l) {
		mListModelListeners.add(l);
	}
	@Override
	public void removeListDataListener(ListDataListener l) {
		mListModelListeners.remove(l);
	}
	public void callListDataListener_ActionAdd(){
		for(ListDataListener l: mListModelListeners)
			l.contentsChanged(new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, mString.size() -1, mString.size() -1));
	}
	public void callListDataListener_ActionEdit(int index){
		for(ListDataListener l: mListModelListeners)
			l.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, index, index));
	}
	/** Implements ListModel<String> End ##################*/

}
