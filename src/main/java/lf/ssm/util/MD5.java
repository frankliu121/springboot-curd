/**
 * @filename			MD5.java
 * @function			Md5加密
 * @author				skyz <skyzhw@gmail.com>
 * @datetime			Jul 2, 2007
 * @lastmodify			Jul 2, 2007
 */
package lf.ssm.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5加密
 * 
 * @author 513416
 *
 */
public class MD5 {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MD5.class);

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	public MD5() {
	}
	
	/**
     * @param data 明文
     * @return 密文大写
     */
    public static String md5(String data){
        return DigestUtils.md5Hex(data).toUpperCase();
    }

	public static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return (new StringBuilder()).append(hexDigits[d1])
				.append(hexDigits[d2]).toString();
	}

	/**
	 * md5加密
	 * 
	 * @param
	 * @return
	 */
	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (NoSuchAlgorithmException e) {
		}
		return resultString;
	}
	
	public static String md5Hex(String message, String encoding) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return hex(md.digest(message.getBytes(encoding)));
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Failed to generate md5 string", e);
			throw new RuntimeException("Failed to generate md5 string", e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Failed to generate md5 string", e);
			throw new RuntimeException("Failed to generate md5 string", e);
		}
	}
	
	public static String hex(byte[] array) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(
					1, 3));
		}
		return sb.toString();
	}


	
	public static void main(String[] args) {
		 String ss = "zjh";
		 String s="zjh";
		System.out.println(MD5.MD5Encode(s));
		System.out.print(MD5.MD5Encode(ss));
	}
}