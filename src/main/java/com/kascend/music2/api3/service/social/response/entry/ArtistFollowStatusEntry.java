package com.kascend.music2.api3.service.social.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
@XmlType(propOrder = { "artistid", "followed"})
public class ArtistFollowStatusEntry extends MusicBaseEntry {
	private Long artistid;
	private Integer followed;
	
	@XmlElement
	public Long getArtistid() {
		return artistid;
	}
	public void setArtistid(Long artistid) {
		this.artistid = artistid;
	}
	@XmlElement
	public Integer getFollowed() {
		return followed;
	}
	public void setFollowed(Integer followed) {
		this.followed = followed;
	}
	
	
}
