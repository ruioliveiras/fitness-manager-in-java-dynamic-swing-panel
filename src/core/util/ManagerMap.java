package core.util;

import java.util.Map;

import model.ObjectKey;

public class ManagerMap<V> extends Manager<V>{
	private Map<Object, V> mMap;

	public ManagerMap(Map<Object, V> map) {
		map.clear();
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
	public void add(V obj) {
		V clon = cloneV(obj);
		mMap.put(((ObjectKey) clon).getKey(),clon);	
	}


}
