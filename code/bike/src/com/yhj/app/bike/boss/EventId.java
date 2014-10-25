package com.yhj.app.bike.boss;

public class EventId {

	public static final String KEY_PUSHRECVFLAG = "pushRecvFlag";
	public static final String KEY_SERVICESTATE = "serviceState";
	public static final String KEY_PUSHID = "pushId";
	public static final String KEY_PUSHCHANNELSTATE = "pushChannelState";

	public static final String KEY_PAGE = "page";
	public static final String KEY_CHANNELID = "channelId";
	public static final String KEY_REPLYID = "replyId";
	public static final String KEY_NEWSID = "newsId";
	public static final String KEY_LISTPOS = "listPos";
	public static final String KEY_DETAILTYPE = "detailType";

	public static final String KEY_TYPE = "type";
	public static final String VALUE_TENCENT_WEIBO = "3";
	public static final String VALUE_SINA_WEIBO = "4";
	public static final String VALUE_QZONE = "5";
	public static final String VALUE_WEIXIN_FRIEND = "6";
	public static final String VALUE_WEIXIN_FRIEND_ZONE = "7";

	public static final String KEY_COMMENTID = "commentId";
	public static final String KEY_IMAGEURL = "imageURL";
	public static final String KEY_VID = "vId";
	public static final String KEY_RESCODE = "resCode";
	public static final String VALUE_RESCODE_SUCCESS = "0";
	public static final String VALUE_RESCODE_ERROR = "1";
	public static final String VALUE_RESCODE_SERVER_ERROR = "9999";
	public static final String VALUE_RESCODE_FORBID = "-1";

	public static final String KEY_ISLIVE = "isLive";
	public static final String VALUE_LIVE = "1";
	public static final String VALUE_NOT_LIVE = "0";

	public static final String KEY_VERSION = "version";
	public static final String KEY_PACKAGENAME = "packageName";
	public static final String KEY_APPNAME = "appName";
	public static final String KEY_TIMESTAMP = "timestamp";
	public static final String KEY_TIMEPERIOD = "timePeriod";

	public static final String BOSS_APP_NORMAL_START = "boss_app_normal_start";
	public static final String BOSS_APP_INSTALL = "boss_app_install";
	public static final String BOSS_APP_UPDATE = "boss_app_update";

	public static final String BOSS_CLICK_SOFT_MENU = "boss_click_soft_menu";

	public static final String BOSS_SETTING_PUSH = "boss_setting_push";
	public static final String BOSS_SETTING_AUTOLOAD = "boss_setting_autoload";
	public static final String BOSS_SETTING_TEXTMODE = "boss_setting_textmode";
	public static final String BOSS_SETTING_NIGHTMODE = "boss_setting_nightmode";
	public static final String BOSS_SETTING_TEXTSIZE = "boss_setting_textsize";
	public static final String BOSS_SETTING_CHECKUPDATE = "boss_setting_checkupdate";
	public static final String BOSS_SETTING_CLEARCACHE = "boss_setting_clearcache";

	public static final String BOSS_SETTING_LOGINFROM_SHARE_TENCENT_WEIBO = "boss_setting_loginfrom_share_tencent_weibo";
	public static final String BOSS_SETTING_LOGINFROM_SHARE_TENCENT_QZONE = "boss_setting_loginfrom_share_tencent_qzone";
	public static final String BOSS_SETTING_LOGINFROM_SHARE_SINAWB = "boss_setting_loginfrom_share_sinawb";
	public static final String BOSS_SETTING_LOGINFROM_WRITE_COMMENT = "boss_setting_loginfrom_write_comment";
	public static final String BOSS_SETTING_LOGINFROM_SUGGEST = "boss_setting_loginfrom_suggest";
	public static final String BOSS_SETTING_LOGINFROM_MYACC_SINAWB = "boss_setting_loginfrom_myacc_sinawb";
	public static final String BOSS_SETTING_LOGINFROM_MYACC_QQNEWS = "boss_setting_loginfrom_myacc_qqnews";
	public static final String BOSS_SETTING_LOGINFROM_FAVORITES = "boss_setting_loginfrom_favorites";

