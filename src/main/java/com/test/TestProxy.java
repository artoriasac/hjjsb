package com.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TestProxy implements MethodInterceptor {
    private Object target;


    public  Object getNew(Object o){
        this.target=o;
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(o.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("sbhjj start");
        Object invoke = methodProxy.invokeSuper(o,objects);
        System.out.println("sbhjj end");
        return invoke;
    }
}
