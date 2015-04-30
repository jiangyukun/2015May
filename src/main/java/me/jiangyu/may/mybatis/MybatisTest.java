package me.jiangyu.may.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * mybatis 测试类
 * Created by jiangyukun on 2015/4/30.
 */
public class MybatisTest {
    public static void main(String[] args) {

    }

    private SqlSessionFactory getSessionFactory() throws IOException {
        Reader reader = Resources.getResourceAsReader("Configuration.xml");
        return new SqlSessionFactoryBuilder().build(reader);
    }
}
