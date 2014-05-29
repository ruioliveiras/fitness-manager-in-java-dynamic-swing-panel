package controller.main;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.activity.Activity;
import model.activity.Altimetry;
import model.activity.Contest;
import model.activity.Distance;
import model.activity.Skill;
import model.events.Event;
import model.events.EventContest;
import model.events.EventDistance;
import model.user.Permissoes;
import model.user.User;
import view.main.panel.PanelEvents.FormAttEnum;
import view.main.panel.PanelEvents.FormButtonEnum;
import controller.EventNotPermited;
import controller.NameDontExistException;
import controller.Main;
import core.FormUtils;
import core.FormUtils.FormHandle;
import core.FormUtils.FormListHandle;

public class ControllerEvents implements ListSelectionListener{
	private FormListHandle mHandler;
	private User mUser;
	private Event mSelected;
	private List<Event> mEvents;
	
	
	public ControllerEvents(FormHandle handler,User user) {
		mHandler = (FormListHandle) handler;
		mUser = user;
		mEvents = Main.getDataSet().eventManager().collection();
		mHandler.addStringAll(mEvents); 
		initListeners();
	}
	
	
	private void initListeners(){
		if (mUser.getPermissoes() != Permissoes.Admin){
			mHandler.getButton(FormButtonEnum.EDITAR).setEnabled(false);
			mHandler.getButton(FormButtonEnum.CRIAR_SALVAR).setEnabled(false);
			mHandler.getButton(FormButtonEnum.INICIAR).setEnabled(false);
		}
		mHandler.addButtonListener(FormButtonEnum.ADERIR, new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				adedir();
			}
		});
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
		mHandler.addButtonListener(FormButtonEnum.CRIAR_SALVAR, new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isEdit = mHandler.getTextIndex(FormButtonEnum.CRIAR_SALVAR) == 0; 
				setComponentsEnable(isEdit); // enable if isEdit
				if (isEdit){
					mHandler.setText2(FormButtonEnum.CRIAR_SALVAR);
				}else{
					mHandler.setText1(FormButtonEnum.CRIAR_SALVAR);
					saveProfileChanges();
				}
			}
		});
		mHandler.addButtonListener(FormButtonEnum.INICIAR, new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				iniciar();
			}

			
		});
		final JComboBox<?> records = (JComboBox<?>) mHandler.getComponent(FormAttEnum.RECORD_TYPE);
		JComboBox<?> a = (JComboBox<?>) mHandler.getComponent(FormAttEnum.ACTIVITY);
		a.setModel(new DefaultComboBoxModel(Main.getActivitiesNames()));
		a.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {	
				try {
					Activity a = Main.getActivity(arg0.getItem().toString());
					if (a instanceof Distance){
						records.setModel(new DefaultComboBoxModel() );
						setEvent(new EventDistance());
					}else if (a instanceof Contest){
						setEvent(new EventContest());
					}else{
						throw new EventNotPermited();
					}
					
					
				} catch (NameDontExistException e) {
					e.printStackTrace();
				} catch (EventNotPermited e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	
	
	
	private void setEvent(Event event){
		try {
			//int index = Main.getEventIndex(event.getName());
			JTextField nome = (JTextField) mHandler.getComponent(FormAttEnum.NOME);
			JTextField dateEvent = (JTextField) mHandler.getComponent(FormAttEnum.DATA_EVENT);
			JTextField dateFim = (JTextField) mHandler.getComponent(FormAttEnum.DATA_FIM);
			JTextField requisito = (JTextField) mHandler.getComponent(FormAttEnum.REQUISITO);
			JTextField nUser = (JTextField) mHandler.getComponent(FormAttEnum.NUM_USER);
			JComboBox<?> activitys = (JComboBox<?>) mHandler.getComponent(FormAttEnum.ACTIVITY);
			JComboBox<?> records = (JComboBox<?>) mHandler.getComponent(FormAttEnum.RECORD_TYPE);
			
			JTextField distance = (JTextField) mHandler.getComponent(FormAttEnum.DISTANCE);
			JTextField numJogos = (JTextField) mHandler.getComponent(FormAttEnum.NUM_JOGOS);
			JTextField pVit = (JTextField) mHandler.getComponent(FormAttEnum.PONTOS_VIT);
			JTextField pEmp = (JTextField) mHandler.getComponent(FormAttEnum.PONTOS_EMP);
			JTextField pDer = (JTextField) mHandler.getComponent(FormAttEnum.PONTOS_DER);	
			
			nome.setText(event.getName());
			dateEvent.setText(new SimpleDateFormat(FormUtils.DATA_PATTERM).format(event.getEventDate().getTime()));
			dateFim.setText(new SimpleDateFormat(FormUtils.DATA_PATTERM).format(event.getEndDate().getTime()));
			requisito.setText(String.valueOf(event.getPreRequisite()));
			nUser.setText(String.valueOf(event.getMaxNumUsers()));
		
			activitys.setSelectedIndex(Main.getActivityIndex(event.getActivity().getName()));
			
			
			if (event instanceof EventDistance){
				EventDistance e = (EventDistance) event;
				distance.setText(String.valueOf(e.getEventDistance()));
				
			}else if (event instanceof EventContest){
				EventContest e = (EventContest) event;
				numJogos.setText(String.valueOf(e.getNumGames()));
				pVit.setText(String.valueOf(e.getVicPts()));
				pDer.setText(String.valueOf(e.getLossPts()));
				pEmp.setText(String.valueOf(e.getDrawPts()));
			}
						
			
			distance.setVisible(event instanceof EventDistance);
			numJogos.setVisible(event instanceof EventContest);
			pVit.setVisible(event instanceof EventContest);
			pEmp.setVisible(event instanceof EventContest);
			pDer.setVisible(event instanceof EventContest);
		
			mHandler.getLabel(FormAttEnum.DISTANCE).setVisible(event instanceof EventDistance);
			mHandler.getLabel(FormAttEnum.NUM_JOGOS).setVisible(event instanceof EventContest);
			mHandler.getLabel(FormAttEnum.PONTOS_VIT).setVisible(event instanceof EventContest);
			mHandler.getLabel(FormAttEnum.PONTOS_EMP).setVisible(event instanceof EventContest);
			mHandler.getLabel(FormAttEnum.PONTOS_DER).setVisible(event instanceof EventContest);

			
			
		} catch (NameDontExistException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	private void saveProfileChanges() {
		
	}

	protected void setComponentsEnable(boolean b){
		for(FormAttEnum e: FormAttEnum .values()){
			mHandler.getComponent(e).setEnabled(b);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		int index = mHandler.getSelectIndex();
		mSelected = mEvents.get(index);
	}
	
	
	private void iniciar() {
		
		
	}
	
	private void adedir() {
		
	}
}
