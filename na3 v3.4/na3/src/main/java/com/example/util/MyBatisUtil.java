package com.example.util;
/*
 * MyBatis工具类MyBatisUtil
 * 获取访问数据库的会话对象
 * 使用方法：MyBatisUtil.getSqlSession()
 */

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
	private static SqlSessionFactory factory;
	static {
		try {
			//加载Mybatis配置、获取资源流
			
			InputStream is = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
			//构建会话工厂（连接池）
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static SqlSession getSqlSession() {
		//返回访问数据库的会话对象
		return factory.openSession();
	}
	public static void closeSqlSession(SqlSession session) {
		if (null != session)
			session.close();
	}
}


