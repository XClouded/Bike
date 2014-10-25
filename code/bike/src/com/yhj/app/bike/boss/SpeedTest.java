package com.yhj.app.bike.boss;

import java.util.Locale;
import java.util.Properties;
import java.util.Random;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import android.os.Process;

import com.tencent.omg.webdev.WebDev;
import com.yhj.app.bike.command.BaseHttpRequest;
import com.yhj.app.bike.command.GetImageRequest;
import com.yhj.app.bike.command.HttpDataRequest;
import com.yhj.app.bike.command.HttpPostRequest;
import com.yhj.app.bike.command.HttpTagDispatch.HttpTag;
import com.yhj.app.bike.system.Application;
import com.yhj.app.bike.utils.MobileUtil;
import com.yhj.app.bike.utils.SLog;

public class SpeedTest {

	private static final String TAG = "SpeedTest";
	/**
	 * 配置拉取
	 */
	public static final String ITIL_SPEEDTEST_GETREMOTECONFIG = "itil_speedTest_GetRemoteConfig";
	/**
	 * 获取splash页
	 */
	public static final String ITIL_SPEEDTEST_GETSPLASH = "itil_speedTest_GetSplash";
	/**
	 * 获取列表页
	 */
	public static final String ITIL_SPEEDTEST_DOWNLOADLISTITEM = "itil_speedTest_DownloadListItem";
	/**
	 * 获取底层页
	 */
	public static final String ITIL_SPEEDTEST_LOADBASEDETAILNORMALCONTENT = "itil_speedTest_LoadBaseDetailNormalContent";
	/**
	 * 图片
	 */
	public static final String ITIL_SPEEDTEST_LOADIMAGE = "itil_speedTest_LoadImage";
	/**
	 * 获取评论
	 */
	public static final String ITIL_SPEEDTEST_GETCOMMENT = "itil_speedTest_GetComment";
	/**
	 * 获取评论数
	 */
	public static final String ITIL_SPEEDTEST_LOADCOMMENTCOUNT = "itil_speedTest_LoadCommentCount";
	/**
	 * 图文直播
	 */
	public static final String ITIL_SPEEDTEST_LOADPICTEXTLIVE = "itil_speedTest_LoadPicTextLive";
	/**
	 * 视频点播
	 */
	public static final String ITIL_SPEEDTEST_VIDEOPLAY = "itil_speedTest_VideoPlay";
	/**
	 * 视频直播
	 */
	public static final String ITIL_SPEEDTEST_VIDEOLIVEPLAY = "itil_speedTest_VideoLivePlay";
	/**
	 * 新闻页卡二级导航
	 */
	public static final String ITIL_SPEEDTEST_SUBCHANNELNEWS = "itil_speedTest_SubChannelNews";
	/**
	 * 图片页卡二级导航
	 */
	public static final String ITIL_SPEEDTEST_SUBCHANNELIMAGE = "itil_speedTest_SubChannelImage";
	/**
	 * 视频页卡二级导航
	 */
	public static final String ITIL_SPEEDTEST_SUBCHANNELVIDEO = "itil_speedTest_SubChannelVideo";
	/**
	 * 获取拓展页卡
	 */
	public static final String ITIL_SPEEDTEST_GETEXTENDEDCHANNELS = "itil_speedTest_GetExtendedChannels";
	/**
	 * 精品推荐
	 */
	public static final String ITIL_SPEEDTEST_BOUTIQUERECOMMEND = "itil_speedTest_BoutiqueRecommend";
	/**
	 * 获取投票信息
	 */
	public static final String ITIL_SPEEDTEST_VOTE = "itil_speedTest_Vote";
	/**
	 * 获取最新微博
	 */
	public static final String ITIL_SPEEDTEST_HOTWEIBO = "itil_speedTest_HotWeibo";
	/**
	 * 获取专题列表
	 */
	public static final String ITIL_SPEEDTEST_LOADSPECIALLISTITEMS = "itil_speedTest_LoadSpecialListItems";
	/**
	 * 获取用户信息
	 */
	public static final String ITIL_SPEEDTEST_GETQQUSERINFO = "itil_speedTest_GetQQUserInfo";
	/**
	 * 获取打包列表
	 */
	public static final String ITIL_SPEEDTEST_GETPACKLIST = "itil_speedTest_GetPackList";
	/**
	 * 离线下载
	 */
	public static final String ITIL_SPEEDTEST_GETPACKDATA = "itil_speedTest_GetPackData";
	/**
	 * 发表评论
	 */
	public static final String ITIL_SPEEDTEST_SENDREPORTCOMMENT = "itil_speedTest_SendReportComment";
	/**
	 * 发表微博
	 */
	public static final String ITIL_SPEEDTEST_SENDWEIBO = "itil_speedTest_SendWeibo";
	/**
	 * 腾讯微博分享
	 */
	public static final String ITIL_SPEEDTEST_SHARETOQQWEIBO = "itil_speedTest_ShareToQQWeibo";
	/**
	 * 新浪微博分享
	 */
	public static final String ITIL_SPEEDTEST_SHARETOSINAWEIBO = "itil_speedTest_ShareToSinaWeibo";
	/**
	 * QQ空间分享
	 */
	public static final String ITIL_SPEEDTEST_SHARETOQZONE = "itil_speedTest_ShareToQzone";
	/**
	 * 朋友圈分享
	 */
	public static final String ITIL_SPEEDTEST_SHARETOCIRCLE = "itil_speedTest_ShareToCircle";
	/**
	 * 上报token
	 */
	public static final String ITIL_SPEEDTEST_REGISTDEVICETOKEN = "itil_speedTest_RegistDeviceToken";
	/**
	 * 财经垂直化插件
	 */
	public static final String ITIL_SPEEDTEST_PLUGINSTOCK = "itil_speedTest_PluginStock";
	/**
	 * 体育垂直化插件
	 */
	public static final String ITIL_SPEEDTEST_PLUGINSPORT = "itil_speedTest_PluginSport";
	/**
	 * 获取评论
	 */
	public static final String ITIL_ERROR_GETCOMMENT = "itil_error_GetComment";
	/**
	 * 图文直播
	 */
	public static final String ITIL_ERROR_LOADPICTEXTLIVE = "itil_error_LoadPicTextLive";
	/**
	 * 视频点播
	 */
	public static final String ITIL_ERROR_VIDEOPLAY = "itil_error_VideoPlay";
	/**
	 * 视频直播
	 */
	public static final String ITIL_ERROR_VIDEOLIVEPLAY = "itil_error_VideoLivePlay";
	/**
	 * 获取用户信息
	 */
	public static final String ITIL_ERROR_GETQQUSERINFO = "itil_error_GetQQUserInfo";
	/**
	 * 获取打包列表
	 */
	public static final String ITIL_ERROR_GETPACKLIST = "itil_error_GetPackList";
	/**
	 * 获取打包数据
	 */
	public static final String ITIL_ERROR_GETPACKDATA = "itil_error_GetPackData";
	/**
	 * 用户登录
	 */
	public static final String ITIL_ERROR_LOGINQQ = "itil_error_LoginQQ";
	/**
	 * 财经垂直化插件
	 */
	public static final String ITIL_ERROR_PLUGINSTOCK = "itil_error_PluginStock";
	/**
	 * 体育垂直化插件
	 */
	public static final String ITIL_ERROR_PLUGINSPORT = "itil_error_PluginSport";

