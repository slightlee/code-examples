package com.tomorrow.sendsmsdemo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

/**
 * TODO
 *
 * @Author Tomorrow
 * @Date 2020/5/5 11:09 下午
 */
@Service
public class SendSmsServiceImpl implements SendSmsService {


    @Override
    public boolean sendSms(String phoneNumbers,String code) {

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "换成你自己的 <accessKeyId>", "换成你自己的 <secret>");

        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        // 默认不变
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        // 自定义请求参数
        //接收短信的手机号码。
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        //短信签名名称x
        request.putQueryParameter("SignName", "Tomo博客网站");
        //短信模板ID。请在控制台模板管理页面模板CODE一列查看。
        request.putQueryParameter("TemplateCode", "SMS_189522529");
        // 短信模板变量对应的实际值，JSON格式。
        HashMap<String, Object> map = new HashMap<>();

        map.put("code",code);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));

        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject object = JSON.parseObject(response.getData());
            String result = (String) object.get("Message");
            if("OK".equals(result) || "OK" == result){
                System.out.println(response.getData()+"短信发送成功！！！");
                return true;
            }

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return false;
    }
}
