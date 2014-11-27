package com.kascend.music2.api3.service.social.response.entry;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
@XmlType(propOrder = { "allcount", "playlist"
})
public class UserPlaylistListEntry extends MusicBaseEntry {
	
	private Integer allcount;
	
	private List<UserPlaylistEntry> playlist;
	
	@XmlElement
	public List<UserPlaylistEntry> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(List<UserPlaylistEntry> playlist) {
		this.playlist = playlist;
	}
	@XmlElement
	public Integer getAllcount() {
		return allcount;
	}

	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}
	
	
}
