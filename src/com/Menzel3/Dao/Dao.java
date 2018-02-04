package com.Menzel3.Dao;

import java.sql.Connection;
import java.util.List;

/**
 * 访问数据的 Dao接口,其定义访问数据表的各种方法 
 * @author White3
 *
 */
interface Dao <T>{
	/**
	 * 批量处理SQL语句
	 * @param connection
	 * @param sql
	 * @param args: 填充占位符, Object[]可变参数
	 */
	void batch(Connection connection, String sql,
			Object[] ... args);
	
	/**
	 * 返回所查询的具体属性值
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 */
	<E> E getForValue(Connection connection, String sql,
			Object ... args);
	
	/**
	 * 查询结果为 T的一个集合
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 */
	List<T> getForList(Connection connection, String sql,
			Object ... args);
	
	/**
	 * 返回所查询的 T对象
	 * @param connection: 数据库连接
	 * @param sql: SQL语句
	 * @param orgs: 填充占位符
	 * @return 
	 */
	T get(Connection connection, String sql,
			Object ... orgs);
	
	/**
	 * DELETE, UPDATE, INSERT
	 * @param connection: 数据库连接
	 * @param sql: SQL语句
	 * @param orgs: 填充占位符
	 */
	void update(Connection connection, String sql,
			Object ... orgs);
	
}
