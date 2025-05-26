package club.xdzn.lab.common.entity.discussion;

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
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * null表示无引用比赛
     */
    @TableField(value = "cid")
    @Schema(description = "null表示无引用比赛")
    private Long cid;

    /**
     * null表示无引用讨论
     */
    @TableField(value = "did")
    @Schema(description = "null表示无引用讨论")
    private Integer did;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    @Schema(description = "评论内容")
    private String content;

    /**
     * 评论者id
     */
    @TableField(value = "from_uid")
    @Schema(description = "评论者id")
    private String fromUid;

    /**
     * 评论者用户名
     */
    @TableField(value = "from_name")
    @Schema(description = "评论者用户名")
    private String fromName;

    /**
     * 评论组头像地址
     */
    @TableField(value = "from_avatar")
    @Schema(description = "评论组头像地址")
    private String fromAvatar;

    /**
     * 评论者角色
     */
    @TableField(value = "from_role")
    @Schema(description = "评论者角色")
    private String fromRole;

    /**
     * 点赞数量
     */
    @TableField(value = "like_num")
    private Integer likeNum;

    /**
     * 是否封禁或逻辑删除该评论
     */
    @TableField(value = "status")
    @Schema(description = "是否封禁或逻辑删除该评论")
    private Integer status;


    @TableField(value = "gmt_create")
    private Date gmtCreate;

    @TableField(value = "gmt_modified")
    private Date gmtModified;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}