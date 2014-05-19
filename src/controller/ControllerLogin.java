package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Dataset;
import model.user.Genero;
import model.user.Permissoes;
import model.user.User;
import core.FormUtils.FormHandle;
import view.ViewLogin;
import view.main.panel.PanelLogin.FormAttEnum;
import view.main.panel.PanelLogin.FormButtonEnum;

public class ControllerLogin{
	private ViewLogin mViewLogin;
	private FormHandle mHandler;
	private Dataset mDataset;
	
	public ControllerLogin(){
		mViewLogin = new ViewLogin();		
		mHandler = mViewLogin.getHandler();
		mDataset = new Dataset();
		User rui = new User("RUI Oliveira", "rui96pedro@hotmail.com", "123", Genero.Masculino, 120, 87, 11, 10, 1994, "Natação", Permissoes.Admin, 0);
		
		mDataset.userManager().add(rui);
		initListeners();
	}
	
	public void start(){
		mViewLogin.show();
	}

	private void loginOK(User user) {
		System.out.println("LOGINok pass or email");
	}

	private void initListeners(){
		mHandler.addButtonListener(FormButtonEnum.ENTRAR, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String email = mHandler.getValue(FormAttEnum.EMAIL);
				String pass = mHandler.getValue(FormAttEnum.PASS);
				User user= mDataset.userManager().get(new User(email));
				if (user != null && user.getPassword().equals(pass)){
					loginOK(user);
				}else{
					System.out.println("Invalid pass or email");
				}
				
				
			}
		});
		mHandler.addButtonListener(FormButtonEnum.REGISTAR, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
	}

	public static void main(String[] args) {
		ControllerLogin cl = new ControllerLogin();
		cl.start();
		
	}
}
