package com.kascend.music2.api3.service.social.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.kascend.music2.api3.service.response.MusicBaseResponse;
import com.kascend.music2.api3.service.social.response.entry.UserPlaylistListEntry;

@XmlRootElement(name = "response")
public class UserPlaylistResponse extends MusicBaseResponse {
	private UserPlaylistListEntry playlists;
	
	@XmlElement
	public UserPlaylistListEntry getPlaylists() {
		return playlists;
	}

	public void setPlaylists(UserPlaylistListEntry playlists) {
		this.playlists = playlists;
	}
	
	
}