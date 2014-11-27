package com.kascend.music2.api3.entity;

public class Song extends BaseData {


	private static final long serialVersionUID = 1L;
	
	private long songId;
	private java.lang.String googleSongId;
	private java.lang.String songTitle;
	private int songRegion;
	private java.lang.String songThumbUri;
	private java.sql.Timestamp createTime;
	private int picStatus;
	private int isUpload;
	private long defaultLyric;
	
	private int songProperties;
	
	private int dataFrom;

	private int status;
	
	private int songDuration;
	
	protected String artistNames;
	protected String artistIds;
		
	private long artistId;
	private String artistName;
	
	private String artistArt;
	
	protected java.lang.String albumTitle;
	protected long albumId;
	
	private int sortIndex;
	private int songHot;
	private int disc=1;
	
	private int position;
	
	private int hasArtistPic;
	private int hasMv;
	private int isnew;
		
	private int orderType;
	
	
	private int playTimes;
	
	private long lastPlayTime;
	
	
	public int getSongProperties() {
		return songProperties;
	}

	public void setSongProperties(int songProperties) {
		this.songProperties = songProperties;
	}

	public String getArtistArt() {
		return artistArt;
	}

	public void setArtistArt(String artistArt) {
		this.artistArt = artistArt;
	}

	public long getLastPlayTime() {
		return lastPlayTime;
	}

	public void setLastPlayTime(long lastPlayTime) {
		this.lastPlayTime = lastPlayTime;
	}

	public int getPlayTimes() {
		return playTimes;
	}

	public void setPlayTimes(int playTimes) {
		this.playTimes = playTimes;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public int getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(int dataFrom) {
		this.dataFrom = dataFrom;
	}

	public String getArtistNames() {
		return artistNames;
	}

	public void setArtistNames(String artistNames) {
		this.artistNames = artistNames;
	}

	public String getArtistIds() {
		return artistIds;
	}

	public void setArtistIds(String artistIds) {
		this.artistIds = artistIds;
	}

	public java.lang.String getAlbumTitle() {
		return albumTitle;
	}

	public void setAlbumTitle(java.lang.String albumTitle) {
		this.albumTitle = albumTitle;
	}

	public long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public long getSongId() {
		return this.songId;
	}

	public java.lang.String getGoogleSongId() {
		return googleSongId;
	}

	public void setGoogleSongId(java.lang.String googleSongId) {
		this.googleSongId = googleSongId;
	}

	public void setSongId(long songId) {
	     this.songId = songId;
	}
	public java.lang.String getSongTitle() {
		return this.songTitle;
	}

	public void setSongTitle(java.lang.String songTitle) {
	     this.songTitle = songTitle;
	}
	
	public int getSongRegion() {
		return this.songRegion;
	}

	public void setSongRegion(int songRegion) {
	     this.songRegion = songRegion;
	}
	
	public java.lang.String getSongThumbUri() {
		return this.songThumbUri;
	}

	public void setSongThumbUri(java.lang.String songThumbUri) {
	     this.songThumbUri = songThumbUri;
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
		sb.append("<song>\r\n");

//		if(this.getArtistName()!=null)
//		{
//			sb.append("    <artist>").append(this.getArtistName()).append("</artist>\r\n");
//		}
		if(this.getSongTitle()!=null)
		{
			sb.append("    <songTitle>").append(this.getSongTitle()).append("</songTitle>\r\n");
		}
//		if(this.getAlbumTitle()!=null)
//		{
//			sb.append("    <album>").append(this.getAlbumTitle()).append("</album>\r\n");
//		}
		
		sb.append("</song>\r\n");				
		
		return sb.toString();
	}


	public int getPicStatus() {
		return picStatus;
	}

	public void setPicStatus(int picStatus) {
		this.picStatus = picStatus;
	}

	public int getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(int isUpload) {
		this.isUpload = isUpload;
	}

	public long getDefaultLyric() {
		return defaultLyric;
	}

	public void setDefaultLyric(long defaultLyric) {
		this.defaultLyric = defaultLyric;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getArtistId() {
		return artistId;
	}

	public void setArtistId(long artistId) {
		this.artistId = artistId;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public int getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}

	public int getSongDuration() {
		return songDuration;
	}

	public void setSongDuration(int songDuration) {
		this.songDuration = songDuration;
	}

	public int getSongHot() {
		return songHot;
	}

	public void setSongHot(int songHot) {
		this.songHot = songHot;
	}

	public int getDisc() {
		return disc;
	}

	public void setDisc(int disc) {
		this.disc = disc;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getHasArtistPic() {
		return hasArtistPic;
	}

	public void setHasArtistPic(int hasArtistPic) {
		this.hasArtistPic = hasArtistPic;
	}

	public int getHasMv() {
		return hasMv;
	}

	public void setHasMv(int hasMv) {
		this.hasMv = hasMv;
	}

	public int getIsnew() {
		return isnew;
	}

	public void setIsnew(int isnew) {
		this.isnew = isnew;
	}
	
}
