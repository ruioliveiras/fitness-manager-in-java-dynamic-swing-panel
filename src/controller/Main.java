package controller;

import java.io.*;
import java.util.*;
import model.*;
import model.activity.*;
import model.user.*;
import model.events.*;

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
        
        
        
        User u1 = new User("RUI Oliveira", "rui96pedro@hotmail.com", "123", Genero.Masculino, 120, 87, 11, 10, 1994, new Natacao(), Permissoes.Admin, 0);
        User u2 = new User("RUI Camposinhos", "ruiCamposinho@gmail.co.uk", "123", Genero.Masculino, 120, 87, 11, 10, 1994, new Natacao(), Permissoes.Admin, 0);
        User u3 = new User("Joao Mendes", "joaomendes@gmail.com", "123", Genero.Masculino, 135, 70, 1, 7, 1991, new Andebol(), Permissoes.User, 0);
        User u4 = new User("Ana Sousa", "anasousa@gmail.com", "123", Genero.Feminino, 140, 60, 7, 2, 1989, new Corrida(), Permissoes.User, 0);
        User u5 = new User("Carlos Silva", "carlossilva@gmail.com", "123", Genero.Masculino, 150, 75, 3, 10, 1988, new Golfe(), Permissoes.User, 0);
        
        
        Natacao nata1 = new Natacao(70 * 1000,new Weather(Weather.Local.INDOOR),-1,new GregorianCalendar(2013,6,1),150,-1);
        Natacao nata2 = new Natacao(60 * 1000,new Weather(Weather.Local.INDOOR),-1,new GregorianCalendar(2013,6,2),100,-1);
        Natacao nata3 = new Natacao(100 * 1000,new Weather(Weather.Local.INDOOR),-1,new GregorianCalendar(2013,GregorianCalendar.JANUARY,15),100,-1);
        Natacao nata4 = new Natacao(120 * 1000,new Weather(Weather.Local.INDOOR),-1,new GregorianCalendar(2013,GregorianCalendar.NOVEMBER,22),100,-1);
        Natacao nata5 = new Natacao(90 * 1000,new Weather(Weather.Local.INDOOR),-1,new GregorianCalendar(2013,GregorianCalendar.JULY,25),100,-1);
        
        Ciclismo cicl1 = new Ciclismo(30 * 60 * 1000, new Weather(Weather.WeatherSun.SUN, Weather.WeatherWind.WINDLESS), -1,new GregorianCalendar(2013,GregorianCalendar.JANUARY,2,15,30),
        5000, 50, 100, 50, 300, 350);
        Ciclismo cicl2 = new Ciclismo(35 * 60 * 1000, new Weather(Weather.WeatherSun.SUN, Weather.WeatherWind.WINDLESS), -1,new GregorianCalendar(2013,GregorianCalendar.MARCH,2,17,0),
        5000, 50, 100, 50, 300, 350);
        Ciclismo cicl3 = new Ciclismo(44 * 60 * 1000, new Weather(Weather.WeatherSun.SUN, Weather.WeatherWind.WINDLESS), -1,new GregorianCalendar(2014,GregorianCalendar.FEBRUARY,22,8,30),
        5000, 60, 50, 100, 300, 320);
        Ciclismo cicl4 = new Ciclismo(51 * 60 * 1000, new Weather(Weather.WeatherSun.SUN, Weather.WeatherWind.WINDLESS), -1,new GregorianCalendar(2014,GregorianCalendar.JUNE,2,14,0),
        6000, 45, 100, 50, 100, 130);
        Ciclismo cicl5 = new Ciclismo(55 * 60 * 1000, new Weather(Weather.WeatherSun.SUN, Weather.WeatherWind.WINDLESS), -1,new GregorianCalendar(),
        5500, 55, 200, 50, 100, 250);
        
        Basquetebol basq1 = new Basquetebol(3000 * 1000, new Weather(Weather.Local.INDOOR), -1,new GregorianCalendar(2013,GregorianCalendar.JUNE,15,16,30)
        , 100, 120, Contest.Result.WIN);
        Basquetebol basq2 = new Basquetebol(2000 * 1000, new Weather(Weather.Local.INDOOR), -1,new GregorianCalendar(2013,GregorianCalendar.NOVEMBER,23,18,30)
        , 130, 100, Contest.Result.LOSE);
        Basquetebol basq3 = new Basquetebol(5000 * 1000, new Weather(Weather.Local.INDOOR), -1,new GregorianCalendar(2013,GregorianCalendar.OCTOBER,3,18,30)
        , 90, 100, Contest.Result.WIN);
        Basquetebol basq4 = new Basquetebol(5000 * 1000, new Weather(Weather.Local.INDOOR), -1,new GregorianCalendar(2013,GregorianCalendar.OCTOBER,3,15,30)
        , 90, 100, Contest.Result.WIN);
        Basquetebol basq5 = new Basquetebol(5000 * 1000, new Weather(Weather.Local.INDOOR), -1,new GregorianCalendar(2013,GregorianCalendar.DECEMBER,10,19,30)
        , 80, 120, Contest.Result.WIN);
        
        Golfe golfe1 = new Golfe(5000 * 1000, new Weather(Weather.Local.OUTDOOR), -1,new GregorianCalendar(2013,GregorianCalendar.NOVEMBER,23,18,30)
        , 30, 40);
        Golfe golfe2 = new Golfe(2500 * 1000, new Weather(Weather.Local.OUTDOOR), -1,new GregorianCalendar(2014,GregorianCalendar.JUNE,2,14,0)
        , 25, 50);
        Golfe golfe3 = new Golfe(5500 * 1000, new Weather(Weather.Local.OUTDOOR), -1,new GregorianCalendar(2014,GregorianCalendar.MAY,22,14,0)
        , 35, 60);
        Golfe golfe4 = new Golfe(3000 * 1000, new Weather(Weather.Local.OUTDOOR), -1,new GregorianCalendar(2013,GregorianCalendar.SEPTEMBER,13,14,0)
        , 40, 45);
        Golfe golfe5 = new Golfe(7000 * 1000, new Weather(Weather.Local.OUTDOOR), -1,new GregorianCalendar(2014,GregorianCalendar.JANUARY,18,15,0)
        , 33, 40);
        
        Ioga ioga1 = new Ioga(3500 *1000, new Weather(Weather.Local.INDOOR), -1, new GregorianCalendar(2014,GregorianCalendar.APRIL,7,10,0));
        Ioga ioga2 = new Ioga(4500 *1000, new Weather(Weather.Local.INDOOR), -1, new GregorianCalendar(2014,GregorianCalendar.JULY,11,9,0));
        Ioga ioga3 = new Ioga(5000 *1000, new Weather(Weather.Local.INDOOR), -1, new GregorianCalendar(2014,GregorianCalendar.SEPTEMBER,26,17,0));
        Ioga ioga4 = new Ioga(2500 *1000, new Weather(Weather.Local.INDOOR), -1, new GregorianCalendar(2014,GregorianCalendar.OCTOBER,30,15,0));
        Ioga ioga5 = new Ioga(1000 *1000, new Weather(Weather.Local.INDOOR), -1, new GregorianCalendar(2014,GregorianCalendar.JANUARY,2,14,30));
        
        Event ev1 = new EventDistance("Volta a UM", new Ciclismo(), new GregorianCalendar(), new GregorianCalendar(2014,GregorianCalendar.JUNE,3),3600 * 1000
        ,4, Distance.MyRecords.MENOR_TEMPO5000.getrecordType(), 5000);
        try{
        ev1.addUser(u1);
        ev1.addUser(u2);
        ev1.addUser(u3);
        ev1.addUser(u4);
        }catch(AddEventException e){
            System.out.println("Excepcao ao adicionar user ao evento:" + e.getMessage());
        }
        
        try{
        ev1.addUser(u5);
        }catch(AddEventException e){
            System.out.println("Excepcao ao adicionar user5 ao evento:" + e.getMessage());
        }

        
        u1.atividadesManager().add(nata1);
        u2.atividadesManager().add(nata2);
        u3.atividadesManager().add(nata3);
        u4.atividadesManager().add(nata4);
        u5.atividadesManager().add(nata5);
        
        u1.atividadesManager().add(cicl1);
        u2.atividadesManager().add(cicl2);
        u3.atividadesManager().add(cicl3);
        u4.atividadesManager().add(cicl4);
        u5.atividadesManager().add(cicl5);
        
        u1.atividadesManager().add(basq1);
        u2.atividadesManager().add(basq2);
        u3.atividadesManager().add(basq3);
        u4.atividadesManager().add(basq4);
        u5.atividadesManager().add(basq5);
        
        u1.atividadesManager().add(golfe1);
        u2.atividadesManager().add(golfe2);
        u3.atividadesManager().add(golfe3);
        u4.atividadesManager().add(golfe4);
        u5.atividadesManager().add(golfe5);
        
        u1.atividadesManager().add(ioga1);
        u2.atividadesManager().add(ioga2);
        u3.atividadesManager().add(ioga3);
        u4.atividadesManager().add(ioga4);
        u5.atividadesManager().add(ioga5);
        
        
        
        sDataSet = new Dataset();
        sDataSet.userManager().add(u1);
        sDataSet.userManager().add(u2);
        sDataSet.userManager().add(u3);
        sDataSet.userManager().add(u4);
        sDataSet.userManager().add(u5);
        sDataSet.eventManager().add(ev1);
    }
    public static void clean() {
        loadSample();
        save();
    }


    
    
}
