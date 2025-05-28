package club.xdzn.lab.common.model.dto;

import club.xdzn.lab.common.entity.user.UserInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class RegisterDTO implements Serializable {

    @Nullable
    private String uuid;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    @NotBlank(message = "验证码不能为空")
    private String code;

    public static UserInfo toPo(RegisterDTO dto) {
        UserInfo po = new UserInfo();
        po.setUuid(dto.getUuid());
        po.setUsername(dto.getUsername());
        po.setPassword(dto.getPassword());
        po.setEmail(dto.getEmail());
        return po;
    }
}