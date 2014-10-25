
package com.yhj.app.bike.cache;

import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.model.pojo.Item;
import com.yhj.app.bike.model.pojo.RssId;
import com.yhj.app.bike.task.TaskManager;
import com.yhj.app.bike.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RssItemsCacheManager {

    public interface StatusListener {
        // 开始读取缓存
        void onLoadStart();

        // 需要加载的id列表
        void onNeedMore(List<String> needIds);

        void onNeedRefresh();

        // 缓存读取结束。如果newItemsList == null，表示读到末尾了。
        void onCacheLoadFinish(int page, List<Item> newItemsList);
    }

    private final int MAX_ITEM_COUNT = 400;
    
    private int pageItemCount = 20;
    private String mChildId = "";
    private File mCacheFile;
    private RssItemsCache mCacheData;
    //                         <id, RssId>
    private HashMap<String, RssId> mIdMap = new HashMap<String, RssId>();

    private StatusListener mStatusListener;

    public RssItemsCacheManager(String childId, StatusListener statusListener) {
        mChildId = childId;
        mStatusListener = statusListener;
        mCacheFile = new File(getCacheFilePath(mChildId));
    }

    synchronized private boolean loadFile() {
        boolean success = true;
        try {
            Object o = FileUtil.readCache(mCacheFile);
            if (o instanceof RssItemsCache) {
                mCacheData = (RssItemsCache) o;
                //这里不需要恢复mIdMap
                //updateIdMap(mCacheData.mIds);
            }
        } catch (Exception e) {
            success = false;
        }
        return success;
    }
    
    synchronized private boolean readData(int startIndex) {
        if (startIndex >= mCacheData.getIdCount()) {
            // 没有更多数据需要读取了
            if (mStatusListener != null) {
                mStatusListener.onCacheLoadFinish(startIndex, null);
            }
            return false;
        }

        if (mStatusListener != null) {
            mStatusListener.onLoadStart();

            ArrayList<String> pageIds = new ArrayList<String>();
            ArrayList<Item> resultList = new ArrayList<Item>();
            if (mCacheData.getItems(startIndex, pageItemCount, resultList, pageIds)) {
                mStatusListener.onCacheLoadFinish(startIndex, resultList);
            } else {
                if (startIndex == 0) {
                    mStatusListener.onCacheLoadFinish(startIndex, resultList);
                }
                mStatusListener.onNeedMore(pageIds);
            }
        }
        return false;
    }

    /**
     * 
     * @param startIndex 从缓存中的第几项id开始加载
     * @return 是否在调用过程中执行了刷新操作
     */
    synchronized public boolean getData(final int startIndex) {
        if (mCacheData == null) {
            if (mCacheFile.exists()) {
                TaskManager.startRunnableRequest(new Runnable() {
                    @Override
                    public void run() {
                        if (loadFile()) {
                            readData(startIndex);
                        } else {
                            mCacheFile.delete();
                            if (mStatusListener != null) {
                                mStatusListener.onCacheLoadFinish(startIndex, null);
                            }
                        }
                    }
                });
                return false;
            } else {
                boolean refreshed = false;
                if (mStatusListener != null) {
                    mStatusListener.onNeedRefresh();
                    refreshed = true;
                }
                return refreshed;
            }
        }
        
        return readData(startIndex);
    }

    synchronized public void updateByRefresh(RssId[] ids, Item[] newslist) {
        updateIdMap(ids);
        if (mCacheData == null) {
            mCacheData = new RssItemsCache(ids, newslist);
            updateCommentCount(newslist);
            write();
        } else if (mCacheData.update(ids, newslist, mIdMap)) {
            updateCommentCount(newslist);
            write();
        }
    }

    /**
     * 
     * @param startIndex 需要加载的起始位置
     * @param newslist     服务器返回的新数据
     * 
     * 由于有可能请求的新闻id失效了，newslist不是请求的个数。需要过滤一遍，去除缓存的无效id
     */
    synchronized public void updateByLoadMore(int startIndex, Item[] newslist) {
        if (mCacheData != null && mCacheData.update(startIndex, pageItemCount, newslist)) {
            write();
        }
    }

    private String getCacheFilePath(String childId) {
        return Constants.CACHE_RSS_PATH + childId;
    }
    
    private void updateIdMap(RssId[] ids) {
        if (ids != null) {
            synchronized (mIdMap) {
                if (ids.length > MAX_ITEM_COUNT) {
                    RssId mIds[];
                    mIds = new RssId[MAX_ITEM_COUNT];
                    for (int i = 0; i < MAX_ITEM_COUNT; i++) {
                        mIds[i] = ids[i];
                    }
                    ids = mIds;
                }

                mIdMap.clear();
                for (RssId rssId : ids) {
                    mIdMap.put(rssId.getId(), rssId);
                }
            }
        }
    }
    
    private void updateCommentCount(Item[] newslist) {
        synchronized (mIdMap) {
            for (Item item : newslist) {
                String id = item.getId();
                RssId rssId = mIdMap.get(id);
                if (rssId != null) {
                    item.setCommentNum(rssId.getComments());
                } else {
                    item.setCommentNum(null);
                }
            }
        }
    }

    private void write() {
        if (mCacheData != null) {
            TaskManager.startRunnableRequestInPool(new Runnable() {
                @Override
                public void run() {
                    RssItemsCache objCache = mCacheData.clone();
                    FileUtil.saveCache(mCacheFile, objCache);
                };
            });
        }
    }
    
    public boolean hasMoreData(int currentCount) {
        int size = mCacheData == null ? 0 : mCacheData.getIdCount();
        return size > currentCount;
    }
}
