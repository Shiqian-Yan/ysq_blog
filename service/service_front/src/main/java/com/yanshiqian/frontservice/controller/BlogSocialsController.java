package com.yanshiqian.frontservice.controller;


import com.yanshiqian.commonutils.R;
import com.yanshiqian.frontservice.entity.BlogSocials;
import com.yanshiqian.frontservice.service.BlogSocialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-17
 */
@RestController
@RequestMapping("/frontservice/blogsocials")
public class BlogSocialsController {
    @Autowired
    private BlogSocialsService blogSocialsService;
    @Cacheable(key = "'social'",value = "mySocial")
    @GetMapping("getAll")
    public R getAll(){
        List<BlogSocials> list = blogSocialsService.list(null);
        return R.ok().data("data",list);
    }

}

