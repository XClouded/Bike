package com.yhj.app.bike.api;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.google.gson.Gson;
import com.yhj.app.bike.model.pojo.App;
import com.yhj.app.bike.model.pojo.BetaApkUser;
import com.yhj.app.bike.model.pojo.Channel;
import com.yhj.app.bike.model.pojo.ChannelList;
import com.yhj.app.bike.model.pojo.CheckSubscribe;
import com.yhj.app.bike.model.pojo.Comment;
import com.yhj.app.bike.model.pojo.CommentCount;
import com.yhj.app.bike.model.pojo.CommentCountItem;
import com.yhj.app.bike.model.pojo.CommentList;
import com.yhj.app.bike.model.pojo.ExtendedChannels;
import com.yhj.app.bike.model.pojo.FavorItemsByLoadMore;
import com.yhj.app.bike.model.pojo.FavorItemsByRefresh;
import com.yhj.app.bike.model.pojo.FullNewsDetail;
import com.yhj.app.bike.model.pojo.HotAppList;
import com.yhj.app.bike.model.pojo.HotAppListItem;
import com.yhj.app.bike.model.pojo.HotChannel;
import com.yhj.app.bike.model.pojo.ImgTxtLive;
import com.yhj.app.bike.model.pojo.Item;
import com.yhj.app.bike.model.pojo.ItemsByLoadMore;
import com.yhj.app.bike.model.pojo.ItemsByRefresh;
import com.yhj.app.bike.model.pojo.LiveStatus;
import com.yhj.app.bike.model.pojo.LocalChannel;
import com.yhj.app.bike.model.pojo.Msg;
import com.yhj.app.bike.model.pojo.MsgRet;
import com.yhj.app.bike.model.pojo.NewsMsgGroup;
import com.yhj.app.bike.model.pojo.NewsMsgList;
import com.yhj.app.bike.model.pojo.NewsVersion;
import com.yhj.app.bike.model.pojo.Offline;
import com.yhj.app.bike.model.pojo.OfflineChannel;
import com.yhj.app.bike.model.pojo.OfflineItems;
import com.yhj.app.bike.model.pojo.Option;
import com.yhj.app.bike.model.pojo.Project;
import com.yhj.app.bike.model.pojo.PushConfig;
import com.yhj.app.bike.model.pojo.PushConn;
import com.yhj.app.bike.model.pojo.RemoteConfig;
import com.yhj.app.bike.model.pojo.RssCatList;
import com.yhj.app.bike.model.pojo.RssChannelList;
import com.yhj.app.bike.model.pojo.RssFirstSubscriptionList;
import com.yhj.app.bike.model.pojo.RssItemsByLoadMore;
import com.yhj.app.bike.model.pojo.RssItemsByRefresh;
import com.yhj.app.bike.model.pojo.RssSubItem;
import com.yhj.app.bike.model.pojo.ShareQQWeiboResponse;
import com.yhj.app.bike.model.pojo.ShareQzoneResponse;
import com.yhj.app.bike.model.pojo.ShareWeChatFriendsResponse;
import com.yhj.app.bike.model.pojo.SimpleNewsDetail;
import com.yhj.app.bike.model.pojo.SimpleRet;
import com.yhj.app.bike.model.pojo.SinaResponse;
import com.yhj.app.bike.model.pojo.SpecialReport;
import com.yhj.app.bike.model.pojo.SplashData;
import com.yhj.app.bike.model.pojo.SubProject;
import com.yhj.app.bike.model.pojo.SyncFavorResult;
import com.yhj.app.bike.model.pojo.UserInfoFromServerJsonFormat;
import com.yhj.app.bike.model.pojo.UserInfoFromServerXMLFormat;
import com.yhj.app.bike.model.pojo.Video;
import com.yhj.app.bike.model.pojo.VoteInfo;
import com.yhj.app.bike.model.pojo.WriteBackState;
import com.yhj.app.bike.shareprefrence.SpUpComment;
import com.yhj.app.bike.utils.SLog;

public class JsonParse {

	public static ItemsByRefresh parseItemsByRefresh(String json) throws Exception {
		return new Gson().fromJson(json, ItemsByRefresh.class);
	}

	public static ItemsByLoadMore parseItemsByLoadMore(String json) throws Exception {
		return new Gson().fromJson(json, ItemsByLoadMore.class);
	}

