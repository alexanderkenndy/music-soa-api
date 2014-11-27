package com.kascend.music2.api3.service.metadata.info;


public class TopInfo extends PageInfo {
	
	private long billboardid;
	private long subbillboardid;
	private long updatetime;
	private int type;
	
	private int subbillboardlistsize=10;
	
	public int getSubbillboardlistsize() {
		return subbillboardlistsize;
	}
	public void setSubbillboardlistsize(int subbillboardlistsize) {
		this.subbillboardlistsize = subbillboardlistsize;
	}
	public long getBillboardid() {
		return billboardid;
	}
	public void setBillboardid(long billboardid) {
		this.billboardid = billboardid;
	}
	public long getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getSubbillboardid() {
		return subbillboardid;
	}
	public void setSubbillboardid(long subbillboardid) {
		this.subbillboardid = subbillboardid;
	}
	

}
