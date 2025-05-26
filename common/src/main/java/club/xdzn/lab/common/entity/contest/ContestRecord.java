package club.xdzn.lab.common.entity.contest;

import club.xdzn.lab.common.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @TableName contest_record
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="contest_record")
@Data
public class ContestRecord extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 比赛id
     */
    @TableField(value = "cid")
    private Long cid;

    /**
     * 用户id
     */
    @TableField(value = "uid")
    private String uid;

    /**
     * 题目id
     */
    @TableField(value = "pid")
    private Long pid;

    /**
     * 比赛中的题目的id
     */
    @TableField(value = "cpid")
    private Long cpid;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 真实姓名
     */
    @TableField(value = "realname")
    private String realname;

    /**
     * 比赛中展示的id
     */
    @TableField(value = "display_id")
    @Schema(description = "比赛中展示的id")
    private String displayId;

    /**
     * 提交id，用于可重判
     */
    @TableField(value = "submit_id")
    @Schema(description = "提交id，用于可重判")
    private Long submitId;

    /**
     * 提交结果，0表示未AC通过不罚时，1表示AC通过，-1为未AC通过算罚时
     */
    @TableField(value = "status")
    @Schema(description = "提交结果，0表示未AC通过不罚时，1表示AC通过，-1为未AC通过算罚时")
    private Integer status;

    /**
     * 具体提交时间
     */
    @TableField(value = "submit_time")
    @Schema(description = "具体提交时间")
    private Date submitTime;

    /**
     * 相对时间，为提交时间减去比赛时间
     */
    @TableField(value = "time")
    @Schema(description = "相对时间，为提交时间减去比赛时间")
    private Long time;

    /**
     * OI比赛的得分
     */
    @TableField(value = "score")
    private Integer score;

    /**
     * 运行耗时
     */
    @TableField(value = "use_time")
    private Integer useTime;

    /**
     * 是否为一血AC(废弃)
     */
    @TableField(value = "first_blood")
    private Integer firstBlood;

    /**
     * AC是否已校验
     */
    @TableField(value = "checked")
    @Schema(description = "AC是否已校验")
    private Integer checked;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}