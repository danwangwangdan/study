package com.common.util;


import com.alibaba.druid.util.JdbcUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.ByteBuffer;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;


/**
 * 实现加解密的实用类
 */
public class EncryptUtil {
    public static final String DEFAULT_PUBLIC_KEY_STRING = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKHGwq7q2RmwuRgKxBypQHw0mYu4BQZ3eMsTrdK8E6igRcxsobUC7uT0SoxIjl1WveWniCASejoQtn/BY6hVKWsCAwEAAQ==";
    private static final Logger log = LoggerFactory.getLogger(EncryptUtil.class);
    // 加密算法
    private static final String encryptAlgorithm = "AES";
    // 加密类型
    // 算法 AES
    // 模式 CBC, CFB, ECB, OFB, PCBC
    // 补码方式 NoPadding, PKCS5Padding, ISO10126Padding
    private static final String encryptType = "AES/CBC/ISO10126Padding"; // 算法/模式/补码方式
    private static final String ivParameter = "JJL2@ys5xs0^99N!";
    private static final IvParameterSpec iv = new IvParameterSpec(str2Bytes(ivParameter));
    private static final String DEFAULT_PRIVATE_KEY_STRING = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAocbCrurZGbC5GArEHKlAfDSZi7gFBnd4yxOt0rwTqKBFzGyhtQLu5PRKjEiOXVa95aeIIBJ6OhC2f8FjqFUpawIDAQABAkAPejKaBYHrwUqUEEOe8lpnB6lBAsQIUFnQI/vXU4MV+MhIzW0BLVZCiarIQqUXeOhThVWXKFt8GxCykrrUsQ6BAiEA4vMVxEHBovz1di3aozzFvSMdsjTcYRRo82hS5Ru2/OECIQC2fAPoXixVTVY7bNMeuxCP4954ZkXp7fEPDINCjcQDywIgcc8XLkkPcs3Jxk7uYofaXaPbg39wuJpEmzPIxi3k0OECIGubmdpOnin3HuCP/bbjbJLNNoUdGiEmFL5hDI4UdwAdAiEAtcAwbm08bKN7pwwvyqaCBC//VnEWaq39DCzxr+Z2EIk=";
    private static final String RSA = "RSA";
    private static final String X509 = "X.509";
    // 用来生成加密密钥
    private static Random random = new Random();
    // base64编码表
    private static String base64KeyTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    // 加密密钥随机取下列的字符组合形成
    private static String keyTable = base64KeyTable;

    /**
     * @param String
     * @return 将String转换为byte数组
     */
    public static byte[] str2Bytes(String value) {
        try {
            return value.getBytes(Hex.DEFAULT_CHARSET_NAME); // value.getBytes()
        } catch (Exception e) {
        }

        return null;
    }

    /**
     * @param byte[]
     * @return 将byte数组转换为String
     */
    public static String bytes2Str(byte[] value) {
        try {
            return new String(value, Hex.DEFAULT_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
        }

        return null;
    }

    /**
     * 直接对字节流进行AES加密
     *
     * @param content
     * @param key
     * @return
     */
    public static byte[] encrypt(byte[] content, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance(encryptType);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);// 初始化
        return cipher.doFinal(content);
    }

    /**
     * 直接对字节流进行AES加密
     *
     * @param content
     * @param key
     * @return
     */
    public static String encrypt2Str(byte[] content, Key key) throws Exception {
        return Base64.encodeBase64String(encrypt(content, key)); // 将加密后的byte数组进行Base64编码
    }

    /**
     * 直接对字符串进行AES加密
     *
     * @param content
     * @param key
     * @return
     */
    public static byte[] encrypt(String content, Key key) throws Exception {
        return encrypt(str2Bytes(content), key);
    }

    /**
     * 直接对字符串进行AES加密
     *
     * @param content
     * @param key
     * @return
     */
    public static String encrypt2Str(String content, Key key) throws Exception {
        return Base64.encodeBase64String(encrypt(content, key)); // 将加密后的byte数组进行Base64编码
    }

    /**
     * 直接对字节流进行AES加密
     *
     * @param content
     * @param password
     * @return
     */
    public static byte[] encrypt(byte[] content, byte[] password) throws Exception {
        Key key = new SecretKeySpec(password, encryptAlgorithm);
        return encrypt(content, key);
    }

