
package com.yhj.app.bike.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.model.pojo.RssCatListItem;
import com.yhj.app.bike.task.TaskManager;
import com.yhj.app.bike.utils.FileUtil;
import com.yhj.app.bike.utils.SLog;
import com.yhj.app.bike.utils.StringUtil;

public class UserRssChannelRecord {

    private static final int ADD_ID_GROUP = 2;
    private static final int DEL_ID_GROUP = 5;
    private static final Pattern pattern = Pattern
            .compile("(add=)?(([0-9]*,?)*)&?(del=)?(([0-9]*,?)*)");
    
    public static final int ID_TYPE_UUID = 0;
    public static final int ID_TYPE_QQ = 1;

    // 记录增删变化
    private static final String mRecordFilePrefix = Constants.SYNC_LOG_PATH + "rss_";
    // 记录所有订阅
    private static final String mAllChannelFilePrefix = Constants.SYNC_LOG_PATH + "rss_chs_";
    private String mId = null;

    private int mIdType = ID_TYPE_UUID;
    private HashMap<String, Object> mAll;
    private HashMap<String, Object> mAdd = new HashMap<String, Object>();
    private HashMap<String, Object> mDel = new HashMap<String, Object>();

    protected UserRssChannelRecord(String id, int idType) {
        mId = id;
        mIdType = idType;
        String channelIdsStr = FileUtil.readString(mRecordFilePrefix + mId);
        parseIdString(channelIdsStr);
        mAll = readRssChannel();
        if (mAll == null) {
            mAll = new HashMap<String, Object>();
        }
    }

    /**
     * 登录QQ时，把当前的订阅状态加到QQ订阅状态上
     * 
     * @param qq
     */
    public void changeToQQ(String qq) {
        if (!mId.equals(qq)) {
            mIdType = ID_TYPE_QQ;
            mId = qq;
            mAdd.clear();
            for (String id : mAll.keySet()) {
                mAdd.put(id, null);
            }
            mAll.clear();
            save();
        }
    }

    /**
     * 退出QQ时，把当前的订阅状态作为本机uid的订阅状态
     * 
     * @param uid
     */
    public void changeToUid(String uid) {
        if (!mId.equals(uid)) {
            mIdType = ID_TYPE_UUID;
            mId = uid;
            save();
        }
    }

    public void save() {
        TaskManager.startRunnableRequest(new Runnable() {
            @Override
            public void run() {
                FileUtil.writeString(mRecordFilePrefix + mId, generateAddDelString(), false);
                writeRssChannel();
            }
        });
    }

    /**
     * idsString: 自己构造的增删记录，格式：add=xx,xx&del=xx,xx 原有已同步到订阅记录不变
     */
    public void reload(String idsString) {
        reset();
        parseIdString(idsString);
        mAll = readRssChannel();
        if (mAll == null) {
            mAll = new HashMap<String, Object>();
        }
        save();
    }

    /**
     * 用服务器返回的全量列表覆盖本地记录
     * 
     * @param coverOperation 是否保留本地的操作历史
     */
    public void reload(List<RssCatListItem> channelList, boolean coverOperation) {
        if (coverOperation) {
            reset();
        } else {
            mAll.clear();
        }
        if (channelList != null) {
            for (RssCatListItem rssCatListItem : channelList) {
                mAll.put(rssCatListItem.getChlid(), null);
            }
        }
        save();
    }

    public boolean hasOperation() {
        return mAdd.size() > 0 || mDel.size() > 0;
    }

    public boolean resetOperation() {
        boolean changed = hasOperation();
        if (changed) {
            mAdd.clear();
            mDel.clear();
            FileUtil.writeString(mRecordFilePrefix + mId, generateAddDelString(), false);
        }
        return changed;
    }

    private void reset() {
        mAll.clear();
        mAdd.clear();
        mDel.clear();
    }

    /**
     * 增加一个订阅频道
     */
    public boolean addChannel(String channelId) {
        boolean changed = false;
        if (!mAll.containsKey(channelId)) {
            mAll.put(channelId, null);
            mAdd.put(channelId, null);
            mDel.remove(channelId);
            save();
            changed = true;
        }
        return changed;
    }

