package com.tomorrow.sendsmsdemo;

import com.tomorrow.sendsmsdemo.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @Author Tomorrow
 * @Date 2020/5/5 11:43 下午
 */
@CrossOrigin
@RestController
public class SendSmsController {

    @Autowired
    SendSmsService sendSmsService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 发送短信测试
     */
    @GetMapping("sendSms/{phoneNumbers}")
    public String sendSms(@PathVariable String phoneNumbers) {

        String code = redisTemplate.opsForValue().get(phoneNumbers);
        if(!StringUtils.isEmpty(code)){
            return  code + "验证码未过期";
        }else {
            // 随机数生成验证码
            code = String.valueOf(new Random().nextInt(999999));
            boolean result = sendSmsService.sendSms(phoneNumbers,code);
            if(result){
                redisTemplate.opsForValue().set(phoneNumbers,code,60000, TimeUnit.SECONDS);
                 return  code + "短信已发送成功";
            }else {
                return "发送失败";
            }
        }

    }
}
