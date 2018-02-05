package com.Menzel3.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.Menzel3.ctss.JdbcUtils;
import com.Utils.ReflectionUtils;

public class JdbcDaoImpl<T> implements Dao<T> {
	private QueryRunner queryRunner = null;
	private Class<T> type = null;

	public JdbcDaoImpl() {
		queryRunner = new QueryRunner();
		type = ReflectionUtils.getSuperGenericType(getClass());
	}
	
	@Override
	public void batch(Connection connection, String sql, Object[]... args) {
		try {
			queryRunner.batch(connection, sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> E getForValue(Connection connection, String sql, Object... args) {
		PreparedStatement ppst = null;
		ResultSet rs = null;
		E value = null;
		try {
			// 1> 获取 ResultSet 对象
			ppst = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; ++i)
				ppst.setObject(i + 1, args[i]);
			rs = ppst.executeQuery();
			value = rs.next() ? (E) rs.getObject(0) : null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, rs, ppst);
		}
		return value;
	}

	@Override
	public List<T> getForList(Connection connection, String sql, Object... args) {
		List<T> arr = new ArrayList<>();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		T obj = null;
		try {
			ppst = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; ++i)
				ppst.setObject(i + 1, args[i]);
			rs = ppst.executeQuery();
			// 2> 获取 ResultSetMetaData 对象
			ResultSetMetaData rsmd = rs.getMetaData();
			// 3> 创建 Map<String, Object> 对象;
			// 键: sql查询项的列的别名 ; 值: 列的值
			Map<String, Object> values = new HashMap<>();
			// 4> 处理结果集
			while (rs.next()) {
				for (int i = 0; i < rsmd.getColumnCount(); ++i) {
					String columnLabel = rsmd.getColumnLabel(i + 1);
					Object columnValue = rs.getObject(columnLabel);
					values.put(columnLabel, columnValue);
				}
				// 5> 通过反射赋值创建得到该对象
				if (values.size() > 0) {
					obj = type.newInstance();
					for (Map.Entry<String, Object> entry : values.entrySet())
						ReflectionUtils.setFieldValue(obj, entry.getKey(), entry.getValue());
				}
				values.clear();
				arr.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, rs, ppst);
		}
		return arr.size() > 0 ? arr : null;
	}

	@Override
	public T get(Connection connection, String sql, Object... orgs) {
		T obj = null;
		try {
			obj = queryRunner.query(connection, sql, new BeanHandler<>(type), orgs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public void update(Connection connection, String sql, Object... orgs) {
		try {
			queryRunner.update(connection, sql, orgs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
