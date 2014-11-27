package com.kascend.music2.api3.service.metadata.info;

import java.util.List;

public class GetSongInfo extends GetSearchInfo {
	
	private int artistartflag;
	private int artistartsize;
	private int albumartflag;
	private int albumartsize;
	private int lyricflag;
	private int artistartfilter;
	private int albumartfilter;
	private int lyricfilter;
	
	private List<Long> songIdList;
	
	
	public List<Long> getSongIdList() {
		return songIdList;
	}
	public void setSongIdList(List<Long> songIdList) {
		this.songIdList = songIdList;
	}

	public int getArtistartflag() {
		return artistartflag;
	}
	public void setArtistartflag(int artistartflag) {
		this.artistartflag = artistartflag;
	}
	public int getArtistartsize() {
		return artistartsize;
	}
	public void setArtistartsize(int artistartsize) {
		this.artistartsize = artistartsize;
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
	public int getLyricflag() {
		return lyricflag;
	}
	public void setLyricflag(int lyricflag) {
		this.lyricflag = lyricflag;
	}
	public int getArtistartfilter() {
		return artistartfilter;
	}
	public void setArtistartfilter(int artistartfilter) {
		this.artistartfilter = artistartfilter;
	}
	public int getAlbumartfilter() {
		return albumartfilter;
	}
	public void setAlbumartfilter(int albumartfilter) {
		this.albumartfilter = albumartfilter;
	}
	public int getLyricfilter() {
		return lyricfilter;
	}
	public void setLyricfilter(int lyricfilter) {
		this.lyricfilter = lyricfilter;
	}
	
}
