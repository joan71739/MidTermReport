package discuss;

import java.awt.print.Pageable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class discussServlet
 */
@WebServlet("/discussServlet")
public class discussServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private discussDao dao;
       private Connection conn;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public discussServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try 
	      {
	    	// 取得連線
	        InitialContext ctxt = new InitialContext();
	        /* 2021/7/13 冠維 discuss -> EmployeeDB */
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
		dao = new discussDao();
		dao.setConnection(conn);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		discussJavaBean discuss = new discussJavaBean();
		
			//設定編碼
		 request.setCharacterEncoding("UTF-8");
	     response.setContentType("text/html;charset=UTF-8");
	     
	     String shopName = request.getParameter("shopName");
	     System.out.println(shopName);
	     
	     String drinkString = request.getParameter("drink");
	     System.out.println(drinkString);
	     
	     String drinkDiscussString = request.getParameter("drinkDiscuss");
	     System.out.println(drinkDiscussString);
	     
	     String drinkStarsString = request.getParameter("drinkstar");
	     int drinkStarsInt = Integer.parseInt(drinkStarsString);
	     System.out.println(drinkStarsInt);
	     
	     discuss.setShopName(shopName);
	     discuss.setDrink(drinkString);
	     discuss.setDrinkDiscuss(drinkDiscussString);
	     discuss.setDrinkstars(drinkStarsInt);
	     dao.insertDiscuss(discuss);
	     request.getRequestDispatcher("/discussServlet2").forward(request,response);
	     return;
	     
	}

}
