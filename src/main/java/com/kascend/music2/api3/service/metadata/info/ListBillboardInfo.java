package com.kascend.music2.api3.service.metadata.info;

import java.util.List;

public class ListBillboardInfo extends PageInfo {
	private String  billboardgroupcode;
	private int  billboardgroupid;
	private List<Long> songBillboardIdList;
	private List<Long> artistBillboardIdList;
	private List<Long> albumBillboardIdList;
	private List<Long> mvBillboardIdList;
	
	private int billboardtype;
	
	
	public int getBillboardtype() {
		return billboardtype;
	}
	public void setBillboardtype(int billboardtype) {
		this.billboardtype = billboardtype;
	}
	public List<Long> getSongBillboardIdList() {
		return songBillboardIdList;
	}
	public void setSongBillboardIdList(List<Long> songBillboardIdList) {
		this.songBillboardIdList = songBillboardIdList;
	}
	public List<Long> getArtistBillboardIdList() {
		return artistBillboardIdList;
	}
	public void setArtistBillboardIdList(List<Long> artistBillboardIdList) {
		this.artistBillboardIdList = artistBillboardIdList;
	}
	public List<Long> getAlbumBillboardIdList() {
		return albumBillboardIdList;
	}
	public void setAlbumBillboardIdList(List<Long> albumBillboardIdList) {
		this.albumBillboardIdList = albumBillboardIdList;
	}
	public List<Long> getMvBillboardIdList() {
		return mvBillboardIdList;
	}
	public void setMvBillboardIdList(List<Long> mvBillboardIdList) {
		this.mvBillboardIdList = mvBillboardIdList;
	}
	public String getBillboardgroupcode() {
		return billboardgroupcode;
	}
	public void setBillboardgroupcode(String billboardgroupcode) {
		this.billboardgroupcode = billboardgroupcode;
	}
	public int getBillboardgroupid() {
		return billboardgroupid;
	}
	public void setBillboardgroupid(int billboardgroupid) {
		this.billboardgroupid = billboardgroupid;
	}
	
	

}
