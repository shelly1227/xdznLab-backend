package club.xdzn.lab.common.entity.problem;

import club.xdzn.lab.common.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @TableName problem_case
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="problem_case")
@Data
public class ProblemCase extends BaseEntity implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 题目id
     */
    @TableField(value = "pid")
    private Long pid;

    /**
     * 测试样例的输入
     */
    @TableField(value = "input")
    private String input;

    /**
     * 测试样例的输出
     */
    @TableField(value = "output")
    private String output;

    /**
     * 该测试样例的IO得分
     */
    @TableField(value = "score")
    private Integer score;

    /**
     * 0可用，1不可用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * subtask分组的编号
     */
    @TableField(value = "group_num")
    private Integer groupNum;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}