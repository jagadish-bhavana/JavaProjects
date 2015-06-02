package com.ampolt.ascada.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;


public class Database {

	private String driver = null;
	private String url = null;
	private String user = null;
	private String password = null;

	private static Database _this;
	private Connection connection;

	private Database() {
		Properties properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream(
					"database.properties"));

			driver = properties.getProperty(
					"com.ascada.util.database.driver", "com.mysql.jdbc.Driver");
			url = properties.getProperty("com.ascada.util.database.url",
					"jdbc:mysql://localhost:3306/ASCADA?characterEncoding=UTF-8");
			user = properties.getProperty(
					"com.ascada.util.database.user", "");
			password = properties.getProperty(
					"com.ascada.util.database.password", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized Database getInstance() {
		if (_this == null) {
			_this = new Database();
		}
		return _this;
	}

	public Connection getConnection() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			return connection;
		}
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}

		return connection;
	}

	public static String makeSQLSafe(Date date) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public boolean execute(String sql) throws SQLException {
		boolean status = false;
		Connection con = null;
		con = getConnection();
		Statement stm = con.createStatement();
		status = stm.execute(sql);
		return status;
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		ResultSet rs = null;
		Connection con = getConnection();
		Statement stm = con.createStatement();
		rs = stm.executeQuery(sql);
		return rs;
	}
	
	public static void main(String args[]){
		Database db = Database.getInstance();
		Runtime runtime = Runtime.getRuntime();
		long freeMemory = runtime.freeMemory();
		long totalMemory = runtime.totalMemory();
		long maxMemory = runtime.maxMemory();
		try {
			System.out.println("sdsdsdsd");
			ResultSet resultSet = db.executeQuery("SELECT * FROM ASCADA.user");
			while (resultSet.next()) {
				System.out.println("ID" + resultSet.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Free Memory  -------- "+freeMemory);
		System.out.println("Total Memory -------- "+totalMemory);
		System.out.println("Max Memory   -------- "+maxMemory);
	}
}
