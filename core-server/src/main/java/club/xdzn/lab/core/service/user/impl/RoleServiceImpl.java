package club.xdzn.lab.core.service.user.impl;

import club.xdzn.lab.common.annotation.Cache;
import club.xdzn.lab.common.annotation.CacheParam;
import club.xdzn.lab.common.constants.RedisConstants;
import club.xdzn.lab.common.entity.user.Role;
import club.xdzn.lab.common.entity.user.RolePermission;
import club.xdzn.lab.core.service.user.RolePermissionService;
import club.xdzn.lab.core.service.user.UserRoleService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import club.xdzn.lab.core.mapper.RoleMapper;
import club.xdzn.lab.core.service.user.RoleService;
import club.xdzn.lab.common.entity.user.UserRole;

import java.util.Collections;
import java.util.List;

/**
* @author Shelly6
* @description 针对表【role(角色表)】的数据库操作Service实现
* @createDate 2025-05-26 17:05:15
*/
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{
    private final RolePermissionService rolePermissionService;
    private final UserRoleService userRoleService;

    @Override
    @Cache(constants = RedisConstants.USER_ROLE)
    public List<String> getRoleNameByUser(@CacheParam String id) {
        return getRoleByUser(id).stream().map(Role::getRole).toList();
    }


    @Override
    public List<Role> getRoleByUser(String id) {
        List<Long> roleIdList = userRoleService.lambdaQuery().select(UserRole::getRoleId)
                .eq(UserRole::getUid, id).list()
                .stream().map(UserRole::getRoleId).toList();
        if(CollUtil.isEmpty(roleIdList)){
            return Collections.emptyList();
        }
        return lambdaQuery().in(Role::getId,roleIdList).list();
    }

    @Override
    public Boolean saveRole(Role role) {
        boolean saved = save(role);
        if(saved && !role.getPermissions().isEmpty()){
            return bindPermission(role.getId(),role.getPermissions());
        }
        return saved;
    }

    private boolean  bindPermission(Long roleId,List<Long> permissionIdList){
        unbindPermission(roleId);
        List<RolePermission> rolePermissionList = permissionIdList.stream()
                .map(permission -> new RolePermission().setRoleId(roleId).setPermissionId(permission)).toList();
        return rolePermissionService.saveBatch(rolePermissionList);
    }

    private void unbindPermission(Long roleId){
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId,roleId);
        rolePermissionService.remove(wrapper);
    }

    @Override
    public Role appendPermission(Role role) {
        List<String> permissionNameByRole = rolePermissionService.getPermissionNameByRole(role.getId());
        List<Long> permissionIds = rolePermissionService.getPermissionIdsByRole(ListUtil.of(role.getId()));
        return role.setPermissionNames(permissionNameByRole).setPermissions(permissionIds);
    }

    @Override
    public Boolean bindPermission(Long roleId, Long permissionId) {
        RolePermission rolePermission = new RolePermission().setPermissionId(permissionId).setRoleId(roleId);
        return rolePermissionService.save(rolePermission);
    }

    @Override
    public Boolean unbindPermission(Long roleId, Long permissionId) {
        return rolePermissionService.unbind(roleId,permissionId);
    }

    @Override
    public List<Long> getRoleIdsByUser(String s) {
        return getRoleByUser(s).stream().map(Role::getId).toList();
    }

    @Override
    public void removeRole(Long roleId) {
        unbindPermission(roleId);
        removeById(roleId);
    }

    @Override
    public Boolean updateRole(Role role) {
        if(!role.getPermissions().isEmpty()){
            return bindPermission(role.getId(),role.getPermissions());
        }
        return updateById(role);
    }
}




