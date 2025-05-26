package club.xdzn.lab.core.service.problem.impl;

import club.xdzn.lab.common.entity.problem.Category;
import club.xdzn.lab.core.service.problem.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import club.xdzn.lab.core.mapper.CategoryMapper;
/**
* @author Shelly6
* @description 针对表【category】的数据库操作Service实现
* @createDate 2025-05-26 20:18:44
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService {

}




