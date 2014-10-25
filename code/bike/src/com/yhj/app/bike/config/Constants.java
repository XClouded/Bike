package com.yhj.app.bike.config;

import android.os.Environment;

import com.yhj.app.bike.utils.FileUtil;
import com.yhj.app.bike.utils.MobileUtil;

public class Constants {
	
	//SessionData文件保存路径
	public static final String ROOT_PATH = FileUtil.getCacheRootDir() + "/jtjr/jiaoyoubao/data/";
	// Cache Log Path
	public static final String CACHE_PATH = ROOT_PATH + "cache/";
	// 是否是beta测试的apk包
	public static final boolean IS_BETATESTING_APK = false;

    public static final String USER_AGENT = "android_test";
//	public static final String USER_AGENT = "腾讯新闻" + MobileUtil.getVersionCode() + "(android)";

	public static int WTLOGIN_APPID = 638023701;

	public static final int READ_BUFFER = 1024;
	public static final String UTF8 = "utf-8";

	public static final String LOG_PATH = Environment.getExternalStorageDirectory() + "/Tencent/Tencentnews/log/";

	//public static final String LOG_LIST_PATH = Environment.getExternalStorageDirectory() + "/Tencent/Tencentnews/log/listData/";

	/**
	 * sdcard/Tencent/Tencentnews/sync/
	 */
	public static final String SYNC_LOG_PATH = Environment.getExternalStorageDirectory() + "/Tencent/Tencentnews/sync/";
	/**
	 * sdcard/Tencent/Tencentnews/favor/
	 */
	public static final String CACHE_FAVOR_PATH = Environment.getExternalStorageDirectory() + "/Tencent/Tencentnews/favor/";

	/**
	 * sdcard/Tencent/Tencentnews/data/
	 */
	public static final String CACHE_DELTE_PATH = Environment.getExternalStorageDirectory() + "/Tencent/Tencentnews/data/";
	/**
	 * sdcard/Tencent/Tencentnews/data/push/
	 */
	public static final String PUSH_PATH = CACHE_DELTE_PATH + "push/";
	/**
	 * sdcard/Tencent/Tencentnews/data/版本号/
	 */
//	public static final String CACHE_PATH = CACHE_DELTE_PATH + MobileUtil.getVersionCode() + "/";

	/**
	 * sdcard/Tencent/Tencentnews/data/版本号/WebViewScreenShot
	 */
	public static final String WEBVIEW_SCREEN_SHOT_PATH = CACHE_DELTE_PATH + MobileUtil.getVersionCode() + "/" + "WebViewScreenShot/";

	/**
	 * 离线存储路径 sdcard/Tencent/Tencentnews/data/版本号/ol/
	 */
	public static final String CACHE_OFFLINE_PATH = CACHE_DELTE_PATH + MobileUtil.getVersionCode() + "/ol/";
	/**
	 * 离线列表预览文件 sdcard/Tencent/Tencentnews/data/版本号/ol/ol.ls
	 */
	public static final String CACHE_OFFLINE_LIST_PATH = CACHE_OFFLINE_PATH + "ol.ls";
	/**
	 * 离线压缩包解压路径 sdcard/Tencent/Tencentnews/data/版本号/ol/uz/
	 */
	public static final String CACHE_OFFLINE_UNZIP_PATH = CACHE_OFFLINE_PATH + "uz/";
	/**
	 * 离线压缩包路径 sdcard/Tencent/Tencentnews/data/版本号/ol/gz/
	 */
	public static final String CACHE_OFFLINE_GZIP_PATH = CACHE_OFFLINE_PATH + "gz/";
	/**
	 * 离线下载专题列表存储路径 sdcard/Tencent/Tencentnews/data/版本号/ol/specialreport/
	 */
	public static final String CACHE_SPECIAL_REPORT_OFFLINE_PATH = CACHE_OFFLINE_PATH + "specialreport/";
	/**
	 * 离线下载底层页存储路径 sdcard/Tencent/Tencentnews/data/版本号/ol/detail/
	 */
	public static final String CACHE_DETAIL_OFFLINE_PATH = CACHE_OFFLINE_PATH + "detail/";

