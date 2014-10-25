package com.yhj.app.bike.download;

/**
 *  下载器状态 及 页面状态（文字显示）
 * @author vincesun 
 * 
 */
public class DownloadConstants {
	
	//下载类型
	public static final int APP_DOWNLOAD_TYPE = 0x401;//应用墙下载
	public static final int VERSION_UPDATE_DOWNLOAD_TYPE = 0x402;//升级下载
	public static final int OUTLINE_DOWNLOAD_TYPE = 0x403;//离线下载
	
	//下载器状态
	public static final int DOWNLOAD_BEGAIN = 0x201;// 开始下载
	public static final int DOWNLOAD_PAUSE = 0x202;// 下载暂停
	public static final int DOWNLOAD_COMPLETED = 0x203;// 下载完成
	public static final int DOWNLOAD_UPDATE = 0x204;// 下载进度更新
	public static final int DOWNLOAD_ERROR = 0x205;// 下载错误
	public static final int INSTALL_SUCCESS = 0x206;// 安装成功
	public static final int INSTALL_ERROR = 0x207;// 安装出错
	public static final int DOWNLOAD_INIT_FILE_ERROR = 0x208;// 初始化文件出错
	public static final int DOWNLOAD_GET_FILE_SIZE_FINISH = 0x209;// 获取文件大小完成
	public static final int SDCARD_ERROR = 0x210; // sdcard出错或没内存了
	public static final int DOWNLOAD_URL_CHANGE = 0x211; //下载过程中下载地址变化,需用户重新点击下载
	
	//应用墙页面文字状态(文字显示)
	/**
	 * 显示:下载（本地没安装且未下载）
	 */
	public static final int T_DOWNLOAD = 0x301;
	/**
	 * 显示:继续下载 或 暂停（未下载完成,下载出错）
	 */
	public static final int T_PAUSE = 0x302;
	/**
	 * 显示:开启 或 打开应用（本地已安装）
	 */
	public static final int T_OPEN = 0x303;
	/**
	 * 显示:安装（下载完成）
	 */
	public static final int T_INSTALL = 0x304;
	/**
	 * 显示:更新（本地已安装且有新版本）
	 */
	public static final int T_UPDATE = 0x305;
	/**
	 * 显示:更新进度百分比（下载中）
	 */
	public static final int T_UPDATE_PROGRESS = 0x306;
	/**
	 * 显示：等待
	 */
	public static final int T_WAIT = 0x307;
	/**
	 * 显示：网络错误/下载错误
	 */
	public static final int T_ERROR = 0x308;
	
//====================下载器备用状态============================
//	public static final int PAUSE_BY_USER = 1;//用户主动暂停，表行为
//	public static final int PAUSE_BY_SYS = 0;//系统暂停
//	public static final int PAUSE_NONE = -1;//系统暂停
//	
}
