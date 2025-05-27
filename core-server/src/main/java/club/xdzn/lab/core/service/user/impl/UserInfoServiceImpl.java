package club.xdzn.lab.core.service.user.impl;

import club.xdzn.lab.common.entity.user.UserInfo;
import club.xdzn.lab.common.exception.CustomException;
import club.xdzn.lab.common.model.dto.LoginDTO;
import club.xdzn.lab.common.model.vo.UserInfoVO;
import club.xdzn.lab.core.utils.PasswordUtils;
import cn.dev33.satoken.exception.DisableServiceException;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import club.xdzn.lab.core.mapper.UserInfoMapper;
import club.xdzn.lab.core.service.user.UserInfoService;
/**
* @author Shelly6
* @description 针对表【user_info(用户信息表)】的数据库操作Service实现
* @createDate 2025-05-26 17:05:15
*/
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

    @Override
    public UserInfoVO login(LoginDTO dto) {
        dto.setPassword(dto.getPassword().trim());
        dto.setUsername(dto.getUsername().trim());
        UserInfo u = lambdaQuery()
                .eq(UserInfo::getUsername, dto.getUsername())
                .one();
        if(u == null){
            throw new CustomException("用户不存在");
        }
        if(!PasswordUtils.match(dto.getPassword(), u.getPassword())){
            throw new CustomException("密码错误");
        }
        try {
            StpUtil.checkDisable(u.getUuid());
        } catch (DisableServiceException e) {
            log.warn("用户被封禁，用户ID为：" + u.getUuid());
            throw new CustomException("当前用户已被封禁");
        }
        StpUtil.login(u.getUuid());
        return UserInfoVO.toVO(u).setToken(StpUtil.getTokenValue());
    }

}




