package com.yanshiqian.blogservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanshiqian.blogservice.entity.BlogContent;
import com.yanshiqian.blogservice.entity.BlogMessage;
import com.yanshiqian.blogservice.mapper.BlogMessageMapper;
import com.yanshiqian.blogservice.service.BlogMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-19
 */
@Service
public class BlogMessageServiceImpl extends ServiceImpl<BlogMessageMapper, BlogMessage> implements BlogMessageService {

    @Override
    public Map<String, Object> listAll(long current, long limit) {
        Page<BlogMessage> page = new Page<>(current,limit);
        baseMapper.selectPage(page,null);
        long total = page.getTotal();//总记录数
        List<BlogMessage> records = page.getRecords();//集合
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("rows",records);
        return res;
    }
}
