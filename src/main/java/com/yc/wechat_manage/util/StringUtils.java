package com.yc.wechat_manage.util;

import java.util.Random;

/**
 * 字符串类
 * 
 * @author Administrator
 *
 */
public class StringUtils {

	public static final String EMPTY_STRING = "";
	
	/**
	 * 所有大小写字母和数字
	 */
	public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 所以大写字母
	 */
	public static final String UPPERCASEALL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 所以小写字母
	 */
	public static final String LOWERCASEALL = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * 所有大小写字母
	 */
	public static final String LETTERCHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * 所有数字
	 */
	public static final String NUMBERCHAR = "0123456789";
	
	/**
	 * 判断多个字符串是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(String... obj) {
		if (obj == null) {
			return true;
		}
		for (String string : obj) {
			if (string == null || string.equals("")) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断多个字符串是否不为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNoEmpty(String... obj) {
		return !isEmpty(obj);
	}

	

	/**
	 * 随机字符串数组
	 * 
	 * @param length          单个字符串数量
	 * @param number          数组数量
	 * @param stringUtilsHelp 随机类型
	 * @return
	 */
	public static String[] randomStringArray(int length, int number, StringUtilsHelp stringUtilsHelp) {
		String[] strArray = new String[number];
		for (int i = 0; i < number; i++) {
			strArray[i] = randomString(length, stringUtilsHelp);
		}
		return strArray;
	}

	/**
	 * 随机字符串数组
	 * 
	 * @param length          单个字符串数量
	 * @param stringUtilsHelp 随机类型
	 * @return
	 */
	public static String randomString(int length, StringUtilsHelp stringUtilsHelp) {
		String strings = EMPTY_STRING;
		switch (stringUtilsHelp) {
		case upperCaseAll:
			strings = UPPERCASEALL;
			break;
		case lowercaseAll:
			strings = LOWERCASEALL;
			break;
		case allChar:
			strings = ALLCHAR;
			break;
		case letterChar:
			strings = LETTERCHAR;
			break;
		case numberChar:
			strings = NUMBERCHAR;
			break;
		default:
			strings = NUMBERCHAR;
			break;
		}
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(strings.charAt(random.nextInt(strings.length())));
		}
		return sb.toString();
	}

	/**
	 * 随机生成Long
	 */
	public static Long randomLong(int length){
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
		}
        long number = Long.parseLong(sb.toString());
        return number;
	}

	/**
	 * 随机生成Int
	 * 
	 * @param length
	 */
	public static int randomInt(int length) throws Exception {
		if (length > 9) {
			throw new Exception("exceed the limit");
		}
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
		}
		int number = Integer.parseInt(sb.toString());
		return number;
	}

	/**
	 * 大小写转换 只针对 英文字母
	 * 
	 * @param str             字符串
	 * @param stringUtilsHelp
	 * @return
	 * @throws Exception
	 */
	public static String toggleCase(String str, StringUtilsHelp stringUtilsHelp) throws Exception {
		// TODO
		if (str == null || str.equals(EMPTY_STRING)) {
			System.out.println("为空");
			throw new Exception("str is null");
		}
		String strs = EMPTY_STRING;
		switch (stringUtilsHelp) {
		case toLowerCase:
			strs = str.toLowerCase();
			break;
		case toUpperCase:
			strs = str.toUpperCase();
			break;
		default:
			strs = str.toUpperCase();
			break;
		}
		return strs;
	}

	/**
	 * 去掉空字符
	 * 
	 * @return
	 */
	public static String blankingString(String str) throws Exception {
		if (str == null || str.equals(EMPTY_STRING)) {
			return EMPTY_STRING;
		}
		String replace = str.toString().replace(" ", EMPTY_STRING);
		return replace;
	}

	/**
	 * 去掉空字符 是否为空 空返回true
	 * 
	 * @return
	 */
	public static boolean isEmptyBlankingString(String str) throws Exception {
		if (str == null) {
			return true;
		}
		String replace = str.toString().replace(" ", "");
		if (replace.equals(EMPTY_STRING)) {
			return true;
		}
		return false;
	}
}
