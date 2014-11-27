package com.kascend.music2.api3.service.social.info;

public class CommentInfo extends SnsItemsInfo {
	
	private String comment;
	
	private long commentId;
	
	private long replycommentid;
	
	private long replyUid;
	
	private int isReply;
	
	private long seconditemid;
	

	public long getReplyUid() {
		return replyUid;
	}

	public void setReplyUid(long replyUid) {
		this.replyUid = replyUid;
	}

	public int getIsReply() {
		return isReply;
	}

	public void setIsReply(int isReply) {
		this.isReply = isReply;
	}

	public long getReplycommentid() {
		return replycommentid;
	}

	public void setReplycommentid(long replycommentid) {
		this.replycommentid = replycommentid;
	}

	public long getSeconditemid() {
		return seconditemid;
	}

	public void setSeconditemid(long seconditemid) {
		this.seconditemid = seconditemid;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
