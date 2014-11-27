package com.kascend.music2.api3.cache;

import java.io.Serializable;

public interface CacheService extends Serializable{
	
	public void put(String key,Object value);
	
	public Object get(String key);
	
	public Object get(String key, int refreshPeriod);
	
	public Object getNoRefresh(String key);
	
	public void flush(String key);
	
	public void flushAll();

}
