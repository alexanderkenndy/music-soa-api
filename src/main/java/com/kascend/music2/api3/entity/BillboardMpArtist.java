package com.kascend.music2.api3.entity;


public class BillboardMpArtist implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private long subBillboardId;
	private long artistId;
	private int position;
	private int lastPosition;
	private int value1;
	private long value2;
	private long value3;
	private java.lang.String value4;
	private java.sql.Timestamp createTime;


	public long getId() {
		return this.id;
	}

	public void setId(long id) {
	     this.id = id;
	}
	public long getSubBillboardId() {
		return this.subBillboardId;
	}

	public void setSubBillboardId(long subBillboardId) {
	     this.subBillboardId = subBillboardId;
	}
	public long getArtistId() {
		return this.artistId;
	}

	public void setArtistId(long artistId) {
	     this.artistId = artistId;
	}
	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
	     this.position = position;
	}
	public int getLastPosition() {
		return this.lastPosition;
	}

	public void setLastPosition(int lastPosition) {
	     this.lastPosition = lastPosition;
	}
	public int getValue1() {
		return this.value1;
	}

	public void setValue1(int value1) {
	     this.value1 = value1;
	}
	public long getValue2() {
		return this.value2;
	}

	public void setValue2(long value2) {
	     this.value2 = value2;
	}
	public long getValue3() {
		return this.value3;
	}

	public void setValue3(long value3) {
	     this.value3 = value3;
	}
	public java.lang.String getValue4() {
		return this.value4;
	}

	public void setValue4(java.lang.String value4) {
	     this.value4 = value4;
	}
	public java.sql.Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.sql.Timestamp createTime) {
	     this.createTime = createTime;
	}


}
