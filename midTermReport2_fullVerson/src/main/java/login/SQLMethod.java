package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class SQLMethod {

	private Connection conn;

	public SQLMethod() {
	}

	// 開連線
	public void createConn() {
//			String urlString = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;user=sa;password=123456";

		SQLServerDataSource ds = new SQLServerDataSource();
		// 2021/7/14 冠維 連線資料庫資料	
		ds.setUser("sa");
		// "123456" -> "1234"
		ds.setPassword("1234");
		ds.setServerName("localhost");
		ds.setPortNumber(1433);
		// "SQLMember" -> "midTermReport2"
		ds.setDatabaseName("midTermReport2");
		try {
			conn = ds.getConnection();
		} catch (SQLServerException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}

	// 關連線
	public void closeConn() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Close connection!!");
		}
	}

	// 搜尋會員帳號
	public ResultSet searchMember(String mem) {
		String sqlStr = "select * from SQLMemberID where UserID = ? ";
		PreparedStatement preState = null;
		ResultSet res = null;
		try {
			preState = conn.prepareStatement(sqlStr);
			preState.setString(1, mem);
			res = preState.executeQuery();
			if (res.next()) {
				// 有這個會員
				return res;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 搜尋會員資料
	public ResultSet searchMemberData(String mem) {
		String sqlStr = "select * from SQLMemberData where UserID = ? ";
		PreparedStatement preState = null;
		ResultSet res = null;
		try {
			preState = conn.prepareStatement(sqlStr);
			preState.setString(1, mem);
			res = preState.executeQuery();
			if (res.next()) {
				// 有這個會員
				return res;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 搜尋廠商資料
	public ResultSet searchMemberDataVendor(String mem) {
		String sqlStr = "select * from SQLMemberDataVendor where UserID = ? ";
		PreparedStatement preState = null;
		ResultSet res = null;
		try {
			preState = conn.prepareStatement(sqlStr);
			preState.setString(1, mem);
			res = preState.executeQuery();
			if (res.next()) {
				// 有這個會員
				return res;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 新增帳號
	public void inisertMember(String useID, String pws, String classM, String vat) {
		// a:一般會員 b:企業會員 c:店長 d:員工
		String sqlStr = "insert into SQLMemberID (UserID, UserPaws, ClassMember, VAT) values (?, ?, ?,?);";
		PreparedStatement preState = null;
		try {
			preState = conn.prepareStatement(sqlStr);
			preState.setString(1, useID);
			preState.setString(2, pws);
			preState.setString(3, classM);
			preState.setString(4, vat);
			preState.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	// 新增會員資料
	public void inisertMemberData(String userID, String name, String birthday, String email) {
		String sqlStr = "insert into SQLMemberData (UserID, Name, Birthday, Email) values (?, ?, ?, ?)";
		PreparedStatement preState = null;
		try {
			preState = conn.prepareStatement(sqlStr);
			preState.setString(1, userID);
			preState.setString(2, name);
			preState.setString(3, birthday);
			preState.setString(4, email);
			preState.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	// 新增會員資料-廠商
	public void inisertMemberDataVendor(String userID, String vendor, String principal, String address, String email) {
		String sqlStr = "insert into SQLMemberDataVendor (UserID,Vendor,Principal,Address,Email) values (?, ?, ?, ?, ?)";
		PreparedStatement preState = null;
		try {
			preState = conn.prepareStatement(sqlStr);
			preState.setString(1, userID);
			preState.setString(2, vendor);
			preState.setString(3, principal);
			preState.setString(4, address);
			preState.setString(5, email);
			preState.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	// 更新會員密碼,統編
	public void updateMember(String userID, String pws, String classMember, String vat) {
		String sqlStr = "update SQLMemberID set UserPaws = ?,ClassMember = ?,vat = ? where UserID = ?";
		PreparedStatement preState = null;
		try {
			preState = conn.prepareStatement(sqlStr);
			preState.setString(1, pws);
			preState.setString(2, classMember);
			preState.setString(3, vat);
			preState.setString(4, userID);
			preState.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 更新會員資料
	public void updateMemberData(String userID, String name, String birthday, String email) {
		String sqlStr = "update SQLMemberData set Name = ? ,Birthday = ? ,Email = ? where UserID = ?";
		PreparedStatement preState = null;
		try {
			preState = conn.prepareStatement(sqlStr);
			preState.setString(1, name);
			preState.setString(2, birthday);
			preState.setString(3, email);
			preState.setString(4, userID);
			preState.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	// 更新會員資料-企業
		public void updateMemberDataVendor(String userID, String vendor, String principal, String address, String email) {
			String sqlStr = "update SQLMemberDataVendor set Vendor = ?,Principal = ?,Address = ?,Email = ? where UserID = ?";
			PreparedStatement preState = null;
			try {
				preState = conn.prepareStatement(sqlStr);
				preState.setString(1, vendor);
				preState.setString(2, principal);
				preState.setString(3, address);
				preState.setString(4, email);
				preState.setString(5, userID);
				preState.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	// 刪除會員
	public void deleteMember(String userID) {
		String sqlStr1 = "delete from SQLMemberID where UserID = ?";
		String sqlStr2 = "delete from SQLMemberData where UserID = ?";
		PreparedStatement preState = null;
		try {
			preState = conn.prepareStatement(sqlStr1);
			preState.setString(1, userID);
			preState.execute();
			preState = conn.prepareStatement(sqlStr2);
			preState.setString(1, userID);
			preState.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 刪除企業
	public void deleteMemberVendor(String userID) {
		String sqlStr1 = "delete from SQLMemberID where UserID = ?";
		String sqlStr2 = "delete from SQLMemberDataVendor where UserID = ?";
		PreparedStatement preState = null;
		try {
			preState = conn.prepareStatement(sqlStr1);
			preState.setString(1, userID);
			preState.execute();
			preState = conn.prepareStatement(sqlStr2);
			preState.setString(1, userID);
			preState.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
