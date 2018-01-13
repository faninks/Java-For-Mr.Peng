package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

public class JDBCUtils {

	public static void main(String[] args) {
		System.out.println(get("select 姓名 name,性别 sex,年龄 age from staff where 姓名  like ?;", com.model.Staff.class, "%彭聪%"));
	}

	/**
	 * 
	 * @param sql 针对 select 类型的sql语句的执行
	 * @param clazz 查询结果的数据类
	 * @param args sql语句中的未知值 (?)
	 * @return
	 */
	public static <T> T get(String sql, Class<T> clazz, Object... args) {
		T obj = null;

		Connection conn = null;
		PreparedStatement ppst = null;
		ResultSet rs = null;
		try {
			// 1> 获取 ResultSet 对象
			conn = DBUtil.getConnection();
			ppst = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; ++i)
				ppst.setObject(i + 1, args[i]);
			rs = ppst.executeQuery();
			// 2> 获取 ResultSetMetaData 对象
			ResultSetMetaData rsmd = rs.getMetaData();
			// 3> 创建 Map<String, Object> 对象;
			// 键: sql查询项的列的别名 ; 值: 列的值
			Map<String, Object> values = new HashMap<>();
			// 4> 处理结果集
			if (rs.next())
				for (int i = 0; i < rsmd.getColumnCount(); ++i) {
					String columnLabel = rsmd.getColumnLabel(i + 1);
					Object columnValue = rs.getObject(columnLabel);
					values.put(columnLabel, columnValue);
				}
			// 5> 通过反射赋值创建得到该对象
			if (values.size() > 0) {
				obj = clazz.newInstance();
				for (Map.Entry<String, Object> entry : values.entrySet()) {
					ReflectionUtils.setFieldValue(obj, entry.getKey(), entry.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, conn, ppst);
		}
		return obj;
	}
}