package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;

import model.Dataset;
import model.activity.Natacao;
import model.activity.Weather;
import model.user.Genero;
import model.user.Permissoes;
import model.user.User;

public class Main {

	private static Dataset sDataSet;
	public static Dataset getDataSet(){return sDataSet;} 
	
	public static void main(String[] args) {
		System.out.println("Hello World!!");
		load();
		
		ControllerLogin login = new ControllerLogin();
		login.start();
		save();
	}

	
	
	public static void save(){
		 try{
	         ObjectOutputStream out = new ObjectOutputStream(
	        		 new FileOutputStream("state.obj"));
	         out.writeObject(sDataSet);
	         out.close();
	      
	      }catch(IOException e){e.printStackTrace(); }
	}

	public static void load(){
		  Dataset d = null;
	      try{
	         ObjectInputStream in = new ObjectInputStream(new FileInputStream("state.obj"));
	         d = (Dataset) in.readObject();
	         in.close();
	      }catch(IOException i){
	      }catch(ClassNotFoundException c){
	         c.printStackTrace();
	      }
	      sDataSet = (d != null) ? d : new Dataset();    
	}
	
	public static void loadSample(){
		User rui = new User("RUI Oliveira", "rui96pedro@hotmaail.com", "123", Genero.Masculino, 120, 87, 11, 10, 1994, "Natação", Permissoes.Admin, 0);
		User rui2 = new User("RUI Camposinhos", "ruiCamposinho@gmail.co.uk", "123", Genero.Masculino, 120, 87, 11, 10, 1994, "Basquetebol", Permissoes.Admin, 0);
		Natacao nata1 = new Natacao(70 * 1000,Weather.INDOOR,-1,new GregorianCalendar(),100,-1);
		Natacao nata2 = new Natacao(60 * 1000,Weather.INDOOR,-1,new GregorianCalendar(),100,-1);
		Natacao nata3 = new Natacao(50 * 1000,Weather.INDOOR,-1,new GregorianCalendar(),100,-1);
		rui.atividadesManager().add(nata1);
		rui.atividadesManager().add(nata2);
		rui.atividadesManager().add(nata3);
		
		sDataSet= new Dataset();
		sDataSet.userManager().add(rui2);
		sDataSet.userManager().add(rui);
	}
	
}
