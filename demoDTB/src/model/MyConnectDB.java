package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnectDB {
	String sql;

	public MyConnectDB() {
		super();
		this.sql = "jdbc:sqlserver://localhost:1433;database=QLKho;user=luan;password=luan;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
	}
	public Connection connect() throws Exception{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection  connect = DriverManager.getConnection(this.sql);
			return connect;
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public void thucThiCauLenhSQL(String sql) throws Exception{
		Connection connect =connect();
		Statement stmt = connect.createStatement();
		stmt.executeUpdate(sql);
	}
	public ResultSet chonDuLieuTuDTB(String sql) throws Exception{
		Connection connect = connect();
		Statement stmt = connect.createStatement();
		ResultSet rs =stmt.executeQuery(sql);
		return rs;
	}
	public static void main(String[] args) throws Exception {
		MyConnectDB connect = new MyConnectDB();
		System.out.println(connect.connect());
	}
}
