package com.lyr.servicezuul;

import net.minidev.json.JSONObject;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Zuul配置Hystrix
 * @ClassName MyFallbackProvider
 * @Description TODO
 * @Author LYR
 * @Date 2019/4/29 17:44
 * @Version 1.0
 **/
@Component
public class MyFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        return "eureka-client"; //服务id，可以用* 或者 null 代表所有服务都过滤
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {

        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK; //请求网关成功了，所以是ok;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                JSONObject json =new JSONObject();
                json.put("state","501");
                json.put("msg","后台接口错误");
                return new ByteArrayInputStream(json.toJSONString().getBytes("UTF-8")); //返回前端的内容
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8); //设置头
                return httpHeaders;
            }
        };
    }
}
