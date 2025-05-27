package club.xdzn.lab.common.model.dto;

import club.xdzn.lab.common.entity.base.ConvertEntity;
import club.xdzn.lab.common.entity.user.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDTO extends ConvertEntity<Role> {
    /**
     * 主键
     */
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
}
