package com.kascend.music2.api3.service.metadata.impl;


import java.util.ArrayList;
import java.util.List;

import com.kascend.common.util.Validator;
import com.kascend.music2.api3.entity.SongMv;
import com.kascend.music2.api3.exception.MusicRcException;
import com.kascend.music2.api3.service.log.MetadataLogService;
import com.kascend.music2.api3.service.metadata.MetadataService;
import com.kascend.music2.api3.service.metadata.info.CacheFlushInfo;
import com.kascend.music2.api3.service.metadata.info.DiscoveryMvInfo;
import com.kascend.music2.api3.service.metadata.info.DiscoveryPlaylistInfo;
import com.kascend.music2.api3.service.metadata.info.DiscoverySongInfo;
import com.kascend.music2.api3.service.metadata.info.FeedBackSongInfo;
import com.kascend.music2.api3.service.metadata.info.GetAlbumInfo;
import com.kascend.music2.api3.service.metadata.info.GetAlbumsOfArtistInfo;
import com.kascend.music2.api3.service.metadata.info.GetAlbumsOfSongInfo;
import com.kascend.music2.api3.service.metadata.info.GetArtistInfo;
import com.kascend.music2.api3.service.metadata.info.GetDownloadUriInfo;
import com.kascend.music2.api3.service.metadata.info.GetLyricInfo;
import com.kascend.music2.api3.service.metadata.info.GetMvInfo;
import com.kascend.music2.api3.service.metadata.info.GetMvUriInfo;
import com.kascend.music2.api3.service.metadata.info.GetRandomSongInfo;
import com.kascend.music2.api3.service.metadata.info.GetSearchKeywordInfo;
import com.kascend.music2.api3.service.metadata.info.GetSimilarSongInfo;
import com.kascend.music2.api3.service.metadata.info.GetSongInfo;
import com.kascend.music2.api3.service.metadata.info.GetSongsOfAlbumInfo;
import com.kascend.music2.api3.service.metadata.info.GetSongsOfArtistInfo;
import com.kascend.music2.api3.service.metadata.info.ListArtistInfo;
import com.kascend.music2.api3.service.metadata.info.ListBillboardInfo;
import com.kascend.music2.api3.service.metadata.info.ListMvInfo;
import com.kascend.music2.api3.service.metadata.info.LookupMvInfo;
import com.kascend.music2.api3.service.metadata.info.LookupSongInfo;
import com.kascend.music2.api3.service.metadata.info.MusicInfo;
import com.kascend.music2.api3.service.metadata.info.RecommendArtistInfo;
import com.kascend.music2.api3.service.metadata.info.RecommendalbumInfo;
import com.kascend.music2.api3.service.metadata.info.SearchAlbumInfo;
import com.kascend.music2.api3.service.metadata.info.SearchArtistInfo;
import com.kascend.music2.api3.service.metadata.info.SearchSongInfo;
import com.kascend.music2.api3.service.metadata.info.SongUriListInfo;
import com.kascend.music2.api3.service.metadata.info.StatClientUsageInfo;
import com.kascend.music2.api3.service.metadata.info.StatInfo;
import com.kascend.music2.api3.service.metadata.info.TopAlbumInfo;
import com.kascend.music2.api3.service.metadata.info.TopArtistInfo;
import com.kascend.music2.api3.service.metadata.info.TopMvInfo;
import com.kascend.music2.api3.service.metadata.info.TopSongInfo;
import com.kascend.music2.api3.service.metadata.info.UploadmetadataInfo;
import com.kascend.music2.api3.service.metadata.response.CountResponse;
import com.kascend.music2.api3.service.metadata.response.New_GetMtvInfoResponse;
import com.kascend.music2.api3.service.metadata.response.New_AlbumInfoResponse;
import com.kascend.music2.api3.service.metadata.response.New_AlbumResponse;
import com.kascend.music2.api3.service.metadata.response.New_ArtistInfoResponse;
import com.kascend.music2.api3.service.metadata.response.New_ArtistListResponse;
import com.kascend.music2.api3.service.metadata.response.New_BillboardListResponse;
import com.kascend.music2.api3.service.metadata.response.New_DownloadUriResponse;
import com.kascend.music2.api3.service.metadata.response.New_GetLyricResponse;
import com.kascend.music2.api3.service.metadata.response.New_GetMvInfoResponse;
import com.kascend.music2.api3.service.metadata.response.New_GetSongInfoResponse;
import com.kascend.music2.api3.service.metadata.response.New_KeyWordListResponse;
import com.kascend.music2.api3.service.metadata.response.New_MtvResponse;
import com.kascend.music2.api3.service.metadata.response.New_MtvUriListResponse;
import com.kascend.music2.api3.service.metadata.response.New_MvResponse;
import com.kascend.music2.api3.service.metadata.response.New_MvUriListResponse;
import com.kascend.music2.api3.service.metadata.response.New_SongListResponse;
import com.kascend.music2.api3.service.metadata.response.New_SongUriListResponse;
import com.kascend.music2.api3.service.metadata.response.New_StatResponse;
import com.kascend.music2.api3.service.metadata.response.New_UserMvListResponse;
import com.kascend.music2.api3.service.metadata.response.New_UserPlaylistResponse;
import com.kascend.music2.api3.service.metadata.response.New_UserSongListResponse;
import com.kascend.music2.api3.service.metadata.response.entry.New_MtvEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_MtvEntryList;
import com.kascend.music2.api3.service.metadata.response.entry.New_MvEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_MvListEntry;
import com.kascend.music2.api3.service.social.response.LikeResponse;
import com.kascend.music2.api3.service.util.AnalysisZipFileUtil;
import com.kascend.music2.api3.service.util.ServiceUtils;

