package com.kascend.music2.api3.service.metadata.info;

import java.util.List;

public class GetRandomSongInfo extends PageInfo {
	
	private int mvflag;
	private int albumartflag;
	private int albumartsize;
	private List<Long> songIdList;
	
	public List<Long> getSongIdList() {
		return songIdList;
	}
	public void setSongIdList(List<Long> songIdList) {
		this.songIdList = songIdList;
	}
	public int getMvflag() {
		return mvflag;
	}
	public void setMvflag(int mvflag) {
		this.mvflag = mvflag;
	}
	public int getAlbumartflag() {
		return albumartflag;
	}
	public void setAlbumartflag(int albumartflag) {
		this.albumartflag = albumartflag;
	}
	public int getAlbumartsize() {
		return albumartsize;
	}
	public void setAlbumartsize(int albumartsize) {
		this.albumartsize = albumartsize;
	}
	
}
