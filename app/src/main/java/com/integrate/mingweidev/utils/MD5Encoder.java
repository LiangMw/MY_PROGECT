package com.integrate.mingweidev.utils;

import java.security.MessageDigest;

public class MD5Encoder {
	
	public static String encode(String string) throws Exception {
	    byte[] hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
	    StringBuilder hex = new StringBuilder(hash.length * 2);
	    for (byte b : hash) {
	        if ((b & 0xFF) < 0x10) {
	        	hex.append("0");
	        }
	        hex.append(Integer.toHexString(b & 0xFF));
	    }
	    return hex.toString();
	}
	public static int getStringRealLength(String str) throws Exception {
		String str1 = new String(str.getBytes("GB2312"),"iso-8859-1");
		return str1.length();
	}
}