	private static Random r = new Random();

	public static boolean whetherSpeedTest() {
		// return r.nextInt(100) == 88;
		return true;
	}

	public static void aspect(BaseHttpRequest baseRequest, HttpResponse httpResponse, long startTime, long headerTime, long dataTime) {

		if (SpeedTest.whetherSpeedTest()) {

			if (baseRequest instanceof HttpDataRequest || baseRequest instanceof HttpPostRequest) {

				SpeedTest.dealData((HttpDataRequest) baseRequest, httpResponse, startTime, headerTime, dataTime);

			} else if (baseRequest instanceof GetImageRequest) {

				String url = baseRequest.getUrl();
				if (url != null && url.length() > 0 && url.toLowerCase(Locale.US).startsWith("http://pnewsapp.tc.qq.com")) {
					SpeedTest.dealImg(url, httpResponse, startTime, headerTime, dataTime);
				}

			}

		}
	}

	private static void dumpLog(String url, HttpResponse httpResponse, long startTime, long headerTime, long dataTime) {

		SLog.e(TAG, SLog.getTraceInfo() + Thread.currentThread().getId() + "-->url=" + url);
		SLog.i(TAG, SLog.getTraceInfo() + Thread.currentThread().getId() + "-->startTime=" + startTime);
		SLog.i(TAG, SLog.getTraceInfo() + Thread.currentThread().getId() + "-->headerTime=" + headerTime);
		SLog.i(TAG, SLog.getTraceInfo() + Thread.currentThread().getId() + "-->dataTime=" + dataTime);

		SLog.i(TAG, SLog.getTraceInfo() + Thread.currentThread().getId() + "-->headerTime-startTime=" + (headerTime - startTime));
		SLog.i(TAG, SLog.getTraceInfo() + Thread.currentThread().getId() + "-->dataTime-headerTime=" + (dataTime - headerTime));

	}

