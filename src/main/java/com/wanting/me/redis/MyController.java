package com.wanting.me.redis;


import com.wanting.me.common.ResponseResult;
import com.wanting.me.entity.My;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Slf4j
@Controller
@RequestMapping("/writer")
public class MyController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/tijiao")
    @ResponseBody
    public ResponseResult tijiao(My my) throws Exception{
        ResponseResult result = new ResponseResult();
       stringRedisTemplate.opsForValue().set("name",my.getName());
       stringRedisTemplate.opsForValue().set("major",my.getMajor());
       stringRedisTemplate.opsForValue().set("xiaoxue",my.getXiaoxue());
       stringRedisTemplate.opsForValue().set("chuzhong",my.getChuzhong());
       stringRedisTemplate.opsForValue().set("gaozhong",my.getGaozhong());
       stringRedisTemplate.opsForValue().set("daxue",my.getDaxue());
       stringRedisTemplate.opsForValue().set("home",my.getHome());
       stringRedisTemplate.opsForValue().set("born",my.getBorn());
       stringRedisTemplate.opsForValue().set("xianzhuang",my.getXianzhuang());
       stringRedisTemplate.opsForValue().set("hope",my.getHope());
       stringRedisTemplate.opsForValue().set("want",my.getWant());
       return result;

    }

    @RequestMapping("/show")
    @ResponseBody
    public ResponseResult show() throws Exception{
        ResponseResult result = new ResponseResult();
        My my = new My();
        String name = stringRedisTemplate.opsForValue().get("name");
        my.setName(name);
        String major = stringRedisTemplate.opsForValue().get("major");
        my.setMajor(major);
        String xiaoxue = stringRedisTemplate.opsForValue().get("xiaoxue");
        my.setXiaoxue(xiaoxue);
        String chuzhong = stringRedisTemplate.opsForValue().get("chuzhong");
        my.setChuzhong(chuzhong);
        String gaozhong = stringRedisTemplate.opsForValue().get("gaozhong");
        my.setGaozhong(gaozhong);
        String daxue = stringRedisTemplate.opsForValue().get("daxue");
        my.setDaxue(daxue);
        String home = stringRedisTemplate.opsForValue().get("home");
        my.setHome(home);
        String born = stringRedisTemplate.opsForValue().get("born");
        my.setBorn(born);
        String xianzhuang = stringRedisTemplate.opsForValue().get("xianzhuang");
        my.setXianzhuang(xianzhuang);
        String hope = stringRedisTemplate.opsForValue().get("hope");
        my.setHope(hope);
        String want = stringRedisTemplate.opsForValue().get("want");
        my.setWant(want);
        result.setData(my);
        return result;

    }


}


