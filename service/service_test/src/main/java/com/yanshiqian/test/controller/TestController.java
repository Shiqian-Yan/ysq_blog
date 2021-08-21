package com.yanshiqian.test.controller;

import com.yanshiqian.commonutils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("testdemo")
    public R test(){
        System.out.println("测试git");
        System.out.println("haha");
        return R.ok().data("teacher","123456");

    }
}
