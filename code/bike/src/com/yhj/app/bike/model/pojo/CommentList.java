package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.shareprefrence.SpUpComment;

public class CommentList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1122861969642290494L;
	private List<Comment[]> hotList;
	private List<Comment[]> newList;
	private String ret;
	private String next; //"我的评论"专用，标识是否还有下一页
	
	private int tagNum = 0;
	private boolean hadAddTag = false;
	private HashMap<String, String> mHashMap=new HashMap<String, String>();    //用来对重复的评论进行去重

	public List<Comment[]> getHotList() {
		return hotList;
	}

	public void setHotList(List<Comment[]> hotList) {
		this.hotList = hotList;
	}

	public List<Comment[]> getNewList() {
		return newList;
	}

	public void setNewList(List<Comment[]> newList) {
		this.newList = newList;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getRet() {
		return this.ret;
	}
	
	public void setNext(String next){
		this.next = next;
	}
	public String hasNext(){
		return this.next;
	}

	/**
	 * 将服务器返回的评论列表合并
	 * 
	 * @return
	 */
	public List<Comment[]> convert2CommentIn1() {
		return addSelfCommentInto2Comments(null);
	}

	/**
	 * 将用户的评论插入评论列表组成:最热评论tag->热门的->最新评论tag->用户的->最新的
	 * 
	 * @param myComment
	 * @return
	 */
	public List<Comment[]> addSelfCommentInto2Comments(Comment[] myComment) {
		List<Comment[]> comment3in1 = new ArrayList<Comment[]>();
		String hmKey = "";
		if (hotList != null && hotList.size() > 0) {
			Comment hotTag = new Comment();
			hotTag.setReplyId(Constants.TAG_COMMENT_CANTBEUP);
			hotTag.setUin(Constants.TAG_COMMENT_HOT);
			Comment[] hotTagComment = new Comment[] { hotTag };
			comment3in1.add(hotTagComment);
			setTagNum(0);
			setTagNum(getTagNum() + 1);

			for (Comment[] c : hotList) {
				
				for (int i = 0; i < c.length; i++) {
					c[i].setHot(true);
					c[i].setHadUp(SpUpComment.getUpComment(c[i].getReplyId()));	
				}				
				
				comment3in1.add(c);
			}
		}

		if(myComment != null && myComment.length > 0) {//add my comment
			hadAddTag = false;
		}
		
		if ((myComment != null && myComment.length > 0) || (newList != null && newList.size() > 0)) {

			if (!hadAddTag) {
				Comment latestTag = new Comment();
				latestTag.setReplyId(Constants.TAG_COMMENT_CANTBEUP);
				latestTag.setUin(Constants.TAG_COMMENT_LATEST);
				Comment[] latestTagComment = new Comment[] { latestTag };
				comment3in1.add(latestTagComment);
				setTagNum(getTagNum() + 1);
				hadAddTag = true;
			}

			if (myComment != null && myComment.length > 0) {
				comment3in1.add(myComment);
			}

			//kiddyliu 2013-07-08 由于需要对评论做去重处理，这里必须修改为依次添加以判断是否有重复的评论
//			if (newList != null && newList.size() > 0) {
//				comment3in1.addAll(newList);
//			}
			if (newList != null && newList.size() > 0) {
				for (Comment[] c : newList) {					
					//评论去重的处理
					if(c.length > 0 && c[c.length-1]!=null ){ 
						hmKey = c[c.length-1].getReplyId()==null? "":c[c.length-1].getReplyId();
						if(!hmKey.equals("") && mHashMap.containsKey(hmKey)){ //评论已经存在
							continue;
						}else{
							mHashMap.put(hmKey, c[c.length-1].getUin());
						}
					}
					//去重处理完成
					comment3in1.add(c);
				}
			}
			//kiddyliu kiddyliu 2013-07-08 修改结束
		}

		return comment3in1;
	}

	public boolean isHadAddTag() {
		return hadAddTag;
	}

	public void setHadAddTag(boolean hadAddTag) {
		this.hadAddTag = hadAddTag;
	}

	/**
	 * @return the tagNum
	 */
	public int getTagNum() {
		return tagNum;
	}

	/**
	 * @param tagNum
	 *            the tagNum to set
	 */
	public void setTagNum(int tagNum) {
		this.tagNum = tagNum;
	}
}
