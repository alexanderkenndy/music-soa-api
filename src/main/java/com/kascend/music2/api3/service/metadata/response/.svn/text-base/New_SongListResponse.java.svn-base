package com.kascend.music2.api3.service.metadata.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.kascend.music2.api3.service.metadata.response.entry.New_SongListEntry;
import com.kascend.music2.api3.service.response.MusicBaseResponse;

import javax.xml.bind.annotation.XmlElement;
@XmlRootElement(name="response")
public class New_SongListResponse extends MusicBaseResponse {
	private New_SongListEntry songslist;

	@XmlElement
	public New_SongListEntry getSongslist() {
		return songslist;
	}

	public void setSongslist(New_SongListEntry songslist) {
		this.songslist = songslist;
	}

}