	/**
	 * sdcard/Tencent/Tencentnews/data/版本号/detail/
	 */
	public static final String CACHE_DETAIL_PATH = CACHE_PATH + "detail/";

	/**
	 * 订阅相关缓存数据sdcard/Tencent/Tencentnews/data/版本号/rss/
	 */
	public static final String CACHE_RSS_PATH = CACHE_PATH + "rss/";
	
	/**
     * 私信组相关缓存数据sdcard/Tencent/Tencentnews/data/版本号/newsmsg/
     */
    public static final String CACHE_NEWS_MSG_PATH = CACHE_PATH + "newsmsg/";
    
	/**
	 * sdcard/Tencent/Tencentnews/data/版本号/specialreport/
	 */
	public static final String CACHE_SPECIAL_REPORT_PATH = CACHE_PATH + "specialreport/";

	/* kiddyliu 2013-06-21  用来标识是在哪个页面展示点评列表
	 * 0：新闻底层页的点评列表 1：我的点评页面的点评列表 2：提到我的页面点评列表*/
	public static final int COMMENT_IN_NEWS_DETAIL_PAGE = 0;
	public static final int COMMENT_IN_MYCOMMENT_PAGE = 1;
	public static final int COMMENT_IN_ATME_COMMENT_PAGE = 2;
	
	/**
	 * SharedPreferences参数
	 */
	public static final String VIDEO_DETAIL_HELP = "video_detail_help";
	public static final String IMAGE_DETAIL_HELP = "image_detail_help";
	public static final String NEWS_DETAIL_HELP = "news_detail_help";
	public static final String RSS_LIST_HELP = "rss_list_help";
	public static final String SP_HELP = "sp_help";
	public static final String SP_CONFIG = "sp_config";
	public static final String SP_OFFLINE = "sp_offline";
	public static final String SP_OFFLINE_PROGRESS = "sp_offline_progress";
	public static final String SP_OFFLINE_CHANNEL_TIME = "sp_offlinechanneltime";
	public static final String CONFIG_IMEI = "config_imei";
	public static final String CONFIG_MAC = "config_mac";
	public static final String CONFIG_PRODUCTTYPE = "product_type";
	public static final String REMOTE_CONFIG = "remote_config";
	public static final String SUB_CHANNEL = "sub_channel";
	public static final String CONFIG_NEWS_CHANNEL_SELECTED = "config_news_channel_selected";
	public static final String CONFIG_PHOTO_CHANNEL = "config_photo_channel";
	public static final String CONFIG_VIDEO_CHANNEL = "config_video_channel";
	public static final String SPLASH_DATA = "splash_data";
	public static final String APPLIST_DATA = "applist_data";
	public static final String PUSH_SEQ = "push_seq";
	public static final String PUSH_CONFIG = "push_config";
	public static final String UPDATA_TIME = "updata_time";
	public static final String EXTENDED_CHANNELS = "extended_channels";
	public static final String SINA_ACCOUNTS_INFO = "sina_accounts_info";
	public static final String WX_ACCOUNTS_INFO = "weixin_accounts_info";
	public static final String SP_SETTING = "sp_setting";
	public static final String SP_FORBID_COMMENT_NEWS = "sp_forbiden_comment_news";
	public static final String SP_NEWS_HAD_READ = "sp_news_had_read";
	public static final String SP_UP_COMMENT = "sp_up_comment";
	public static final String SP_USER_UIN = "sp_user_uin";
	public static final String SP_UPDATE_VERSION_CODE = "sp_update_version_code";
	public static final String SP_DRAFT = "sp_draft";
	public static final String SP_VOTE_OPTID = "sp_vote_iptid";
	public static final String SP_VOTE_AMOUNT = "sp_vote_amount";
	public static final String SHARE_DRAFT = "share_draft";
	public static final String CHECK_UPDATE = "check_update";
	public static final String CHECK_UPDATE_PROGRESS = "check_update_progress";
	public static final String SILENT_DOWNLOAD = "silent_download";
	public static final String SP_RSS_LOGIN_ALERT = "sp_rss_login_alert";
    public static final String SP_RSS_CHANNEL_VERSION = "sp_rss_channel_version";
    public static final String SP_RSS_COVER_PARAM = "sp_rss_cover_param";
    public static final String SP_UUID = "sp_uuid";
	public static final String APPID = "20131314";
	public static final String SP_RSS_CHECK_TIME = "sp_rss_check_time";
	public static final String SP_RSS_HAS_UPDATE = "sp_rss_has_update";
	public static final String SP_RSS_GUIDE_ALREADY_RUN = "sp_rss_already_run";
    public static final String SP_RSS_FIRST_RSS_MEDIA = "sp_rss_first_rss_media";
	/**
	 * 设置项是否push
	 */
	public static final boolean SETTING_IF_PUSH = true;
	public static final String SETTING_KEY_IF_PUSH = "setting_key_if_push";
	/**
	 * 设置是否加载更多
	 */
	public static final boolean SETTING_IF_AUTO_LOAD_MORE = true;
	public static final String SETTING_KEY_IF_AUTO_LOAD_MORE = "setting_key_if_auto_load_more";

