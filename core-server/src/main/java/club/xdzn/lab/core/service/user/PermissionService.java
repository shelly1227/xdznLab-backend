package club.xdzn.lab.core.service.user;

import club.xdzn.lab.common.entity.user.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Shelly6
* @description 针对表【permission】的数据库操作Service
* @createDate 2025-05-26 17:05:15
*/
public interface PermissionService extends IService<Permission> {
    /**
     * 获取子权限
     *
     * @param parentId 父 ID
     * @return {@link List}<{@link Permission}>
     */
    List<Permission> getChildren(String parentId);

    /**
     * 按角色获取权限
     *
     * @param roleId 角色 ID
     * @return {@link List}<{@link Long}>
     */
    List<Long> getPermissionByRole(Long roleId);

    /**
     * 按用户获取权限
     *
     * @param id 编号
     * @return {@link List}<{@link String}>
     */
    List<String> getPermissionByUser(String id);

    /**
     * 删除权限
     *
     * @param id 编号
     * @return {@link Boolean}
     */
    Boolean removePermission(Long id);

}
