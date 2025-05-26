package club.xdzn.lab.core.service.user.impl;

import club.xdzn.lab.common.entity.user.UserRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import club.xdzn.lab.core.mapper.UserRoleMapper;
import club.xdzn.lab.core.service.user.UserRoleService;
/**
* @author Shelly6
* @description 针对表【user_role(用户与角色关联表)】的数据库操作Service实现
* @createDate 2025-05-26 17:05:15
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




