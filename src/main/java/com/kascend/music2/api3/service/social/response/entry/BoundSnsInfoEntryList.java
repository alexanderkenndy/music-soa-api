package com.kascend.music2.api3.service.social.response.entry;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;

public class BoundSnsInfoEntryList extends MusicBaseEntry {
	private List<BoundSnsInfoEntry> sns;
	@XmlElement
	public List<BoundSnsInfoEntry> getSns() {
		return sns;
	}

	public void setSns(List<BoundSnsInfoEntry> sns) {
		this.sns = sns;
	}

	
	
}
