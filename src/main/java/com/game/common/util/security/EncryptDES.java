package com.game.common.util.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;


/**
 * DES加密
 */
public class EncryptDES {

    private final static String DES = "DES";

    /**
     * 根据键值进行加密
     */
    public static String encrypt(String data, String key) {
        byte[] bt = new byte[0];
        try {
            bt = encrypt(data.getBytes(), key.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }

    /**
     * 根据键值进行解密
     */
    public static String decrypt(String data, String key) {
        if (data == null) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bt = new byte[0];
        try {
            byte[] buf = decoder.decodeBuffer(data);
            bt = decrypt(buf, key.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(bt);
    }

    /**
     * 根据键值进行加密
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = cipherInit(data, key, Cipher.ENCRYPT_MODE);
        return cipher.doFinal(data);
    }

    /**
     * 根据键值进行解密
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = cipherInit(data, key, Cipher.DECRYPT_MODE);
        return cipher.doFinal(data);
    }

    private static Cipher cipherInit(byte[] data, byte[] key, int cipherValue)
                throws Exception {
        /** 生成一个可信任的随机数源**/
        SecureRandom sr = new SecureRandom();
        /** 从原始密钥数据创建DESKeySpec对象**/
        DESKeySpec dks = new DESKeySpec(key);
        /** 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象**/
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        /** Cipher对象实际完成加密或解密操作**/
        Cipher cipher = Cipher.getInstance(DES);
        /**用密钥初始化Cipher对象**/
        cipher.init(cipherValue, securekey, sr);
        return cipher;
    }

    public static void main(String[] args) {
        String data = "123456";
        //String key = "free!@#$%";
        String key = MD5.md5("18913600130");
        /**加密**/
        System.err.println(encrypt(data, key));
        /**解密**/
        System.err.println(decrypt(encrypt(data, key), key));
        System.err.println(decrypt(
                    "A+ICPBJJmz0=", key));
    }
}

