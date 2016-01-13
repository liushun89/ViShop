package com.vishop.core.util;

import com.google.common.primitives.Bytes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class MD5Util {

	/**
	 * 求一个字符串的md5摘要字符串
	 * 
	 * @param input
	 * @return
	 */
	public final static String calc(String input) {
		try {
			byte[] plainText = input.getBytes("UTF8");
			byte[] result = calc(plainText);
			return GuavaUtil.byteToHex(result);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 求一个字节数字的md5摘要
	 * 
	 * @param content
	 * @return
	 */
	public final static byte[] calc(byte[] content) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			return messageDigest.digest(content);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	/**
	 * 对一个密码进行加盐摘要
	 * 
	 * @param password
	 * @param salt
	 * @return
	 */
	public final static String calc(String password, String salt) {
		try {
			byte[] first = calc(password.getBytes("UTF8"));
			byte[] saltBytes = salt.getBytes("UTF8");
			byte[] result = calc(Bytes.concat(first, saltBytes));
			return GuavaUtil.byteToHex(result);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
}