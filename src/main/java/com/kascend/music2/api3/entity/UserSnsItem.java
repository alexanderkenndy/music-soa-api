package com.kascend.music2.api3.entity;

public class UserSnsItem extends BaseData {
	
	public int getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
	}
	private long itemId;
	private long uid;
	private String snsItemId;
	private int snsSite;
	private String content;
	private String picThumb;
	private String pic;
	private int createTime;
	private int updateTime;

	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getSnsItemId() {
		return snsItemId;
	}
	public void setSnsItemId(String snsItemId) {
		this.snsItemId = snsItemId;
	}
	public int getSnsSite() {
		return snsSite;
	}
	public void setSnsSite(int snsSite) {
		this.snsSite = snsSite;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicThumb() {
		return picThumb;
	}
	public void setPicThumb(String picThumb) {
		this.picThumb = picThumb;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getCreateTime() {
		return createTime;
	}
	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

}
