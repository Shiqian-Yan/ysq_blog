package com.yanshiqian.blogclass.controller;


import com.yanshiqian.blogclass.entity.BlogChapter;
import com.yanshiqian.blogclass.entity.BlogClass;
import com.yanshiqian.blogclass.service.BlogClassService;
import com.yanshiqian.commonutils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-14
 */
@RestController
@RequestMapping("/blogclass/class")
public class BlogClassController {
    @Autowired
    private BlogClassService blogClassService;
    @GetMapping("getAllClass")
    public R getAllClass(){
        List<BlogClass> res = blogClassService.getAllChapterClass();
        return R.ok().data("children",res);
    }
    @GetMapping("getClass")
    public R getClassAll(){
        List<BlogClass> list = blogClassService.list(null);
        return R.ok().data("data",list);
    }
    @PostMapping("addClass")
    public R addClass(@RequestBody BlogClass blogClass){
        blogClass.setParentId("0");
        blogClassService.save(blogClass);
        return R.ok();
    }
    @ApiOperation(value = "修改分类")
    @PostMapping("updateClass")
    public R updateById(@RequestBody BlogClass blogClass) {
        blogClassService.updateById(blogClass);
        return R.ok();
    }
    @GetMapping("findById/{id}")
    public R findById(@PathVariable String id){
        BlogClass service = blogClassService.getById(id);
        return R.ok().data("data",service);
    }
    @DeleteMapping("{id}")
     public R deleteById(@PathVariable String id){
        blogClassService.deleteClassById(id);
         return R.ok();
    }
    @PostMapping("addsecClass/{parentId}")
    public R addSecClass(@PathVariable String parentId,@RequestBody BlogClass blogClass){
        blogClass.setParentId(parentId);
        blogClassService.save(blogClass);
        return R.ok();
    }

}

