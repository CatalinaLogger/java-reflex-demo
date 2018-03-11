package com.example.demo1;

import org.junit.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * IDE代码提示原理展示
 */
public class TestConstructor {
    class ClassDemo {
        private String name;
        private Double value;
        public ClassDemo(String name) throws NullPointerException, ClassNotFoundException, Exception{
            this.name = name;
        }
        public ClassDemo(String name, Double value) {
            this.name = name;
            this.value = value;
        }
        @Override
        public String toString() {
            return "ClassDemo{" +
                    "name='" + name + '\'' +
                    ", value=" + value +
                    '}';
        }
    }

    @Test
    public void testConstructor() throws ClassNotFoundException {
        Class<?> cls = Class.forName("com.example.demo1.TestConstructor$ClassDemo");
        Constructor<?>[] cons = cls.getConstructors();// 取得所有构造器
        for (int i=0; i<cons.length; i++) {
            System.out.print(Modifier.toString(cons[i].getModifiers()) + " ");
            System.out.print(cons[i].getName() + "(");
            Class<?>[] params = cons[i].getParameterTypes();
            for (int j=0; j<params.length; j++) {
                System.out.print(params[j].getSimpleName() + " arg" + j);
                if (j < params.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print(")");
            Class<?>[] exps = cons[i].getExceptionTypes();
            for (int k=0; k<exps.length; k++) {
                System.out.print(exps[k].getSimpleName());
                if (k < exps.length - 1) {
                    System.out.println(",");
                }
            }
            System.out.println();
        }
    }
    @Test
    public void testNewClassDemo() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cls = Class.forName("com.example.demo1.TestConstructor$ClassDemo");
        Constructor<?> cons = cls.getConstructor(TestConstructor.class, String.class, Double.class);
        ClassDemo demo = (ClassDemo) cons.newInstance(null, "java开发", 79.9);
        System.out.println(demo.toString());
    }

}
