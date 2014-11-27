package com.kascend.music2.api3.service.social.info;

import com.kascend.music2.api3.service.metadata.info.PageInfo;

public class LeaveMessageInfo extends PageInfo {
	private long touid;
	
	private String message;
	
	private long replymessageid;
	
	private long messageId;
	
	private int isReply;
	
	private long targetuid;
	
	private int pagecount=10;
	
	public long getMessageId() {
		return messageId;
	}
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	public int getIsReply() {
		return isReply;
	}
	public void setIsReply(int isReply) {
		this.isReply = isReply;
	}
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	public long getTargetuid() {
		return targetuid;
	}
	public void setTargetuid(long targetuid) {
		this.targetuid = targetuid;
	}

	public long getTouid() {
		return touid;
	}
	public void setTouid(long touid) {
		this.touid = touid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getReplymessageid() {
		return replymessageid;
	}
	public void setReplymessageid(long replymessageid) {
		this.replymessageid = replymessageid;
	}

}
