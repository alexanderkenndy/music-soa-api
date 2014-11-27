package com.kascend.music2.api3.service.metadata.response;

import javax.xml.bind.annotation.XmlRootElement;
import com.kascend.music2.api3.service.metadata.response.entry.New_LyricEntry;
import com.kascend.music2.api3.service.response.MusicBaseResponse;

@XmlRootElement(name = "response")
public class New_GetLyricResponse extends MusicBaseResponse {
	private New_LyricEntry song;

	public New_LyricEntry getSong() {
		return song;
	}

	public void setSong(New_LyricEntry song) {
		this.song = song;
	}
}
