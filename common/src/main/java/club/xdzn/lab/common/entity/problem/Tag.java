package club.xdzn.lab.common.entity.problem;

import club.xdzn.lab.common.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @TableName tag
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="tag")
@Data
public class Tag extends BaseEntity implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标签名字
     */
    @TableField(value = "name")
    private String name;

    /**
     * 标签颜色
     */
    @TableField(value = "color")
    private String color;

    @TableField(value = "gid")
    private Long gid;


    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}