    /**
     * 直接对字节流进行AES加密
     *
     * @param content
     * @param password
     * @return
     */
    public static String encrypt2Str(byte[] content, byte[] password) throws Exception {
        return Base64.encodeBase64String(encrypt(content, password)); // 将加密后的byte数组进行Base64编码
    }

    /**
     * 直接对字节流进行AES加密
     *
     * @param content
     * @param password
     * @return
     */
    public static byte[] encrypt(byte[] content, String password) throws Exception {
        return encrypt(content, Hex.decodeHex(password.toCharArray())); // 将十六进制串的密码转换为byte数组
    }

    /**
     * 直接对字节流进行AES加密
     *
     * @param content
     * @param password
     * @return
     */
    public static String encrypt2Str(byte[] content, String password) throws Exception {
        return Base64.encodeBase64String(encrypt(content, password)); // 将加密后的byte数组进行Base64编码
    }

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static byte[] encrypt(String content, String password) throws Exception {
        return encrypt(str2Bytes(content), password); // 将content转换为byte数组后加密
    }

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static String encrypt2Str(String content, String password) throws Exception {
        return Base64.encodeBase64String(encrypt(content, password)); // 将content转换为byte数组后加密
    }

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static byte[] encrypt(String content, byte[] password) throws Exception {
        return encrypt(str2Bytes(content), password); // 将content转换为byte数组后加密
    }

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static String encrypt2Str(String content, byte[] password) throws Exception {
        return Base64.encodeBase64String(encrypt(content, password)); // 将content转换为byte数组后加密
    }

    /**
     * 解密
     *
     * @param content 待解密内容
     * @param key     解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance(encryptType);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);// 初始化
        return cipher.doFinal(content); // 解密
    }

    /**
     * 解密
     *
     * @param content 待解密内容
     * @param key     解密密钥
     * @return
     * @throws Exception
     */
    public static byte[] decryptStr(String content, Key key) throws Exception {
        return decrypt(Base64.decodeBase64(content), key);
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, byte[] password) throws Exception {
        Key key = new SecretKeySpec(password, encryptAlgorithm);
        return decrypt(content, key);
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] decryptStr(String content, byte[] password) throws Exception {
        return decrypt(Base64.decodeBase64(content), password);
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, String password) throws Exception {
        return decrypt(content, Hex.decodeHex(password.toCharArray())); // 将十六进制串的密码转换为byte数组
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] decryptStr(String content, String password) throws Exception {
        return decrypt(Base64.decodeBase64(content), password);
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] decrypt(String content, String password) throws Exception {
        return decrypt(str2Bytes(content), password); // 将content转换为byte数组后加密
    }

