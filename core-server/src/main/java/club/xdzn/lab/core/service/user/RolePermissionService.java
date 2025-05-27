package club.xdzn.lab.core.service.user;

import club.xdzn.lab.common.entity.user.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Shelly6
* @description 针对表【role_permission】的数据库操作Service
* @createDate 2025-05-26 17:05:15
*/
public interface RolePermissionService extends IService<RolePermission> {

    List<String> getPermissionNameByRole(Long id);

    List<Long> getPermissionIdsByRole(List<Long> of);

    Boolean unbind(Long roleId, Long permissionId);
}
