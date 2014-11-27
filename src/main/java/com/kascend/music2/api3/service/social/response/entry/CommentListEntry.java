package com.kascend.music2.api3.service.social.response.entry;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
@XmlType(propOrder = { "allcount", "comment"
})
public class CommentListEntry extends MusicBaseEntry {
	private List<CommentEntry> comment;
	
	private Integer allcount;
	
	
	@XmlElement
	public Integer getAllcount() {
		return allcount;
	}

	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}
	
	@XmlElement
	public List<CommentEntry> getComment() {
		return comment;
	}

	public void setComment(List<CommentEntry> comment) {
		this.comment = comment;
	}
	
}
