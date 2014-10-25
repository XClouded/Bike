package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class OfflineSimpleNewsDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3045357973701593361L;
	private String id;
	private String url;
	private String topic;
	private String surl;
	private String intro;
	private String remarks;

//	extInfo qqmusic qqmovie
//	content text
//	attribute IMG_0
	Item[] relate_news;
	
}
