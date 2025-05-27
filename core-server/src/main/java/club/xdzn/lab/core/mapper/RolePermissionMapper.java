package club.xdzn.lab.core.mapper;

import club.xdzn.lab.common.entity.user.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    List<String> selectPermissionNameByRole(Long roleId);
}




