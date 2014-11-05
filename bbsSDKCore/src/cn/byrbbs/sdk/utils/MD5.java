package cn.byrbbs.sdk.utils;

import java.security.MessageDigest;

public class MD5 {

	private static final char[] hexDigits = { 
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String hexdigest(String string) {
		String s = null;
		try {
			s = hexdigest(string.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static String hexdigest(byte[] bytes) {
		String s = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(bytes);
			byte[] tmp = md.digest();
			char[] str = new char[32];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
				str[(k++)] = hexDigits[(byte0 & 0xF)];
			}
			s = new String(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static void main(String[] args) {
		System.out.println(hexdigest("c"));
	}

}
