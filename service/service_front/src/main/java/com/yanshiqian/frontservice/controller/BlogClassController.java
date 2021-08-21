package com.yanshiqian.frontservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanshiqian.commonutils.R;
import com.yanshiqian.frontservice.entity.BlogClass;
import com.yanshiqian.frontservice.entity.BlogContent;
import com.yanshiqian.frontservice.service.BlogClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/frontservice/blogclass")
public class BlogClassController {
    @Autowired
    private BlogClassService blogClassService;

    @Cacheable(key = "'classList'",value = "bannerClass")
    @GetMapping("getClass")
    public R getAllClass(){
        QueryWrapper<BlogClass> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id","0");
        List<BlogClass> list = blogClassService.list(wrapper);
        return R.ok().data("data",list);
    }


}

