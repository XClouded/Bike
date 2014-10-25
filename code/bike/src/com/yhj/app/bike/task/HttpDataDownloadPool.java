package com.yhj.app.bike.task;

import java.util.ArrayList;

import com.yhj.app.bike.R;
import com.yhj.app.bike.command.HttpDataRequest;
import com.yhj.app.bike.command.HttpDataResponse;
import com.yhj.app.bike.command.HttpTagDispatch;
import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.http.HttpEngine;
import com.yhj.app.bike.http.HttpEngine.HttpCode;
import com.yhj.app.bike.model.OrgData;
import com.yhj.app.bike.model.pojo.HttpCryptResult;
import com.yhj.app.bike.model.pojo.HttpInfo;
import com.yhj.app.bike.model.pojo.HttpResult;
import com.yhj.app.bike.system.Application;
import com.yhj.app.bike.utils.SLog;

/**
 * 数据下载线程池
 * 
 * @author haiyandu
 * @since 2012-3-27
 */
public class HttpDataDownloadPool {
	private static int jsonIndex = 1000;
	private static final int POOL_SIZE = 2;
	private static final int JSON_MAX_INDEX = 10000;
	private static HttpDataDownloadPool instance = null;
	private ArrayList<HttpInfo> mHttpPrepareQueue = new ArrayList<HttpInfo>();
	//HTTP进程数组
	private HttpInfo[] mHttpProcessingArray = new HttpInfo[POOL_SIZE];
	private HttpThread[] mHttpThread = new HttpThread[POOL_SIZE];
	private boolean[] mProcessingState = { false, false };
	private static final int MAX_PREPARE_NUM = 20;
	private static final long MAX_TIME = 5 * 60 * 1000;

	public HttpDataDownloadPool(){
		
	}
	
	public static synchronized HttpDataDownloadPool getInstance() {
		if (instance == null) {
			instance = new HttpDataDownloadPool();
		}
		return instance;
	}

	public void addTask(HttpDataRequest request, HttpDataResponse response) {
		synchronized (mHttpPrepareQueue) {
			HttpInfo httpinfo = new HttpInfo(request, response);
			long thistime = System.currentTimeMillis();
			httpinfo.setStartTime(thistime);
			// 添加到任务队列中，添加到队头
			mHttpPrepareQueue.add(0,httpinfo);
			int length = mHttpPrepareQueue.size();
			if (length >= MAX_PREPARE_NUM) {

				HttpInfo removeInfo = mHttpPrepareQueue.remove(length-1);
				// 系统繁忙，任务自动被取消，返回ERROR_NET_ACCESS错误
				onRecvError(removeInfo.getRequest(), removeInfo.getResponse(), HttpCode.SYSTEM_CANCELLED, getMessage(R.string.string_http_data_busy));
			}

			// 超过五分钟的请求全部消除
			//从后往前循环检测，即开始检测先加入的，每次新加入的都置于列表的开始
			for (int i = mHttpPrepareQueue.size() - 1; i >= 0; i--) {
				long time = mHttpPrepareQueue.get(i).getStartTime();
				if (Math.abs(thistime - time) > MAX_TIME) {
					HttpInfo removeInfo = mHttpPrepareQueue.remove(i);
					// 系统繁忙，任务自动被取消，返回ERROR_NET_ACCESS错误
					onRecvError(removeInfo.getRequest(), removeInfo.getResponse(), HttpCode.ERROR_NET_ACCESS, getMessage(R.string.string_http_data_connent_timeout));
					
					
				}
			}
		}
		
		//每次添加任务启动两个线程处理请求（0,2）
		for (int i = 0; i < POOL_SIZE; i++) {
			if (mProcessingState[i] == false) {
				// 开始下载任务
				mProcessingState[i] = true;
				mHttpThread[i] = new HttpThread(i);
				mHttpThread[i].setPriority(Constants.THREAD_PRIORITY);
				mHttpThread[i].start();
				break;
			}
		}
	}

	private HttpInfo pullOneTaskInHeadSync() {
		synchronized (mHttpPrepareQueue) {
			if (mHttpPrepareQueue.size() == 0) {
				return null;
			}
			HttpInfo headInfo = mHttpPrepareQueue.remove(0);
			return headInfo;
		}
	}

