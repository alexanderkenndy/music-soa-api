package com.kascend.music2.api3.service.metadata.impl;


import java.util.ArrayList;
import java.util.List;

import com.kascend.common.util.Validator;
import com.kascend.music2.api3.cache.CacheService;
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

public class CacheMetadataServiceImpl implements MetadataService{
	
	MetadataServiceHelper mmsHelper;
	CacheService cacheService;
	MetadataLogService logService;

	@Override
	public New_SongListResponse searchsongs(SearchSongInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("searchsongs").append("_")
		.append(info.getAppkey()).append("_")
		.append(info.getKeyname()).append("_")
		.append(info.getPage()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getAllcountflag()).append("_")
		.append(info.getAlbumartflag()).append("_")
		.append(info.getAlbumartsize());
		String key=new String(sb);
		New_SongListResponse response=(New_SongListResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.searchsongs(info);
			cacheService.put(key, response);
		}
		logService.saveSearchSongsLog(info, response);
		return response;
	}

	@Override
	public New_SongListResponse searchsongsforlucene(SearchSongInfo info) {
		return mmsHelper.searchsongsforlucene(info);
	}
	@Override
	public New_AlbumResponse searchalbums(SearchAlbumInfo info){
		StringBuffer sb=new StringBuffer();
		sb.append("searchalbums").append("_")
		.append(info.getKeyname()).append("_")
		.append(info.getPage()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getAllcountflag()).append("_")
		.append(info.getAlbumartflag()).append("_")
		.append(info.getAlbumartsize()).append("_")
		.append(info.getBioflag());
		String key=new String(sb);
		New_AlbumResponse response=(New_AlbumResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.searchalbums(info);
			cacheService.put(key, response);
		}
		logService.saveSearchAlbumLog(info, response);
		return response;
	}
	

	@Override
	public New_ArtistListResponse searchartists(SearchArtistInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("searchartists").append("_")
		.append(info.getKeyname()).append("_")
		.append(info.getPage()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getArtistartflag()).append("_")
		.append(info.getArtistartsize()).append("_")
		.append(info.getAllcountflag()).append("_")
		.append(info.getBioflag());
		String key=new String(sb);
		New_ArtistListResponse response=(New_ArtistListResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.searchartists(info);
			cacheService.put(key, response);
		}
		logService.saveSearchArtistlog(info, response);
		return response;
	}
	@Override
	public New_SongListResponse lookupsongs(LookupSongInfo info){
		StringBuffer sb=new StringBuffer();
		sb.append("lookupsongs").append("_")
		.append(info.getSong()).append("_")
		.append(info.getArtist()).append("_")
		.append(info.getAlbum()).append("_")
		.append(info.getFilename()).append("_")
		.append(info.getPage()).append("_")
		.append(info.getAllcountflag()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getAlbumartflag()).append("_")
		.append(info.getAlbumartsize());
		String key=new String(sb);
		New_SongListResponse response=(New_SongListResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.lookupsongs(info);
			cacheService.put(key, response);
		}
		return response;
	}


	@Override
	public New_KeyWordListResponse getsearchkeywords(GetSearchKeywordInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getsearchkeywords").append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getType());
		String key=new String(sb);
		New_KeyWordListResponse response=(New_KeyWordListResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.getsearchkeywords(info);
			cacheService.put(key, response);
		}
		return response;
	}
	@Override
	public New_KeyWordListResponse getsongkeywords(GetSearchKeywordInfo info) {
		return this.getsearchkeywords(info);
	}

	@Override
	public New_BillboardListResponse listbillboards(ListBillboardInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("listbillboards")	.append("_")
		.append(info.getBillboardgroupid())	.append("_")
		.append(info.getBillboardgroupcode());
		String key=new String(sb);
		New_BillboardListResponse response=(New_BillboardListResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.listbillboards(info);
			cacheService.put(key, response);
		}
		return response;
	}