    /**
     * 将str进行md5加密
     *
     * @param str
     * @return
     */
    public static String str2Md5Str(String str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * 将str进行md5加密
     *
     * @param str
     * @return
     */
    public static String bytes2Md5Str(byte[] str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * 将str进行md5加密
     *
     * @param str
     * @return
     */
    public static byte[] str2Md5(String str) {
        return DigestUtils.md5(str);
    }

    /**
     * 将str进行md5加密
     *
     * @param str
     * @return
     */
    public static byte[] bytes2Md5(byte[] str) {
        return DigestUtils.md5(str);
    }

    /**
     * 将short值转换为byte数组
     *
     * @param value
     * @return
     */
    public static byte[] short2ByteArray(short value) {
        int offset;
        int length = 2;
        byte[] result = new byte[length];

        for (int i = 0; i < length; i++) {
            offset = (result.length - 1 - i) * 8;
            result[i] = (byte) ((value >>> offset) & 0xFF);
        }

        return result;
    }

    /**
     * 将byte数组转换为short值
     *
     * @param value
     * @return
     */
    public static short byteArray2Short(byte[] value) {
        if (2 != value.length) {
            return -1;
        }

        int offset;
        short result = 0;

        for (int i = 0; i < value.length; i++) {
            offset = (value.length - 1 - i) * 8;
            result += (value[i] << offset);
        }

        return result;
    }

    /**
     * 将int值转换为byte数组
     *
     * @param value
     * @return
     */
    public static byte[] int2ByteArray(int value) {
        int offset;
        int length = 4;
        byte[] result = new byte[length];

        for (int i = 0; i < length; i++) {
            offset = (result.length - 1 - i) * 8;
            result[i] = (byte) ((value >>> offset) & 0xFF);
        }

        return result;
    }

    /**
     * 将byte数组转换为int值
     *
     * @param value
     * @return
     */
    public static int byteArray2Int(byte[] value) {
        if (4 != value.length) {
            return -1;
        }

        int offset;
        int result = 0;

        for (int i = 0; i < value.length; i++) {
            offset = (value.length - 1 - i) * 8;
            result += (value[i] << offset);
        }

        return result;
    }

    /**
     * 将long值转换为byte数组
     *
     * @param value
     * @return
     */
    public static byte[] long2ByteArray(long value) {
        int offset;
        int length = 8;
        byte[] result = new byte[length];

        for (int i = 0; i < length; i++) {
            offset = (result.length - 1 - i) * 8;
            result[i] = (byte) ((value >>> offset) & 0xFF);
        }

        return result;
    }

    /**
     * 将byte数组转换为long值
     *
     * @param value
     * @return
     */
    public static long byteArray2Long(byte[] value) {
        if (8 != value.length) {
            return -1;
        }

        int offset;
        long result = 0;

        for (int i = 0; i < value.length; i++) {
            offset = (value.length - 1 - i) * 8;
            result += (value[i] << offset);
        }

        return result;
    }

    /**
     * 合并两个数组
     *
     * @param first
     * @param second
     * @return
     */
    public static byte[] mergeArray(byte[] first, byte[] second) {
        byte[] result = new byte[first.length + second.length];
        System.arraycopy(first, 0, result, 0, first.length); // 拷贝第一个数组
        System.arraycopy(second, 0, result, first.length, second.length); // 拷贝第二个数组

        return result;
    }

    /**
     * 随机生成加密密钥
     *
     * @return
     */
    public static byte[] generateKey() {
        byte[] k1 = long2ByteArray(random.nextLong());
        byte[] k2 = long2ByteArray(random.nextLong());

        return mergeArray(k1, k2);
    }

    /**
     * 随机生成加密密钥,此种算法生成的密钥加密强度太低,暂不采用(需要将字符表扩展)
     *
     * @return
     */
    public static String generateStrKey() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            sb.append(keyTable.charAt(random.nextInt(keyTable.length())));
        }

        return sb.toString();
    }

    /**
     * @param ByteBuffer
     * @return byte数组
     */
    public static byte[] readUTF(ByteBuffer buffer) {
        short len = buffer.getShort();
        byte[] result = new byte[len];
        buffer.get(result);

        return result;
    }

    /**
     * @param ByteBuffer
     * @return byte数组
     * @throws IOException
     */
    public static DataOutputStream writeUTF(DataOutputStream os, byte[] value) throws IOException {
        os.writeShort(Short.reverseBytes((short) value.length)); // 先写入两个字节的长度,与本地字节序相同
        os.write(value); // 再写入数据

        return os;
    }

    /**
     * @param ByteBuffer
     * @return byte数组
     * @throws IOException
     */
    public static DataOutputStream writeUTF(DataOutputStream os, String value) throws IOException {
        return writeUTF(os, str2Bytes(value)); // 将字符串转换为byte数组后写入到输出流
    }

    /**
     * @param sessionKey
     * @param sessionId
     * @return 生成Base64编码的Ticket
     * @throws UnsupportedEncodingException
     */
    public static String generateTicket(String sessionKey, String sessionId, String pwd) {
        try {
            StringBuilder sb = new StringBuilder(sessionKey).append("|").append(sessionId);

            return encrypt2Str(sb.toString(), pwd); // 加密后编码为Base64
        } catch (Exception e) {
            return null;
        }
    }

    public static String decryptRSA(String cipherText) throws Exception {
        return decryptRSA((String) null, cipherText);
    }

    public static String decryptRSA(String publicKeyText, String cipherText) throws Exception {
        PublicKey publicKey = getPublicKey(publicKeyText);

        return decryptRSA(publicKey, cipherText);
    }

    public static PublicKey getPublicKeyByX509(String x509File) {
        if (null == x509File || x509File.isEmpty()) {
            return getPublicKey(null);
        }

        FileInputStream in = null;
        try {
            in = new FileInputStream(x509File);

            CertificateFactory factory = CertificateFactory.getInstance(X509);
            Certificate cer = factory.generateCertificate(in);
            return cer.getPublicKey();
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to get public key", e);
        } finally {
            JdbcUtils.close(in);
        }
    }

