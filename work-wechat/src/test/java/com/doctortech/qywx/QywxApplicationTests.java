package com.doctortech.qywx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class QywxApplicationTests {

    @Test
    void contextLoads() {
    }

}

class QywxTest {

    String sendUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";
    String token = "X9x41jvUXH7RJlWo5fb8WF57M_FzC7dGvZmoSFfrZc9aGsmaq662IQwJ4JgfkYALERgZfz9zB0-VdNmgXynmzgBluOzUqVIzS1zJsGnfYilWlFypTl1ilX3Mwwcr1twx6zLLcvzPzV53PUa_ArErCI7D-ApYKPmt-J_J79mWTMR2f9TGJSKjdB87Knw9QUfMtKJ9IHJfgmPmsguQ5lg16A";
    String userId = "fuqiang";
    String targetUrl = sendUrl + token;

    @Test
    void sendMessage() {
        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.getForObject(targetUrl, JSONObject.class, )
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", userId);
        jsonObject.put("msgtype", "text");
        jsonObject.put("agentid", 1000010);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("content", "企业微信 Java 调用测试，嘿嘿嘿");
        jsonObject.put("text", jsonObject1);

        jsonObject.put("safe", 0);
        jsonObject.put("enable_id_trans", 0);
        jsonObject.put("enable_duplicate_check", 0);
        jsonObject.put("duplicate_check_interval", 1800);
        JSONObject response = restTemplate.postForObject(targetUrl, jsonObject, JSONObject.class);
        System.out.println("targetUrl = " + targetUrl);
        System.out.println("jsonObject = " + jsonObject);
        System.out.println("response = " + response);
    }

    @Test
    void sendMessage2() {
        String request = "{\n" +
                "    \"touser\": \"fuqiang\",\n" +
                "    \"msgtype\": \"text\",\n" +
                "    \"agentid\": 1000010,\n" +
                "    \"text\": {\n" +
                "        \"content\": \"企业微信 postman 调用测试成功，嘿嘿嘿\"\n" +
                "    },\n" +
                "    \"safe\": 0,\n" +
                "    \"enable_id_trans\": 0,\n" +
                "    \"enable_duplicate_check\": 0,\n" +
                "    \"duplicate_check_interval\": 1800\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(request);
        RestTemplate restTemplate = new RestTemplate();
        JSONObject response = restTemplate.postForObject(targetUrl, jsonObject, JSONObject.class);
        System.out.println("response = " + response);
        System.out.println("jsonObject = " + jsonObject);
    }

}
