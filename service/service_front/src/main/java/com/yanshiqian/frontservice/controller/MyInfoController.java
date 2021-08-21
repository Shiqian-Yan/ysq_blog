package com.yanshiqian.frontservice.controller;


import com.yanshiqian.commonutils.R;
import com.yanshiqian.frontservice.entity.MyInfo;
import com.yanshiqian.frontservice.service.MyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-18
 */
@RestController
@RequestMapping("/frontservice/myinfo")
public class MyInfoController {
    @Autowired
    private MyInfoService myInfoService;
    @Cacheable(key = "'info'",value = "myInfo")
    @GetMapping("myInfo")
    public R myInfo(){
        MyInfo myInfo = myInfoService.getById("1");
        return R.ok().data("data",myInfo);
    }

}

