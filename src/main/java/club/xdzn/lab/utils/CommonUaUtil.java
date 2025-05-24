package club.xdzn.lab.utils;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户代理工具类
 *
 * @author shelly
 * @date 2024/8/29 15:34
 */
public class CommonUaUtil {

    private static final String UNKNOWN = "Unknown";

    public static String getBrowser(HttpServletRequest request) {
        UserAgent userAgent = getUserAgent(request);
        if (ObjectUtil.isEmpty(userAgent)) {
            return StrPool.DASHED;
        } else {
            String browser = userAgent.getBrowser().toString();
            if (StringUtils.isNotBlank(browser) && browser.length() > 250) {
                browser = browser.substring(0, 250);
            }
            return UNKNOWN.equals(browser) ? StrUtil.DASHED : browser;
        }
    }

    public static String getOs(HttpServletRequest request) {
        UserAgent userAgent = getUserAgent(request);
        if (ObjectUtil.isEmpty(userAgent)) {
            return StrPool.DASHED;
        } else {
            String os = userAgent.getOs().toString();
            if (StringUtils.isNotBlank(os) && os.length() > 250) {
                os = os.substring(0, 250);
            }
            return UNKNOWN.equals(os) ? StrPool.DASHED : os;
        }
    }

    /**
     * 获取请求代理头
     */
    private static UserAgent getUserAgent(HttpServletRequest request) {
        String userAgentStr = JakartaServletUtil.getHeaderIgnoreCase(request, "User-Agent");
        UserAgent userAgent = UserAgentUtil.parse(userAgentStr);
        if (ObjectUtil.isNotEmpty(userAgentStr) && UNKNOWN.equals(userAgent.getBrowser().getName())) {
                userAgent.setBrowser(new Browser(userAgentStr, null, ""));
            }

        return userAgent;
    }
    private CommonUaUtil(){}
}
