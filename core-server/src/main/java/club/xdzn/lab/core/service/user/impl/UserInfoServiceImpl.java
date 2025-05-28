package club.xdzn.lab.core.service.user.impl;

import club.xdzn.lab.common.constants.MailConstant;
import club.xdzn.lab.common.constants.RedisConstants;
import club.xdzn.lab.common.constants.RoleConstant;
import club.xdzn.lab.common.entity.user.UserInfo;
import club.xdzn.lab.common.entity.user.UserRole;
import club.xdzn.lab.common.enums.CodeEnum;
import club.xdzn.lab.common.exception.CustomException;
import club.xdzn.lab.common.model.dto.*;
import club.xdzn.lab.common.model.vo.UserAdminPageVO;
import club.xdzn.lab.common.model.vo.UserInfoVO;
import club.xdzn.lab.common.utils.RedisUtil;
import club.xdzn.lab.core.service.user.UserRoleService;
import club.xdzn.lab.core.utils.EmailUtils;
import club.xdzn.lab.core.utils.PasswordUtils;
import club.xdzn.lab.core.utils.ValidateCodeUtils;
import cn.dev33.satoken.exception.DisableServiceException;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import club.xdzn.lab.core.mapper.UserInfoMapper;
import club.xdzn.lab.core.service.user.UserInfoService;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
* @author Shelly6
* @description 针对表【user_info(用户信息表)】的数据库操作Service实现
* @createDate 2025-05-26 17:05:15
*/
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{
    private final RedisUtil redisUtil;
    private final EmailUtils emailUtils;
    private final UserRoleService userRoleService;
    @Resource
    private ThreadPoolExecutor threadPoolTaskExecutor;

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

    @Override
    public void sendCode(String identifier) {
        String code = ValidateCodeUtils.generateValidateCodeUtils(6).toString();
        String redisKey = RedisConstants.EMAIL_CODE.getKey() + identifier;
        EmailUtils.isValidEmail(identifier);
        Map<String, Object> contentMap = new HashMap<>(7);
        contentMap.put("code", code);
        CompletableFuture.runAsync(() ->emailUtils.sendHtmlMail(new MailDTO()
                .setToEmail(identifier)
                .setSubject(MailConstant.CAPTCHA)
                .setTemplate(MailConstant.USER_REGISTER_TEMPLATE)
                .setContentMap(contentMap)
        ), threadPoolTaskExecutor);
        redisUtil.set(redisKey, code);
        redisUtil.expire(redisKey, 300);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterDTO dto) throws CustomException {
        String redisKey = RedisConstants.EMAIL_CODE.getKey() + dto.getEmail();
        if (redisUtil.getObject(redisKey) == null || redisUtil.getTime(redisKey) == 0) {
            throw new CustomException(CodeEnum.CODE_EXPIRED);
        }
        if (!dto.getCode().equals(redisUtil.getObject(redisKey).toString())) {
            throw new CustomException(CodeEnum.CODE_ERROR);
        }
        checkUserData(dto.getUsername(), dto.getPassword(), dto.getEmail());
        String uuid = IdUtil.simpleUUID();
        dto.setUuid(uuid);
        dto.setPassword(PasswordUtils.encrypt(dto.getPassword().trim()));
        dto.setUsername(dto.getUsername().trim());
        dto.setEmail(dto.getEmail().trim());
        boolean save = saveUserAndRole(RegisterDTO.toPo(dto), RoleConstant.USER_ID);
        if (!save) {
            throw new CustomException("添加失败！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void adminSave(UserInfoDTO instance) throws CustomException {
        if(!EmailUtils.isValid(instance.getEmail())){
            instance.setEmail(null);
        }
        checkUserData(instance.getUsername(), instance.getPassword(), instance.getEmail());
        instance.setUuid(IdUtil.simpleUUID());
        instance.setPassword(PasswordUtils.encrypt(instance.getPassword().trim()));
        instance.setUsername(instance.getUsername().trim());
        instance.setEmail(instance.getEmail().trim());
        UserInfo userInfo = instance.toPo(UserInfo.class);
        boolean save = saveUserAndRole(userInfo, instance.getRoleId());
        if (!save) {
            throw new CustomException("添加失败！");
        }
    }

    @Override
    public RoleInfoDTO getUserRoleInfo(String id) {
        return userRoleService.getUserRoleInfo(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserInfo(UserInfoDTO instance) throws CustomException {
        if(!EmailUtils.isValid(instance.getEmail())){
            instance.setEmail(null);
        }
        checkUserData(instance.getUsername(), instance.getPassword(), instance.getEmail());
        if(StringUtils.isNotBlank(instance.getPassword())){
            instance.setPassword(PasswordUtils.encrypt(instance.getPassword().trim()));
        }
        if (!StringUtils.isEmpty(instance.getTitleName()) && instance.getTitleName().length() > 20) {
            throw new CustomException("头衔的长度建议不要超过20位");
        }
        boolean update = updateById(instance.toPo(UserInfo.class));
        boolean updateRole = true;
        RoleInfoDTO roleInfoDTO = userRoleService.getUserRoleInfo(instance.getUuid());
        if (!Objects.equals(roleInfoDTO.roleId(), instance.getRoleId())) {
            LambdaUpdateWrapper<UserRole> wrapper = new LambdaUpdateWrapper<UserRole>()
                    .eq(UserRole::getUid, instance.getUuid())
                    .eq(UserRole::getRoleId, roleInfoDTO.roleId())
                    .set(UserRole::getRoleId, instance.getRoleId());
            updateRole = userRoleService.update(wrapper);
            //TODO 通知以及删除缓存
        }
        if (!update || !updateRole) {
            throw new CustomException("更新失败！");
        }
    }

    @Override
    public IPage<UserAdminPageVO> getAdminPage(Long pageNum, Long pageSize, String key, Integer type) {
        if(type != 1 && type != 2){
            throw new CustomException("参数错误！");
        }
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<UserRole>().eq(UserRole::getIsDeleted, 0);
        if(type == 1){
            wrapper.eq(UserRole::getRoleId, RoleConstant.USER_ID);
        }else{
            wrapper.ne(UserRole::getRoleId, RoleConstant.USER_ID);
        }
        wrapper.select(UserRole::getUid,  UserRole::getRoleId);
        List<UserRole> list = userRoleService.list(wrapper).stream().distinct().toList();
        List<String> uid = list.stream().map(UserRole::getUid).toList();
        if(CollUtil.isEmpty(uid)){
            return new Page<>();
        }
        return lambdaQuery()
                .select(UserInfo::getUuid,  UserInfo::getUsername, UserInfo::getNickname,
                        UserInfo::getEmail, UserInfo::getGender, UserInfo::getTitleName,
                        UserInfo::getTitleColor, UserInfo::getStatus, UserInfo::getGmtCreate)
                .in(UserInfo::getUuid, uid)
                .like(StringUtils.isNotBlank(key), UserInfo::getUsername, key)
                .or()
                .like(StringUtils.isNotBlank(key), UserInfo::getRealname, key)
                .page(new Page<>(pageNum, pageSize))
                .convert(UserAdminPageVO::toVO);
    }

    private boolean saveUserAndRole(UserInfo userInfo, Long roleId) {
        boolean save = save(userInfo);
        boolean saveRole = userRoleService.save(new UserRole()
                .setUid(userInfo.getUuid())
                .setRoleId(roleId)
        );
        return save && saveRole;
    }

    private void checkUserData(String username, String password, String email) {
        if(StringUtils.isBlank(username)){
            throw new CustomException("用户名不能为空！");
        }
        if (username.length() > 20) {
            throw new CustomException("用户名长度不能超过20位!");
        }
        if(StringUtils.isBlank(password)){
            throw new CustomException("密码不能为空！");
        }
        if (password.length() < 6 || password.length() > 20) {
            throw new CustomException("密码长度应该为6~20位！");
        }
        Long count = lambdaQuery()
                .eq(UserInfo::getUsername, username.trim())
                .or()
                .eq(StringUtils.isNotBlank(email), UserInfo::getEmail, email.trim())
                .count();
        if (count > 0) {
            throw new CustomException("用户名或邮箱已存在！");
        }
    }

}




