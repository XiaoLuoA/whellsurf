package com.since.whellsurf.resolve;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author luoxinyuan
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ObjRequestBody {
    /**
     * 解析时用到的JSON的key
     */
    String value() default "";
}
