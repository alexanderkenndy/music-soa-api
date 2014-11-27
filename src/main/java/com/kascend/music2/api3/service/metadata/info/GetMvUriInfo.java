package com.kascend.music2.api3.service.metadata.info;

public class GetMvUriInfo extends PageInfo {
	private long songid;
	private int mvid;
	private int mtvid;
	public long getSongid() {
		return songid;
	}
	public void setSongid(long songid) {
		this.songid = songid;
	}
	public int getMvid() {
		return mvid;
	}
	public void setMvid(int mvid) {
		this.mvid = mvid;
	}
	public int getMtvid() {
		return mtvid;
	}
	public void setMtvid(int mtvid) {
		this.mtvid = mtvid;
	}
}
