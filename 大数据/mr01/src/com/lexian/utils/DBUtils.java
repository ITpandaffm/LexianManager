package com.lexian.utils;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;

public class DBUtils {
	
	private static QueryRunner queryRunner = new QueryRunner();
	private static String deleteSql="delete from ";
	
	public static void clearTableRecord(String tableName) {
		Connection connection = null;
		
		try {
			connection = JDBCUtils.getConnection();
			queryRunner.update(connection, deleteSql+tableName, null);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.release(connection);
		}
	}
}
