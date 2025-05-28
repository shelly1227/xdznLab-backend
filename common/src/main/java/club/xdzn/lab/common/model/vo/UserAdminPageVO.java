package club.xdzn.lab.common.model.vo;

import club.xdzn.lab.common.entity.user.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserAdminPageVO {
    /**
     * uuid用户id
     */
    @Schema(description = "uuid用户id")
    private String uuid;

    /**
     * 登录账号
     */
    @Schema(description = "登录名")
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 性别
     */
    private String gender;

    /**
     * 称号、头衔
     */
    @Schema(description = "称号、头衔")
    private String titleName;

    /**
     * 称号、头衔的背景颜色
     */
    @Schema(description = "称号、头衔的背景颜色")
    private String titleColor;

    @Schema(description = "创建时间")
    private Date gmtCreate;

    /**
     * 0可用，1不可用
     */
    @Schema(description = "0可用，1不可用")
    private Integer status;

    public static UserAdminPageVO toVO(UserInfo userInfo) {
        return new UserAdminPageVO()
                .setUuid(userInfo.getUuid())
                .setUsername(userInfo.getUsername())
                .setNickname(userInfo.getNickname())
                .setEmail(userInfo.getEmail())
                .setGender(userInfo.getGender())
                .setTitleName(userInfo.getTitleName())
                .setTitleColor(userInfo.getTitleColor())
                .setGmtCreate(userInfo.getGmtCreate())
                .setStatus(userInfo.getStatus());
    }
}
