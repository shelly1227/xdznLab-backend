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
 * @TableName sys_notice_receive
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_notice_receive")
@Data
public class SysNoticeReceive extends BaseEntity implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 对应 sys_notice_publish 的主键
     */
    @TableField(value = "notice_id")
    private Integer noticeId;

    /**
     * 接受通知的用户id
     */
    @TableField(value = "recipient_id")
    @Schema(description = "接受通知的用户id")
    private String recipientId;

    /**
     * Sys 系统通知 / Mine 我的消息
     */
    @TableField(value = "type")
    @Schema(description = "Sys 系统通知 / Mine 我的消息")
    private String type;

    /**
     * 是否已读
     */
    @TableField(value = "state")
    @Schema(description = "是否已读")
    private Integer state;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}