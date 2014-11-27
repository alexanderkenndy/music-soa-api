package com.kascend.music2.api3.exception;

public class MusicConst {

	// music常量定义
	public static final int MUSIC_REGION_CN = 21;
	public static final int MUSIC_REGION_EN_US = 22;
	public static final int MUSIC_REGION_JP_KR = 23;
	public static final int MUSIC_REGION_OTHER = 24;

	public static final int MUSIC_ARTIST_MALE = 0;
	public static final int MUSIC_ARTIST_FEMALE = 1;
	public static final int MUSIC_ARTIST_BAND = 2;

	// Recommendatiom常量定义
	public static final int RECOMMENDTION_REGION_CN = 0;
	public static final int RECOMMENDTION_REGION_EN_US = 1;
	public static final int RECOMMENDTION_REGION_JP_KR = 2;
	public static final int RECOMMENDTION_REGION_OTHER = 3;

	public static final int RECOMMENDTION_ARTIST_MALE = 0;
	public static final int RECOMMENDTION_ARTIST_FEMALE = 1;
	public static final int RECOMMENDTION_ARTIST_BAND = 2;

	public static final int RECOMMENDTION_BEFORE_SIXTIES = 0;
	public static final int RECOMMENDTION_SIXTIES = 1;
	public static final int RECOMMENDTION_SEVENTIES = 2;
	public static final int RECOMMENDTION_EIGHTIES = 3;
	public static final int RECOMMENDTION_NINETIES = 4;
	public static final int RECOMMENDTION_TWITYFIRST_CENTURY = 5;

	public static final int MUSIC_SEARCH_TYPE_ALL = 0;
	public static final int MUSIC_SEARCH_TYPE_SONG = 1;
	public static final int MUSIC_SEARCH_TYPE_ALBUM = 3;
	public static final int MUSIC_SEARCH_TYPE_ARTIST = 2;

	public static final int DEFAULT_KEYWORD_COUNT = 5;
	public static final int DEFAULT_KEYWORD_SEARCH_TYPE = 0;
	//定义download server的secret和appkey
	public static final String DOWNLOAD_SERVER_APPKEY="downloadServer.appkey";
	public static final String DOWNLOAD_SERVER_SECRET="downloadServer.secret";
	public static final String DOWNLOAD_SERVER_TOKEN="downloadServer.token";
	//定义user server的secret和appkey
	public static final String USER_SERVER_APPKEY="userServer.appkey";
	public static final String USER_SERVER_SECRET="userServer.secret";

}
