package com.yhj.app.bike.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.yhj.app.bike.model.pojo.Comment;

public class TextUtil {

	private static SpannableStringBuilder builder = new SpannableStringBuilder();
	private final static String COLOR_1 = "#404040";
	private final static String COLOR_NAME = "#6E8CB2";
	private final static String COLOR_2 = "#999999";
	
	private final static String NIGHT_COLOR_1 = "#f0f4f8";
	private final static String NIGHT_COLOR_NAME = "#5fabf1";
	private final static String NIGHT_COLOR_2 = "#b0b5b8";

	public static void formatText(Context context, TextView yourTextView, Comment one, Comment two, boolean isNightTheme) {
		yourTextView.setText(processTimeLineText(context, one, two, isNightTheme), BufferType.SPANNABLE);
		yourTextView.getPaint().setAntiAlias(true);
	}

	private static SpannableStringBuilder processTimeLineText(Context context, Comment one, Comment two, boolean isNightTheme) {
		builder.clear();
		builder.clearSpans();
		int end = 0;

		builder.append(one.getReplyContent());
		end = builder.length();

		int start = 0;
		if(isNightTheme){
			builder.setSpan(new ForegroundColorSpan(Color.parseColor(NIGHT_COLOR_1)), start, end, 0);
		}else{
			builder.setSpan(new ForegroundColorSpan(Color.parseColor(COLOR_1)), start, end, 0);
		}
		start = end;

		if (two != null) {

			builder.append(" || ");
			end = builder.length();
			if(isNightTheme){
				builder.setSpan(new ForegroundColorSpan(Color.parseColor(NIGHT_COLOR_NAME)), start, end, 0);
			}else{
				builder.setSpan(new ForegroundColorSpan(Color.parseColor(COLOR_NAME)), start, end, 0);
			}
			start = end;

			if (two.isOpenMb()) {
				builder.append(two.getMb_nick_name());
			} else {
				builder.append(two.getNick());
			}

			builder.append(" : ");
			end = builder.length();
			if(isNightTheme){
				builder.setSpan(new ForegroundColorSpan(Color.parseColor(NIGHT_COLOR_NAME)), start, end, 0);
			}else{
				builder.setSpan(new ForegroundColorSpan(Color.parseColor(COLOR_NAME)), start, end, 0);
			}
			start = end;

			builder.append(two.getReplyContent());
			end = builder.length();
			if(isNightTheme){
				builder.setSpan(new ForegroundColorSpan(Color.parseColor(NIGHT_COLOR_2)), start, end, 0);
			}else{
				builder.setSpan(new ForegroundColorSpan(Color.parseColor(COLOR_2)), start, end, 0);
			}
		}

		return builder;
	}	
}
