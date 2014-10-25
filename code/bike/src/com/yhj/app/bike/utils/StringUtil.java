package com.yhj.app.bike.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.yhj.app.bike.R;
import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.system.Application;

public class StringUtil {

	// MD5 加密
	public static String toMd5(String src) {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(src.getBytes());
			return toHexString(algorithm.digest(), "");
		} catch (NoSuchAlgorithmException e) {
			SLog.e("Md5 encode failed!", e.getMessage());
			return "error";
		}
	}

	public static String toHexString(byte[] bytes, String separator) {
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			hexString.append(Integer.toHexString(0xFF & b)).append(separator);
		}
		return hexString.toString();
	}

	/**
	 * 根据key从形如key1=value1&key2=value2的字符串中得到value
	 * 
	 * @param data
	 * @param key
	 * @author haiyandu
	 * @return
	 * @since 2011-10-27
	 */
	public static String getOAuthInfo(String data, String key) {
		if (key == null) {
			return null;
		}
		if (data != null) {
			String subDatas[] = data.split("&");
			for (int i = 0; i < subDatas.length; i++) {
				String sunDatas[] = subDatas[i].split("=");
				if (key.equals(sunDatas[0])) {
					if (sunDatas.length < 2) {
						return "";
					} else {
						return sunDatas[1];
					}
				}
			}
		}
		return null;

	}

	public static byte[] appendBytes(byte[] src, byte[] append, int start, int length) {
		if (src == null) {
			src = new byte[0];
		}
		if (append == null || length == 0) {
			return src;
		}
		int totalLength = src.length + length;
		byte[] returnBytes = new byte[totalLength];
		for (int i = 0; i < totalLength; i++) {
			if (i < src.length) {
				returnBytes[i] = src[i];
			} else {
				returnBytes[i] = append[i - src.length];
			}
		}
		return returnBytes;
	}

	public static boolean isReturnFail(String data) {
		JSONObject object = null;
		try {
			object = new JSONObject(data);
		} catch (JSONException e) {
			SLog.e(e.toString(), e.getMessage());
		}
		return !object.isNull("fail");
	}

	public static String getRelativeTimeStringEx(long time) {
		String yearPattern = "yyyy-MM-dd";

		Application applicaion = Application.getInstance();
		Calendar calendar = Calendar.getInstance();
		long now = calendar.getTimeInMillis();

		if (time > now) {
			return applicaion.getString(R.string.image_text_live_date_format_now);
		}
		long diff = now - time;
		calendar.clear();
		calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
		calendar.setTimeInMillis(diff);

		int year = calendar.get(Calendar.YEAR) - 1970;
		int day = calendar.get(Calendar.DAY_OF_YEAR) - 1;
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);

		if (0 != year || 3 <= day) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(time);
			return formatDate(c, yearPattern);
		}

		if (0 != day) {
			return Integer.toString(day) + applicaion.getString(R.string.image_text_live_date_format_day);
		}

		if (0 != hour) {
			return Integer.toString(hour) + applicaion.getString(R.string.image_text_live_date_format_hour);
		}

		if (0 != minute) {
			return Integer.toString(minute) + applicaion.getString(R.string.image_text_live_date_format_minute);
		}

		return Integer.toString(second) + applicaion.getString(R.string.image_text_live_date_format_second);

	}

	public static String getImageTextLiveTimeString(long time) {
		String yearPattern = "yyyy-MM-dd";

		Application applicaion = Application.getInstance();
		Calendar calendar = Calendar.getInstance();
		long now = calendar.getTimeInMillis();

		if (time > now) {
			return applicaion.getString(R.string.image_text_live_date_format_now);
		}

		Calendar timeCalendar = Calendar.getInstance();
		timeCalendar.setTimeInMillis(time);

		int year = calendar.get(Calendar.YEAR) - timeCalendar.get(Calendar.YEAR);
		if (0 != year) {
			return formatDate(timeCalendar, yearPattern);
		}

		// 7,23:00 8,02:00
		long hour = calendar.get(Calendar.DAY_OF_YEAR) * 24 + calendar.get(Calendar.HOUR_OF_DAY) - (timeCalendar.get(Calendar.DAY_OF_YEAR) * 24 + timeCalendar.get(Calendar.HOUR_OF_DAY));

		if (hour / 24 >= 3) {
			return formatDate(timeCalendar, yearPattern);
		} else if (hour / 24 > 0) {
			return Integer.toString((int) hour / 24) + applicaion.getString(R.string.image_text_live_date_format_day);
		} else if (hour / 24 == 0 && hour % 24 != 0) {
			return Integer.toString((int) hour % 24) + applicaion.getString(R.string.image_text_live_date_format_hour);
		}

		int second = calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND) - (timeCalendar.get(Calendar.MINUTE) * 60 + timeCalendar.get(Calendar.SECOND));
		if (second / 60 != 0) {
			return Integer.toString(second / 60) + applicaion.getString(R.string.image_text_live_date_format_minute);
		}
		return Integer.toString(second % 60) + applicaion.getString(R.string.image_text_live_date_format_second);
	}

	/**
	 * 时间格式化
	 * 
	 * @param time
	 * @author haiyandu
	 * @return
	 * @since 2011-6-12
	 */
	public static String getPublishTime(long time) {
		String yearPattern = "yyyy-MM-dd";
		String twoDaysAgoPtn = "MM-dd HH:mm";
		String hourPattern = "HH:mm";

		Application applicaion = Application.getInstance();

		Calendar currentCal = Calendar.getInstance();
		Calendar publicCal = Calendar.getInstance();
		publicCal.setTimeInMillis(time);

		if (currentCal.getTimeInMillis() < time) {
			return applicaion.getString(R.string.date_format_just_ago);
		}

		int currentYear = currentCal.get(Calendar.YEAR);
		int publicYear = publicCal.get(Calendar.YEAR);
		// 如果年份非自然年，则显示yyyy-MM-dd
		if (publicYear < currentYear) {
			return formatDate(publicCal, yearPattern);
		}

		int currentDay = currentCal.get(Calendar.DAY_OF_YEAR);
		int publicDay = publicCal.get(Calendar.DAY_OF_YEAR);
		// 如果两天前的则显示为MM-dd HH:mm
		if (currentDay - publicDay >= 3) {
			return formatDate(publicCal, twoDaysAgoPtn);
		}
		// 前天
		if (currentDay - publicDay == 2) {
			return formatDate(publicCal, applicaion.getString(R.string.date_format_two_day_ago) + hourPattern);
		}
		// 昨天
		if (currentDay - publicDay == 1) {
			return formatDate(publicCal, applicaion.getString(R.string.date_format_yesterday) + hourPattern);
		}

		// 当天
		int currentHour = currentCal.get(Calendar.HOUR_OF_DAY);
		int publicHour = publicCal.get(Calendar.HOUR_OF_DAY);

		int currentMinute = currentCal.get(Calendar.MINUTE);
		int publicMinute = publicCal.get(Calendar.MINUTE);

		int hour = 0;
		if (currentHour - publicHour >= 1 && currentHour - publicHour <= 24) {
			if (currentMinute >= publicMinute) {
				hour = currentHour - publicHour;
				return hour + applicaion.getString(R.string.date_format_hour_ago);
			} else {
				if (currentHour - publicHour > 1) {
					hour = currentHour - publicHour - 1;
					return hour + applicaion.getString(R.string.date_format_hour_ago);
				}
			}
		}

		int currentSecond = currentCal.get(Calendar.SECOND);
		int publicSecond = publicCal.get(Calendar.SECOND);

		int minute = 0;
		// 形如current=9:50,public=9:40的情况
		if (currentMinute >= publicMinute) {
			minute = currentMinute - publicMinute;
		} else {
			// 形如current=9:30,public=8:40的情况
			minute = currentMinute + 60 - publicMinute;
		}

		if (minute > 1) {
			return minute + applicaion.getString(R.string.date_format_minute_ago);
		} else if (minute == 1) {
			if (currentSecond >= publicSecond) {
				return 1 + applicaion.getString(R.string.date_format_minute_ago);
			}
		}
		return applicaion.getString(R.string.date_format_just_ago);

	}
	
	   /**
     * 时间格式化
     * 
     * @param time
     * @author haiyandu
     * @return
     * @since 2011-6-12
     */
    public static String getFavoritesTime(long time) {
        String yearPattern = "yyyy-MM-dd";
        String twoDaysAgoPtn = "MM-dd HH:mm";
        String hourPattern = "HH:mm";

        Application applicaion = Application.getInstance();

        Calendar currentCal = Calendar.getInstance();
        Calendar publicCal = Calendar.getInstance();
        publicCal.setTimeInMillis(time);

        if (currentCal.getTimeInMillis() < time) {
            return applicaion.getString(R.string.date_format_just_ago);
        }

        int currentYear = currentCal.get(Calendar.YEAR);
        int publicYear = publicCal.get(Calendar.YEAR);
        // 如果年份非自然年，则显示yyyy-MM-dd
        if (publicYear < currentYear) {
            return formatDate(publicCal, yearPattern);
        }

        int currentDay = currentCal.get(Calendar.DAY_OF_YEAR);
        int publicDay = publicCal.get(Calendar.DAY_OF_YEAR);
        // 如果两天前的则显示为MM-dd HH:mm
        if (currentDay - publicDay >= 3) {
            return formatDate(publicCal, twoDaysAgoPtn);
        }
        // 前天
        if (currentDay - publicDay == 2) {
            return formatDate(publicCal, applicaion.getString(R.string.date_format_two_day_ago) + hourPattern);
        }
        // 昨天
        if (currentDay - publicDay == 1) {
            return formatDate(publicCal, applicaion.getString(R.string.date_format_yesterday) + hourPattern);
        }

        // 当天
        int currentHour = currentCal.get(Calendar.HOUR_OF_DAY);
        int publicHour = publicCal.get(Calendar.HOUR_OF_DAY);

        int currentMinute = currentCal.get(Calendar.MINUTE);
        int publicMinute = publicCal.get(Calendar.MINUTE);

        int hour = 0;
        if (currentHour - publicHour >= 1 && currentHour - publicHour <= 24) {
            if (currentMinute >= publicMinute) {
                hour = currentHour - publicHour;
                return hour + applicaion.getString(R.string.date_format_hour_ago);
            } else {
                if (currentHour - publicHour > 1) {
                    hour = currentHour - publicHour - 1;
                    return hour + applicaion.getString(R.string.date_format_hour_ago);
                }
            }
        }

        int currentSecond = currentCal.get(Calendar.SECOND);
        int publicSecond = publicCal.get(Calendar.SECOND);

        int minute = 0;
        // 形如current=9:50,public=9:40的情况
        if (currentMinute >= publicMinute) {
            minute = currentMinute - publicMinute;
        } else {
            // 形如current=9:30,public=8:40的情况
            minute = currentMinute + 60 - publicMinute;
        }

        if (minute > 1) {
            return minute + applicaion.getString(R.string.date_format_minute_ago);
        } else if (minute == 1) {
            if (currentSecond >= publicSecond) {
                return 1 + applicaion.getString(R.string.date_format_minute_ago);
            }
        }
        if(currentSecond  >= publicSecond){
            return Integer.toString(currentSecond - publicSecond) + applicaion.getString(R.string.image_text_live_date_format_second);
        }else{
            return Integer.toString(currentSecond - publicSecond + 60) + applicaion.getString(R.string.image_text_live_date_format_second);
        }
    }

	/**
	 * 把服务器返回的时间转成客户端格式
	 * 
	 * @param time
	 * @return
	 * @author haiyandu
	 * @since 2011-11-4 上午10:02:22
	 */
	public static String getPublishTime(String time) {

		Date date = new Date(time);
		return getPublishTime(date.getTime());

	}

	private static String formatDate(Calendar cal, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(cal.getTime());
	}

	public static String getImageFileName(String url) {
		String fileName = "";
		if (null != url && url.length() > 7) {
			fileName = url.substring(url.lastIndexOf("/") + 1, url.length());
		}
		return fileName;
	}

	public static String urlEncode(String str) throws UnsupportedEncodingException {
		if (str == null) {
			str = "";
		}
		return URLEncoder.encode(str, Constants.UTF8).replaceAll("\\+", "%20").replaceAll("%7E", "~").replaceAll("\\*", "%2A");
	}

	public static String urlDecode(String str) throws UnsupportedEncodingException {
		return URLDecoder.decode(str, Constants.UTF8);
	}

	public static String getIdFromTag(String tag) {
		if (tag == null || "".equals(tag)) {
			return "";
		}
		int indexOfDot = tag.lastIndexOf(".");
		if (indexOfDot == -1) {
			return "";
		}
		return tag.substring(indexOfDot + 1, tag.length());
	}

	public static String getValidKey(int width, String url) {
		StringBuilder valid_key = new StringBuilder();
		valid_key.append(width);
		valid_key.append(url);
		valid_key.append("DS*kdl)2s34lstm;;vnb!");
		return DigestUtils.md5Hex(valid_key.toString());
	}

	public static String getTsizeValidKey(int tsize, String url) {
		StringBuilder valid_key = new StringBuilder();
		valid_key.append(tsize);
		valid_key.append(url);
		valid_key.append("_area");
		valid_key.append("DS*kdl)2s34lstm;;vnb!");
		return DigestUtils.md5Hex(valid_key.toString());
	}

	public static String[] list2Array(List<String> list) {
		if (list == null || list.size() == 0) {
			return null;
		}
		int size = list.size();
		String[] strings = new String[size];
		for (int i = 0; i < size; i++) {
			strings[i] = list.get(i);
		}
		return strings;
	}

	public static String getScreenMinSide() {
		int nWidth = MobileUtil.getScreenWidthIntPx();
		int nHeight = MobileUtil.getScreenHeightIntPx();
		if (nWidth >= nHeight) {
			return String.valueOf(nHeight);
		}
		return String.valueOf(nWidth);
	}

	public static String cutOffString100(String str) {
		if (!TextUtils.isEmpty(str)) {
			str = str.length() > 100 ? str.substring(0, 100) + "..." : str;
		}
		return str;
	}

	public static String cutDateString(String str) {
		if (!TextUtils.isEmpty(str)) {
			str = str.substring(5, str.length() - 3);
		}
		return str;
	}

	public static String StringFilter(String str) {
		Matcher m = null;
		try {
			str = str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!");// 替换中文标号
			String regEx = "[『』]"; // 清除掉特殊字符
			Pattern p = Pattern.compile(regEx);
			m = p.matcher(str);
		} catch (PatternSyntaxException e) {
			return str;
		}
		return m.replaceAll("").trim();
	}

	// 将字符串转换为半角
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	// 将字符串转换为全角
	public static String ToSBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i] < 127 && c[i] > 32)
				c[i] = (char) (c[i] + 65248);
		}
		return new String(c);
	}

	// 判断一个字符是汉字、数字、字母还是其他（如符号）
	public static int getTypeOfChar(char a) {
		if (StringUtil.isCnHz(a)) { // 汉字
			return 0;
		} else if (Character.isDigit(a)) {
			return 1;
		} else if (Character.isLetter(a)) {
			return 2;
		} else if (StringUtil.isChinese(a) && !StringUtil.isCnHz(a)) {// 汉字符号
			return 4;
		} else {
			return 3;
		}
	}

	// 判断是否是汉字
	public static boolean isCnHz(char a) {
		// return String.valueOf(a).matches("[\u4E00-\u9FA5]"); //
		// 利用正则表达式，经测试可以区分开中文符号
		return String.valueOf(a).matches("[\u4E00-\u9FBF]");
	}

	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}

	public static String getNextWord(String str, int charType) {
		String retStr = "";
		char[] c = str.toCharArray();
		int typeOfChar = 100;
		for (int i = 0; i < c.length; i++) {
			typeOfChar = StringUtil.getTypeOfChar(c[i]);
			if (typeOfChar != charType) {
				break;
			}
			retStr += String.valueOf(c[i]);
		}
		return retStr;
	}

	// 去除空格回车换行
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 截取指定长度中文字符串（一个中文长度为2个英文字符长度）
	 * 
	 * @param str
	 * @param subLength
	 * @return
	 */
	public static String subString(String str, int subLength) {
		int n = 0;
		int i = 0;
		int j = 0;
		int byteNum = subLength * 2;
		boolean flag = true;
		if (str == null) {
			return "";
		}

		for (i = 0; i < str.length(); i++) {
			if ((int) (str.charAt(i)) < 128) {
				n += 1;
			} else {
				n += 2;
			}
			if (n > byteNum && flag) {
				j = i;
				flag = false;
			}
			if (n >= byteNum + 2) {
				break;
			}
		}

		if (n >= byteNum + 2 && i != str.length() - 1) {
			str = str.substring(0, j);
			str += "...";
		}
		return str;
	}

	private static DecimalFormat df = new DecimalFormat("0.0");

	public static String tenTh2wan(long num) {
		if (num >= 10000 && num < 10000000) {
			String str = df.format(num / 10000.0D);

			return (!str.contains(".0") ? str : str.substring(0, str.length() - 2)) + "万";
		} else if (num >= 10000000) {
			String str = df.format(num / 100000000.0D);

			return (!str.contains(".0") ? str : str.substring(0, str.length() - 2)) + "亿";
		} else {
			return num + "";
		}
	}

	public static String tenTh2wan(String number) {
		if (number != null) {

			long num = Long.parseLong(number);
			return tenTh2wan(num);
		} else {
			return "0";
		}
	}
	
	public static String List2String(Object list) {
	    if (list == null) {
	        return "";
	    }
        String result = list.toString().replaceAll("[\\[| |\\]]", "");
        return result;
	}
}