	/**
	 * 文字模式
	 */
	public static final int TYPE_ITEM_TEXT = 0;
	public static final int TYPE_ITEM_IMAGE = 1;
	public static final int TYPE_ITEM_SECTION = 2;
	public static final int TYPE_ITEM_THREE_PHOTO = 2;
	public static final int TYPE_ITEM_CELL = 3;
	public static final boolean SETTING_IF_TEXT_MODE = false;
	public static final String SETTING_KEY_IF_TEXT_MODE = "setting_key_if_text_mode";
	public static final int TYPE_ITEM_RECEVER = 0;
	public static final int TYPE_ITEM_SENDER = 1;

	// 夜间模式
	public static final String SETTING_THEME = "setting_theme";
	public static final int SETTING_THEME_DEFAULT_MODE = 0;
	public static final int SETTING_THEME_NIGHT_MODE = 1;

	/**
	 * 设置项字体大小 0-小 1-中 2-大 3-特大 对应不可改变
	 */
	public static final String[] wordSizeStr = new String[] { "小", "中", "大", "特大" };
	public static final int SETTING_MIN_TEXT_SIZE = 0;
	public static final int SETTING_MID_TEXT_SIZE = 1;
	public static final int SETTING_MAX_TEXT_SIZE = 2;
	public static final int SETTING_BIG_TEXT_SIZE = 3;
	public static final String SETTING_KEY_TEXT_SIZE = "setting_key_text_size";

	// 线程优先级MAX_PRIORITY = 10;NORM_PRIORITY = 5;MIN_PRIORITY = 1;
	public final static int THREAD_PRIORITY = 3;

	public static final String REQUEST_METHOD_POST = "POST";

	public static final String REQUEST_METHOD_GET = "GET";

	public static final String REQUEST_METHOD_PUT = "PUT";

	public static final String REQUEST_METHOD_DELETE = "DELETE";

	public static final String DEFAULT_CURSOR = "-1";

	/** 下拉刷新列表的各个状态 */
	public static final int LIST = 0;
	public static final int EMPTY = 1;
	public static final int ERROR = 2;
	public static final int LOADING = 3;

	/** 检查更新跳转 */
	public static final int FROM_UPDATE_NOW = 1;
	public static final int FROM_SETTING = 2;

	/** 静默下载状态 */
	public static final int SILENT_DEFAULT = 0;
	public static final int SILENT_START_DOWNLOAD = 1;

