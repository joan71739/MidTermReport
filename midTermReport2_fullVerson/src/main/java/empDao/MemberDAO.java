package empDao;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import empJavaBean.EmpJavaBean;

public class MemberDAO {
	private Connection conn;

	public MemberDAO() {

	}

	// 開連線
//	public void createConn() throws SQLException {
//		String urlString = "jdbc:sqlserver://DESKTOP-6B3HJOF\\\\SQLEXPRESS:1433;databaseName=123;user=sa;password=s12369874";
//		conn = DriverManager.getConnection(urlString);
//		boolean status =! conn.isClosed();
//		System.out.println("Connection Oepen Status: "+ status);
//		 InitialContext ctxt;
//		try {
//			ctxt = new InitialContext();
//			DataSource ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/EmployeeDB");  // for MS SQL Server
//			conn = ds.getConnection();
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//	}
	public void setConnection(Connection conn) {
		System.out.println("123");
		this.conn = conn;

	}

	// 關連線
	public void closeConn() throws SQLException {
		if (conn != null) {
			conn.close();
			System.out.println("Close Connection Finish!");

		}
	}

	// 存取資料庫值
	public List<EmpJavaBean> queryAll() {
		String sqlString = "select * from EMP";

		PreparedStatement preState = null;
		ResultSet rs = null;
		List<EmpJavaBean> memberArrayList = new ArrayList<>();

		try {
			preState = conn.prepareStatement(sqlString);
			rs = preState.executeQuery();
			EmpJavaBean javaBean;
			while (rs.next()) {
				javaBean = new EmpJavaBean();
				javaBean.setId(rs.getInt("id"));
				javaBean.setName(rs.getString("name"));
				javaBean.setJob(rs.getString("job"));
				javaBean.setHiredate(rs.getString("hiredate"));
				javaBean.setSal(rs.getInt("sal"));
				memberArrayList.add(javaBean);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {

			try {
				rs.close();
				preState.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

		return memberArrayList;
	}

	// 新增會員
	public void addMember(EmpJavaBean m) throws SQLException {

		String sqlStr = "Insert into EMP (name,job,hiredate,sal) Values(?,?,?,?)";
		PreparedStatement preState = conn.prepareStatement(sqlStr);

		preState.setString(1, m.getName());
		preState.setString(2, m.getJob());
		preState.setString(3, m.getHiredate());
		preState.setInt(4, m.getSal());
		preState.execute();
		preState.close();
	}

	// 修改會員
	public void updateById(EmpJavaBean m) throws SQLException {

		String sqlStr = "Update EMP Set name=? ,job=? ,hiredate=? ,sal=? where id=?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);

		preState.setString(1, m.getName());
		preState.setString(2, m.getJob());
		preState.setString(3, m.getHiredate());
		preState.setInt(4, m.getSal());
		preState.setInt(5, m.getId());
		preState.execute();
		preState.close();
	}

	// 查詢會員
	public EmpJavaBean findById(int id) throws SQLException {
		String sqlStr = "Select * from EMP where id = ?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setInt(1, id);
		ResultSet rs = preState.executeQuery();

		EmpJavaBean m = null;

		while (rs.next()) {
			m = new EmpJavaBean();
			m.setId(rs.getInt("id"));
			m.setName(rs.getString("name"));
			m.setJob(rs.getString("job"));
			m.setHiredate(rs.getString("hiredate"));
			m.setSal(rs.getInt("sal"));
			
		}
		
		rs.close();
		preState.close();
		return m;
	}

	// 刪除會員
	public void deleteById(int id) throws SQLException {
		String sqlStr = "Delete From EMP where id =?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setInt(1, id);
		preState.execute();
		preState.close();

	}

}
