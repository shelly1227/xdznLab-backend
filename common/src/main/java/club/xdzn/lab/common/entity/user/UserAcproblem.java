package club.xdzn.lab.common.entity.user;

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
 * 用户AC题目记录表
 * @TableName user_acproblem
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="user_acproblem")
@Data
public class UserAcproblem extends BaseEntity implements Serializable {
    /**
     * 主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID，关联 user_info 表的 uuid
     */
    @Schema(description = "用户ID")
    private String uid;

    /**
     * 题目ID，关联题目表
     */
    @Schema(description = "题目ID")
    private Long pid;

    /**
     * 提交ID，关联提交记录表
     */
    @Schema(description = "提交ID")
    private Long submitId;


    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}