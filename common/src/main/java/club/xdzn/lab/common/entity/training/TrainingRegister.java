package club.xdzn.lab.common.entity.training;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName training_register
 */
@TableName(value ="training_register")
@Data
public class TrainingRegister implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 训练id
     */
    @TableField(value = "tid")
    private Long tid;
    /**
     * 用户id
     */
    @TableField(value = "uid")
    private String uid;
    /**
     * 是否可用
     */
    @TableField(value = "status")
    private Integer status;


    @TableField(value = "gmt_create")
    private Date gmtCreate;

    @TableField(value = "gmt_modified")
    private Date gmtModified;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}