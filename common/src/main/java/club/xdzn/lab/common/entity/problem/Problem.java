package club.xdzn.lab.common.entity.problem;

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
 * @TableName problem
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="problem")
@Data
public class Problem extends BaseEntity implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 问题的自定义ID 例如（XDZNOJ-1000）
     */
    @TableField(value = "problem_id")
    @Schema(description = "题目的自定义ID 例如（XDZNOJ-1000）")
    private String problemId;

    /**
     * 题目
     */
    @TableField(value = "title")
    @Schema(description = "题目")
    private String title;

    /**
     * 作者
     */
    @TableField(value = "author")
    @Schema(description = "作者")
    private String author;

    /**
     * 0为ACM,1为OI
     */
    @TableField(value = "type")
    @Schema(description = "0为ACM,1为OI")
    private Integer type;

    /**
     * 单位ms
     */
    @TableField(value = "time_limit")
    @Schema(description = "单位ms")
    private Integer timeLimit;

    /**
     * 单位kb
     */
    @TableField(value = "memory_limit")
    private Integer memoryLimit;

    /**
     * 单位mb
     */
    @TableField(value = "stack_limit")
    private Integer stackLimit;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 输入描述
     */
    @TableField(value = "input")
    private String input;

    /**
     * 输出描述
     */
    @TableField(value = "output")
    private String output;

    /**
     * 题面样例
     */
    @TableField(value = "examples")
    @Schema(description = "题面样例")
    private String examples;

    /**
     * 题目难度,0简单，1中等，2困难
     */
    @TableField(value = "difficulty")
    @Schema(description = "题目难度,0简单，1中等，2困难")
    private Integer difficulty;

    /**
     * 备注,提醒
     */
    @TableField(value = "hint")
    @Schema(description = "备注,提醒")
    private String hint;

    /**
     * 默认为1公开，2为私有，3为比赛题目
     */
    @TableField(value = "auth")
    @Schema(description = "题目权限,0公开，1私有，2比赛题目")
    private Integer auth;

    /**
     * 当该题目为io题目时的分数
     */
    @TableField(value = "io_score")
    @Schema(description = "当该题目为io题目时的分数")
    private Integer ioScore;

    /**
     * 该题目对应的相关提交代码，用户是否可用分享
     */
    @TableField(value = "code_share")
    @Schema(description = "该题目对应的相关提交代码，用户是否可用分享")
    private Integer codeShare;

    /**
     * 题目评测模式,default、function
     */
    @TableField(value = "judge_mode")
    @Schema(description = "题目评测模式,default、function")
    private String judgeMode;

    /**
     * 函数程序代码
     */
    @TableField(value = "function_code")
    @Schema(description = "函数程序代码")
    private String functionCode;

    /**
     * 特判程序或交互程序代码的语言
     */
    @Schema(description = "特判程序或交互程序代码的语言")
    @TableField(value = "function_language")
    private String functionLanguage;

    /**
     * 是否默认去除用户代码的文末空格
     */
    @TableField(value = "is_remove_end_blank")
    @Schema(description = "是否默认去除用户代码的文末空格")
    private Integer isRemoveEndBlank;

    /**
     * 是否默认开启该题目的测试样例结果查看
     */
    @TableField(value = "open_case_result")
    @Schema(description = "是否默认开启该题目的测试样例结果查看")
    private Integer openCaseResult;

    /**
     * 题目测试数据是否是上传文件的
     */
    @TableField(value = "is_upload_case")
    @Schema(description = "题目测试数据是否是上传文件的")
    private Integer isUploadCase;

    /**
     * 题目测试数据的版本号
     */
    @TableField(value = "case_version")
    @Schema(description = "题目测试数据的版本号")
    private String caseVersion;

    @TableField(value = "is_group")
    private Integer isGroup;

    /**
     * 
     */
    @TableField(value = "gid")
    private Long gid;

    /**
     * 申请公开的进度：null为未申请，1为申请中，2为申请通过，3为申请拒绝
     */
    @TableField(value = "apply_public_progress")
    @Schema(description = "申请公开的进度：null为未申请，1为申请中，2为申请通过，3为申请拒绝")
    private Integer applyPublicProgress;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}