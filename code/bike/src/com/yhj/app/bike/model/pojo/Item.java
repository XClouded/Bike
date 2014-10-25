package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Item implements Serializable {
	private static final long serialVersionUID = 3184982603936926810L;
	String id;
	String uinnick;
	String uinname;
	String title;
	String surl;
	String weiboid;
	String commentid;
	String url;
	String time;
	String timestamp;
	String articletype;
	String thumbnails[];
	String qishu;
	String source;
	String imagecount;
	String comment;
	String flag;
	String thumbnails_qqnews[];
	String voteId;
	String voteNum;
	@SerializedName("abstract")
	String bstract;
	String graphicLiveID;
	String showTitle;
	/**
	 * 所属专题列表id
	 */
	String specialID;
	String commentNum;
	String video_hits;
	String videoTotalTime;
	String pushCommentCount;
	String showType;
	String thumbnails_qqnews_photo[];
	/**
	 * CELL类型数据(1101自选股插件)
	 */
	String htmlUrl;
	String height;
	/**
	 * 收藏
	 */
	String favorTimestamp;//收藏时间
	String favorSource;//收藏来源 0-腾讯新闻，1-浏览器，2-微云
	
	
	/**
	 * 订阅
	 */
    private RssAuthor rssAuthor[];
    private String chlid;
    private String chlname;
    private String chlmrk;
    private String chlsicon;
    private String chlicon;
    private Boolean isRss = false;
    private String intro;
    private String origUrl;

	public Item() {

	}	
	
	
	public String getOrigUrl() {
        return origUrl;
    }


    public void setOrigUrl(String origUrl) {
        this.origUrl = origUrl;
    }


    public String getIntro() {
        return intro;
    }


    public void setIntro(String intro) {
        this.intro = intro;
    }


    public Boolean getIsRss() {
        return isRss;
    }



    public void setIsRss(Boolean isRss) {
        this.isRss = isRss;
    }



    public String getFavorTimestamp() {
		return favorTimestamp;
	}



	public void setFavorTimestamp(String favorTimestamp) {
		this.favorTimestamp = favorTimestamp;
	}



	public String getFavorSource() {
		return favorSource;
	}



	public void setFavorSource(String favorSource) {
		this.favorSource = favorSource;
	}



	public String getHtmlUrl() {
		return htmlUrl;
	}



	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}



	public String getHeight() {
		return height;
	}



	public void setHeight(String height) {
		this.height = height;
	}



	public String getPushCommentCount() {
		return pushCommentCount;
	}

	public void setPushCommentCount(String pushCommentCount) {
		this.pushCommentCount = pushCommentCount;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String[] getThumbnails_qqnews_photo() {
		return thumbnails_qqnews_photo;
	}

	public void setThumbnails_qqnews_photo(String[] thumbnails_qqnews_photo) {
		this.thumbnails_qqnews_photo = thumbnails_qqnews_photo;
	}

	public String getImagecount() {
		return imagecount;
	}

	public Item(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUinnick() {
		return uinnick;
	}

	public void setUinnick(String uinnick) {
		this.uinnick = uinnick;
	}

	public String getUinname() {
		return uinname;
	}

	public void setUinname(String uinname) {
		this.uinname = uinname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSurl() {
		return surl;
	}

	public void setSurl(String surl) {
		this.surl = surl;
	}

	public String getWeiboid() {
		return weiboid;
	}

	public void setWeiboid(String weiboid) {
		this.weiboid = weiboid;
	}

	public String getCommentid() {
		return commentid;
	}

	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getArticletype() {
		return articletype;
	}

	public void setArticletype(String articletype) {
		this.articletype = articletype;
	}

	public String[] getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(String[] thumbnails) {
		this.thumbnails = thumbnails;
	}

	public String getQishu() {
		return qishu;
	}

	public void setQishu(String qishu) {
		this.qishu = qishu;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getImageCount() {
		return imagecount;
	}

	public void setImagecount(String imagecount) {
		this.imagecount = imagecount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String[] getThumbnails_qqnews() {
		return thumbnails_qqnews;
	}

	public void setThumbnails_qqnews(String[] thumbnails_qqnews) {
		this.thumbnails_qqnews = thumbnails_qqnews;
	}

	public String getVoteId() {
		return voteId;
	}

	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}

	public String getVoteNum() {
		return voteNum;
	}

	public void setVoteNum(String voteNum) {
		this.voteNum = voteNum;
	}

	public String getBstract() {
		return bstract;
	}

	public void setBstract(String bstract) {
		this.bstract = bstract;
	}

	public String getGraphicLiveID() {
		return graphicLiveID;
	}

	public void setGraphicLiveID(String graphicLiveID) {
		this.graphicLiveID = graphicLiveID;
	}

	public String getShowTitle() {
	    return showTitle;
	}

	public void setShowTitle(String showTitle) {
	    this.showTitle = showTitle;
	}

	public String getSpecialID() {
		return specialID;
	}

	public void setSpecialID(String specialID) {
		this.specialID = specialID;
	}

	// public boolean isHadRead() {
	// return hadRead;
	// }
	//
	// public void setHadRead(boolean hadRead) {
	// this.hadRead = hadRead;
	// }

	public String getVideoTotalTime() {
		return videoTotalTime;
	}

	public void setVideoTotalTime(String videoTotalTime) {
		this.videoTotalTime = videoTotalTime;
	}

	public String getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (o instanceof Item) {
			return ((Item) o).getId().equals(this.id);
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public String getVideo_hits() {
		if(video_hits==null || video_hits==""){
			video_hits = "0";
		}
		return video_hits;
	}

	public void setVideo_hits(String video_hits) {
		this.video_hits = video_hits;
	}

    public RssAuthor[] getRssAuthor() {
        return rssAuthor;
    }

    public void setRssAuthor(RssAuthor[] rssAuthor) {
        this.rssAuthor = rssAuthor;
    }

    public String getChlid() {
        return chlid;
    }

    public void setChlid(String chlid) {
        this.chlid = chlid;
    }

    public String getChlname() {
        return chlname;
    }

    public void setChlname(String chlname) {
        this.chlname = chlname;
    }

    public String getChlmrk() {
        return chlmrk;
    }

    public void setChlmrk(String chlmrk) {
        this.chlmrk = chlmrk;
    }

    public String getChlsicon() {
        return chlsicon;
    }

    public void setChlsicon(String chlsicon) {
        this.chlsicon = chlsicon;
    }

    public String getChlicon() {
        return chlicon;
    }

    public void setChlicon(String chlicon) {
        this.chlicon = chlicon;
    }
}
