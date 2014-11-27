package com.kascend.music2.api3.service.metadata.info;

import java.util.List;

public class GetSimilarSongInfo extends PageInfo{
	
	private String artist;
	private long songid;
	private String song;
	private int albumartflag;
	private int albumartsize;
	private long artistid;
	private List<Integer> musicIdList;
	
	private int songFileFlag=0;
	
	private List<Long> songIdList;
	
	public int getSongFileFlag() {
		return songFileFlag;
	}
	public void setSongFileFlag(int songFileFlag) {
		this.songFileFlag = songFileFlag;
	}
	public List<Long> getSongIdList() {
		return songIdList;
	}
	public void setSongIdList(List<Long> songIdList) {
		this.songIdList = songIdList;
	}
	public List<Integer> getMusicIdList() {
		return musicIdList;
	}
	public void setMusicIdList(List<Integer> musicIdList) {
		this.musicIdList = musicIdList;
	}
	public long getArtistid() {
		return artistid;
	}
	public void setArtistid(long artistid) {
		this.artistid = artistid;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public long getSongid() {
		return songid;
	}
	public void setSongid(long songid) {
		this.songid = songid;
	}
	public String getSong() {
		return song;
	}
	public void setSong(String song) {
		this.song = song;
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
