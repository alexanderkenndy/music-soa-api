package com.kascend.music2.api3.service.metadata.response;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.kascend.music2.api3.service.metadata.response.entry.New_GetSongInfoEntry;
import com.kascend.music2.api3.service.response.MusicBaseResponse;

@XmlRootElement(name = "response")
public class New_GetSongInfoResponse extends MusicBaseResponse{

	private List<New_GetSongInfoEntry> songinfo;

	@XmlElement
	public List<New_GetSongInfoEntry> getSonginfo() {
		return songinfo;
	}

	public void setSonginfo(List<New_GetSongInfoEntry> songinfo) {
		this.songinfo = songinfo;
	}

}
