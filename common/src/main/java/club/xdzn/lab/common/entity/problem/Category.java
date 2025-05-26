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
 * @TableName category
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="category")
@Data
public class Category extends BaseEntity implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "name")
    private String name;


    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}