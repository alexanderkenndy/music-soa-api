package com.kascend.music2.api3.service.util;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.kascend.frameworkcommons.service.BaseService;
import com.kascend.music2.api3.dao.MetadataDao;
import com.kascend.music2.api3.service.social.impl.SocialServiceHelper;

public class ApiTimeTask extends BaseService implements Job{
	MetadataDao metadataDao;
	private static final Logger log = Logger
			.getLogger(SocialServiceHelper.class);
	public MetadataDao getMetadataDao() {
		return metadataDao;
	}
	public void setMetadataDao(MetadataDao metadataDao) {
		this.metadataDao = metadataDao;
	}
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
	public void doExecute(){
		try {
			metadataDao.clearRandomSong();
			metadataDao.insertRandomSong();
			metadataDao.clearRecommendUser();
			metadataDao.insertRecommendUser();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
}
