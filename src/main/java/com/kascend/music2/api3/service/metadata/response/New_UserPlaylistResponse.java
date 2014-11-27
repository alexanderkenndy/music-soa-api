package com.kascend.music2.api3.service.metadata.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.kascend.music2.api3.service.metadata.response.entry.New_UserPlaylistListEntry;
import com.kascend.music2.api3.service.response.MusicBaseResponse;

import javax.xml.bind.annotation.XmlElement;
@XmlRootElement(name="response")
public class New_UserPlaylistResponse extends MusicBaseResponse {
	private New_UserPlaylistListEntry playlists;

	@XmlElement
	public New_UserPlaylistListEntry getPlaylists() {
		return playlists;
	}

	public void setPlaylists(New_UserPlaylistListEntry playlists) {
		this.playlists = playlists;
	}

}
