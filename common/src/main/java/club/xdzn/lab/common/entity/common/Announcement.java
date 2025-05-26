package club.xdzn.lab.common.entity.common;

import club.xdzn.lab.common.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * 公告表
 * @TableName announcement
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="announcement")
@Data
public class Announcement extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 公告标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 公告内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 发布者id（比赛创建者或超级管理员）
     */
    @Schema(description = "发布者id")
    @TableField(value = "uid")
    private String uid;

    /**
     * 公告类型：0系统公告，1比赛公告
     */
    @TableField(value = "type")
    @Schema(description = "公告类型：0系统公告，1比赛公告")
    private Integer type;

    /**
     * 比赛id，仅当 type = 1（比赛公告）时有效
     */
    @Schema(description = "比赛id，仅当 type = 1（比赛公告）时有效")
    @TableField(value = "cid")
    private Long cid;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}