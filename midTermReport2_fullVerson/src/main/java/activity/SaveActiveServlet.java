package activity;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import activityDao.ActivityDao;
import activityJavaBean.ActivityJavaBean;

/**
 * Servlet implementation class SaveActive
 */
//@WebServlet("/SaveActive") 用 XML 佈署
public class SaveActiveServlet extends HttpServlet {
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
    public SaveActiveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
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
		
		// 設定編碼
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		
//		String startDateParam = request.getParameter("startDate");

		// 呼叫 Dao 幫忙
//		getDataTest dao = new getDataTest();
//		dao.queryData();
//		List<String> rsData = dao.queryData();
		
		// 顯示在螢幕上
//		PrintWriter out = response.getWriter();
//		
//		for(String item :rsData ) {
//			out.print("<div> Message:"+ item + "</div>");
//		}
//
//		out.close();
		
		
		// 返回 ManageActivityServlet ，並要顯示已新增的活動
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 設定編碼
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		// 表單資料存到 JavaBean
		ActivityJavaBean activityJavaBean = new ActivityJavaBean();
		activityJavaBean.setActivityTopic( request.getParameter("activityTopic") );
		
		
		String startTimeYMD = request.getParameter("startTimeYMD");
		String startTimeH = request.getParameter("startTimeH");
		activityJavaBean.setStartTime( dateToSQLString(startTimeYMD,startTimeH) );
		
		String endTimeYMD = request.getParameter("endTimeYMD");
		String endTimeH = request.getParameter("endTimeH");
		activityJavaBean.setEndTime( dateToSQLString(endTimeYMD,endTimeH) );

		activityJavaBean.setActivityContent( request.getParameter("activityContent") );
		activityJavaBean.setActivityType( Integer.parseInt(request.getParameter("activityType")) );
				
		// 呼叫 Dao 幫忙 insert 活動資料
		
		dao.insertActivityForm(activityJavaBean);
		
		// *****關閉連線: 找時機關閉*****
		/*
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Connection close Error");
			e.printStackTrace();
		}
		*/
		
		// 導回 ManageActivityServlet。
		// 不要遺留 request 參數，使用 sendRedirect()
		
		// 相對路徑:
		// sevlet 不要加 .java 否則會 Error
		// 有 / 是從root目錄
		// 若不加斜線預設就會從project下的目錄開始找
		response.sendRedirect("ManageActivityServlet");
	}

}
