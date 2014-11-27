package com.kascend.music2.api3.service.social.impl;

import java.util.ArrayList;
import java.util.List;
import com.kascend.music2.api3.cache.CacheService;
import com.kascend.music2.api3.service.log.SocialLogService;
import com.kascend.music2.api3.service.metadata.info.GetMvsOfPlaylistInfo;
import com.kascend.music2.api3.service.metadata.info.GetSongsOfPlaylistInfo;
import com.kascend.music2.api3.service.metadata.info.PageInfo;
import com.kascend.music2.api3.service.social.info.RelevanceUidInfo;
import com.kascend.music2.api3.service.metadata.info.SyncPlaylistInfo;
import com.kascend.music2.api3.service.metadata.response.New_AlbumInfoResponse;
import com.kascend.music2.api3.service.metadata.response.New_AlbumResponse;
import com.kascend.music2.api3.service.metadata.response.New_ArtistInfoResponse;
import com.kascend.music2.api3.service.metadata.response.New_ArtistListResponse;
import com.kascend.music2.api3.service.metadata.response.New_UserMvListResponse;
import com.kascend.music2.api3.service.metadata.response.New_UserSongListResponse;
import com.kascend.music2.api3.service.metadata.response.entry.New_ArtistEntry;
import com.kascend.music2.api3.service.social.SocialService;
import com.kascend.music2.api3.service.social.info.AttentionUsersInfo;
import com.kascend.music2.api3.service.social.info.CommentInfo;
import com.kascend.music2.api3.service.social.info.DeletePlaylistSongInfo;
import com.kascend.music2.api3.service.social.info.DiscoveryAlbumOfSongInfo;
import com.kascend.music2.api3.service.social.info.FollowArtistInfo;
import com.kascend.music2.api3.service.social.info.FollowUserInfo;
import com.kascend.music2.api3.service.social.info.GetCommentsOfItemInfo;
import com.kascend.music2.api3.service.social.info.GetLastSyncPlaylistTimeInfo;
import com.kascend.music2.api3.service.social.info.GetPlaylistsOfUserInfo;
import com.kascend.music2.api3.service.social.info.GetSnsShareInfo;
import com.kascend.music2.api3.service.social.info.GetTalentInfo;
import com.kascend.music2.api3.service.social.info.GetUserAlbumsInfo;
import com.kascend.music2.api3.service.social.info.GetUserAlbumsOfArtistInfo;
import com.kascend.music2.api3.service.social.info.GetUserArtistInfo;
import com.kascend.music2.api3.service.social.info.GetUserFansInfo;
import com.kascend.music2.api3.service.social.info.GetUserSongsOfAlbum;
import com.kascend.music2.api3.service.social.info.LeaveMessageInfo;
import com.kascend.music2.api3.service.social.info.LikeInfo;
import com.kascend.music2.api3.service.social.info.ListUserInfo;
import com.kascend.music2.api3.service.social.info.MyAttentionAlbumInfo;
import com.kascend.music2.api3.service.social.info.MyAttentionInfo;
import com.kascend.music2.api3.service.social.info.RecommendUserInfo;
import com.kascend.music2.api3.service.social.info.SearchUserInfo;
import com.kascend.music2.api3.service.social.info.ShareInfo;
import com.kascend.music2.api3.service.social.info.SnsAddItemInfo;
import com.kascend.music2.api3.service.social.info.SnsGetUserRelationListInfo;
import com.kascend.music2.api3.service.social.info.SnsInviteFriendInfo;
import com.kascend.music2.api3.service.social.info.SnsItemsOfArtistInfo;
import com.kascend.music2.api3.service.social.info.SnsWeiBoInfo;
import com.kascend.music2.api3.service.social.info.UpdateUserHeadIconInfo;
import com.kascend.music2.api3.service.social.info.UpdateUserInfo;
import com.kascend.music2.api3.service.social.info.UserFollowedArtistInfo;
import com.kascend.music2.api3.service.social.info.UserInfo;
import com.kascend.music2.api3.service.social.info.UserSnsShareInfo;
import com.kascend.music2.api3.service.social.response.CommentListResponse;
import com.kascend.music2.api3.service.social.response.CommentResponse;
import com.kascend.music2.api3.service.social.response.DeletePlaylistSongResponse;
import com.kascend.music2.api3.service.social.response.DiscoveryAlbumOfSongResponse;
import com.kascend.music2.api3.service.social.response.FollowResponse;
import com.kascend.music2.api3.service.social.response.ItemListResponse;
import com.kascend.music2.api3.service.social.response.LastSyncPlaylistTimeResponse;
import com.kascend.music2.api3.service.social.response.LeaveMessageResponse;
import com.kascend.music2.api3.service.social.response.LikeResponse;
import com.kascend.music2.api3.service.social.response.MyAttentionAlbumResponse;
import com.kascend.music2.api3.service.social.response.ShareResponse;
import com.kascend.music2.api3.service.social.response.UserListResponse;
import com.kascend.music2.api3.service.social.response.UserPlaylistResponse;
import com.kascend.music2.api3.service.social.response.UserResponse;
import com.kascend.music2.api3.service.social.response.UserSnsResponse;
import com.kascend.music2.api3.service.social.response.UserSnsShareResponse;
import com.kascend.music2.api3.service.social.response.WeiboBondAccountResponse;
import com.kascend.music2.api3.service.social.response.entry.ArtistFollowStatusListEntry;
import com.kascend.music2.api3.service.social.response.entry.UserEntry;
import com.kascend.music2.api3.service.social.response.entry.UserFollowStatusListEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;

