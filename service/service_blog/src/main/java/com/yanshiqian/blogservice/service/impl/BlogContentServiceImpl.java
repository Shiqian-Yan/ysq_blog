package com.yanshiqian.blogservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.yanshiqian.blogservice.entity.BlogContent;
import com.yanshiqian.blogservice.entity.vo.Blog;
import com.yanshiqian.blogservice.entity.vo.BlogQuery;
import com.yanshiqian.blogservice.mapper.BlogContentMapper;
import com.yanshiqian.blogservice.service.BlogContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanshiqian.servicebase.exceptionHandler.GuliException;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-13
 */
@Service
public class BlogContentServiceImpl extends ServiceImpl<BlogContentMapper, BlogContent> implements BlogContentService {

    @Override
    public void addBlog(Blog blog, String userId, String username) {
        BlogContent blogContent = new BlogContent();
        blogContent.setUserId(userId);
        blogContent.setUserName(username);
        BeanUtils.copyProperties(blog,blogContent);
        baseMapper.insert(blogContent);
    }

    @Override
    public void updateBlog(Blog blog, String blogid,String id) {
        BlogContent blogContent = baseMapper.selectById(blogid);
        System.out.println(blogid);
        if(blogContent ==null){
            throw new GuliException(20001,"不存在");
        }
        if(!blogContent.getUserId().equals(id)){
            throw new GuliException(20001,"无权限修改");
       }

        BeanUtils.copyProperties(blog,blogContent);
        baseMapper.updateById(blogContent);

    }
    @Override
    public Map<String, Object> listPage(long current, long limit, BlogQuery blogQuery) {
        Page<BlogContent> page = new Page<>(current,limit);
        QueryWrapper<BlogContent> wrapper = new QueryWrapper<>();
        String name = blogQuery.getUserName();
        String title = blogQuery.getTitle();
        String description = blogQuery.getDescription();
        String content = blogQuery.getContent();
        String begin = blogQuery.getBegin();
        String end = blogQuery.getEnd();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("user_name",name);
        }
        if(!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(description)){
            wrapper.eq("description",description);
        }
        if(!StringUtils.isEmpty(content)){
            wrapper.eq("content",content);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);//大于等于
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);//小于等于
        }
        baseMapper.selectPage(page,wrapper);
        long total = page.getTotal();//总记录数
        List<BlogContent> records = page.getRecords();//集合

        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("rows",records);
        return res;
    }

    @Override
    public void deleteBlog(String blogid, String id) {
        BlogContent blogContent = baseMapper.selectById(blogid);
        if(blogContent ==null){
            throw new GuliException(20001,"不存在");
        }
        if(!blogContent.getUserId().equals(id)){
            throw new GuliException(20001,"无权限删除");
        }
        baseMapper.deleteById(blogContent);//逻辑删除

    }
}
