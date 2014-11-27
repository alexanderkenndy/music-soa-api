package com.kascend.music2.api3.entity;

import java.util.Date;

public class ClientVersion extends BaseData{
	
	private int verid;
	private int appid;
	private String version;
	private Date createTime;
	public int getVerid() {
		return verid;
	}
	public void setVerid(int verid) {
		this.verid = verid;
	}
	public int getAppid() {
		return appid;
	}
	public void setAppid(int appid) {
		this.appid = appid;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
