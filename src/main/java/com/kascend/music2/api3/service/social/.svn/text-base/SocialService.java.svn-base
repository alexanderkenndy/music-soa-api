package com.kascend.music2.api3.service.social;

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

public interface SocialService {
	
	
	public LikeResponse  like(LikeInfo info);
	
	public ShareResponse share(ShareInfo info);
	
	public CommentResponse comment(CommentInfo info);
	
	public UserResponse getuserinfo(UserInfo info);
	
	public UserResponse updateuserinfo(UpdateUserInfo info);
	
	public UserResponse updateuserheadicon(UpdateUserHeadIconInfo info);
	
	public ItemListResponse attentionusers(AttentionUsersInfo info);
	
	public ItemListResponse plaza(AttentionUsersInfo info);
	
	public FollowResponse followartist(FollowArtistInfo info);
	
	public FollowResponse followuser(FollowUserInfo info);
	
	public New_ArtistListResponse getuserfollowedartists(UserFollowedArtistInfo info);
	
	public UserListResponse getuserfollowedusers(UserInfo info);
	
	public ItemListResponse getsnsitemsofartist(SnsItemsOfArtistInfo info);
	
	
	public UserListResponse gettalentusers(UserInfo info);
	
	public ItemListResponse getitemsofuser(AttentionUsersInfo info);
	
	public CommentListResponse getcommentsofitem(GetCommentsOfItemInfo info);
	
	public New_UserSongListResponse syncplaylist(SyncPlaylistInfo info);
	
	public New_UserSongListResponse getsongsofplaylist(GetSongsOfPlaylistInfo info);
	
	public New_UserMvListResponse getmvsofplaylist(GetMvsOfPlaylistInfo info);
	
	public New_AlbumResponse myattention(MyAttentionInfo info);
	
	public MyAttentionAlbumResponse myattentionalbum(MyAttentionAlbumInfo info);
	
	public UserListResponse getuserfans(GetUserFansInfo info);
	
	public UserResponse gettalentinfo(GetTalentInfo info);
	
	public DiscoveryAlbumOfSongResponse discoveryalbumofsong(DiscoveryAlbumOfSongInfo info);
	
	public New_AlbumResponse discoverysongs(MyAttentionInfo info);
	
	public WeiboBondAccountResponse bondweiboaccount(SnsWeiBoInfo info);
	
	public WeiboBondAccountResponse removeweiboaccount(SnsWeiBoInfo info);
	
	public New_UserSongListResponse getuserrecentlyplayed(GetSongsOfPlaylistInfo info);
	
	public New_UserSongListResponse getusermostplayed(GetSongsOfPlaylistInfo info);
	
	public New_UserSongListResponse getuserlikesongs(GetSongsOfPlaylistInfo info);
	
	public UserPlaylistResponse  getplaylistsofuser(GetPlaylistsOfUserInfo info);
	
	public New_ArtistListResponse getuserartists(GetUserArtistInfo info);
	
	public New_ArtistInfoResponse getuseralbumsofartist(GetUserAlbumsOfArtistInfo info);
	
	public New_AlbumResponse getuseralbums(GetUserAlbumsInfo info);
	
	public New_AlbumInfoResponse getusersongsofalbum(GetUserSongsOfAlbum info);
	
	public UserResponse syncuser(PageInfo info);
	
	public LastSyncPlaylistTimeResponse getlastsyncplaylisttime(GetLastSyncPlaylistTimeInfo info);
	
	public DeletePlaylistSongResponse deleteplaylistsongs(DeletePlaylistSongInfo info);
	
	public UserListResponse recommendusers(RecommendUserInfo info);
	
	public UserListResponse searchusers(SearchUserInfo info);
	
	public UserListResponse listusers(ListUserInfo info);
	
	
	public UserSnsResponse snsgetuserrelationlist(SnsGetUserRelationListInfo info);
	
	public UserSnsResponse snsadditem(SnsAddItemInfo info);
	
	public UserSnsResponse snsinvitefriend(SnsInviteFriendInfo info);
	
	public LeaveMessageResponse leavemessage(LeaveMessageInfo info);
	
	public LeaveMessageResponse getleavemessages(LeaveMessageInfo info);
	
	public UserSnsShareResponse snsshare(UserSnsShareInfo info);
	
	public UserSnsShareResponse getsharesnsitem(GetSnsShareInfo info);
}
