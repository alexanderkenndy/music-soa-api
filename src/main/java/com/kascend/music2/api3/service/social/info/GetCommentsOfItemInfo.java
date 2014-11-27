package com.kascend.music2.api3.service.social.info;

import java.util.List;

import com.kascend.music2.api3.entity.UserComment;
import com.kascend.music2.api3.service.metadata.info.PageInfo;

public class GetCommentsOfItemInfo extends PageInfo {
	private long id;
	
	private int itemtype;
	
	private List<UserComment> userCommentList;
	

	public List<UserComment> getUserCommentList() {
		return userCommentList;
	}
	public void setUserCommentList(List<UserComment> userCommentList) {
		this.userCommentList = userCommentList;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getItemtype() {
		return itemtype;
	}
	public void setItemtype(int itemtype) {
		this.itemtype = itemtype;
	}
	
	

}
