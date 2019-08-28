package com.example.demo.feign;

import com.example.demo.http.JsonResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "baidu-client", url = "${spring.feign.baiduClient.url")
public interface BaiduClient {
    @RequestMapping(value = "/q", method = RequestMethod.POST)
    @Headers(value = "Content-Type: application/json")
    JsonResponse createProduct(@RequestBody(required = false) String body);
}
