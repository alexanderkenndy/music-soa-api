package com.kascend.music2.api3.entity;

public class User extends BaseData{
	private long uid;

    private java.sql.Timestamp lastLoginTime;

    private java.sql.Timestamp createTime;

    private String registerIp;

    private String registerIpRegion;

    private String name;

    private String nickname;

    private String title;

    private int followCount;

    private int followArtistCount;

    private int fansCount;
    
    private int shareCount;
    
    private int likeCount;

    private String headIcon;

    private int popularity;

    private int activity;

    private int sex;

    private int score;

    private int grade;

    private int userFrom=1;

    private String signature;

    private int userType=1;
    
    private long artistId;
    
    private long usUid;
    
    private java.sql.Timestamp updateTime;
    
    private long lastTime;
    
    private long lastSongId;
    
    private long lastSongPlayTime;
    
    private int songCount;
    private int albumCount;
    private int artistCount;
    private int recentAlbumCount;
    
    private int status;
    private int age;
    private String email;
    private String phone;
    
    
	public int getSongCount() {
		return songCount;
	}

	public void setSongCount(int songCount) {
		this.songCount = songCount;
	}

	public int getAlbumCount() {
		return albumCount;
	}

	public void setAlbumCount(int albumCount) {
		this.albumCount = albumCount;
	}

	public int getArtistCount() {
		return artistCount;
	}

	public void setArtistCount(int artistCount) {
		this.artistCount = artistCount;
	}

	public int getRecentAlbumCount() {
		return recentAlbumCount;
	}

	public void setRecentAlbumCount(int recentAlbumCount) {
		this.recentAlbumCount = recentAlbumCount;
	}

	public long getLastSongId() {
		return lastSongId;
	}

	public void setLastSongId(long lastSongId) {
		this.lastSongId = lastSongId;
	}

	public long getLastSongPlayTime() {
		return lastSongPlayTime;
	}

	public void setLastSongPlayTime(long lastSongPlayTime) {
		this.lastSongPlayTime = lastSongPlayTime;
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public java.sql.Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(java.sql.Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public java.sql.Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getRegisterIpRegion() {
		return registerIpRegion;
	}

	public void setRegisterIpRegion(String registerIpRegion) {
		this.registerIpRegion = registerIpRegion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getFollowCount() {
		return followCount;
	}

	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}

	public int getFollowArtistCount() {
		return followArtistCount;
	}

	public void setFollowArtistCount(int followArtistCount) {
		this.followArtistCount = followArtistCount;
	}

	public int getFansCount() {
		return fansCount;
	}

	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}

	public String getHeadIcon() {
		return headIcon;
	}

	public void setHeadIcon(String headIcon) {
		this.headIcon = headIcon;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public int getActivity() {
		return activity;
	}

	public void setActivity(int activity) {
		this.activity = activity;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(int userFrom) {
		this.userFrom = userFrom;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public long getArtistId() {
		return artistId;
	}

	public void setArtistId(long artistId) {
		this.artistId = artistId;
	}

	public java.sql.Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	public long getUsUid() {
		return usUid;
	}

	public void setUsUid(long usUid) {
		this.usUid = usUid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    
}
