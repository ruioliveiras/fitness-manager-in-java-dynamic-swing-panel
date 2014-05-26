package core.util;

import java.util.Map;

import model.ObjectKey;

public class ManagerMap<V> extends Manager<V>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Object, V> mMap;

	public ManagerMap(Map<Object, V> map) {
		super();
		mMap = map;
	}

	public ManagerMap(Manager.OnManagerAdd<V> listener ,Map<Object, V> map) {
		super(listener);
		mMap = map;
	}

	
	@Override
	protected Iterable<V> getValues() {
		return mMap.values();
	}

	@Override
	protected V getNoClone(ObjectKey a) {
		return mMap.get(a.getKey());
	}

	@Override
	public boolean contains(V obj) {
		if (obj instanceof ObjectKey)
			return mMap.get(((ObjectKey) obj).getKey()) == null;
		else
			return false;
	}

	@Override
	public void remove(V obj) {
		mMap.remove(((ObjectKey) obj).getKey());
	}

	@Override
	public boolean add(V obj) {
		if (super.add(obj)){
			V clon = cloneV(obj);
			mMap.put(((ObjectKey) clon).getKey(),clon);
			return true;
		}
		return false;
			
	}
	public int getSize() {
		return mMap.size();
	}


}
