package club.xdzn.lab.common.entity.training;

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
 * @TableName training
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="training")
@Data
public class Training extends BaseEntity implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 训练题单名称
     */
    @TableField(value = "title")
    private String title;

    /**
     * 训练题单简介
     */
    @TableField(value = "description")
    @Schema(description = "训练题单简介")
    private String description;

    /**
     * 训练题单权限类型：Public、Private
     */
    @Schema(description = "训练题单权限类型：Public、Private")
    @TableField(value = "auth")
    private String auth;

    /**
     * 训练题单权限为Private时的密码
     */
    @Schema(description = "训练题单权限为Private时的密码")
    @TableField(value = "private_pwd")
    private String privatePwd;

    /**
     * 编号，升序
     */
    @TableField(value = "rank")
    private Integer rank;

    /**
     * 是否可用
     */
    @Schema(description = "是否可用")
    @TableField(value = "status")
    private Integer status;

    @Schema(description = "是否分组")
    @TableField(value = "is_group")
    private Integer isGroup;

    /**
     * 分类id
     */
    @Schema(description = "分类id")
    @TableField(value = "cid")
    private Long cid;

    @Schema(description = "团队id")
    @TableField(value = "gid")
    private Long gid;


    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}