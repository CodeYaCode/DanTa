package com.chonglangxing.www.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.chonglangxing.www.constant.DatabaseConstant;

/**
 *
 * @author LiuChen
 * @date 2016年4月27日 下午5:16:56
 *
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
	private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();
	private static String perviousDataBase = DatabaseConstant.DEFAULT_DATABASE;
	private static String currentDataBase = DatabaseConstant.DEFAULT_DATABASE;
	
	public static String getPerviousDataBase() {
		return perviousDataBase;
	}
	
	public static String getCurrentDataBase() {
		return currentDataBase;
	}
	
	public static void swapToLocal() {
		setDataSourceKey(DatabaseConstant.LOCAL_DATABASE);
	}

	public static void setDataSourceKey(String dataSource) {
		perviousDataBase = currentDataBase;
		currentDataBase = dataSource;
		dataSourceKey.set(dataSource);
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return dataSourceKey.get();
	}
	
	public enum DB {
		 Local(DatabaseConstant.LOCAL_DATABASE);
		private DB() {
			
		}
		private DB(String dbName) {
			this.dbName = dbName;
		}
		private String dbName;
		
		public String getDbName() {
			return this.dbName;
		}
		
		public void swapToDB() {
			setDataSourceKey(this.dbName);
		}
		public static DB getDBbyNode(String node) {
			for(DB db : DB.values()) {
				if(db.name().equalsIgnoreCase(node)) {
					return db;
				}
			}
			return Local;
		}
	}
}
