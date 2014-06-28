package model;


import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import core.util.Manager;
import core.util.ManagerMap;


public class Dataset implements Serializable{
	private static final long serialVersionUID = -2526376303734576085L;

	private Manager<User> mUsers;
	private Manager<Event> mEvents;

	
	public Dataset() {
		mUsers = new ManagerMap<User>(new HashMap<Object, User>());
		mEvents = new ManagerMap<Event>(new HashMap<Object, Event>());
	}
	
	public Dataset(Manager<User> users){
		this();
		Iterator<User> ite = users.iterator();
		while(ite.hasNext()){
			mUsers.add(ite.next());
		}
		mEvents = new ManagerMap<Event>(new HashMap<Object, Event>());
	}
	
	public Dataset(Dataset users){
		this(users.userManager());
	}
	
	public Manager<User> userManager(){
		return mUsers;
	}
	public Manager<Event> eventManager(){
		return mEvents;
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
	


	
	
	
//  public final static Class<?>[] sActivitys = {Ciclismo.class,Basquetebol.class,Nata};  
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