    /**
     * 删除一个订阅频道
     */
    public boolean delChannel(String channelId) {
        boolean changed = false;
        if (mAll.containsKey(channelId) && !mDel.containsKey(channelId)) {
            mDel.put(channelId, null);
            mAdd.remove(channelId);
            mAll.remove(channelId);
            save();
            changed = true;
        }
        return changed;
    }

    public Set<String> getAllChannelIdSet() {
        return mAll.keySet();
    }

    public String getAllIds() {
        return getIdString(mAll.keySet());
    }

    public String getAddIds() {
        return getIdString(mAdd.keySet());
    }

    public String getDelIds() {
        return getIdString(mDel.keySet());
    }

    public boolean isBookedChannel(String id) {
        return mAll.containsKey(id);
    }
    
    /**
     * 本地存储的订阅列表和服务器返回的做比较，看是否有不同
     * 
     * @param newChannelList
     * @return
     */
    public boolean isDifferent(List<RssCatListItem> newChannelList) {
        if (newChannelList == null || newChannelList.size() == 0) {
            if (mAll.size() == 0) {
                return false;
            } else {
                return true;
            }
        } else if (mAll.size() != newChannelList.size()) {
            return true;
        } else {
            for (RssCatListItem rssCatListItem : newChannelList) {
                if (!mAll.containsKey(rssCatListItem.getChlid())) {
                    return false;
                }
            }
            return true;
        }
    }

    private String getIdString(Set<String> idList) {
        if (idList == null || idList.size() == 0) {
            return null;
        }
        String idsString = StringUtil.List2String(idList);
        return idsString;
    }

    private String generateAddDelString() {
        StringBuilder sb = new StringBuilder();
        if (mAdd.size() > 0) {
            sb.append("add=");
            sb.append(this.getAddIds());

            if (mDel.size() > 0) {
                sb.append("&");
            }
        }

        if (mDel.size() > 0) {
            sb.append("del=");
            sb.append(this.getDelIds());
        }
        return sb.toString();

    }

    /**
     * 从增删记录中解析出增加和删除的ids串
     */
    private void parseIdString(String idsString) {
        Matcher matcher = pattern.matcher(idsString);
        if (matcher.find()) {
            String addIds = matcher.group(ADD_ID_GROUP);
            parseIdString(mAdd, addIds);

            String delIds = matcher.group(DEL_ID_GROUP);
            parseIdString(mDel, delIds);
        }
    }

    /**
     * 分别解析ids串，写入给定的list
     */
    private void parseIdString(HashMap<String, Object> list, String ids) {
        if (ids == null || ids.length() == 0) {
            return;
        }
        String[] idsArray = ids.split(",");
        for (int i = 0; i < idsArray.length; i++) {
            list.put(idsArray[i], null);
        }
    }

    private boolean writeRssChannel() {
        boolean bFlag = true;
        try {
            String base64 = FileUtil.getSeriString(mAll);
            bFlag = FileUtil.writeString(mAllChannelFilePrefix + mId, base64, false);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return bFlag;
    }

    @SuppressWarnings("unchecked")
    private HashMap<String, Object> readRssChannel() {
        HashMap<String, Object> object = null;
        try {
            String base64 = FileUtil.readString(mAllChannelFilePrefix + mId);
            object = (HashMap<String, Object>) FileUtil.getObjectFromBytes(base64);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return object;
    }
    
    public boolean isQQUser() {
        return mIdType == ID_TYPE_QQ;
    }

    public void dump(String tag) {
        if (SLog.ISDEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append(tag).append("----").append("id = ").append(mId).append("\n").append("all (")
                    .append(mAll.size()).append(") = ").append(getAllIds()).append("\n")
                    .append("add (").append(mAdd.size()).append(") = ").append(getAddIds())
                    .append("\n").append("del (").append(mDel.size()).append(") = ")
                    .append(getDelIds());
            Log.d("rss_sync", sb.toString());
        }
    }
}
