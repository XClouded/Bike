package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 离线下载的预览五条
 * 
 * @author jackiecheng
 * 
 */
public class OfflinePreview implements Serializable {

	private static final long serialVersionUID = 8263189505378538711L;

	String downloadedChannel;
	List<Item> downloadedItems;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		OfflinePreview other = (OfflinePreview) obj;
		if (downloadedChannel != null && downloadedChannel.equals(other.downloadedChannel)) {
			return true;
		}
		return false;
	}

	public String getDownloadedChannel() {
		return downloadedChannel;
	}

	public List<Item> getDownloadedItems() {
		return downloadedItems;
	}

	public void setDownloadedChannel(String downloadedChannel) {
		this.downloadedChannel = downloadedChannel;
	}

	public void setDownloadedItems(List<Item> downloadedItems) {
		this.downloadedItems = downloadedItems;
	}

}
