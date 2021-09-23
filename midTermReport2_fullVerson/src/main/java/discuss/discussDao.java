package discuss;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class discussDao {
	private Connection conn;
	public void setConnection( Connection conn) {
		this.conn = conn;
	}
	public void insertDiscuss( discussJavaBean idjb ) {
		String sqlString = "insert into forum(shopName,drink,drinkDiscuss,drinkstar) "
				+ "values(?,?,?,?)";
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement(sqlString);
			prepareStatement.setString(1,idjb.getShopName());
			prepareStatement.setString(2,idjb.getDrink());
			prepareStatement.setString(3,idjb.getDrinkDiscuss());
			prepareStatement.setInt(4,idjb.getDrinkstars());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
			try {
				
				prepareStatement.close();
				
			} catch (SQLException e) {
				System.out.println("Dao insertActivityForm close Error");
				e.printStackTrace();
			}
			
		}
	}
	
		public List<discussJavaBean> discussTake() {
			String sqlString = " select * from forum";
			
			PreparedStatement preState=null;
			ResultSet rs=null;
			List<discussJavaBean> discussJavaBeansList = new ArrayList<>();
			
			try {
				preState = conn.prepareStatement(sqlString);
				rs = preState.executeQuery();
				discussJavaBean javaBean;
				
				while ( rs.next() ) {
					
					javaBean = new discussJavaBean();
					javaBean.setShopName(rs.getString("shopName"));
					javaBean.setDrink(rs.getString("drink"));
					javaBean.setDrinkDiscuss(rs.getString("drinkDiscuss"));
					javaBean.setDrinkstars(rs.getInt("drinkstar"));
					discussJavaBeansList.add(javaBean);
				}
				
			} catch (SQLException e) {
				System.out.println("Dao Error");
				e.printStackTrace();
			} finally {
				
				try {
					rs.close();
					preState.close();
				} catch (SQLException e) {
					System.out.println("Dao close Error");
					e.printStackTrace();
				}
			}
			
			return discussJavaBeansList;

}

	}

	

