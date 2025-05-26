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
 * @TableName contest
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="contest")
@Data
public class Contest extends BaseEntity implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 比赛创建者id
     */
    @TableField(value = "uid")
    private String uid;

    /**
     * 比赛标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 0为acm赛制，1为比分赛制
     */
    @TableField(value = "type")
    @Schema(description = "0为acm赛制，1为比分赛制")
    private Integer type;

    /**
     * 比赛说明
     */
    @TableField(value = "description")
    private String description;

    /**
     * 0为公开赛，1为私有赛（访问有密码），2为保护赛（提交有密码）
     */
    @TableField(value = "auth")
    @Schema(description = "0为公开赛，1为私有赛（访问有密码），2为保护赛（提交有密码）")
    private Integer auth;

    /**
     * 比赛密码
     */
    @TableField(value = "pwd")
    private String pwd;

    /**
     * 开始时间
     */
    @TableField(value = "start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField(value = "end_time")
    private Date endTime;

    /**
     * 比赛时长(s)
     */
    @TableField(value = "duration")
    @Schema(description = "比赛时长(s)")
    private Long duration;

    /**
     * 是否开启封榜
     */
    @TableField(value = "seal_rank")
    @Schema(description = "是否开启封榜")
    private Integer sealRank;

    /**
     * 封榜起始时间，一直到比赛结束，不刷新榜单
     */
    @TableField(value = "seal_rank_time")
    @Schema(description = "封榜起始时间，一直到比赛结束，不刷新榜单")
    private Date sealRankTime;

    /**
     * 比赛结束是否自动解除封榜,自动转换成真实榜单
     */
    @TableField(value = "auto_real_rank")
    @Schema(description = "比赛结束是否自动解除封榜,自动转换成真实榜单")
    private Integer autoRealRank;

    /**
     * -1为未开始，0为进行中，1为已结束
     */
    @TableField(value = "status")
    @Schema(description = "-1为未开始，0为进行中，1为已结束")
    private Integer status;

    /**
     * 是否可见
     */
    @TableField(value = "visible")
    @Schema(description = "是否可见")
    private Integer visible;

    /**
     * 是否打开打印功能
     */
    @TableField(value = "open_print")
    @Schema(description = "是否打开打印功能")
    private Integer openPrint;

    /**
     * 是否开启账号限制
     */
    @TableField(value = "open_account_limit")
    @Schema(description = "是否开启账号限制")
    private Integer openAccountLimit;

    /**
     * 账号限制规则
     */
    @TableField(value = "account_limit_rule")
    @Schema(description = "账号限制规则")
    private String accountLimitRule;

    /**
     * 排行榜显示（username、nickname、realname）
     */
    @TableField(value = "rank_show_name")
    @Schema(description = "排行榜显示（username、nickname、realname）")
    private String rankShowName;

    /**
     * 是否开放比赛榜单
     */
    @TableField(value = "open_rank")
    @Schema(description = "是否开放比赛榜单")
    private Integer openRank;

    /**
     * oi排行榜得分方式，Recent、Highest
     */
    @Schema(description = "oi排行榜得分方式，Recent、Highest")
    @TableField(value = "oi_rank_score_type")
    private String oiRankScoreType;


    @TableField(value = "is_group")
    private Integer isGroup;

    @TableField(value = "gid")
    private Long gid;

    /**
     * 是否允许比赛结束后进行提交
     */
    @TableField(value = "allow_end_submit")
    @Schema(description = "是否允许比赛结束后进行提交")
    private Integer allowEndSubmit;


    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}