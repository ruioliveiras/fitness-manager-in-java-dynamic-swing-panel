package model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import model.activity.Basquetebol;
import model.activity.Natacao;
import model.activity.Weather;
import model.user.Genero;
import model.user.Permissoes;
import model.user.User;
import core.util.Manager;
import core.util.Manager.ObjectDontExistException;
import core.util.ManagerMap;


public class Dataset implements Serializable{

	
	private Manager<User> mUsers;
	
	public Dataset() {
		mUsers = new ManagerMap<User>(new HashMap<Object, User>());
	}
	
	public Dataset(Manager<User> users){
		this();
		Object a,b;
		Iterator<User> ite = users.iterator();
		Iterator<User> ite2 = users.iterator();
		
		for (int i = 0; ite.hasNext(); a =ite.next(),b =ite2.next()) {
			
		}
		
		while(ite.hasNext()){
			mUsers.add(ite.next());
		}
	}
	
	public Dataset(Dataset users){
		this(users.userManager());
	}
	
	public Manager<User> userManager(){
		return mUsers;
	}
	

	public final static Comparator<User> ORDER_USER_ALFA = new Comparator<User>() {
		
		@Override
		public int compare(User o1, User o2) {
			return o1.getNome().compareTo(o2.getNome());
		}
	};
	
	public final static Comparator<User> ORDER_USER_SIMPLE = new Comparator<User>() {
		@Override
		public int compare(User o1, User o2) {
			return 1;
		}
		
	};
	
	static test foo;
	
	interface test{
		public void dooo(int a);
	}
	
	public static void main(String[] args) {
		Dataset a = new Dataset();
		User rui = new User("RUI Oliveira", "rui96pedro@hotmaail.com", "123", Genero.Masculino, 120, 87, 11, 10, 1994, "Natação", Permissoes.Admin, 0);
		User rui2 = new User("RUI Camposinhos", "ruiCamposinho@gmail.co.uk", "123", Genero.Masculino, 120, 87, 11, 10, 1994, "Basquetebol", Permissoes.Admin, 0);
		User test = new User();
		Natacao nata1 = new Natacao(70 * 1000,Weather.INDOOR,-1,new GregorianCalendar(),100,-1);
		Natacao nata2 = new Natacao(60 * 1000,Weather.INDOOR,-1,new GregorianCalendar(),100,-1);
		Natacao nata3 = new Natacao(50 * 1000,Weather.INDOOR,-1,new GregorianCalendar(),100,-1);
		rui.atividadesManager().add(nata1);
		rui.atividadesManager().add(nata2);
		rui.atividadesManager().add(nata3);
		test.setEmail("rui96pedro@hotmaail.com");
		a.userManager().add(rui2);
		a.userManager().add(rui);
		User b;
		try {
			b = a.userManager().get(test);
			System.out.print("SUSSES" + b.getNome());
		} catch (ObjectDontExistException e) {
			e.printStackTrace();
		}
		
	}
	
//	public final static Class<?>[] sActivitys = {Ciclismo.class,Basquetebol.class};  
//	public static void teste(){
//		boolean t = Activity.class.isAssignableFrom(sActivitys[0]);
//		Activity a;
//		try {
//			Constructor<?> construct = sActivitys[0].getDeclaredConstructor();
//			a = (Activity)construct.newInstance();
//			
//			System.out.print(a.getName());
//		} catch (NoSuchMethodException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}
//	
//	public static void main(String[] args) {
//		teste();
//	}

}