    public static RssItemsByRefresh parseRssItemsByRefresh(String json) throws Exception {
        return new Gson().fromJson(json, RssItemsByRefresh.class);
    }

    public static RssItemsByLoadMore parseRssItemsByLoadMore(String json) throws Exception {
        return new Gson().fromJson(json, RssItemsByLoadMore.class);
    }
    
    public static FavorItemsByRefresh parseFavorItemsByRefresh(String json) throws Exception {
        return new Gson().fromJson(json, FavorItemsByRefresh.class);
    }

    public static FavorItemsByLoadMore parseFavorItemsByLoadMore(String json) throws Exception {
        return new Gson().fromJson(json, FavorItemsByLoadMore.class);
    }
	    
	public static SpecialReport parseSpecialNewsList(String json) throws Exception {
		return new Gson().fromJson(json, SpecialReport.class);
	}

	public static ImgTxtLive parseImgTxtLive(String json) throws Exception {
		return new Gson().fromJson(json, ImgTxtLive.class);
	}

	// push消息数据解析
	public static PushConfig parsePushConfig(String json) throws Exception {
		return new Gson().fromJson(json, PushConfig.class);
	}

	// push轮询数据解析
	public static PushConn parsePushConnect(String json) throws Exception {
		PushConn push = new PushConn();
		JSONObject ret = new JSONObject(json);
		push.setCode(JSON.getRawString("code", ret));
		push.setFlag(JSON.getRawString("flag", ret));
		push.setSeq(JSON.getRawString("seq", ret));
		String str = ret.getString("msg");
		if (str != null && str.length() > 0) {
			JSONObject jo = new JSONObject(str);
			Msg msg = new Msg();
			msg.setChlid(JSON.getRawString("s", jo));
			msg.setMsg(JSON.getRawString("a", jo));
			msg.setNewsId(JSON.getRawString("i", jo));
			push.setMsg(msg);
		}
		return push;
	}

	// splash数据解析
	public static SplashData parseSplashData(String json) throws Exception {
		return new Gson().fromJson(json, SplashData.class);
	}

	public static WriteBackState parsePublishQQNewsMulti(String json) throws Exception {
		return new Gson().fromJson(json, WriteBackState.class);
	}

	/**
	 * parse comment array
	 * 
	 * @param json
	 * @return
	 * @author haiyandu
	 * @since 2012-6-14下午04:31:49
	 */
	private static List<Comment[]> parseCommentArray(String json) throws Exception {
		ArrayList<Comment[]> list = new ArrayList<Comment[]>();
		Comment comment = null;
		JSONArray ret = new JSONArray(json);
		Gson gson = new Gson();
		for (int i = 0; i < ret.length(); i++) {
			JSONArray jo = ret.getJSONArray(i);
			int size = jo.length();
			Comment[] commentgroup = new Comment[size];
			for (int j = 0; j < size; j++) {
				JSONObject object = jo.getJSONObject(j);
				comment = gson.fromJson(object.toString(), Comment.class);
				boolean isup = SpUpComment.getUpComment(comment.getReplyId());
				if (isup) {
					comment.setHadUp(isup);
					comment.setAgreeCount("" + (Integer.parseInt(comment.getAgreeCount()) + 1));
				}
				commentgroup[j] = comment;
			}
			list.add(commentgroup);
		}
		return list;
	}

	/**
	 * parse CommentList
	 * 
	 * @param json
	 * @return
	 * @author haiyandu
	 * @since 2012-6-18下午04:31:49
	 */
	public static CommentList parseNewsCommentList(String json) throws Exception {
		JSONObject ret = new JSONObject(json);
		CommentList list = new CommentList();
		if (ret.has("ret")) {
			list.setRet(ret.getString("ret"));
		}
		
		if(ret.has("bnext")){
			list.setNext(ret.getString("bnext"));
		}
		
		if (ret.has("comments")) {
			JSONObject js = ret.getJSONObject("comments");
			if (js.has("hot") && !js.isNull("hot")) {
				List<Comment[]> hotList = parseCommentArray(js.getString("hot"));
				list.setHotList(hotList);
			}
			if (js.has("new") && !js.isNull("new")) {
				List<Comment[]> newList = parseCommentArray(js.getString("new"));
				list.setNewList(newList);
			}
		}
		return list;
	}

