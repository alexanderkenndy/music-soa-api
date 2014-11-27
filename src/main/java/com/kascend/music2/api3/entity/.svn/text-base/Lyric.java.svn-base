package com.kascend.music2.api3.entity;


public class Lyric extends BaseData  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long lyricId;
	private long songId;
	private java.lang.String lyricUri;
	private java.lang.String lyricText;
	private java.sql.Timestamp createTime;
	private int dataFrom;
	
	private Song song;

	public long getLyricId() {
		return this.lyricId;
	}

	public void setLyricId(long lyricId) {
	     this.lyricId = lyricId;
	}
	public long getSongId() {
		return this.songId;
	}

	public void setSongId(long songId) {
	     this.songId = songId;
	}
	public java.lang.String getLyricUri() {
		return this.lyricUri;
	}

	public void setLyricUri(java.lang.String lyricUri) {
	     this.lyricUri = lyricUri;
	}
	public java.lang.String getLyricText() {
		return this.lyricText;
	}

	public void setLyricText(java.lang.String lyricText) {
	     this.lyricText = lyricText;
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
		sb.append("<lyricUri>\r\n");

		if(this.getLyricUri()!=null)
		{
			sb.append("    <lyrics>").append(this.getLyricUri()).append("</lyrics>\r\n");
		}
		sb.append("</song>\r\n");				
		
		return sb.toString();
	}

	public int getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(int dataFrom) {
		this.dataFrom = dataFrom;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

}
