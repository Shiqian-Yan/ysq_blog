package com.yanshiqian.blogservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BlogContent对象", description="讲师")
public class BlogContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "博客ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "分类")
    private String classId;

    @ApiModelProperty(value = "摘要")
    private String description;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Boolean isDeleted;
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;


}
