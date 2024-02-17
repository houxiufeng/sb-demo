package com.example.demo.common;

import com.example.demo.annotation.Mylog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 一个aop demo
 * 正常执行顺序 around before->before->方法本身->afterReturning->after->around after
 * 异常执行顺序 around before->before->方法本身->afterThrowing->after
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class LogAdvice {
    // 定义一个切点：所有被Mylog注解修饰的方法会织入advice
    @Pointcut("@annotation(com.example.demo.annotation.Mylog)")
    private void logAdvicePointcut(){}

    @Before("logAdvicePointcut()")
    public void beforeAdvice(JoinPoint joinPoint){
        // 这里只是一个示例，你可以写任何处理逻辑
        System.out.println("---------Before触发了----------");
        // 获取签名
        Signature signature = joinPoint.getSignature();
        // 获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        // 获取即将执行的方法名
        String funcName = signature.getName();
        log.info("即将执行方法为: {}，属于{}包", funcName, declaringTypeName);

        // 如果注解加在controller方法上，还能获取一些请求信息，比如获取请求的 URL 和 IP
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        // 获取请求 URL
//        String url = request.getRequestURL().toString();
//        // 获取请求 IP
//        String ip = request.getRemoteAddr();
//        log.info("用户请求的url为：{}，ip地址为：{}", url, ip);
    }

    @Around("logAdvicePointcut()")
    public Object permissionCheckFirst(ProceedingJoinPoint joinPoint) throws Throwable {
        // 这里只是一个示例，你可以写任何处理逻辑
        System.out.println("---------Around触发了----------");
        //获取请求参数，详见接口类
        Object[] objects = joinPoint.getArgs();
        System.out.println(Arrays.toString(objects));
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Mylog annotation = method.getAnnotation(Mylog.class);
        if (annotation != null) {
            log.info("当前方法为: {}，mylog内容{}, 级别是{}", method.getName(), annotation.value(), annotation.level());
        }
        //这里可以根据具体的类型来查看或者判断某些条件
//        Integer id = ((JSONObject) objects[0]).getInteger("id");
//        DictDetailDto object1 = (DictDetailDto)objects[0];
        System.out.println("---------around before----------");
        Object proceed = joinPoint.proceed(objects);
        System.out.println("---------around after----------");
        return proceed;
    }

    @AfterReturning(pointcut  = "logAdvicePointcut()", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result){
        // 这里只是一个示例，你可以写任何处理逻辑
        System.out.println("---------AfterReturning触发了----------");
        Signature signature = joinPoint.getSignature();
        String classMethod = signature.getName();
        log.info("方法{}执行完毕，返回参数为：{}", classMethod, result);
        // 实际项目中可以根据业务做具体的返回值增强
        log.info("对返回参数进行业务上的增强：{}", result + "增强版");
    }

    @AfterThrowing(pointcut = "logAdvicePointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        System.out.println("---------afterThrowing触发了----------");
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        // 处理异常的逻辑
        log.info("执行方法{}出错，异常为：{}", method, ex);
    }

    @After("logAdvicePointcut()")
    public void afterAdvice(JoinPoint joinPoint){
        // 这里只是一个示例，你可以写任何处理逻辑
        System.out.println("---------After触发了----------");
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        log.info("方法{}已经执行完", method);
    }
}