	/**
	 * parse SubChannels
	 * 
	 * @param json
	 * @return
	 * @author haiyandu
	 * @since 2012-6-18下午04:31:49
	 */
	public static ChannelList parseChannelList(String json) throws Exception {
		JSONObject ret = new JSONObject(json);
		ChannelList channels = new ChannelList();
		List<Channel> channelList = null;
		List<LocalChannel> localList = null;
		Gson gson = new Gson();
		channels.setRet(JSON.getRawString("ret", ret));
		channels.setVersion(JSON.getRawString("version", ret));
		if (ret.has("channellist") && !ret.isNull("channellist")) {
			channelList = new ArrayList<Channel>();
			JSONArray jsonArray = ret.getJSONArray("channellist");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				Channel channel = gson.fromJson(object.toString(), Channel.class);
				channelList.add(channel);
			}
			channels.setChannelList(channelList);
		}
		if (ret.has("local_chllist") && !ret.isNull("local_chllist")) {
			localList = new ArrayList<LocalChannel>();
			JSONArray jsonArray = ret.getJSONArray("local_chllist");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				LocalChannel local = gson.fromJson(object.toString(), LocalChannel.class);
				localList.add(local);
			}
			channels.setLocalChannels(localList);
		}
		return channels;
	}

	/**
	 * parse QQNewsRemoteConfig
	 * 
	 * @param json
	 * @return
	 * @author haiyandu
	 * @since 2012-6-18下午04:31:49
	 */
	public static RemoteConfig parseNewsRemoteConfig(String json) throws Exception {
		JSONObject ret = new JSONObject(json);
		Gson gson = new Gson();
		RemoteConfig config = new RemoteConfig();
		List<Offline> OfflineList = new ArrayList<Offline>();
		List<HotChannel> HotChannelList = new ArrayList<HotChannel>();
		JSONArray jsonArray;
		if (ret.has("ret")) {
			config.setRet(ret.getString("ret"));
		}
		if (ret.has("version")) {
			config.setVersion(ret.getString("version"));
		}
		JSONObject js;
		if (ret.has("Boutique")) {
			js = ret.getJSONObject("Boutique");
			config.setBoutiqueVersion(js.getString("BoutiqueVersion"));
		}
		if (ret.has("SubMenu")) {
			js = ret.getJSONObject("SubMenu");
			config.setSubMenuVersion(js.getString("SubMenuVersion"));
			config.setSubMenuExtendVersion(js.getString("SubMenuExtendVersion"));
		}
		if (ret.has("Splash")) {
			js = ret.getJSONObject("Splash");
			config.setSplashVersion(js.getString("SplashVersion"));
		}
		if (ret.has("QQAD")) {
			js = ret.getJSONObject("QQAD");
			config.setQQADVersion(js.getString("QQADVersion"));
		}
		if (ret.has("qqnewsversion")) {
			js = ret.getJSONObject("qqnewsversion");
			NewsVersion newsVersion = gson.fromJson(js.toString(), NewsVersion.class);
			config.setNewsVersion(newsVersion);
		}
		if (ret.has("needAudioInM3u8")) {
			config.setNeedAudioInM3u8(ret.getString("needAudioInM3u8"));
		}
		if (ret.has("ListMode")) {
			config.setListMode(ret.getString("ListMode"));
		}
		if (ret.has("AppMode")) {
			config.setAppMode(ret.getString("AppMode"));
		}
		if (ret.has("AppModeEx")) {
			config.setAppModeEx(ret.getString("AppModeEx"));
		}
		if (ret.has("cleanid")) {
			config.setCleanId(ret.getString("cleanid"));
		}
		if (ret.has("offline")) {
			jsonArray = ret.getJSONArray("offline");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jo = jsonArray.getJSONObject(i);
				Offline offline = gson.fromJson(jo.toString(), Offline.class);
				OfflineList.add(offline);
			}
			config.setOfflineList(OfflineList);
		}
		if (ret.has("hotchannels")) {
			jsonArray = ret.getJSONArray("hotchannels");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jo = jsonArray.getJSONObject(i);
				HotChannel channel = gson.fromJson(jo.toString(), HotChannel.class);
				HotChannelList.add(channel);
			}
			config.setHotChannel(HotChannelList);
		}
		return config;
	}

	/**
	 * parse NewsVersionInfo
	 * 
	 * @param json
	 * @return
	 * @author haiyandu
	 * @since 2012-6-18下午04:31:49
	 */
	public static NewsVersion parseNewsVersionInfo(String json) throws Exception {

		JSONObject ret = new JSONObject(json);
		Gson gson = new Gson();
		NewsVersion newsVersion = new NewsVersion();
		if (ret.has("qqnewsversion")) {
			newsVersion = gson.fromJson(ret.getJSONObject("qqnewsversion").toString(), NewsVersion.class);
		}
		return newsVersion;

	}

