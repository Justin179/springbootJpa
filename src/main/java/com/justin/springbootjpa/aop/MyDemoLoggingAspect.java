package com.justin.springbootjpa.aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class MyDemoLoggingAspect {

    /*
        指定的目錄下的所有類別的所有方法 (目前沒這些目錄，不過語法基本就是下面那樣)

    private Logger logger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* com.justin.springbootmall.controller.*.*(..))")
    private void forControllerPackage(){}
    @Pointcut("execution(* com.justin.springbootmall.rest.service.*.*(..))")
    private void forServicePackage(){}
    @Pointcut("execution(* com.justin.springbootmall.rest.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){
        String shortString = joinPoint.getSignature().toShortString();
        logger.info("========> in @Before: "+shortString);
    }
    */


    @Around("execution(* com.justin.springbootjpa.aop.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        String shortString = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("executing @Around on method: "+shortString);

        long begin = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed(); // 至少會跑5秒
        long end = System.currentTimeMillis();

        long duration = end - begin;
        System.out.println("Duration: "+ duration/1000.0 + " seconds");

        return proceed;
    }

    @Before("execution(public void addAccount())")
    public void beforeAddAccountAdvice(){
        System.out.println("\n====>>> Executing @Before advice on addAccount()");
    }

}