public  class MetadataServiceImpl implements MetadataService{
	
	MetadataServiceHelper mmsHelper;
	MetadataLogService logService;
	
	@Override
	public New_SongListResponse searchsongs(SearchSongInfo info) {
		New_SongListResponse response=mmsHelper.searchsongs(info);
		logService.saveSearchSongsLog(info, response);
		return response;
	}
	@Override
	public New_SongListResponse searchsongsforlucene(SearchSongInfo info) {
		return mmsHelper.searchsongsforlucene(info);
	}
	@Override
	public New_AlbumResponse searchalbums(SearchAlbumInfo info){
		New_AlbumResponse response=mmsHelper.searchalbums(info);
		logService.saveSearchAlbumLog(info, response);
		return response;
	}

	@Override
	public New_ArtistListResponse searchartists(SearchArtistInfo info) {
		New_ArtistListResponse response= mmsHelper.searchartists(info);
		logService.saveSearchArtistlog(info, response);
		return response;
	}

	@Override
	public New_SongListResponse lookupsongs(LookupSongInfo info){
		return mmsHelper.lookupsongs(info);
	}

	@Override
	public New_KeyWordListResponse getsearchkeywords(GetSearchKeywordInfo info) {
		return mmsHelper.getsearchkeywords(info);
	}

	@Override
	public New_KeyWordListResponse getsongkeywords(GetSearchKeywordInfo info) {
		return mmsHelper.getsongkeywords(info);
	}

	@Override
	public New_BillboardListResponse listbillboards(ListBillboardInfo info){
		return mmsHelper.listbillboards(info);
	}

	@Override
	public New_SongListResponse topsongs(TopSongInfo info) {
		New_SongListResponse response=mmsHelper.topsongs(info);
		Integer totalSize=response.getSongslist().getTotalsize();
		if(totalSize!=null&&totalSize.intValue()>0){
			long billboardId=response.getSongslist().getBillboardid();
			long subBillboardId=response.getSongslist().getSubbillboardid();
			info.setBillboardid(billboardId);
			info.setSubbillboardid(subBillboardId);
			logService.saveBillboardLog(info,totalSize);
		}
		
		return response;
	}
	@Override
	public New_AlbumResponse topalbums(TopAlbumInfo info) {
		New_AlbumResponse response=mmsHelper.topalbums(info);
		Integer totalSize=response.getAlbumslist().getTotalsize();
		if(totalSize!=null&&totalSize.intValue()>0){
			long billboardId=response.getAlbumslist().getBillboardid();
			long subBillboardId=response.getAlbumslist().getSubbillboardid();
			info.setBillboardid(billboardId);
			info.setSubbillboardid(subBillboardId);
			logService.saveBillboardLog(info,totalSize);
		}
		return response;
	}

