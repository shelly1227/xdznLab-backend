package club.xdzn.lab.core.service.user.impl;

import club.xdzn.lab.common.entity.user.RolePermission;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import club.xdzn.lab.core.mapper.RolePermissionMapper;
import club.xdzn.lab.core.service.user.RolePermissionService;

import java.util.List;

/**
* @author Shelly6
* @description 针对表【role_permission】的数据库操作Service实现
* @createDate 2025-05-26 17:05:15
*/
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService{

    @Override
    public List<String> getPermissionNameByRole(Long roleId) {
        return baseMapper.selectPermissionNameByRole(roleId);
    }

    @Override
    public List<Long> getPermissionIdsByRole(List<Long> roleIds) {
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(RolePermission::getPermissionId).in(RolePermission::getRoleId,roleIds);
        return baseMapper.selectObjs(wrapper)
                .stream().map(o -> Long.parseLong(o.toString()))
                .toList();
    }

    @Override
    public Boolean unbind(Long roleId, Long permissionId) {
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId,roleId).eq(RolePermission::getPermissionId,permissionId);
        return remove(wrapper);
    }
}




