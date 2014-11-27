package com.kascend.music2.api3.entity;

public class UserFollowUser extends BaseData {
	private long followId;
	private long uid;
	private long followUid;
	private long createTime;
	public long getFollowId() {
		return followId;
	}
	public void setFollowId(long followId) {
		this.followId = followId;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public long getFollowUid() {
		return followUid;
	}
	public void setFollowUid(long followUid) {
		this.followUid = followUid;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	
}
