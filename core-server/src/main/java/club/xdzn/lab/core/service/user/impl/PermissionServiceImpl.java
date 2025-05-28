package club.xdzn.lab.core.service.user.impl;

import club.xdzn.lab.common.annotation.Cache;
import club.xdzn.lab.common.annotation.CacheParam;
import club.xdzn.lab.common.constants.RedisConstants;
import club.xdzn.lab.common.entity.user.Permission;
import club.xdzn.lab.common.exception.CustomException;
import club.xdzn.lab.core.service.user.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import club.xdzn.lab.core.mapper.PermissionMapper;
import club.xdzn.lab.core.service.user.PermissionService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import club.xdzn.lab.common.entity.user.RolePermission;
/**
* @author Shelly6
* @description 针对表【permission】的数据库操作Service实现
* @createDate 2025-05-26 17:05:15
*/
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{
    private final RolePermissionService rolePermissionService;

    @Override
    public Boolean removePermission(Long id) {
        Long count = lambdaQuery().eq(Permission::getParentId, id).count();
        if (count > 0) {
            throw new CustomException("存在子权限，无法删除");
        }
        return removeById(id);
    }

    @Override
    public List<Permission> getChildren(String parentId) {
        return baseMapper.getChildren(parentId);
    }

    @Override
    public List<Long> getPermissionByRole(Long roleId) {
        List<Long> permissionIdList = rolePermissionService.lambdaQuery()
                .select(RolePermission::getPermissionId)
                // 此时得到的是权限对象的集合
                .eq(RolePermission::getRoleId, roleId).list()
                .stream().map(RolePermission::getPermissionId).toList();
        if (permissionIdList.isEmpty()) {
            return Collections.emptyList();
        }
        return lambdaQuery().in(Permission::getId, permissionIdList)
                .list().stream()
                .map(Permission::getId).toList();
    }

    @Override
    @Cache(constants = RedisConstants.USER_PERMISSION)
    public List<String> getPermissionByUser(@CacheParam String id) {
        List<Permission> allPermissions = list();
        List<Permission> permissions = baseMapper.getPermissionByUser(id);
        return getSubPermissions(allPermissions, permissions).stream().map(Permission::getKeyName).toList();
    }
    /**
     * 获取子权限
     *
     * @param allPermissions    所有权限
     * @param parentPermissions 父权限
     * @return {@link Set}<{@link Permission}>
     */
    public static Set<Permission> getSubPermissions(List<Permission> allPermissions, List<Permission> parentPermissions) {
        Set<Permission> subPermissions = new HashSet<>();
        for (Permission parentPermission : parentPermissions) {
            subPermissions.add(parentPermission);
            getSubPermissionRecursively(allPermissions, parentPermission.getId(), subPermissions);
        }
        return subPermissions;
    }

    /**
     * 递归获取子权限
     *
     * @param allPermissions 所有权限
     * @param parentId       父 ID
     * @param subPermissions 子权限
     */
    public static void getSubPermissionRecursively(List<Permission> allPermissions, Long parentId, Set<Permission> subPermissions) {
        for (Permission permission : allPermissions) {
            if (permission.getParentId().equals(parentId)) {
                subPermissions.add(permission);
                getSubPermissionRecursively(allPermissions, permission.getId(), subPermissions);
            }
        }
    }

}




