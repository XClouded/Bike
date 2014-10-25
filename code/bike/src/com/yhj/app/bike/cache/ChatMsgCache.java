package com.yhj.app.bike.cache;

import java.io.File;

import com.yhj.app.bike.api.JsonParse;
import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.model.pojo.NewsMsgGroup;
import com.yhj.app.bike.model.pojo.NewsMsgList;
import com.yhj.app.bike.utils.FileUtil;

public class ChatMsgCache {
    private File cacheFile;
    
    private NewsMsgList cacheData;
    
    private static Thread writeThread = null;
    
    public ChatMsgCache() {
        
    }
    
    //  media uin + loginUin
    public ChatMsgCache(String mediaUin, String loginUin) {
        cacheFile = new File(Constants.CACHE_NEWS_MSG_PATH + mediaUin + "." + loginUin);
    }
    
    public void setCacheData(NewsMsgList cacheData) {
        this.cacheData = cacheData;
        write();
    }
    
    public NewsMsgList getCacheData() {
        return read();
    }
    
    public File getCacheFile() {
        return cacheFile;
    }

    public void setCacheFile(File cacheFile) {
        this.cacheFile = cacheFile;
    }
    
    private NewsMsgList read() {
        Object o = FileUtil.readCache(cacheFile);
        if (o == null) {
            String json = (String) FileUtil.readString(Constants.CACHE_NEWS_MSG_PATH + cacheFile.getName());
            if (json != null && json.length() > 0) {
                try {
                    o = JsonParse.parseNewsMsgGroup(json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (o instanceof NewsMsgList) {
            cacheData = (NewsMsgList) o;
        }
        return cacheData;
    }
    
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
