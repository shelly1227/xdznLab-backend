package club.xdzn.lab.common.entity.problem;

import club.xdzn.lab.common.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @TableName language
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="language")
@Data
public class Language extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 语言类型
     */
    @TableField(value = "content_type")
    private String contentType;

    /**
     * 语言描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 语言名字
     */
    @TableField(value = "name")
    private String name;

    /**
     * 编译指令
     */
    @TableField(value = "compile_command")
    @Schema(description = "编译指令")
    private String compileCommand;

    /**
     * 模板
     */
    @TableField(value = "template")
    @Schema(description = "模板")
    private String template;

    /**
     * 语言默认代码模板
     */
    @TableField(value = "code_template")
    @Schema(description = "语言默认代码模板")
    private String codeTemplate;

    /**
     * 是否可作为特殊判题的一种语言
     */
    @TableField(value = "is_spj")
    @Schema(description = "是否可作为特殊判题的一种语言")
    private Integer isSpj;

    /**
     * 扩展信息(可能有用)
     */
    @TableField(value = "ext")
    @Schema(description = "扩展信息(可能有用)")
    private String ext;

    /**
     * 语言排序
     */
    @TableField(value = "seq")
    @Schema(description = "语言排序")
    private Integer seq;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}