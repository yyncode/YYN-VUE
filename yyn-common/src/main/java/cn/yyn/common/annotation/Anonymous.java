package cn.yyn.common.annotation;

import java.lang.annotation.*;

/**
 * 匿名访问不鉴权注解
 * 
 * @author yyncode
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Anonymous
{
}
