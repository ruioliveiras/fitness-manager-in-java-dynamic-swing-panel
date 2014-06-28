package view.main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public abstract class PanelToolBar{
	private static final int TOOL_BAR_SIZE = 50;
	
	protected int mWidth;
	protected int mHeight;
	protected JPanel mPanel;
	
	
	
	public PanelToolBar() {}
	
	
	
	
	public JPanel loadIn(JPanel in){
		mPanel = in;
		mHeight = (int) mPanel.getPreferredSize().getHeight(); 
		mWidth = (int) mPanel.getPreferredSize().getWidth();
	
		mPanel.removeAll();
		mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.PAGE_AXIS));
		
		JPanel tool = builToolbar();
		JPanel work = builWorkSpace();
		
		tool.setPreferredSize(new Dimension(mWidth, TOOL_BAR_SIZE));
		tool.setBorder(BorderFactory.createLineBorder(Color.BLUE,1,true));
		work.setPreferredSize(new Dimension((int)(mWidth * 0.98), (int) (0.98 * mHeight - TOOL_BAR_SIZE) ));
		work.setBorder(BorderFactory.createLineBorder(Color.BLUE,1,true));
		
		mPanel.add(tool);
		mPanel.add(work);
		
		mPanel.revalidate();
		return mPanel;
	}
	
	
	abstract protected JPanel builToolbar();
	abstract protected JPanel builWorkSpace();
	
	
	
	
}
