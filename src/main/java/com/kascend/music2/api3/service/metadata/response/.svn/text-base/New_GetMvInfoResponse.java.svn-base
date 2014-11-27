package com.kascend.music2.api3.service.metadata.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.kascend.music2.api3.service.metadata.response.entry.New_MvEntry;
import com.kascend.music2.api3.service.response.MusicBaseResponse;


@XmlRootElement(name = "response")
public class New_GetMvInfoResponse extends MusicBaseResponse {
	private List<New_MvEntry> mvinfo;
	@XmlElement
	public List<New_MvEntry> getMvinfo() {
		return mvinfo;
	}

	public void setMvinfo(List<New_MvEntry> mvinfo) {
		this.mvinfo = mvinfo;
	}
	
}