	@Override
	public New_SongListResponse topsongs(TopSongInfo info) {
		
		StringBuffer sb=new StringBuffer();
		sb.append("topsongs").append("_")
		.append(info.getBillboardid()).append("_")
		.append(info.getSubbillboardid()).append("_")
		.append(info.getPage()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getAlbumartflag()).append("_")
		.append(info.getAlbumartsize());
		String key=new String(sb);
		New_SongListResponse response=(New_SongListResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.topsongs(info);
			cacheService.put(key.toString(), response);
		}
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
		StringBuffer sb=new StringBuffer();
		sb.append("topalbums").append("_")
		.append(info.getBillboardid()).append("_")
		.append(info.getPage()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getSubbillboardid()).append("_")
		.append(info.getBioflag()).append("_")
		.append(info.getAlbumartflag()).append("_")
		.append(info.getAlbumartsize());
		String key=new String(sb);
		New_AlbumResponse response=(New_AlbumResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.topalbums(info);
			cacheService.put(key, response);
		}
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
		StringBuffer sb=new StringBuffer();
		sb.append("topartists").append("_")
		.append(info.getBillboardid()).append("_")
		.append(info.getPage())	.append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getArtistartflag()).append("_")
		.append(info.getSubbillboardid()).append("_")
		.append(info.getBioflag()).append("_")
		.append(info.getArtistartsize());
		String key=new String(sb);
		
