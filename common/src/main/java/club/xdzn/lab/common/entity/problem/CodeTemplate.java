package club.xdzn.lab.common.entity.problem;

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
 * 
 * @TableName code_template
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="code_template")
@Data
public class CodeTemplate extends BaseEntity implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "pid")
    private Long pid;


    @TableField(value = "lid")
    private Long lid;


    @TableField(value = "code")
    private String code;

    @TableField(value = "status")
    private Integer status;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}