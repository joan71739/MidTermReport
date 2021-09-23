package servlet;

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

import dao.LoginDAO;

/**
 * Servlet implementation class LoginStore
 */
@WebServlet("/LoginStore")
public class LoginStore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	DataSource ds = null;
	InitialContext ctxt = null;
	Connection conn = null;

	// 連線方法

	public Connection util() {
		try {
			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/EmployeeDB");
			conn = ds.getConnection();

		} catch (NamingException e) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		}

		return conn;
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 固定寫法，頁首
		req.setCharacterEncoding(CHARSET_CODE);
		res.setContentType(CONTENT_TYPE);

		res.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		res.setHeader("Pragma", "no-cache"); // HTTP 1.0
		res.setDateHeader("Expires", -1); // Prevents caching at the proxy server

		// 判斷按鈕
		// 判斷帳號密碼是否正確
		if (req.getParameter("login") != null) {
			gotologinProcess(req, res); // 送出按鈕

		}

	}

	//
	public void gotologinProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 取出使用者輸入的值
		String stuserid = req.getParameter("stuserid").trim();
		String password = req.getParameter("password").trim();

		// 取出使用者帳號的資料
		// 取連線
		UpdateStoreItem ut = new UpdateStoreItem();
		conn = ut.util();
		//送出帳號

		// 連線DAO
		LoginDAO loginDAO = new LoginDAO(conn);
		
		if(loginDAO.findByUserid(stuserid, password)) {
			System.out.println("登入成功");
			req.getSession(true).setAttribute("lostuserid", stuserid);
			req.getRequestDispatcher("/LoginOK.jsp").forward(req, res);
//			req.getRequestDispatcher("/NewSServlet").forward(req, res);
//			req.getRequestDispatcher("/UpdateStoreItem.jsp").forward(req, res);
		}else {
			System.out.println("登入失敗");
			req.getRequestDispatcher("/LoginWrong.jsp").forward(req, res);
		}
		

	}
}
