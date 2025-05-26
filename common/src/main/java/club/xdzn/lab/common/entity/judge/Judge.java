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
 * @TableName judge
 */
@TableName(value ="judge")
@Data
public class Judge implements Serializable {
    /**
     * 
     */
    @TableId(value = "submit_id", type = IdType.AUTO)
    private Long submitId;

    /**
     * 题目id
     */
    @TableId(value = "pid")
    private Long pid;

    /**
     * 题目展示id
     */
    @TableId(value = "display_pid")
    private String displayPid;

    /**
     * 用户id
     */
    @TableId(value = "uid")
    private String uid;

    /**
     * 比赛id，非比赛题目默认为0
     */
    @TableId(value = "cid")
    private Long cid;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 提交的时间
     */
    @TableField(value = "submit_time")
    private Date submitTime;

    /**
     * 结果码具体参考文档
     */
    @TableField(value = "status")
    @Schema(description = "结果码具体参考文档")
    private Integer status;

    /**
     * 0为仅自己可见，1为全部人可见。
     */
    @TableField(value = "share")
    @Schema(description = "0为仅自己可见，1为全部人可见。")
    private Integer share;

    /**
     * 错误提醒（编译错误）
     */
    @TableField(value = "error_message")
    @Schema(description = "错误提醒（编译错误）")
    private String errorMessage;

    /**
     * 运行时间(ms)
     */
    @TableField(value = "time")
    private Integer time;

    /**
     * 运行内存（kb）
     */
    @TableField(value = "memory")
    private Integer memory;

    /**
     * IO判题则不为空
     */
    @TableField(value = "score")
    @Schema(description = "IO判题则不为空")
    private Integer score;

    /**
     * 代码长度
     */
    @TableField(value = "length")
    private Integer length;

    /**
     * 代码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 代码语言
     */
    @TableField(value = "language")
    private String language;

    /**
     * 团队id，不为团队内提交则为null
     */
    @TableField(value = "gid")
    private Long gid;

    /**
     * 比赛中题目排序id，非比赛题目默认为0
     */
    @TableField(value = "cpid")
    @Schema(description = "比赛中题目排序id，非比赛题目默认为0")
    private Long cpid;

    /**
     * 判题机ip
     */
    @TableField(value = "judger")
    @Schema(description = "判题机ip")
    private String judger;

    /**
     * 提交者所在ip
     */
    @TableField(value = "ip")
    @Schema(description = "提交者所在ip")
    private String ip;

    /**
     * 乐观锁
     */
    @TableField(value = "version")
    @Schema(description = "乐观锁")
    private Integer version;

    /**
     * oi排行榜得分
     */
    @TableField(value = "oi_rank_score")
    @Schema(description = "oi排行榜得分")
    private Integer oiRankScore;

    /**
     * 是否为人工评测
     */
    @TableField(value = "is_manual")
    @Schema(description = "是否为人工评测")
    private Integer isManual;


    @TableField(value = "gmt_create")
    private Date gmtCreate;


    @TableField(value = "gmt_modified")
    private Date gmtModified;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}