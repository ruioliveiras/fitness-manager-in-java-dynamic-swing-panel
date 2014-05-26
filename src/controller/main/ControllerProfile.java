package controller.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import model.activity.Activity;
import model.user.Genero;
import model.user.Permissoes;
import model.user.User;
import view.main.panel.PanelProfile;
import view.main.panel.PanelProfile.FormAttEnum;
import view.main.panel.PanelProfile.FormButtonEnum;
import controller.ActivityNameDontExistException;
import controller.Main;
import core.FormUtils;
import core.FormUtils.FormHandle;

public class ControllerProfile {
	private FormHandle mHandler;
	private User mUser;
	
	public ControllerProfile(FormHandle handler, User user) {
		mHandler = handler; 
		mUser = user; 
		initListeners();
		initValues();
	}
	
	
	private void initListeners(){
		mHandler.addButtonListener(FormButtonEnum.EDITAR, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isEdit = mHandler.getTextIndex(FormButtonEnum.EDITAR) == 1; 
				setComponentsEnable(isEdit); // enable if isEdit
				if (isEdit){
					mHandler.setText2(FormButtonEnum.EDITAR);
				}else{
					mHandler.setText1(FormButtonEnum.EDITAR);
					saveProfileChanges();
				}
			
			}

			
		});
	}
	
	private void initValues(){
		SimpleDateFormat sdf = new SimpleDateFormat(FormUtils.DATA_PATTERM);
		
		mHandler.setValue(FormAttEnum.NAME, mUser.getNome());
		mHandler.setValue(FormAttEnum.EMAIL, mUser.getEmail());
		mHandler.setValue(FormAttEnum.PASS, mUser.getPassword());
		mHandler.setValue(FormAttEnum.ALTURA, String.valueOf(mUser.getAltura()));
		mHandler.setValue(FormAttEnum.PESO, String.valueOf(mUser.getPeso()));
		mHandler.setValue(FormAttEnum.SEXO, mUser.getGenero().toString());
		mHandler.setValue(FormAttEnum.PREFERIDO, mUser.getDesportoFavorito().getName());
		mHandler.setValue(FormAttEnum.NASCIMENTO, sdf.format(mUser.getDataNascimento().getTime()).toString());
		
	}
	
	
	private void saveProfileChanges() {
		
		String email,nome,password;
		int altura,peso;
		Genero genero;
		Calendar date;
		Activity desportoFavorito;
		
		try {
			email = mHandler.getValue(PanelProfile.FormAttEnum.EMAIL);
			nome = mHandler.getValue(PanelProfile.FormAttEnum.NAME);
			password = mHandler.getValue(PanelProfile.FormAttEnum.PASS);
			altura = Integer.parseInt(mHandler.getValue(PanelProfile.FormAttEnum.ALTURA));
			peso =  Integer.parseInt(mHandler.getValue(PanelProfile.FormAttEnum.PESO));
			String despAux = mHandler.getValue(PanelProfile.FormAttEnum.PREFERIDO);
			desportoFavorito = Main.getActivity(despAux);
			
			char g =  mHandler.getValue(PanelProfile.FormAttEnum.SEXO).toLowerCase().charAt(0); 
			genero = (g == 'm' ) ? Genero.Masculino : (g == 'f') ? Genero.Feminino : Genero.Desconhecido; 

			String d = mHandler.getValue(PanelProfile.FormAttEnum.NASCIMENTO);
			date = new GregorianCalendar();

			date.setTime((new SimpleDateFormat(FormUtils.DATA_PATTERM)).parse(d));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Data de Nascimento com formato invalido");
			/*Dont save*/
			return; 
		}catch (ActivityNameDontExistException e) {
			JOptionPane.showMessageDialog(null, "Desporto não existe");
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
	}
}
