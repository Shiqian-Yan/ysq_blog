package com.yanshiqian.frontservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanshiqian.commonutils.R;
import com.yanshiqian.frontservice.entity.BlogClass;
import com.yanshiqian.frontservice.entity.BlogContent;
import com.yanshiqian.frontservice.entity.vo.BlogQuery;
import com.yanshiqian.frontservice.mapper.BlogContentMapper;
import com.yanshiqian.frontservice.service.BlogClassService;
import com.yanshiqian.frontservice.service.BlogContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanshiqian.servicebase.exceptionHandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-15
 */
@Service
public class BlogContentServiceImpl extends ServiceImpl<BlogContentMapper, BlogContent> implements BlogContentService {
    @Autowired
    private BlogClassService blogClassService;
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
        System.out.println(total);
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("rows",records);
        return res;
    }

    @Override
    public Map<String, Object> listPageByName(String blogclass, long current, long limit) {
        QueryWrapper<BlogClass> queryWrapper = new QueryWrapper<>();
        System.out.println(blogclass);
        queryWrapper.eq("title",blogclass);
        BlogClass one = blogClassService.getOne(queryWrapper);
        if(one ==null){
            throw new GuliException(20001,"此分类不存在");
        }
        QueryWrapper<BlogClass> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("parent_id", one.getId());
        List<BlogClass> list = blogClassService.list(queryWrapper1);
        list.add(one);
        QueryWrapper<BlogContent> queryWrapper2 = new QueryWrapper<>();
        List<String> idList = new ArrayList<>();
        for(BlogClass blogClass:list){
            idList.add(blogClass.getId());
        }
        queryWrapper2.in("class_id",idList);
        Page<BlogContent> page = new Page<>(current,limit);
        baseMapper.selectPage(page,queryWrapper2);
        long total = page.getTotal();//总记录数
        List<BlogContent> records = page.getRecords();//集合
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("rows",records);
        return res;
    }

    @Override
    public Map<String, Object> listPageBySearch(String search, long current, long limit) {
        Page<BlogContent> page = new Page<>(current,limit);
        QueryWrapper<BlogContent> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(search)){
            wrapper.like("title",search).or().like("description",search)
            .or().like("content",search);
        }
        baseMapper.selectPage(page,wrapper);
        long total = page.getTotal();//总记录数
        List<BlogContent> records = page.getRecords();//集合
        System.out.println(total);
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("rows",records);
        return res;
    }
}
