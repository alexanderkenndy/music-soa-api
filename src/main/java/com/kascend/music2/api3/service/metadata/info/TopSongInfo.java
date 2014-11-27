package com.kascend.music2.api3.service.metadata.info;

public class TopSongInfo extends TopInfo {
	
	private int albumartflag;
	private int albumartsize;

	public int getAlbumartsize() {
		return albumartsize;
	}

	public void setAlbumartsize(int albumartsize) {
		this.albumartsize = albumartsize;
	}

	public int getAlbumartflag() {
		return albumartflag;
	}

	public void setAlbumartflag(int albumartflag) {
		this.albumartflag = albumartflag;
	}

}
