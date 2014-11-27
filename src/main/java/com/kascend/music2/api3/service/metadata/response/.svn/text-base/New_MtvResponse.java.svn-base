package com.kascend.music2.api3.service.metadata.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.kascend.music2.api3.service.metadata.response.entry.New_MtvEntryList;
import com.kascend.music2.api3.service.response.MusicBaseResponse;



@XmlRootElement(name = "response")
public class New_MtvResponse extends MusicBaseResponse {
	private New_MtvEntryList mtvlist;
	
	@XmlElement
	public New_MtvEntryList getMtvlist() {
		return mtvlist;
	}

	public void setMtvlist(New_MtvEntryList mtvlist) {
		this.mtvlist = mtvlist;
	}
	
}
