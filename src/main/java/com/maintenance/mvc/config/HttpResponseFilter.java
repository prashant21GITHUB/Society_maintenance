package com.maintenance.mvc.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 *
 * @author bajpai
 */
@ControllerAdvice
public class HttpResponseFilter implements ResponseBodyAdvice<Object>{

    public boolean supports(MethodParameter mp, Class<? extends HttpMessageConverter<?>> type) {
        return true;
    }

    public Object beforeBodyWrite(Object body, MethodParameter mp, MediaType mt, Class<? extends HttpMessageConverter<?>> type, ServerHttpRequest request, ServerHttpResponse response) {
//        response.getHeaders().add("Access-Control-Allow-Origin", "*");
//        response.getHeaders().add("Access-Control-Allow-Methods",
//                "GET, POST");
//        response.getHeaders().
        response.getHeaders().set("Access-Control-Allow-Origin", request.getHeaders().getAccessControlAllowOrigin());
        response.getHeaders().set("Access-Control-Allow-Methods", "GET, POST");
    //        response.getHeaders().set("Access-Control-Allow-Methods", "GET POST");
//        response.getHeaders().setAccessControlAllowOrigin(response.getHeaders().);
//        System.out.println(response.getHeaders().getAccessControlRequestHeaders());
//        System.out.println(response.getHeaders().getAccessControlAllowMethods());
        return body;
    }
    
}
