package com.yanshiqian.frontservice.service.impl;

import com.yanshiqian.frontservice.entity.AclUser;
import com.yanshiqian.frontservice.mapper.AclUserMapper;
import com.yanshiqian.frontservice.service.AclUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-15
 */
@Service
public class AclUserServiceImpl extends ServiceImpl<AclUserMapper, AclUser> implements AclUserService {

}
