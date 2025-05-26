package club.xdzn.lab.common.entity.group;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * @TableName group
 */
@TableName(value ="group")
@Data
public class Group implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 头像地址
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 团队名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 团队简称，创建题目时题号自动添加的前缀
     */
    @Schema(name = "团队简称，创建题目时题号自动添加的前缀")
    @TableField(value = "short_name")
    private String shortName;

    /**
     * 团队简介
     */
    @Schema(name = "团队简介")
    @TableField(value = "brief")
    private String brief;

    /**
     * 团队介绍
     */
    @TableField(value = "description")
    @Schema(name = "团队介绍")
    private String description;

    /**
     * 团队创建者用户名
     */
    @TableField(value = "owner")
    @Schema(name = "团队创建者用户名")
    private String owner;

    /**
     * 团队创建者用户id
     */
    @TableField(value = "uid")
    private String uid;

    /**
     * 是否可见
     */
    @Schema(name = "是否可见")
    @TableField(value = "visible")
    private Integer visible;

    /**
     * 是否封禁
     */
    @TableField(value = "status")
    @Schema(name = "是否封禁")
    private Integer status;

    /**
     * 邀请码
     */
    @Schema(name = "邀请码")
    @TableField(value = "code")
    private String code;

    /**
     * 
     */
    @TableField(value = "gmt_create")
    private Date gmtCreate;

    /**
     * 
     */
    @TableField(value = "gmt_modified")
    private Date gmtModified;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}