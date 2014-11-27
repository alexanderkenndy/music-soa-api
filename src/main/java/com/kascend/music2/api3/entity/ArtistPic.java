package com.kascend.music2.api3.entity;

import java.util.Date;

public class ArtistPic {

	private long artistPicId;
	private long artistId;
	private String picUri;
	private int sortIndex;
	private Date createTime;
	private String smallPic;
	private String middlePic;
	
	private int picType;

	public long getArtistPicId() {
		return artistPicId;
	}

	public void setArtistPicId(long artistPicId) {
		this.artistPicId = artistPicId;
	}

	public long getArtistId() {
		return artistId;
	}

	public void setArtistId(long artistId) {
		this.artistId = artistId;
	}

	public String getPicUri() {
		return picUri;
	}

	public void setPicUri(String picUri) {
		this.picUri = picUri;
	}

	public int getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(int sortIndex) {
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

	public int getPicType() {
		return picType;
	}

	public void setPicType(int picType) {
		this.picType = picType;
	}
	
	
}
