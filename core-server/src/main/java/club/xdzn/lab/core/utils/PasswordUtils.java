package club.xdzn.lab.core.utils;

import cn.hutool.crypto.digest.MD5;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * 密码工具类
 * @author HeXin
 * @date 2025/05/24
 */
public class PasswordUtils {
    /**
     * 加密盐
     */
    static final byte[] SLAT = {'x','d','z','n'};

    private PasswordUtils() {
    }

    /**
     * 加密
     */
    public static String encrypt(String password) {
        MD5 md5 = new MD5(SLAT);
        return md5.digestHex16(md5.digestHex(password));
    }

    /**
     * 密码配对
     */
    public static boolean match(String password, String encryptedPassword) {
        return encryptedPassword.equals(encrypt(password));
    }
    /**
     * 基于多个字符串生成稳定的端口号
     *
     * @param parts 多个用于混合的字符串
     * @return 合法端口号（1024-65535）
     */
    public static int generatePort(String... parts) {
        try {
            StringBuilder sb = new StringBuilder();
            for (String part : parts) {
                sb.append(part).append("#");
            }
            // 生成 SHA-256 哈希
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = sha256.digest(sb.toString().getBytes(StandardCharsets.UTF_8));

            // 取前两个字节映射成合法端口（1024-65535）
            int raw = ((hash[0] & 0xFF) << 8) | (hash[1] & 0xFF);
            return 1024 + (raw % (65535 - 1024));
        } catch (Exception e) {
            return -1;
        }
    }
}
