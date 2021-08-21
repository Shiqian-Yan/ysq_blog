package com.yanshiqian.frontservice.service.impl;

import com.yanshiqian.frontservice.entity.BlogMessage;
import com.yanshiqian.frontservice.mapper.BlogMessageMapper;
import com.yanshiqian.frontservice.service.BlogMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-18
 */
@Service
public class BlogMessageServiceImpl extends ServiceImpl<BlogMessageMapper, BlogMessage> implements BlogMessageService {

}
