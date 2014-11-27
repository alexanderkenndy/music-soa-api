package com.kascend.music2.api3.service.social.info;

import com.kascend.music2.api3.service.metadata.info.PageInfo;

public class SnsAddItemInfo extends PageInfo {
	
	private int weibosource;
	private String accesstoken;
	private String accesssecret;
	private String content;
	private float longitude;
	private float latitude;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public float getLongitude() {
		return longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
}
