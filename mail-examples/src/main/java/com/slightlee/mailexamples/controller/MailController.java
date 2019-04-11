package com.slightlee.mailexamples.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Auther: LIMING
 * @Email: lmm_work@163.com
 * @Date: 2019/4/11/11 13:02
 */
@RestController
public class MailController {

    @RequestMapping("index")
    public String index(){
        return "hello";
    }

    @RequestMapping("register/{id}")
    public String register(@PathVariable int id){
        return "激活成功" + id;
    }
}
