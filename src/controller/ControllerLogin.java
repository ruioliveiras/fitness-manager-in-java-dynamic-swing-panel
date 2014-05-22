package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

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
	}

	private void loginOK(User user) {
		mViewLogin.hide();
		mControllerMain = new ControllerMain(user);
		mControllerMain.start();
	}
	private void register(){
		mViewRegister.show();
		mViewRegister.getHandler().setText2(PanelProfile.FormButtonEnum.EDITAR);
		mViewRegister.getHandler().addButtonListener(PanelProfile.FormButtonEnum.EDITAR, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String email,nome,password,desportoFavorito;
				int altura,peso;
				Genero genero;
				Calendar date;
				
				try {
					email = mViewRegister.getHandler().getValue(PanelProfile.FormAttEnum.EMAIL);
					nome = mViewRegister.getHandler().getValue(PanelProfile.FormAttEnum.NAME);
					password = mViewRegister.getHandler().getValue(PanelProfile.FormAttEnum.PASS);
					altura = Integer.parseInt(mViewRegister.getHandler().getValue(PanelProfile.FormAttEnum.ALTURA));
					peso =  Integer.parseInt(mViewRegister.getHandler().getValue(PanelProfile.FormAttEnum.PESO));
					desportoFavorito = mViewRegister.getHandler().getValue(PanelProfile.FormAttEnum.PREFERIDO);

					char g =  mViewRegister.getHandler().getValue(PanelProfile.FormAttEnum.SEXO).toLowerCase().charAt(0); 
					genero = (g == 'm' ) ? Genero.Masculino : (g == 'f') ? Genero.Feminino : Genero.Desconhecido; 

					String d = mViewRegister.getHandler().getValue(PanelProfile.FormAttEnum.NASCIMENTO);
					date = new GregorianCalendar();

					date.setTime((new SimpleDateFormat("dd/MM/yyyy")).parse(d));
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "Data de Nascimento com formato invalido");
					/*Dont save*/
					return; 
				}catch (NumberFormatException e) {
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
				mViewRegister.hide();
			}
		});
	}

	private void initListeners(){
		mHandler.addButtonListener(FormButtonEnum.ENTRAR, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String email = mHandler.getValue(FormAttEnum.EMAIL);
				String pass = mHandler.getValue(FormAttEnum.PASS);
				User user;
				try {
					user = Main.getDataSet().userManager().get(new User(email));
					if (user != null && user.getPassword().equals(pass)){
						loginOK(user);
					}else{
						JOptionPane.showMessageDialog(null, "Invalid pass or email");
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
	}

	public static void main(String[] args) {
		ControllerLogin cl = new ControllerLogin();
		cl.start();
		
	}
}
