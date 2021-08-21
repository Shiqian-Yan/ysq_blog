package com.yanshiqian.blogservice.service;

import com.yanshiqian.blogservice.entity.BlogContent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yanshiqian.blogservice.entity.vo.Blog;
import com.yanshiqian.blogservice.entity.vo.BlogQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-13
 */
public interface BlogContentService extends IService<BlogContent> {

    void addBlog(Blog blog, String userId, String name);

    void updateBlog(Blog blog,String blogid,String id);

    Map<String, Object> listPage(long current, long limit, BlogQuery blogQuery);

    void deleteBlog(String blogid, String id);
}
