package com.example.demo1;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class TestProperties {

    /**
     * 输出Properties文件
     * 自动转换编码
     */
    @Test
    public void writePropertiesFile() {
        Properties pro = new Properties();
        pro.setProperty("message.name", "com.example.demo1.News");
        pro.setProperty("city", "湖南");
        try {
            pro.store(new FileOutputStream(new File("F:"+File.separator+"info.properties")), "this is File");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取Properties文件
     * 自动转换编码
     * 通过绝对路径访问文件实现方式不够优雅
     */
    @Test
    public void loadPropertiesFile() {
        Properties pro = new Properties();
        try {
            pro.load(new FileInputStream(new File("F:"+File.separator+"info.properties")));
            System.out.println(pro.getProperty("city"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用ResourceBundle工具类读取classpath路径下的文件
     * 写入文件时建议使用Properties
     */
    @Test
    public void loadResourceBundle() {
        ResourceBundle rb = ResourceBundle.getBundle("info");
        System.out.println(rb.getString("keyName"));
    }
}
