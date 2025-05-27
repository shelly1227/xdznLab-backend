package club.xdzn.lab.core.controller.user;

import club.xdzn.lab.common.annotation.AccessLimit;
import club.xdzn.lab.common.controller.BaseController;
import club.xdzn.lab.common.model.dto.LoginDTO;
import club.xdzn.lab.common.model.dto.UserInfoDTO;
import club.xdzn.lab.common.model.vo.UserInfoVO;
import club.xdzn.lab.common.result.Result;
import club.xdzn.lab.core.service.user.UserInfoService;
import club.xdzn.lab.common.entity.user.UserInfo;
import cn.dev33.satoken.annotation.SaCheckLogin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 * @author shelly
 */
@RestController
@RequestMapping("/user")
@SaCheckLogin
@Tag(name = "用户模块")
public class UserController extends BaseController<UserInfoService, UserInfo, UserInfoDTO, Long> {

    @PostMapping("/login")
    @Operation(summary = "登录")
    @AccessLimit(seconds = 60, maxCount = 5)
    public Result<UserInfoVO> login(@Validated @RequestBody LoginDTO dto) {
        return Result.success(service.login(dto));
    }
    @Override
    protected Class<UserInfo> createInstance() {
        return UserInfo.class;
    }
}
