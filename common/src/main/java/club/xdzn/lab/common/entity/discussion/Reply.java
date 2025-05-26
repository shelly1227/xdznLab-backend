package club.xdzn.lab.common.entity.discussion;

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
 * @TableName reply
 */
@TableName(value ="reply")
@Data
public class Reply implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 被回复的评论id
     */
    @TableField(value = "comment_id")
    private Integer commentId;

    /**
     * 发起回复的用户id
     */
    @TableField(value = "from_uid")
    private String fromUid;

    /**
     * 发起回复的用户名
     */
    @TableField(value = "from_name")
    private String fromName;

    /**
     * 发起回复的用户头像地址
     */
    @TableField(value = "from_avatar")
    @Schema(description = "发起回复的用户头像地址")
    private String fromAvatar;

    /**
     * 发起回复的用户角色
     */
    @TableField(value = "from_role")
    @Schema(description = "发起回复的用户角色")
    private String fromRole;

    /**
     * 被回复的用户id
     */
    @TableField(value = "to_uid")
    @Schema(description = "被回复的用户id")
    private String toUid;

    /**
     * 被回复的用户名
     */
    @TableField(value = "to_name")
    @Schema(description = "被回复的用户名")
    private String toName;

    /**
     * 被回复的用户头像地址
     */
    @Schema(description = "被回复的用户头像地址")
    @TableField(value = "to_avatar")
    private String toAvatar;

    /**
     * 回复的内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 是否封禁或逻辑删除该回复
     */
    @TableField(value = "status")
    @Schema(description = "是否封禁或逻辑删除该回复")
    private Integer status;

    @TableField(value = "gmt_create")
    private Date gmtCreate;

    @TableField(value = "gmt_modified")
    private Date gmtModified;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}