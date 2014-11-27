package com.kascend.music2.api3.service.metadata.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.kascend.music2.api3.service.metadata.response.entry.New_AlbumEntry;
import com.kascend.music2.api3.service.response.MusicBaseResponse;

@XmlRootElement(name = "response")
public class New_AlbumInfoResponse extends MusicBaseResponse {
	private New_AlbumEntry album;

	@XmlElement
	public New_AlbumEntry getAlbum() {
		return album;
	}

	public void setAlbum(New_AlbumEntry album) {
		this.album = album;
	}

}
