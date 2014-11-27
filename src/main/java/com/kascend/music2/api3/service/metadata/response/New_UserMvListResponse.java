package com.kascend.music2.api3.service.metadata.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.kascend.music2.api3.service.metadata.response.entry.New_UserMvListEntry;
import com.kascend.music2.api3.service.response.MusicBaseResponse;


@XmlRootElement(name = "response")
public class New_UserMvListResponse extends MusicBaseResponse {
	private New_UserMvListEntry mvlist;
	@XmlElement
	public New_UserMvListEntry getMvlist() {
		return mvlist;
	}

	public void setMvlist(New_UserMvListEntry mvlist) {
		this.mvlist = mvlist;
	}
	

	
}
