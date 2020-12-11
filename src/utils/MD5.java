package utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	MessageDigest m;

	private MD5(MessageDigest m) {
		this.m = m;
	}

	public String hash(String s) {
		m.reset();
		byte[] array = m.digest(s.getBytes(Charset.forName("UTF-8")));
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}

	public static MD5 get() {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			return new MD5(m);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args) {
		System.out.println(MD5.get().hash("a"));
	}
}
