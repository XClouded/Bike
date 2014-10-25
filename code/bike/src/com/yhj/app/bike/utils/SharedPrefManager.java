package com.yhj.app.bike.utils;

import com.yhj.app.bike.shareprefrence.SpConfig;
import com.yhj.app.bike.shareprefrence.SpForbidenCommentNews;
import com.yhj.app.bike.shareprefrence.SpNewsHadRead;
import com.yhj.app.bike.shareprefrence.SpTopicHadVoted;
import com.yhj.app.bike.shareprefrence.SpUserHelp;

public class SharedPrefManager {
	
	//程序更新擦除数据
	public static void updataDeleteAll(){
		SpConfig.delCheckUpdateFlag();
		SpConfig.delSilentDownloadFlag();
		SpForbidenCommentNews.delAll();
		SpNewsHadRead.delAll();
		SpUserHelp.delAllHelp();
		SpTopicHadVoted.delVoteOptidAll();
	}
}
