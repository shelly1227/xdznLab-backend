package club.xdzn.lab.core.mapper;


import club.xdzn.lab.common.entity.user.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> getChildren(String parentId);

    List<Permission> getPermissionByUser(String id);
}




