package activity;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
 * Servlet implementation class ManageActivityServlet
 */
//@WebServlet("/ManageActivityServlet") 用 XML 佈署
public class ManageActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection conn;
	
	// Dao
	private ActivityDao dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageActivityServlet() {
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
		
		// 請 Dao 幫忙抓在資料庫裡的活動資料
		List<ActivityJavaBean> activityArrayList = dao.queryAllActivity();
		
		// 將資料存到 request 中
		request.setAttribute("allActivity", activityArrayList);
		
		// 導向 manageActivity.jsp 網頁
		request.getRequestDispatcher("/manageActivity.jsp").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
