package club.xdzn.lab.core.utils;

import club.xdzn.lab.common.exception.CustomException;

import java.util.Random;

/**
 * 验证码生成工具类
 * @author shelly
 */
public class ValidateCodeUtils {

    private ValidateCodeUtils(){

    }
    static final Random random = new Random();

    /**
     * 随机生成验证码
     *
     * @param length 声场验证码长度为4位或者6位
     */
    public static Integer generateValidateCodeUtils(int length) {
        int code;
        if (length == 4) {
            code = random.nextInt(9999);
            if (code < 1000) {
                code = code + 1000;
            }
        } else if (length == 6) {
            code = random.nextInt(999999);
            if (code < 100000) {
                code = code + 100000;
            }
        } else {
            throw new CustomException("只能生成四位或者六位数字验证码");
        }
        return code;
    }
}
