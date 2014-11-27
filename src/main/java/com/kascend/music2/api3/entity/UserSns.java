package com.kascend.music2.api3.entity;

public class UserSns extends BaseData {
	private long snsId;
	private long uid;
	private int snsSite;
	private String snsAccount;
	private String snsUserName;
	private String snsPassword;
	private String snsUid;
	private String snsHeadIcon;
	private int status;
	private long createTime;
	private long updateTime;
	
	private String location;
	
	private String gender;
	
	private String description;
	
	private int itemcount;
	
	private int friendcount;
	
	private int followercount;
	
	private int favoritecount;

	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getItemcount() {
		return itemcount;
	}
	public void setItemcount(int itemcount) {
		this.itemcount = itemcount;
	}
	public int getFriendcount() {
		return friendcount;
	}
	public void setFriendcount(int friendcount) {
		this.friendcount = friendcount;
	}
	public int getFollowercount() {
		return followercount;
	}
	public void setFollowercount(int followercount) {
		this.followercount = followercount;
	}
	public int getFavoritecount() {
		return favoritecount;
	}
	public void setFavoritecount(int favoritecount) {
		this.favoritecount = favoritecount;
	}
	public long getSnsId() {
		return snsId;
	}
	public void setSnsId(long snsId) {
		this.snsId = snsId;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public int getSnsSite() {
		return snsSite;
	}
	public void setSnsSite(int snsSite) {
		this.snsSite = snsSite;
	}
	public String getSnsAccount() {
		return snsAccount;
	}
	public void setSnsAccount(String snsAccount) {
		this.snsAccount = snsAccount;
	}
	public String getSnsUserName() {
		return snsUserName;
	}
	public void setSnsUserName(String snsUserName) {
		this.snsUserName = snsUserName;
	}
	public String getSnsPassword() {
		return snsPassword;
	}
	public void setSnsPassword(String snsPassword) {
		this.snsPassword = snsPassword;
	}
	public String getSnsUid() {
		return snsUid;
	}
	public void setSnsUid(String snsUid) {
		this.snsUid = snsUid;
	}
	public String getSnsHeadIcon() {
		return snsHeadIcon;
	}
	public void setSnsHeadIcon(String snsHeadIcon) {
		this.snsHeadIcon = snsHeadIcon;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	
}