public class CacheSocialServiceImpl implements SocialService {
	
	protected SocialServiceHelper sosHelper;
	protected SocialLogService socialLogService;
	CacheService cacheService;

	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public SocialServiceHelper getSosHelper() {
		return sosHelper;
	}

	public void setSosHelper(SocialServiceHelper sosHelper) {
		this.sosHelper = sosHelper;
	}

	public SocialLogService getSocialLogService() {
		return socialLogService;
	}

	public void setSocialLogService(SocialLogService socialLogService) {
		this.socialLogService = socialLogService;
	}
	@Override
	public UserResponse getuserinfo(UserInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getuserinfo").append("_")
		.append(info.getUid());
		String key=sb.toString();
		UserResponse response=(UserResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.getuserinfo(info);
			cacheService.put(key, response);
		}
		return response;
	}

	@Override
	public ItemListResponse attentionusers(AttentionUsersInfo info) {
		return sosHelper.attentionusers(info);
	}

	@Override
	public FollowResponse followartist(FollowArtistInfo info) {
		return sosHelper.followartist(info);
	}
	@Override
	public FollowResponse followuser(FollowUserInfo info) {
		return sosHelper.followuser(info);
	}
	
	@Override
	public UserResponse updateuserinfo(UpdateUserInfo info) {
		return sosHelper.updateuserinfo(info);
	}

	@Override
	public UserResponse updateuserheadicon(UpdateUserHeadIconInfo info) {
		return sosHelper.updateuserheadicon(info);
	}

	@Override
	public ItemListResponse getsnsitemsofartist(SnsItemsOfArtistInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getsnsitemsofartist").append("_")
		.append(info.getPage()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getArtistid());
		String key=sb.toString();
		ItemListResponse response=(ItemListResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.getsnsitemsofartist(info);
			cacheService.put(key, response);
		}
		return response;
	}

	@Override
	public New_ArtistListResponse getuserfollowedartists(
			UserFollowedArtistInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getuserfollowedartists").append("_")
		.append(info.getPage()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getAllcountflag()).append("_");
		if(info.getTargetuid()>0){
			sb.append(info.getTargetuid());
		}else{
			sb.append(info.getUid());
		}
		String key=sb.toString();
		New_ArtistListResponse response=(New_ArtistListResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.getuserfollowedartists(info);
			cacheService.put(key, response);
		}
		if(response.getArtistslist().getArtist()!=null&&response.getArtistslist().getArtist().size()>0){
			List<New_ArtistEntry> artistEntryList=response.getArtistslist().getArtist();
			List<Long> artistIdList=new ArrayList<Long>();
			for(New_ArtistEntry na:artistEntryList){
				artistIdList.add(na.getArtistid());
			}
			ArtistFollowStatusListEntry artistFollowStatusListEntry=sosHelper.getArtistFollowStatusList(artistIdList, info.getUid());
			response.setArtistfollowstatuslist(artistFollowStatusListEntry);
		}
		return response;
	}

