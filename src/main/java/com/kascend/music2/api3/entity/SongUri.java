package com.kascend.music2.api3.entity;


public class SongUri extends BaseData {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long suId;
	private long songId;
	private java.lang.String songUri;
	private int songFilesize;
	private float songDlRate;
	private int uriType;
	private java.sql.Timestamp createTime;
	private int status;
	private int dataFrom;
	
	private Song song;

	public long getSuId() {
		return this.suId;
	}

	public void setSuId(long suId) {
	     this.suId = suId;
	}
	public long getSongId() {
		return this.songId;
	}

	public void setSongId(long songId) {
	     this.songId = songId;
	}
	public java.lang.String getSongUri() {
		return this.songUri;
	}

	public void setSongUri(java.lang.String songUri) {
	     this.songUri = songUri;
	}
	
	public int getSongFilesize() {
		return this.songFilesize;
	}

	public void setSongFilesize(int songFilesize) {
	     this.songFilesize = songFilesize;
	}
	public float getSongDlRate() {
		return this.songDlRate;
	}

	public void setSongDlRate(float songDlRate) {
	     this.songDlRate = songDlRate;
	}
	public int getUriType() {
		return this.uriType;
	}

	public void setUriType(int uriType) {
	     this.uriType = uriType;
	}
	
	public java.sql.Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.sql.Timestamp createTime) {
	     this.createTime = createTime;
	}

	@Override
	public String toString()
	{

		StringBuilder sb = new StringBuilder();
		sb.append("<songUri>\r\n");

		
		if(song.getSongTitle()!=null)
		{
			sb.append("    <songTitle>").append(song.getSongTitle()).append("</songTitle>\r\n");
		}
		
		if(this.getSongUri()!=null)
		{
			sb.append("    <songUri>").append(this.getSongUri()).append("</songUri>\r\n");
		}
		sb.append("</songUri>\r\n");				
		
		return sb.toString();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public int getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(int dataFrom) {
		this.dataFrom = dataFrom;
	}
}
