package com.annotation.around;

import com.common.model.MemberInfo;
import com.common.model.Result;
import com.common.utils.MemberUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class LoginAround {
    @Around("@annotation(com.annotation.Login)")
    public Object doInvoke(ProceedingJoinPoint joinPoint)throws Throwable{
        try{
            MemberInfo memberInfo = MemberUtils.getMemberInfo();
            if (memberInfo==null){
                memberInfo=new MemberInfo();
                memberInfo.setId(1);
                memberInfo.setNickname("sbhjj");
                MemberUtils.setMemberInfo(memberInfo);
            }
            return joinPoint.proceed();
        }catch (Exception t){
            return new Result<>(t.getMessage());
        }
    }
}
