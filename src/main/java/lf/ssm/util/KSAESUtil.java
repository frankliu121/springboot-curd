package lf.ssm.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class KSAESUtil {

    // 加密
    public static String Encrypt(String sSrc, byte[] key, byte[] iv) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return parseByte2HexStr(encrypted).toLowerCase();
    }

    // 解密
    public static String Decrypt(String sSrc, byte[] raw, byte[] iv) throws Exception {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
            byte[] encrypted1 = parseHexStr2Byte(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr)
    {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++)
        {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    // 测试加密解密
    public static void main(String[] args)throws Exception {

        String Key= "F3A1C47F778CF214C62A5F68360F001B";
        String Iv= "A827B2FBAF9ECA4E81B6DECF3EEBCFC9";

        String aaa = "{\"ClientOrderId\":\"16082210410001\"," +
                "\"PhoneNo\":\"13891430676\",\"PrdInfo\":\"陕西移动10M全国通用流量\"," +
                "\"OrderStatus\":\"SubmitFail\",\"Desc\":\"提交失败，余额不足。\"}";

        String ttt = Encrypt(aaa, parseHexStr2Byte(Key), parseHexStr2Byte(Iv));
        System.out.println(ttt);

        String DeString = Decrypt(ttt.toUpperCase(), parseHexStr2Byte(Key), parseHexStr2Byte(Iv));

        System.out.println(DeString);

    }

}
