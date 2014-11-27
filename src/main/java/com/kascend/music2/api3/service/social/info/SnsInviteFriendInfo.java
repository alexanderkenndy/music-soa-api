package com.kascend.music2.api3.service.social.info;

import com.kascend.music2.api3.service.metadata.info.PageInfo;

public class SnsInviteFriendInfo extends PageInfo {
	private int weibosource;
	private String accesstoken;
	private String accesssecret;
	private String snsuids;
	private String snsusernames;
	
	private int type;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public String getSnsuids() {
		return snsuids;
	}
	public void setSnsuids(String snsuids) {
		this.snsuids = snsuids;
	}
	public String getSnsusernames() {
		return snsusernames;
	}
	public void setSnsusernames(String snsusernames) {
		this.snsusernames = snsusernames;
	}

}
