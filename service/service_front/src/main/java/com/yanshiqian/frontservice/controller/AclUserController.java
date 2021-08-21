package com.yanshiqian.frontservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanshiqian.commonutils.R;
import com.yanshiqian.frontservice.entity.AclUser;
import com.yanshiqian.frontservice.service.AclUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/frontservice/acluser")
public class AclUserController {
    @Autowired
    private AclUserService aclUserService;
     @GetMapping("getUsername")
    public R getUsername(){
         QueryWrapper<AclUser> queryWrapper = new QueryWrapper<>();
         queryWrapper.eq("username","admin");
         AclUser one = aclUserService.getOne(queryWrapper);
         return R.ok().data("data",one);
     }
}

