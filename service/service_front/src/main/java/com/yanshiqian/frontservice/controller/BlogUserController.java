package com.yanshiqian.frontservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanshiqian.commonutils.R;
import com.yanshiqian.frontservice.entity.BlogUser;
import com.yanshiqian.frontservice.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-17
 */
@RestController
@RequestMapping("/frontservice/bloguser")
public class BlogUserController {
    @Autowired
    private BlogUserService blogUserService;
    @GetMapping("getUser")
    @Cacheable(key = "'user'",value = "userValue")
    public R getUser(){
        QueryWrapper<BlogUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("acl_id","1");
        BlogUser one = blogUserService.getOne(queryWrapper);
        return R.ok().data("user",one);
    }

}

