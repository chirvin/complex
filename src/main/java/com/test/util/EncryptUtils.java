package com.test.util;

import com.google.common.base.Strings;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author scc
 * @date 2020/5/28 11:38
 */
public class EncryptUtils {

    private static final String SECRET_KEY = "d582d5d6-4767-4172-9132-aa407f1d2b4d";

    /**
     * MD5加密，使用apache的commons-codec包
     * @param content
     * @return
     */
    public static String md5Encrypt(String content) {
        if(Strings.isNullOrEmpty(content)) {
            return null;
        }
        return DigestUtils.md5Hex(content);
    }

    /**
     * SHA加密，使用apache的commons-codec包
     * @param content
     * @return
     */
    public static String shaEncrypt(String content, String encName) {
        if(Strings.isNullOrEmpty(content)) {
            return null;
        }
        if (Strings.isNullOrEmpty(encName)) {
            return DigestUtils.sha1Hex(content);
        } else {
            if ("SHA-1".equals(encName)) {
                return DigestUtils.sha1Hex(content);
            } else if ("SHA-256".equals(encName)) {
                return DigestUtils.sha256Hex(content);
            } else if ("SHA-384".equals(encName)) {
                return DigestUtils.sha384Hex(content);
            } else if ("SHA-512".equals(encName)) {
                return DigestUtils.sha512Hex(content);
            } else {
                return null;
            }
        }
    }

    /**
     * BASE64加密字节数组，使用apache的commons-codec包
     * @param bytes
     * @return
     */
    public static byte[] encryptBase64(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return Base64.encodeBase64(bytes);
    }

    /**
     * BASE64解密字节数组，使用apache的commons-codec包
     * @param bytes
     * @return
     */
    public static byte[] decryptBase64(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return Base64.decodeBase64(bytes);
    }

    public static void main(String[] args) {
//        String a = UUID.randomUUID().toString();
//        System.out.println(a);
//        System.out.println(md5Encrypt(a));
//        System.out.println(shaEncrypt(a, null));
//        System.out.println("--------------------------------------");
//        byte[] b1 = encryptBase64(a.getBytes());
//        System.out.println(StringUtils.newStringUtf8(b1));
//        byte[] b2 = decryptBase64(b1);
//        System.out.println(StringUtils.newStringUtf8(b2));

        // A系统传递参数tenantId，并使用sha加密密钥，base64编码生成token
        // B系统使用同样的密钥和加密方法，验证生成的token是否相等
        String tenantId = "110";
        String shaTenantId = shaEncrypt(tenantId + SECRET_KEY, null);
        if (!Strings.isNullOrEmpty(shaTenantId)) {
            System.out.println(shaTenantId);
            byte[] bytes = encryptBase64(shaTenantId.getBytes());
            String token = StringUtils.newStringUtf8(bytes);
            System.out.println(token);
        }
    }
}
