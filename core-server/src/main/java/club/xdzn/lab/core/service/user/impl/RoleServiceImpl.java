package club.xdzn.lab.core.service.user.impl;

import club.xdzn.lab.common.entity.user.Role;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import club.xdzn.lab.core.mapper.RoleMapper;
import club.xdzn.lab.core.service.user.RoleService;

/**
* @author Shelly6
* @description 针对表【role(角色表)】的数据库操作Service实现
* @createDate 2025-05-26 17:05:15
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




