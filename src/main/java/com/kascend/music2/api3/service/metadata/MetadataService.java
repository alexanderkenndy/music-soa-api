package com.kascend.music2.api3.service.metadata;

import com.kascend.music2.api3.service.metadata.info.CacheFlushInfo;
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
import com.kascend.music2.api3.service.social.response.LikeResponse;

public interface MetadataService {
	
	@Deprecated
	//public MusicResponse likesong(MusicInfo info);
	public LikeResponse  likesong(MusicInfo info);
	@Deprecated
	public New_MtvResponse listmtvs(ListMvInfo info);
	
	@Deprecated
	public New_MtvResponse lookupmtvs(LookupMvInfo info);
	@Deprecated
	public New_GetMtvInfoResponse getmtvinfo(GetMvInfo info);
	@Deprecated
	public New_MtvResponse lookupgroupmtvs(LookupMvInfo info);
	@Deprecated
	public New_MtvResponse topmtvs(TopMvInfo info);
	@Deprecated
	public New_MtvUriListResponse getmtvuri(GetMvUriInfo info);
	
	public CountResponse cacheflush(CacheFlushInfo info);
	public New_StatResponse  statclientusage(StatClientUsageInfo info);
	
	public New_SongListResponse getsimilarsongs(GetSimilarSongInfo info);
	
	public New_BillboardListResponse listbillboards(ListBillboardInfo info);
	
	public New_SongListResponse getrandomsongs(GetRandomSongInfo info);
	
	public New_SongListResponse topsongs(TopSongInfo info);
	
	//rename mtv to mv
	public New_MvUriListResponse getmvuri(GetMvUriInfo info);
	
	public New_MvResponse listmvs(ListMvInfo info);
	
	public New_MvResponse lookupmvs(LookupMvInfo info);
	
	public New_MvResponse lookupgroupmvs(LookupMvInfo info);
	
	public New_MvResponse topmvs(TopMvInfo info);
	
	public New_GetMvInfoResponse getmvinfo(GetMvInfo info);
	//new interface
	public New_ArtistListResponse searchartists(SearchArtistInfo info);
	
	public New_ArtistListResponse listartists(ListArtistInfo info);
	
	public New_SongListResponse searchsongs(SearchSongInfo info);
	
	public New_SongListResponse searchsongsforlucene(SearchSongInfo info);
	
	public New_ArtistListResponse topartists(TopArtistInfo info);
	
	
	public New_SongListResponse lookupsongs(LookupSongInfo info);
	
	public New_KeyWordListResponse getsearchkeywords(GetSearchKeywordInfo info);
	
	public New_KeyWordListResponse getsongkeywords(GetSearchKeywordInfo info);
	
	public New_ArtistInfoResponse getartistinfo(GetArtistInfo info);
	
	public New_ArtistInfoResponse getartistart(GetArtistInfo info);
	
	public New_SongListResponse getsongsofartist(GetSongsOfArtistInfo info);
	
	public New_SongListResponse gethotsongsofartist(GetSongsOfArtistInfo info);
	
	public New_AlbumInfoResponse getalbuminfo(GetAlbumInfo info);
	public New_AlbumInfoResponse getalbumart(GetAlbumInfo info);
	
	public New_SongListResponse getsongsofalbum(GetSongsOfAlbumInfo info);
	
	public New_GetLyricResponse getlyric(GetLyricInfo info);
	
	public New_AlbumResponse getalbumsofartist(GetAlbumsOfArtistInfo info);
	
	public New_StatResponse stat(StatInfo info);
	
	public New_AlbumResponse topalbums(TopAlbumInfo info);
	
	public New_AlbumResponse getalbumsofsong(GetAlbumsOfSongInfo info);
	
	public New_DownloadUriResponse getdownloaduri(GetDownloadUriInfo info);

	public New_DownloadUriResponse getsampleuri(GetDownloadUriInfo info);
	
	public New_AlbumResponse searchalbums(SearchAlbumInfo info);
	
	public New_GetSongInfoResponse getsonginfo(GetSongInfo info);
	
	public New_GetSongInfoResponse getsonginfoforlucene(GetSongInfo info);
	
	public New_ArtistListResponse recommendartists(RecommendArtistInfo info);
	
	public New_AlbumResponse recommendalbums(RecommendalbumInfo info);
	
	public New_StatResponse uploadmetadatas(UploadmetadataInfo info);
	
	public New_SongUriListResponse getsongurilist(SongUriListInfo info);

	public New_SongUriListResponse getsampleurilist(SongUriListInfo info);
	
	public New_SongListResponse  jlucenesearchsongs(GetSongInfo info);
	
	public New_ArtistListResponse  jlucenesearchartists(GetSongInfo info);
	
	public New_AlbumResponse  jlucenesearchalbums(GetSongInfo info);
	
	public New_SongListResponse  jlucenesearchkeywords(SearchSongInfo info);
	
	public New_StatResponse feedbacksong(FeedBackSongInfo info);
}
