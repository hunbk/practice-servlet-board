package com.bit.homework.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class SqlSessionManager {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatis/mybatis-config.xml";
        try (Reader reader = Resources.getResourceAsReader(resource)) {
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
