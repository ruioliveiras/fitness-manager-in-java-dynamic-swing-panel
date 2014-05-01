package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.main.panel.PanelActivities;
import view.main.panel.PanelEvents;
import view.main.panel.PanelProfile;
import view.main.panel.PanelRecords;


public class ViewMain implements ActionListener, ListSelectionListener{
	private final static String TXT_PERFIL  		= "Perfil";
	private final static String TXT_ACTIVIDADES  	= "Actividades";
	private final static String TXT_RECORDES	  	= "Recordes";
	private final static String TXT_EVENTOS  		= "Eventos";
	private final static int SIZE_WIDTH 	= 900;
	private final static int SIZE_HEIGHT 	= 600;
	
	private String mTitle;
	private JList<String> mFriends;
	private JFrame mFrame = new JFrame();
	private JPanel mIFrame;
	
		public ViewMain(String title,String[] friends){
			mTitle = title;
			mFriends = new JList<String>(friends);
			mFriends.setSize((int) (SIZE_WIDTH * 0.3), SIZE_HEIGHT);
			mFriends.addListSelectionListener(this);
			
			mIFrame = new JPanel();
			mIFrame.setPreferredSize(new Dimension((int) (SIZE_WIDTH * 0.7), (int) (SIZE_HEIGHT - 42)));
	        mIFrame.setBorder(BorderFactory.createLineBorder(Color.BLUE,1,true));
		}

		

		public void show(){
			
				
			mFrame.setTitle(mTitle);
			mFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
			mFrame.setSize(SIZE_WIDTH, SIZE_HEIGHT);
			
			mFrame.getContentPane().add(buildMain());
			
			mFrame.pack();
			mFrame.setVisible(true);
			mFrame.setResizable(false);

		/*	 JComponent panel = new ButtonBarFactoryExample().buildPanel();*/
		}

		public JPanel buildMain(){
			JPanel jPanel = new JPanel();
			JPanel  aux= new JPanel();
			aux.setLayout(new BoxLayout(aux, BoxLayout.PAGE_AXIS));
		
			JScrollPane listScroller = new JScrollPane(mFriends);
	        listScroller.setPreferredSize(new Dimension((int) (SIZE_WIDTH  * 0.3), SIZE_HEIGHT));
	        listScroller.setBorder(BorderFactory.createLineBorder(Color.BLUE,1,true));
		    jPanel.add(listScroller);
			
	        
	        JButton button = new JButton(TXT_ACTIVIDADES);
	        button.addActionListener(this);
	        
	        aux.add(buildMenu());
	        aux.add(mIFrame);
	        
	        jPanel.add(aux);
			

	      return jPanel;
			
		}
		
		public JPanel buildMenu() { 
			JPanel panel = new JPanel();	
			panel.setPreferredSize(new Dimension((int) (SIZE_WIDTH * 0.7), 42));
			panel.setBorder(BorderFactory.createLineBorder(Color.BLUE,1,true));
			
			
			
		    JButton button = new JButton(TXT_PERFIL);
		    button.addActionListener(this);
	        panel.add(button);
	        
	        button = new JButton(TXT_ACTIVIDADES);
	        button.addActionListener(this);
	        panel.add(button);
	        
	        button = new JButton(TXT_RECORDES);
	        button.addActionListener(this);
	        panel.add(button);
	        
	        button = new JButton(TXT_EVENTOS);
	        button.addActionListener(this);
	        panel.add(button);
	 
	         
	       
	      return panel;
		}
		
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case TXT_PERFIL:
				mIFrame.removeAll();
				(new PanelProfile(null)).loadIn(mIFrame);
				mIFrame.revalidate();
				break;
			case TXT_ACTIVIDADES:
				ArrayList<String> ola1 = new ArrayList<String>();ola1.add("OLA");
				new PanelActivities(mIFrame,ola1).load(mIFrame, ola1);
				break;
			case TXT_RECORDES:
				ArrayList<String> ola2 = new ArrayList<String>();ola2.add("OLA");
				new PanelRecords(mIFrame,ola2).load(mIFrame, ola2);
				break;
			case TXT_EVENTOS:
				ArrayList<String> ola3 = new ArrayList<String>();ola3.add("OLA");
				new PanelEvents(mIFrame,ola3).load(mIFrame, ola3);
				break;
			default:
				break;
			}
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			System.out.println("TESTE:" + e.getFirstIndex());
			
		}
		
		public static void main(String[] args){
			String arr[] = {"RUI","PEDRO"};
			ViewMain f = new ViewMain("Oliveiras",arr);
			f.show();
		}




}