    public static PublicKey getPublicKey(String publicKeyText) {
        if (null == publicKeyText || publicKeyText.isEmpty()) {
            publicKeyText = DEFAULT_PUBLIC_KEY_STRING;
        }

        try {
            byte[] publicKeyBytes = Base64.decodeBase64(publicKeyText);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyBytes);

            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return keyFactory.generatePublic(x509KeySpec);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to get public key", e);
        }
    }

    public static PublicKey getPublicKeyByPublicKeyFile(String publicKeyFile) {
        if (null == publicKeyFile || publicKeyFile.isEmpty()) {
            return getPublicKey(null);
        }

        FileInputStream in = null;
        try {
            in = new FileInputStream(publicKeyFile);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int len = 0;
            byte[] b = new byte[512 / 8];
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }

            byte[] publicKeyBytes = out.toByteArray();
            X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory factory = KeyFactory.getInstance(RSA);
            return factory.generatePublic(spec);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to get public key", e);
        } finally {
            JdbcUtils.close(in);
        }
    }

    public static String decryptRSA(PublicKey publicKey, String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        if (null == cipherText || cipherText.isEmpty()) {
            return cipherText;
        }

        byte[] cipherBytes = Base64.decodeBase64(cipherText);
        byte[] plainBytes = cipher.doFinal(cipherBytes);

        return new String(plainBytes);
    }

    public static String encryptRSA(String plainText) throws Exception {
        return encryptRSA((String) null, plainText);
    }

    public static String encryptRSA(String key, String plainText) throws Exception {
        if (null == key) {
            key = DEFAULT_PRIVATE_KEY_STRING;
        }

        byte[] keyBytes = Base64.decodeBase64(key);
        return encryptRSA(keyBytes, plainText);
    }

    public static String encryptRSA(byte[] keyBytes, String plainText) throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance(RSA);
        PrivateKey privateKey = factory.generatePrivate(spec);
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        byte[] encryptedBytes = cipher.doFinal(str2Bytes(plainText));
        String encryptedString = Base64.encodeBase64String(encryptedBytes);

        return encryptedString;
    }

    public static byte[][] genKeyPairBytes(int keySize) throws NoSuchAlgorithmException {
        byte[][] keyPairBytes = new byte[2][];

        KeyPairGenerator gen = KeyPairGenerator.getInstance(RSA);
        gen.initialize(keySize, new SecureRandom());
        KeyPair pair = gen.generateKeyPair();

        keyPairBytes[0] = pair.getPrivate().getEncoded();
        keyPairBytes[1] = pair.getPublic().getEncoded();

        return keyPairBytes;
    }

    public static String[] genKeyPair(int keySize) throws NoSuchAlgorithmException {
        byte[][] keyPairBytes = genKeyPairBytes(keySize);
        String[] keyPairs = new String[2];

        keyPairs[0] = Base64.encodeBase64String(keyPairBytes[0]);
        keyPairs[1] = Base64.encodeBase64String(keyPairBytes[1]);

        return keyPairs;
    }

    /**
     * 直接对字节流进行DES加密
     *
     * @param content
     * @param key
     * @return
     */
    public static byte[] desEncrypt(byte[] content, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
        return cipher.doFinal(content);
    }

    /**
     * 直接对字节流进行DES加密
     *
     * @param content
     * @param key
     * @return
     */
    public static String desEncrypt2Str(byte[] content, Key key) throws Exception {
        return Base64.encodeBase64String(desEncrypt(content, key)); // 将加密后的byte数组进行Base64编码
    }

    /**
     * 直接对字符串进行DES加密
     *
     * @param content
     * @param key
     * @return
     */
    public static byte[] desEncrypt(String content, Key key) throws Exception {
        return desEncrypt(str2Bytes(content), key);
    }

    /**
     * 直接对字符串进行DES加密
     *
     * @param content
     * @param key
     * @return
     */
    public static String desEncrypt2Str(String content, Key key) throws Exception {
        return Base64.encodeBase64String(desEncrypt(content, key)); // 将加密后的byte数组进行Base64编码
    }

    /**
     * 直接对字节流进行DES加密
     *
     * @param content
     * @param password
     * @return
     */
    public static byte[] desEncrypt(byte[] content, byte[] password) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(new SecureRandom(password));
        Key key = keyGenerator.generateKey();
        return desEncrypt(content, key);
    }

    /**
     * 直接对字节流进行DES加密
     *
     * @param content
     * @param password
     * @return
     */
    public static String desEncrypt2Str(byte[] content, byte[] password) throws Exception {
        return Base64.encodeBase64String(desEncrypt(content, password)); // 将加密后的byte数组进行Base64编码
    }

    /**
     * 直接对字节流进行DES加密
     *
     * @param content
     * @param password
     * @return
     */
    public static byte[] desEncrypt(byte[] content, String password) throws Exception {
        return desEncrypt(content, Hex.decodeHex(password.toCharArray())); // 将十六进制串的密码转换为byte数组
    }

    /**
     * 直接对字节流进行DES加密
     *
     * @param content
     * @param password
     * @return
     */
    public static String desEncrypt2Str(byte[] content, String password) throws Exception {
        return Base64.encodeBase64String(desEncrypt(content, password)); // 将加密后的byte数组进行Base64编码
    }

    /**
     * DES加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static byte[] desEncrypt(String content, String password) throws Exception {
        return desEncrypt(str2Bytes(content), password); // 将content转换为byte数组后加密
    }

    /**
     * DES加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static String desEncrypt2Str(String content, String password) throws Exception {
        return Base64.encodeBase64String(desEncrypt(content, password)); // 将content转换为byte数组后加密
    }

    /**
     * DES加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static byte[] desEncrypt(String content, byte[] password) throws Exception {
        return desEncrypt(str2Bytes(content), password); // 将content转换为byte数组后加密
    }

    /**
     * DES加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static String desEncrypt2Str(String content, byte[] password) throws Exception {
        return Base64.encodeBase64String(desEncrypt(content, password)); // 将content转换为byte数组后加密
    }

    /**
     * DES解密
     *
     * @param content 待解密内容
     * @param key     解密密钥
     * @return
     */
    public static byte[] desDecrypt(byte[] content, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
        return cipher.doFinal(content); // 解密
    }

    /**
     * DES解密
     *
     * @param content 待解密内容
     * @param key     解密密钥
     * @return
     * @throws Exception
     */
    public static byte[] desDecryptStr(String content, Key key) throws Exception {
        return desDecrypt(Base64.decodeBase64(content), key);
    }

    /**
     * DES解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] desDecrypt(byte[] content, byte[] password) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(new SecureRandom(password));
        Key key = keyGenerator.generateKey();
        return desDecrypt(content, key);
    }

    /**
     * DES解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] desDecryptStr(String content, byte[] password) throws Exception {
        return desDecrypt(Base64.decodeBase64(content), password);
    }

    /**
     * DES解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] desDecrypt(byte[] content, String password) throws Exception {
        return desDecrypt(content, Hex.decodeHex(password.toCharArray())); // 将十六进制串的密码转换为byte数组
    }

    /**
     * DES解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] desDecryptStr(String content, String password) throws Exception {
        return desDecrypt(Base64.decodeBase64(content), password);
    }

    /**
     * DES解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] desDecrypt(String content, String password) throws Exception {
        return desDecrypt(str2Bytes(content), password); // 将content转换为byte数组后加密
    }

    public static byte[] parseTicket(String ticket, String key) {
        byte[] sessionKey = null;
        try {
            byte[] plain = EncryptUtil.decryptStr(ticket, key);
            if (null != plain) {
                String st = EncryptUtil.bytes2Str(plain);
                String[] strs = st.split("\\|");
                String sKey = strs[0];
                sessionKey = Hex.decodeHex(sKey.toCharArray());
            }
        } catch (Exception e) {
        }

        return sessionKey;
    }

    public static String parseBody(String serverTicket, String serverKey, String body) {
        byte[] sessionkey = parseTicket(serverTicket, serverKey);

        try {
            byte[] array = decryptStr(body, sessionkey);
            if (null != array) {
                return bytes2Str(array);
            }
        } catch (Exception e) {
        }

        return null;
    }


    public static void main(String[] args) throws Exception {
        //String serverTicket = "0bS06pjekZq6qqwgjPYeT2ySnxzDEQ6HNHpZMhnCMWlJcQ4VBXjtO0yG4XAejxfXV7voV/EhVtUrOurrNiKOEQ==";
        //String serverKey = "40294d7463a5673365c6921e01fef3eb";
        //String body = "oAkO02gZlpTbnqq+ZTMtdkTHXb2LoIJFbF1SWspUkO52RfMzbqtYuX8aRj4w96PmxxE4qp2XvlvQ5Pi0oDc1x/uMrTg7DxN4WEM/nwgoGX8=";
        //
        //System.out.println(parseBody(serverTicket, serverKey, body));
        //
        //String password = args[0];
        //System.out.println(encryptRSA(password));
        System.out.println(EncryptUtil.str2Md5Str("123123"));
    }
}
