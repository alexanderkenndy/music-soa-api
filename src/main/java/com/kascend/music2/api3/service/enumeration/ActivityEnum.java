package com.kascend.music2.api3.service.enumeration;

public enum ActivityEnum {
	/**
	 *  1.听一首歌	1
	    2.曲库增加一首歌	5
		3.收藏一首歌曲	5
		4.关注一个好友	10
		用户信息完整度：	
		5.有头像	10
		6.填性别	10
		7.填昵称	10
		8.填签名	10
	 */
	listenedSong(1,1),addSong(2,5),likeSong(3,5),
	followUser(4,10),haveHeadIcon(5,10),haveSex(6,10),
	haveNickname(7,10),haveSignature(8,10);
	
	private final int value;
	
    public int getValue() {
        return value;
    }

    private final int type;
    
    public int getType(){
    	return type;
    }
    
    ActivityEnum(int type,int value) {
        this.value = value;
        this.type=type;
    }
}
