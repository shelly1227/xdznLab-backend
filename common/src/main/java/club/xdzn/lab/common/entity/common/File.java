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
 * 
 * @TableName file
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="file")
@Data
public class File extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "uid")
    @Schema(description = "用户id")
    private String uid;

    /**
     * 文件名
     */
    @TableField(value = "name")
    @Schema(description = "文件名")
    private String name;

    /**
     * 文件后缀格式
     */
    @TableField(value = "suffix")
    @Schema(description = "文件后缀格式")
    private String suffix;

    /**
     * 文件所在文件夹的路径
     */
    @TableField(value = "folder_path")
    @Schema(description = "文件所在文件夹的路径")
    private String folderPath;

    /**
     * 文件绝对路径
     */
    @TableField(value = "file_path")
    @Schema(description = "文件绝对路径")
    private String filePath;

    /**
     * 文件所属类型，例如avatar
     */
    @TableField(value = "type")
    @Schema(description = "文件所属类型，例如avatar")
    private String type;

    /**
     * 是否删除
     */
    @TableField(value = "delete")
    private Integer delete;


    @TableField(value = "gid")
    private Long gid;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}