package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.activity.Activity;
import model.user.Genero;
import model.user.Permissoes;
import model.user.User;
import view.FormLogin;
import view.FormRegister;
import view.main.panel.PanelLogin.FormAttEnum;
import view.main.panel.PanelLogin.FormButtonEnum;
import view.main.panel.PanelProfile;
import core.FormUtils.FormHandle;
import core.util.Manager.ObjectDontExistException;
import core.util.Util;

public class ControllerLogin{
	private FormLogin mViewLogin;
	private FormRegister mViewRegister;
	private ControllerMain mControllerMain;
	private FormHandle mHandler;
	
	public ControllerLogin(){
		mViewLogin = new FormLogin();		
		mViewRegister = new FormRegister();
		mHandler = mViewLogin.getHandler();
		initListeners();
	}
	
	public void start(){
		mViewLogin.show();
		setComponentsEnable(true);
	}

	private void loginOK(User user) {
		mViewLogin.hide();
		mControllerMain = new ControllerMain(user);
		mControllerMain.start();
	}

	@SuppressWarnings("unchecked")
	private void register(){
		mViewRegister = new FormRegister();
		mViewRegister.show();
		mViewRegister.getHandler().setText2(PanelProfile.FormButtonEnum.EDITAR);
		mViewRegister.getHandler().getButton(PanelProfile.FormButtonEnum.APAGAR).setVisible(false);
		mViewRegister.getHandler().getComponent(PanelProfile.FormAttEnum.FORMA).setVisible(false);
		mViewRegister.getHandler().setValue(PanelProfile.FormAttEnum.NASCIMENTO,Util.dateFormat_only(new GregorianCalendar()));
		((JComboBox<String>) mViewRegister.getHandler().getComponent(PanelProfile.FormAttEnum.PREFERIDO)).setModel(new DefaultComboBoxModel<String>(Main.getActivitiesNames()));
		mViewRegister.getHandler().addButtonListener(PanelProfile.FormButtonEnum.EDITAR, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String email,nome,password;
				int altura,peso;
				Genero genero;
				Calendar date;
				Activity desportoFavorito;
				
				try {
					email = (String) mViewRegister.getHandler().getValue(PanelProfile.FormAttEnum.EMAIL);
					
					nome = (String) mViewRegister.getHandler().getValue(PanelProfile.FormAttEnum.NAME);
					password =(String) mViewRegister.getHandler().getValue(PanelProfile.FormAttEnum.PASS);
					altura = Integer.parseInt((String) mViewRegister.getHandler().getValue(PanelProfile.FormAttEnum.ALTURA));
					peso =  Integer.parseInt((String) mViewRegister.getHandler().getValue(PanelProfile.FormAttEnum.PESO));
					int despAux =	((JComboBox<String>) mViewRegister.getHandler().getComponent(PanelProfile.FormAttEnum.PREFERIDO)).getSelectedIndex();
					desportoFavorito = Main.getActivity(despAux);
					
					String sexoString =((String)mViewRegister.getHandler().getValue(PanelProfile.FormAttEnum.SEXO));
					char g =  sexoString.toLowerCase().charAt(0); 
					if (sexoString.length() == 1 &&  ((g == 'f') || (g == 'm') || (g == 'd') )){
						genero = (g == 'm' ) ? Genero.Masculino : (g == 'f') ? Genero.Feminino : Genero.Desconhecido; 
					}else{
						throw new StringIndexOutOfBoundsException();
					}
						
					

					String d =(String) mViewRegister.getHandler().getValue(PanelProfile.FormAttEnum.NASCIMENTO);
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
				if (Main.getDataSet().userManager().contains(u)){
					JOptionPane.showMessageDialog(null, "Email já existe");
				}else{
					Main.getDataSet().userManager().add(u);
					Main.save();
					mViewRegister.hide();
				}
			}
		});
	}

	private void initListeners(){
		mHandler.addButtonListener(FormButtonEnum.ENTRAR, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String email = (String)mHandler.getValue(FormAttEnum.EMAIL);
				String pass = (String)mHandler.getValue(FormAttEnum.PASS);
				User user;
				try {
					//autoLogin
					if (email.equals("")){
						loginOK(Main.getDataSet().userManager().get(new User("rui96pedro@hotmail.com")));
					}else{
						user = Main.getDataSet().userManager().get(new User(email));
						if (user != null && user.getPassword().equals(pass)){
							loginOK(user);
						}else{
							JOptionPane.showMessageDialog(null, "Invalid pass or email");
						}
					}
				} catch (ObjectDontExistException e) {
					JOptionPane.showMessageDialog(null, "Email don't exist");
				}
			
			}
		});
		mHandler.addButtonListener(FormButtonEnum.REGISTAR, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				register();
			}
		});
		mHandler.addButtonListener(FormButtonEnum.CLEAN, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.clean();
			}
		});
	}

	protected void setComponentsEnable(boolean b){
		for(FormAttEnum e: FormAttEnum .values()){
			mHandler.getComponent(e).setEnabled(b);
		}
	}
	
}
