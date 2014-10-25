package com.yhj.app.bike.command;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.yhj.app.bike.cache.UserDBHelper;
import com.yhj.app.bike.http.HttpEngine.HttpCode;
import com.yhj.app.bike.utils.MobileUtil;
import com.yhj.app.bike.utils.SLog;
import com.yhj.app.bike.utils.StringUtil;

public abstract class BaseHttpRequest {
	private static final String TAG = BaseHttpRequest.class.getSimpleName();

	private String url;

	private Map<String, String> urlParams = null;

	private Map<String, String> headParams = null;

	private boolean gzip = true;

	/**
	 * 是否需要身份认证
	 */
	private boolean needAuth = true;

	/**
	 * Http类型
	 */
	private String sort;

	/**
	 * 是否重试
	 */
	private boolean retry = false;

	/**
	 * 是否取消本次请求
	 */
	private boolean cancelled = false;

	private boolean mIsCdn = true;
	
	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Map<String, String> getUrlParams() {
		return urlParams;
	}

	public Map<String, String> getHeadParams() {
		return headParams;
	}

	public boolean isGzip() {
		return gzip;
	}

	public boolean isNeedAuth() {
		return needAuth;
	}

	public void setUrlParams(Map<String, String> urlParams) {
		this.urlParams = urlParams;
	}

	public void setHeadParams(Map<String, String> headParams) {
		this.headParams = headParams;
	}

	public void setGzip(boolean gzip) {
		this.gzip = gzip;
	}

	public void setNeedAuth(boolean needAuth) {
		this.needAuth = needAuth;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isRetry() {
		return retry;
	}

	public void setRetry(boolean retry) {
		this.retry = retry;
	}

	public void setCdn(boolean iscdn){
		this.mIsCdn = iscdn;
	}
	
	public boolean isCdn(){
		return this.mIsCdn;
	}
	
	/**
	 * 向urlParams添加一个数据
	 * 
	 * @param key
	 * @param value
	 */
	public void addUrlParams(String key, String value) {
		if (this.urlParams == null) {
			this.urlParams = new HashMap<String, String>();
		}
		this.urlParams.put(key, value);
	}

	/**
	 * 向headParams添加一个数据
	 * 
	 * @param key
	 * @param value
	 */
	public void addHeadParams(String key, String value) {
		if (this.headParams == null) {
			this.headParams = new HashMap<String, String>();
		}
		this.headParams.put(key, value);
	}

	/**
	 * 检测URL的有效性
	 * 
	 * @return
	 */
	protected HttpCode checkUrlParams() {
		if (this.url == null || this.url.equals("")) {
			return HttpCode.ERROR_NET_ACCESS;
		}

		return HttpCode.STATUS_OK;
	}

	/**
	 * 加入用户身份信息
	 */
	protected void addUserVerifyInfo() {
		if (UserDBHelper.getInstance().getUserInfo() != null) {
			if (getHeadParams() == null
					|| (getHeadParams() != null && !getHeadParams()
							.containsKey("cookie"))) {
				addHeadParams("cookie", UserDBHelper.getInstance().getUserInfo().creatCookieStr());
			}
			SLog.v("cookie=" + getHeadParams().get("cookie"));
		}
	}

	/**
	 * 正常逻辑下返回组装后的URL，包含版本信息等
	 * 
	 * @return
	 */
	protected void makeUrlWithSystemInfo() {
		if (this.urlParams == null) {
			this.urlParams = new HashMap<String, String>();
		}
		StringBuilder sb = new StringBuilder();
		if (this.url.contains("?")) {
			sb.append(this.url + "&");
		} else {
			sb.append(this.url + "?");
		}
		try {
			if(mIsCdn){
				this.urlParams.put("appver", URLEncoder.encode(MobileUtil.getSystemSdk() + "_android_" + MobileUtil.getVersionName()));
			}
			this.urlParams.put("devid", URLEncoder.encode(MobileUtil.getImei()));
			this.urlParams.put("mac", URLEncoder.encode(MobileUtil.getLocalMacAddress()));
			this.urlParams.put("apptype", URLEncoder.encode("android"));
			this.urlParams.put("store", URLEncoder.encode(MobileUtil.getFrom()));
			this.urlParams.put("hw", "" + URLEncoder.encode(MobileUtil.getManufacturer() + "_" + MobileUtil.getProductType()));
			this.urlParams.put("sceneid", URLEncoder.encode(MobileUtil.getSceneId()));

			Iterator<String> iterator = this.urlParams.keySet().iterator();
			int size = this.urlParams.keySet().size();
			int index = 0;
			while (iterator.hasNext()) {
				index++;
				Object key = iterator.next();
				String value = this.urlParams.get(key);
				SLog.v(TAG, "key=[" + key.toString() + "], value=[" + value + "]");
				if (index == size) {
					if (!"".equals(value)) {
						sb.append(key + "=" + StringUtil.urlEncode(value));
					}
				} else {
					if (!"".equals(value)) {
						sb.append(key + "=" + StringUtil.urlEncode(value) + "&");
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			SLog.e(TAG, e.toString(), e);
		}

		/**
		 * 合成URL
		 */
		this.url = sb.toString();
		SLog.e("URL", this.url);
	}

	@Override
	public String toString() {
		return "BaseHttpRequest [url=" + url + ", urlParams=" + urlParams + ", headParams=" + headParams + ", gzip=" + gzip + ", needAuth=" + needAuth + ", sort=" + sort + ", retry=" + retry + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((headParams == null) ? 0 : headParams.hashCode());
		result = prime * result + ((sort == null) ? 0 : sort.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((urlParams == null) ? 0 : urlParams.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseHttpRequest other = (BaseHttpRequest) obj;
		if (headParams == null) {
			if (other.headParams != null)
				return false;
		} else if (!headParams.equals(other.headParams))
			return false;
		if (sort == null) {
			if (other.sort != null)
				return false;
		} else if (!sort.equals(other.sort))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (urlParams == null) {
			if (other.urlParams != null)
				return false;
		} else if (!urlParams.equals(other.urlParams))
			return false;
		return true;
	}
	
	/**
	 * 发起请求后统一调用，<组装URL。。。>
	 * 
	 * @return 返回HttpCode错误码
	 */
	abstract public HttpCode prepareRequest();
}
