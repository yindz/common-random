package com.apifan.common.test;

import com.apifan.common.random.source.OtherSource;

import java.io.IOException;

/**
 * @author yinzl
 * @Title com.apifan.common.test.OtherSourceTestDemo
 * @Package PACKAGE_NAME
 * @Description:
 * @email yinzl@cfzq.com
 * @date 2021/5/7 14:33
 */
public class OtherSourceTestDemo {

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 10; i++) {
            System.out.println(OtherSource.getInstance().randomChineseSentence());
        }
    }
}
