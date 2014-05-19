package model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import core.util.Manager;
import model.activity.Basquetebol;
import model.user.Genero;
import model.user.Permissoes;
import model.user.User;


public class Dataset {

	
	private Manager<User> mUsers;
	
	public Dataset() {
		mUsers = new Manager<User>(new HashMap<Object, User>());
	}
	
	public Dataset(Manager<User> users){
		this();
		Iterator<User> ite = users.iterator();
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
	
	public static void main(String[] args) {
		Dataset a = new Dataset();
		User rui = new User("RUI Oliveira", "rui96pedro@hotmaail.com", "123", Genero.Masculino, 120, 87, 11, 10, 1994, "Natação", Permissoes.Admin, 0);
		User rui2 = new User("RUI Camposinhos", "ruiCamposinho@gmail.co.uk", "123", Genero.Masculino, 120, 87, 11, 10, 1994, "Basquetebol", Permissoes.Admin, 0);
		User test = new User();
		Basquetebol a1 = new Basquetebol();
		rui.addActivityRecord(a1);
		test.setEmail("rui96pedro@hotmaail.com");
		a.userManager().add(rui2);
		a.userManager().add(rui);
		User b = a.userManager().get(test);
		System.out.print("SUSSES" + b.getNome());
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
