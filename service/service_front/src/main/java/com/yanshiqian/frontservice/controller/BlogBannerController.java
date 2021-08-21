package com.yanshiqian.frontservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanshiqian.commonutils.R;
import com.yanshiqian.frontservice.entity.BlogBanner;
import com.yanshiqian.frontservice.service.BlogBannerService;
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
 * @since 2021-08-18
 */
@RestController
@RequestMapping("/frontservice/blogbanner")
public class BlogBannerController {
    @Autowired
    private BlogBannerService blogBannerService;

    @Cacheable(key = "'mainPage'",value = "banner")
    @GetMapping("getBanner")
    public R getBanner(){
        QueryWrapper<BlogBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title","ysqblog");
        BlogBanner banner = blogBannerService.getOne(queryWrapper);
        return R.ok().data("data",banner);
    }

}

