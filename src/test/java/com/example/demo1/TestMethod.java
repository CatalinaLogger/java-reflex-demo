package com.example.demo1;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

interface  DemoInterface{
    void print(String str);
}

abstract class DemoAbstract{
    public abstract void get();
}

class DemoClass extends DemoAbstract implements DemoInterface{
    private String name;
    public void print(String str) {
        System.out.println("Hello: "+str);
    }
    public void get() {}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

public class TestMethod {
    /**
     * 字符串首字母转大写
     * @param str
     * @return
     */
    public String headToUppercase(String str) {
        if (str == null) {
            return null;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    /**
     * 通过反射获取类的方法
     * @throws ClassNotFoundException
     */
    @Test
    public void testClassMethod() throws ClassNotFoundException {
        Class<?> cls = Class.forName("com.example.demo1.DemoClass");
        // Method[] methods = cls.getDeclaredMethods(); // 该类声明的方法
        Method[] methods = cls.getMethods(); // 该类所有的方法
        for (int i=0; i<methods.length; i++) {
            System.out.print(Modifier.toString(methods[i].getModifiers()) + " ");
            System.out.print(methods[i].getReturnType().getSimpleName() + " ");
            System.out.print(methods[i].getName() + "(");
            Class<?>[] params = methods[i].getParameterTypes();
            for (int j=0; j<params.length; j++) {
                System.out.print(params[j].getSimpleName() + " arg" + j);
                if (j<params.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print(")");
            Class<?>[] exps = methods[i].getExceptionTypes();
            if (exps.length > 0) {
                System.out.print(" throws ");
                for (int k=0; k<exps.length; k++) {
                    System.out.print(exps[k].getSimpleName());
                    if (k<exps.length-1) {
                        System.out.print(", ");
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * 通过反射调用类的方法
     * @throws Exception
     */
    @Test
    public void testInvokeMethod() throws Exception {
        String className = "com.example.demo1.DemoClass"; // 类名称
        String property = "name";                         // 属性名
        String value = "Jack Love";                       // 属性值
        Class<?> cls = Class.forName(className);
        Object o = cls.newInstance();
        Field field = cls.getDeclaredField(property);
        // Method setMethod = cls.getMethod("set" + headToUppercase(property), String.class);       // 实现的不够完美
        Method setMethod = cls.getMethod("set" + headToUppercase(property), field.getType()); // 更好的实现方式
        Method getMethod = cls.getMethod("get" + headToUppercase(property));
        setMethod.invoke(o, value);
        System.out.println(getMethod.invoke(o));
    }

    /**
     * 通过反射获取类的属性
     * @throws Exception
     */
    @Test
    public void testInvokeField() throws Exception {
        Class<?> cls = Class.forName("com.example.demo1.DemoClass");
        Object o = cls.newInstance();
        Field name = cls.getDeclaredField("name");
        name.setAccessible(true); // 取消封装属性
        name.set(o,"Jack Love");
        System.out.println(name.get(o));
    }
}


