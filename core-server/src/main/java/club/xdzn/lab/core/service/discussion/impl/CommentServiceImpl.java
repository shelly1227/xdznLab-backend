package club.xdzn.lab.core.service.discussion.impl;

import club.xdzn.lab.common.entity.discussion.Comment;
import club.xdzn.lab.core.mapper.CommentMapper;
import club.xdzn.lab.core.service.discussion.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
* @author Shelly6
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2025-05-26 19:30:59
*/
@Service

public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService {

}




