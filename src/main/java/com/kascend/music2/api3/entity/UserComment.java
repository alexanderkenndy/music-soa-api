package com.kascend.music2.api3.entity;

public class UserComment extends BaseData {  /** identifier field */
	
    private long commentId;

    /** nullable persistent field */
    private long uid;

    /** persistent field */
    private long itemId;

    /** persistent field */
    private int itemType;

    /** persistent field */
    private int isReply;

    /** persistent field */
    private long replyCommentId;

    /** persistent field */
    private long replayUid;

    /** persistent field */
    private int status;

    /** persistent field */
    private int createTime;
    
    private long secondItemId;
    

    /** persistent field */
    private long updateTime;
    private User user;
    
    private String content;
    
    private String subTitle;
    

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public long getCommentId() {
		return commentId;
	}
	
	public long getSecondItemId() {
		return secondItemId;
	}



	public void setSecondItemId(long secondItemId) {
		this.secondItemId = secondItemId;
	}



	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public int getIsReply() {
		return isReply;
	}

	public void setIsReply(int isReply) {
		this.isReply = isReply;
	}

	public long getReplyCommentId() {
		return replyCommentId;
	}

	public void setReplyCommentId(long replyCommentId) {
		this.replyCommentId = replyCommentId;
	}

	public long getReplayUid() {
		return replayUid;
	}

	public void setReplayUid(long replayUid) {
		this.replayUid = replayUid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}


	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
}
