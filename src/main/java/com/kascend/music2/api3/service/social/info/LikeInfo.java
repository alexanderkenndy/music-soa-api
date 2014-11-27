package com.kascend.music2.api3.service.social.info;


public class LikeInfo extends SnsItemsInfo {
	
	private int like;
	
	private int share;
	
	public int getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

}
