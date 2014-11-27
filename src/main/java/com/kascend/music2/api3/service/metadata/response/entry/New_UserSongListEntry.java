package com.kascend.music2.api3.service.metadata.response.entry;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;

@XmlType(propOrder = {"allcount","playlistid", "playlisttitle","addedsongs", "playlistart", "bio","user" ,"song" })
public class New_UserSongListEntry extends MusicBaseEntry{

	protected Integer allcount;
	
	protected List<New_UserSongEntry> song;
	
	protected New_UserEntry user;
	
	private Integer addedsongs;
	
	private Long playlistid;
	
	private String playlisttitle;
	
	private String playlistart;
	
	private String bio;
	
	@XmlElement
	public Integer getAddedsongs() {
		return addedsongs;
	}

	public void setAddedsongs(Integer addedsongs) {
		this.addedsongs = addedsongs;
	}

	@XmlElement
	public Integer getAllcount() {
		return allcount;
	}

	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}
	
	@XmlElement
	public List<New_UserSongEntry> getSong() {
		return song;
	}

	public void setSong(List<New_UserSongEntry> song) {
		this.song = song;
	}
	
	@XmlElement
	public Long getPlaylistid() {
		return playlistid;
	}

	public void setPlaylistid(Long playlistid) {
		this.playlistid = playlistid;
	}
	@XmlElement
	public String getPlaylisttitle() {
		return playlisttitle;
	}

	public void setPlaylisttitle(String playlisttitle) {
		this.playlisttitle = playlisttitle;
	}
	@XmlElement
	public String getPlaylistart() {
		return playlistart;
	}

	public void setPlaylistart(String playlistart) {
		this.playlistart = playlistart;
	}
	@XmlElement
	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	@XmlElement
	public New_UserEntry getUser() {
		return user;
	}

	public void setUser(New_UserEntry user) {
		this.user = user;
	}

	
}
