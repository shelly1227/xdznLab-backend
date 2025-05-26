package club.xdzn.lab.common.entity.contest;

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
 * @TableName contest_register
 */
@TableName(value ="contest_register")
@Data
public class ContestRegister implements Serializable {
    /**
     * 
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
     * 默认为0表示正常，1为失效。
     */
    @TableField(value = "status")
    @Schema(description = "默认为0表示正常，1为失效。")
    private Integer status;

    /**
     * 
     */
    @TableField(value = "gmt_create")
    private Date gmtCreate;

    /**
     * 
     */
    @TableField(value = "gmt_modified")
    private Date gmtModified;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}