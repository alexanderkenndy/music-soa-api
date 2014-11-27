package com.kascend.music2.api3.service.metadata.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.kascend.music2.api3.service.metadata.response.entry.New_GetMtvInfoEntry;
import com.kascend.music2.api3.service.response.MusicBaseResponse;


@XmlRootElement(name = "response")
public class New_GetMtvInfoResponse extends MusicBaseResponse {
	private List<New_GetMtvInfoEntry> mtvinfo;
	@XmlElement
	public List<New_GetMtvInfoEntry> getMtvinfo() {
		return mtvinfo;
	}
	public void setMtvinfo(List<New_GetMtvInfoEntry> mtvinfo) {
		this.mtvinfo = mtvinfo;
	}
	
}
