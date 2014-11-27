package com.kascend.music2.api3.entity;

public class UserCommentContent extends BaseData {
    /** identifier field */
    private long commentId;

    /** nullable persistent field */
    private String content;

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
    
}
