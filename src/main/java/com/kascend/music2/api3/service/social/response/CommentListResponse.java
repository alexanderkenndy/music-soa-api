package com.kascend.music2.api3.service.social.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.kascend.music2.api3.service.response.MusicBaseResponse;
import com.kascend.music2.api3.service.social.response.entry.CommentListEntry;
@XmlRootElement(name = "response")
public class CommentListResponse extends MusicBaseResponse {
	
	private CommentListEntry commentslist;
	
	@XmlElement
	public CommentListEntry getCommentslist() {
		return commentslist;
	}

	public void setCommentslist(CommentListEntry commentslist) {
		this.commentslist = commentslist;
	}
	
}
