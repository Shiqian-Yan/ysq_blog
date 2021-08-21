package com.yanshiqian.blogservice.service;

import com.yanshiqian.blogservice.entity.BlogMessage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-19
 */
public interface BlogMessageService extends IService<BlogMessage> {

    Map<String, Object> listAll(long current, long limit);
}
