package com.kascend.music2.api3.service.metadata.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.kascend.music2.api3.service.metadata.response.entry.New_MtvUriEntry;
import com.kascend.music2.api3.service.response.MusicBaseResponse;

@XmlRootElement(name = "response")
public class New_MtvUriListResponse extends MusicBaseResponse {
	private List<New_MtvUriEntry> uri;
	@XmlElement
	public List<New_MtvUriEntry> getUri() {
		return uri;
	}

	public void setUri(List<New_MtvUriEntry> uri) {
		this.uri = uri;
	}

}
