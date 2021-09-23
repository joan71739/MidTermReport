package activity;

import java.io.Console;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import activityDao.ActivityDao;
import activityJavaBean.ActivityJavaBean;

/**
 * Servlet implementation class updateActivityServlet
 */
//@WebServlet("/updateActivityServlet") 用 XML 佈署
public class updateActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	// 連線
	private Connection conn;
	
	// Dao
	private ActivityDao dao;
	
	// 轉表單日期 -> SQL datetime 格式
	private String dateToSQLString(String time_DDMMYYY, String time_HH) {
		
		String[] timeArray = time_DDMMYYY.split("/");
		
		StringBuffer sb = new StringBuffer();
		sb.append(timeArray[2]);	// YYY
		sb.append("-");
		sb.append(timeArray[0]);	//MM
		sb.append("-");
		sb.append(timeArray[1]);	//DD
		sb.append(" ");
		sb.append(time_HH);			//HH
		sb.append(":00:00");
		return sb.toString();

	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateActivityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		  
	      try 
	      {
	    	// 取得連線
	        InitialContext ctxt = new InitialContext();
	        DataSource ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/EmployeeDB");  // for MS SQL Server
	        conn = ds.getConnection();

	      }
	      catch (NamingException e){
	    	  System.out.println("DB JNDI Error !!");
	    	  e.printStackTrace();
	      } catch (SQLException e) {
	    	  System.out.println("DB getConnection Error !!");
	    	  e.printStackTrace();
	      }
	      
	      // 建立 Dao
	      dao = new ActivityDao();
	      dao.setConnection(conn);	

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 不會用到
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 設定編碼
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		// 表單資料: 判斷 delete 或 update
		String deleteValue = request.getParameter("deleteValue");
		int IDValue = Integer.parseInt(request.getParameter("IDValue"));
		
		// deleteValue 為 1 就刪除此筆資料， 為 0 就修改此筆資料
		if( deleteValue.equals("1") ) { 
			
			// 呼叫 Dao 幫忙刪除此筆資料
			dao.deleteOneActivityByID( IDValue );
			
		}else if( deleteValue.equals("0") ) {
			
			// 新表單資料存到 JavaBean
			ActivityJavaBean activityJavaBean = new ActivityJavaBean();
			activityJavaBean.setActivityTopic( request.getParameter("activityTopic") );
			
			
			String startTimeYMD = request.getParameter("startTimeYMD");
			String startTimeH = request.getParameter("startTimeH");
			activityJavaBean.setStartTime( dateToSQLString(startTimeYMD,startTimeH) );
			
			String endTimeYMD = request.getParameter("endTimeYMD");
			String endTimeH = request.getParameter("endTimeH");
			activityJavaBean.setEndTime( dateToSQLString(endTimeYMD,endTimeH) );

			activityJavaBean.setActivityContent( request.getParameter("activityContent") );
			
			// 呼叫 Dao 幫忙修改此筆資料
			dao.updateOneActivityByID(IDValue,activityJavaBean);
		}
		
		
		// 導回 manageActivityServlet
		request.getRequestDispatcher("/ManageActivityServlet").forward(request, response);
		return;
		
	}

}
