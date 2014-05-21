package core.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import model.ObjectClonable;
import model.ObjectKey;


public abstract class Manager<V>{

	
	private OnManagerAdd<V> mAddListener;
	
	public Manager() {
		mAddListener = null;
	}
	
	public Manager(OnManagerAdd<V> addListener){
		setAddListener(addListener);
	}
	
	public void setAddListener (OnManagerAdd<V> addListener){
		mAddListener = addListener;
	}
	protected abstract Iterable<V> getValues();
	protected abstract V getNoClone(ObjectKey a);
	public abstract boolean contains(V obj);
	public abstract void remove(V obj);
	public boolean add(V obj){
		boolean  res = true;
		if (mAddListener != null){
			res = mAddListener.beforeAdd(obj);
		}
		return res;
	}
	
	
	/*TODO: FAZER COPIA?*/
	
//	{
//		if (obj instanceof ObjectKey && mMap != null){
//			mMap.put(((ObjectKey) obj).getKey(),obj);
//		}else if (mSet != null){
//			mSet.add(obj);
//		}
//	}
	
	
	public Iterator<V> iterator(Comparator<V> order){
		Collection<V> res = (order == null) ?  new ArrayList<V>() :  new TreeSet<V>(order);
		Iterable<V> ite = getValues();
		for (V value : ite) {
			res.add(cloneV(value));
			
		}
		return res.iterator();
	}

	public Iterator<V> iterator(){
		return iterator(null);
	}

	public Collection<V> collection(){
		ArrayList<V> res = new ArrayList<V>();
		Iterator<V> ite = iterator();
		
		while(ite.hasNext()){
			res.add(ite.next());
		}
		return res;
	}
	public V get(ObjectKey a){
		V v = getNoClone(a);
		return  cloneV(v);
	}
	
	@SuppressWarnings("unchecked")
	protected V cloneV (V aux){
		try {
			return((V) ((ObjectClonable) aux).clone());
		} catch (Exception e) {
			throw new RuntimeException("Clone not supported");
		}
	}
	
	
	public interface OnManagerAdd<V>{
		public boolean beforeAdd(V obj);
	}
}
