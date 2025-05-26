package club.xdzn.lab.common.entity.notice;

import club.xdzn.lab.common.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @TableName sys_notice_publish
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_notice_publish")
@Data
public class SysNoticePublish extends BaseEntity implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 发给哪些用户类型
     */
    @TableField(value = "type")
    @Schema(description = "发送给哪些用户类型")
    private String type;

    /**
     * 是否已拉取给用户
     */
    @TableField(value = "state")
    @Schema(description = "是否已拉取给用户")
    private Integer state;

    /**
     * 接受通知的用户id
     */
    @TableField(value = "recipient_id")
    @Schema(description = "接受通知的用户id")
    private String recipientId;

    /**
     * 发送通知的管理员id
     */
    @TableField(value = "admin_id")
    @Schema(description = "发送通知的管理员id")
    private String adminId;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}