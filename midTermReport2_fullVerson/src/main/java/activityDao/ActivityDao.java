package activityDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import activityJavaBean.ActivityJavaBean;

public class ActivityDao {
	
	private Connection conn;
	
	public ActivityDao(){
		System.out.println("++++++++++++++Dao被建立+++++++++++++++++++");
	}
	
	// 必須要先設定連線
	public void setConnection( Connection conn) {
		this.conn = conn;
	}
	
	// insert 活動表單
	public void insertActivityForm( ActivityJavaBean javaBean ) {
			String sqlString = "insert into Activity(activityTopic,startTime,endTime,activityContent,activityType) "
					+ "values(?,?,?,?,?)";
			
			PreparedStatement preState=null;
			
			try {
				
				preState = conn.prepareStatement(sqlString);
				preState.setString(1, javaBean.getActivityTopic());
				preState.setString(2, javaBean.getStartTime());
				preState.setString(3, javaBean.getEndTime());
				preState.setString(4, javaBean.getActivityContent());
				preState.setInt(5, javaBean.getActivityType());
				preState.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("Dao insertActivityForm Error");
				e.printStackTrace();
			} finally {
				
				try {
					
					preState.close();
					
				} catch (SQLException e) {
					System.out.println("Dao insertActivityForm close Error");
					e.printStackTrace();
				}
				
			}
		
	}
	
	// Query 已建立的全部活動
	public List<ActivityJavaBean> queryAllActivity() {
		String sqlString = "select * from Activity";
		
		PreparedStatement preState=null;
		ResultSet rs=null;
		List<ActivityJavaBean> activityArrayList = new ArrayList<>();
		
		try {
			preState = conn.prepareStatement(sqlString);
			rs = preState.executeQuery();
			ActivityJavaBean javaBean;
			
			while ( rs.next() ) {
				
				javaBean = new ActivityJavaBean();
				javaBean.setID(rs.getInt("ID"));
				javaBean.setActivityTopic(rs.getString("activityTopic"));
				javaBean.setStartTime(rs.getString("startTime"));
				javaBean.setEndTime(rs.getString("endTime"));
				javaBean.setActivityContent(rs.getString("activityContent"));
				javaBean.setActivityType(rs.getInt("activityType"));
				activityArrayList.add(javaBean);
			}
			
		} catch (SQLException e) {
			System.out.println("Dao queryAllActivity Error");
			e.printStackTrace();
		} finally {
			
			try {
				rs.close();
				preState.close();
			} catch (SQLException e) {
				System.out.println("Dao queryAllActivity close Error");
				e.printStackTrace();
			}
		}
		
		return activityArrayList;

	}
	
	// Query 單一活動
	public ActivityJavaBean queryOneActivityByID( int ID ) {
		String sqlString = "select * from Activity where ID=?";
		
		PreparedStatement preState=null;
		ResultSet rs=null;
		ActivityJavaBean javaBean=null;
		
		try {
			
			preState = conn.prepareStatement(sqlString);
			preState.setInt(1, ID);
			rs = preState.executeQuery();
			
			
			while ( rs.next() ) {
				javaBean = new ActivityJavaBean();
				javaBean.setID(rs.getInt("ID"));
				javaBean.setActivityTopic(rs.getString("activityTopic"));
				javaBean.setStartTime(rs.getString("startTime"));
				javaBean.setEndTime(rs.getString("endTime"));
				javaBean.setActivityContent(rs.getString("activityContent"));
				javaBean.setActivityType(rs.getInt("activityType"));
			}
			
			
			
		} catch (SQLException e) {
			System.out.println("Dao queryOneActivityByID Error");
			e.printStackTrace();
		}finally {
			
			try {
				rs.close();
				preState.close();
			} catch (SQLException e) {
				System.out.println("Dao queryOneActivityByID close Error");
				e.printStackTrace();
			}
			
		}
		
		return javaBean;
		
	}
	
	
	// Delete 單一活動
	public void deleteOneActivityByID( int ID ) {
		String sqlString = "delete from Activity where id = ?";
		
		PreparedStatement preState=null;
		
		try {
			
			preState = conn.prepareStatement(sqlString);
			preState.setInt(1, ID);
			preState.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Dao deleteOneActivityByID Error");
			e.printStackTrace();
		}finally {
			
			try {
				preState.close();
			} catch (SQLException e) {
				System.out.println("Dao deleteOneActivityByID close Error");
				e.printStackTrace();
			}
			
		}
		
	}
	
	// Update 單一活動
	public void updateOneActivityByID(int ID, ActivityJavaBean javaBean) {
		String sqlString = "update Activity "
				+ " set activityTopic = ?, startTime = ?, endTime = ?, activityContent = ? "
				+ " where id = ? ";
		
		PreparedStatement preState=null;
		
		try {
			
			preState = conn.prepareStatement(sqlString);
			preState.setString(1, javaBean.getActivityTopic());
			preState.setString(2, javaBean.getStartTime());
			preState.setString(3, javaBean.getEndTime());
			preState.setString(4, javaBean.getActivityContent());
			preState.setInt(5, ID );
			preState.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("Dao updateOneActivityByID Error");
			e.printStackTrace();
		} finally {
			
			try {
				
				preState.close();
				
			} catch (SQLException e) {
				System.out.println("Dao updateOneActivityByID close Error");
				e.printStackTrace();
			}
			
		}
	
	}
	
}
