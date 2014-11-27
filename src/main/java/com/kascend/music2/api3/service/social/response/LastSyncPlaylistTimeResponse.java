package com.kascend.music2.api3.service.social.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.MusicBaseResponse;
@XmlRootElement(name = "response")
@XmlType(propOrder = { "lastsynctime", "wrongrang"
})
public class LastSyncPlaylistTimeResponse extends MusicBaseResponse {
	private Integer lastsynctime;
	private Integer wrongrang;
	@XmlElement
	public Integer getLastsynctime() {
		return lastsynctime;
	}
	public void setLastsynctime(Integer lastsynctime) {
		this.lastsynctime = lastsynctime;
	}
	@XmlElement
	public Integer getWrongrang() {
		return wrongrang;
	}
	public void setWrongrang(Integer wrongrang) {
		this.wrongrang = wrongrang;
	}
	
	
}
