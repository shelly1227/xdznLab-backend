package club.xdzn.lab.common.entity.contest;

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
 * @TableName contest_problem
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="contest_problem")
@Data
public class ContestProblem extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 该题目在比赛中的顺序id
     */
    @TableField(value = "display_id")
    private String displayId;

    /**
     * 比赛id
     */
    @TableField(value = "cid")
    private Long cid;

    /**
     * 题目id
     */
    @TableField(value = "pid")
    private Long pid;

    /**
     * 该题目在比赛中的标题，默认为原名字
     */
    @TableField(value = "display_title")
    private String displayTitle;

    /**
     * 气球颜色
     */
    @TableField(value = "color")
    private String color;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}