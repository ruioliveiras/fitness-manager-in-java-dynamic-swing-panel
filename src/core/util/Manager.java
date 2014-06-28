package core.util;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import model.objectInterface.ObjectClonable;
import model.objectInterface.ObjectKey;


public abstract class Manager<V> implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	public boolean edit(V obj){
		remove(obj);
		return add(obj);
	}
	public abstract int size();

	
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

	public List<V> collection(){
		ArrayList<V> res = new ArrayList<V>();
		Iterator<V> ite = iterator();
		
		while(ite.hasNext()){
			res.add(ite.next());
		}
		return res;
	}
	public V get(ObjectKey a) throws ObjectDontExistException{
		V v = getNoClone(a);
		if (v == null)
			throw new ObjectDontExistException("Object '" + a.toString() + "' ");
		return  cloneV(v);
	}
	
	@SuppressWarnings("unchecked")
	protected V cloneV (V aux){
		Object obj;
		
		try {
			if (aux instanceof String){
				obj = aux;
				return (V) obj;
			}else{
				obj = ((ObjectClonable) aux).clone();
				if (obj == null){
					throw new NullPointerException("Clone return null? "+aux.getClass());
				}
			return((V) obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Clone not supported "+ aux.getClass());
		}
	}
	
	
	public interface OnManagerAdd<V> extends Serializable{
		public boolean beforeAdd(V obj);
	}
	public static class ObjectDontExistException extends Exception implements Serializable{
		private static final long serialVersionUID = 1L;

		public ObjectDontExistException() {
			super();
		}

		public ObjectDontExistException(String message) {
			super(message);
		}	
	}
}
