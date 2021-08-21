package com.yanshiqian.frontservice.controller;


import com.yanshiqian.commonutils.R;
import com.yanshiqian.frontservice.entity.BlogMessage;
import com.yanshiqian.frontservice.service.BlogMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/frontservice/blogmessage")
public class BlogMessageController {
    @Autowired
    private BlogMessageService blogMessageService;
    @PostMapping("sendMessage")
    public R sendMessage(@RequestBody BlogMessage blogMessage){
        blogMessageService.save(blogMessage);
        return R.ok().data("message",blogMessage);
    }

}

