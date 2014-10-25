package com.yhj.app.bike.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 腾讯新闻底层页正则匹配 */
public class TFormat {

	/** 匹配 <!--xxx_x--> */
	public static Matcher matchAll(String text) {
		Pattern p = Pattern.compile("(<!--(IMG|VIDEO|LINK)_\\d+-->)|(<!--H\\d+--[^>]*?>.*?<!--/H\\d+-->)");
		return p.matcher(text);
	}

	/** 匹配 <!--xxx_x--> */
	public static Matcher matchExpression(String text) {
		Pattern p = Pattern.compile("<!--(IMG|VIDEO|LINK)_\\d+-->");
		return p.matcher(text);
	}
	
	/** 匹配 <!--IMG_x--> */
	public static Matcher matchImg(String text) {
		Pattern p = Pattern.compile("<!--IMG_\\d+-->");
		return p.matcher(text);
	}
	
	/** 匹配 <!--VIDEO_x--> */
	public static Matcher matchVideo(String text) {
		Pattern p = Pattern.compile("<!--VIDEO_\\d+-->");
		return p.matcher(text);
	}

	/** 匹配超链接 */
	public static Matcher matchLink(String text) {
		Pattern p = Pattern.compile("<!--LINK_\\d+-->");
		return p.matcher(text);
	}
	
	/** 匹配<!--Hx-->xxx<!--/Hx-->标签 */
	public static Matcher matchSection(String text) {
		Pattern p = Pattern.compile("<!--H\\d--[^>]*?>.*?<!--/H\\d-->");
		return p.matcher(text);
	}
	
	/** 去掉<!--Hx--> */
	public static Matcher matchLabel(String text) {
		Pattern p = Pattern.compile("<!--[^(-->)]*-->");
		return p.matcher(text);
	}
}