//	/**
//	 * parse AppListQQNews
//	 * 
//	 * @param xml
//	 * @return
//	 * @author haiyandu
//	 * @since 2012-7-10下午04:31:49
//	 */
//	public static AppList parseAppListQQNews(String xml) throws Exception {
//		AppList list = new AppList();
//		List<App> mAppList = null;
//		App mApp = null;
//		XmlPullParser parser = Xml.newPullParser();
//		ByteArrayInputStream is = new ByteArrayInputStream(xml.getBytes());
//		parser.setInput(is, "UTF-8");
//		int event = parser.getEventType();
//		while (event != XmlPullParser.END_DOCUMENT) {
//			switch (event) {
//			case XmlPullParser.START_DOCUMENT:
//				mAppList = new ArrayList<App>();
//				break;
//			case XmlPullParser.START_TAG:
//				String name = parser.getName();
//				if (name.equals("app")) {
//					mApp = new App();
//				} else if (name.equals("applist")) {
//					String value = parser.getAttributeValue(0);
//					list.setVersion(value);
//				} else if (name.equals("ico3")) {
//					String value = parser.nextText();
//					mApp.setIco3(value);
//				} else if (name.equals("ico4")) {
//					String value = parser.nextText();
//					mApp.setIco4(value);
//				} else if (name.equals("url")) {
//					String value = parser.nextText();
//					mApp.setUrl(value);
//				} else if (name.equals("txt")) {
//					String value = parser.nextText();
//					mApp.setTxt(value);
//				}
//				break;
//			case XmlPullParser.END_TAG:
//				if (parser.getName().equals("app")) {
//					mAppList.add(mApp);
//					mApp = null;
//				}
//				break;
//			}
//			event = parser.next();
//		}
//		list.setAppList(mAppList);
//		return list;
//	}

	public static UserInfoFromServerJsonFormat parseUserInfoFromServerJson(String json) throws Exception {
		return new Gson().fromJson(json, UserInfoFromServerJsonFormat.class);
	}

	public static UserInfoFromServerXMLFormat parseUserInfoFromServerXML(String xml) throws Exception {
		UserInfoFromServerXMLFormat mUserInfoFromServerXMLFormat = null;
		XmlPullParser parser = Xml.newPullParser();
		ByteArrayInputStream is = new ByteArrayInputStream(xml.getBytes());
		parser.setInput(is, "UTF-8");
		int event = parser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				mUserInfoFromServerXMLFormat = new UserInfoFromServerXMLFormat();
				break;
			case XmlPullParser.START_TAG:
				String name = parser.getName();
				if (name.equals("retcode")) {
					mUserInfoFromServerXMLFormat.setRetcode(parser.nextText());
				} else if (name.equals("name")) {
					mUserInfoFromServerXMLFormat.setName(parser.nextText());
				} else if (name.equals("nick")) {
					mUserInfoFromServerXMLFormat.setNick(parser.nextText());
				} else if (name.equals("headurl")) {
					mUserInfoFromServerXMLFormat.setHeadurl(parser.nextText());
				} else if (name.equals("msgTotal")) {
					mUserInfoFromServerXMLFormat.setMsgToal(parser.nextText());
				} else if (name.equals("following")) {
					mUserInfoFromServerXMLFormat.setFollowing(parser.nextText());
				} else if (name.equals("follower")) {
					mUserInfoFromServerXMLFormat.setFollowing(parser.nextText());
				} else if (name.equals("qqnick")) {
					mUserInfoFromServerXMLFormat.setFollowing(parser.nextText());
				} else if (name.equals("QZoneStat")) {
					mUserInfoFromServerXMLFormat.setOpenQZone("1".equals(parser.nextText()) ? true : false);
				} else if (name.equals("MBStat")) {
					mUserInfoFromServerXMLFormat.setOpenMBlog("1".equals(parser.nextText()) ? true : false);
				} else if (name.equals("WeiXinStat")) {
					mUserInfoFromServerXMLFormat.setOpenWeiXin("1".equals(parser.nextText()) ? true : false);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			event = parser.next();
		}
		return mUserInfoFromServerXMLFormat;
	}

	public static Video parseVideoUrl(String xml) throws Exception {
		Video video = null;
		XmlPullParser parser = Xml.newPullParser();
		ByteArrayInputStream is = new ByteArrayInputStream(xml.getBytes());
		parser.setInput(is, "UTF-8");
		int event = parser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				video = new Video();
				break;
			case XmlPullParser.START_TAG:
				String name = parser.getName();
				if (name.equals("url")) {
					video.setUrl(parser.nextText());
				} else if (name.equals("msg")) {
					video.setMsg(parser.nextText());
				} else if (name.equals("em")) {
					video.setCode(parser.nextText());
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			event = parser.next();
		}
		return video;
	}

	public static LiveStatus parseVideoLiveStatus(String json) throws Exception {
		JSONObject ret = new JSONObject(json);
		LiveStatus status = null;
		if (ret.has("retcode")) {
			String code = ret.getString("retcode");
			if (code.equals("0")) {
				status = new Gson().fromJson(json, LiveStatus.class);
			} else {
				status = new LiveStatus();
				status.setRetCode(code);
				status.setError(ret.getString("info"));
			}
		}
		return status;
	}

	public static ExtendedChannels parseExtendedChannels(String json) throws Exception {
		return new Gson().fromJson(json, ExtendedChannels.class);
	}

	public static VoteInfo parseVoteInfo(String json) throws Exception {
		JSONObject jsObject = new JSONObject(json);
		VoteInfo voteInfo = new Gson().fromJson(jsObject.toString(), VoteInfo.class);

		if (jsObject.has("INFO")) {

			JSONObject jo = jsObject.getJSONObject("INFO");
			if (jo.has("PROJECT")) {
				// 解析project
				voteInfo.getInfo().setProject(parseProjectArray(jo.getString("PROJECT")));
			}
		}

		return voteInfo;
	}

	/**
	 * 解析project数组
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	private static List<Project> parseProjectArray(String json) throws Exception {
		// 解析project
		JSONArray jsArray = new JSONArray(json);
		int len = jsArray.length();
		List<Project> lstProject = new ArrayList<Project>(len);

		for (int i = 0; i < len; ++i) {
			JSONObject jo = jsArray.getJSONObject(i);
			Project project = new Gson().fromJson(jo.toString(), Project.class);
			if (jo.has("SUBPROJ")) {
				// 解析SUBPROJECT
				project.setSubProjectList(parseSubProjectArray(jo.getString("SUBPROJ")));
			}
			lstProject.add(project);

		}

		return lstProject;
	}

	/**
	 * 解析sub project数组
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	private static List<SubProject> parseSubProjectArray(String json) throws Exception {
		// 解析subproject
		JSONArray jsArray = new JSONArray(json);
		int len = jsArray.length();
		List<SubProject> lstSubProject = new ArrayList<SubProject>(len);

		for (int i = 0; i < len; ++i) {
			JSONObject jo = jsArray.getJSONObject(i);
			SubProject subProject = new Gson().fromJson(jo.toString(), SubProject.class);
			subProject.setOptionList(parseOptionArray(jo));
			lstSubProject.add(subProject);
		}

		return lstSubProject;
	}

	/**
	 * 根据optionXX前缀匹配解析option的2维数组
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	private static List<List<Option>> parseOptionArray(JSONObject jo) throws Exception {

		JSONArray jsArray = jo.names();
		List<List<Option>> lstListOption = new ArrayList<List<Option>>();

		for (int i = 0; i < jsArray.length(); ++i) {
			String keyStr = jsArray.getString(i);
			if (keyStr.length() < 6)
				continue;
			if (keyStr.substring(0, 6).equals("OPTION")) // 匹配OPTION
			{
				JSONArray jr = jo.getJSONArray(keyStr);
				List<Option> lstOption = new ArrayList<Option>(jr.length());

				for (int j = 0; j < jr.length(); ++j) {
					JSONObject jObject = jr.getJSONObject(j);
					Option option = new Gson().fromJson(jObject.toString(), Option.class);
					lstOption.add(option);
				} // for j

				lstListOption.add(lstOption);
			} // for if

		}
		return lstListOption;
	}

	public static SinaResponse parseSinaWeiboReponse(String json) throws Exception {
		SinaResponse response = new SinaResponse();
		JSONObject ret = new JSONObject(json);
		response.setPublish(JSON.getRawString("publish", ret));
		if (ret.has("sina")) {
			JSONObject js = ret.getJSONObject("sina");
			response.setRet(JSON.getRawString("ret", js));
			response.setRetmsg(JSON.getRawString("retmsg", js));
			JSONObject data = js.getJSONObject("data");
			response.setAccess_token(JSON.getRawString("access_token", data));
			response.setRefresh_token(JSON.getRawString("refresh_token", data));
		}
		return response;
	}

	public static ShareQQWeiboResponse parseQQWeiboReponse(String json) throws Exception {
		ShareQQWeiboResponse response = new ShareQQWeiboResponse();
		JSONObject ret = new JSONObject(json);
		response.setPublish(JSON.getRawString("publish", ret));
		if (ret.has("qqweibo")) {
			JSONObject js = ret.getJSONObject("qqweibo");
			response.setRet(JSON.getRawString("ret", js));
			response.setRetmsg(JSON.getRawString("retmsg", js));
			JSONObject data = js.getJSONObject("data");
			response.setId(JSON.getRawString("id", data));
			response.setTime(JSON.getRawString("time", data));
		}
		return response;
	}

	public static ShareQzoneResponse parseQzoneReponse(String json) throws Exception {
		ShareQzoneResponse response = new ShareQzoneResponse();
		JSONObject ret = new JSONObject(json);
		response.setPublish(JSON.getRawString("publish", ret));
		if (ret.has("qzone")) {
			JSONObject js = ret.getJSONObject("qzone");
			response.setRet(JSON.getRawString("ret", js));
			response.setRetmsg(JSON.getRawString("retmsg", js));
		}
		return response;
	}

	public static ShareWeChatFriendsResponse parseWechatFriendReponse(String json) throws Exception {
		ShareWeChatFriendsResponse response = new ShareWeChatFriendsResponse();
		JSONObject ret = new JSONObject(json);
		response.setPublish(JSON.getRawString("publish", ret));
		if (ret.has("friends")) {
			JSONObject js = ret.getJSONObject("friends");
			response.setRet(JSON.getRawString("ret", js));
			response.setRetmsg(JSON.getRawString("retmsg", js));
		}
		return response;
	}

	/**
	 * push时获取数据接口
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static FullNewsDetail parseFullHtmlContent(String json) throws Exception {
		FullNewsDetail detail = new FullNewsDetail();
		JSONObject ret = new JSONObject(json);
		Gson gson = new Gson();

		if (ret.has("newslist")) {
			detail.setmItem(gson.fromJson(ret.getString("newslist"), Item.class));
		}

		SimpleNewsDetail newsetail = new Gson().fromJson(json, SimpleNewsDetail.class);
		if (ret.has("content")) {
			String text = null;
			text = JSON.getRawString("text", ret.getJSONObject("content"));
			newsetail.setText(text);
		}

		if (ret.has("attribute") && !ret.getString("attribute").equals("[]")) {
			newsetail.setAttribute(ret.getJSONObject("attribute"));
		}

		if (ret.has("relate_news")) {
			List<Item> lItem = parseListItem(ret.getJSONArray("relate_news"));
			newsetail.setRelate_news(lItem);
		}

		detail.setmDetail(newsetail);
		return detail;
	}

	/**
	 * 解析相关新闻List
	 * 
	 * @return
	 * @throws Exception
	 */
	private static List<Item> parseListItem(JSONArray listData) throws Exception {
		int len = listData.length();
		List<Item> list = new ArrayList<Item>(len);
		for (int i = 0; i < len; i++) {
			JSONObject jo = listData.getJSONObject(i);
			Item it = new Gson().fromJson(jo.toString(), Item.class);
			list.add(it);
		}
		return list;
	}

	/**
	 * 获取底层页数据
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static SimpleNewsDetail parseSimpleHtmlContent(String json) throws Exception {

		JSONObject ret = new JSONObject(json);
		SimpleNewsDetail detail = new Gson().fromJson(json, SimpleNewsDetail.class);
		if (ret.has("content")) {
			String text = null;
			text = JSON.getRawString("text", ret.getJSONObject("content"));
			detail.setText(text);
		}

		if (ret.has("attribute") && !ret.getString("attribute").equals("[]")) {
			detail.setAttribute(ret.getJSONObject("attribute"));
		}

		if (ret.has("relate_news")) {
			List<Item> lItem = parseListItem(ret.getJSONArray("relate_news"));
			detail.setRelate_news(lItem);
		}
		return detail;
	}

	public static HotAppList parseHotAppListReponse(String json) throws Exception {
		HotAppList response = new HotAppList();
		JSONObject ret = new JSONObject(json);
		if (ret.has("banner")) {
			JSONObject js = ret.getJSONObject("banner");
			response.setBannerURL(JSON.getRawString("url", js));
			response.setBannerIMG(JSON.getRawString("img", js));
		}

		if (ret.has("apps")) {
			JSONArray jsArray = new JSONArray();
			jsArray = ret.getJSONArray("apps");
			int len = jsArray.length();
			ArrayList<HotAppListItem> arrayItem = new ArrayList<HotAppListItem>();
			for (int i = 0; i < len; i++) {
				HotAppListItem item = new HotAppListItem();
				item.setId(JSON.getRawString("id", (JSONObject) jsArray.get(i)));
				item.setName(JSON.getRawString("name", (JSONObject) jsArray.get(i)));
				item.setUrl(JSON.getRawString("url", (JSONObject) jsArray.get(i)));
				item.setDesc(JSON.getRawString("desc", (JSONObject) jsArray.get(i)));
				item.setIcon(JSON.getRawString("icon", (JSONObject) jsArray.get(i)));
				item.setSize(JSON.getRawString("size", (JSONObject) jsArray.get(i)));
				item.setApkName(JSON.getRawString("apkName", (JSONObject) jsArray.get(i)));
				item.setVer(JSON.getRawString("ver", (JSONObject) jsArray.get(i)));
				arrayItem.add(item);
			}
			response.setListItem(arrayItem);

		}
		if (ret.has("remain")) {
			response.setRemain(ret.getString("remain"));
		}
		return response;
	}

	/*
	 * 解析服务器端返回（检查用户是否是合法的测试用户） 0表示可以使用; 1表示用户不是测试用户; 2表示不在测试有效期范围
	 */
	public static BetaApkUser parseBetaTestingAuthInfo(String json) throws Exception {
		return new Gson().fromJson(json, BetaApkUser.class);
	}
	
	
	//解析删除点评、置顶点评、取消置顶点评操作服务器端的返回值
	public static SimpleRet parseRerurnMsgOfCommentOperating(String json) throws Exception {
		return new Gson().fromJson(json, SimpleRet.class);
	}	

	/**
	 * 获取新闻评论数
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static CommentCount parseNewsCommentCount(String json) throws Exception {
		CommentCount response = new CommentCount();
		JSONObject ret = new JSONObject(json);
		if (ret.has("ret")) {
			String retNum = ret.getString("ret");
			response.setRet(retNum);
		}
		if (ret.has("info")) {
			JSONObject infoObect = ret.getJSONObject("info");
			Iterator<?> keyIter = infoObect.keys();
			String key;
			HashMap<String, Object> info = new HashMap<String, Object>();
			while (keyIter.hasNext()) {
				key = (String) keyIter.next();
				JSONObject obj = infoObect.getJSONObject(key);
				CommentCountItem it = new Gson().fromJson(obj.toString(), CommentCountItem.class);
				info.put(key, it);
			}
			response.setInfo(info);
		}
		return response;
	}

	public static OfflineChannel[] parseOfflineList(String json) throws Exception {
		OfflineChannel[] ocs = null;
		JSONObject ret = new JSONObject(json);
		String retStr = JSON.getRawString("ret", ret);
		if ("0".equalsIgnoreCase(retStr)) {
			if (ret.has("list")) {
				JSONArray ja = ret.getJSONArray("list");
				int size = ja.length();
				if (size > 0) {
					ocs = new OfflineChannel[size];
					for (int i = 0; i < size; i++) {
						ocs[i] = new Gson().fromJson(ja.getJSONObject(i).toString(), OfflineChannel.class);
					}
				}
			}
		}
		return ocs;
	}

	public static OfflineItems parseOfflineLibWithoutArticlepool(String json) throws Exception {
		long sstart = System.currentTimeMillis();
		OfflineItems offlineItems = null;
		SLog.i("offline", "开始解析列表json");
		offlineItems = new Gson().fromJson(json, OfflineItems.class);
		SLog.i("offline", "共耗时:" + (System.currentTimeMillis() - sstart));
		return offlineItems;
	}
	
	/**
	 * 解析媒体列表
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static RssCatList parseRssCatList(String json) throws Exception {
		RssCatList response = null;
		JSONObject ret = new JSONObject(json);
		if (ret.has("ret")) {
			String code = ret.getString("ret");
			if (code.equals("0")) {
				response = new Gson().fromJson(json, RssCatList.class);
			}

	        if (ret.has("subCounts")) {
	            JSONObject infoObect = ret.getJSONObject("subCounts");
	            Iterator<?> keyIter = infoObect.keys();
	            String key;
	            HashMap<String, String> subCounts = new HashMap<String, String>();
	            while (keyIter.hasNext()) {
	                key = (String) keyIter.next();
	                subCounts.put(key, infoObect.getString(key));
	            }
	            response.setSubCounts(subCounts);
	        }
		}
		return response;
	}

	/**
	 * 解析单个订阅媒体
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static RssSubItem parseRssSubItem(String json) throws Exception {
	    RssSubItem response = null;
	    JSONObject ret = new JSONObject(json);
	    if (ret.has("ret")) {
	        String code = ret.getString("ret");
	        if (code.equals("0")) {
	            response = new Gson().fromJson(json, RssSubItem.class);
	        }
	    }
	    return response;
	}
	
	/**
	 * 瑙ｆ瀽璁㈤槄鍒楄〃
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static RssChannelList parseRssChannelList(String json) throws Exception {
		RssChannelList response = null;
		JSONObject ret = new JSONObject(json);
		if (ret.has("ret")) {
			String code = ret.getString("ret");
			if (code.equals("0") || code.equals("1")) {
				response = new Gson().fromJson(json, RssChannelList.class);
			}
		}
		return response;
	}
	
	/**
     * 瑙ｆ瀽璁㈤槄鍒楄〃
     * 
     * @param json
     * @return
     * @throws Exception
     */
    public static RssFirstSubscriptionList parseRssFirstSubscriptionList(String json) throws Exception {
        RssFirstSubscriptionList response = null;
        JSONObject ret = new JSONObject(json);
        if (ret.has("ret")) {
            String code = ret.getString("ret");
            if (code.equals("0") || code.equals("1")) {
                response = new Gson().fromJson(json, RssFirstSubscriptionList.class);
            }
        }
        return response;
    }

    /**
     * 瑙ｆ瀽娣诲姞鍒犻櫎鏀惰棌鐨勭粨鏋?
     * 
     * @param json
     * @return
     * @throws Exception
     */
    public static SyncFavorResult parseSyncFavorResult(String json) throws Exception {
        SyncFavorResult response = null;
        JSONObject ret = new JSONObject(json);
        if (ret.has("ret")) {
            String code = ret.getString("ret");
            if (code.equals("0")) {
                response = new Gson().fromJson(json, SyncFavorResult.class);
            }
        }
        return response;
    }
    
    /**parse msglist
     * @param json
     * @return
     * @throws Exception
     */
    public static NewsMsgGroup parseNewsMsgGroup(String json)throws Exception {
    	return new Gson().fromJson(json, NewsMsgGroup.class);
    }
    
    /**parse 删除列表返回结果
     * @param json
     * @return
     * @throws Exception
     */
    public static String parseDelNewsMsgGroup(String json) throws Exception {
        JSONObject ret = new JSONObject(json);
        if (ret.has("ret")) {
            String code = ret.getString("ret");
            if (code.equals("0")) {
                return code;
            }
        }
        return "";
    }
    
    public static NewsMsgList parseNewsMsgList(String json)throws Exception {
    	return new Gson().fromJson(json, NewsMsgList.class);
    }
    
    public static MsgRet parseAddNewsMsg(String json)throws Exception {
    	return new Gson().fromJson(json, MsgRet.class);
    }
    
    public static CheckSubscribe parseCheckSubscribeUpdate(String json) throws Exception {
    	return new Gson().fromJson(json, CheckSubscribe.class);
    }
}
