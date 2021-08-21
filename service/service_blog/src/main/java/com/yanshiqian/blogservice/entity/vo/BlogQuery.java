package com.yanshiqian.blogservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BlogQuery {
    @ApiModelProperty(value = "博客作者")
    private String userName;
    @ApiModelProperty(value = "博客名称,模糊查询")
    private String title;


    @ApiModelProperty(value = "摘要")
    private String description;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
