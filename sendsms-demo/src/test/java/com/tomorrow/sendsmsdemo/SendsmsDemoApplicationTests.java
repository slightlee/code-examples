package com.tomorrow.sendsmsdemo;

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
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
class SendsmsDemoApplicationTests {

    /**
     * 短信发送测试
     */
    @Test
    void sendsmsdemo() {

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
        request.putQueryParameter("PhoneNumbers", "需要接收短信的手机号");
        //短信签名名称
        request.putQueryParameter("SignName", "Tomo博客网站");
        //短信模板ID。请在控制台模板管理页面模板CODE一列查看。
        request.putQueryParameter("TemplateCode", "SMS_189522529");
        // 短信模板变量对应的实际值，JSON格式。
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",123456);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));


        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData()+"短信发送成功！！！");

            /**
             *  返回数据
             *
             * {"Message":"OK","RequestId":"1A6293BA-E8C8-4F32-A679-D442507D7A51","BizId":"138424888748799794^0","Code":"OK"}短信发送成功！！！
             *
             * BizId    发送回执ID，可根据该ID在接口QuerySendDetails中查询具体的发送状态。
             * Code     请求状态码。 返回OK代表请求成功。
             * Message  状态码的描述。
             * RequestId 请求ID。
             */

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test(){
        String valueOf = String.valueOf(new Random().nextInt(999999));
        String code = UUID.randomUUID().toString().substring(0, 6);
        System.out.println(valueOf);

        String mess = "{\"Message\":\"OK\",\"RequestId\":\"1A6293BA-E8C8-4F32-A679-D442507D7A51\",\"BizId\":\"138424888748799794^0\",\"Code\":\"OK\"}";
        JSONObject object = JSON.parseObject(mess);
        String result = (String) object.get("Message");
        System.out.println(result);
    }


}
