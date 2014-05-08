package model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import model.activity.Activity;
import model.activity.Basquetebol;
import model.activity.Ciclismo;
import model.user.User;


public class Dataset {
	
	
	public final static Class<?>[] sActivitys = {Ciclismo.class,Basquetebol.class};  
	public static void teste(){
		boolean t = Activity.class.isAssignableFrom(sActivitys[0]);
		Activity a;
		try {
			Constructor<?> construct = sActivitys[0].getDeclaredConstructor();
			a = (Activity)construct.newInstance();
			
			System.out.print(a.getName());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		teste();
	}

}