	@Override
	public New_ArtistListResponse topartists(TopArtistInfo info) {
		New_ArtistListResponse response=mmsHelper.topartists(info);
			Integer totalSize=response.getArtistslist().getTotalsize();
			if(totalSize!=null&&totalSize.intValue()>0){
				long billboardId=response.getArtistslist().getBillboardid();
				long subBillboardId=response.getArtistslist().getSubbillboardid();
				info.setBillboardid(billboardId);
				info.setSubbillboardid(subBillboardId);
				logService.saveBillboardLog(info,totalSize);
			}
		return response;
	}
	@Override
	public New_ArtistInfoResponse getartistinfo(GetArtistInfo info){
		return mmsHelper.getartistinfo(info);
	}

	@Override
	public New_AlbumResponse getalbumsofartist(GetAlbumsOfArtistInfo info){
		return mmsHelper.getalbumsofartist(info);
	}

	public New_SongListResponse getsongsofalbum(GetSongsOfAlbumInfo info) {
		return mmsHelper.getsongsofalbum(info);
	}
	
	@Override
	public New_AlbumResponse getalbumsofsong(GetAlbumsOfSongInfo info){
		return mmsHelper.getalbumsofsong(info);
	}
	
	@Override
	public New_AlbumInfoResponse getalbuminfo(GetAlbumInfo info){
		return mmsHelper.getalbuminfo(info);
	}
	

	@Override
	public New_AlbumInfoResponse getalbumart(GetAlbumInfo info) {
		return mmsHelper.getalbumart(info);
	}

	@Override
	public New_GetSongInfoResponse getsonginfo(GetSongInfo info) {
		New_GetSongInfoResponse response=mmsHelper.getsonginfo(info);
		if(response.getSonginfo().size()>0){
			Long songId=response.getSonginfo().get(0).getSongid();
			if(songId!=null){
				logService.saveGetSongInfoLog(info, response,songId);
			}
		}
		return response;
	}
	@Override
	public New_GetSongInfoResponse getsonginfoforlucene(GetSongInfo info) {
		New_GetSongInfoResponse response=mmsHelper.getsonginfoforlucene(info);
		return response;
	}
	@Override
	public New_GetLyricResponse getlyric(GetLyricInfo info){
		return mmsHelper.getlyric(info);
	}

	@Override
	public New_SongUriListResponse getsongurilist(SongUriListInfo info) {
		return mmsHelper.getsongurilist(info);
	}

	@Override
	public New_SongUriListResponse getsampleurilist(SongUriListInfo info){
		return getsongurilist(info);
	}
	@Override
	public New_ArtistListResponse recommendartists(RecommendArtistInfo info){
		return mmsHelper.recommendartists(info);
	}

	@Override
	public New_AlbumResponse recommendalbums(RecommendalbumInfo info){
		return mmsHelper.recommendalbums(info);
	}

	@Override
	public New_SongListResponse getsimilarsongs(GetSimilarSongInfo info) {
		return mmsHelper.getsimilarsongs(info);
	}


	@Override
	public New_SongListResponse getrandomsongs(GetRandomSongInfo info) {
		return mmsHelper.getrandomsongs(info);
	}

	@Override
	public LikeResponse  likesong(MusicInfo info){
		return mmsHelper.likesong(info);
	}

	@Override
	public New_StatResponse stat(StatInfo info){
		New_StatResponse response=mmsHelper.stat(info);
		if(response.getRc()==0){
			logService.saveStatLog(info);
		}
		return response;
	}
	@Override
	public New_StatResponse feedbacksong(FeedBackSongInfo info) {
		New_StatResponse response=mmsHelper.feedbacksong(info);
		if(response.getRc()==0){
			logService.saveFeedBackSongLog(info);
		}
		return response;
	}

	@Override
	public New_StatResponse statclientusage(StatClientUsageInfo info) {
		return mmsHelper.statclientusage(info);
	}
	public MetadataLogService getLogService() {
		return logService;
	}

