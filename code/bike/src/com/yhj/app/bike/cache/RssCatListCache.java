package com.yhj.app.bike.cache;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.yhj.app.bike.api.JsonParse;
import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.model.pojo.RssCatList;
import com.yhj.app.bike.model.pojo.RssCatListCat;
import com.yhj.app.bike.model.pojo.RssCatListItem;
import com.yhj.app.bike.utils.FileUtil;

/**
 * 次文件用来读取所有订阅媒体和分类缓存数据
 * 
 * @author wangzhishou@qq.com
 * 
 */
public class RssCatListCache {

    private File          cacheFile;

    private RssCatList    cacheData;

    private static Thread writeThread = null;

    public RssCatListCache() {

    }

    public RssCatListCache(String id) {
        cacheFile = new File(Constants.CACHE_RSS_PATH + id);
    }

    /**
     * 设置缓存数据
     * 
     * @param cacheData
     */
    public void setCacheData(RssCatList cacheData) {
        this.cacheData = cacheData;
        write();
    }

    /**
     * 更新缓存数据
     * 
     * @param cacheData
     */
    public void updateCacheData(RssCatList tmpCacheData) {
        if (this.cacheData != null) {
            if (this.cacheData.getVersion().equals(tmpCacheData.getVersion())) {
                this.cacheData.setSelected(tmpCacheData.getSelected());
                this.cacheData.setSubCounts(tmpCacheData.getSubCounts());
                HashMap<String, String> subCounts = tmpCacheData.getSubCounts();
                List<RssCatListCat> mCatListCats = this.cacheData.getCats();
                int n = cacheData.getCats().size();
                for (int i = 0; i < n; i++) {
                    RssCatListCat rssCatListCat = mCatListCats.get(i);
                    List<RssCatListItem> channels = rssCatListCat.getChannels();
                    int m = channels.size();
                    if (m > 0) {
                        for (int j = 0; j < m; j++) {
                            RssCatListItem rssCatListItem = channels.get(j);
                            String chlid = rssCatListItem.getChlid();
                            String sC = subCounts.get(chlid);
                            if(sC != null) {
                                rssCatListItem.setSubCount(sC);
                                channels.set(j, rssCatListItem);
                            }
                        }
                    }
                    rssCatListCat.setChannels(channels);
                    mCatListCats.set(i, rssCatListCat);
                }
                this.cacheData.setCats(mCatListCats);
            } else {
                this.cacheData = tmpCacheData;
            }
        }
        write();
    }

    /**
     * 获取缓存数据
     * 
     * @return
     */
    public RssCatList getCacheData() {
        return read();
    }

    /**
     * 获取缓存文件目录
     * 
     * @return
     */
    public File getCacheFile() {
        return cacheFile;
    }

    /**
     * 设置缓存文件目录
     * 
     * @param cacheFile
     */
    public void setCacheFile(File cacheFile) {
        this.cacheFile = cacheFile;
    }

    /**
     * 从文件读取数据
     * 
     * @param file
     * @return
     */
    private RssCatList read() {
        Object o = FileUtil.readCache(cacheFile);
        if (o == null) {
            String json = (String) FileUtil.readString(Constants.CACHE_RSS_PATH + cacheFile.getName());
            if (json != null && json.length() > 0) {
                try {
                    o = JsonParse.parseRssCatList(json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (o instanceof RssCatList) {
            cacheData = (RssCatList) o;
        }
        return cacheData;
    }

    /**
     * 向缓存文件写数据
     */
    private void write() {
        writeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 向文件写入数据
                FileUtil.saveCache(cacheFile, cacheData);
            }
        });
        writeThread.start();
    }

    public static void waitExit() {
        try {
            if (writeThread != null) {
                writeThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
