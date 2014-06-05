package controller.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer.Form;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.activity.Activity;
import model.user.Permissoes;
import model.user.User;
import view.main.panel.PanelRecords;
import view.main.panel.PanelRecords.FormAttEnum;
import view.main.panel.PanelRecords.FormButtonEnum;
import controller.Main;
import core.CaloriesCalculation;
import core.FormUtils;
import core.UserStats;
import core.FormUtils.FormHandle;
import core.FormUtils.FormListHandle;
import core.FormUtils.OnPanelLoadLisneter;
import core.util.Util;
import core.util.Manager.ObjectDontExistException;

public class ControllerRecords implements ListSelectionListener {
	private FormListHandle mHandler;
	private User mUser;
	private Map<Integer,Activity> mActivitys;
	private List<String> mActivitysString;
	private Permissoes mRight;
	
	public ControllerRecords(FormHandle handler,User user) {
		mHandler = (FormListHandle) handler;
		mUser = user;
		mActivitys = mUser.getRecords();
		setActivityString(mActivitys);
		mHandler.addStringAll(mActivitysString); 
		initListeners();
		mRight = user.getPermissoes();
		checkRight();
		setComponentsEnable();
		
	}
	public void setUser(User user,Permissoes p){
		mUser = user;
		mActivitys = mUser.getRecords();
		setActivityString(mActivitys);
		mHandler.addStringAll(mActivitysString);
		mRight = p;
		checkRight();
	
	}
	
	private void setActivityString(Map<Integer,Activity> hash){
		mActivitysString = new ArrayList<String>(hash.size());
		Iterator<Integer> ite = hash.keySet().iterator();
		for (Activity activity : hash.values()) {
			mActivitysString.add(activity.getRecordToString(activity.getRecord(ite.next())));
		}
	}
	
	private void initListeners(){
		mHandler.addButtonListener(FormButtonEnum.ACTUALIZAR, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}

		});
		mHandler.addLoadLisnener(new OnPanelLoadLisneter() {
			
			@Override
			public void load() {
				try {
					mActivitys = Main.getDataSet().userManager().get(mUser).getRecords();
					mHandler.setValue(PanelRecords.FormAttEnum.DATE1, Util.dateFormat(new GregorianCalendar()));
					mHandler.setValue(PanelRecords.FormAttEnum.DATE2, Util.dateFormat(new GregorianCalendar()));
					setComponentsEnable();
				} catch (ObjectDontExistException e) {
					JOptionPane.showMessageDialog(null, "Utilizador invalido");
				}
				setActivityString(mActivitys);
				mHandler.addStringAll(mActivitysString);
			}
		});

	}

	private void refresh()  {
		try {
			String sd1 =(String) mHandler.getValue(PanelRecords.FormAttEnum.DATE1);
			GregorianCalendar d1 = new GregorianCalendar();
			d1.setTimeInMillis(Util.dateFormat(sd1));
			

			String sd2 =(String) mHandler.getValue(PanelRecords.FormAttEnum.DATE2);
			GregorianCalendar d2 = new GregorianCalendar();
			d2.setTimeInMillis(Util.dateFormat(sd2));
			
			mHandler.setValue(PanelRecords.FormAttEnum.CALORIAS,
					String.valueOf(UserStats.getCaloriesStats(mUser, d1, d2)));
		
			mHandler.setValue(PanelRecords.FormAttEnum.DISTANCIA,
					String.valueOf(UserStats.getDistanceStats(mUser, d1, d2)));
		
			mHandler.setValue(PanelRecords.FormAttEnum.PONTOS,
					"NOT IMPLEMENTED");
		
			mHandler.setValue(PanelRecords.FormAttEnum.WORKOUT,
					String.valueOf(UserStats.getWorkoutStats(mUser, d1, d2)));
		
			
			
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Data Invalida");
			
		}
		
	}
	
	
	private void checkRight(){
		boolean edit;
		if (mRight == Permissoes.Admin || mRight == Permissoes.User){
			edit = true;
		}else{
			edit = false;
		}
		
		for(FormButtonEnum e: FormButtonEnum .values()){
			mHandler.getButton(e).setEnabled(edit);
		}
	}
	

	protected void setComponentsEnable(){
		mHandler.getComponent(PanelRecords.FormAttEnum.DATE1).setEnabled(true);
		mHandler.getComponent(PanelRecords.FormAttEnum.DATE2).setEnabled(true);
		mHandler.getComponent(PanelRecords.FormAttEnum.CALORIAS).setEnabled(false);
		mHandler.getComponent(PanelRecords.FormAttEnum.PONTOS).setEnabled(false);
		mHandler.getComponent(PanelRecords.FormAttEnum.DISTANCIA).setEnabled(false);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		
	}
}
