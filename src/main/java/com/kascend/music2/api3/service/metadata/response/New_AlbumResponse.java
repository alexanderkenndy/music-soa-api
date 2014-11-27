package com.kascend.music2.api3.service.metadata.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.kascend.music2.api3.service.metadata.response.entry.New_AlbumListEntry;
import com.kascend.music2.api3.service.response.MusicBaseResponse;

@XmlRootElement(name = "response")
public class New_AlbumResponse extends MusicBaseResponse {
	private New_AlbumListEntry albumslist;

	@XmlElement
	public New_AlbumListEntry getAlbumslist() {
		return albumslist;
	}

	public void setAlbumslist(New_AlbumListEntry albumslist) {
		this.albumslist = albumslist;
	}

}
