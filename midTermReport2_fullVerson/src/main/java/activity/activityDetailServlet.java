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
 * Servlet implementation class activityDetail
 */
//@WebServlet("/activityDetailServlet") 用 XML 佈署
public class activityDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private Connection conn;
	
	// Dao
	private ActivityDao dao;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public activityDetailServlet() {
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
		
		// 設定編碼
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		// 取得活動編號
		int ID = Integer.parseInt(request.getParameter("ID"));
		
		// 請 Dao 幫忙抓出該筆活動詳細內容
		ActivityJavaBean javaBean = dao.queryOneActivityByID(ID);

		// 將資料存到 request 中
		request.setAttribute("oneActivity", javaBean);
		
		// 導向 JSP 顯示網頁
		request.getRequestDispatcher("/activityDetail.jsp").forward(request, response);
		return;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//不會用到
	}

}
