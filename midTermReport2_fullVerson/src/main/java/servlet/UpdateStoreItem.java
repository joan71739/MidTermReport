package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
import dao.NewStoreDAO;
import dao.StoreBean;

/**
 * Servlet implementation class UpdateStoreItem
 */
@WebServlet("/UpdateStoreItem")
public class UpdateStoreItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	DataSource ds = null;
	InitialContext ctxt = null;
	Connection conn = null;

	//存取帳號
	String lostuserid;
	String company;

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
	
	
	//POST

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

			// ※修改資料用,判斷按鈕,共兩個
			// 1.確認輸入帳號
			if (req.getParameter("submit") != null) {
				gotoSubmitProcess(req, res); // 輸入帳號送出按鈕
				// 2.確認修改資料
			} else if (req.getParameter("confirm") != null) {
				gotoConfirmProcess(req, res); // 輸入修改後確認按鈕
				// 判斷修改按鈕導向頁面
			} else if (req.getParameter("updatebtn") != null) {
				req.getRequestDispatcher("/UpdateStore.jsp").forward(req, res);// 導向查詢頁面

				// ※查詢資料用，判斷按鈕,共三個
				// 3.利用統編查詢店家資料，此按鈕可以傳送統編資料
			} else if (req.getParameter("query") != null) {
				
				gotoQueryConfirmProcess(req, res); // 輸入統編確認按鈕
				// 4.利用店家帳號查詢店家資料
			} else if (req.getParameter("usquery") != null) {
				gotoSubmitProcess(req, res); // 輸入統編確認按鈕
				// 判斷查詢按鈕導向頁面
			} else if (req.getParameter("querybtn") != null) {
				//取企業統編
				
				// 取出使用者帳號的資料
				// -取連線
				UpdateStoreItem ut = new UpdateStoreItem();
				conn = ut.util();
				
				// -連線DAO
				LoginDAO loginDAO = new LoginDAO(conn);
				// -取出使用者帳號之公司統編
				company = loginDAO.findCompany(lostuserid);
				// -送出帳號並導入頁面
				req.getSession(true).setAttribute("company", company);				
				req.getRequestDispatcher("/Query.jsp").forward(req, res);// 導向查詢頁面

				// ※刪除資料用
				// 5.確認輸入欲刪除帳號
			} else if (req.getParameter("delete") != null) {
				gotoSubmitProcess(req, res); // 輸入帳號送出按鈕
				// 6.利用帳號刪除資料
			} else if (req.getParameter("CFdelete") != null) {
				gotoDeleteProcess(req, res); // 送出欲刪除的帳號至資料庫
				// 判斷刪除按鈕導向頁面
			} else if (req.getParameter("deletebtn") != null) {
				req.getRequestDispatcher("/DeleteStore.jsp").forward(req, res);// 導向查詢頁面
			}

		} else {
			req.getRequestDispatcher("/LoginStore.jsp").forward(req, res);
		}

	}

	// 1.確認輸入店家帳號輸出資料
	public void gotoSubmitProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 取出使用者輸入的值
//		String stuserid = req.getParameter("stuserid").trim();

		// 取出使用者帳號的資料
		// 取連線
		UpdateStoreItem ut = new UpdateStoreItem();
		conn = ut.util();

		// 連線DAO
		NewStoreDAO storeDAO = new NewStoreDAO(conn);
		try {
			StoreBean reg_store = storeDAO.findByUserid(lostuserid);
			req.getSession(true).setAttribute("reg_store", reg_store);

			//帳號送出
			req.getSession(true).setAttribute("lostuserid", lostuserid);
			
			// 1.修改用_店家帳號送出
			if (req.getParameter("submit") != null) {

				req.getRequestDispatcher("/CheckStore.jsp").forward(req, res);

				// 2.查詢用_店家帳號送出
			} else if (req.getParameter("usquery") != null) {

				req.getRequestDispatcher("/StoreList_Userid.jsp").forward(req, res);

				// 3.刪除用_店家帳號送出
			} else if (req.getParameter("delete") != null) {

				req.getRequestDispatcher("/DSPlayDelete.jsp").forward(req, res);
			}
			
//			req.getRequestDispatcher("/NewSServlet").forward(req, res);
//			req.getRequestDispatcher("/UpdateStoreItem").forward(req, res);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		}

	}

	// 2.確認修改資料
	public void gotoConfirmProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 取出使用者輸入的值
		String stuserid = (String) req.getSession(true).getAttribute("lostuserid");
		String title = req.getParameter("title").trim();
		String manager = req.getParameter("manager").trim();
		String stadd = req.getParameter("stadd").trim();
		String tel = req.getParameter("tel").trim();
		String intro = req.getParameter("intro").trim();
		StoreBean storeData = new StoreBean(stuserid, title, manager, stadd, tel, intro);

		try {

			// 取出使用者帳號的資料
			// 取連線
			UpdateStoreItem ut = new UpdateStoreItem();
			conn = ut.util();

			// 連線DAO
			NewStoreDAO storeDAO = new NewStoreDAO(conn);
			
			//帳號送出
			req.getSession(true).setAttribute("lostuserid", lostuserid);
			
			// 執行並判斷是否新增資料成功
			if (storeDAO.updateStore(storeData)) {
				System.out.println("修改店家資料成功");
				
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

	// 3.利用企業統編查詢店家資料，此按鈕可以傳送統編資料
	public void gotoQueryConfirmProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			
		// 取連線
		UpdateStoreItem ut = new UpdateStoreItem();
		conn = ut.util();
		
		// 連線DAO
		NewStoreDAO storeDAO = new NewStoreDAO(conn);
		

		//帳號送出
		req.getSession(true).setAttribute("lostuserid", lostuserid);
		
		try {
			ArrayList<StoreBean> reg_array = storeDAO.findByCompany(company);

			req.getSession(true).setAttribute("company", company);
			req.getSession(true).setAttribute("reg_array", reg_array);

			req.getRequestDispatcher("/StoreList.jsp").forward(req, res);
			
//			req.getRequestDispatcher("/NewSServlet").forward(req, res);
//			req.getRequestDispatcher("/UpdateStoreItem").forward(req, res);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		}

	}

	// 4.利用店家帳號刪除資料
	public void gotoDeleteProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 取使用者帳號
//		String stuserid = (String) req.getSession(true).getAttribute("stuserid");

		// 取出使用者帳號的資料
		// 取連線
		UpdateStoreItem ut = new UpdateStoreItem();
		conn = ut.util();

		// 連線DAO
		NewStoreDAO storeDAO = new NewStoreDAO(conn);

		//帳號送出
		req.getSession(true).setAttribute("lostuserid", lostuserid);
		
		// 執行並判斷是否刪除資料成功
		try {
			if (storeDAO.dileteByStoreid(lostuserid)) {
				System.out.println("刪除店家資料成功");
				
//				req.getRequestDispatch；er("/NewSServlet").forward(req, res);
//				req.getRequestDispatcher("/UpdateStoreItem").forward(req, res);
				
//				req.getSession(true).invalidate();// 關session
				req.getRequestDispatcher("/Thanks.jsp").forward(req, res);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