	public static final String BOSS_FAVORITES_CLICK_MENU = "boss_favorites_click_menu";
	public static final String BOSS_FAVORITES_LIST_CLICK_DEL_BTN = "boss_favorites_list_click_del_btn";
	public static final String BOSS_FAVORITES_CLICK_COLLECT_BTN = "boss_favorites_click_collect_btn";
	public static final String BOSS_FAVORITES_CLICK_UN_COLLECT_BTN = "boss_favorites_click_un_collect_btn";

	public static final String ITIL_LOAD_CHANNEL_TIME = "itil_load_channel_time";
	public static final String ITIL_LOAD_CHANNEL_TIME_RESULT = "itil_load_channel_time_result";
	public static final String ITIL_LOAD_COMMENT_TIME = "itil_load_comment_time";
	public static final String ITIL_LOAD_COMMENT_TIME_RESULT = "itil_load_comment_time_result";
	public static final String ITIL_LOAD_DETAIL_TIME = "itil_load_detail_time";
	public static final String ITIL_LOAD_DETAIL_TIME_RESULT = "itil_load_detail_time_result";
	public static final String ITIL_SEND_WORDS_TIME = "itil_send_words_time";
	public static final String ITIL_SEND_WORDS_TIME_RESULT = "itil_send_words_time_result";
	public static final String ITIL_PLAY_VIDEO_TIME = "itil_play_video_time";
	public static final String ITIL_LOAD_REMOTE_CONFIG_TIME = "itil_load_remote_config_time";
	public static final String ITIL_LOAD_REMOTE_CONFIG_TIME_RESULT = "itil_load_remote_config_time_result";
	public static final String ITIL_LOAD_SPLASH_TIME = "itil_load_splash_time";
	public static final String ITIL_LOAD_SPLASH_TIME_RESULT = "itil_load_splash_time_result";

	public static final String BOSS_DOWNLOADER_BEGIN = "boss_downloader_begin";
	public static final String BOSS_DOWNLOADER_COMPLETED = "boss_downloader_completed";
	public static final String BOSS_DOWNLOADER_INSTALLED = "boss_downloader_installed";

	public static final String BOSS_VIEW_CHANNEL_TIMESTAMP = "boss_view_channel_timestamp";

	public static final String BOSS_OFFLINE_VIEW_TIME = "boss_offline_view_time";
	public static final String BOSS_OFFLINE_ONE_CHANNEL_LIST_VIEW_TIME = "boss_offline_one_channel_list_view_time";
	public static final String BOSS_OFFLINE_CLICK_MENU = "boss_offline_click_menu";
	public static final String BOSS_OFFLINE_CLICK_START_DOWNLOAD_BTN = "boss_offline_click_start_download_btn";
	public static final String BOSS_OFFLINE_ERROR_NO_NET = "boss_offline_error_no_net";
	public static final String BOSS_OFFLINE_CLICK_CANCEL_DOWNLOAD_BTN = "boss_offline_click_cancel_download_btn";
	public static final String BOSS_OFFLINE_DOWNLOAD_ALL_SUCCESS = "boss_offline_download_all_success";
	public static final String BOSS_OFFLINE_ENTER_ACTIVITY = "boss_offline_enter_activity";
	public static final String BOSS_OFFLINE_CLICK_READ_MORE = "boss_offline_click_read_more";
	public static final String BOSS_OFFLINE_ERROR_GET_DOWNLOAD_LIST_NO_NET = "boss_offline_error_get_download_list_no_net";
	public static final String BOSS_OFFLINE_ERROR_DOWNLOADER_T_ERROR = "boss_offline_error_downloader_t_error";

	public static final String BOSS_PUSH = "boss_push";
	public static final String BOSS_PUSH_CLICK_NOTIFICATION = "boss_push_click_notification";
	public static final String BOSS_PUSH_NOT_OPEN_BY_FIRST_INSTALL = "boss_push_not_open_by_first_install";
	public static final String BOSS_PUSH_UN_START = "boss_push_un_start";

