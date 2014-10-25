package com.yhj.app.bike.model.pojo;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.utils.FileUtil;
import com.yhj.app.bike.utils.SLog;

/**
 * 离线下载的频道列表数据（同下拉刷新的列表数据结构，但需要稍微转换下）
 * 
 * @author jackiecheng
 * 
 */
public class OfflineItems implements Serializable {
	private static final long serialVersionUID = -1596514872740655467L;

	Id ids[];
	OfflineNewslist newslist[];

	public OfflineItems() {

	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public Id[] getIds() {
		return ids;
	}

	public void setIds(Id[] ids) {
		this.ids = ids;
	}

	public OfflineNewslist[] getNewslist() {
		return newslist;
	}

	public void setNewslist(OfflineNewslist[] newslist) {
		this.newslist = newslist;
	}

	/**
	 * 不可在UI线程里执行！
	 * 
	 * @param channelName
	 * @return
	 * @author jackiecheng
	 */
	public List<Item> orderByIdsAndSeparateItemSpecialReportAndBack5Items(final String channelName) {
		SLog.i("ol", "orderByIdsAndSeparateItemSpecialReportAndBack5Items start");
		long s = System.currentTimeMillis();

		if (ids != null && newslist != null && ids.length > 0 && newslist.length > 0) {

			final List<Item> ret = new ArrayList<Item>();

			Map<String, OfflineNewslist> newsMap = new HashMap<String, OfflineNewslist>();
			for (OfflineNewslist onl : newslist) {
				if (onl != null && onl.getListitems() != null) {
					newsMap.put(onl.getListitems().getId(), onl);
				}
			}

			for (int i = 0, j = ids.length; i < j; i++) {

				Id id = ids[i];
				if (id != null) {
					OfflineNewslist tempOnl = newsMap.get(id.getId());
					if (tempOnl != null) {
						final Item item = tempOnl.getListitems();
						final SpecialReport sr = tempOnl.getContent();
						if (item != null) {
							item.setCommentNum(id.getComments());
							item.setVideo_hits(id.getVideo_hits());
							ret.add(item);
						}
						if (sr != null) {

							for (int c = 0; c < sr.getIdlist().length; c++) {
								sr.getIdlist()[c].putCommentNumIntoItem();
							}

							new Thread(new Runnable() {
								public void run() {
									FileUtil.saveSerObjectToFile(sr, Constants.CACHE_SPECIAL_REPORT_OFFLINE_PATH + item.getId());
								}
							}).start();
						}
					}
				}
			}

			new Thread(new Runnable() {
				public void run() {
					FileUtil.saveCache(new File(Constants.CACHE_OFFLINE_PATH + channelName + ".ls"), ret);
				}
			}).start();

			List<Item> ret2 = new ArrayList<Item>();
			if (ret.size() > 5) {
				// 不可使用sublist(0,5)方法
				for (int i = 0; i < 5; i++) {
					ret2.add(ret.get(i));
				}
				Item i = new Item("READ_MORE_OFF_LINE");
				ret2.add(i);
			} else {
				ret2 = ret;
			}
			SLog.i("ol", "orderByIdsAndSeparateItemSpecialReportAndBack5Items end");
			SLog.i("offline", "切分存文件耗时:" + (System.currentTimeMillis() - s));

			SLog.i("ol", "v2()--->抽出来5条数据");

			return ret2;
		}
		return null;
	}
}
