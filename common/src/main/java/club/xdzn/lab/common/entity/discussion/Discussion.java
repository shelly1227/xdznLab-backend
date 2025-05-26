package club.xdzn.lab.common.entity.discussion;

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
 * @TableName discussion
 */
@TableName(value ="discussion")
@Data
public class Discussion implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类id
     */
    @TableField(value = "category_id")
    private Integer categoryId;

    /**
     * 讨论标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 讨论简介
     */
    @TableField(value = "description")
    private String description;

    /**
     * 讨论内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 关联题目id
     */
    @TableField(value = "pid")
    private String pid;

    /**
     * 发表者id
     */
    @TableField(value = "uid")
    private String uid;

    /**
     * 发表者用户名
     */
    @TableField(value = "author")
    private String author;

    /**
     * 发表讨论者头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 发表者角色
     */
    @TableField(value = "role")
    private String role;

    /**
     * 浏览数量
     */
    @TableField(value = "view_num")
    private Integer viewNum;

    /**
     * 点赞数量
     */
    @TableField(value = "like_num")
    private Integer likeNum;

    /**
     * 优先级，是否置顶
     */
    @TableField(value = "top_priority")
    private Integer topPriority;

    /**
     * 评论数量
     */
    @TableField(value = "comment_num")
    private Integer commentNum;

    /**
     * 是否封禁该讨论
     */
    @TableField(value = "status")
    private Integer status;

    @TableField(value = "gid")
    private Long gid;

    @TableField(value = "gmt_create")
    private Date gmtCreate;

    @TableField(value = "gmt_modified")
    private Date gmtModified;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}