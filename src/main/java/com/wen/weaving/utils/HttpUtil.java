package com.wen.weaving.utils;

import org.springframework.stereotype.Component;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class HttpUtil {

    public String doGet(String url) {
        try {
            URL thisurl = new URL(url); // 把字符串转换为URL请求地址
            HttpURLConnection connection = (HttpURLConnection) thisurl
                    .openConnection();// 打开连接
            connection.connect();// 连接会话
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("失败!");
            return null;
        }
    }

    public String doDelete(String url) {
        try {
            URL thisurl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) thisurl
                    .openConnection();
            connection.setRequestMethod("DELETE");//就是这里不一样
            connection.connect();
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            br.close();// 关闭流

            connection.disconnect();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("失败!");
            return null;
        }
    }

}
