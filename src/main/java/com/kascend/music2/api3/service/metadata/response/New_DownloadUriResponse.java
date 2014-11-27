package com.kascend.music2.api3.service.metadata.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.kascend.music2.api3.service.response.MusicBaseResponse;

@XmlRootElement(name = "response")
public class New_DownloadUriResponse extends MusicBaseResponse {

	private Long songid;

	@XmlElement
	public Long getSongid() {
		return songid;
	}

	public void setSongid(Long songid) {
		this.songid = songid;
	}

	@XmlElement
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	private String uri;

	private String filename;

	@XmlElement
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
