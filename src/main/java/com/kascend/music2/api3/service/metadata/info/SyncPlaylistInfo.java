package com.kascend.music2.api3.service.metadata.info;

import java.io.File;

public class SyncPlaylistInfo extends PageInfo {
	
	private File file[];
	
	private File cover[];
	
	private int share;
	
	public int getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
	}

	public File[] getCover() {
		return cover;
	}

	public void setCover(File[] cover) {
		this.cover = cover;
	}

	private int playlistprop;
	
	public int getPlaylistprop() {
		return playlistprop;
	}

	public void setPlaylistprop(int playlistprop) {
		this.playlistprop = playlistprop;
	}

	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}
	
	
	
}
