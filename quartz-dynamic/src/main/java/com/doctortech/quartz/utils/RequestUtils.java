package com.doctortech.quartz.utils;

import java.io.IOException;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.fluent.*;

/**
 * @Author: foo
 * @Date: 2021-10-26 15:29
 * @description:
 */
public class RequestUtils {
    /*public static void main(String[] args) {
        sendRequest();
    }*/

    public String sendRequest(String name) {

        // 2haoSearch (GET )

        try {

            // Create request
            String url = "https://openapi.2haohr.com/api/employees/search/?access_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJlaHItb3BlbmFwaSIsImlhdCI6MTYzNTI5NzM2NiwiY29tcGFueV9pZCI6IjlkNGFjNzVmZjFiNTQ2ZTY5ZmM5NGRjNDZmMTcwNWYyIiwiZXhwIjoxNjM2NTA2OTY2fQ.LOr0yV-5IURzc7SxWUXZHdjs8kZqMsbh4PKT4wO6J4A&keyword=";
            Content content = Request.Get(url + name)
                    // Fetch request and return content
                    .execute().returnContent();
            JSONObject jsonObject = JSON.parseObject(content.asString());
            JSONArray jsonArray = (JSONArray) jsonObject.getJSONObject("data").get("objects");
            String id = jsonArray.get(0).toString().split("id\":\"")[2];
            String trueid = id.split("\"}")[0];
            //String id = list.get(2);

            System.out.println(name + " id = " + trueid);
            return trueid;
            // Print content
            //System.out.println(content);
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
}


