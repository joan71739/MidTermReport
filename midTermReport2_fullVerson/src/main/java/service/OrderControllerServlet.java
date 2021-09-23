package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import JavaBeen.ShoppingCarBeen;
import JavaBeen.orderBeen;

@WebServlet("/OrderControllerServlet")
public class OrderControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DataSource DB;

	public OrderControllerServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			InitialContext ctx = new InitialContext();
			DB = (DataSource) ctx.lookup("java:comp/env/jdbc/EmployeeDB");
			if (DB == null) {
				throw new ServletException("Unknown DataSource 'jdbc/EmployeeDB'");
			}
		} catch (NamingException e) {

			e.printStackTrace();
		}

		// 建立連線
		Connection conn = null;
		Statement stat = null;

		List<String> id = new ArrayList<String>();
		// 未完成
		List<String> store = new ArrayList<String>();
		List<String> shopDate = new ArrayList<String>();
		List<String> total = new ArrayList<String>();
		
		String todo= request.getParameter("todo");
		if(todo==null) {
		try {

			String sql = "select ORDID,shoppDate,total from ORD";
			conn = DB.getConnection();
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				id.add(rs.getString("ORDID"));
				shopDate.add(rs.getString("shoppDate"));
				total.add(rs.getString("total"));
			}
			orderBeen allorder = new orderBeen((String[]) id.toArray(new String[0]),
					(String[]) shopDate.toArray(new String[0]), (String[]) total.toArray(new String[0]));
			request.setAttribute("order", allorder);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (stat != null) {
					stat.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}else if(todo.equals("add")) {
		try {
			String begin= request.getParameter("begin");
			String end=request.getParameter("end");
			String sql = "select ORDID,shoppDate,total from ORD "
					+ "where shoppDate>= '"+begin+"' and shoppDate<= '"+end+"'";
			conn = DB.getConnection();
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				id.add(rs.getString("ORDID"));
				shopDate.add(rs.getString("shoppDate"));
				total.add(rs.getString("total"));
			}
			orderBeen allorder = new orderBeen((String[]) id.toArray(new String[0]),
					(String[]) shopDate.toArray(new String[0]), (String[]) total.toArray(new String[0]));
			request.removeAttribute("order");
			request.setAttribute("order", allorder);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (stat != null) {
					stat.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
	}
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/selectOrd.jsp");
		requestDispatcher.forward(request, response);

	}

}
