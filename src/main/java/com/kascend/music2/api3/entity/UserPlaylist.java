package com.kascend.music2.api3.entity;

public class UserPlaylist extends BaseData{
	  /** identifier field */
    private Long playlistId;

    /** persistent field */
    private long uid;

    /** nullable persistent field */
    private String title;

    /** nullable persistent field */
    private String thumb;

    /** nullable persistent field */
    private String description;

    /** persistent field */
    private int createTime;

    /** persistent field */
    private int updateTime;

    /** persistent field */
    private int playlistType;

    /** persistent field */
    private int dataFrom;

    /** persistent field */
    private long targetPlaylistId;

    /** persistent field */
    private int isOpen;

    /** persistent field */
    private int status;

    /** persistent field */
    private int itemCount;

	public Long getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(Long playlistId) {
		this.playlistId = playlistId;
	}

	public int getPlaylistType() {
		return playlistType;
	}

	public void setPlaylistType(int playlistType) {
		this.playlistType = playlistType;
	}

	public int getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(int dataFrom) {
		this.dataFrom = dataFrom;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public int getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
	}


	public long getTargetPlaylistId() {
		return targetPlaylistId;
	}

	public void setTargetPlaylistId(long targetPlaylistId) {
		this.targetPlaylistId = targetPlaylistId;
	}



	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
    
}
