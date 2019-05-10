package com.test;

import java.lang.annotation.Annotation;

public class Sbhjj {
    public static void main(String[] args) {
//       Test test=new Test();
//        TestProxy testProxy=new TestProxy();
//        Test t= (Test)testProxy.getNew(test);
//        t.test();

        Test test=new Test();
        Annotation[] annotations = test.getClass().getAnnotations();
        for (Annotation annotation:annotations){
            if (annotation instanceof TestB){

            }
        }


    }
}
