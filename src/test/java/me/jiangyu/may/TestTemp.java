package me.jiangyu.may;

import me.jiangyu.core.util.StringUtils;

/**
 * 临时测试类
 * Created by jiangyukun on 2015/4/30.
 */
public class TestTemp {
    public static void main(String[] args) {
        System.out.println(StringUtils.isEmpty(""));
    }

    static class A {
        interface B {
            void a(Object o);

            void a(String str);
        }
    }
}
