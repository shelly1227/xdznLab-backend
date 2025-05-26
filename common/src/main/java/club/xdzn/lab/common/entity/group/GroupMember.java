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
 * @TableName group_member
 */
@TableName(value ="group_member")
@Data
public class GroupMember implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 团队id
     */
    @TableField(value = "gid")
    @Schema(description = "团队id")
    private Long gid;

    /**
     * 用户id
     */
    @TableField(value = "uid")
    @Schema(description = "用户id")
    private String uid;

    /**
     * 1未审批，2拒绝，3普通成员，4团队管理员，5团队拥有者
     */
    @Schema(description = "1未审批，2拒绝，3普通成员，4团队管理员，5团队拥有者")
    @TableField(value = "auth")
    private Integer auth;

    /**
     * 申请理由
     */
    @TableField(value = "reason")
    @Schema(description = "申请理由")
    private String reason;

    @TableField(value = "gmt_create")
    private Date gmtCreate;

    @TableField(value = "gmt_modified")
    private Date gmtModified;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}