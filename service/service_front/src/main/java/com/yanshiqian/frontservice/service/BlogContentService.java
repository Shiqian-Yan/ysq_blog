package com.yanshiqian.frontservice.service;

import com.yanshiqian.frontservice.entity.BlogContent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yanshiqian.frontservice.entity.vo.BlogQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-15
 */
public interface BlogContentService extends IService<BlogContent> {

    Map<String, Object> listPage(long current, long limit, BlogQuery blogQuery);

    Map<String, Object> listPageByName(String blogclass, long current, long limit);

    Map<String, Object> listPageBySearch(String search, long current, long limit);
}
