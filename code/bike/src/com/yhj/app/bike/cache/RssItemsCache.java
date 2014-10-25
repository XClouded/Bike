
package com.yhj.app.bike.cache;

import com.yhj.app.bike.model.pojo.Item;
import com.yhj.app.bike.model.pojo.RssId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class RssItemsCache implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1712583114080513075L;

    private RssId mIds[];

    // <id, RssItem>
    private HashMap<String, Item> mNewslist;

    @SuppressWarnings("unchecked")
    private RssItemsCache(RssItemsCache other) {
        this.mIds = other.mIds.clone();
        this.mNewslist = (HashMap<String, Item>) other.mNewslist.clone();
    }

    public RssItemsCache(RssId[] ids, Item[] newslist) {
        this.mIds = ids;
        updateItemList(newslist);
    }

    synchronized public int getIdCount() {
        return mIds == null ? 0 : mIds.length;
    }

    synchronized public boolean getItems(int startIndex, int count, ArrayList<Item> resultList,
            ArrayList<String> pageIds) {
        boolean cacheHit = false;
        if (mIds != null && mNewslist != null) {
            for (int i = 0; startIndex + i < mIds.length && i < count; i++) {
                String id = mIds[startIndex + i].getId();
                Item item = mNewslist.get(id);
                if (item != null) {
                    resultList.add(item);
                } else {
                    pageIds.add(id);
                }
            }
        }
        cacheHit = pageIds.size() == 0;
        return cacheHit;
    }

    synchronized public boolean update(RssId[] ids, Item[] newslist, HashMap<String, RssId> idMap) {
        boolean dataChanged = true;
        updateIdList(ids, idMap);
        updateItemList(newslist);
        return dataChanged;
    }

    synchronized public boolean update(int startIndex, int count, Item[] newslist) {
        boolean dataChanged = true;
        updateItemList(newslist);
        if (newslist.length < count && startIndex + newslist.length < mIds.length) {
            filterInvalidateIds(startIndex, count);
        }
        return dataChanged;
    }

    // 移除mIds中的无效id
    private void filterInvalidateIds(int startIndex, int count) {
        ArrayList<Integer> invalidateIdIndex = new ArrayList<Integer>();
        int end = Math.min(startIndex + count, mIds.length);
        for (int i = startIndex; i < end; i++) {
            if (!mNewslist.containsKey(mIds[i].getId())) {
                invalidateIdIndex.add(i);
            }
        }
        if (invalidateIdIndex.size() > 0) {
            int newLength = mIds.length - invalidateIdIndex.size();
            RssId[] newIds = new RssId[newLength];
            int nextJumpIndex = invalidateIdIndex.get(0);
            for (int i = 0, j = 0, k = 0; i < mIds.length && j < newLength; i++) {
                if (startIndex <= i && i < startIndex + count && i == nextJumpIndex) {
                    k++;
                    if (k < invalidateIdIndex.size()) {
                        nextJumpIndex = invalidateIdIndex.get(k);
                    }
                    continue;
                }
                newIds[j] = mIds[i];
                j++;
            }
            mIds = newIds;
        }
    }

    private void updateIdList(RssId[] ids, HashMap<String, RssId> idMap) {
        if (mIds != null && mNewslist != null) {
            Set<String> cachedIdSet = mNewslist.keySet();
            ArrayList<String> removeIds = new ArrayList<String>();
            for (String cachedId : cachedIdSet) {
                if (!idMap.containsKey(cachedId)) {
                    removeIds.add(cachedId);
                }
            }
            for (String id : removeIds) {
                mNewslist.remove(id);
            }
        }
        mIds = ids;
    }

    private void updateItemList(Item[] newslist) {
        if (mNewslist == null) {
            mNewslist = new HashMap<String, Item>();
        }
        for (Item rssItem : newslist) {
            String newId = rssItem.getId();
            mNewslist.put(newId, rssItem);
        }
    }

    public RssItemsCache clone() {
        RssItemsCache otherCache = new RssItemsCache(this);
        return otherCache;
    }
}