	/**
	 * 退出系统时使用
	 */
	public void waitThreadExit() {
		for (int i = 0; i < POOL_SIZE; i++) {
			try {
				if (mHttpThread[i] != null)
					mHttpThread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 退出系统时使用
	 * 
	 * @author haiyandu
	 * @since 2012-3-27
	 */
	public void cancelAllThread() {
		if (mHttpPrepareQueue != null && mHttpPrepareQueue.size() > 0) {
			// 备份一份数据，清除原始队列，通知所有http请求失败。
			synchronized (mHttpPrepareQueue) {
				ArrayList<HttpInfo> backupQueue = new ArrayList<HttpInfo>();
				backupQueue.addAll(mHttpPrepareQueue);
				mHttpPrepareQueue.clear();
				for (HttpInfo hi : backupQueue) {
					onRecvCancelled(hi.getRequest(), hi.getResponse());
				}
			}
		}
		for (int i = 0; i < POOL_SIZE; i++) {
			if (mProcessingState[i] && mHttpThread[i] != null) {
				// 这里的Cancel会继续运行当前任务，运行完成后会停止,所以不需要通知Error。
				mHttpThread[i].cancel();
			}
			mProcessingState[i] = false;
		}
	}

	private String getMessage(int res) {
		return Application.getInstance().getString(res);
	}

	private void onRecvOK(final HttpDataRequest request, final HttpDataResponse response, final HttpCode retCode, final Object result) {
		
		SLog.i("cjz","onRecvOK request back:url-->"+request.getUrl());
		
		
		Application.getInstance().runOnUIThread(new Runnable() {
			@Override
			public void run() {
				
				SLog.i("cjz","onRecvOK runOnUIThread request back:url-->"+request.getUrl());
				
				response.onHttpRecvOK(request, result);
			}
		});
	}

	private void onRecvError(final HttpDataRequest request, final HttpDataResponse response, final HttpCode retCode, final String msg) {
		
		SLog.i("cjz","onRecvError request back:url-->"+request.getUrl());
		
		Application.getInstance().runOnUIThread(new Runnable() {

			@Override
			public void run() {
				
				SLog.i("cjz","onRecvError runOnUIThread request back:url-->"+request.getUrl());
				
				response.onHttpRecvError(request, retCode, msg);
			}
		});
	}

	private void onRecvCancelled(final HttpDataRequest request, final HttpDataResponse response) {
		
		
		Application.getInstance().runOnUIThread(new Runnable() {
			@Override
			public void run() {
				response.onHttpRecvCancelled(request);
			}
		});
	}

	private class HttpThread extends Thread {

		private int runId = 0;
		private boolean cancel = false;

		public HttpThread(int runId) {
			super();
			this.runId = runId;
		}

		public void cancel() {
			this.cancel = true;
		}

		@Override
		public void run() {
			// Auto-generated method stub
			mProcessingState[runId] = true;
			while (!cancel) {
				//取出队头任务
				mHttpProcessingArray[runId] = pullOneTaskInHeadSync();

				if (mHttpProcessingArray[runId] == null) {
					break;
				}

				final HttpDataRequest request = mHttpProcessingArray[runId].getRequest();
				HttpDataResponse response = mHttpProcessingArray[runId].getResponse();
				HttpResult result = null;

				HttpEngine httpEngine = HttpEngine.getHttpEngine(request);

				/**
				 * 完全从网络取数据
				 */

				try {
				    result = httpEngine.execute();
				} catch (Exception e) {
				    result = null;
				    e.printStackTrace();
				}
				
				if (result == null) {
					onRecvError(request, response, HttpCode.ERROR_NET_ACCESS, getMessage(R.string.string_http_data_busy));
					continue;
				}

				if(request.isCancelled()) {
					onRecvCancelled(request, response);
					continue;
				}
				
				if (result.getResultCode() == HttpCode.STATUS_OK && result.getData() != null) {

					try {
						// 网络访问成功
						SLog.v("****网络访问成功");
						String json = new String(result.getData());
						
						json = json.trim();
						printJSONString(json);
						
						OrgData data = null;
						
						if (result instanceof HttpCryptResult) {
							data = ((HttpCryptResult)result).getOrgData();
						}
						
						final Object jsonResult = HttpTagDispatch.dispatch(request, data);
						if (jsonResult != null) {
							onRecvOK(request, response, HttpCode.STATUS_OK, jsonResult);
						} else {
							onRecvError(request, response, HttpCode.ERROR_NET_ACCESS, getMessage(R.string.string_http_data_fail));
						}
						continue;
					} catch (Exception e) {
						SLog.e(e.toString(), e);
						onRecvError(request, response, HttpCode.ERROR_NET_ACCESS, getMessage(R.string.string_http_data_fail));
						continue;
					}

				} else {
					// 列举所有网络错误
					int resId = R.string.string_http_data_busy;
					switch (result.getResultCode()) {
					case STATUS_OK:
						/**
						 * 返回状态正确，但是没有数据，也认为是访问失败了
						 */
						result.setResultCode(HttpCode.ERROR_NET_ACCESS);
					case ERROR_NO_CONNECT:
						resId = R.string.string_http_data_nonet;
						break;
					case ERROR_NO_REGISTER:
						resId = R.string.string_http_data_user_nocheck;
						break;
					case ERROR_NET_TIMEOUT:
						resId = R.string.string_http_data_connent_timeout;
						break;
					case ERROR_NET_ACCESS:
						resId = R.string.string_http_data_busy;
						break;
					case ERROR_SERVICE_ACCESS:
						resId = R.string.string_http_service_error;
						break;
					default:
						break;
					}

					onRecvError(request, response, result.getResultCode(), getMessage(resId));
				}
			}

			mProcessingState[runId] = false;
		}

	}

	private void printJSONString(String json) {
		jsonIndex++;
		if (jsonIndex >= JSON_MAX_INDEX) {
			jsonIndex = 0;
		}
		String index = " [" + jsonIndex + "] ";
		SLog.i("JSON", index + "JSON数据  = " + json);
	}
}