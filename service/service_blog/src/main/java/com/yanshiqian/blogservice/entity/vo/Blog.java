package com.yanshiqian.blogservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Blog {
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "分类")
    private String classId;

    @ApiModelProperty(value = "摘要")
    private String description;

    @ApiModelProperty(value = "内容")
    private String content;
}
