package com.kascend.music2.api3.service.social.info;

import com.kascend.music2.api3.service.metadata.info.PageInfo;

public class SnsWeiBoInfo extends PageInfo {
	private int weibosource;
	private String accesstoken;
	private String accesssecret;
	private String snsuserid;
	public int getWeibosource() {
		return weibosource;
	}
	public void setWeibosource(int weibosource) {
		this.weibosource = weibosource;
	}
	public String getAccesstoken() {
		return accesstoken;
	}
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	public String getAccesssecret() {
		return accesssecret;
	}
	public void setAccesssecret(String accesssecret) {
		this.accesssecret = accesssecret;
	}
	public String getSnsuserid() {
		return snsuserid;
	}
	public void setSnsuserid(String snsuserid) {
		this.snsuserid = snsuserid;
	}
	
	

}
