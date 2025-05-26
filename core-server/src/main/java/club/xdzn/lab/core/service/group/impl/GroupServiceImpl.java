package club.xdzn.lab.core.service.group.impl;

import club.xdzn.lab.common.entity.group.Group;
import club.xdzn.lab.core.mapper.GroupMapper;
import club.xdzn.lab.core.service.group.GroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author Shelly6
* @description 针对表【group】的数据库操作Service实现
* @createDate 2025-05-26 19:26:28
*/
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group>
    implements GroupService {

}



