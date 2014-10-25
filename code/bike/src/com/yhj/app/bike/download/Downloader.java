package com.yhj.app.bike.download;
/*package com.tencent.news.download;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import com.tencent.news.config.Constants;
import com.tencent.news.system.Application;

*//**
 * 下载器
 * 
 *//*
public class Downloader {

	public String appId;
	public long fileSize; // 文件总大小
	public String packageName;// 文件包名
	public long curCompleteSize;// 现在总完成大小
	public double phonyPercent; // 已下载的假进度百分比
	public double maxPhonyPercent = 10 + (Math.random() * 9); // 最大的假进度(在10-18之间随机1个)
	public int netWorkType = -1;
	public String pushTitle = "";
	public String pushContent = "";
	public int pushType;
	public String pushIcon = "";
	private double curProgress = 0;
	private String curSProgress = "0%";
	public String sendTime = "";
	public String feedType = "";
	public int pauseType = 0;
	public int lastNetworkType = -1;
	public String viaString = "";

	public String urlStr;// 下载的地址ַ
	private final String localTempFilePath;// 临时文件保存路径
	private final String localRealFilePath;// 真实文件保存路径
	private final String fileName;// 文件名字
	private final DownloadDBHelper dbHelper;// 数据库
	private File tmpHolder;
	private boolean isNoticeBand;
	private int curSNProgress;
	private String version = "";
	private String mFilesize;
	private boolean isOffLine = false;

	private volatile List<DownloadDataInfo> infos;// 存放下载信息类的集合

	private ArrayList<DownloadThread> downloadThreads = new ArrayList<DownloadThread>();
	private ConcurrentLinkedQueue<DownloadListener> listeners = new ConcurrentLinkedQueue<DownloadListener>();

	// private int downloadState = DownloadConstants.T_PAUSE;//PAUSE
	private int downloadState;

	public static final String DOWNLOAD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Tencent" + File.separator + "Tencentnews" + File.separator + "market"
			+ File.separator;

	private final int threadcount = DEFAULT_THREAD_COUNT;// 线程数
	public static final int DEFAULT_THREAD_COUNT = 1;// 默认启动下载线程块
	private int curThreadCount = 0;

	private ArrayList<String> sendTimes = new ArrayList<String>();
	private StringBuilder sendTimeJson = new StringBuilder();

	private DownloadListener listener;

	public Downloader(String appId, String urlstr, String packageName, DownloadDBHelper db, String appName, DownloadListener listener, String version, String filesize) {
		this.appId = appId;
		this.urlStr = urlstr;
		Log.v("onNetworkConnect", urlstr);
		String fileName = getLocalfileName(urlstr);
		this.localTempFilePath = getFilePath(appId, fileName, ".temp");
		this.localRealFilePath = getFilePath(appId, fileName, "");
		this.fileName = fileName;
		this.packageName = packageName;
		this.dbHelper = db;
		this.pushTitle = appName;
		// this.pushType = dType;
		// this.sendTime = sendTime;
		this.listener = listener;
		this.pushContent = version;
		this.mFilesize = filesize;

	}

	public void startDownload() {
		DownloadManager.getInstance().initListener();
		clearThread();
		addSendTime(sendTime);
		// pauseType = PAUSE_NONE;
		lastNetworkType = DownloadManager.getInstance().getNetworkType();

		notifyListener(DownloadConstants.DOWNLOAD_BEGAIN, this);

		if (mFilesize != null && !mFilesize.equals("")) {
			isOffLine = true;
		} else {
			isOffLine = false;
		}

		// 没有sd卡
		if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			downloadState = DownloadConstants.SDCARD_ERROR;
			notifyListener(DownloadConstants.DOWNLOAD_INIT_FILE_ERROR, this);
			return;
		}

		// 如果文件已经存在则不再下载，直接提示下载完成
		if (isFileExists()) {
			complete();
			return;
		}

		// 得到数据库中已有的urlstr的下载器的具体信息
		infos = dbHelper.getInfosByAppId(appId);

		// 初始化网络监听
		DownloadManager.getInstance().initListener();
		if (netWorkType <= -1) {
			netWorkType = DownloadManager.getInstance().getNetworkType();
		}

		if (!isStartDownload()) {

			// 先更新一下进度条，免得因为走完假进度暂停的任务再下载时
			// 因为创建HTTP通道而等待很久才出现进度条
			// if (phonyPercent >= maxPhonyPercent) {
			//
			// notifyListener(eventId.DOWNLOAD_UPDATE);
			// }

			// 先保存数据库，再去下载
			for (int i = 0; i < threadcount; i++) {
				DownloadDataInfo info = new DownloadDataInfo(i, 0, 0, 0, urlStr, appId, packageName, String.valueOf(phonyPercent), netWorkType, pushTitle, pushContent, pushType, pushIcon, sendTime);
				infos.add(info);
			}

			// 保存infos中的数据到数据库
			dbHelper.addInfos(infos);
			if (!mFilesize.equals("")) {
				fileSize = Long.parseLong(mFilesize);
				try {
					File file = new File(localTempFilePath);
					if (file != null) {
						if (file.getParent() != null && !file.getParentFile().exists()) {
							file.getParentFile().mkdirs();
							file.delete();
							if (!file.exists())
								file.createNewFile();
						}
					}

					// 本地访问文件
					RandomAccessFile accessFile = new RandomAccessFile(file, "rwd");
					accessFile.setLength(fileSize);
					accessFile.close();
				} catch (Exception e) {
				}
				curCompleteSize = 0;
				download();
			} else {
				initFileSize();
			}

		} else {
			File file = new File(localTempFilePath);
			if (!file.exists()) { // 当异常情况下载了一半的文件被删除时应该重新下载
				dbHelper.deleteInfoByAppId(appId);
				curCompleteSize = 0;
				phonyPercent = 0;

				Log.v("onNetworkConnect", "+++++当异常情况下载了一半的文件被删除时应该重新下载+++++");
				// notifyListener(eventId.DOWNLOAD_UPDATE);
				startDownload();
				return;
			}

			Log.v("onNetworkConnect", "+++++数据库中找到下载信息，继续下载+++++");
			long size = 0;
			long compeleteSize = 0;
			for (DownloadDataInfo info : infos) {
				compeleteSize += info.getCompeleteSize();
				size += info.getEndPos() - info.getStartPos() + 1; // 计算fileSize
				phonyPercent = Double.valueOf(info.getPhonyPercent());
			}
			curCompleteSize = compeleteSize;
			fileSize = size;
			curSProgress = getDownloadingText(fileSize, compeleteSize);
			curSNProgress = getDownloadPercent(fileSize, compeleteSize);

			download();
		}

	}

	private boolean isStartDownload() {
		if (infos == null || infos.size() == 0)
			return false;

		long size = 0;
		for (DownloadDataInfo info : infos) {
			size += info.getEndPos();
		}

		// 终于找到数据被重复添加的原因！！getEndPos()的数据是在initfilesize中创建完HTTP通道之后获取完filesize才更新的
		// 创建HTTP通道有时间延时，如果获取filesize失败的话，getEndPos永远是0，此时就会返回false，之后在startdown中
		// 又会往infos（info本身已经有一条数据）添加一条记录，导致数据被重复添加！
		if (size == 0) {
			// 清掉之前的数据
			DownloadDBHelper.getInstance().deleteInfoByAppId(appId);
			infos.clear();// 清空列表，防止数据重复添加
		}
		return (size > 0);
	}

	*//**
	 * 
	 *//*
	private void initFileSize() {
		new Thread() {
			@Override
			public void run() {
				try {
					HttpURLConnection connection = DownloadConnectionHelper.getHttpConnection(urlStr, true, true, 5000, 2 * 60 * 1000, null, true);

					fileSize = connection.getContentLength();
					connection.disconnect();

					String sdCardState = Environment.getExternalStorageState();
					if (!Environment.MEDIA_MOUNTED.equals(sdCardState) || (1024 * 10 + getAvailaleSize()) <= fileSize) {

						downloadState = DownloadConstants.SDCARD_ERROR;
						// ===========!!!
						// notifyListener(DownloadConstants.DOWNLOAD_INIT_FILE_ERROR,this);
						return;
					}

					File file = new File(localTempFilePath);
					if (file != null) {
						if (file.getParent() != null && !file.getParentFile().exists()) {
							file.getParentFile().mkdirs();
							file.delete();
							if (!file.exists())
								file.createNewFile();
						}
					}

					// 本地访问文件
					RandomAccessFile accessFile = new RandomAccessFile(file, "rwd");
					accessFile.setLength(fileSize);
					accessFile.close();
				} catch (Exception e) {
					// Log.v(TAG, "initFileSize RUN " + e);
					if (DownloadManager.getInstance().isNetworkCneted()) {
						downloadError();
					}
					// e.printStackTrace();
					// WNSLog.e(TAG, "" + e.getMessage());
					return;
				}
				// ========!!!
				// notifyListener(eventId.DOWNLOAD_GET_FILE_SIZE_FINISH);

				long range = fileSize / threadcount;
				// infos = new ArrayList<DownloadDataInfo>();

				for (int i = 0; i < threadcount - 1; i++) {
					DownloadDataInfo info = infos.get(i);
					info.setStartPos(i * range);
					info.setEndPos((i + 1) * range - 1);

					dbHelper.updataPieceInfoByAppId(info);
				}

				DownloadDataInfo info = infos.get(threadcount - 1);
				info.setStartPos((threadcount - 1) * range);
				info.setEndPos(fileSize - 1);
				dbHelper.updataPieceInfoByAppId(info);
				curCompleteSize = 0;
				download();
			}
		}.start();
	}

	*//**
	 * 利用线程开始下载数据
	 *//*
	private void download() {
		if (infos != null) {
			for (DownloadDataInfo info : infos) {
				if (isPause()) {
					break;
				}
				long infoStartPos = info.getStartPos();
				long infoEndPos = info.getEndPos();
				long infoCompleteSize = info.getCompeleteSize();
				if (infoStartPos + infoCompleteSize <= infoEndPos) {
					if (curThreadCount < threadcount) {
						curThreadCount++;
						DownloadThread mt = new DownloadThread(info.getThreadId(), infoStartPos, infoEndPos, infoCompleteSize, this);
						downloadThreads.add(mt);
						mt.start();
					}
				}
			}
		}
	}

	public class DownloadThread extends Thread {
		private final int threadId;
		private final long startPos;
		private final long endPos;
		private long compeleteSize;
		private Downloader downloader;
		private HttpURLConnection connection = null;
		private RandomAccessFile randomAccessFile = null;
		private InputStream is = null;

		public DownloadThread(int threadId, long startPos, long endPos, long compeleteSize, Downloader downloader) {
			this.threadId = threadId;
			this.startPos = startPos;
			this.endPos = endPos;
			this.compeleteSize = compeleteSize;
			this.downloader = downloader;
		}

		@Override
		public void run() {
			try {

				if (isPause()) {
					return;
				}

				if (tmpHolder == null) {
					tmpHolder = new File(localTempFilePath);
				}

				// //先更新一下进度条，免得因为走完假进度暂停的任务再下载时
				// //因为创建HTTP通道而等待很久才出现进度条
				// if (phonyPercent >= maxPhonyPercent) {
				//
				// notifyListener(eventId.DOWNLOAD_UPDATE);
				// }
				if (isOffLine) {
					connection = DownloadConnectionHelper.getHttpConnection(urlStr, true, true, 10000, 2 * 60 * 1000, "bytes=" + (startPos + compeleteSize) + "-", true);
				} else {
					connection = DownloadConnectionHelper.getHttpConnection(urlStr, true, true, 10000, 2 * 60 * 1000, "bytes=" + (startPos + compeleteSize) + "-" + endPos, true);
				}

				if (isPause()) {

					return;
				}

				randomAccessFile = new RandomAccessFile(localTempFilePath, "rwd");
				randomAccessFile.seek(startPos + compeleteSize);
				// 将要下载的文件写到保存在保存路径下的文件中

				is = connection.getInputStream();
				byte[] buffer = new byte[20240];// 20240
				int length = -1;
				while ((length = is.read(buffer)) != -1 && !this.isInterrupted()) {
					if (isPause()) {
						break;
					}

					if (tmpHolder != null && !tmpHolder.exists()) {
						pause();
						break;
					}
					
					randomAccessFile.write(buffer, 0, length);
					compeleteSize += length;
					// 更新数据库中的下载信息
					dbHelper.updataPieceInfoByAppId(threadId, compeleteSize, appId, sendTimeJson.toString());
					// 用消息将下载信息传给进度条，对进度条进行更新
					curCompleteSize += length;
					curSProgress = getDownloadingText(fileSize, compeleteSize);
					curSNProgress = getDownloadPercent(fileSize, compeleteSize);
					notifyListener(DownloadConstants.DOWNLOAD_UPDATE, downloader);

				}
			} catch (Exception e) {
				e.printStackTrace();
				//downloadError();
				if (downloadThreads.contains(this)) {

					downloadError();
				}

			} finally {
				try {
	//				downloadThreads.remove(this);
					disconnect();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (isOffLine && downloadState != DownloadConstants.DOWNLOAD_COMPLETED && downloadState != DownloadConstants.DOWNLOAD_PAUSE && downloadState != DownloadConstants.DOWNLOAD_ERROR) {
				File tempFile = new File(localTempFilePath);
				if (tempFile.exists()) {
					File f = new File(localRealFilePath);
					if (f.exists()) {
						f.delete();
					}
					if (tempFile.renameTo(f)) { // 文件重命名,将temp文件修改
						Log.v("vincesun", "转1");
						complete();
					} else {
						downloadError();
					}
				}
				return;
			}

			if (curCompleteSize >= fileSize && downloadState != DownloadConstants.DOWNLOAD_COMPLETED && !isOffLine) {// 下载完成curCompleteSize
																														// >=
																														// fileSize
																														// &&
				File tempFile = new File(localTempFilePath);
				if (tempFile.exists()) {
					File f = new File(localRealFilePath);
					if (f.exists()) {
						f.delete();
					}
					if (tempFile.renameTo(f)) { // 文件重命名,将temp文件修改
						Log.v("vincesun", "转2");
						complete();
					} else {
						downloadError();
					}
				}
				return;
			}
		}

		public void exit() {
			try {
				if (Thread.currentThread().isAlive()) {
					downloadState = DownloadConstants.DOWNLOAD_PAUSE;
					downloadThreads.remove(this);
					this.interrupt();
					disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void disconnect() {
			try {
				if (is != null) {
					is.close();
					is = null;
				}

				if (randomAccessFile != null) {
					randomAccessFile.close();
					randomAccessFile = null;
				}

				if (connection != null) {
					connection.disconnect();
					connection = null;
				}

				if (curThreadCount > 0) {
					curThreadCount--;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void delFile() {
		// 删掉真实安装包
		File f = new File(localRealFilePath);
		if (f.exists()) {
			f.delete();
		}

		// 删掉零时文件
		f = new File(localTempFilePath);
		if (f.exists()) {
			f.delete();
		}

		curCompleteSize = 0;
		phonyPercent = 0;
		curProgress = 0;
	}

	public synchronized void complete() {
		Log.v("vincesun", "++++++++++++++++++++++++DM++++COM");
		notifyListener(DownloadConstants.DOWNLOAD_COMPLETED, this);

		if(this.getAppId().equals(Constants.APPID)){
			return ;
		}
		// 已经下载成功，从队列里删除!!!!!
		// DownloadManager.getInstance().addCompletedItem(this);
		// DownloadManager.getInstance().removeDownloader(appId);
		if (!isOffLine) {
			List<DownloadDataInfo> infos = DownloadDBHelper.getInstance().getInfosByAppId(appId);
			if (infos.size() == 0) {
				DownloadDataInfo info = new DownloadDataInfo(0, 0, 1, 1, urlStr, appId, packageName, "0", netWorkType, pushTitle, pushContent, pushType, pushIcon, sendTime);
				infos.add(info);
				dbHelper.addInfos(infos);
			}

			String localAPKPath;
			localAPKPath = getRealLocalfilePath();
			// 包名为空时表示安装包无效
			String apkPackageName = DownloadAppUtil.getApkName(Application.getInstance().getApplicationContext(), localAPKPath);
			if (apkPackageName != null && DownloadAppUtil.installApp(Application.getInstance().getApplicationContext(), localAPKPath)) {
				setState(DownloadConstants.INSTALL_SUCCESS);
				return;
			} else {
				// downloadState = INSTALL_ERROR;
				// 安装出错
				notifyListener(DownloadConstants.INSTALL_ERROR, this);
			}
		}

	}

	// 设置暂停
	public synchronized void pause() {
		// downloadState = PAUSE;
		notifyListener(DownloadConstants.DOWNLOAD_PAUSE, this);
	}

	// 下载出错
	public void downloadError() {

		downloadState = DownloadConstants.DOWNLOAD_ERROR;
		notifyListener(DownloadConstants.DOWNLOAD_ERROR, this);
	}

	// 重置下载状态״̬
	public synchronized void reset() {
		// downloadState = INIT;
	}

	public synchronized int getState() {
		return downloadState;
	}

	public synchronized void setState(int downloadState) {
		this.downloadState = downloadState;
	}

	*//**
	 * 判断是否正在下载
	 *//*
	public synchronized boolean isDownloading() {
		return downloadState == DownloadConstants.DOWNLOAD_BEGAIN || downloadState == DownloadConstants.DOWNLOAD_UPDATE;
	}

	*//**
	 * 判断是否暂停
	 *//*
	public synchronized boolean isPause() {
		return downloadState == DownloadConstants.DOWNLOAD_PAUSE || downloadState == DownloadConstants.DOWNLOAD_ERROR || downloadState == DownloadConstants.SDCARD_ERROR;
	}

	*//**
	 * 判断是否完成
	 *//*
	public synchronized boolean isComplete() {
		return downloadState == DownloadConstants.DOWNLOAD_COMPLETED;
	}

	*//**
	 * 返回真正下载文件路径
	 * 
	 * @return
	 *//*
	public String getRealLocalfilePath() {
		return localRealFilePath;
	}

	*//**
	 * 返回临时下载文件路径
	 * 
	 * @return
	 *//*
	public String getTempLocalfilePath() {
		return localTempFilePath;
	}

	public String getFileName() {
		return fileName;
	}

	*//**
	 * 查询sdcard剩余内存,单位byte
	 * 
	 * @return
	 *//*
	private long getAvailaleSize() {
		File path = Environment.getExternalStorageDirectory(); // 取得sdcard文件路径
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return availableBlocks * blockSize;
	}

	public JSONObject toJSONObject() {
		JSONObject object1 = new JSONObject();
		try {
			object1.put("downloadState", downloadState);
			int progress = getDownloadPercent(fileSize, curCompleteSize);
			object1.put("pro", progress);
			object1.put("phony_pro", phonyPercent);
			object1.put("appid", appId);
			object1.put("sendtime", sendTimeJson.toString());

		} catch (JSONException e) {
			// e.printStackTrace();

		}
		return object1;
	}

	
	 * Helper function to build the downloading text.
	 
	public static String getDownloadingText(long totalBytes, long currentBytes) {
		int progress = getDownloadPercent(totalBytes, currentBytes);
		StringBuilder sb = new StringBuilder();
		sb.append(progress);
		sb.append('%');
		return sb.toString();
	}

	public static int getDownloadPercent(long totalBytes, long currentBytes) {
		if (totalBytes <= 0) {
			return 0;
		}

		if (currentBytes > totalBytes) {
			currentBytes = totalBytes;
		}
		int percent = (int) (currentBytes * 100 / totalBytes);
		return percent;
	}

	*//**
	 * 通过下载url生成一个文件名
	 * 
	 * @param downloadUrl
	 * @return
	 *//*
	public static String getLocalfileName(String downloadUrl) {
		int endN = downloadUrl.indexOf("?");
		if (endN != -1) {
			downloadUrl = downloadUrl.substring(0, endN);
		}
		int startN = downloadUrl.lastIndexOf("/");
		String fileName = downloadUrl.substring(startN + 1, downloadUrl.length());
		return fileName;
	}

	public void installCallBack(int action) {
		switch (action) {
		case DownloadConstants.INSTALL_SUCCESS:
			// downloadState = INSTALL_COMPLETE;
			notifyListener(DownloadConstants.INSTALL_SUCCESS, this);

			DownloadDBHelper dbHelper = DownloadManager.getInstance().getDownloadDBHelper();
			dbHelper.deleteInfoByAppId(appId);
			// DownloadManager.getInstance().removeDownloader(appId);
			// DownloadManager.getInstance().removeComplatedItem(appId);
			// delFile();
			break;
		case DownloadConstants.INSTALL_ERROR:
			// downloadState = INSTALL_ERROR;
			notifyListener(DownloadConstants.INSTALL_ERROR, this);
			break;
		}
	}

	public synchronized double getCurProgress() {
		return curProgress;
	}

	public synchronized void setCurProgress(double curProgress) {
		this.curProgress = curProgress;
	}

	public synchronized double getProgress() {

		return Math.max(phonyPercent, getDownloadPercent(fileSize, curCompleteSize));
	}

	public boolean isFileExists() {
		return new File(localRealFilePath).exists();
	}

	public ArrayList<String> getSendTimes() {
		return sendTimes;
	}

	public void addSendTime(String sendTime) {
		if (!sendTimes.contains(sendTime)) {
			this.sendTimes.add(sendTime);

			if (sendTimeJson.length() == 0) {

				sendTimeJson.append(sendTime);
			} else {

				sendTimeJson.append(",");
				sendTimeJson.append(sendTime);
			}
		}
	}

	public StringBuilder getSendTimeJson() {
		return sendTimeJson;
	}

	public static String getFilePath(String appid, String fileName, String tmp) {
		return Downloader.DOWNLOAD_PATH + "_" + appid + "_" + fileName + tmp;
	}

	

	public int getCurThreadCount() {
		return curThreadCount;
	}

	public void setCurThreadCount(int curThreadCount) {
		this.curThreadCount = curThreadCount;
	}

	public void setListener(DownloadListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	public boolean removeListener(DownloadListener listener) {
		return listeners.remove(listener);
	}

	public void clearListener() {
		listeners.clear();
	}

	public void setNoticeBand(boolean b) {
		isNoticeBand = b;
	}

	public void notifyListener(final int code, final Downloader downloader) {

		Application.getInstance().runOnUIThread(new Runnable() {
			@Override
			public void run() {
				switch (code) {
				case DownloadConstants.DOWNLOAD_BEGAIN:

					listener.onDownloadUpdate(downloader, curSProgress, curSNProgress);
					break;

				case DownloadConstants.DOWNLOAD_PAUSE:
					listener.onDownloadPause(downloader, curSNProgress);
					break;

				case DownloadConstants.DOWNLOAD_COMPLETED:
					listener.onDownloadFinish(downloader);
					break;

				case DownloadConstants.DOWNLOAD_UPDATE:
					listener.onDownloadUpdate(downloader, curSProgress, curSNProgress);
					break;

				case DownloadConstants.DOWNLOAD_ERROR:
					listener.onDownloadError(downloader, curSNProgress);
					break;

				case DownloadConstants.INSTALL_SUCCESS:
					listener.installSucceed(appId, packageName, downloader);
					break;

				case DownloadConstants.INSTALL_ERROR:
					listener.installError(downloader);
					break;

				case DownloadConstants.DOWNLOAD_INIT_FILE_ERROR:
					break;

				case DownloadConstants.DOWNLOAD_GET_FILE_SIZE_FINISH:
					break;

				default:
					break;
				}
			}
		});
	}

	private void clearThread() {
		int len = downloadThreads.size();
		for(int i = 0; i< len; i++){
			downloadThreads.get(i).exit();
		}
		
	}

	*//**
	 * 暂停下载
	 **//*
	public synchronized void pauseDownload(boolean isShowNotification) {
		// if (!isDownloading()) {
		// return;
		// }
		
		if(isShowNotification){
			downloadState = DownloadConstants.DOWNLOAD_PAUSE;
			notifyListener(DownloadConstants.DOWNLOAD_PAUSE, this);
		}else{
			DownloadNotificationManager.getInstance().kill(Integer.parseInt(this.getAppId()));
		}
		clearThread();
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUrlStr() {
		return urlStr;
	}

	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPushTitle() {
		return pushTitle;
	}

	public void setPushTitle(String pushTitle) {
		this.pushTitle = pushTitle;
	}

	public String getPushContent() {
		return pushContent;
	}

	public void setPushContent(String pushContent) {
		this.pushContent = pushContent;
	}
}
*/