package club.xdzn.lab.core.mapper;

import club.xdzn.lab.common.entity.user.UserRole;
import club.xdzn.lab.common.model.dto.RoleInfoDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    RoleInfoDTO getUserRoleInfo(String id);
}




