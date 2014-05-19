package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Dataset;
import model.user.Genero;
import model.user.Permissoes;
import model.user.User;
import view.ViewLogin;
import view.ViewMain;
import view.main.panel.PanelLogin.FormAttEnum;
import view.main.panel.PanelLogin.FormButtonEnum;
import core.FormUtils.FormHandle;

public class ControllerMain {
	private ViewMain mViewMain;
	private Dataset mDataset;
	
	public ControllerMain(User u){
		mViewMain = new ViewMain(u.getNome(),(String[]) u.amigosManager().collection().toArray() );		
		mDataset = new Dataset();
		User rui = new User("RUI Oliveira", "rui96pedro@hotmail.com", "123", Genero.Masculino, 120, 87, 11, 10, 1994, "Natação", Permissoes.Admin, 0);
		
		mDataset.userManager().add(rui);
		initListeners();
	}
	
	public void start(){
		mViewMain.show();
	}

	private void loginOK(User user) {
		System.out.println("LOGINok pass or email");
	}

	private void initListeners(){
		
	}

	public static void main(String[] args) {
		ControllerLogin cl = new ControllerLogin();
		cl.start();
		
	}
}
