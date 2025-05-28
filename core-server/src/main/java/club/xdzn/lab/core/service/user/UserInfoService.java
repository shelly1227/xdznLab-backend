package club.xdzn.lab.core.service.user;


import club.xdzn.lab.common.entity.user.UserInfo;
import club.xdzn.lab.common.model.dto.LoginDTO;
import club.xdzn.lab.common.model.dto.RegisterDTO;
import club.xdzn.lab.common.model.dto.RoleInfoDTO;
import club.xdzn.lab.common.model.dto.UserInfoDTO;
import club.xdzn.lab.common.model.vo.UserAdminPageVO;
import club.xdzn.lab.common.model.vo.UserInfoVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Shelly6
* @description 针对表【user_info(用户信息表)】的数据库操作Service
* @createDate 2025-05-26 17:05:15
*/
public interface UserInfoService extends IService<UserInfo> {

    UserInfoVO login(LoginDTO dto);

    void sendCode(String identifier);

    void register(RegisterDTO dto);

    void adminSave(UserInfoDTO instance);

    RoleInfoDTO getUserRoleInfo(String id);

    void updateUserInfo(UserInfoDTO instance);

    IPage<UserAdminPageVO> getAdminPage(Long pageNum, Long pageSize, String key, Integer type);
}
