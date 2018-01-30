package com.test;

import java.sql.Connection;

import com.Menzel3.ctss.JdbcUtils;

public class test0 {

	public static void main(String[] args) {
		System.out.println("------start connecting to server------");
		Connection conn = JdbcUtils.getConnetion();
		System.out.println(conn);
		JdbcUtils.release(conn, null, null);
		System.out.println("------close connecting to server------");
	}

}