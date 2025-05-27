package club.xdzn.lab.common.model.dto;

import club.xdzn.lab.common.entity.base.ConvertEntity;
import club.xdzn.lab.common.entity.user.Permission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionDTO extends ConvertEntity<Permission> {
    /**
     * id
     */
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

    /**
     * 有子权限
     *
     * @see Boolean
     */
    @Schema(description = "是否存在子级权限")
    private Boolean hasChildren = false;
}
