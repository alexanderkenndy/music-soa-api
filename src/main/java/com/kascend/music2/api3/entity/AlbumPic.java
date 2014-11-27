package com.kascend.music2.api3.entity;

import java.util.Date;

public class AlbumPic {

	private Long albumPicId;
	private Long albumId;
	private String picUri;
	private Integer sortIndex;
	private Date createTime;
	private String smallPic;
	private String middlePic;
	private Integer picType;
	public Integer getPicType() {
		return picType;
	}
	public void setPicType(Integer picType) {
		this.picType = picType;
	}
	public Long getAlbumPicId() {
		return albumPicId;
	}
	public void setAlbumPicId(Long albumPicId) {
		this.albumPicId = albumPicId;
	}
	public Long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}
	public String getPicUri() {
		return picUri;
	}
	public void setPicUri(String picUri) {
		this.picUri = picUri;
	}
	public Integer getSortIndex() {
		return sortIndex;
	}
	public void setSortIndex(Integer sortIndex) {
		this.sortIndex = sortIndex;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSmallPic() {
		return smallPic;
	}
	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}
	public String getMiddlePic() {
		return middlePic;
	}
	public void setMiddlePic(String middlePic) {
		this.middlePic = middlePic;
	}
	
	
}
