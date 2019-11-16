package com.skylight.blog.controller;

import com.skylight.blog.exception.MyException;
import com.skylight.blog.mapper.LabelMapper;
import com.skylight.blog.model.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试控制器
 */
@Controller
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    LabelMapper labelMapper;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        logger.info("HelloController里的日志输出！info");
        return "Hello MyBlog!";
    }

    @RequestMapping("/myError")
    public String test(){
        return "error/404";
    }

    @RequestMapping("/wrong")
    public int zero(){
        return 1/0;
    }

    @RequestMapping("/exception")
    public String exception() throws Exception {
        logger.info("访问/hello时抛出了异常！");
        throw new Exception("访问/hello时抛出了异常！");
    }

    @RequestMapping("/myException")
    public String myException() throws MyException {
        logger.info("访问/hello时抛出了自定义异常！");
        throw new MyException("666","这里是自定义异常！");
    }

    /*
    @RequestMapping("/redis")
    @ResponseBody
    public List<Label> getAllStudent() {
        //查询缓存
        List<Label> labelList= (List<Label>)redisTemplate.opsForValue().get("labelList");
        if(null == labelList) {
            //缓存为空，查询一遍数据库
            labelList = labelMapper.getLabelList();
            //把数据库查询出来数据，放入Redis中
            redisTemplate.opsForValue().set("labelList",labelList);

            logger.info("从数据库中获取数据了~");
        }

        logger.info("调用 Redis 接口了~");

        return labelList;
    }
    */
}