	@Override
	public LikeResponse like(LikeInfo info) {
		return sosHelper.like(info);
	}

	@Override
	public ShareResponse share(ShareInfo info) {
		return sosHelper.share(info);
	}

	@Override
	public CommentResponse comment(CommentInfo info) {
		return sosHelper.comment(info);
	}

	@Override
	public ItemListResponse plaza(AttentionUsersInfo info) {
		return sosHelper.plaza(info);
	}

	@Override
	public UserListResponse gettalentusers(UserInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getitemsofuser").append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getAllcountflag()).append("_")
		.append(info.getUserfrom()).append("_")
		.append(info.getUsertype()).append("_")
		.append(info.getPage());
		String key=sb.toString();
		UserListResponse response=(UserListResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.gettalentusers(info);
			cacheService.put(key, response);
		}
		if(response.getUserslist().getUser()!=null&&response.getUserslist().getUser().size()>0){
			List<UserEntry> userEntryList=response.getUserslist().getUser();
			List<Long> followUidList=new ArrayList<Long>();
			for(UserEntry ue:userEntryList){
				followUidList.add(ue.getUid());
			}
			UserFollowStatusListEntry userFollowStatusListEntry=sosHelper.getUserFollowStatusList(followUidList, info.getUid());
			response.setUserfollowstatuslist(userFollowStatusListEntry);
			
		}
		return response;
	}

	@Override
	public ItemListResponse getitemsofuser(AttentionUsersInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getitemsofuser").append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getItemtype()).append("_")
		.append(info.getTargetuid()).append("_")
		.append(info.getPage());
		String key=sb.toString();
		ItemListResponse response=(ItemListResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.getitemsofuser(info);
			cacheService.put(key, response);
		}
		return response;
	}

	@Override
	public UserListResponse getuserfollowedusers(UserInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getuserfollowedusers").append("_")
		.append(info.getAllcountflag()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getPage()).append("_");
		if(info.getTargetuid()>0){
			sb.append(info.getTargetuid());
		}else{
			sb.append(info.getUid());
		}
		String key=sb.toString();
		UserListResponse response=(UserListResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.getuserfollowedusers(info);
			cacheService.put(key, response);
		}
		if(response.getUserslist().getUser()!=null&&response.getUserslist().getUser().size()>0){
			List<UserEntry> userEntryList=response.getUserslist().getUser();
			List<Long> followUidList=new ArrayList<Long>();
			for(UserEntry ue:userEntryList){
				followUidList.add(ue.getUid());
			}
			UserFollowStatusListEntry userFollowStatusListEntry=sosHelper.getUserFollowStatusList(followUidList, info.getUid());
			response.setUserfollowstatuslist(userFollowStatusListEntry);
		}
		return response;
	}

	@Override
	public CommentListResponse getcommentsofitem(GetCommentsOfItemInfo info) {
		return sosHelper.getcommentsofitem(info);
	}
	@Override
	public New_UserSongListResponse syncplaylist(SyncPlaylistInfo info) {
		return sosHelper.syncplaylist(info);
	}
	@Override
	public New_UserSongListResponse getsongsofplaylist(
			GetSongsOfPlaylistInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getsongsofplaylist").append("_")
		.append(info.getSort()).append("_")
		.append(info.getPlaylistid()).append("_")
		.append(info.getAllcountflag()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getPage()).append("_");
		if(info.getTargetuid()>0){
			sb.append(info.getTargetuid());
		}else{
			sb.append(info.getUid());
		}
		String key=sb.toString();
		New_UserSongListResponse response=(New_UserSongListResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.getsongsofplaylist(info);
			cacheService.put(key, response);
		}
		return response;
	}
	
	@Override
	public New_UserSongListResponse getuserrecentlyplayed(
			GetSongsOfPlaylistInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getuserrecentlyplayed").append("_")
		.append(info.getAllcountflag()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getPage()).append("_");
		if(info.getTargetuid()>0){
			sb.append(info.getTargetuid());
		}else{
			sb.append(info.getUid());
		}
		String key=sb.toString();
		New_UserSongListResponse response=(New_UserSongListResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.getuserrecentlyplayed(info);
			cacheService.put(key, response);
		}
		return response;
	}
	
