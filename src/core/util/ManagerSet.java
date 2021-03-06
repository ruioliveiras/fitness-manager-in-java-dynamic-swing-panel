package core.util;

import java.util.Collection;
import java.util.Set;

import model.objectInterface.ObjectKey;

public class ManagerSet<V> extends Manager<V>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Set<V> mSet;

	public ManagerSet( Set<V> set) {
		super();
		mSet = set;
	}
	public ManagerSet(Manager.OnManagerAdd<V> listener, Set<V> set) {
		super(listener);
		mSet = set;
	}

	@Override
	protected Iterable<V> getValues() {
		return mSet;
	}

	@Override
	protected V getNoClone(ObjectKey a) {
		for (V v : mSet) {
			if (v instanceof ObjectKey){
				if (((ObjectKey) v).getKey().equals(a.getKey())){
					return v;
				}
			}
		}
		
		return null;
	}

	@Override
	public boolean contains(V obj) {
		return mSet.contains(obj);
	}

	@Override
	public void remove(V obj) {
		mSet.remove(obj);
	}

	@Override
	public boolean add(V obj) {
		if (super.add(obj)){
			V a = cloneV(obj);
			mSet.add(a);
			return true;
		}else{
			return false;
		}
		
	}
	public int size() {
		return mSet.size();
	}
	public void addAll(Collection<V> collection) {
		for (V obj : collection) {
			add(obj);
		}
		
	}


}
