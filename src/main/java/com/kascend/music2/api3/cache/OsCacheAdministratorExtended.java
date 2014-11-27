package com.kascend.music2.api3.cache;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.opensymphony.oscache.general.GeneralCacheAdministrator;

public class OsCacheAdministratorExtended implements FactoryBean, InitializingBean{
	
	private Resource oscacheConfig;
	private GeneralCacheAdministrator osCacheAdministrator;

	public Resource getOscacheConfig() {
		return oscacheConfig;
	}

	public void setOscacheConfig(Resource oscacheConfig) {
		this.oscacheConfig = oscacheConfig;
	}

	

	@Override
	public void afterPropertiesSet() throws Exception {
		Properties prop=new Properties();
		try {
			prop.load(oscacheConfig.getInputStream());
			osCacheAdministrator=new GeneralCacheAdministrator(prop);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Object getObject() throws Exception {
		return osCacheAdministrator;
	}

	@Override
	public Class getObjectType() {
		// TODO Auto-generated method stub
		return GeneralCacheAdministrator.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
	


}
