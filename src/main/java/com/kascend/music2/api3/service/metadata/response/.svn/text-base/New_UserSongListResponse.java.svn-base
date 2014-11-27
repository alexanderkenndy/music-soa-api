package com.kascend.music2.api3.service.metadata.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.kascend.music2.api3.service.metadata.response.entry.New_UserSongListEntry;
import com.kascend.music2.api3.service.response.MusicBaseResponse;

import javax.xml.bind.annotation.XmlElement;
@XmlRootElement(name="response")
public class New_UserSongListResponse extends MusicBaseResponse {
	private New_UserSongListEntry songslist;

	@XmlElement
	public New_UserSongListEntry getSongslist() {
		return songslist;
	}

	public void setSongslist(New_UserSongListEntry songslist) {
		this.songslist = songslist;
	}

}