	public void setLogService(MetadataLogService logService) {
		this.logService = logService;
	}

	public MetadataServiceHelper getMmsHelper() {
		return mmsHelper;
	}

	public void setMmsHelper(MetadataServiceHelper mmsHelper) {
		this.mmsHelper = mmsHelper;
	}
	@Override
	public New_MvResponse listmvs(ListMvInfo info) {
		return mmsHelper.listmvs(info);
	}
	@Override
	public New_MvResponse topmvs(TopMvInfo info){
		New_MvResponse response=mmsHelper.topmvs(info);
			Integer totalSize=response.getMvlist().getTotalsize();
			if(totalSize!=null&&totalSize.intValue()>0){
				long billboardId=response.getMvlist().getBillboardid();
				long subBillboardId=response.getMvlist().getSubbillboardid();
				info.setBillboardid(billboardId);
				info.setSubbillboardid(subBillboardId);
				logService.saveBillboardLog(info,totalSize);
			}
		return response;
	}
	@Override
	public New_MtvResponse topmtvs(TopMvInfo info) {
		New_MtvResponse response=mmsHelper.topmtvs(info);
			Integer totalSize=response.getMtvlist().getTotalsize();
			if(totalSize!=null&&totalSize.intValue()>0){
				long billboardId=response.getMtvlist().getBillboardid();
				long subBillboardId=response.getMtvlist().getSubbillboardid();
				info.setBillboardid(billboardId);
				info.setSubbillboardid(subBillboardId);
				logService.saveBillboardLog(info,totalSize);
			}
		return response;
	}
	@Override
	public New_MvResponse lookupmvs(LookupMvInfo info) {
		return mmsHelper.lookupmvs(info);
	}
	@Override
	public New_MtvResponse lookupmtvs(LookupMvInfo info) {
		return mmsHelper.lookupmtvs(info);
	}

