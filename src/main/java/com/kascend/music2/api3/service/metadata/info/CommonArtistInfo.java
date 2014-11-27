package com.kascend.music2.api3.service.metadata.info;

import java.util.List;

public class CommonArtistInfo extends PageInfo {
	private List<Long> artistIdList;
	private int artistartflag;
	
	public int getArtistartflag() {
		return artistartflag;
	}

	public void setArtistartflag(int artistartflag) {
		this.artistartflag = artistartflag;
	}

	public List<Long> getArtistIdList() {
		return artistIdList;
	}

	public void setArtistIdList(List<Long> artistIdList) {
		this.artistIdList = artistIdList;
	}
	
	
}
