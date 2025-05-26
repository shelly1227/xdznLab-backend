package club.xdzn.lab.common.entity.user;

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
 * session表
 * @TableName session
 */
@TableName(value ="session")
@Data
public class Session implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String uid;

    @Schema(description = "用户代理信息")
    private String userAgent;

    @Schema(description = "用户ip")
    private String ip;

    private Date gmtCreate;

    private Date gmtModified;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}