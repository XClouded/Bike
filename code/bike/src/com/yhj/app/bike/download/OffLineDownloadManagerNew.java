package com.yhj.app.bike.download;
/*package com.tencent.news.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.tencent.mm.sdk.platformtools.Log;
import com.tencent.news.api.JsonParse;
import com.tencent.news.config.Constants;
import com.tencent.news.model.pojo.Item;
import com.tencent.news.model.pojo.OfflineChannel;
import com.tencent.news.model.pojo.OfflineItems;
import com.tencent.news.model.pojo.OfflinePreview;
import com.tencent.news.shareprefrence.SpOffline;
import com.tencent.news.shareprefrence.SpOfflineOneChannelTime;
import com.tencent.news.shareprefrence.SpOfflineProgress;
import com.tencent.news.utils.FileUtil;
import com.tencent.news.utils.SLog;

public class OffLineDownloadManagerNew implements DownloadListener {
	private static OffLineDownloadManagerNew instance;
	private static final int FINISH_DOWNLOAD_AND_START_PARSE = 0x1;
	private static final int FINISH_DOWNLOAD_AND_STOP_PARSE = 0x2;

	private static final int BUFFER_SIZE = 4096;

	private List<OfflinePreview> downloadedData = new ArrayList<OfflinePreview>();
	private LinkedBlockingQueue<OfflineChannel> queue = new LinkedBlockingQueue<OfflineChannel>();
	private List<OfflineChannel> mNeedDownload = null;
	private OffLineDownloadListener mListener = null;
	private int mTag = 0;
	private int TagNum = 0;
	// private int zipChildNum;
	// private int unzipFinishNum = 1;
	private String appid;
	private int state;
	private final ArrayList<Downloader> downloaders = new ArrayList<Downloader>();
	private boolean beCanceled = false;
	private Object clearCacheLock = new Object();
	private boolean clearCache = false;
	private boolean downloadAllFinish = false;
	private PhonyProgressUpdate p;
	private int phonyNumFromNet;
	private Downloader downloader;

	public static OffLineDownloadManagerNew getInstance() {
		if (instance == null) {
			instance = new OffLineDownloadManagerNew();
		}
		return instance;
	}

	public void addDownloadTask(List<OfflineChannel> needDownload) {
		downloadAllFinish = false;
		beCanceled = false;
		this.setClearCache(false);

		int len = needDownload.size();

		SLog.i("ol", "needDownload.size()--->" + needDownload.size());

		if (needDownload != null && len != 0) {
			TagNum = len;
			mNeedDownload = new ArrayList<OfflineChannel>();
			mNeedDownload.addAll(needDownload);
			state = DownloadConstants.T_DOWNLOAD;
			mTag = 0;
			// unzipFinishNum = 1;
			if (unZipAndParseThread == null) {
				unZipAndParseThread = new Thread(unZipAndParseRunnable);
				unZipAndParseThread.start();
			}
			startDownloadTask();
		}
	}

	public void addListener(OffLineDownloadListener listener) {
		if (listener != null) {
			mListener = listener;
		}
	}

	long startDown;

	private void startDownloadTask() {

		startDown = System.currentTimeMillis();

		phonyNumFromNet = 80 + (int) (Math.random() * 10);
		p = new PhonyProgressUpdate();
		p.setMaxNum(phonyNumFromNet);
		p.setPhonyNum(0);
		p.setRun(true);
		p.start();
		OfflineChannel oc = mNeedDownload.get(mTag);
		String url = oc.getPath();
		String filesize = oc.getSize();
		// zipChildNum =
		// Integer.parseInt(mNeedDownload.get(mTag).getFile_count());
		if (url != null && url.length() != 0 && filesize != null && filesize.length() != 0) {
			// 启动下载
			DownloadDBHelper dbHelper = DownloadDBHelper.getInstance();

			appid = SpOfflineProgress.getChannelProgressID(oc);
			if (appid != null && appid.length() > 0) {

			} else {
				appid = String.valueOf(System.currentTimeMillis());
				SpOfflineProgress.setChannelProgressID(oc, appid);
			}

			SLog.e("olprogress", "appid" + appid + ", oc=" + oc.getChlid());

			downloader = new Downloader(appid, url, "", dbHelper, "", this, "", filesize);
			downloaders.add(downloader);
			downloader.startDownload();

		}
	}

	public void stopDownloadTask() {
		Log.v("vincesun", "stop==================");
		beCanceled = true;
		// mTag = 0;
		if (downloader != null) {
			downloader.pauseDownload(true);
		}

		if (p != null) {
			p.setRun(false);
			p.exit();
		}
		downloadAllFinish = true;
		state = DownloadConstants.T_PAUSE;
		// if (mListener != null) {
		// mListener = null;
		// }
	}

	public void setClearCache(boolean isClearing) {
		synchronized (clearCacheLock) {
			clearCache = isClearing;
		}
	}

	public void doNotification() {
		DownloadNotificationManager.getInstance().showNotification(123456, DownloadConstants.T_UPDATE_PROGRESS, DownloadNotificationManager.OFFLINE_TYPE, "", "");
	}

	public void stopNotification() {
		if (downloadedData != null && downloadedData.size() > 0 && mNeedDownload != null && mNeedDownload.size() > 0) {
			DownloadNotificationManager.getInstance().showNotification(123456, DownloadConstants.T_INSTALL, DownloadNotificationManager.OFFLINE_TYPE, "", "");
		}
	}

	public void killNotification() {
		DownloadNotificationManager.getInstance().kill(123456);
	}

	private void unzipFile(OfflineChannel oc) {
		String fileName = Downloader.getLocalfileName(oc.getPath());
		File gzFile = new File(getFilePath(SpOffline.getChannelAppid(oc.getChlid()), fileName, ""));
		long startunzip = System.currentTimeMillis();
		Unzip(gzFile, Constants.CACHE_OFFLINE_PATH);
		SLog.i("olTime", "解压耗时" + (System.currentTimeMillis() - startunzip) + "ms");
		Log.v("vincesun", gzFile.exists() + "====================");
		if (gzFile.exists()) {
			gzFile.delete();
		}
	}

	public static String getFilePath(String appid, String fileName, String tmp) {
		return Downloader.DOWNLOAD_PATH + "_" + appid + "_" + fileName + tmp;
	}

	@Override
	public void onDownloadGetSizeFinish(Downloader downloader) {

	}

	@Override
	public void onDownloadInitFileError(Downloader downloader) {

	}

	@Override
	public void onDownloadBegin(Downloader downloader) {
		Log.v("vincesun", "offline::begin");

	}

	@Override
	public void onDownloadPause(Downloader downloader, int n_progress) {
		state = DownloadConstants.T_PAUSE;

		int surplus = TagNum - mTag - 1;

		if (p.getPhonyNum() > n_progress) {
			n_progress = p.getPhonyNum();
		}

		mListener.downloadProgressChanged(DownloadConstants.T_PAUSE, n_progress, mNeedDownload.get(mTag), surplus);
		mTag = 0;
	}

	@Override
	public void onDownloadWait(Downloader downloader) {

	}

	@Override
	public void onDownloadUpdate(Downloader downloader, String progress, int n_progress) {

		// String fileName =
		// Downloader.getLocalfileName(mNeedDownload.get(mTag).getPath());
		// File gztFile = new File(getFilePath(appid, fileName + ".temp", ""));
		// SLog.e("ol", "onDownloadUpdate压缩包" + fileName + ".temp存不存---->" +
		// gztFile.exists());

		if (!beCanceled) {
			state = DownloadConstants.T_UPDATE_PROGRESS;
		} else {
			return;
		}

		int surplus = TagNum - mTag - 1;
		Log.v("vincesun", "下载进度:::::" + String.valueOf(n_progress));
		if (p.getPhonyNum() > phonyNumFromNet || p.getPhonyNum() < n_progress) {
			p.setRun(false);
			p.exit();
			Log.v("vincesun", "结束假进度::" + p.getPhonyNum());
			if (mListener != null && p.getPhonyNum() < n_progress) {
				mListener.downloadProgressChanged(DownloadConstants.T_UPDATE_PROGRESS, n_progress, mNeedDownload.get(mTag), surplus);
			}
		}
		// SLog.i("ol", "onDownloadUpdate()------>" +
		// (DownloadConstants.T_UPDATE_PROGRESS ==
		// OffLineDownloadManagerNew.getInstance().getState()));
	}

	@Override
	public void onDownloadUpdate(List<Downloader> downloaders) {

	}

	@Override
	public void onDownloadError(Downloader downloader, int n_progress) {
		Log.v("vincesun", "offline::ERROR!!!!!!!!");
		p.setRun(false);
		p.exit();

		state = DownloadConstants.T_ERROR;
		beCanceled = true;
		int surplus = TagNum - mTag - 1;
		mListener.downloadProgressChanged(DownloadConstants.T_ERROR, n_progress, mNeedDownload.get(mTag), surplus);
	}

	@Override
	public void onDownloadFinish(Downloader downloader) {
		Log.v("ol", "下载完成");

		SLog.i("olTime", "下载一个压缩包耗时" + (System.currentTimeMillis() - startDown) + "ms");

		if (downloader.isDownloading()) {
			downloader.pauseDownload(true);
		}
		int surplus = TagNum - mTag - 1;
		SLog.i("ol", "mNeedDownload.get(mTag).getChlid()=" + mNeedDownload.get(mTag).getChlid() + ",mNeedDownload.get(mTag).getPath=" + mNeedDownload.get(mTag).getPath());
		SpOffline.setChannelAppid(mNeedDownload.get(mTag).getChlid(), appid);
		addParseTask(mNeedDownload.get(mTag));

		if (beCanceled) {
			return;
		}

		if (surplus > 0 && state != DownloadConstants.T_ERROR) {
			mTag++;
			// unzipFinishNum = 1;
			startDownloadTask();
		} else {
			if (mListener != null) {
				downloadAllFinish = true;
				state = DownloadConstants.T_INSTALL;
				if (mHandler != null) {
					mHandler.sendEmptyMessage(FINISH_DOWNLOAD_AND_STOP_PARSE);
				}
				mListener.downloadProgressChanged(DownloadConstants.T_INSTALL, 0, mNeedDownload.get(mTag), surplus);
			}
		}

	}

	@Override
	public void onDownloadDelete(Downloader downloader) {

	}

	@Override
	public void downloadUrlChangeError(Downloader downloader) {

	}

	@Override
	public void installError(Downloader downloader) {

	}

	@Override
	public void installSucceed(String appid, String packageName, Downloader downloader) {

	}

	@Override
	public void uninstallSucceed(String appid, String packageName) {

	}

	public class PhonyProgressUpdate extends Thread {
		public int phonyNum = 0;
		public boolean isRun;
		public int maxNum;

		public int getMaxNum() {
			return maxNum;
		}

		public void setMaxNum(int maxNum) {
			this.maxNum = maxNum;
		}

		public boolean isRun() {
			return isRun;
		}

		public void setRun(boolean isRun) {
			this.isRun = isRun;
		}

		public int getPhonyNum() {
			return phonyNum;
		}

		public void setPhonyNum(int phonyNum) {
			this.phonyNum = phonyNum;
		}

		public PhonyProgressUpdate() {
		}

		@Override
		public void run() {
			while (isRun) {
				try {
					if (!this.isInterrupted()) {
						if (phonyNum > maxNum) {
							isRun = false;
							this.interrupt();
						}
						phonyNum += 1;
						if (phonyNum > 100) {
							phonyNum = 100;
						}
						Log.v("vincesun", "phonyNum::::" + phonyNum);
						int surplus = TagNum - mTag - 1;
						if (mListener != null) {
							mListener.downloadProgressChanged(DownloadConstants.T_UPDATE_PROGRESS, phonyNum, mNeedDownload.get(mTag), surplus);
						}
						Thread.sleep(100);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		public void exit() {
			try {
				if (Thread.currentThread().isAlive()) {
					isRun = false;
					this.interrupt();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private void Unzip(File zip, final String dir) {

		String strEntry = null;
		try {

			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream(zip);
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
			ZipEntry entry = null;
			while ((entry = zis.getNextEntry()) != null) {

				strEntry = entry.getName();

				File entryFile = new File(dir + strEntry);

				if (entry.isDirectory()) {
					entryFile.mkdirs();

					// SLog.i("ol", "解压出一个文件___夹" + strEntry);

					// updateP();
					continue;

				} else {
					// SLog.i("ol", "解压出一个文件" + strEntry);
					FileUtil.makeDIRAndCreateFile(dir + strEntry);
				}

				int count;
				byte data[] = new byte[BUFFER_SIZE];
				FileOutputStream fos = new FileOutputStream(entryFile);
				dest = new BufferedOutputStream(fos, BUFFER_SIZE);
				while ((count = zis.read(data, 0, BUFFER_SIZE)) != -1) {
					dest.write(data, 0, count);
				}

				dest.flush();
				dest.close();
				// updateP();
			}
			zis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<OfflineChannel> getNeedDownload() {
		return mNeedDownload;
	}

	public List<OfflinePreview> getDownloadedData() {
		return downloadedData;
	}

	public void setDownloadedData(List<OfflinePreview> downloadedData) {
		this.downloadedData = downloadedData;
	}

	public void addParseTask(OfflineChannel c) {

		SLog.i("ol", "addParseTask()--->增加解析任务---->" + (c.getChlid()) + ",url=" + c.getPath());
		synchronized (this.queue) {
			queue.add(c);
		}
		if (mHandler != null) {
			mHandler.sendEmptyMessage(FINISH_DOWNLOAD_AND_START_PARSE);
		}
	}

	public void v2(OfflineChannel oc) {
		clearCacheIfNeeded();

		try {
			SLog.i("olTime", "v2()--->得到解压解析任务---->" + (oc.getChlid()));
			SpOfflineOneChannelTime.setUpdataChannelTime(oc.getChlid(), System.currentTimeMillis());
			unzipFile(oc);

			String json = FileUtil.readString(Constants.CACHE_OFFLINE_PATH + oc.getChlid());

			long sjiexi = System.currentTimeMillis();
			SLog.d("ol", "======================开始解析" + oc.getChlid());
			OfflineItems oi = JsonParse.parseOfflineLibWithoutArticlepool(json);
			SLog.d("ol", "======================完成解析" + oc.getChlid() + (System.currentTimeMillis() - sjiexi));

			List<Item> items = oi.orderByIdsAndSeparateItemSpecialReportAndBack5Items(oc.getChlid());

			SLog.i("olTime", "解析耗时" + (System.currentTimeMillis() - sjiexi) + "ms");

			OfflinePreview op = new OfflinePreview();
			op.setDownloadedChannel(oc.getChlid());
			op.setDownloadedItems(items);

			SLog.i("ol", "v2()--->解析完成一个频道");

			if (downloadedData != null) {
				if (downloadedData.size() > 0) {
					int location = downloadedData.indexOf(op);
					if (location < 0) {
						downloadedData.add(op);
					} else {
						downloadedData.set(location, op);
					}
				} else {
					downloadedData.add(op);
				}

				new Thread(new Runnable() {

					@Override
					public void run() {
						FileUtil.saveCache(new File(Constants.CACHE_OFFLINE_LIST_PATH), downloadedData);
					}
				}).start();
				if (mListener != null) {

					SLog.i("ol", "downloadAllFinish=" + downloadAllFinish + "queue.size()=" + queue.size() + ",beCanceled=" + beCanceled);
					
					SpOffline.setUpdataAllTime(System.currentTimeMillis());
					
					if ((downloadAllFinish && queue.size() == 0) || beCanceled) {
						SpOffline.setChannelVersion(oc);
						mListener.afterParse();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			clearCacheIfNeeded();
		}

	}

	private void clearCacheIfNeeded() {
		synchronized (clearCacheLock) {
			if (clearCache) {
				File cacheFile = new File(Constants.CACHE_OFFLINE_PATH);
				FileUtil.delete(cacheFile, true);
				SpOfflineOneChannelTime.delAll();
				return;
			}
		}
	}

	public int getmTag() {
		return mTag;
	}

	public void setmTag(int mTag) {
		this.mTag = mTag;
	}

	Handler mHandler;
	Thread unZipAndParseThread;
	Runnable unZipAndParseRunnable = new Runnable() {

		@Override
		public void run() {
			Looper.prepare();
			mHandler = new Handler(new IncomingHandlerCallback());
			Looper.loop();
		}
	};

	class IncomingHandlerCallback implements Handler.Callback {

		@Override
		public boolean handleMessage(Message msg) {

			SLog.i("ol", "===========收到消息==============" + msg.what);

			switch (msg.what) {
			case FINISH_DOWNLOAD_AND_START_PARSE:
				OfflineChannel oc;
				synchronized (queue) {
					oc = queue.poll();
				}
				if (oc != null) {
					v2(oc);
				}
				break;
			case FINISH_DOWNLOAD_AND_STOP_PARSE:
				if (unZipAndParseThread != null) {
					unZipAndParseThread.interrupt();
				}
				break;
			}
			return true;
		}
	}
}
*/