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
 * 权限表
 * @TableName permission
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="permission")
@Data
public class Permission extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @Schema(name = "id")
    private Long id;

    /**
     * 权限名称
     */
    @Schema(name = "权限名称")
    private String permissionName;

    /**
     * 权限关键词(权限认证使用此字段)
     */
    @Schema(name = "权限关键词(权限认证使用此字段)")
    private String keyName;

    /**
     * 状态 (0：关闭，1：开启)
     */
    @Schema(name = "状态 (0：关闭，1：开启)")
    private Integer status;

    /**
     * 父级权限id
     */
    @Schema(name = "父级权限id")
    private Long parentId;

    /**
     * 图标
     */
    @Schema(name = "图标")
    private String icon;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}