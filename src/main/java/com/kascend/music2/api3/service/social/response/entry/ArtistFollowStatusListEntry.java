package com.kascend.music2.api3.service.social.response.entry;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;

public class ArtistFollowStatusListEntry extends MusicBaseEntry {
	
	private List<ArtistFollowStatusEntry> artist;
	
	@XmlElement
	public List<ArtistFollowStatusEntry> getArtist() {
		return artist;
	}

	public void setArtist(List<ArtistFollowStatusEntry> artist) {
		this.artist = artist;
	}

	

	
	
}
