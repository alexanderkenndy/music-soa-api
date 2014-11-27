package com.kascend.music2.api3.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

public class OsCacheService implements CacheService{
	
	private GeneralCacheAdministrator osCache;
	private int refreshPeriod;
	private String API_TAG="Music_Api_";
	private final Log log=LogFactory.getLog(getClass());
	@Override
	public void put(String key, Object value) {
		try {
			osCache.putInCache(API_TAG + key, value);
		} catch (Exception ex) {
			osCache.cancelUpdate(API_TAG + key);
		}
		log.debug("Put OsCache Size: "+osCache.getCache().getSize());
	}
	

	@Override
	public Object get(String key, int refreshPeriod) {
		Object obj=null;
		try {
			log.debug("Get OsCache Size: "+osCache.getCache().getSize());
			obj=osCache.getFromCache(API_TAG+key, refreshPeriod);
		} catch (NeedsRefreshException nre) {
			 osCache.cancelUpdate(API_TAG+key);
		}
		return obj;
	}

	@Override
	public Object getNoRefresh(String key) {
		Object obj=null;
		try {
			log.debug("Get OsCache Size: "+osCache.getCache().getSize());
			obj=osCache.getFromCache(API_TAG+key);
		} catch (NeedsRefreshException nre) {
			 osCache.cancelUpdate(API_TAG+key);
		}
		return obj;
	}

	@Override
	public Object get(String key) {
		return get(key, refreshPeriod);
	}

	@Override
	public void flush(String key) {
		osCache.flushEntry(API_TAG+key);
	}
	@Override
	public void flushAll() {
		osCache.flushAll();
	}
	public GeneralCacheAdministrator getOsCache() {
		return osCache;
	}

	public void setOsCache(GeneralCacheAdministrator osCache) {
		this.osCache = osCache;
	}

	public int getRefreshPeriod() {
		return refreshPeriod;
	}

	public void setRefreshPeriod(int refreshPeriod) {
		this.refreshPeriod = refreshPeriod;
	}

	
}
