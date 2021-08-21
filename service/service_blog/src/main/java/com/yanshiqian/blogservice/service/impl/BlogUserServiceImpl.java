package com.yanshiqian.blogservice.service.impl;

import com.yanshiqian.blogservice.entity.BlogUser;
import com.yanshiqian.blogservice.mapper.BlogUserMapper;
import com.yanshiqian.blogservice.service.BlogUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-19
 */
@Service
public class BlogUserServiceImpl extends ServiceImpl<BlogUserMapper, BlogUser> implements BlogUserService {

}
