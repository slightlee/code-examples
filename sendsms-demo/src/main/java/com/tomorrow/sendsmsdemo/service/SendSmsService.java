package com.tomorrow.sendsmsdemo.service;

/**
 * 发送短信接口
 */
public interface SendSmsService {

    boolean sendSms(String phoneNumbers,String code);

}
