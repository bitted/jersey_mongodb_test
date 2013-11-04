
package com.lakala.demo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 类名称：Md5Encrypt
 * 类描述：(md5加密)
 * 创建人：litj
 * 创建时间：2013-11-1 上午11:23:55
 * 修改人：
 * 修改时间：2013-11-1 上午11:23:55
 * 修改备注：
 * @version 1.0.0
 */
public class Md5Encrypt {
	/**
	 * Used building output as Hex
	 */
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 对字符串进行MD5加密
	 * 
	 * @param text
	 *            明文
	 * 
	 * @return 密文
	 */
	public static String md5(String text) {
		MessageDigest msgDigest = null;

		try {
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(
					"System doesn't support MD5 algorithm.");
		}

		try {
			msgDigest.update(text.getBytes("UTF-8"));    //注意改接口是按照指定编码形式签名
 
		} catch (UnsupportedEncodingException e) {

			throw new IllegalStateException(
					"System doesn't support your  EncodingException.");

		}

		byte[] bytes = msgDigest.digest();

		String md5Str = new String(encodeHex(bytes));

		return md5Str;
	}

	public static char[] encodeHex(byte[] data) {

		int l = data.length;

		char[] out = new char[l << 1];

		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS[0x0F & data[i]];
		}

		return out;
	}

	public static void main(String[] args) {
		System.out.println(Md5Encrypt.md5("20060301mmt_zdsj12345652315ff0e7d04a93ea59be2020001234562013091220134494949949422013449494994942000000020000088txy"));
	}
}