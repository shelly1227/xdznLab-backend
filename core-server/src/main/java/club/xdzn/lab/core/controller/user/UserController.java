package club.xdzn.lab.core.controller.user;

import club.xdzn.lab.common.annotation.AccessLimit;
import club.xdzn.lab.common.controller.BaseController;
import club.xdzn.lab.common.model.dto.LoginDTO;
import club.xdzn.lab.common.model.dto.RegisterDTO;
import club.xdzn.lab.common.model.dto.RoleInfoDTO;
import club.xdzn.lab.common.model.dto.UserInfoDTO;
import club.xdzn.lab.common.model.vo.UserAdminPageVO;
import club.xdzn.lab.common.model.vo.UserInfoVO;
import club.xdzn.lab.common.result.PageInfo;
import club.xdzn.lab.common.result.Result;
import club.xdzn.lab.core.service.user.UserInfoService;
import club.xdzn.lab.common.entity.user.UserInfo;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 * @author shelly
 */
@RestController
@RequestMapping("/user")
@SaCheckLogin
@Tag(name = "用户模块")
public class UserController extends BaseController<UserInfoService, UserInfo, UserInfoDTO, String> {


    @PostMapping("/login")
    @Operation(summary = "登录")
    @SaIgnore
    @AccessLimit(seconds = 60, maxCount = 5)
    public Result<UserInfoVO> login(@Validated @RequestBody LoginDTO dto) {
        return Result.success(service.login(dto));
    }

    @Operation(summary = "退出登录")
    @SaIgnore
    @GetMapping("/logout")
    public Result<String> logout(){
        String id = StpUtil.getLoginIdAsString();
        StpUtil.logout(id);
        return Result.success();
    }

    @Override
    public Result<Void> save(UserInfoDTO instance) {
        service.adminSave(instance);
        return Result.success();
    }

    @Override
    public Result<Void> delete(String id) {
        //TODO 关联删除
        return super.delete(id);
    }

    @Override
    public Result<UserInfo> get(String id) {
        UserInfo userInfo = service.getById(id);
        if(userInfo == null || userInfo.getIsDeleted() == 1){
            return Result.fail("用户不存在！");
        }
        RoleInfoDTO roleInfoDTO = service.getUserRoleInfo(id);
        userInfo.setRole(roleInfoDTO.roleName());
        userInfo.setRoleId(roleInfoDTO.roleId());
        return Result.success(userInfo);
    }

    @Override
    public Result<Void> update(UserInfoDTO instance) {
        service.updateUserInfo(instance);
        return Result.success();
    }

    @Operation(summary = "发送验证码")
    @AccessLimit(seconds = 60, maxCount = 1)
    @SaIgnore
    @GetMapping("/sendCode")
    public Result<Void> sendCode(@RequestParam("identifier") String identifier){
        if(!StringUtils.hasText(identifier)){
            return Result.fail("请输入邮箱！");
        }
        service.sendCode(identifier);
        return Result.success();
    }

    @Operation(summary = "注册账号")
    @SaIgnore
    @PostMapping("/regist")
    public Result<Void> regist(@RequestBody @Validated RegisterDTO dto){
        service.register(dto);
        return Result.success();
    }

    @Operation(summary = "分页查询")
    @SaCheckPermission("admin.user.get")
    @GetMapping("/page")
    public Result<PageInfo<UserAdminPageVO>> page(@RequestParam(required = false, defaultValue = "1") Long pageNum,
                                                  @RequestParam(required = false, defaultValue = "10") Long pageSize,
                                                  @RequestParam(required = false) String key,
                                                  @RequestParam(required = false, defaultValue = "0") @Schema(description = "0:普通用户 1:非普通用户") Integer type) {
        return Result.page(service.getAdminPage(pageNum, pageSize, key, type));
    }

    @Override
    protected Class<UserInfo> createInstance() {
        return UserInfo.class;
    }
}
