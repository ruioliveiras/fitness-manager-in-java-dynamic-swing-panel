package model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import model.user.User;

public class UserGroup {
	public enum OrderUser{
		ALFA_NUMERIC(sComparatorUserAlfa),
		NO_ORDER(sComparatorUserSimple);
		
		private Comparator<User> eComp;
		
		OrderUser(Comparator<User> comp){
			eComp = comp;
		}
		
		private Comparator<User> getComp(){return eComp;};
	}
	public enum classManager{
		
	}
	
	private HashMap<String, User> mUsers;
	
	public UserGroup() {
		mUsers = new HashMap<String, User>();
	}
	
	public UserGroup(Map<String,User> users){
		for (User user : users.values()) {
			mUsers.put(user.getEmail(),user.clone());
		}
	}
	public UserGroup(UserGroup users){
		Iterator<User> ite = users.getUsers(OrderUser.NO_ORDER);
		User user;
		while (ite.hasNext()){
			user = ite.next();
			mUsers.put(user.getEmail(),user); /*No need to clone user, because getUsers return clones*/
		}
	}
	

	
	public Iterator<User> getUsers(OrderUser order){
		TreeSet<User> users = new TreeSet<User>(order.getComp());
		for (User user : mUsers.values()) {
			users.add(user.clone());
		}
		return users.iterator();
	}
	
	public void addUser(User user){
		if (user == null)
			return;	
		mUsers.put(user.getEmail(),user.clone());
	}
	
	public User getUser(String email){
		
		User u = mUsers.get(email);
		return (u==null) ? null : u.clone();
	}
	
	
	
	private final static Comparator<User> sComparatorUserAlfa = new Comparator<User>() {
		
		@Override
		public int compare(User o1, User o2) {
			return o1.getNome().compareTo(o2.getNome());
		}
	};
	private final static Comparator<User> sComparatorUserSimple = new Comparator<User>() {
		
		@Override
		public int compare(User o1, User o2) {
			return 1;
		}
		
	};
}