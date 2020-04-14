package com.baizhi.bqs;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.0.3</version>
</dependency>
*/
public class SendSms {

    public static void sendMsg(){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4Fi9k6HFSxn5s35mGnw1", "2xzuEZoU2hqHEfLNULP2rpjODl59zt");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "13848954077");
        request.putQueryParameter("SignName", "菠小萝");
        request.putQueryParameter("TemplateCode", "SMS_187105361");
        request.putQueryParameter("TemplateParam", "{\"code\":\"567432\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            String data = response.getData();
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sendMsg();
    }
}
