package club.xdzn.lab.core.service.user.impl;

import club.xdzn.lab.common.entity.user.UserInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import club.xdzn.lab.core.mapper.UserInfoMapper;
import club.xdzn.lab.core.service.user.UserInfoService;
/**
* @author Shelly6
* @description 针对表【user_info(用户信息表)】的数据库操作Service实现
* @createDate 2025-05-26 17:05:15
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}




