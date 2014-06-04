package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ListModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionListener;

import view.main.panel.PanelActivities;
import view.main.panel.PanelEvents;
import view.main.panel.PanelProfile;
import view.main.panel.PanelRecords;
import controller.ControllerLogin;
import controller.Main;
import core.FormUtils.FormHandle;


public class FormMain implements ActionListener{
	private final static String TXT_PERFIL  		= "Perfil";
	private final static String TXT_ACTIVIDADES  	= "Actividades";
	private final static String TXT_RECORDES	  	= "Recordes";
	private final static String TXT_EVENTOS  		= "Eventos";
	private final static String TXT_ADDDFRIEND 		= "Adicionar Amigo";
	private final static String TXT_EXIT 			= "Sair";
	private final static int SIZE_WIDTH 	= 900;
	private final static int SIZE_HEIGHT 	= 600;

	private String mTitle;
	private JList<String> mFriends;
	private JFrame mFrame = new JFrame();
	private JPanel mIFrame;

	private PanelProfile mProfile;
	private PanelActivities mActivities;
	private PanelEvents mEvents;
	private PanelRecords mRecords;
	private MainListener mListener; 

	public FormMain(){
		mProfile = new PanelProfile();
		mActivities = new PanelActivities();
		mEvents = new PanelEvents();
		mRecords = new PanelRecords();
	}

	public FormMain(String title,ListModel<String>  friends){
		this();
		mTitle = title;
		mFriends = new JList<String>(friends);
		mFriends.setSize((int) (SIZE_WIDTH * 0.3), SIZE_HEIGHT);

		mIFrame = new JPanel();
		mIFrame.setPreferredSize(new Dimension((int) (SIZE_WIDTH * 0.7), (int) (SIZE_HEIGHT - 42)));
		mIFrame.setBorder(BorderFactory.createLineBorder(Color.BLUE,1,true));
	}


	public void setFriends(String [] friends){
		mFriends.setListData(friends);
	}

	public void setFriendsListener(ListSelectionListener listener){
		mFriends.addListSelectionListener(listener);
	}
	public int getFriendsSelected(){
		return mFriends.getSelectedIndex(); 
	}
	
	public void setListener(MainListener listerner){mListener = listerner;}
	
	public void notifyDataChaged(ListModel<String> friends){
		mFriends.removeAll();
		mFriends.setModel(new DefaultComboBoxModel<String>());
		mFriends.setModel(friends);
	}
	
	public int getSelectedIndex(){
		return mFriends.getSelectedIndex();
	}
	
	public void show(){


		mFrame.setTitle(mTitle);
		mFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
		mFrame.setSize(SIZE_WIDTH, SIZE_HEIGHT);

		mFrame.getContentPane().add(buildMain());

		mFrame.pack();
		mFrame.setVisible(true);
		mFrame.setResizable(false);

		//Profile
		mIFrame.removeAll();
		mProfile.loadIn(mIFrame);
		mIFrame.revalidate();
		
		/*	 JComponent panel = new ButtonBarFactoryExample().buildPanel();*/
	}
	

	
	public FormHandle getHandlerProfile(){
		return mProfile;
	}
	public FormHandle getHandlerEvents(){
		return mEvents;
	}
	public FormHandle getHandlerActivities(){
		return mActivities;
	}
	public FormHandle getHandlerRecords(){
		return mRecords;
	}
	public FormHandle getHandlerStats(){
		throw new RuntimeErrorException(new Error("Not implement yet"));
	}
	
	protected JPanel buildMain(){
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

	protected JPanel buildMenu() { 
		final JPanel panel = new JPanel();	
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

		button = new JButton(TXT_ADDDFRIEND);
		button.addActionListener(this);
		panel.add(button);

		button = new JButton(TXT_EXIT);
		button.addActionListener(this);
		panel.add(button);
		
		return panel;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case TXT_PERFIL:
			mIFrame.removeAll();
			mProfile.loadIn(mIFrame);
			mIFrame.revalidate();
			break;
		case TXT_ACTIVIDADES:
			ArrayList<Object> ola1 = new ArrayList<Object>();ola1.add("OLA");
			mActivities.loadIn(mIFrame);
			break;
		case TXT_RECORDES:
			ArrayList<Object> ola2 = new ArrayList<Object>();ola2.add("OLA");
			mRecords.loadIn(mIFrame);
			break;
		case TXT_EVENTOS:
			ArrayList<Object> ola3 = new ArrayList<Object>();ola3.add("OLA");
			mEvents.loadIn(mIFrame);
			break;
		case TXT_ADDDFRIEND:
			   String email = JOptionPane.showInputDialog(null,
			  "Adicionar Amigo","Email do Amigo",JOptionPane.QUESTION_MESSAGE);
			   if (mListener != null)
				   mListener.addFriend(email);
			break;
		case TXT_EXIT:
			Main.save();
			mFrame.setVisible(false);
			mFrame.dispose(); 
			ControllerLogin login = new ControllerLogin();
		    login.start();
			break;
		default:
			break;
		}
	}






	public interface MainListener{
		public void addFriend(String email);
		public void acceptInvite(String email);
		public void refuseInvite(String email);
	}

	private JFrame mPopupInviteFrame; 
	public void showPopupInvite(final String email){
		if (mPopupInviteFrame != null){
			mPopupInviteFrame.setVisible(false);
			mPopupInviteFrame.dispose();
		}
		
		String title = "Convite "+ email;
		mPopupInviteFrame = new JFrame(title);
		mPopupInviteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contentPane = mPopupInviteFrame.getContentPane();

		JToggleButton acceptButton = new JToggleButton("Aceitar");
		contentPane.add(acceptButton, BorderLayout.NORTH);

		JToggleButton refuseButton = new JToggleButton("Não Aceitar");
		contentPane.add(refuseButton, BorderLayout.CENTER);

		JToggleButton cancelButton = new JToggleButton("Cancelar");
		contentPane.add(cancelButton, BorderLayout.SOUTH);

		acceptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mListener.acceptInvite(email);
				mPopupInviteFrame.setVisible(false);
				mPopupInviteFrame.dispose();
			}
		});
		refuseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mListener.refuseInvite(email);
				mPopupInviteFrame.setVisible(false);
				mPopupInviteFrame.dispose();
			}
		});
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mPopupInviteFrame.setVisible(false);
				mPopupInviteFrame.dispose();
			}
		});


		mPopupInviteFrame.setSize(300, 125);
		mPopupInviteFrame.setVisible(true);
	}
}
