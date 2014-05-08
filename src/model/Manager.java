package model;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import model.Clonable;
import model.ObjectKey;

/*
 * Colecçoes de User: email -> user
 * coleçoe sde activity Activity
 * coleçoes de Events Events;
 * 
 * 
 * 
 */

public class Manager<V>{
	private Map<Object, V> mMap;
	private Set<V> mSet;
	
	public Manager(Map<Object, V> map){
		mMap = map;
	}
	public Manager(Set<V> set){
		mSet = set;
	}
	
	public void add(V obj){
		if (obj instanceof ObjectKey && mMap != null){
			mMap.put(((ObjectKey) obj).getKey(),obj);
		}else if (mSet != null){
			mSet.add(obj);
		}
	}
	@SuppressWarnings("unchecked")
	public Iterator<V> iterator(Comparator<V> order){
		TreeSet<V> res = new TreeSet<V>(order);
		Iterable<V> ite = (mMap != null) ? mMap.values() : mSet ;
		for (V value : ite) {
			try {
				res.add((V) ((Clonable) value).clone());
			} catch (Exception e) {
				throw new RuntimeException("Clone not supported");
			}
		}
		return res.iterator();
	}
	@SuppressWarnings("unchecked")
	public Iterator<V> iterator(){
		ArrayList<V> res = new ArrayList<V>();
		Iterable<V> ite = (mMap != null) ? mMap.values() : mSet ;
		for (V value : ite) {
			try {
				res.add((V) ((Clonable) value).clone());
			} catch (Exception e) {
				throw new RuntimeException("Clone not supported");
			}
		}
		return res.iterator();
	}
	
	public V get(ObjectKey a){
		return mMap.get(a.getKey());
	}
}
