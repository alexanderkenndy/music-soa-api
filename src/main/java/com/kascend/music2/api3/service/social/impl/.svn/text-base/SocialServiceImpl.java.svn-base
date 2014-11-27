package com.kascend.music2.api3.service.social.impl;

import java.util.ArrayList;
import java.util.List;

import com.kascend.music2.api3.service.log.SocialLogService;
import com.kascend.music2.api3.service.metadata.info.GetMvsOfPlaylistInfo;
import com.kascend.music2.api3.service.metadata.info.GetSongsOfPlaylistInfo;
import com.kascend.music2.api3.service.metadata.info.PageInfo;
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

public class SocialServiceImpl implements SocialService {
	
	protected SocialServiceHelper sosHelper;
	protected SocialLogService socialLogService;

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
		return sosHelper.getuserinfo(info);
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
		return sosHelper.getsnsitemsofartist(info);
	}

	@Override
	public New_ArtistListResponse getuserfollowedartists(
			UserFollowedArtistInfo info) {
		New_ArtistListResponse response= sosHelper.getuserfollowedartists(info);
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
		UserListResponse response=sosHelper.gettalentusers(info);
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
	public UserListResponse getuserfollowedusers(UserInfo info) {
		UserListResponse response= sosHelper.getuserfollowedusers(info);
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
		return sosHelper.getitemsofuser(info);
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
		return sosHelper.getsongsofplaylist(info);
	}

	@Override
	public New_UserMvListResponse getmvsofplaylist(
			GetMvsOfPlaylistInfo info) {
		return sosHelper.getmvsofplaylist(info);
	}

	@Override
	public New_AlbumResponse myattention(MyAttentionInfo info) {
		return sosHelper.myattention(info);
	}

	@Override
	public MyAttentionAlbumResponse myattentionalbum(MyAttentionAlbumInfo info) {
		MyAttentionAlbumResponse response= sosHelper.myattentionalbum(info);
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
		UserListResponse response= sosHelper.getuserfans(info);
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
		UserResponse response=sosHelper.gettalentinfo(info);
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
		DiscoveryAlbumOfSongResponse response=sosHelper.discoveryalbumofsong(info);
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
		return sosHelper.discoverysongs(info);
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
	public New_UserSongListResponse getuserrecentlyplayed(
			GetSongsOfPlaylistInfo info) {
		return sosHelper.getuserrecentlyplayed(info);
	}

	@Override
	public New_UserSongListResponse getusermostplayed(
			GetSongsOfPlaylistInfo info) {
		return sosHelper.getusermostplayed(info);
	}
	@Override
	public New_UserSongListResponse getuserlikesongs(
			GetSongsOfPlaylistInfo info) {
		return sosHelper.getuserlikesongs(info);
	}

	@Override
	public UserPlaylistResponse getplaylistsofuser(GetPlaylistsOfUserInfo info) {
		return sosHelper.getplaylistsofuser(info);
	}

	@Override
	public New_ArtistListResponse getuserartists(GetUserArtistInfo info) {
		return sosHelper.getuserartists(info);
	}

	@Override
	public New_ArtistInfoResponse getuseralbumsofartist(
			GetUserAlbumsOfArtistInfo info) {
		return sosHelper.getuseralbumsofartist(info);
	}

	@Override
	public New_AlbumResponse getuseralbums(GetUserAlbumsInfo info) {
		return sosHelper.getuseralbums(info);
	}

	@Override
	public UserResponse syncuser(PageInfo info) {
		return sosHelper.syncuser(info);
	}

	@Override
	public New_AlbumInfoResponse getusersongsofalbum(GetUserSongsOfAlbum info) {
		return sosHelper.getusersongsofalbum(info);
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
		return sosHelper.recommendusers(info);
	}
	@Override
	public UserListResponse searchusers(SearchUserInfo info) {
		UserListResponse response= sosHelper.searchusers(info);
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
	public UserSnsResponse snsgetuserrelationlist(
			SnsGetUserRelationListInfo info) {
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
