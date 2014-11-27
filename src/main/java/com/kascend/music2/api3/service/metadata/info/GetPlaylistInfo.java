package com.kascend.music2.api3.service.metadata.info;

public class GetPlaylistInfo extends PageInfo {
	private long playlistid;

	private int dataFrom;
	
	private int sort;
	
	private long targetuid;
	
	private int pageFlag;
	
	public int getPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(int pageFlag) {
		this.pageFlag = pageFlag;
	}

	public long getTargetuid() {
		return targetuid;
	}

	public void setTargetuid(long targetuid) {
		this.targetuid = targetuid;
	}
	

	public int getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(int dataFrom) {
		this.dataFrom = dataFrom;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	public long getPlaylistid() {
		return playlistid;
	}

	public void setPlaylistid(long playlistid) {
		this.playlistid = playlistid;
	}
	
}
