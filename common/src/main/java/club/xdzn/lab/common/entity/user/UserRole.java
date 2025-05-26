package club.xdzn.lab.common.entity.user;

import club.xdzn.lab.common.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户与角色关联表
 * @TableName user_role
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="user_role")
@Data
public class UserRole extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private String uid;

    /**
     * 角色ID
     */
    private Long roleId;


    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}