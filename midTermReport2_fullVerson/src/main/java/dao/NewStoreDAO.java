package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NewStoreDAO {

	private Connection conn;
	// 連線工具

	public NewStoreDAO() {

	}

	public NewStoreDAO(Connection conn) {
		this.conn = conn;
	}

	// 1.新增商店方法
	public boolean addStore(StoreBean s) {
		// SQL語法
		String sqlStr = "Insert into store(stuserid,title,manager,stadd,tel,intro) Values(?,?,?,?,?,?)";

		// preparedstatement

		PreparedStatement preState;
		try {
			preState = conn.prepareStatement(sqlStr);
			preState.setString(1, s.getStuserid());
			preState.setString(2, s.getTitle());
			preState.setString(3, s.getManager());
			preState.setString(4, s.getStadd());
			preState.setString(5, s.getTel());
			preState.setString(6, s.getIntro());

			preState.execute();
			int update = preState.getUpdateCount();
			preState.close();

			if (update >= 1)
				return true;
			else
				return false;

		} catch (SQLException e) {
			System.err.println("新增店家資料時發生錯誤:" + e);
			return false;

		}

	}

	// 2.修改方法：利用店家ID 修改店家負責人、地址、電話、介紹文、圖片
	public boolean updateStore(StoreBean s) {
		String sqlStr = "Update store Set title=?, manager=?,stadd=?,tel=?,intro=? Where stuserid=?";

		// preparedstatement

		try {
			PreparedStatement preState = conn.prepareStatement(sqlStr);
			preState.setString(1, s.getTitle());
			preState.setString(2, s.getManager());
			preState.setString(3, s.getStadd());
			preState.setString(4, s.getTel());
			preState.setString(5, s.getIntro());
//		preState.setBlob(5, s.getPhoto());
			preState.setString(6, s.getStuserid());

			preState.execute();
			int update = preState.getUpdateCount();
			preState.close();

			if (update >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.err.println("修改店家資料時發生錯誤:" + e);
			return false;

		}

	}

	// 3.查詢方法：利用店家帳號查詢店家資

	public StoreBean findByUserid(String stuserid) throws SQLException, ClassNotFoundException {
		String sqlStr = "select * from store where stuserid=?";

		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setString(1, stuserid);
		ResultSet rs = preState.executeQuery();

		// 新增參數
		StoreBean s = null;

		// date格式
		SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");

		while (rs.next()) {

			s = new StoreBean();
			s.setStoreid(rs.getString("storeid"));
			s.setStartdate(ft.format(rs.getDate("startdate")));
			s.setStuserid(rs.getString("stuserid"));
			s.setTitle(rs.getString("title"));
			s.setManager(rs.getString("manager"));
			s.setStadd(rs.getString("stadd"));
			s.setTel(rs.getString("tel"));
			s.setIntro(rs.getString("intro"));
//				s.setPhoto(rs.getBlob("photo"));

		}

		rs.close();
		preState.close();
		return s;

	}

	// 4.利用企業統編查詢店家

	public ArrayList<StoreBean> findByCompany(String company) throws SQLException, ClassNotFoundException {
		String sqlStr = "select * from store where stuserid in ( select stuserid from login where company=?)";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setString(1, company);
		ResultSet rs = preState.executeQuery();

		// 新增參數
		StoreBean s = null;
		ArrayList<StoreBean> rsData = new ArrayList<>();

		// date格式
		SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");

		while (rs.next()) {

			s = new StoreBean();
			s.setStoreid(rs.getString("storeid"));
			s.setStartdate(ft.format(rs.getDate("startdate")));
			s.setStuserid(rs.getString("stuserid"));
			s.setTitle(rs.getString("title"));
			s.setManager(rs.getString("manager"));
			s.setStadd(rs.getString("stadd"));
			s.setTel(rs.getString("tel"));
			s.setIntro(rs.getString("intro"));
//				s.setPhoto(rs.getBlob("photo"));

			rsData.add(s);
		}

		rs.close();
		preState.close();
		return rsData;

	}

	// 5.利用店家帳號刪除整筆資料

	public boolean dileteByStoreid(String stuserid) throws ClassNotFoundException {
		String sqlStr = "Delete from store where stuserid=?";

		try {
			PreparedStatement preState = conn.prepareStatement(sqlStr);
			preState.setString(1, stuserid);
			preState.execute();

			int update = preState.getUpdateCount();
			preState.close();

			if (update >= 1) {
				return true;
			} else
				return false;

		} catch (SQLException e) {
			System.err.println("新增店家資料時發生錯誤:" + e);
			return false;

		}
	}

	// 6.查詢此帳號是否已經有資料，for"新增店家資料"判斷
	public boolean findUseridinStore(String stuserid) {

		try {
			String sqlStr = "Select stuserid from store where stuserid=?";
			PreparedStatement preState = conn.prepareStatement(sqlStr);
			preState.setString(1, stuserid);
			ResultSet rs = preState.executeQuery();
			boolean boo = rs.next();
			
			rs.close();
			preState.close();
			
			if (boo) {
				return false;
			} else {
				return true;
			}
			

		} catch (SQLException e) {
			return true;
		}
	}

}
