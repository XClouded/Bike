package com.yhj.app.bike.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.model.pojo.Channel;
import com.yhj.app.bike.model.pojo.ChannelList;
import com.yhj.app.bike.model.pojo.ExtendedChannels;
import com.yhj.app.bike.model.pojo.ListMapData;
import com.yhj.app.bike.model.pojo.LocalChannel;
import com.yhj.app.bike.model.pojo.PushConfig;
import com.yhj.app.bike.model.pojo.RemoteConfig;
import com.yhj.app.bike.model.pojo.SplashData;
import com.yhj.app.bike.shareprefrence.SpConfig;

/**
 * @author      haiyandu
 * @description    
 * @date        2012-8-23
 */
public class InfoConfigUtil {
	public static boolean WriteRemoteConfig(Object object){
		boolean bFlag = true;
		try{
			String base64 = FileUtil.getSeriString(object);
			SpConfig.setRemoteConfig(base64);
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return bFlag;
	}
	
	public static RemoteConfig ReadRemoteConfig(){
		RemoteConfig object = null;
		try {
			String base64 = SpConfig.getRemoteConfig();
			object = (RemoteConfig)FileUtil.getObjectFromBytes(base64);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return object;
	}
	
	public static boolean WriteSubChannel(Object object){
		boolean bFlag = true;
		try{
			String base64 = FileUtil.getSeriString(object);
			SpConfig.setSubChannel(base64);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return bFlag;
	}
	
	public static ListMapData ReadSubChannel(){
		ListMapData object = null;
		try {
			String base64 = SpConfig.getSubChannel();
			object = (ListMapData)FileUtil.getObjectFromBytes(base64);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return object;
	}
	
	public static boolean WriteSplash(Object object){
		boolean bFlag = true;
		try{
			String base64 = FileUtil.getSeriString(object);
			SpConfig.setSplashData(base64);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return bFlag;
	}
	
	public static SplashData ReadSplash(){
		SplashData object = null;
		try {
			String base64 = SpConfig.getSplashData();
			object = (SplashData)FileUtil.getObjectFromBytes(base64);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return object;
	}
	
	public static boolean WriteAppList(Object object){
		boolean bFlag = true;
		try{
			String base64 = FileUtil.getSeriString(object);
			SpConfig.setAppList(base64);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return bFlag;
	}
	
//	public static AppList ReadAppList(){
//		AppList object = null;
//		try {
//			String base64 = SpConfig.getAppList();
//			object = (AppList)FileUtil.getObjectFromBytes(base64);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		return object;
//	}
	
	public static boolean WritePushSeq(Object object){
		boolean bFlag = true;
		try{
			String base64 = FileUtil.getSeriString(object);
			SpConfig.setPushSeq(base64);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return bFlag;
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> ReadPushSeq(){
		List<String> object = null;
		try {
			String base64 = SpConfig.getPushSeq();
			object = (List<String>)FileUtil.getObjectFromBytes(base64);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return object;
	}
	
	public static boolean WritePushConfig(Object object){
		boolean bFlag = true;
		try{
			String base64 = FileUtil.getSeriString(object);
			SpConfig.setPushConfig(base64);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return bFlag;
	}
	
	public static PushConfig ReadPushConfig(){
		PushConfig object = null;
		try {
			String base64 = SpConfig.getPushConfig();
			object = (PushConfig)FileUtil.getObjectFromBytes(base64);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return object;
	}
	
	public static boolean WriteExtendedChannels(Object object){
		boolean bFlag = true;
		try{
			String base64 = FileUtil.getSeriString(object);
			SpConfig.setExtendedChannels(base64);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return bFlag;
	}
	
	public static ExtendedChannels ReadExtendedChannels(){
		ExtendedChannels object = null;
		try {
			String base64 = SpConfig.getExtendedChannels();
			object = (ExtendedChannels)FileUtil.getObjectFromBytes(base64);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return object;
	}
	
	/**
	 * 保存配置信息
	 * @param object
	 * @return
	 */
	private static boolean WriteConfig(String configName, Object object){
		boolean bFlag = true;
		try{
			String base64 = FileUtil.getSeriString(object);
			SpConfig.setConfig(configName, base64);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return bFlag;
	}
	
	/**
	 * 获取配置信息
	 * @return
	 */
	private static Object ReadPhotoSubChannel(String configName){
		Object object = null;
		try {
			String base64 = SpConfig.getConfig(configName);
			object = (Object)FileUtil.getObjectFromBytes(base64);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return object;
	}
	
	/**
	 * 保存图片频道配置信息
	 * @param object
	 * @return
	 */
	public static boolean WritePhotoSubChannel(Object object){
		return WriteConfig(Constants.CONFIG_PHOTO_CHANNEL, object);
	}
	
	/**
	 * 获取图片频道配置信息
	 * @return
	 */
	public static ChannelList ReadPhotoSubChannel(){
		return (ChannelList)ReadPhotoSubChannel(Constants.CONFIG_PHOTO_CHANNEL);
	}
	
	/**
	 * 保存视频频道配置信息
	 * @param object
	 * @return
	 */
	public static boolean WriteVideoSubChannel(Object object){
		return WriteConfig(Constants.CONFIG_VIDEO_CHANNEL, object);
	}
	
	/**
	 * 获取视频频道配置信息
	 * @return
	 */
	public static ChannelList ReadVideoSubChannel(){
		return (ChannelList)ReadPhotoSubChannel(Constants.CONFIG_VIDEO_CHANNEL);
	}
	
	/**
	 * 保存选中新闻频道配置信息
	 * @param object
	 * @return
	 */
	public static boolean WriteNewsChannelSelected(Object object){
		return WriteConfig(Constants.CONFIG_NEWS_CHANNEL_SELECTED, object);
	}
	
	/**
	 * 获取选中新闻频道配置信息
	 * @return
	 */
	public static ListMapData ReadNewsChannelSelected(){
		return (ListMapData)ReadPhotoSubChannel(Constants.CONFIG_NEWS_CHANNEL_SELECTED);
	}
	
	/**
	 * 频道信息转换数据类型
	 * @param source
	 * @param isInit 是否初始频道 初始频道默认选中
	 * @return
	 */
	public static ListMapData translate(ChannelList source, boolean isInit){
		ListMapData target=null;
		if(source!=null && source.getChannelList()!=null && source.getChannelList().size()>0){
			target= new ListMapData();
			List<Channel> Channels = source.getChannelList();
			int nChannelSize = Channels.size();
			for(int i = 0; i < nChannelSize; ++i){
				Channel channel = Channels.get(i);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("chlid", channel.getChlid());
				map.put("channelName", channel.getChlname());
				map.put("recommend", channel.getRecomment());
				map.put("count", channel.getCount());
				map.put("from", "channellist");
				if(isInit==true){
					map.put("isSelected", true);
					map.put("order", i);
				}
				target.getMenuDataMap().add(map);
			}
			if(source.getLocalChannels()!=null && source.getLocalChannels().size()>0){
				List<LocalChannel> localChannels = source.getLocalChannels();
				nChannelSize = localChannels.size();
				for(int i = 0; i < nChannelSize; ++i){
					LocalChannel channel = localChannels.get(i);
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("chlid", channel.getChlid());
					map.put("channelName", channel.getChlname());
					map.put("recommend", channel.getRecommend());
					map.put("count", channel.getCount());
					map.put("selected", channel.getSelected());
					map.put("head_letter", channel.getHead_letter());
					map.put("from", "local_chllist");
					//if(isInit==true){
					//	map.put("isSelected", true);
					//}
					target.getMenuDataMap().add(map);
				}
			}
		}
		return target;
	}
	
	/**
	 * 频道信息转换数据类型
	 * @param source
	 * @param onlyShow //只转换需要显示的
	 * @return
	 */
	public static ChannelList translate(ListMapData source,boolean onlyShow){
		ChannelList target = new ChannelList();
		List<Channel> channelList = new ArrayList<Channel>();
		if(source!=null && source.getMenuDataMap()!=null && source.getMenuDataMap().size()>0){
			ArrayList<HashMap<String, Object>> list = source.getMenuDataMap();
			int nChannelSize = list.size();
			for(int i = 0; i < nChannelSize; ++i){
				channelList.add(null);
			}
			for(int i = 0; i < nChannelSize; ++i){
				HashMap<String, Object> mapElement = list.get(i);
				if(onlyShow==true && (mapElement.get("isSelected")==null || (Boolean)mapElement.get("isSelected")==false)){
					continue;
				}
				Channel channel = new Channel();
				channel.setChlid((String)mapElement.get("chlid"));
				channel.setChlname((String)mapElement.get("channelName"));
				channel.setCount((String)mapElement.get("count"));
				channel.setRecomment((String)mapElement.get("recommend"));
				if(mapElement.get("order")!=null){
					int order = (Integer) mapElement.get("order");
					if(order<0){
						order=nChannelSize-1;
					}
					if(order<nChannelSize && channelList.get(order)==null){
						channelList.set(order, channel);
					}else{
						channelList.add(order, channel);
					}
				}else{
					channelList.add(channel);
				}
			}
			nChannelSize = channelList.size();
			for(int i = nChannelSize-1; i >= 0; i--){
				if(channelList.get(i)==null){
					channelList.remove(i);
				}
			}
		}
		target.setChannelList(channelList);
		return target;
	}
	
	/**
	 * 数据合并
	 * @param oldData
	 * @param newData
	 * @return
	 */
	public static ListMapData mergerListMapData(ListMapData oldData, ListMapData newData){
		if(oldData==null){
			oldData = new ListMapData();
		}
		Boolean has_recommend_local=false;//是否推荐过地方频道
		HashMap<String, Object> mHashMap=oldData.getmHashMap();
		if(mHashMap!=null ){
			if(mHashMap.containsKey("has_recommend_local")){
				has_recommend_local=(Boolean)mHashMap.get("has_recommend_local");
			}
		}else{
			mHashMap = new HashMap<String, Object>();
		}
		
		ListMapData retData = new ListMapData();
		ArrayList<HashMap<String, Object>> menuDataList = new ArrayList<HashMap<String, Object>>();
		ArrayList<HashMap<String, Object>> showDataList = new ArrayList<HashMap<String, Object>>();//显示的菜单
		
		HashMap<String, String> mapOld = new HashMap<String, String>();
		HashMap<String, HashMap<String, Object>> mapNew = new HashMap<String, HashMap<String, Object>>();
		//获取新列表HashMap数据
		if(newData!=null && newData.getMenuDataMap()!=null && newData.getMenuDataMap().size()>0){
			ArrayList<HashMap<String, Object>> list = newData.getMenuDataMap();
			int nChannelSize = list.size();
			for(int i = 0; i < nChannelSize; ++i){
				HashMap<String, Object> channel = list.get(i);
				mapNew.put((String)channel.get("chlid"), channel);
			}
		}
		//获取旧列表HashMap数据，并把不用的频道从旧列表删除
		if(oldData!=null && oldData.getMenuDataMap()!=null && oldData.getMenuDataMap().size()>0){
			ArrayList<HashMap<String, Object>> list = oldData.getMenuDataMap();
			int nChannelSize = list.size();
			for(int i = 0; i < nChannelSize; ++i){
				showDataList.add(null);
			}
			showDataList.add(null);
			showDataList.add(null);
			ArrayList<HashMap<String, Object>> tempMenuList = new ArrayList<HashMap<String, Object>>();//加到要闻后的
			boolean isShowChannel=false;
			
			for(int i = 0; i < nChannelSize; ++i){
				HashMap<String, Object> channel = list.get(i);
				isShowChannel=false;
				if(mapNew.get((String)channel.get("chlid"))==null){
					//旧列表有新列表中没有，从旧列表中删除
					list.remove(channel);
					i--;
					nChannelSize--;
				}else{
					if(channel.get("isSelected")!=null && (Boolean)channel.get("isSelected")==true){
						if(channel.get("order")!=null){
							int order = (Integer) channel.get("order");
							if(order<0 || order>=nChannelSize-1){
								order=nChannelSize-1;
							}
							if(order<nChannelSize && showDataList.get(order)==null){
								showDataList.set(order, channel);
								isShowChannel=true;
							}else{
								if(order<nChannelSize-1){
									order++;
								}
								showDataList.add(order, channel);
								isShowChannel=true;
							}
						}else{
							showDataList.add(channel);
							isShowChannel=true;
						}
					}
					mapOld.put((String)channel.get("chlid"), "1");
					HashMap<String, Object> channeltemp = (HashMap<String, Object>)mapNew.get((String)channel.get("chlid"));
					channel.put("channelName", channeltemp.get("channelName"));
					channel.put("count", channeltemp.get("count"));
					//判断是否新推荐(用户未选择过、且之前状态是未推荐)
					if(channel.get("recommend")==null){
						channel.put("recommend", "0");
					}
					//如果是地方站，强制不做推荐
					if(channeltemp.containsKey("from") && channeltemp.get("from").equals("local_chllist")){
						channeltemp.put("recommend", "0");
					}
					if( (channeltemp.get("recommend").equals("1") || channeltemp.get("recommend").equals("2")) && channel.get("recommend").equals("0") && 
							(channel.get("isSelected")==null || (Boolean)channel.get("isSelected")==false)){
						channel.put("isSelected", true);
						channel.put("recommend", channeltemp.get("recommend"));
						if(channeltemp.get("recommend").equals("2") && menuDataList.size()>1){
							menuDataList.add(channel);
							tempMenuList.add(channel);
							isShowChannel=true;
							//showDataList.add(1, channel);//加到要闻后
						}else{
							menuDataList.add(channel);
							showDataList.add(channel);
							isShowChannel=true;
						}
					}else{
						menuDataList.add(channel);
					}
					if(isShowChannel==true && channeltemp.containsKey("from") && channeltemp.get("from").equals("local_chllist")){
						has_recommend_local=true;//已经设置过显示地方频道
					}
					if(has_recommend_local==false && isShowChannel==false && channeltemp.containsKey("from") && channeltemp.get("from").equals("local_chllist") && 
							channeltemp.containsKey("selected") && channeltemp.get("selected").equals("1")){
						channel.put("isSelected", true);
						showDataList.add(1, channel);//加到要闻后
						has_recommend_local=true;
					}
				}
			}
			nChannelSize=tempMenuList.size();
			for(int i = 0; i < nChannelSize; ++i){
				HashMap<String, Object> channel = tempMenuList.get(i);
				showDataList.add(1, channel);//加到要闻后
			}
		}
		//获取新列表有，旧列表没有的数据
		if(newData!=null && newData.getMenuDataMap()!=null && newData.getMenuDataMap().size()>0){
			ArrayList<HashMap<String, Object>> list = newData.getMenuDataMap();
			int nChannelSize = list.size();
			boolean isAdd=false;
			for(int i = 0; i < nChannelSize; ++i){
				isAdd=false;
				HashMap<String, Object> channel = list.get(i);
				if(mapOld.get((String)channel.get("chlid"))==null){
					if(channel.get("recommend")==null){
						channel.put("recommend", "0");
					}

					//如果是地方站，强制不做推荐
					if(channel.containsKey("from") && channel.get("from").equals("local_chllist")){
						channel.put("recommend", "0");
					}
					
					//地方新闻如果选中，添加到要闻后
					if(has_recommend_local==false && channel.get("selected")!=null && channel.get("selected").equals("1")){
						//channel.put("recommend", "0");
						channel.put("isSelected", true);
						menuDataList.add(channel);//加到要闻后
						showDataList.add(1, channel);
						isAdd=true;
						has_recommend_local=true;
					}
					String recommend = (String)channel.get("recommend");
					if(recommend.equals("1") && isAdd==false){
						//channel.put("recommend", "0");
						channel.put("isSelected", true);
						menuDataList.add(channel);//加到列表后
						showDataList.add(channel);
						isAdd=true;
					}else if(recommend.equals("2") && isAdd==false){
						//channel.put("recommend", "0");
						channel.put("isSelected", true);
						menuDataList.add(channel);//加到要闻后
						showDataList.add(1, channel);
						isAdd=true;
					}
					//默认添加
					if(isAdd==false){
						channel.put("isSelected", false);
						menuDataList.add(channel);//加到列表后
					}
				}
				//mapNew.put((String)channel.get("chlid"), channel);
			}
		}
		
		//设置order
		HashMap<String, HashMap<String, Object>> map = new HashMap<String, HashMap<String, Object>>();
		int nShowDataSize = showDataList.size();
		int newOrder=0;
		for(int i = 0; i < nShowDataSize; ++i){
			if(showDataList.get(i)!=null){
				HashMap<String, Object> channel = showDataList.get(i);
				//showDataList.get(i).put("order", newOrder);
				channel.put("order", newOrder);
				newOrder++;
				map.put((String)channel.get("chlid"), channel);
			}
		}
		
		int nmenuDataListSize = menuDataList.size();
		for (int i = 0; i < nmenuDataListSize; i++) {
			HashMap<String, Object> channel = menuDataList.get(i);
			String child = (String)channel.get("chlid");
			if(map.get(child)!=null){
				menuDataList.set(i, (HashMap<String, Object>)map.get(child));
			}
		}
		mHashMap.put("has_recommend_local",has_recommend_local);
		retData.setmHashMap(mHashMap);
		retData.setMenuDataMap(menuDataList);
		putChannelIdAndName(retData);
		return retData;
	}
	
	public static void putChannelIdAndName(ListMapData source){
		if(source!=null && source.getMenuDataMap()!=null && source.getMenuDataMap().size()>0){
			ArrayList<HashMap<String, Object>> list = source.getMenuDataMap();
			int nChannelSize = list.size();
			for(int i = 0; i < nChannelSize; ++i){
				HashMap<String, Object> mapElement = list.get(i);
				SpConfig.putChannelIdAndName((String)mapElement.get("chlid"), (String)mapElement.get("channelName"));
			}
		}
	}
}
