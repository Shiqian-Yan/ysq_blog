package com.yanshiqian.aclservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanshiqian.aclservice.entity.User;
import com.yanshiqian.aclservice.service.UserService;
import com.yanshiqian.blogservice.entity.*;
import com.yanshiqian.blogservice.entity.vo.Blog;
import com.yanshiqian.blogservice.entity.vo.BlogQuery;
import com.yanshiqian.blogservice.service.*;
import com.yanshiqian.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-13
 */
@RestController
@RequestMapping("/admin/acl/content")
public class BlogController {
    @Autowired
    private BlogContentService blogContentService;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogUserService blogUserService;
    @Autowired
    private BlogBannerService blogBannerService;
    @Autowired
    private BlogSocialsService blogSocialsService;
    @Autowired
    private BlogMessageService blogMessageService;
    //发表博客
    @PreAuthorize("hasAuthority('blog.add')")
    @PostMapping("add")
    public R addBlog(@RequestBody Blog blog){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.selectByUsername(username);
        blogContentService.addBlog(blog,user.getId(),username);
        return R.ok();
    }
    //更新博客
    @PreAuthorize("hasAuthority('blog.update')")
    @PostMapping("update/{blogid}")
    public R updateBlog(@RequestBody Blog blog,@PathVariable String blogid){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.selectByUsername(username);
        blogContentService.updateBlog(blog,blogid,user.getId());
        return R.ok();
    }
    //列表博客
    @PreAuthorize("hasAuthority('blog.list')")
    @PostMapping("list/{current}/{limit}")
    public R listBlog(@PathVariable long current,
                      @PathVariable long limit,
                      @RequestBody(required = false) BlogQuery blogQuery){

        Map<String,Object> map = blogContentService.listPage(current,limit,blogQuery);
        return R.ok().data(map);
    }
    @PreAuthorize("hasAuthority('blog.list')")
    @GetMapping("listBlogById/{id}")
    public R listBlogById(@PathVariable String id){
        BlogContent blogContent = blogContentService.getById(id);
        return R.ok().data("blogcontent",blogContent);

    }
    @PreAuthorize("hasAuthority('blog.remove')")
    @DeleteMapping("delete/{blogid}")
    public R deleteBlog(@PathVariable String blogid){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.selectByUsername(username);
        blogContentService.deleteBlog(blogid,user.getId());
        return R.ok();
    }
    @PreAuthorize("hasAuthority('daily.list')")
    @GetMapping("getNumber")
    public R getNumber(){
        List<BlogContent> list = blogContentService.list(null);
        List<String> title = new ArrayList<>();
        List<Integer> num = new ArrayList<>();
        for(BlogContent blogContent:list){
            title.add(blogContent.getTitle());
            num.add(blogContent.getSort());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("x",title);
        map.put("y",num);
        return R.ok().data(map);
    }
    @PreAuthorize("hasAuthority('bloguser.update')")
    @PostMapping("updateUser")
    public R updateUser(@RequestBody BlogUser blogUser){
        System.out.println(blogUser);
        QueryWrapper<BlogUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id","1");
        blogUserService.update(blogUser,queryWrapper);
        return R.ok();
    }
    @PreAuthorize("hasAuthority('banner.update')")
    @PostMapping("updateBanner")
    public R updateBanner(@RequestBody BlogBanner banner){
        QueryWrapper<BlogBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id","1");
        blogBannerService.update(banner,queryWrapper);
        return R.ok();
    }
    @PreAuthorize("hasAuthority('social.update')")
    @PostMapping("updateSocial")
    public R updateSocial(@RequestBody BlogSocials blogSocials){
        blogSocialsService.updateById(blogSocials);
        return R.ok();
    }
    @PreAuthorize("hasAuthority('social.update')")
    @PostMapping("addSocial")
    public R addSocial(@RequestBody BlogSocials blogSocials){
        blogSocialsService.save(blogSocials);
        return R.ok();
    }
    @PreAuthorize("hasAuthority('social.delete')")
    @DeleteMapping("deleteSocial/{id}")
    public R addSocial(@PathVariable String id){
        blogSocialsService.removeById(id);
        return R.ok();
    }
    @PreAuthorize("hasAuthority('social.list')")
    @GetMapping("listSocial")
    public R listMessage(){
        List<BlogSocials> list = blogSocialsService.list(null);
        return R.ok().data("data",list);
    }
    @PreAuthorize("hasAuthority('social.list')")
    @GetMapping("getSocialInfo/{id}")
    public R getSocialInfo(@PathVariable String id){
        BlogSocials socials = blogSocialsService.getById(id);
        return R.ok().data("data",socials);
    }
    @PreAuthorize("hasAuthority('message.list')")
    @GetMapping("listMessage/{current}/{limit}")
    public R listMessage(@PathVariable long current, @PathVariable long limit){
        Page<BlogMessage> page = new Page<>(current,limit);
        Map<String,Object> map = blogMessageService.listAll(current,limit);
        return R.ok().data(map);
    }
    @PreAuthorize("hasAuthority('message.list')")
    @GetMapping("reply/{id}")
    public R reply(@PathVariable String id){
        BlogMessage byId = blogMessageService.getById(id);
        byId.setReply(!(byId.getReply()));
        blogMessageService.updateById(byId);
        return R.ok();
    }
}