	@Override
	public New_GetMtvInfoResponse getmtvinfo(GetMvInfo info) {
		New_GetMtvInfoResponse response=mmsHelper.getmtvinfo(info);
		long songId=0l;
		if (info.getSongid()>0) {
			songId=info.getSongid();
		} else {
			if(response.getMtvinfo().size()>0){
				Long songid=response.getMtvinfo().get(0).getSongid();
				if(songid!=null){
					songId=songid;
				}
			}
		}
		logService.saveGetMvInfoLog(info,songId);
		return response;
	}
	public New_GetMvInfoResponse getmvinfo(GetMvInfo info) {
		New_GetMvInfoResponse response=mmsHelper.getmvinfo(info);
		long songId=0l;
		if (info.getSongid()>0) {
			songId=info.getSongid();
		} else {
			if(response.getMvinfo().size()>0){
				Long songid=response.getMvinfo().get(0).getSongid();
				if(songid!=null){
					songId=songid;
				}
			}
		}
		logService.saveGetMvInfoLog(info,songId);
		return response;
	}
	@Override
	public New_MtvResponse lookupgroupmtvs(LookupMvInfo info) {//TODO
		New_MtvResponse response=new New_MtvResponse();
		New_MtvEntryList result=new New_MtvEntryList();
		List<New_MtvEntry> mtvList=new ArrayList<New_MtvEntry>();
		result.setMtv(mtvList);
		response.setMtvlist(result);
		info.setArtistmvflag(info.getArtistmtvflag());
		info.setAlbummvflag(info.getAlbummtvflag());
		New_MvResponse mvResponse=lookupgroupmvs(info);
		New_MvListEntry mvListEntry=mvResponse.getMvlist();
		List<New_MvEntry> mvEntryList=mvListEntry.getMv();
		for(int i=0;i<mvEntryList.size();i++){
			New_MvEntry mvEntry=mvEntryList.get(i);
			SongMv songMv=mmsHelper.mv2mtvEntryCopy(mvEntry);
			New_MtvEntry mtvEntry=new New_MtvEntry(songMv);
			mtvList.add(i, mtvEntry);
		}
		result.setMtvscount(mvListEntry.getMvscount());
		return response;
	}
	public New_MvResponse lookupgroupmvs(LookupMvInfo info){
		New_MvResponse response=new New_MvResponse();
		ServiceUtils.pageCompute(info);
		String xmlStr=info.getXml();
		if(Validator.isBlank(xmlStr)){
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		New_MvListEntry mtvEntrys=new New_MvListEntry();
		List<New_MvEntry> entrys=new ArrayList<New_MvEntry>();
		List<LookupMvInfo> lookupMvInfoList=AnalysisZipFileUtil.getSearchInformationFromXMLStr(xmlStr);
		if(lookupMvInfoList!=null&&lookupMvInfoList.size()>0){
			for(int i=0;i<lookupMvInfoList.size();i++){
				LookupMvInfo lookupMvInfo=lookupMvInfoList.get(i);
				lookupMvInfo.setPage(1);
				lookupMvInfo.setPagecount(info.getPagecount());
				lookupMvInfo.setArtistmvflag(info.getArtistmvflag());
				lookupMvInfo.setAlbummvflag(info.getAlbummvflag());
				New_MvListEntry mvEntry=mmsHelper.getSongMvEntryList(lookupMvInfo);
				List<New_MvEntry> toplist=mvEntry.getMv();
				entrys.addAll(toplist);
			}
		}
		entrys=ServiceUtils.removeIterativeSongIdForMvEntry(entrys);
		entrys=ServiceUtils.removeIterativeMvIdForMvEntry(entrys);
		entrys=ServiceUtils.outSidePaging(entrys, info.getPagecount(), info.getPage());
		List<New_MvEntry> list=ServiceUtils.randomAccessList(entrys);
		mtvEntrys.setMvscount(list.size());
		mtvEntrys.setMv(list);
		response.setMvlist(mtvEntrys);
		return response;
	}
	@Override
	public CountResponse cacheflush(CacheFlushInfo info) {
		return new CountResponse();
	}

	@Override
	public New_MtvUriListResponse getmtvuri(GetMvUriInfo info) {
		info.setMvid(info.getMtvid());
		logService.saveGetMvUriLog(info);
		return mmsHelper.getmtvuri(info);
	}
	@Override
	public New_MvUriListResponse getmvuri(GetMvUriInfo info) {
		logService.saveGetMvUriLog(info);
		return mmsHelper.getmvuri(info);
	}

	@Override
	public New_ArtistListResponse listartists(ListArtistInfo info) {
		return mmsHelper.listartists(info);
	}
	@Override
	public New_ArtistInfoResponse getartistart(GetArtistInfo info) {
		return mmsHelper.getartistart(info);
	}
	@Override
	public New_SongListResponse getsongsofartist(GetSongsOfArtistInfo info) {
		return mmsHelper.getsongsofartist(info);
	}
	@Override
	public New_SongListResponse gethotsongsofartist(GetSongsOfArtistInfo info) {
		return mmsHelper.gethotsongsofartist(info);
	}
	@Override
	public New_DownloadUriResponse getdownloaduri(GetDownloadUriInfo info) {
		return mmsHelper.getdownloaduri(info);
	}
	@Override
	public New_DownloadUriResponse getsampleuri(GetDownloadUriInfo info) {
		return mmsHelper.getsampleuri(info);
	}
	@Override
	public New_StatResponse uploadmetadatas(UploadmetadataInfo info) {
		return mmsHelper.uploadmetadatas(info);
	}
	@Override
	public New_SongListResponse jlucenesearchsongs(GetSongInfo info) {
		return mmsHelper.jlucenesearchsongs(info);
	}
	@Override
	public New_ArtistListResponse jlucenesearchartists(GetSongInfo info) {
		return mmsHelper.jlucenesearchartists(info);
	}
	@Override
	public New_AlbumResponse jlucenesearchalbums(GetSongInfo info) {
		return mmsHelper.jlucenesearchalbums(info);
	}
	@Override
	public New_SongListResponse jlucenesearchkeywords(SearchSongInfo info) {
		return mmsHelper.jlucenesearchkeywords(info);
	}
	@Override
	public New_MtvResponse listmtvs(ListMvInfo info) {
		return mmsHelper.listmtvs(info);
	}

}
