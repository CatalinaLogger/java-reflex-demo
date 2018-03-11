package com.example.demo1;

import org.junit.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TestAnnotation {

    @Retention(RetentionPolicy.RUNTIME)
    @interface MyAnnotation {
        String name() default "default value";
        String value();
    }

    @MyAnnotation(value="custom value")
    class TestClass {

    }

    @Test
    public void testGetAnnotation() {
        TestClass tc = new TestClass();
        Class<?> cls = tc.getClass();
        MyAnnotation annotations = cls.getAnnotation(MyAnnotation.class);
        System.out.println(annotations.name());
        System.out.println(annotations.value());
    }

}
