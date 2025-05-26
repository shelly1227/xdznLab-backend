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
 * @TableName judge_server
 */
@TableName(value ="judge_server")
@Data
public class JudgeServer implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 判题服务名字
     */
    @TableField(value = "name")
    private String name;

    /**
     * 判题机ip
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 判题机端口号
     */
    @TableField(value = "port")
    private Integer port;

    /**
     * ip:port
     */
    @TableField(value = "url")
    @Schema(description = "ip:port")
    private String url;

    /**
     * 判题机所在服务器cpu核心数
     */
    @TableField(value = "cpu_core")
    @Schema(description = "判题机所在服务器cpu核心数")
    private Integer cpuCore;

    /**
     * 当前判题数
     */
    @TableField(value = "task_number")
    @Schema(description = "当前判题数")
    private Integer taskNumber;

    /**
     * 判题并发最大数
     */
    @Schema(description = "判题并发最大数")
    @TableField(value = "max_task_number")
    private Integer maxTaskNumber;

    /**
     * 0可用，1不可用
     */
    @TableField(value = "status")
    @Schema(description = "0可用，1不可用")
    private Integer status;

    @TableField(value = "gmt_create")
    private Date gmtCreate;

    @TableField(value = "gmt_modified")
    private Date gmtModified;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}