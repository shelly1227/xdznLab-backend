package club.xdzn.lab.common.model.vo;

import club.xdzn.lab.common.entity.user.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserInfoVO {
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
     * 学校
     */
    private String school;

    /**
     * 专业
     */
    private String course;

    /**
     * 学号
     */
    @Schema(description = "学号")
    private String number;

    /**
     * 真实名字
     */
    @Schema(description = "真实名字")
    private String realname;

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
     * 头像图片地址
     */
    @Schema(description = "头像图片地址")
    private String avatar;

    /**
     * 个性签名
     */
    @Schema(description = "个性签名")
    private String signature;

    /**
     * 博客地址
     */
    @Schema(description = "博客地址")
    private String blog;

    /**
     * github地址
     */
    @Schema(description = "github地址")
    private String github;

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

    @Schema(description = "token")
    private String token;

    public static UserInfoVO toVO(UserInfo userInfo){
        return new UserInfoVO()
                .setUuid(userInfo.getUuid())
                .setUsername(userInfo.getUsername())
                .setNickname(userInfo.getNickname())
                .setSchool(userInfo.getSchool())
                .setCourse(userInfo.getCourse())
                .setNumber(userInfo.getNumber())
                .setRealname(userInfo.getRealname())
                .setEmail(userInfo.getEmail())
                .setGender(userInfo.getGender())
                .setAvatar(userInfo.getAvatar())
                .setSignature(userInfo.getSignature())
                .setBlog(userInfo.getBlog())
                .setGithub(userInfo.getGithub())
                .setTitleName(userInfo.getTitleName())
                .setTitleColor(userInfo.getTitleColor());
    }
}
