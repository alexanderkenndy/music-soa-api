package com.kascend.music2.api3.service.social.response.entry;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;

@XmlType(propOrder = { "allcount","user"})
public class UserListEntry extends MusicBaseEntry {
	
	private List<UserEntry> user;
	private Integer allcount;
	
	@XmlElement
	public Integer getAllcount() {
		return allcount;
	}
	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}
	
	@XmlElement
	public List<UserEntry> getUser() {
		return user;
	}
	public void setUser(List<UserEntry> user) {
		this.user = user;
	}
	
	
}
