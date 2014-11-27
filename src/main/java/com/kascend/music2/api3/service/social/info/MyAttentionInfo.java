package com.kascend.music2.api3.service.social.info;

import java.util.List;

import com.kascend.music2.api3.service.metadata.info.PageInfo;

public class MyAttentionInfo extends PageInfo {
	
	private int pagecount=20;

	private List<Long> albumIdList;
	
	private List<Long> uidList;
	
	private long lastplaytime;
	
	private long passDate;
	
	private int bioflag;
	
	private int albumartflag;
	
	
	
	
	public int getAlbumartflag() {
		return albumartflag;
	}


	public void setAlbumartflag(int albumartflag) {
		this.albumartflag = albumartflag;
	}


	public int getBioflag() {
		return bioflag;
	}


	public void setBioflag(int bioflag) {
		this.bioflag = bioflag;
	}


	public List<Long> getUidList() {
		return uidList;
	}


	public long getPassDate() {
		return passDate;
	}


	public void setPassDate(long passDate) {
		this.passDate = passDate;
	}


	public void setUidList(List<Long> uidList) {
		this.uidList = uidList;
	}


	public long getLastplaytime() {
		return lastplaytime;
	}



	public void setLastplaytime(long lastplaytime) {
		this.lastplaytime = lastplaytime;
	}

	public List<Long> getAlbumIdList() {
		return albumIdList;
	}

	public void setAlbumIdList(List<Long> albumIdList) {
		this.albumIdList = albumIdList;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	
}
