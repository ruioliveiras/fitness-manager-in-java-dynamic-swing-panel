package view.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.FormUtils;
import core.FormUtils.FormAttr;
import core.FormUtils.FormButtons;
import core.FormUtils.FormHandle;

public abstract class PanelToolBar implements FormHandle{
	private static final int TOOL_BAR_SIZE = 50;
	
	protected int mWidth;
	protected int mHeight;
	protected JPanel mPanel;
	protected FormAttr[] mAttrs;
	protected FormButtons[] mButtons;
	protected JComponent[] mJComponets;
	protected JButton[] mJButtons;
	protected JPanel mTool;
	protected JPanel mWork;
	
	public PanelToolBar() {}

	public PanelToolBar(
			FormAttr[] attrs,
			FormButtons[] buttons){
		mAttrs = attrs;
		mButtons = buttons;	
	}
	
	
	public JPanel loadIn(JPanel in){
		mPanel = in;
		mHeight = (int) mPanel.getPreferredSize().getHeight(); 
		mWidth = (int) mPanel.getPreferredSize().getWidth();
	
		mPanel.removeAll();
		mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.PAGE_AXIS));
		
		JPanel work = new JPanel();
		
		mTool = builToolbar(new JPanel());
		mWork = builWorkSpace(work);
		
		mTool.setPreferredSize(new Dimension(mWidth, TOOL_BAR_SIZE));
		mTool.setBorder(BorderFactory.createLineBorder(Color.BLUE,1,true));
		mWork.setPreferredSize(new Dimension((int)(mWidth * 0.98), (int) (0.98 * mHeight - TOOL_BAR_SIZE) ));
		mWork.setBorder(BorderFactory.createLineBorder(Color.BLUE,1,true));
		
		mPanel.add(mTool);
		mPanel.add(mWork);
		
		mPanel.revalidate();
		return mPanel;
	}
	
	
	protected JPanel builToolbar(JPanel tool) {
	
		for(int i =0;i<mButtons.length;i++){
			JButton b = getButton(mButtons[i]);
			setText1(mButtons[i]);
			tool.add(b);
		}
	

		return tool;
	}
	protected JPanel builWorkSpace(JPanel actForm){
		actForm.setPreferredSize(new Dimension(mWidth , (int) (mHeight * 0.3)));
		for(int i =0;i<mAttrs.length;i++){
			FormUtils.addLine(actForm, mAttrs[i].getName(), getComponent(mAttrs[i]),200);
			getComponent(mAttrs[i]).setEnabled(false);
		}
		return actForm;
	}
	
	
	@Override
	public String getValue(FormAttr a) {
		JComponent c = getComponent(a);
		if (c instanceof JTextField){
			return ((JTextField)c).getText();
		} 
		
		return "";
	}

	@Override
	public void setValue(FormAttr a,Object obj) {
		JComponent c = getComponent(a);
		if (c instanceof JTextField && obj instanceof String){
			((JTextField)c).setText((String) obj);;
		}
	
	}
	
	@Override
	public JComponent getComponent(FormAttr a) {
		return  mJComponets[a.getIndex()];
	}
	@Override
	public JLabel getLabel(FormAttr a) {
		return (JLabel) mWork.getComponent( a.getIndex() * 2);
	}


	@Override
	public JButton getButton(FormButtons a) {
		return mJButtons[a.getIndex()];
	}
	
	@Override
	public void setText1(FormButtons a) {
		getButton(a).setText(a.text1());
	}
	@Override
	public void setText2(FormButtons a) {
		getButton(a).setText(a.text2());
	}
	@Override
	public int getTextIndex(FormButtons a) {
		if (getButton(a).getText().equals(a.text1()))
			return 1;
		else
			return 2;
	}
	
	@Override
	public void addButtonListener(FormButtons a, ActionListener lis) {
		getButton(a).addActionListener(lis);
	}
}