	private static Properties getPts(String url, HttpResponse httpResponse, long startTime, long headerTime, long dataTime) {

		dumpLog(url, httpResponse, startTime, headerTime, dataTime);

		Properties pts = new Properties();
		Header[] headers = httpResponse.getAllHeaders();
		if (headers != null && headers.length > 0) {
			for (int i = 0; i < headers.length; i++) {
				Header header = headers[i];

				if ("X-Client-Ip".equalsIgnoreCase(header.getName())) {
					SLog.i(TAG, SLog.getTraceInfo() + "-->" + header.getName() + "=" + header.getValue());
					pts.setProperty("clientIP", header.getValue());
				} else if ("X-Server-Time".equalsIgnoreCase(header.getName())) {
					SLog.i(TAG, SLog.getTraceInfo() + "-->" + header.getName() + "=" + header.getValue());
					pts.setProperty("servertime", header.getValue());
				} else if ("X-Server-Ip".equalsIgnoreCase(header.getName())) {
					SLog.i(TAG, SLog.getTraceInfo() + "-->" + header.getName() + "=" + header.getValue());
					pts.setProperty("serverIP", header.getValue());
				}
			}
		} else {
			pts.setProperty("clientIP", "");
			pts.setProperty("servertime", "");
			pts.setProperty("serverIP", "");
		}

		pts.setProperty("deviceID", MobileUtil.getBossId());
		pts.setProperty("startTime", "" + startTime);
		pts.setProperty("requestUrl", "" + url);
		pts.setProperty("status", "" + httpResponse.getStatusLine());
		pts.setProperty("connTime", "" + (headerTime - startTime));
		pts.setProperty("transTime", "" + (dataTime - headerTime));
		try {
			pts.setProperty("size", "" + httpResponse.getEntity().getContentLength());
		} catch (Exception e) {
			pts.setProperty("size", "0");
		}
		pts.setProperty("extra", "");
		return pts;
	}

	private static void dealImg(String url, HttpResponse httpResponse, long startTime, long headerTime, long dataTime) {
		if (url == null || httpResponse == null) {
			return;
		}

		report(ITIL_SPEEDTEST_LOADIMAGE, getPts(url, httpResponse, startTime, headerTime, dataTime));

	}

	public static void dealData(HttpDataRequest baseRequest, HttpResponse httpResponse, long startTime, long headerTime, long dataTime) {

		if (baseRequest == null || httpResponse == null) {
			return;
		}

		dispachReport(baseRequest.getTag(), getPts(baseRequest.getUrl(), httpResponse, startTime, headerTime, dataTime));
	}