	/** 下拉刷新timeTag name */
	public static final String TOPIC_TAG = "TopicActivity_news";
	public static final String IMPORTANT_TAG = "ImportantNewsActivity_news";
	public static final String PHOTO_TAG = "PhotoActivity_news";
	public static final String SPORTS_TAG = "SportsActivity_news";
	public static final String FINANCE_TAG = "FinanceActivity_news";
	public static final String SPORTFUL_TAG = "SportFulActivity_news";
	public static final String TECHNOLOGY_TAG = "TechnologyActivity_news";
	public static final String MILITARY_TAG = "MilitaryActivity_news";
	public static final String DIGIT_TAG = "DigitActivity_news";
	public static final String FEMALE_TAG = "FemaleActivity_news";
	public static final String AUTO_TAG = "AutoActivity_news";
	public static final String HOUSE_TAG = "HouseActivity_news";

	/** activity 传递数据的key值 */
	public static final String PHOTO_KEY = "com.tencent_news_photo";
	public static final String VIDEO_KEY = "com.tencent_news.video";
	public static final String NEWS_DETAIL_KEY = "com.tencent.news.detail";
	public static final String WRITE_WHICH_KEY = "com.tencent.news.write.whichUI";
	public static final String WRITE_COMMENT_KEY = "com.tencent.news.write";
	public static final String WRITE_COMMENT_IMG_KEY = "com.tencent.news.write.img";
	public static final String WRITE_COMMENT_SHARE_TYPE_KEY = "com.tencent.news.write.shareType";
	public static final String WRITE_COMMENT_QQ_WEIBO_KEY = "com.tencent.news.write.commentqqweibo";
	public static final String WRITE_TRAN_QQ_WEIBO_KEY = "com.tencent.news.write.tranqqweibo";
	public static final String WRITE_COMMENT_VID_KEY = "com.tencent.news.write.vid";
	public static final String WRITE_COMMENT_GRAPHICLIVECHLID_KEY = "com.tencent.news.write.graphiclivechlid";
	public static final String WRITE_COMMENT_CHANNEL_KEY = "com.tencent.news.write.channel";
	public static final String WRITE_TRAN_COMMENT_KEY = "com.tencent.news.write.tran";
	public static final String LOGIN_SUCCESS_BACK_USER_KEY = "login_success_back_user_key";
	public static final String NEWS_ID_KEY = "news_id";
	public static final String NEWS_CLICK_POSITION = "news_position";
	public static final String NEWS_CHANNEL_CHLID_KEY = "com.tencent_news_detail_chlid";
	public static final String NEWS_CLICK_ITEM_POSITION = "com.tencent_news_list_item";
	public static final String NEWS_CLICK_ITEM_UIN = "com.tencent_news_list_item_uin";
	public static final String NEWS_ITEM_READ_BROADCAST = "com.tencent_news_list_item_read_broadcast";
	public static final String IS_SPECIAL_KEY = "is_special";
	public static final String IS_WIDGET_FROM_KEY = "is_widget";
	public static final String WRITE_SUCCESS_ACTION = "com.tencent.news.write.success";
	public static final String CLEAR_CAHCE_ACTION = "com.tencent.news.clear.cache";
	public static final String WRITE_SUCCESS_SERVER_BACK_COMMENT = "com.tencent.news.write.success.back.comment";
	public static final String WRITE_SUCCESS_SERVER_BACK_COMMENT_ITEM_ID = "com.tencent.news.write.success.back.commentid";
	public static final String PLAY_VIDEO_VID_KEY = "com.tencent.news.play_video";
	public static final String PREVIEW_IMAGE_KEY = "com.tencent.news.view_image";
	public static final String PREVIEW_IMAGE_INDEX_KEY = "com.tencent.news.view_image_index";
	public static final String PREVIEW_IMAGE_DESCRIPTION_KEY = "com.tencent.news.view_image_description";
	public static final String APPLIST_CLICKED_APP = "com.tencent.news.applist.clicked_app";
	public static final String NEWS_DETAIL_TITLE_KEY = "com.tencent.news.newsdetail";
	public static final String NEWS_DETAIL_FROM_OFFLINE_KEY = "com.tencent.news.newsdetail.fromOffline";
	public static final String SAVE_SUB_CHANNELLIST_KEY = "sub_channellist";
	public static final String NEWS_CURRENT_CHANNEL = "news_current_channel";
	public static final String PHOTO_CURRENT_CHANNEL = "photo_current_channel";
	public static final String VIDEO_CURRENT_CHANNEL = "video_current_channel";
	public static final String SAVE_CURRENT_EXTENDED_BAR = "extended_channels_bar";
	public static final String SAVE_NEWS_VERSION_KEY = "news_version";
	public static final String WATICH_IMAGE_CURRENT_INDEX_BACK = "watich_image_index_back";
	public static final int RQ_WATICH_IMAGE = 1024;
	public static final String LIVE_PLAY_URL = "play_url";
	public static final String LIVE_PLAY_TITLE = "play_title";
	public static final String NEWS_CHLIDE_CHANNEL = "news_chlid_channel";
	public static final String IS_START_CHANNEL = "isStartChannel";
	public static final String IS_FROM_VIEWPAGER = "is_from_viewpager";
	public static final String IS_CURRENT_CHANNEL = "current_channel";
	public static final String WEIXIN_CHANNEL = "weixin_channel";
	public static final String NEWS_SHARE_CONTENT = "news_share_content";
	public static final String NEWS_SHARE_ITEM = "news_share_item";
	public static final String NEWS_SHARE_TYPE = "news_share_type";
	public static final String SINA_LOGIN_FROM = "sina_login_from";
	public static final String NEWS_SHARE_WEIXIN_OR_FIRENDS = "news_share_weixin_or_firends";
	public static final String IS_RELATE_NEWS = "is_relate_news";

