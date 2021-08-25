package com.yanshiqian.frontservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanshiqian.commonutils.R;
import com.yanshiqian.frontservice.entity.BlogComment;
import com.yanshiqian.frontservice.service.BlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-24
 */
@RestController
@RequestMapping("/frontservice/blogcomment")
public class BlogCommentController {
    @Autowired
    private BlogCommentService blogCommentService;
    @GetMapping("comment/{id}")
    public R getCommentById(@PathVariable String id){
        QueryWrapper<BlogComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("blog_id",id);
        List<BlogComment> list = blogCommentService.list(queryWrapper);
        return R.ok().data("data",list);
    }
    @PostMapping("addComment/{id}")
    public R addCommentById(@RequestBody BlogComment blogComment,@PathVariable String id){
        blogComment.setBlogId(id);
        System.out.println(blogComment);
        blogCommentService.save(blogComment);
        return R.ok();
    }
}

