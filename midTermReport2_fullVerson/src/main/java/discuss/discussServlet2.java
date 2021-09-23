package discuss;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class discussServlet2
 */
@WebServlet("/discussServlet2")
public class discussServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private discussDao dao;
	private Connection conn;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public discussServlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			// 取得連線
			InitialContext ctxt = new InitialContext();
			/* 2021/7/13 冠維 discuss -> EmployeeDB */
			DataSource ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/EmployeeDB"); // for MS SQL Server
			conn = ds.getConnection();

		} catch (NamingException e) {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		discussJavaBean discuss = new discussJavaBean();
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
		List<discussJavaBean> discussTake = dao.discussTake();
		request.setAttribute("alldiscuss",discussTake);
		request.getRequestDispatcher("/page2.jsp").forward(request,response);
	 	return;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
