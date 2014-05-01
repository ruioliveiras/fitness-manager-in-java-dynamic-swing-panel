package view.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import view.FormUtils;
import view.FormUtils.FormAttr;
import view.FormUtils.FormButtons;

public abstract class PanelListToolBar extends PanelToolBar implements ActionListener,ListModel<String>, ListSelectionListener {
		
	
	private ArrayList<ListDataListener> mListModelListeners = new ArrayList<ListDataListener>(); 
	private List<String> mString;
	protected FormUtils.FormAttr mAttrs;
	protected FormUtils.FormButtons mButtons;
	
	public PanelListToolBar(
			FormAttr attrs,
			FormButtons buttons){
		super();
		
		mAttrs = attrs;
		mButtons = buttons;	
	}
	
	
	public void AddString(String str){
		mString.add(str);
		callListDataListener_ActionAdd();
	}
	
	public void EditString(int index,String str){
		mString.set(index, str);
		callListDataListener_ActionAdd();
	}
	
	public JPanel load(JPanel panel,List<String> strings){
		mString = strings;
		return super.loadIn(panel);
	}
	
	@Override
	public JPanel loadIn(JPanel panel){
		throw new NotImplementedException();
	}
	

	@Override
	protected JPanel builToolbar() {
		JPanel tool = new JPanel();
		
		for(int i =0;i<mButtons.size();i++){
			JButton b = mButtons.getButton(i);
			b.addActionListener(this);
			tool.add(b);
		}
	

		return tool;
	}

	@Override
	protected JPanel builWorkSpace() {
		JPanel panel = new JPanel();
		JPanel actForm;
		JList<String> activities;
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		actForm = new JPanel();
		actForm.setPreferredSize(new Dimension(mWidth , (int) (mHeight * 0.3)));
		for(int i =0;i<mAttrs.size();i++){
			FormUtils.addLine(actForm, mAttrs.getLabel(i), mAttrs.getField(i), mAttrs.getWidth(i));
		}

		activities = new JList<String>(this);
		activities.addListSelectionListener(this);
		JScrollPane listScroller = new JScrollPane(activities);
        listScroller.setPreferredSize(new Dimension(mWidth,(int) (mHeight * 0.7)));
        listScroller.setBorder(BorderFactory.createLineBorder(Color.BLUE,1,true));
	    
        
        panel.add(actForm);
        panel.add(listScroller);
        
        return panel;
	}


	/** Implements ActionListener (each option button)  ##################*/
	@Override
	public abstract void actionPerformed(ActionEvent arg0);

	/** Implements ListSelectionListener (Activitie list click) ##################*/
	@Override
	public abstract void valueChanged(ListSelectionEvent arg0);



	/** Implements ListModel<String> (Activitie list provider) Start ##################*/
	@Override
	public int getSize() {
		return mString.size();
	}
	
	@Override
	public String getElementAt(int index) {
		return mString.get(index);
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