	@Override
	public New_UserSongListResponse getusermostplayed(
			GetSongsOfPlaylistInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getusermostplayed").append("_")
		.append(info.getAllcountflag()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getPage()).append("_");
		if(info.getTargetuid()>0){
			sb.append(info.getTargetuid());
		}else{
			sb.append(info.getUid());
		}
		String key=sb.toString();
		New_UserSongListResponse response=(New_UserSongListResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.getusermostplayed(info);
			cacheService.put(key, response);
		}
		return response;
	}
	public New_UserSongListResponse getuserlikesongs(
			GetSongsOfPlaylistInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getuserlikesongs").append("_")
		.append(info.getAllcountflag()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getPage()).append("_");
		if(info.getTargetuid()>0){
			sb.append(info.getTargetuid());
		}else{
			sb.append(info.getUid());
		}
		String key=sb.toString();
		New_UserSongListResponse response=(New_UserSongListResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.getuserlikesongs(info);
			cacheService.put(key, response);
		}
		return response;
	}
	@Override
	public New_UserMvListResponse getmvsofplaylist(
			GetMvsOfPlaylistInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getmvsofplaylist").append("_")
		.append(info.getSort()).append("_")
		.append(info.getPlaylistid()).append("_")
		.append(info.getAllcountflag()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getPage());
		String key=sb.toString();
		New_UserMvListResponse response=(New_UserMvListResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.getmvsofplaylist(info);
			cacheService.put(key, response);
		}
		return response;
	}

	@Override
	public New_AlbumResponse myattention(MyAttentionInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("myattention").append("_")
		.append(info.getLastplaytime()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getUid()).append("_")
		.append(info.getPage());
		String key=sb.toString();
		New_AlbumResponse response=(New_AlbumResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.myattention(info);
			cacheService.put(key, response);
		}
		return response;
	}

	@Override
	public MyAttentionAlbumResponse myattentionalbum(MyAttentionAlbumInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("myattentionalbum").append("_")
		.append(info.getAlbumid()).append("_")
		.append(info.getTargetuid());
		String key=sb.toString();
		MyAttentionAlbumResponse response=(MyAttentionAlbumResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.myattentionalbum(info);
			cacheService.put(key, response);
		}
		if(response.getAttentionalbum()!=null&&response.getAttentionalbum().getUser()!=null){
			List<Long> followUidList=new ArrayList<Long>();
			long followUid=response.getAttentionalbum().getUser().getUid();
			followUidList.add(followUid);
			UserFollowStatusListEntry userFollowStatusListEntry=sosHelper.getUserFollowStatusList(followUidList,info.getUid());
			response.setUserfollowstatuslist(userFollowStatusListEntry);
		}
		return response;
	}

	@Override
	public UserListResponse getuserfans(GetUserFansInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getuserfans").append("_")
		.append(info.getPage()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getAllcountflag()).append("_");
		if(info.getTargetuid()>0){
			sb.append(info.getTargetuid());
		}else{
			sb.append(info.getUid());
		}
		String key=sb.toString();
		UserListResponse response=(UserListResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.getuserfans(info);
			cacheService.put(key, response);
		}
		if(response.getUserslist().getUser().size()>0){
			List<UserEntry> userEntryList=response.getUserslist().getUser();
			List<Long> followUidList=new ArrayList<Long>();
			for(UserEntry ue:userEntryList){
				followUidList.add(ue.getUid());
			}
			UserFollowStatusListEntry userFollowStatusListEntry=sosHelper.getUserFollowStatusList(followUidList, info.getUid());
			response.setUserfollowstatuslist(userFollowStatusListEntry);
		}
		return response;
	}

	@Override
	public UserResponse gettalentinfo(GetTalentInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("gettalentinfo").append("_")
		.append(info.getTargetuid());
		String key=sb.toString();
		UserResponse response=(UserResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.gettalentinfo(info);
			cacheService.put(key, response);
		}
		if(response.getUser()!=null){
			List<Long> followUidList=new ArrayList<Long>();
			followUidList.add(response.getUser().getUid());
			UserFollowStatusListEntry userFollowStatusListEntry=sosHelper.getUserFollowStatusList(followUidList, info.getUid());
			response.setUserfollowstatuslist(userFollowStatusListEntry);
		}
		return response;
	}