	private static void dispachReport(HttpTag tag, Properties pts) {
		/*switch (tag) {
		case NEWS_REMOTE_CONFIG:
			report(ITIL_SPEEDTEST_GETREMOTECONFIG, pts);
			break;
		case SPLASH_DATA:
			report(ITIL_SPEEDTEST_GETSPLASH, pts);
			break;
		case NEWS_NEWS_TOP:
			report(ITIL_SPEEDTEST_DOWNLOADLISTITEM, pts);
			break;
		case SIMPLE_HTML_CONTENT:
			report(ITIL_SPEEDTEST_LOADBASEDETAILNORMALCONTENT, pts);
			break;
		case QQNEWS_COMMENT:
		case QQNEWS_COMMENT_GET_MORE:
		case GET_MYCOMMENTS:
		case GET_MYCOMMENTS_MORE:
		case GET_ATCOMMENTS:
		case GET_ATCOMMENTS_MORE:
			report(ITIL_SPEEDTEST_GETCOMMENT, pts);
			break;
		case QQNEWS_COMMENT_COUNT:
			report(ITIL_SPEEDTEST_LOADCOMMENTCOUNT, pts);
			break;
		case IMG_TXT_LIVE_NEW_DATA:
		case IMG_TXT_LIVE_REFRESH_DATA:
		case IMG_TXT_LIVE_AUTO_REFRESH_DATA:
		case IMG_TXT_LIVE_LOAD_MORE_DATA:
			report(ITIL_SPEEDTEST_LOADPICTEXTLIVE, pts);
			break;
		case GET_VIDEO_URL:
			report(ITIL_SPEEDTEST_VIDEOPLAY, pts);
			break;
		case VIDEO_LIVE:
			report(ITIL_SPEEDTEST_VIDEOLIVEPLAY, pts);
			break;
		case GET_SUB_CHANNELS:
			String url = pts.getProperty("requestUrl");
			if (url.indexOf("getSubChannels?") > 0) {
				report(ITIL_SPEEDTEST_SUBCHANNELNEWS, pts);
			} else if (url.indexOf("getSubChannelsReserved?") > 0 && url.indexOf("news_photo") > 0) {
				report(ITIL_SPEEDTEST_SUBCHANNELIMAGE, pts);
			} else if (url.indexOf("getSubChannelsReserved?") > 0 && url.indexOf("news_video") > 0) {
				report(ITIL_SPEEDTEST_SUBCHANNELVIDEO, pts);
			}
			break;
		case GET_EXTENDED_SUB_CHANNELS:
			report(ITIL_SPEEDTEST_GETEXTENDEDCHANNELS, pts);
			break;
		case HOT_APP_LIST:
			report(ITIL_SPEEDTEST_BOUTIQUERECOMMEND, pts);
			break;
		case QQNEWS_VOTE_INFO:
			report(ITIL_SPEEDTEST_VOTE, pts);
			break;
		case SPECIAL_NEWS_LIST:
			report(ITIL_SPEEDTEST_LOADSPECIALLISTITEMS, pts);
			break;
		case GET_USER_INFO_AFTER_WTLOGIN:
			report(ITIL_SPEEDTEST_GETQQUSERINFO, pts);
			break;
		case OFFLINE_LIST:
			report(ITIL_SPEEDTEST_GETPACKLIST, pts);
			break;
		case PUBLISH_QQNEWS_MULTI:
		case PUBLISH_TRANS_COMMENT_MULTI:
			report(ITIL_SPEEDTEST_SENDREPORTCOMMENT, pts);
			break;
		case SHARE_TENCENT_WEIBO:
			report(ITIL_SPEEDTEST_SHARETOQQWEIBO, pts);
			break;
		case SHARE_SINA_WEIBO:
			report(ITIL_SPEEDTEST_SHARETOSINAWEIBO, pts);
		case SHARE_TENCENT_QZONE:
			report(ITIL_SPEEDTEST_SHARETOQZONE, pts);
		default:
			break;
		}*/
	}

	private static void report(String eventId, Properties pts) {
		WebDev.trackCustomEvent(Application.getInstance().getApplicationContext(), eventId, pts);
	}

}