	public static final String BOSS_SEND_MM_RESULT = "boss_send_mm_result";
	public static final String BOSS_SHARE_NEWS_RESULT = "boss_share_news_result";

	public static final String BOSS_IMAGE_DOWNLOAD = "boss_image_download";

	public static final String BOSS_VIEW_DETAIL = "boss_view_detail";
	public static final String KEY_VIEW_START = "start";
	public static final String KEY_VIEW_END = "end";

	//----------订阅,我的消息相关
	public static final String BOSS_RSS_CHLID = "boss_rss_chlid";
	public static final String BOSS_RSS_CAT_ID = "boss_rss_cat_id";
    public static final String BOSS_RSS_IS_LOGIN = "boss_rss_is_login";	
	public static final String BOSS_RSS_CLICK_TAB = "boss_rss_click_tab";
	public static final String BOSS_RSS_CLICK_ADD_BTN = "boss_rss_click_add_btn";
    public static final String BOSS_RSS_CLICK_CANCEL_BTN = "boss_rss_click_cancel_btn";
	public static final String BOSS_RSS_CLICK_MENU = "boss_rss_click_menu";
	public static final String BOSS_RSS_CLICK_READ_MORE_BTN = "boss_rss_click_read_more_btn";
	public static final String BOSS_RSS_CLICK_SEND_MSG_TO_WRITOR = "boss_rss_click_send_msg_to_writor";
	public static final String BOSS_RSS_CLICK_HISTORY = "boss_rss_click_history";
	public static final String BOSS_RSS_CLICK_MEDIA_CARD = "boss_rss_click_media_card";
	public static final String BOSS_RSS_SOMEONE_S_COUNT = "boss_rss_someone_s_count";
	public static final String BOSS_SETTING_LOGINFROM_RSS = "boss_setting_loginfrom_rss";
	
	public static final String BOSS_MY_COMMENT_CLICK_ORIGINAL_ARTICLE_BTN = "boss_my_comment_click_original_article_btn";
	public static final String BOSS_MY_COMMENT_CLICK_UP_ONE_COMMENT_BTN = "boss_my_comment_click_up_one_comment_btn";
	public static final String BOSS_MY_COMMENT_CLICK_TRAN_COMMENT_BTN = "boss_my_comment_click_tran_comment_btn";
	public static final String BOSS_MY_COMMENT_CLICK_COPY_BTN = "boss_my_comment_click_copy_btn";
	public static final String BOSS_MY_COMMENT_CLICK_DEL_BTN = "boss_my_comment_click_del_btn";	
	
	public static final String BOSS_AT_COMMENT_CLICK_ORIGINAL_ARTICLE_BTN = "boss_at_comment_click_original_article_btn";
	public static final String BOSS_AT_COMMENT_CLICK_UP_ONE_COMMENT_BTN = "boss_at_comment_click_up_one_comment_btn";
	public static final String BOSS_AT_COMMENT_CLICK_TRAN_COMMENT_BTN = "boss_at_comment_click_tran_comment_btn";
	public static final String BOSS_AT_COMMENT_CLICK_COPY_BTN = "boss_at_comment_click_copy_btn";	
	
	public static final String BOSS_COMMENT_LIST_CLICK_UP_ONE_COMMENT_BTN = "boss_comment_list_click_up_one_comment_btn";
	public static final String BOSS_COMMENT_LIST_CLICK_TRAN_COMMENT_BTN = "boss_comment_list_click_tran_comment_btn";
	public static final String BOSS_COMMENT_LIST_CLICK_COPY_BTN = "boss_comment_list_click_copy_btn";
	public static final String BOSS_COMMENT_LIST_CLICK_DEL_BTN = "boss_comment_list_click_del_btn";
	public static final String BOSS_COMMENT_LIST_CLICK_SETTOP_BTN = "boss_comment_list_click_settop_btn";
	public static final String BOSS_COMMENT_LIST_CLICK_SETNORMAL_BTN = "boss_comment_list_click_setnormal_btn";
	public static final String BOSS_COMMENT_LIST_CLICK_SENDMSG_BTN = "boss_comment_list_click_sendmsg_btn";
}
