package com.kascend.music2.api3.service.metadata.response.entry;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlType(propOrder = {"allcount", "playlistid", "playlisttitle", "playlistart", "bio" ,"user","mv" })
public class New_UserMvListEntry implements Serializable{

	protected Integer allcount;
	protected List<New_UserMvEntry> mv;
	protected New_UserEntry user;
	private Long playlistid;
	private String playlisttitle;
	private String playlistart;
	private String bio;
	@XmlElement
	public Integer getAllcount() {
		return allcount;
	}

	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}
	
	@XmlElement
	public List<New_UserMvEntry> getMv() {
		return mv;
	}

	public void setMv(List<New_UserMvEntry> mv) {
		this.mv = mv;
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
