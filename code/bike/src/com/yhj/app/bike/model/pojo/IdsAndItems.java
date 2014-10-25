package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class IdsAndItems implements Serializable {

	private static final long serialVersionUID = 1836508984395224239L;

	String section;
	Id[] ids;
	@SerializedName("newslist")
	Item[] newslist;

	public IdsAndItems() {

	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Id[] getIds() {
		return ids;
	}

	public void setIds(Id[] ids) {
		this.ids = ids;
	}

	public Item[] getNewslist() {
		return newslist;
	}

	public void setNewslist(Item[] newslist) {
		this.newslist = newslist;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	/**
	 * 将ids里的评论数放到新闻里
	 */
	public void putCommentNumIntoItem() {
		if (ids != null && newslist != null) {
			for (Item m : newslist) {
				for (Id id : ids) {
					if (id.getId().equals(m.getId())) {
						m.setCommentNum(id.getComments());
						m.setVideo_hits(id.getVideo_hits());
						break;
					}
				}
			}
		}
	}

	public List<Id> convertIdArrayToList() {
		List<Id> idList = new ArrayList<Id>();
		if (ids != null && ids.length > 0) {
			for (Id id : ids) {
				idList.add(id);
			}
		}
		return idList;
	}

	public List<String> convertIdStrArrayToList() {
		List<String> idStrList = new ArrayList<String>();
		if (ids != null && ids.length > 0) {
			for (Id id : ids) {
				idStrList.add(id.getId());
			}
		}
		return idStrList;
	}

	public List<Item> convertItemArrayToList() {
		List<Item> itemList = new ArrayList<Item>();
		if (newslist != null && newslist.length > 0) {
			for (Item item : newslist) {
				itemList.add(item);
			}
		}
		return itemList;
	}

	public void order20ItemsBy20Ids() {
		if (this.ids != null && this.newslist != null && this.ids.length > 0 && this.newslist.length > 0) {
			Item[] orderedItems = new Item[newslist.length];
			for (int i = 0; i < ids.length; i++) {
				Id id = ids[i];
				Item tempItem = new Item(id.getId());
				for (Item item : newslist) {
					if (item.equals(tempItem)) {
						orderedItems[i] = item;
						break;
					}
				}
			}
			this.newslist = orderedItems;
		}
	}

	/**
	 * 获取IdsAndItems里的所有item的id,为了新旧专题对象差分合并准备的方法
	 * 
	 * @return
	 */
	@Deprecated
	public String getAllItem() {
		if (this.newslist != null && this.newslist.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0, j = this.newslist.length; i < j; i++) {
				if (i != 0) {
					sb.append(",");
				}
				sb.append(this.newslist[i].id);
			}
			return sb.toString();
		}
		return "";
	}
}
