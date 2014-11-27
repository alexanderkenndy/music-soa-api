package com.kascend.music2.api3.entity;


public class SubBillboard extends BaseData {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long subBillboardId;
	private long billboardId;
	private java.lang.String subBillboardTitle;
	private int subBillboardPosition;
	private int subBillboardProps;
	private java.sql.Timestamp subBillboardFrom;
	private java.sql.Timestamp subBillboardTo;
	private java.sql.Timestamp createTime;
	
	private java.sql.Timestamp updateTime;
	private int size;
	private int hasListPic;
	
	private java.lang.String billboardTitle;
	
	private Billboard billboard; 


	public long getSubBillboardId() {
		return this.subBillboardId;
	}

	public void setSubBillboardId(long subBillboardId) {
	     this.subBillboardId = subBillboardId;
	}
	public long getBillboardId() {
		return this.billboardId;
	}

	public void setBillboardId(long billboardId) {
	     this.billboardId = billboardId;
	}
	public java.lang.String getSubBillboardTitle() {
		return this.subBillboardTitle;
	}

	public void setSubBillboardTitle(java.lang.String subBillboardTitle) {
	     this.subBillboardTitle = subBillboardTitle;
	}
	public int getSubBillboardPosition() {
		return this.subBillboardPosition;
	}

	public void setSubBillboardPosition(int subBillboardPosition) {
	     this.subBillboardPosition = subBillboardPosition;
	}
	public int getSubBillboardProps() {
		return this.subBillboardProps;
	}

	public void setSubBillboardProps(int subBillboardProps) {
	     this.subBillboardProps = subBillboardProps;
	}
	public java.sql.Timestamp getSubBillboardFrom() {
		return this.subBillboardFrom;
	}

	public void setSubBillboardFrom(java.sql.Timestamp subBillboardFrom) {
	     this.subBillboardFrom = subBillboardFrom;
	}
	public java.sql.Timestamp getSubBillboardTo() {
		return this.subBillboardTo;
	}

	public void setSubBillboardTo(java.sql.Timestamp subBillboardTo) {
	     this.subBillboardTo = subBillboardTo;
	}
	public java.sql.Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.sql.Timestamp createTime) {
	     this.createTime = createTime;
	}

	public java.lang.String getBillboardTitle() {
		return billboardTitle;
	}

	public void setBillboardTitle(java.lang.String billboardTitle) {
		this.billboardTitle = billboardTitle;
	}

	public Billboard getBillboard() {
		return billboard;
	}

	public void setBillboard(Billboard billboard) {
		this.billboard = billboard;
	}

	public java.sql.Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getHasListPic() {
		return hasListPic;
	}

	public void setHasListPic(int hasListPic) {
		this.hasListPic = hasListPic;
	}


}
