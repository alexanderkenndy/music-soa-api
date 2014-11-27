package com.kascend.music2.api3.service.util;

import com.kascend.music2.api3.service.metadata.info.PageInfo;

public class PageUtil {
	
	public static PageInfo pageCompute(PageInfo info){
		return pageCompute(info,Constants.MAX_PAGE_COUNT,Constants.COMMON_PAGE_COUNT);
	}
	
	public static PageInfo maxPageCompute(PageInfo info){
		return pageCompute(info,Constants.BIGGEST_PAGE_COUNT,Constants.COMMON_PAGE_COUNT);
	}
	
	public static PageInfo pageCompute(PageInfo info,int maxValue,int defaultPageValue) {
		int page=info.getPage();
		int pageCount=info.getPagecount();
		if(pageCount==0){
			pageCount=defaultPageValue;
		}else if(pageCount>maxValue){
			pageCount=maxValue;
		}
		int start = (page - 1) * pageCount;
		info.setPage(page);
		info.setStart(start);
		return info;
	}
	
}
