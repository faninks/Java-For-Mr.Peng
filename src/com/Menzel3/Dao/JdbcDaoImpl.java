package com.Menzel3.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.Menzel3.ctss.JdbcUtils;
import com.Utils.ReflectionUtils;

public class JdbcDaoImpl<T> implements Dao<T> {
	private QueryRunner queryRunner = null;
	private Class<T> type;

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
			value = rs.next()?(E)rs.getObject(0):null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, rs, ppst);
		}
		return value;
	}

	@Override
	public List<T> getForList(Connection connection, String sql, Object... args) {

		return null;
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
