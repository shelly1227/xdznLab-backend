package club.xdzn.lab.core.service.common.impl;

import club.xdzn.lab.common.constants.RedisConstants;
import club.xdzn.lab.common.utils.RedisUtil;
import club.xdzn.lab.core.service.user.PermissionService;
import club.xdzn.lab.core.service.user.RoleService;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 权限验证配置
 * @author shelly
 */
@Component
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    private final PermissionService permissionService;
    private final RoleService roleService;
    private final RedisUtil redisUtil;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        String id = StpUtil.getLoginIdAsString();
        List<String> permissions = permissionService.getPermissionByUser(id);
        if(permissions.isEmpty()){
            return Collections.emptyList();
        }
        redisUtil.set(RedisConstants.USER_PERMISSION.getKey()+id,permissions);
        return permissions;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> roleNames =  roleService.getRoleNameByUser(loginId.toString());
        if(CollUtil.isEmpty(roleNames)){
            return Collections.emptyList();
        }
        redisUtil.set(RedisConstants.USER_ROLE.getKey()+loginId,roleNames);
        return roleNames;
    }
}
