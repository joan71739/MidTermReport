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

import dao.NewStoreDAO;
import dao.StoreBean;

/**
 * Servlet implementation class NewSServlet
 */
@WebServlet("/NewSServlet")
public class NewSServlet extends HttpServlet {

	// 固定
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
	String lostuserid;

	DataSource ds = null;
	InitialContext ctxt = null;
	Connection conn = null;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

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

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 固定寫法，頁首
		req.setCharacterEncoding(CHARSET_CODE);
		res.setContentType(CONTENT_TYPE);

		res.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		res.setHeader("Pragma", "no-cache"); // HTTP 1.0
		res.setDateHeader("Expires", -1); // Prevents caching at the proxy server

		// 判斷登入
		lostuserid = (String) req.getSession(true).getAttribute("lostuserid");
		if (lostuserid != null) {

			// 判斷按鈕

			if (req.getParameter("submit") != null) {
				gotoSubmitProcess(req, res); // 送出按鈕

			} else if (req.getParameter("confirm") != null) {
				gotoConfirmProcess(req, res); // 確認按鈕

			} else if (req.getParameter("new") != null) {// 導向頁面

				//存入登入帳號
				req.getSession(true).setAttribute("lostuserid", lostuserid);
				
				//判斷是否新增過資料
					// 取連線
				UpdateStoreItem ut = new UpdateStoreItem();
				conn = ut.util();

					// 連線DAO
				NewStoreDAO storeDAO = new NewStoreDAO(conn);
				boolean boo = storeDAO.findUseridinStore(lostuserid);
				System.out.println(boo);
				if(boo) {
						// 送出登入帳號導出頁面
					req.getRequestDispatcher("/NewStoreItem.jsp").forward(req, res);
				}else {
						// 送出登入帳號導出頁面
					req.getRequestDispatcher("/NewWrong.jsp").forward(req, res);
				}

			}

		} else {
			req.getRequestDispatcher("/LoginStore.jsp").forward(req, res);
		}
	}

	// 送出方法
	public void gotoSubmitProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 取出使用者輸入的值
//		String stuserid = req.getParameter("stuserid").trim();
		String title = req.getParameter("title").trim();
		String manager = req.getParameter("manager").trim();
		String stadd = req.getParameter("stadd").trim();
		String tel = req.getParameter("tel").trim();
		String intro = req.getParameter("intro").trim();

		StoreBean reg_store = new StoreBean(lostuserid, title, manager, stadd, tel, intro);
		req.getSession(true).setAttribute("reg_store", reg_store);
		req.getRequestDispatcher("/DSPlayStore.jsp").forward(req, res);

		// 帳號送出
		req.getSession(true).setAttribute("lostuserid", lostuserid);
		req.getRequestDispatcher("/NewSServlet").forward(req, res);
		req.getRequestDispatcher("/UpdateStoreItem").forward(req, res);
	}

	// 確認方法
	public void gotoConfirmProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		try {
			// 取連線
			UpdateStoreItem ut = new UpdateStoreItem();
			conn = ut.util();

			// 連線DAO
			NewStoreDAO storeDAO = new NewStoreDAO(conn);

			// 取資料
			StoreBean storeData = (StoreBean) req.getSession(true).getAttribute("reg_store");

			// 帳號送出
			req.getSession(true).setAttribute("lostuserid", lostuserid);

			// 判斷是否新增資料成功
			if (storeDAO.addStore(storeData)) {
				System.out.println("新增店家資料成功");

//				req.getRequestDispatcher("/NewSServlet").forward(req, res);
//				req.getRequestDispatcher("/UpdateStoreItem").forward(req, res);

//				req.getSession(true).invalidate();// 關session
				req.getRequestDispatcher("/Thanks.jsp").forward(req, res);
			}

		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}

	}

}
