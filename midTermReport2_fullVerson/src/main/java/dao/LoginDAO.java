package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

	private Connection conn;
	// 連線工具

	public LoginDAO() {

	}

	public LoginDAO(Connection conn) {
		this.conn = conn;
	}

	// 1.查詢方法：利用帳號查詢密碼
	public Boolean findByUserid(String stuserid, String stpassword) {

		String sqlStr = "select * from login where stuserid=? AND stpassword=?";

		try {
			PreparedStatement preState;
			preState = conn.prepareStatement(sqlStr);
			preState.setString(1, stuserid);
			preState.setString(2, stpassword);

			ResultSet rs = preState.executeQuery();

			
			boolean next = rs.next();

			rs.close();
			preState.close();
			
			if (next)
				return true;
			else
				return false;

		} catch (SQLException e) {
			System.out.println("帳號及密碼輸入錯誤，請重新輸入");
			return false;
		}
	}

	// 2.查詢方法：利用帳號找公司統編
	public String findCompany(String stuserid) {

		String sqlStr = "select company from login where stuserid=?";
		try {
			PreparedStatement preState;
			preState = conn.prepareStatement(sqlStr);
			preState.setString(1, stuserid);
			ResultSet rs = preState.executeQuery();

			rs.next();
			String company = rs.getString("company");
			rs.close();
			preState.close();

			return company;

		} catch (SQLException e) {
			String s = "錯誤";
			return s;

		}

	}
}
