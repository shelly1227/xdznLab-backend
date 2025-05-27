package club.xdzn.lab.common.entity.user;

import club.xdzn.lab.common.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName role_permission
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="role_permission")
@Data
@Accessors(chain = true)
public class RolePermission extends BaseEntity implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "id")
    private Long id;

    private Long roleId;

    private Long permissionId;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}