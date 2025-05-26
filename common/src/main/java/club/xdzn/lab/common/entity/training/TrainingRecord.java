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
 * @TableName training_record
 */
@TableName(value ="training_record")
@Data
public class TrainingRecord implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "tid")
    private Long tid;

    @TableField(value = "tpid")
    private Long tpid;

    @TableField(value = "pid")
    private Long pid;


    @TableField(value = "uid")
    private String uid;

    @TableField(value = "submit_id")
    private Long submitId;

    @TableField(value = "gmt_create")
    private Date gmtCreate;

    @TableField(value = "gmt_modified")
    private Date gmtModified;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}