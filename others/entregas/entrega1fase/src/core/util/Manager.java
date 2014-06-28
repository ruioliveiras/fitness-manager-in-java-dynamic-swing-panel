package core.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import model.Clonable;
import model.ObjectKey;


public class Manager<V>{
	private Map<Object, V> mMap;
	private Set<V> mSet;
	
	public Manager(Map<Object, V> map){
		mMap = map;
	}
	public Manager(Set<V> set){
		mSet = set;
	}
	/*TODO: FAZER COPIA?*/
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
	
	@SuppressWarnings("unchecked")
	public Collection<V> collection(){
		ArrayList<V> res = new ArrayList<V>();
		Iterable<V> ite = (mMap != null) ? mMap.values() : mSet ;
		for (V value : ite) {
			try {
				res.add((V) ((Clonable) value).clone());
			} catch (Exception e) {
				throw new RuntimeException("Clone not supported");
			}
		}
		return res;
	}
	
	public V get(ObjectKey a){
		return mMap.get(a.getKey());
	}
	
	public void remove(V obj){
		if (obj instanceof ObjectKey && mMap != null){
			mMap.remove((ObjectKey) obj);
		}else if (mSet != null){
			mSet.remove(obj);
		}
	}
	
	public boolean contains(V obj){
		if (obj instanceof ObjectKey && mMap != null){
			return mMap.get((ObjectKey) obj) != null;
		}else if (mSet != null){
			return mSet.contains(obj);
		}
		return false;
	}
	
}
