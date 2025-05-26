package club.xdzn.lab.common.entity.judge;

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
 * @TableName judge_case
 */
@TableName(value ="judge_case")
@Data
public class JudgeCase implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 提交判题id
     */
    @TableId(value = "submit_id")
    private Long submitId;

    /**
     * 用户id
     */
    @TableId(value = "uid")
    private String uid;

    /**
     * 题目id
     */
    @TableId(value = "pid")
    private Long pid;

    /**
     * 测试样例id
     */
    @TableField(value = "case_id")
    private Long caseId;

    /**
     * 具体看结果码
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 测试该样例所用时间ms
     */
    @TableField(value = "time")
    private Integer time;

    /**
     * 测试该样例所用空间KB
     */
    @TableField(value = "memory")
    private Integer memory;

    /**
     * IO得分
     */
    @TableField(value = "score")
    private Integer score;

    /**
     * subtask分组的组号
     */
    @TableField(value = "group_num")
    @Schema(description = "subtask分组的组号")
    private Integer groupNum;

    /**
     * 排序
     */
    @TableField(value = "seq")
    @Schema(description = "排序")
    private Integer seq;

    /**
     * default,subtask_lowest,subtask_average
     */
    @TableField(value = "mode")
    @Schema(description = "default,subtask_lowest,subtask_average")
    private String mode;

    /**
     * 样例输入，比赛不可看
     */
    @TableField(value = "input_data")
    @Schema(description = "样例输入，比赛不可看")
    private String inputData;

    /**
     * 样例输出，比赛不可看
     */
    @TableField(value = "output_data")
    @Schema(description = "样例输出，比赛不可看")
    private String outputData;

    /**
     * 用户样例输出，比赛不可看
     */
    @Schema(description = "用户样例输出，比赛不可看")
    @TableField(value = "user_output")
    private String userOutput;


    @TableField(value = "gmt_create")
    private Date gmtCreate;

    @TableField(value = "gmt_modified")
    private Date gmtModified;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}