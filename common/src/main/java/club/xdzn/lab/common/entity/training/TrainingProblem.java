package club.xdzn.lab.common.entity.training;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * @TableName training_problem
 */
@TableName(value ="training_problem")
@Data
public class TrainingProblem implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 训练id
     */
    @TableField(value = "tid")
    private Long tid;

    /**
     * 题目id
     */
    @TableField(value = "pid")
    private Long pid;

    @TableField(value = "rank")
    private Integer rank;

    @TableField(value = "display_id")
    @Schema(description = "题目在训练中的展示id")
    private String displayId;

    @TableField(value = "gmt_create")
    private Date gmtCreate;

    @TableField(value = "gmt_modified")
    private Date gmtModified;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}