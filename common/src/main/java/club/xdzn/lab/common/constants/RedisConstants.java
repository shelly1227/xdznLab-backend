package club.xdzn.lab.common.constants;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * redis常量
 * @author shelly
 */
@Getter
public enum RedisConstants {
    /**
     * 默认
     */
    DEFAULT("",10L,TimeUnit.SECONDS),
    /**
     * 用户常量
     */
    USER("User:", 1800L, TimeUnit.SECONDS),
    /**
     * 用户角色常量
     */
    USER_ROLE("User:role:", 1800L, TimeUnit.SECONDS),
    /**
     * 用户权限常量
     */
    USER_PERMISSION("User:permission:", 1800L, TimeUnit.SECONDS),
    /**
     * 用户点赞常量
     */

    USER_COURSE_LIKE("User:courseLike:", 3600L,TimeUnit.SECONDS),
    /**
     * 邮箱验证码常量
     */
    EMAIL_CODE("Email:code:", 300L, TimeUnit.SECONDS),
    /**
     * 手机验证码常量
     */
    PHONE_CODE("Phone:code:", 300L, TimeUnit.SECONDS),
    /**
     * 验证码常量
     */
    VERIFICATION_CODE("Verification:code:", 300L, TimeUnit.SECONDS),
    /**
     * 用户token常量
     */
    USER_TOKEN("User:token:", 3L,TimeUnit.DAYS ),
    /**
     * 用户信息常量
     */
    USER_INFO("User:info:",3L ,TimeUnit.DAYS );
    /**
     * 键
     */
    private final String key;
    /**
     * 过期时间
     */
    private final Long ttl;

    /**
     * 时间单位
     */
    private final TimeUnit timeUnit;

    RedisConstants(String key, Long ttl, TimeUnit timeUnit) {
        this.key = key;
        this.ttl = ttl;
        this.timeUnit = timeUnit;
    }

}
