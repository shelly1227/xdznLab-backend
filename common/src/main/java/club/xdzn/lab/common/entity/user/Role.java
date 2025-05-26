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
 * 角色表
 * @TableName role
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="role")
@Data
public class Role extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "主键")
    private Long id;

    /**
     * 角色标识，例如 admin、tourist、user
     */
    @Schema(description = "角色标识")
    private String role;

    /**
     * 角色描述
     */
    @Schema(description = "角色描述")
    private String description;

    /**
     * 是否可用, 0 可用, 1 不可用
     */
    @Schema(description = "是否可用")
    private Integer status;


    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}