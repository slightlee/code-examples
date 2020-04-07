package com.tomorrow.uploaddemo.controller;

import com.tomorrow.uploaddemo.util.FileLoad;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Tomorrow
 * @Date 2020/4/6 4:58 下午
 * @Version V1.0
 */
@Controller
@RequestMapping("/api")
public class UploadController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 单文件上传
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {

        String result = FileLoad.upload(file);

        return result;
    }

    /**
     * 多文件上传
     */
    @RequestMapping("/uploads")
    @ResponseBody
    public String uploads(@RequestParam("file") MultipartFile[] file) {

        String result = FileLoad.uploads(file);

        return result;
    }

}
