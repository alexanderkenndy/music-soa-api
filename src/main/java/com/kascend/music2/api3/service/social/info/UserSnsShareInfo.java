package com.kascend.music2.api3.service.social.info;

import com.kascend.music2.api3.service.metadata.info.PageInfo;

public class UserSnsShareInfo extends PageInfo {
	private long id;
	private int itemtype;
	private String title;
	private int weibosource;
	private String accesstoken;
	private String accesssecret;
	
	private int type;
	
	private long snsId;
	
	private long snsShareId;
	
	
	public long getSnsShareId() {
		return snsShareId;
	}
	public void setSnsShareId(long snsShareId) {
		this.snsShareId = snsShareId;
	}
	public long getSnsId() {
		return snsId;
	}
	public void setSnsId(long snsId) {
		this.snsId = snsId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getItemtype() {
		return itemtype;
	}
	public void setItemtype(int itemtype) {
		this.itemtype = itemtype;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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

	
}