	@Override
	public DiscoveryAlbumOfSongResponse discoveryalbumofsong(
			DiscoveryAlbumOfSongInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("discoveryalbumofsong").append("_")
		.append(info.getAlbumid());
		String key=sb.toString();
		DiscoveryAlbumOfSongResponse response=(DiscoveryAlbumOfSongResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.discoveryalbumofsong(info);
			cacheService.put(key, response);
		}
		if(response.getAlbum()!=null&&response.getAlbum()!=null){
			List<Long> followUidList=new ArrayList<Long>();
			if(response.getAlbum().getListeneduserslist().getUser().size()>0){
				List<UserEntry> userEntryList=response.getAlbum().getListeneduserslist().getUser();
				for(int i=0;i<userEntryList.size();i++){
					long followUid=userEntryList.get(i).getUid();
					followUidList.add(followUid);
				}
			}
			if(response.getAlbum().getLikeduserslist().getUser().size()>0){
				List<UserEntry> userEntryList=response.getAlbum().getLikeduserslist().getUser();
				for(int i=0;i<userEntryList.size();i++){
					long followUid=userEntryList.get(i).getUid();
					followUidList.add(followUid);
				}
			}
			followUidList=ServiceUtils.removeIterativeOnIdList(followUidList);
			UserFollowStatusListEntry userFollowStatusListEntry=sosHelper.getUserFollowStatusList(followUidList, info.getUid());
			response.setUserfollowstatuslist(userFollowStatusListEntry);
		}
		return response;
	}
	
	@Override
	public New_AlbumResponse discoverysongs(MyAttentionInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("discoverysongs").append("_")
		.append(info.getPage()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getLastplaytime());
		String key=sb.toString();
		New_AlbumResponse response=(New_AlbumResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.discoverysongs(info);
			cacheService.put(key, response);
		}
		return response;
	}

	@Override
	public WeiboBondAccountResponse bondweiboaccount(SnsWeiBoInfo info) {
		return sosHelper.bondweiboaccount(info);
	}

	@Override
	public WeiboBondAccountResponse removeweiboaccount(SnsWeiBoInfo info) {
		return sosHelper.removeweiboaccount(info);
	}

	@Override
	public UserPlaylistResponse getplaylistsofuser(GetPlaylistsOfUserInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getplaylistsofuser").append("_")
		.append(info.getTargetuid()).append("_")
		.append(info.getPage()).append("_")
		.append(info.getAllcountflag()).append("_")
		.append(info.getPagecount());
		String key=sb.toString();
		UserPlaylistResponse response=(UserPlaylistResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.getplaylistsofuser(info);
			cacheService.put(key, response);
		}
		return response;
	}

	@Override
	public New_ArtistListResponse getuserartists(GetUserArtistInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getuserartists").append("_")
		.append(info.getAllcountflag()).append("_")
		.append(info.getPage()).append("_")
		.append(info.getPagecount()).append("_");
		if(info.getTargetuid()>0){
			sb.append(info.getTargetuid());
		}else{
			sb.append(info.getUid());
		}
		String key=sb.toString();
		New_ArtistListResponse response=(New_ArtistListResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.getuserartists(info);
			cacheService.put(key, response);
		}
		return response;
	}

	@Override
	public New_ArtistInfoResponse getuseralbumsofartist(
			GetUserAlbumsOfArtistInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getuseralbumsofartist").append("_")
		.append(info.getListenedflag()).append("_")
		.append(info.getArtistid()).append("_")
		.append(info.getPage()).append("_")
		.append(info.getPagecount()).append("_");
		if(info.getTargetuid()>0){
			sb.append(info.getTargetuid());
		}else{
			sb.append(info.getUid());
		}
		String key=sb.toString();
		New_ArtistInfoResponse response=(New_ArtistInfoResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.getuseralbumsofartist(info);
			cacheService.put(key, response);
		}
		return response;
	}

