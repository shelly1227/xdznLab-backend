package club.xdzn.lab.core.service.problem.impl;

import club.xdzn.lab.common.entity.problem.Tag;
import club.xdzn.lab.core.service.problem.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import club.xdzn.lab.core.mapper.TagMapper;
/**
* @author Shelly6
* @description 针对表【tag】的数据库操作Service实现
* @createDate 2025-05-26 20:18:44
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

}




