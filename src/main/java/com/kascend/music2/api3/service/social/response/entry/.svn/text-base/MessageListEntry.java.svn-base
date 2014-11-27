package com.kascend.music2.api3.service.social.response.entry;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
@XmlType(propOrder = { "allcount", "message"
})
public class MessageListEntry extends MusicBaseEntry {
	private Integer allcount;
	private List<MessageEntry> message;
	@XmlElement
	public Integer getAllcount() {
		return allcount;
	}
	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}
	
	@XmlElement
	public List<MessageEntry> getMessage() {
		return message;
	}
	public void setMessage(List<MessageEntry> message) {
		this.message = message;
	}
	
	
}
