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
 * @TableName discussion_report
 */
@TableName(value ="discussion_report")
@Data
public class DiscussionReport implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 讨论id
     */
    @TableField(value = "did")
    private Integer did;

    /**
     * 举报者的用户名
     */
    @TableField(value = "reporter")
    @Schema(description = "举报者的用户名")
    private String reporter;

    /**
     * 举报内容
     */
    @TableField(value = "content")
    @Schema(description = "举报内容")
    private String content;

    /**
     * 是否已读
     */
    @TableField(value = "status")
    private Integer status;

    @TableField(value = "gmt_create")
    private Date gmtCreate;

    @TableField(value = "gmt_modified")
    private Date gmtModified;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}