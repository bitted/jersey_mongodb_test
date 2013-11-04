package com.lakala.demo.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jodd.util.StringUtil;

import org.apache.log4j.Logger;

public class StringUtils extends org.apache.commons.lang.StringUtils {
	private static Logger logger = Logger.getLogger(StringUtils.class);

	/**
	 * 将用“,”分隔的字符串转成Long 型 List
	 * 
	 * @param ids
	 * @return
	 */
	public static List<Long> stringToList(String ids) {
		return stringToList(ids, ",");
	}

	/**
	 * 将字符串转成Long 型 List
	 * 
	 * @param ids
	 * @param separator
	 *            分隔符
	 * @return
	 */
	public static List<Long> stringToList(String ids, String separator) {
		if (isEmpty(ids)) {
			return new ArrayList<Long>();
		}
		List<Long> idList = new ArrayList<Long>();
		String[] ss = ids.split(separator);
		try {
			for (String s : ss) {
				idList.add(Long.valueOf(s));
			}
		} catch (NumberFormatException e) {
			throw new RuntimeException("字符串不能转成Long型，请检查参数格式：" + ids);
		}
		return idList;
	}

	public static boolean isEmpty(String... param) {
		for (String str : param) {
			if (isEmpty(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 去除html标签
	 * 
	 * @param HTMLStr
	 * @return
	 */
	public static String trimHtmlTags(String HTMLStr) {
		if (isEmpty(HTMLStr)) {
			return "";
		}
		String htmlStr = HTMLStr;
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			String regEx_html = "<[^>]+>";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll("");
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll("");
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll("");
			textStr = htmlStr.replaceAll(" ", "");
			textStr = htmlStr.replaceAll("<", "<");
			textStr = htmlStr.replaceAll(">", ">");
			textStr = htmlStr.replaceAll("®", "®");
			textStr = htmlStr.replaceAll("&", "&");
			textStr = htmlStr.replaceAll("&[A-Za-z ]+([;])?", "");
		} catch (Exception e) {
			logger.info("去除html标签发生异常.");
			// System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;
	}

	/**
	 * 过滤特殊字符
	 * 
	 * @param HTMLStr
	 * @return
	 */
	public static String trimSpecialTags(String HTMLStr) {
		if (isEmpty(HTMLStr)) {
			return "";
		}
		String htmlStr = HTMLStr;
		htmlStr = htmlStr.replaceAll("<", "&lt;");
		htmlStr = htmlStr.replaceAll(">", "&gt;");
		htmlStr = htmlStr.replaceAll("&", "&amp;");
		htmlStr = htmlStr.replaceAll("'", "&apos;");
		htmlStr = htmlStr.replaceAll("\"", "&quot;");
		return htmlStr;
	}

	/**
	 * 用,分隔
	 * 
	 * @param array
	 * @return array == null || array.length == 0 ? null
	 */
	public static String arrayToString(Object[] array) {
		if (array == null || array.length == 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (Object o : array) {
			sb.append(o).append(",");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 数字验证
	 * 
	 * @author Leon
	 * @param string
	 * @return
	 */
	public static boolean checkNumber(String string) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(string);
		return isNum.matches() ? true : false;
	}

	public static String changeUtf8ToUnicode(byte[] aByte) {
		StringBuffer sUnicodeStringBuffer = new StringBuffer();
		int sLength = aByte.length;
		int sInt_1, sInt_2, sInt_3, sInt_4, sInt_5, sInt_6;
		for (int i = 0; i < sLength; i++) {
			sInt_1 = (int) aByte[i] & 0xff;
			switch (sInt_1 >> 4) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				/* 0xxxxxxx */
				sUnicodeStringBuffer.append((char) aByte[i]);
				break;
			case 12:
			case 13:
				/* 110x xxxx 10xx xxxx */
				if (i + 1 < sLength) {
					sInt_2 = (char) aByte[i + 1];
					if ((sInt_2 & 0xC0) == 0x80) {
						sUnicodeStringBuffer.append((char) (((sInt_1 & 0x1F) << 6) | (sInt_2 & 0x3F)));
						i++;
					}
				}
				break;
			case 14:
				/* 1110 xxxx 10xx xxxx 10xx xxxx */
				if (i + 2 < sLength) {
					sInt_2 = (int) aByte[i + 1];
					sInt_3 = (int) aByte[i + 2];
					if (((sInt_2 & 0xC0) == 0x80) || ((sInt_3 & 0xC0) == 0x80)) {
						sUnicodeStringBuffer
								.append((char) (((sInt_1 & 0x0F) << 12) | ((sInt_2 & 0x3F) << 6) | ((sInt_3 & 0x3F) << 0)));
						i = i + 2;
					}
				}
				break;
			}
		}
		return sUnicodeStringBuffer.toString();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "1,2,3,4,5,";
		System.out.println(URLEncoder.encode("中", "UNICODE"));
		System.out.println(StringUtil.replaceLast(str, ",", ""));
	}
}
