package com.kascend.music2.api3.service.social.info;

import java.util.List;

import com.kascend.music2.api3.service.metadata.info.PageInfo;

public class GetUserAlbumsOfArtistInfo extends PageInfo {
	private long targetuid;
	private long artistid;
	private int listenedflag;
	
	private List<Long> listenedAlbumIdList;
	
	public List<Long> getListenedAlbumIdList() {
		return listenedAlbumIdList;
	}
	public void setListenedAlbumIdList(List<Long> listenedAlbumIdList) {
		this.listenedAlbumIdList = listenedAlbumIdList;
	}
	public long getTargetuid() {
		return targetuid;
	}
	public void setTargetuid(long targetuid) {
		this.targetuid = targetuid;
	}
	public long getArtistid() {
		return artistid;
	}
	public void setArtistid(long artistid) {
		this.artistid = artistid;
	}
	public int getListenedflag() {
		return listenedflag;
	}
	public void setListenedflag(int listenedflag) {
		this.listenedflag = listenedflag;
	}
	
	

}
