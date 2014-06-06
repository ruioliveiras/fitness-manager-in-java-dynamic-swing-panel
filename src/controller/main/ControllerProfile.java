package controller.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.activity.Activity;
import model.events.Event;
import model.user.Genero;
import model.user.Permissoes;
import model.user.User;
import view.main.panel.PanelProfile;
import view.main.panel.PanelProfile.FormAttEnum;
import view.main.panel.PanelProfile.FormButtonEnum;
import controller.Main;
import controller.NameDontExistException;
import core.FormUtils.FormHandle;
import core.FormUtils.SimpleListener;
import core.util.Manager.ObjectDontExistException;
import core.util.Util;

public class ControllerProfile {
	private FormHandle mHandler;
	private User mUser;
	private Permissoes mRight;
	private SimpleListener mOnDeleteAccountListener;
	
	public ControllerProfile(FormHandle handler, User user) {
		mHandler = handler; 
		mUser = user; 
		initListeners();
		initValues();
		mRight = user.getPermissoes();
		checkRight();
	}
	public void setUser(User user,Permissoes right){
		mUser = user;
		mRight = right;
		initValues();
		checkRight();
	}
	
	private void initListeners(){
		mHandler.addButtonListener(FormButtonEnum.EDITAR, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isEdit = mHandler.getTextIndex(FormButtonEnum.EDITAR) == 1; 
				setComponentsEnable(isEdit); // enable if isEdit
				if (isEdit){
					mHandler.setText2(FormButtonEnum.EDITAR);
					mHandler.getComponent(PanelProfile.FormAttEnum.EMAIL).setEnabled(false);
				}else{
					mHandler.setText1(FormButtonEnum.EDITAR);
					saveProfileChanges();
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
					apagarConta();
					if (mOnDeleteAccountListener != null){
						mOnDeleteAccountListener.action(mUser);
					}
				}
			
			}

			
		});
		
	}
	
	@SuppressWarnings("unchecked")
	private void initValues(){
		((JComboBox<String>) mHandler.getComponent(PanelProfile.FormAttEnum.PREFERIDO))
			.setModel(new DefaultComboBoxModel<String>(Main.getActivitiesNames()));

		
		mHandler.setValue(FormAttEnum.NAME, mUser.getNome());
		mHandler.setValue(FormAttEnum.EMAIL, mUser.getEmail());
		mHandler.setValue(FormAttEnum.PASS, mUser.getPassword());
		mHandler.setValue(FormAttEnum.ALTURA, String.valueOf(mUser.getAltura()));
		mHandler.setValue(FormAttEnum.PESO, String.valueOf(mUser.getPeso()));
		mHandler.setValue(FormAttEnum.FREQ, String.valueOf(mUser.getFreqCardio()));
		 DecimalFormat decimalF = new DecimalFormat("###%");
		mHandler.setValue(FormAttEnum.FORMA, decimalF.format(mUser.getForma()));
		mHandler.setValue(FormAttEnum.SEXO, mUser.getGenero().toString());
		try {
			mHandler.setValue(FormAttEnum.PREFERIDO, Main.getActivityIndex(mUser.getDesportoFavorito().getName()));
			mHandler.setValue(FormAttEnum.NASCIMENTO,Util.dateFormat_only(mUser.getDataNascimento()));
		} catch (NameDontExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addOnDeleteAcount(SimpleListener simpleListener) {
		mOnDeleteAccountListener = simpleListener;
		
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

	private void apagarConta(){
		try {mUser = Main.getDataSet().userManager().get(mUser); 

		//Todos os amigos
		for(String email:mUser.amigosManager().collection()){
			User u = Main.getDataSet().userManager().get(new User(email));
			u.amigosManager().remove(mUser.getEmail());
			Main.getDataSet().userManager().edit(u);
		}
		//Apagar dos Eventos
		List<Event> levents = Main.getDataSet().eventManager().collection();
		for (Event event : levents) {
			if (event.getUserManager().contains(mUser.getEmail()) ){
				event.getUserManager().remove(mUser.getEmail());
				Main.getDataSet().eventManager().edit(event);
			}

		}
		Main.getDataSet().userManager().remove(mUser);
		Main.save();
		}catch (ObjectDontExistException e) {


		}
	}

	private void saveProfileChanges() {

		String email,nome,password;
		int altura,peso;
		Genero genero;
		Calendar date;
		Activity desportoFavorito;
		
		try {
			email = (String) mHandler.getValue(PanelProfile.FormAttEnum.EMAIL);
			nome = (String) mHandler.getValue(PanelProfile.FormAttEnum.NAME);
			password = (String) mHandler.getValue(PanelProfile.FormAttEnum.PASS);
			altura = Integer.parseInt((String) mHandler.getValue(PanelProfile.FormAttEnum.ALTURA));
			peso =  Integer.parseInt((String) mHandler.getValue(PanelProfile.FormAttEnum.PESO));
			Integer despAux =(Integer) mHandler.getValue(PanelProfile.FormAttEnum.PREFERIDO);
			desportoFavorito = Main.getActivity(despAux);
			
			char g =  ((String)mHandler.getValue(PanelProfile.FormAttEnum.SEXO)).toLowerCase().charAt(0); 
			genero = (g == 'm' ) ? Genero.Masculino : (g == 'f') ? Genero.Feminino : Genero.Desconhecido; 

			String d =(String) mHandler.getValue(PanelProfile.FormAttEnum.NASCIMENTO);
			date = new GregorianCalendar();

			date.setTimeInMillis(Util.dateFormat_only(d));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Data de Nascimento com formato invalido");
			/*Dont save*/
			return; 
		}catch (StringIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Introduzir sexo");
			return;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Números incorrectos");
			return;
		}catch (NullPointerException e){
			JOptionPane.showMessageDialog(null, "Erro, faltou preencher alguma coisa");
			/*Dont save*/
			return; 
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Erro fatal");
			e.printStackTrace();
			/*Dont save*/
			return; 
		}

		User u = new User(nome, email, password, genero, altura, peso, 
				date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.MONTH), date.get(Calendar.YEAR), desportoFavorito, Permissoes.User, 0);
		Main.getDataSet().userManager().add(u);
		Main.save();

	}
	
	protected void setComponentsEnable(boolean b){
		for(FormAttEnum e: FormAttEnum .values()){
			mHandler.getComponent(e).setEnabled(b);
		}
		mHandler.getComponent(PanelProfile.FormAttEnum.FORMA).setEnabled(false);
	}
}
