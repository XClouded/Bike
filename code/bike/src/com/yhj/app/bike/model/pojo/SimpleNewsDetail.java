package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;

public class SimpleNewsDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3045357973701593361L;
	private String ret;
	private String id;
	private String url;
	private String topic;
	private String surl;
	private String intro;
	private String remarks;
	private String text;

	private TreeMap<String, Object> attr = new TreeMap<String, Object>(new OrderComparator());
	private List<Item> relate_news;
	private Editor editor;
	private RssCatListItem card;

	public SimpleNewsDetail() {

	}

	/**
	 * 用来图片排序的
	 */
	private static class OrderComparator implements Comparator<String>, Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2648791148539994329L;

		@Override
		public int compare(String s1, String s2) {
			if (s1.indexOf("IMG") > -1 && s2.indexOf("IMG") > -1) {
				String a1[] = s1.split("_");
				String a2[] = s2.split("_");
				if (a1 != a2 && a1.length > 0 && a2.length > 0) {
					int n1 = Integer.parseInt(a1[1]);
					int n2 = Integer.parseInt(a2[1]);
					return n1 - n2;
				} else {
					return s1.compareTo(s2);
				}
			} else {
				return s1.compareTo(s2);
			}
		}
	}

	public TreeMap<String, Object> getAttribute() {
		return attr;
	}

	public void setAttribute(JSONObject attribute) {
		if (attribute == null) {
			return;
		}
		Iterator<?> keyIter = attribute.keys();
		String key;
		while (keyIter.hasNext()) {
			key = (String) keyIter.next();
			Log.i("setAttribute", "key" + key);
			if (key.indexOf("IMG") > -1 && attribute.has(key)) {
				try {
					JSONObject obj = attribute.getJSONObject(key);
					Image it = new Gson().fromJson(obj.toString(), Image.class);
					this.attr.put(key, it);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			if (key.indexOf("VIDEO") > -1 && attribute.has(key)) {
				try {
					JSONObject obj = attribute.getJSONObject(key);
					VideoValue it = new Gson().fromJson(obj.toString(), VideoValue.class);
					this.attr.put(key, it);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			if (key.indexOf("LINK") > -1 && attribute.has(key)) {
				try {
					JSONObject obj = attribute.getJSONObject(key);
					Item it = new Gson().fromJson(obj.toString(), Item.class);
					this.attr.put(key, it);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getSurl() {
		return surl;
	}

	public void setSurl(String surl) {
		this.surl = surl;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Item> getRelate_news() {
		return relate_news;
	}

	public void setRelate_news(List<Item> relate_news) {
		this.relate_news = relate_news;
	}

	public Editor getEditor() {
		return editor;
	}

	public void setEditor(Editor editor) {
		this.editor = editor;
	}

    public RssCatListItem getCard() {
        return card;
    }

    public void setCard(RssCatListItem card) {
        this.card = card;
    }
}
