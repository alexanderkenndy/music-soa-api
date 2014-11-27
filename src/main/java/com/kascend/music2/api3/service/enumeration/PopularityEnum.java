package com.kascend.music2.api3.service.enumeration;

public enum PopularityEnum {
	/**
	 * 1.多一个粉丝 ：10
	 * 2.被查看一次:1
	 */
	addFans(1,10),beWatched(2,1);
	
	private final int value;
    public int getValue() {
        return value;
    }
    private final int type;
    public int getType(){
    	return type;
    }
    PopularityEnum(int type,int value) {
        this.value = value;
        this.type=type;
    }
}
