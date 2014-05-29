package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;

import model.Dataset;
import model.activity.Activity;
import model.activity.Aerobica;
import model.activity.Andebol;
import model.activity.ArtesMarciais;
import model.activity.BTT;
import model.activity.Badminton;
import model.activity.Basquetebol;
import model.activity.Boxe;
import model.activity.Caiaque;
import model.activity.Caminhada;
import model.activity.CaminhadaMontanha;
import model.activity.Ciclismo;
import model.activity.Corrida;
import model.activity.CorridaOrientacao;
import model.activity.Danca;
import model.activity.Escalada;
import model.activity.Esgrima;
import model.activity.Futebol;
import model.activity.Ginasio;
import model.activity.Ginastica;
import model.activity.Golfe;
import model.activity.Hipismo;
import model.activity.Hoquei;
import model.activity.Ioga;
import model.activity.Mergulho;
import model.activity.Natacao;
import model.activity.Pilates;
import model.activity.Polo;
import model.activity.Raguebi;
import model.activity.Remo;
import model.activity.Skate;
import model.activity.SkiDownhill;
import model.activity.Snowboard;
import model.activity.Squash;
import model.activity.Surf;
import model.activity.Tenis;
import model.activity.TenisMesa;
import model.activity.Vela;
import model.activity.Voleibol;
import model.activity.VoleibolPraia;
import model.activity.Weather;
import model.activity.WindSurf;
import model.user.Genero;
import model.user.Permissoes;
import model.user.User;

public class Main {

	private static Dataset sDataSet;
	public static Dataset getDataSet(){return sDataSet;} 
	private final static Activity sActivivitys[] = { 
		new Aerobica(),
		new Andebol(),
		new ArtesMarciais(),
		new Badminton(),
		new Basquetebol(),
		new Boxe(),
		new BTT(),
		new Caiaque(),
		new Caminhada(),
		new CaminhadaMontanha(),
		new Ciclismo(),
		new Corrida(),
		new CorridaOrientacao(),
		new Danca(),
		new Escalada(),
		new Esgrima(),
		new Futebol(),
		new Ginasio(),
		new Ginastica(),
		new Golfe(),
		new Hipismo(),
		new Hoquei(),
		new Ioga(),
		new Mergulho(),
		new Natacao(),
		new Pilates(),
		new Polo(),
		new Raguebi(),
		new Remo(),
		new Skate(),
		new SkiDownhill(),
		new Snowboard(),
		new Squash(),
		new Surf(),
		new Tenis(),
		new TenisMesa(),
		new Vela(),
		new Voleibol(),
		new VoleibolPraia(),
		new WindSurf()
	};
	public static String getActivityName(int index){return sActivivitys[index].getName();} 
	public static Activity getActivity(int index){return sActivivitys[index].clone();}
	public static Activity getActivity(String name) throws NameDontExistException{
		return getActivity(getActivityIndex(name));
	}
	public static int getActivityIndex(String name) throws NameDontExistException{
		
		for (int i = 0; i < sActivivitys.length; i++) {
			if (sActivivitys[i].getName().equals(name)){
				return i;
			}		
		}
		throw new NameDontExistException();
	}

	
	
	
	public static String[] getActivitiesNames() {
		String[] array = new String[sActivivitys.length];
		
		for (int i = 0; i < sActivivitys.length; i++) {
			array[i] = sActivivitys[i].getName();
		}
		return array;
	}
	
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
	      Object read;
		  try{
	         ObjectInputStream in = new ObjectInputStream(new FileInputStream("state.obj"));
	         read = in.readObject(); 
	         if (read instanceof Dataset){
	        	 d = (Dataset) read;
	         }
	         in.close();
	      }catch(IOException i){
	      }catch(ClassNotFoundException c){
	         c.printStackTrace();
	      }
		  try {
			if (d.userManager().collection().size() > 0){
				sDataSet = d;
			}else{
				loadSample();
			}
				
		} catch (Exception e) {
			loadSample();
		}
    
	}
	
	public static void loadSample(){
		User rui = new User("RUI Oliveira", "rui96pedro@hotmail.com", "123", Genero.Masculino, 120, 87, 11, 10, 1994, new Natacao(), Permissoes.Admin, 0);
		User rui2 = new User("RUI Camposinhos", "ruiCamposinho@gmail.co.uk", "123", Genero.Masculino, 120, 87, 11, 10, 1994, new Natacao(), Permissoes.Admin, 0);
		Natacao nata1 = new Natacao(70 * 1000,new Weather(Weather.Local.INDOOR),-1,new GregorianCalendar(2013,6,1),150,-1);
		Natacao nata2 = new Natacao(60 * 1000,new Weather(Weather.Local.INDOOR),-1,new GregorianCalendar(2013,6,2),100,-1);
		Natacao nata3 = new Natacao(50 * 1000,new Weather(Weather.Local.INDOOR),-1,new GregorianCalendar(2013,6,3),100,-1);
		rui.atividadesManager().add(nata1);
		rui.atividadesManager().add(nata2);
		rui.atividadesManager().add(nata3);
		
		sDataSet= new Dataset();
		sDataSet.userManager().add(rui2);
		sDataSet.userManager().add(rui);
	}
	public static void clean() {
		loadSample();
		save();
	}


	
	
}