	@Override
	public New_AlbumResponse getuseralbums(GetUserAlbumsInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getuseralbums").append("_")
		.append(info.getAllcountflag()).append("_")
		.append(info.getPage()).append("_")
		.append(info.getPagecount()).append("_");
		if(info.getTargetuid()>0){
			sb.append(info.getTargetuid());
		}else{
			sb.append(info.getUid());
		}
		String key=sb.toString();
		New_AlbumResponse response=(New_AlbumResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.getuseralbums(info);
			cacheService.put(key, response);
		}
		return response;
	}

	@Override
	public UserResponse syncuser(PageInfo info) {
		return sosHelper.syncuser(info);
	}

	@Override
	public New_AlbumInfoResponse getusersongsofalbum(GetUserSongsOfAlbum info) {
		StringBuffer sb=new StringBuffer();
		sb.append("getusersongsofalbum").append("_")
		.append(info.getAlbumid()).append("_")
		.append(info.getTargetuid());
		String key=sb.toString();
		New_AlbumInfoResponse response=(New_AlbumInfoResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.getusersongsofalbum(info);
			cacheService.put(key, response);
		}
		return response;
	}


	@Override
	public LastSyncPlaylistTimeResponse getlastsyncplaylisttime(
			GetLastSyncPlaylistTimeInfo info) {
		return sosHelper.getlastsyncplaylisttime(info);
	}

	@Override
	public DeletePlaylistSongResponse deleteplaylistsongs(
			DeletePlaylistSongInfo info) {
		return sosHelper.deleteplaylistsongs(info);
	}

	@Override
	public UserListResponse recommendusers(RecommendUserInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("recommendusers").append("_")
		.append(info.getToken()).append("_")
		.append(info.getUid()).append("_")
		.append(info.getPage()).append("_")
		.append(info.getPagecount()).append("_");
		String key=sb.toString();
		UserListResponse response=(UserListResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.recommendusers(info);
			cacheService.put(key, response);
		}
		return response;
	}
	
	@Override
	public UserListResponse searchusers(SearchUserInfo info) {
		StringBuffer sb=new StringBuffer();
		sb.append("searchusers").append("_")
		.append(info.getNickname()).append("_")
		.append(info.getHeadiconflag()).append("_")
		.append(info.getPage()).append("_")
		.append(info.getPagecount()).append("_")
		.append(info.getKeyname());
		String key=sb.toString();
		UserListResponse response=(UserListResponse)cacheService.get(key);
		if(response==null){
			response=sosHelper.searchusers(info);
			cacheService.put(key, response);
		}
		if(response.getUserslist().getUser()!=null&&response.getUserslist().getUser().size()>0){
			List<UserEntry> userEntryList=response.getUserslist().getUser();
			List<Long> followUidList=new ArrayList<Long>();
			for(UserEntry ue:userEntryList){
				followUidList.add(ue.getUid());
			}
			UserFollowStatusListEntry userFollowStatusListEntry=sosHelper.getUserFollowStatusList(followUidList, info.getUid());
			response.setUserfollowstatuslist(userFollowStatusListEntry);
			
		}
		return response;
	}

	@Override
	public UserListResponse listusers(ListUserInfo info) {
		return sosHelper.listusers(info);
	}
	
	@Override
	public UserSnsResponse snsgetuserrelationlist(SnsGetUserRelationListInfo info){
		return sosHelper.snsgetuserrelationlist(info);
	}

	@Override
	public UserSnsResponse snsadditem(SnsAddItemInfo info) {
		return sosHelper.snsadditem(info);
	}

	@Override
	public UserSnsResponse snsinvitefriend(SnsInviteFriendInfo info) {
		return sosHelper.snsinvitefriend(info);
	}

	@Override
	public LeaveMessageResponse leavemessage(LeaveMessageInfo info) {
		return sosHelper.leavemessage(info);
	}
	
	@Override
	public LeaveMessageResponse getleavemessages(LeaveMessageInfo info) {
		return sosHelper.getleavemessages(info);
	}

	@Override
	public UserSnsShareResponse snsshare(UserSnsShareInfo info) {
		return sosHelper.snsshare(info);
	}

	@Override
	public UserSnsShareResponse getsharesnsitem(GetSnsShareInfo info) {
		return sosHelper.getsharesnsitem(info);
	}
}
