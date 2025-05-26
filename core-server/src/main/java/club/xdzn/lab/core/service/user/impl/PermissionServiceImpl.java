package club.xdzn.lab.core.service.user.impl;

import club.xdzn.lab.common.entity.user.Permission;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import club.xdzn.lab.core.mapper.PermissionMapper;
import club.xdzn.lab.core.service.user.PermissionService;
/**
* @author Shelly6
* @description 针对表【permission】的数据库操作Service实现
* @createDate 2025-05-26 17:05:15
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}