		New_ArtistListResponse response=(New_ArtistListResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.topartists(info);
			cacheService.put(key, response);
		}
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
		StringBuffer sb=new StringBuffer();
		sb.append("getartistinfo")
		.append("_")
		.append(info.getArtist())
		.append("_")
		.append(info.getArtistid())
		.append("_")
		.append(info.getArtistartsize());
		String key=new String(sb);
		New_ArtistInfoResponse response=(New_ArtistInfoResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.getartistinfo(info);
			cacheService.put(key, response);
		}
		return response;
	}
	@Override
	public New_ArtistInfoResponse getartistart(GetArtistInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getartistart")
		.append("_")
		.append(info.getArtist())
		.append("_")
		.append(info.getArtistid())
		.append("_")
		.append(info.getArtistartsize());
		String key=new String(sb);
		New_ArtistInfoResponse response=(New_ArtistInfoResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.getartistart(info);
			cacheService.put(key, response);
		}
		return response;
	}
	
	@Override
	public New_AlbumResponse getalbumsofartist(GetAlbumsOfArtistInfo info){
		StringBuffer sb=new StringBuffer();
		sb.append("getalbumsofartist")
		.append("_")
		.append(info.getArtist())
		.append("_")
		.append(info.getArtistid())
		.append("_")
		.append(info.getBioflag())
		.append("_")
		.append(info.getPage())
		.append("_")
		.append(info.getPagecount())
		.append("_")
		.append(info.getAllcountflag())
		.append("_")
		.append(info.getAlbumartflag())
		.append("_")
		.append(info.getAlbumartsize());
		String key=new String(sb);
		New_AlbumResponse response=(New_AlbumResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.getalbumsofartist(info);
			cacheService.put(key, response);
		}
		return response;
	}

	@Override
	public New_SongListResponse getsongsofalbum(GetSongsOfAlbumInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getsongsofalbum").append("_")
		.append(info.getAppkey()).append("_")
		.append(info.getAlbum()).append("_")
		.append(info.getAlbumid()).append("_")
		.append(info.getSort()).append("_")
		.append(info.getArtist()).append("_")
		.append(info.getAlbumartflag()).append("_")
		.append(info.getAlbumartsize());
		String key=new String(sb);
		New_SongListResponse response=(New_SongListResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.getsongsofalbum(info);
			cacheService.put(key, response);
		}
		return response;
	}
	@Override
	public New_AlbumResponse getalbumsofsong(GetAlbumsOfSongInfo info){
		StringBuffer sb=new StringBuffer();
		sb.append("getalbumsofsong")
		.append("_")
		.append(info.getSong())
		.append("_")
		.append(info.getSongid())
		.append("_")
		.append(info.getArtist())
		.append("_")
		.append(info.getAlbumartflag())
		.append("_")
		.append(info.getBioflag())
		.append("_")
		.append(info.getAlbumartsize());
		String key=new String(sb);
		New_AlbumResponse response=(New_AlbumResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.getalbumsofsong(info);
			cacheService.put(key, response);
		}
		return response;
	}
	@Override
	public New_AlbumInfoResponse getalbuminfo(GetAlbumInfo info){
		StringBuffer sb=new StringBuffer();
		sb.append("getalbuminfo")
		.append("_")
		.append(info.getArtist())
		.append("_")
		.append(info.getAlbum())
		.append("_")
		.append(info.getAlbumid())
		.append("_")
		.append(info.getAlbumartsize());
		String key=new String(sb);
		New_AlbumInfoResponse response=(New_AlbumInfoResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.getalbuminfo(info);
			cacheService.put(key, response);
		}
		return response;
	}

	@Override
	public New_AlbumInfoResponse getalbumart(GetAlbumInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getalbumart")
		.append("_")
		.append(info.getArtist())
		.append("_")
		.append(info.getAlbum())
		.append("_")
		.append(info.getAlbumid())
		.append("_")
		.append(info.getAlbumartsize());
		String key=new String(sb);
		New_AlbumInfoResponse response=(New_AlbumInfoResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.getalbumart(info);
			cacheService.put(key, response);
		}
		return response;
	}
	@Override
	public New_GetSongInfoResponse getsonginfoforlucene(GetSongInfo info) {
		return mmsHelper.getsonginfoforlucene(info);
	}
	@Override
	public New_GetSongInfoResponse getsonginfo(GetSongInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getsonginfo").append("_")
		.append(info.getSongid()).append("_")
		.append(info.getSong()).append("_")
		.append(info.getArtist()).append("_")
		.append(info.getAlbum()).append("_")
		.append(info.getFilename()).append("_")
		.append(info.getArtistartfilter()).append("_")
		.append(info.getLyricfilter()).append("_")
		.append(info.getLyricflag()).append("_")
		.append(info.getAlbumartfilter()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getAlbumartflag()).append("_")
		.append(info.getAlbumartsize()).append("_")
		.append(info.getArtistartsize()).append("_")
		.append(info.getArtistartflag());
		String key=new String(sb);
		New_GetSongInfoResponse response=(New_GetSongInfoResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.getsonginfo(info);
			cacheService.put(key, response);
		}
		if(response.getSonginfo().size()>0){
			Long songId=response.getSonginfo().get(0).getSongid();
			if(songId!=null){
				logService.saveGetSongInfoLog(info, response,songId);
			}
		}
		return response;
	}
	
	@Override
	public New_GetLyricResponse getlyric(GetLyricInfo info){
		StringBuffer sb=new StringBuffer();
		sb.append("getlyric")
		.append("_")
		.append(info.getSongid())
		.append("_")
		.append(info.getSong())
		.append("_")
		.append(info.getArtist());
		String key=new String(sb);
		New_GetLyricResponse response=(New_GetLyricResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.getlyric(info);
			cacheService.put(key, response);
		}
		return response;
	}
	@Override
	public New_SongUriListResponse getsongurilist(SongUriListInfo info) {
//		StringBuffer sb=new StringBuffer();
//		sb.append("getsongurilist")
//		.append("_")
//		.append(info.getRate()).append("_")
//		.append(info.getSongid());
//		String key=new String(sb);
//		New_SongUriListResponse response=(New_SongUriListResponse)cacheService.get(key);
//		if(response==null){
//			response=mmsHelper.getsongurilist(info);
//			cacheService.put(key, response);
//		}
		return mmsHelper.getsongurilist(info);
	}
	@Override
	public New_SongUriListResponse getsampleurilist(SongUriListInfo info) {
		
		return getsongurilist(info);
	}
	@Override
	public New_DownloadUriResponse getdownloaduri(GetDownloadUriInfo info){
		return mmsHelper.getdownloaduri(info);
	}


	@Override
	public New_DownloadUriResponse getsampleuri(GetDownloadUriInfo info){
		return mmsHelper.getsampleuri(info);
	}
	@Override
	public New_ArtistListResponse recommendartists(RecommendArtistInfo info){
		StringBuffer sb=new StringBuffer();
		sb.append("recommendartists")
		.append("_")
		.append(info.getArtistid())
		.append("_")
		.append(info.getPagecount())
		.append("_")
		.append(info.getBioflag())
		.append("_")
		.append(info.getArtist())
		.append("_")
		.append(info.getPage())
		.append("_")
		.append(info.getAllcountflag())
		.append("_")
		.append(info.getArtistartflag())
		.append("_")
		.append(info.getArtistartsize());
		String key=new String(sb);
		New_ArtistListResponse response=(New_ArtistListResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.recommendartists(info);
			cacheService.put(key, response);
		}
		return response;
	}

	@Override
	public New_AlbumResponse recommendalbums(RecommendalbumInfo info){
		StringBuffer sb=new StringBuffer();
		sb.append("recommendalbums")
		.append("_")
		.append(info.getAlbum())
		.append("_")
		.append(info.getAlbumid())
		.append("_")
		.append(info.getPagecount())
		.append("_")
		.append(info.getPage())
		.append("_")
		.append(info.getAllcountflag())
		.append("_")
		.append(info.getBioflag())
		.append("_")
		.append(info.getArtist())
		.append("_")
		.append(info.getAlbumartflag())
		.append("_")
		.append(info.getAlbumartsize());
		String key=new String(sb);
		New_AlbumResponse response=(New_AlbumResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.recommendalbums(info);
			cacheService.put(key, response);
		}
		return response;
	}

	@Override
	public New_SongListResponse getsimilarsongs(GetSimilarSongInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getsimilarsongs")
		.append("_")
		.append(info.getSongid())
		.append("_")
		.append(info.getSong())
		.append("_")
		.append(info.getArtist())
		.append("_")
		.append(info.getAlbumartflag())
		.append("_")
		.append(info.getAlbumartsize());
		String key=new String(sb);
		New_SongListResponse response=(New_SongListResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.getsimilarsongs(info);
			cacheService.put(key, response);
		}
		return response;
	}

	@Override
	public New_SongListResponse getrandomsongs(GetRandomSongInfo info) {
		return mmsHelper.getrandomsongs(info);
	}

	@Override
	public LikeResponse  likesong(MusicInfo info) {
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
	public New_SongListResponse getsongsofartist(GetSongsOfArtistInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getsongsofartist").append("_")
		.append(info.getAppkey()).append("_")
		.append(info.getArtist()).append("_")
		.append(info.getArtistid()).append("_")
		.append(info.getPage()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getAllcountflag()).append("_")
		.append(info.getAlbumartflag()).append("_")
		.append(info.getAlbumartsize());
		String key=new String(sb);
		New_SongListResponse response=(New_SongListResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.getsongsofartist(info);
			cacheService.put(key, response);
		}
		return response;
	}
	
	@Override
	public New_SongListResponse gethotsongsofartist(GetSongsOfArtistInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("gethotsongsofartist")
		.append("_")
		.append(info.getAppkey())
		.append("_")
		.append(info.getArtist())
		.append("_")
		.append(info.getArtistid())
		.append("_")
		.append(info.getPage())
		.append("_")
		.append(info.getPagecount())
		.append("_")
		.append(info.getAllcountflag())
		.append("_")
		.append(info.getAlbumartflag())
		.append("_")
		.append(info.getAlbumartsize());
		String key=new String(sb);
		New_SongListResponse response=(New_SongListResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.gethotsongsofartist(info);
			cacheService.put(key, response);
		}
		return response;
	}


	@Override
	public New_MtvUriListResponse getmtvuri(GetMvUriInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getmtvuri")
		.append("_")
		.append(info.getMtvid())
		.append("_")
		.append(info.getSongid());
		String key=new String(sb);
		New_MtvUriListResponse response=(New_MtvUriListResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.getmtvuri(info);
			cacheService.put(key, response);
		}
		info.setMvid(info.getMtvid());
		logService.saveGetMvUriLog(info);
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

	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}


	@Override
	public New_MvResponse listmvs(ListMvInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("listmvs")
		.append("_")
		.append(info.getPage())
		.append("_")
		.append(info.getArtist())
		.append("_")
		.append(info.getArtistid())
		.append("_")
		.append(info.getPagecount());
		String key=new String(sb);
		New_MvResponse response=(New_MvResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.listmvs(info);
			cacheService.put(key, response);
		}
		return response;
	}

	@Override
	public New_MtvResponse topmtvs(TopMvInfo info){
		StringBuffer sb=new StringBuffer();
		sb.append("topmtvs")
		.append("_")
		.append(info.getBillboardid())
		.append("_")
		.append(info.getSubbillboardid())
		.append("_")
		.append(info.getPage())
		.append("_")
		.append(info.getPagecount());
		String key=new String(sb);
		New_MtvResponse response=(New_MtvResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.topmtvs(info);
			cacheService.put(key.toString(), response);
		}
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
	public New_MvResponse topmvs(TopMvInfo info){
		StringBuffer sb=new StringBuffer();
		sb.append("topmvs")
		.append("_")
		.append(info.getBillboardid())
		.append("_")
		.append(info.getPage())
		.append("_")
		.append(info.getSubbillboardid())
		.append("_")
		.append(info.getPagecount());
		
		String key=new String(sb);
		New_MvResponse response=(New_MvResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.topmvs(info);
			cacheService.put(key.toString(), response);
		}
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
	public New_MvResponse lookupmvs(LookupMvInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("lookupmvs")
		.append("_")
		.append(info.getSong())
		.append("_")
		.append(info.getSongid())
		.append("_")
		.append(info.getArtist())
		.append("_")
		.append(info.getAlbum())
		.append("_")
		.append(info.getFilename())
		.append("_")
		.append(info.getPage())
		.append("_")
		.append(info.getAllcountflag())
		.append("_")
		.append(info.getPagecount())
		.append("_")
		.append(info.getArtistmvflag())
		.append("_")
		.append(info.getAlbummvflag());
		String key=new String(sb);
		New_MvResponse response=(New_MvResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.lookupmvs(info);
			cacheService.put(key, response);
		}
		return response;
	}
	@Override
	public New_MtvResponse lookupmtvs(LookupMvInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("lookupmtvs")
		.append("_")
		.append(info.getSong())
		.append("_")
		.append(info.getSongid())
		.append("_")
		.append(info.getArtist())
		.append("_")
		.append(info.getAlbum())
		.append("_")
		.append(info.getFilename())
		.append("_")
		.append(info.getPage())
		.append("_")
		.append(info.getAllcountflag())
		.append("_")
		.append(info.getPagecount())
		.append("_")
		.append(info.getArtistmtvflag())
		.append("_")
		.append(info.getAlbummtvflag());
		String key=new String(sb);
		New_MtvResponse response=(New_MtvResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.lookupmtvs(info);
			cacheService.put(key, response);
		}
		return response;
	}
	@Override
	public New_GetMvInfoResponse getmvinfo(GetMvInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getmvinfo")
		.append("_")
		.append(info.getSongid()).append("_")
		.append(info.getMvid()).append("_")
		.append(info.getSong()).append("_")
		.append(info.getArtist()).append("_")
		.append(info.getAlbum()).append("_")
		.append(info.getFilename()).append("_")
		.append(info.getMvartsize());
		String key=new String(sb);
		New_GetMvInfoResponse response=(New_GetMvInfoResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.getmvinfo(info);
			cacheService.put(key, response);
		}
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
	public New_GetMtvInfoResponse getmtvinfo(GetMvInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getmtvinfo").append("_")
		.append(info.getSongid()).append("_")
		.append(info.getMtvid()).append("_")
		.append(info.getSong()).append("_")
		.append(info.getArtist()).append("_")
		.append(info.getAlbum()).append("_")
		.append(info.getFilename()).append("_")
		.append(info.getMtvartsize());
		String key=new String(sb);
		New_GetMtvInfoResponse response=(New_GetMtvInfoResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.getmtvinfo(info);
			cacheService.put(key, response);
		}
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
	@Override
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
				New_MvListEntry mvEntry=this.cacheMvEntryList(lookupMvInfo);
				List<New_MvEntry> toplist=mvEntry.getMv();
				entrys.addAll(toplist);
			}
		}
		entrys=ServiceUtils.removeIterativeSongIdForMvEntry(entrys);
		entrys=ServiceUtils.removeIterativeMvIdForMvEntry(entrys);
		entrys=ServiceUtils.outSidePaging(entrys, info.getPagecount(), info.getPage());
		List<New_MvEntry> list=ServiceUtils.randomAccessList(entrys);
		mtvEntrys.setMvscount(list.size());
		mtvEntrys.setMv(entrys);
		response.setMvlist(mtvEntrys);
		return response;
	}
	@Override
	public New_MtvResponse lookupgroupmtvs(LookupMvInfo info) {
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
	public New_MvListEntry cacheMvEntryList(LookupMvInfo info){
		StringBuffer sb=new StringBuffer();
		sb.append("lookupgroupmvs").append("_")
		.append(info.getSong()).append("_")
		.append(info.getSongid()).append("_")
		.append(info.getArtist()).append("_")
		.append(info.getAlbum()).append("_")
		.append(info.getFilename()).append("_")
		.append(info.getPage()).append("_")
		.append(info.getXml()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getArtistmvflag()).append("_")
		.append(info.getAlbummvflag());
		 String key=new String(sb);
		 New_MvListEntry mtvEntry=(New_MvListEntry)cacheService.get(key);
			if(mtvEntry==null){
				mtvEntry=mmsHelper.getSongMvEntryList(info);
				cacheService.put(key, mtvEntry);
			}
		return mtvEntry;
	}
	@Override
	public CountResponse cacheflush(CacheFlushInfo info){
		if(info.getFlushcode()==1010){
			cacheService.flushAll();
		}
		return new CountResponse();
	}

	@Override
	public New_MvUriListResponse getmvuri(GetMvUriInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getmvuri").append("_")
		.append(info.getMvid()).append("_")
		.append(info.getSongid());
		String key=new String(sb);
		New_MvUriListResponse response=(New_MvUriListResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.getmvuri(info);
			cacheService.put(key, response);
		}
		logService.saveGetMvUriLog(info);
		return response;
	}

	@Override
	public New_ArtistListResponse listartists(ListArtistInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("listartists").append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getPage()).append("_")
		.append(info.getBioflag()).append("_")
		.append(info.getRegions()).append("_")
		.append(info.getStyles()).append("_")
		.append(info.getSort()).append("_")
		.append(info.getAllcountflag());
		String key=new String(sb);
		New_ArtistListResponse response=(New_ArtistListResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.listartists(info);
			cacheService.put(key, response);
		}
		return response;
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
		StringBuffer sb=new StringBuffer();
		sb.append("listmtvs2")
		.append("_")
		.append(info.getPage())
		.append("_")
		.append(info.getArtist())
		.append("_")
		.append(info.getArtistid())
		.append("_")
		.append(info.getPagecount());
		String key=new String(sb);
		New_MtvResponse response=(New_MtvResponse)cacheService.get(key);
		if(response==null){
			response=mmsHelper.listmtvs(info);
			cacheService.put(key, response);
		}
		return response;
	}


}