	/**
	 * 写界面类型
	 */
	public static final int WRITE_SUGGEST = 0;
	public static final int WRITE_COMMENT = 1;
	public static final int WRITE_TRANS_COMMENT = 2;

	public static final String SUGGEST_ID = "suggest";

	public static final String LOGIN_FROM = "com.tencent.news.login_from";
	public static final int LOGIN_FROM_SETTING = 0;
	public static final int LOGIN_FROM_SHARE_BTN = 1;
	public static final int LOGIN_FROM_SEND_BTN = 2;
	public static final int LOGIN_FROM_SHARE_TENCENT_WEIBO = 3;
	public static final int LOGIN_FROM_SHARE_TENCENT_QZONE = 4;
	public static final int LOGIN_FROM_RSS_ADD = 5;
	public static final int LOGIN_FROM_MY_MSG = 6;
	public static final int LOGIN_FROM_PORTFOLIO = 7;
	public static final int LOGIN_FROM_RSS_SEND = 8;
	public static final String LOGIN_BACK = "com.tencent.news.login_back";
	public static final String SAVE_INPUT = "input";

	/**
	 * 刷新评论数的广播
	 */
	public static final String REFRESH_COMMENT_NUMBER_ACTION = "refresh.comment.number.action";
	public static final String REFRESH_COMMENT_NUMBER = "refresh_comment_number";
	public static final String REFRESH_COMMENT_ITEM_ID = "refresh_comment_item_id";
	public static final String REFRESH_MSG_LIST = "refresh_msg_list";

	/**
	 * 拉起新闻详情页成功后，返回的id是否已读
	 */
	public static final String NEWS_HAD_READ_ACTION = "news_had_read_broadcast";
	public static final String NEWS_HAD_READ_FOR_OFFLINE_ACTION = "news_had_read_for_offline_action";
	public static final String NEWS_HAD_READ_SPECIAL_ACTION = "news_had_read_special_broadcast";

	/**
	 * 微信成功授权广播
	 */
	public static final String WX_AUTH_SUCCESS_ACTION = "wx_auth_success_action";

