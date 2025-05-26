package club.xdzn.lab.core.service.common.impl;

import club.xdzn.lab.common.entity.common.File;
import club.xdzn.lab.core.mapper.FileMapper;
import club.xdzn.lab.core.service.common.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author Shelly6
* @description 针对表【file】的数据库操作Service实现
* @createDate 2025-05-26 19:47:05
*/
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File>
    implements FileService {

}




