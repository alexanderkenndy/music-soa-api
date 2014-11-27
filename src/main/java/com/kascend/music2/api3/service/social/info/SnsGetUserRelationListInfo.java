package com.kascend.music2.api3.service.social.info;

import com.kascend.music2.api3.service.metadata.info.PageInfo;

public class SnsGetUserRelationListInfo extends PageInfo{
	
	private int allcountflag;
	private int weibosource;
	private String accesstoken;
	private String accesssecret;
	private String snsuserid;
	private int type;
	
	public int getAllcountflag() {
		return allcountflag;
	}
	public void setAllcountflag(int allcountflag) {
		this.allcountflag = allcountflag;
	}
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