	/**
	 * what刷新评论数
	 */
	public static final int REFRESH_COMMENT_NUM = 100;
	/**
	 * what禁止评论
	 */
	public static final int FORBID_COMMENT = -1;
	/**
	 * what提示没有更多评论了
	 */
	public static final int TIPS_NO_MORE_COMMENTS = 2048;
	public static final String FORBID_COMMENT_ID = "-1";

	/**
	 * 去登录
	 */
	public static final int REQUEST_CODE_LOGIN = 101;
	/**
	 * 去新浪微博登录
	 */
	public static final int REQUEST_CODE_LOGIN_SINA = 102;
	/**
	 * 去微信登录
	 */
	public static final int REQUEST_CODE_LOGIN_WEIXIN = 103;
	/**
	 * 去频道设置
	 */
	public static final int REQUEST_CODE_SET_CHANNEL = 105;
    /**
     * 去订阅频道设置
     */
    public static final int REQUEST_CODE_SET_RSS_CHANNEL = 106;

	/**
	 * 清除缓存通知栏的id
	 */
	public final static int CLEAR_CACHE_NOTIFICATION_ID = 1000;
	/**
	 * 延迟删除通知时间，单位：ms<br/>
	 */
	public static final int DELETE_NOTIFICATION_DELAY = 7000;
	/**
	 * 验证码长度
	 */
	public static final String VERIFICATION_CODE_REG = "[\\d[a-zA-Z]]{4}";

	public final static String ALARM_ACTION = "alarm.push.timer.action";

	/**
	 * 新闻频道一屏个数展现
	 */
	public static final int NUMBER_OF_SCREEN = 5;

	/**
	 * 滚动方向判断常量
	 */
	public static final float DIRETION_NUMBER = 0.5f;

	public final static String WEB_ERROR = "file:///android_asset/error.html";

	/**
	 * push 状态 与上报时间间隔
	 */
	public static final int SERVICE_STARTING = 0;
	public static final int SERVICE_STOPPING = 1;
	public static final int SERVICE_RUNNING = 2;
	public static final int SERVICE_CLOSEED = 3;

	public static final int PUSH_NOT_CONNECTED = 0;
	public static final int PUSH_CONNECTED = 1;
	public static final int PUSH_CONNECTING = 2;
	public static final int PUSH_HTTP = 3;
	// 时间间隔
	public static final int TIMER_INTERVAL = 6 * 60 * 60 * 1000; // 6小时

	// image text live
	public static final int REQUEST_CODE_IMAGE_TEXT_LIVE = 9000;
	public static final String IMAGE_TEXT_LIVE_KEY_IMAGE_RESULT_INDEX = "image_text_live_key_image_result_index";

	public static final String TAG_COMMENT_HOT = "hot";
	public static final String TAG_COMMENT_LATEST = "latest";
	public static final String TAG_COMMENT_CANTBEUP = "cantbeup";

	/**
	 * 新闻频道新加载广播
	 */
	public static final String NEWS_CHANNEL_NEW_LOAD = "news_channel_new_load";
	public static final int WHAT_CHANNEL_LOAD = 1001;
	
	/**
	 * 刷新收藏列表广播
	 */
    public static final String FAVOR_LIST_REFRESH_ACTION = "favor_list_refresh_action";
    public static final String FAVOR_LIST_REFRESH_ID = "favor_list_refresh_id";
    public static final String FAVOR_LIST_OP_TYPE = "favor_list_op_type";
    public static final String FAVOR_LIST_ITEM = "favor_list_item";

    /**
     * 刷新私信编辑状态广播
     */
    public static final String UPDATE_MSGGROUP_EDIT_STATUS = "update_msg_group_edit_status";
    /**
     * 刷新私信和提到我的评论未读消息数广播
     */
    public static final String UPDATE_MAIL_AND_AT_COUNT = "update_mail_and_at_count";
    
    public static final String NEWS_SUB_ID = "news_sub";
    
    // action for rss guide finished event.
    public static final String RSS_GUIDE_FINISHED_ACTION = "rss.guide.finished.action";
    
}
