package com.kascend.music2.api3.entity;

public class SongFile extends BaseData {
    private long songFileId;
    
    private long songId;

    private int rate;

    private int status;

    private java.sql.Timestamp createTime;

    private int fileSize;
    
    private String uri;
    

	public long getSongId() {
		return songId;
	}

	public void setSongId(long songId) {
		this.songId = songId;
	}

	public long getSongFileId() {
		return songFileId;
	}

	public void setSongFileId(long songFileId) {
		this.songFileId = songFileId;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public java.sql.Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
    
    
}
