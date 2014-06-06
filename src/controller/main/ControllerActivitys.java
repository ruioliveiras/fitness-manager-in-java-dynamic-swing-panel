package controller.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.activity.Activity;
import model.activity.Altimetry;
import model.activity.Contest;
import model.activity.Distance;
import model.activity.Ginasio;
import model.activity.Skill;
import model.activity.Weather;
import model.user.Permissoes;
import model.user.User;
import view.main.panel.PanelActivities.FormAttEnum;
import view.main.panel.PanelActivities.FormButtonEnum;
import controller.Main;
import controller.NameDontExistException;
import core.FormUtils;
import core.FormUtils.FormHandle;
import core.FormUtils.FormListHandle;
import core.FormUtils.OnPanelLoadLisneter;
import core.util.Manager.ObjectDontExistException;
import core.util.Util;

public class ControllerActivitys implements ListSelectionListener{
	private FormListHandle mHandler;
	private User mUser;
	private Activity mSelected;
	private List<Activity> mActivitys;
	private Permissoes mRight;
	
	public ControllerActivitys(FormHandle handler,User user) {
		mHandler = (FormListHandle) handler;
		mUser = user;
		mActivitys = mUser.atividadesManager().collection();
		mHandler.addStringAll(mActivitys); 
		initListeners();
		mRight = user.getPermissoes();
		checkRight();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initListeners(){
		mHandler.addButtonListener(FormButtonEnum.EDITAR, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isEdit = mHandler.getTextIndex(FormButtonEnum.EDITAR) == 1; 
				setComponentsEnable(isEdit); // enable if isEdit
				//setActivity(mSelected);
				if (isEdit){
					mHandler.setText2(FormButtonEnum.EDITAR);
					 mHandler.getComponent(FormAttEnum.DATE).setEnabled(false);
				}else{
					mHandler.setText1(FormButtonEnum.EDITAR);
					saveActivity(mSelected);
				}
			
			}
		});
		mHandler.addButtonListener(FormButtonEnum.CRIAR_SALVAR, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isEdit = mHandler.getTextIndex(FormButtonEnum.CRIAR_SALVAR) == 1; 
				setComponentsEnable(isEdit); // enable if isEdit
				
				if (isEdit){
					setActivity(new Ginasio());
					mHandler.setText2(FormButtonEnum.CRIAR_SALVAR);
				}else{
					mHandler.setText1(FormButtonEnum.CRIAR_SALVAR);
					saveActivity(null);
				}
			
			}

		});
		mHandler.addButtonListener(FormButtonEnum.APAGAR, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean confirmacao = mHandler.getTextIndex(FormButtonEnum.APAGAR) == 1; 
				if (confirmacao){
					mHandler.setText2(FormButtonEnum.APAGAR);
				}else{
					mHandler.setText1(FormButtonEnum.APAGAR);
					apagar();
					refresh();
				}
			
			}


		});
		mHandler.addListListener(this);
		Object weather[] = {"Indoor","Sunny Windless","Sunny Moderate wind","Sunny Strong wind","Cloudy Windless","Cloudy ","Cloudy Moderate wind","Rainy Windless","Rainy Moderate wind","Rainy Strong wind"};

		((JComboBox<Object>) mHandler.getComponent(FormAttEnum.TEMPO)).setModel(new DefaultComboBoxModel<>(weather));//Weather.getWeatherArray()));

		JComboBox<?> a = (JComboBox<?>) mHandler.getComponent(FormAttEnum.COMBO);
		a.setModel(new DefaultComboBoxModel(Main.getActivitiesNames()));
		a.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				
				try {
					setActivity( Main.getActivity(arg0.getItem().toString()));
				} catch (NameDontExistException e) {
					e.printStackTrace();
				}
			}
		});
		mHandler.addLoadLisnener(new OnPanelLoadLisneter() {
			@Override
			public void load() {
				refresh();
				
			}
		});

	}
	
	public void setUser(User user,Permissoes p){
		mRight = p;
		mUser = user;
		mActivitys = mUser.atividadesManager().collection();
		mHandler.addStringAll(mActivitys);
		checkRight();
	}
	private void setActivity(Activity act){
		try {
			int index = Main.getActivityIndex(act.getName());
			JTextField time = (JTextField) mHandler.getComponent(FormAttEnum.TIME);
			JTextField hr = (JTextField) mHandler.getComponent(FormAttEnum.HEARTH);
			JTextField date = (JTextField) mHandler.getComponent(FormAttEnum.DATE);
			JTextField calorias = (JTextField) mHandler.getComponent(FormAttEnum.CALORIAS);
			JTextField distance = (JTextField) mHandler.getComponent(FormAttEnum.DISTANCE);
			JTextField maxspeed = (JTextField) mHandler.getComponent(FormAttEnum.MAXSPEED);
			JTextField asc = (JTextField) mHandler.getComponent(FormAttEnum.ASCENDENT);
			JTextField des = (JTextField) mHandler.getComponent(FormAttEnum.DESCENDET);
			JTextField minAlt = (JTextField) mHandler.getComponent(FormAttEnum.MINALT);
			JTextField maxAlt = (JTextField) mHandler.getComponent(FormAttEnum.MAXALT);
			JTextField pointRiv = (JTextField) mHandler.getComponent(FormAttEnum.POINTRIVAL);
			JTextField pointTeam = (JTextField) mHandler.getComponent(FormAttEnum.POINTTEAM);
			JTextField maxPoint = (JTextField) mHandler.getComponent(FormAttEnum.MAXPOINT);
			JTextField points = (JTextField) mHandler.getComponent(FormAttEnum.POINTS);			
			
			
			((JComboBox<?>) mHandler.getComponent(FormAttEnum.COMBO)).setSelectedIndex(index);
			time.setText(Util.hourFormat(act.getDuration()));
			hr.setText(String.valueOf(act.getHeartRate()));
			((JComboBox<?>) mHandler.getComponent(FormAttEnum.TEMPO)).setSelectedIndex(Weather.getIndexWeather(act.getWeather()));
			date.setText(Util.dateFormat(act.getDate()));
			calorias.setText(String.valueOf(act.calculateCalories(mUser)));
			
			if (act instanceof Distance){
				distance.setText(String.valueOf(((Distance) act).getDistance()));
				maxspeed.setText(String.valueOf(((Distance) act).getMaxSpeed()));	
			}
			if (act instanceof Altimetry){
				asc.setText(String.valueOf(((Altimetry) act).getAscendent()));
				des.setText(String.valueOf(((Altimetry) act).getDescendent()));
				minAlt.setText(String.valueOf(((Altimetry) act).getMinAltitude()));
				maxAlt.setText(String.valueOf(((Altimetry) act).getMaxAltitude()));
			}
			if (act instanceof Contest){
				pointRiv.setText(String.valueOf(((Contest) act).getPointRival()));
				pointTeam.setText(String.valueOf(((Contest) act).getPointTeam()));
			}
			if (act instanceof Skill){
				maxPoint.setText(String.valueOf(((Skill) act).getMaxTrick()));
				points.setText(String.valueOf(((Skill) act).getPoints()));
			}
			
			
			distance.setVisible(act instanceof Distance);
			maxspeed.setVisible(act instanceof Distance);
			asc.setVisible(act instanceof Altimetry);
			des.setVisible(act instanceof Altimetry);
			minAlt.setVisible(act instanceof Altimetry);
			maxAlt.setVisible(act instanceof Altimetry);
			pointRiv.setVisible(act instanceof Contest);
			pointTeam.setVisible(act instanceof Contest);
			maxPoint.setVisible(act instanceof Skill);
			points.setVisible(act instanceof Skill);
		
			mHandler.getLabel(FormAttEnum.DISTANCE).setVisible(act instanceof Distance);
			mHandler.getLabel(FormAttEnum.MAXSPEED).setVisible(act instanceof Distance);
			mHandler.getLabel(FormAttEnum.ASCENDENT).setVisible(act instanceof Altimetry);
			mHandler.getLabel(FormAttEnum.DESCENDET).setVisible(act instanceof Altimetry);
			mHandler.getLabel(FormAttEnum.MINALT).setVisible(act instanceof Altimetry);
			mHandler.getLabel(FormAttEnum.MAXALT).setVisible(act instanceof Altimetry);
			mHandler.getLabel(FormAttEnum.POINTRIVAL).setVisible(act instanceof Contest);
			mHandler.getLabel(FormAttEnum.POINTTEAM).setVisible(act instanceof Contest);
			mHandler.getLabel(FormAttEnum.MAXPOINT).setVisible(act instanceof Skill);
			mHandler.getLabel(FormAttEnum.POINTS).setVisible(act instanceof Skill);
			
			
		} catch (NameDontExistException e) {
			JOptionPane.showMessageDialog(null, "Utilizador invalido");
		}
	}
	
	private void saveActivity(Activity original) {
		Activity act;
		JTextField time = (JTextField) mHandler.getComponent(FormAttEnum.TIME);
		JTextField hr = (JTextField) mHandler.getComponent(FormAttEnum.HEARTH);
		JTextField date = (JTextField) mHandler.getComponent(FormAttEnum.DATE);
		JTextField distance = (JTextField) mHandler.getComponent(FormAttEnum.DISTANCE);
		JTextField maxspeed = (JTextField) mHandler.getComponent(FormAttEnum.MAXSPEED);
		JTextField asc = (JTextField) mHandler.getComponent(FormAttEnum.ASCENDENT);
		JTextField des = (JTextField) mHandler.getComponent(FormAttEnum.DESCENDET);
		JTextField minAlt = (JTextField) mHandler.getComponent(FormAttEnum.MINALT);
		JTextField maxAlt = (JTextField) mHandler.getComponent(FormAttEnum.MAXALT);
		JTextField pointRiv = (JTextField) mHandler.getComponent(FormAttEnum.POINTRIVAL);
		JTextField pointTeam = (JTextField) mHandler.getComponent(FormAttEnum.POINTTEAM);
		JTextField maxPoint = (JTextField) mHandler.getComponent(FormAttEnum.MAXPOINT);
		JTextField points = (JTextField) mHandler.getComponent(FormAttEnum.POINTS);			
		
		
		String actString = ((JComboBox<?>) mHandler.getComponent(FormAttEnum.COMBO)).
				getSelectedItem().toString();
		
		try {
			act = Main.getActivity(actString);
			String s = time.getText();
			act.setDuration(Util.hourFormat(s) );
			act.setHeartRate(Integer.parseInt( hr.getText()));
			//((JTextField) mHandler.getComponent(FormAttEnum.TEMPO))
			GregorianCalendar greg = new GregorianCalendar();
			greg.setTimeInMillis(Util.dateFormat(date.getText()));
			act.setDate(greg);

			act.setWeather( Weather.getWeatherIndex(((JComboBox<?>) mHandler.getComponent(FormAttEnum.TEMPO)).getSelectedIndex()));
			
			if (act instanceof Distance){
				((Distance) act).setDistance(Integer.parseInt( distance.getText()));
				((Distance) act).setMaxSpeed(Integer.parseInt( maxspeed.getText()));
			}
			if (act instanceof Altimetry){
				((Altimetry) act).setAscendent(Integer.parseInt( asc.getText()));
				((Altimetry) act).setDescendent(Integer.parseInt( des.getText()));
				((Altimetry) act).setMinAltitude(Integer.parseInt( minAlt.getText()));
				((Altimetry) act).setMaxAltitude(Integer.parseInt( maxAlt.getText()));
			}
			if (act instanceof Contest){
				((Contest) act).setPointRival(Integer.parseInt( pointRiv.getText()));
				((Contest) act).setPointTeam(Integer.parseInt( pointTeam.getText()));	
			}
			if (act instanceof Skill){
				((Skill) act).setMaxTrick(Integer.parseInt( maxPoint.getText()));
				((Skill) act).setPoints(Integer.parseInt( points.getText()));
			}
			if (original != null){
				mUser.atividadesManager().remove(original);
			}
			mUser.atividadesManager().add(act);
			Main.getDataSet().userManager().add(mUser);
			Main.save();
			refresh();
		} catch (NameDontExistException e) {
			JOptionPane.showMessageDialog(null, "ERRo nome não existe");
		} catch (ParseException e) {
			
			JOptionPane.showMessageDialog(null, "Parse error");
	
		} catch (ArithmeticException e){
			JOptionPane.showMessageDialog(null, "Erro:  Zero");
		}

	}
	private void apagar() {
		if (mSelected != null){
			try {
				mUser = Main.getDataSet().userManager().get(new User(mUser));
				mUser.atividadesManager().remove(mSelected);
				Main.getDataSet().userManager().edit(mUser);
				Main.save();				
			} catch (ObjectDontExistException e) {}
			
		}
	}

	private void setComponentsEnable(boolean b){
		for(FormAttEnum e: FormAttEnum .values()){
			mHandler.getComponent(e).setEnabled(b);
		}
		mHandler.getComponent(FormAttEnum.CALORIAS).setEnabled(false);
	}

	private void refresh(){
		try {
			mActivitys = Main.getDataSet().userManager().get(mUser).atividadesManager().collection();
			mHandler.addStringAll(mActivitys); 
			setActivity(new Ginasio());
		} catch (ObjectDontExistException e) {
			JOptionPane.showMessageDialog(null, "Utilizador invalido");
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
	@Override
	public void valueChanged(ListSelectionEvent e) {
		int index = mHandler.getSelectIndex();
		mSelected = mActivitys.get(index);
		setActivity(mSelected);
		
	}
}
