package club.xdzn.lab.common.entity.notice;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 
 * @TableName msg_remind
 */
@TableName(value ="msg_remind")
@Data
public class MsgRemind implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 动作类型，如点赞讨论帖Like_Post、点赞评论Like_Discuss、评论Discuss、回复Reply等
     */
    @TableField(value = "action")
    @Schema(name = "action", description = "动作类型，如点赞讨论帖Like_Post、点赞评论Like_Discuss、评论Discuss、回复Reply等")
    private String action;

    /**
     * 消息来源id，讨论id或比赛id
     */
    @Schema(name = "消息来源id，讨论id或比赛id")
    @TableField(value = "source_id")
    private Integer sourceId;

    /**
     * 事件源类型：'Discussion'、'Contest'等
     */
    @TableField(value = "source_type")
    @Schema(name = "事件源类型", description = "事件源类型：'Discussion'、'Contest'等")
    private String sourceType;

    /**
     * 事件源的内容，比如回复的内容，评论的帖子标题等等
     */
    @TableField(value = "source_content")
    @Schema(name = "事件源的内容", description = "比如回复的内容，评论的帖子标题等等")
    private String sourceContent;

    /**
     * 事件引用上一级评论或回复id
     */
    @TableField(value = "quote_id")
    @Schema(name = "事件引用上一级评论或回复id")
    private Integer quoteId;

    /**
     * 事件引用上一级的类型：Comment、Reply
     */
    @TableField(value = "quote_type")
    @Schema(name = "事件引用上一级的类型", description = "Comment、Reply")
    private String quoteType;

    /**
     * 事件所发生的地点链接 url
     */
    @TableField(value = "url")
    private String url;

    /**
     * 是否已读
     */
    @TableField(value = "state")
    @Schema(name = "是否已读")
    private Integer state;

    /**
     * 操作者的id
     */
    @TableField(value = "sender_id")
    @Schema(name = "操作者的id")
    private String senderId;

    /**
     * 接受消息的用户id
     */
    @TableField(value = "recipient_id")
    @Schema(name = "接受消息的用户id")
    private String recipientId;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified")
    private Date gmtModified;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}