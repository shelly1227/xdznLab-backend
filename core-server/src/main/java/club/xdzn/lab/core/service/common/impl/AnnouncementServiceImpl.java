package club.xdzn.lab.core.service.common.impl;

import club.xdzn.lab.common.entity.common.Announcement;
import club.xdzn.lab.core.mapper.AnnouncementMapper;
import club.xdzn.lab.core.service.common.AnnouncementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author Shelly6
* @description 针对表【announcement(公告表)】的数据库操作Service实现
* @createDate 2025-05-26 19:47:05
*/
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement>
    implements AnnouncementService {

}


