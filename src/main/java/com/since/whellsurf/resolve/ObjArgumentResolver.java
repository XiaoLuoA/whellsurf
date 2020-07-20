package com.since.whellsurf.resolve;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author luoxinyuan
 * 多实体参数解析
 */
public class ObjArgumentResolver implements HandlerMethodArgumentResolver {
 
  private static final String JSON_BODY_ATTRIBUTE = "JSON_REQUEST_BODY";
 
  /**
   * 设置支持的方法参数类型
   *
   * @param parameter 方法参数
   * @return 支持的类型
   */
  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    // 支持带@MultiRequestBody注解的参数
    return parameter.hasParameterAnnotation(ObjRequestBody.class);
  }

  /**
   * 参数解析，利用fastjson
   * 注意：非基本类型返回null会报空指针异常，要通过反射或者JSON工具类创建一个空对象
   */
  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
    String jsonBody = getRequestBody(webRequest);
    JSONObject jsonObject = JSON.parseObject(jsonBody);
    // 根据@MultiRequestBody注解value作为json解析的key
    ObjRequestBody parameterAnnotation = parameter.getParameterAnnotation(ObjRequestBody.class);
    //注解的value是JSON的key
    assert parameterAnnotation != null;
    String key = parameterAnnotation.value() ;
    // 如果@MultiRequestBody注解没有设置value，则取参数名作为json解析的key
    key = StringUtils.isEmpty(key) ? parameter.getParameterName():key;
    Object value = jsonObject.get(key);
    if (value == null ) {
      throw new IllegalArgumentException(
              String.format("param %s is not present", key));
    }
    // 获取的注解后的类型
    Class<?> parameterType = parameter.getParameterType();

      if(List.class.isAssignableFrom(parameterType)){
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        Type t = parameter.getGenericParameterType();
        Class clazz = (Class) ((ParameterizedType)t).getActualTypeArguments()[0];
        return jsonArray.toJavaList(clazz);
      }
      return JSON.parseObject(value.toString(), parameterType);
  }

  /**
   * 获取请求体JSON字符串
   */
  private String getRequestBody(NativeWebRequest webRequest) {
    HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
 
    // 有就直接获取
    String jsonBody = (String) webRequest.getAttribute(JSON_BODY_ATTRIBUTE, NativeWebRequest.SCOPE_REQUEST);
    // 没有就从请求中读取
    if (jsonBody == null) {
      try {
        assert servletRequest != null;
        jsonBody = IOUtils.toString(servletRequest.getReader());
        webRequest.setAttribute(JSON_BODY_ATTRIBUTE, jsonBody, NativeWebRequest.SCOPE_REQUEST);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    return jsonBody;
  }
}
