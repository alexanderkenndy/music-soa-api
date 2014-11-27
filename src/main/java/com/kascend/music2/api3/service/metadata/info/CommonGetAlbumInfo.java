package com.kascend.music2.api3.service.metadata.info;

import java.util.List;

public class CommonGetAlbumInfo extends PageInfo{
	private List<Long> albumIdList;
	private int statusflag;
	private int songcountflag;
	public List<Long> getAlbumIdList() {
		return albumIdList;
	}
	public void setAlbumIdList(List<Long> albumIdList) {
		this.albumIdList = albumIdList;
	}
	public int getStatusflag() {
		return statusflag;
	}
	public void setStatusflag(int statusflag) {
		this.statusflag = statusflag;
	}
	public int getSongcountflag() {
		return songcountflag;
	}
	public void setSongcountflag(int songcountflag) {
		this.songcountflag = songcountflag;
	}
	
}
