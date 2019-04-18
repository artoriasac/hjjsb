package com.annotation.around;

import com.common.model.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class LogsAround {
    @Around("@annotation(com.annotation.Logs)")
    public Object doInvoke(ProceedingJoinPoint joinPoint)throws Throwable{
        try{
            return joinPoint.proceed();
        }catch (Exception t){
            return new Result<>(t.getMessage());
        }
    }
}