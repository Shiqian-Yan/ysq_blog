package com.yanshiqian.frontservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanshiqian.commonutils.R;
import com.yanshiqian.frontservice.entity.BlogClass;
import com.yanshiqian.frontservice.entity.BlogContent;
import com.yanshiqian.frontservice.entity.vo.BlogQuery;
import com.yanshiqian.frontservice.service.BlogClassService;
import com.yanshiqian.frontservice.service.BlogContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/frontservice/blogcontent")
public class BlogContentController {
    @Autowired
    private BlogContentService blogContentService;

    @PostMapping("list/{current}/{limit}")
    public R listBlog(@PathVariable long current,
                      @PathVariable long limit,
                      @RequestBody(required = false) BlogQuery blogQuery){

        Map<String,Object> map = blogContentService.listPage(current,limit,blogQuery);
        return R.ok().data(map);
    }
    @GetMapping("listBlogById/{id}")
    public R listBlogById(@PathVariable String id){
        BlogContent blogContent = blogContentService.getById(id);
        blogContent.setSort(blogContent.getSort()+1);
        QueryWrapper<BlogContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        blogContentService.update(blogContent,queryWrapper);
        return R.ok().data("blogcontent",blogContent);

    }
    @GetMapping("/listByClass/{blogclass}/{current}/{limit}")
    public R listByClass(@PathVariable String blogclass,@PathVariable long current,@PathVariable long limit){
        Map<String,Object> map = blogContentService.listPageByName(blogclass,current,limit);
        return R.ok().data(map);
    }
    @GetMapping("/listBySearch/{search}/{current}/{limit}")
    public R listBySearch(@PathVariable String search,@PathVariable long current,@PathVariable long limit){
        Map<String,Object> map = blogContentService.listPageBySearch(search,current,limit);
        return R.ok().data(map);
    }
}

