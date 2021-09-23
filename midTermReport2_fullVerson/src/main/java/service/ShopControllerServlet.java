package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import JavaBeen.ShoppingCarBeen;
import JavaBeen.prouctBeen;

@WebServlet("/shopping")
public class ShopControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DataSource DB;

	public ShopControllerServlet() {
		super();

	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// 收索資料庫
		try {
			InitialContext ctx = new InitialContext();
			DB = (DataSource) ctx.lookup("java:comp/env/jdbc/EmployeeDB");
			if (DB == null) {
				throw new ServletException("Unknown DataSource 'jdbc/EmployeeDB'");
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 建立連線
		Connection conn = null;
		Statement stat = null;

		List<String> proName = new ArrayList<String>();
		List<Float> proPrice = new ArrayList<Float>();

		try {
			String sql = "select proname,proprice from prouct";
			conn = DB.getConnection();
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);

			// 讀取資料
			while (rs.next()) {
				proName.add(rs.getString("proname"));
				proPrice.add(rs.getFloat("proprice"));
			}

			// 塞入prouctBeen
			prouctBeen.setProName((String[]) proName.toArray(new String[0]));
			prouctBeen.setProPrice((Float[]) proPrice.toArray(new Float[0]));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stat != null) {
					stat.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);

		List<ShoppingCarBeen> car = (ArrayList<ShoppingCarBeen>) session.getAttribute("car");
		String togo = "";
		String todo = request.getParameter("todo");
		if (todo == null) {
			togo = "/order.jsp";
		} else if (todo.equals("add")) {
			// 新的購買商品
			String ice = request.getParameter("ice");
			String sugar = request.getParameter("sugar");
			int proid = Integer.parseInt(request.getParameter("prouct"));
			int qty = Integer.parseInt(request.getParameter("qty"));
			ShoppingCarBeen newShoppCar = new ShoppingCarBeen(proid, qty, ice, sugar);
			// 原本的購物車空的
			if (car == null) {
				// 建新的
				car = new ArrayList<>();
				car.add(newShoppCar);
				// 塞改session
				session.setAttribute("car", car);
			} else { // 購物車有舊的東西
				boolean found = false; // 確認是否有相同商品
				// 轉為iterator集合,因為有hasnext()
				Iterator iter = car.iterator();
				while (!found && iter.hasNext()) {
					// 原本消費的東西
					ShoppingCarBeen oldcar = (ShoppingCarBeen) iter.next();
					if (oldcar.getProid() == newShoppCar.getProid() && oldcar.getIce().equals(newShoppCar.getIce())
							&& oldcar.getSugar().equals(newShoppCar.getSugar())) { // 原本消費東西和舊消費東西相同
						int newqty=Integer.parseInt(request.getParameter("newqty"));
						oldcar.setQty(oldcar.getQty() + newShoppCar.getQty());
						found = true;
					}
				}
				if (!found) { // 加入就購物車沒有的商品
					car.add(newShoppCar);
				}
			}
			togo = "/order.jsp";
		} else if (todo.equals("remove")) {
			int carIndex = Integer.parseInt(request.getParameter("cartIndex"));
			car.remove(carIndex);

			togo = "/order.jsp";
		} else if (todo.equals("checkout")) {
			float total = 0;
			int allQty = 0;
			for (ShoppingCarBeen item : car) {
				total += item.getPrice() * item.getQty();
				allQty += item.getQty();
			}

			request.setAttribute("allProuctTotal", total);
			request.setAttribute("allQty", allQty);

			togo = "/checkout.jsp";

		} else if (todo.equals("confim")) {
			try {
				InitialContext ctxt = new InitialContext();
				DB = (DataSource) ctxt.lookup("java:comp/env/jdbc/EmployeeDB");
				if (DB == null)
					throw new ServletException("Unknown DataSource 'jdbc/EmployeeDB'");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = null;
			PreparedStatement stat = null;
			float total = 0;
			for (ShoppingCarBeen item : car) {
				total += item.getPrice() * item.getQty();
			}

			try {
				String sql = "insert into ORD(productID, total)" + "values(?,?)";
				conn = DB.getConnection();
				stat = conn.prepareStatement(sql);
				stat.setInt(1, 003);
				stat.setFloat(2, total);
				stat.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (stat != null)
						stat.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			int ordid = 0;
			try {
				/* 2021/7/13 冠維 productID=001 -> productID=003 */
				String selectSql = "select ORDID from ORD where productID=003 and total=" + total;
				conn = DB.getConnection();
				stat = conn.prepareStatement(selectSql);
				ResultSet rs = stat.executeQuery();
				while (rs.next()) {
					ordid += rs.getInt("ORDID");
				}
				String insertSql = "insert into ORDitem(ORDID,poructName,actualprice,QTY,itemtot)"
						+ "values(?,?,?,?,?)";
				stat = conn.prepareStatement(insertSql);
				for (ShoppingCarBeen item : car) {
					stat.setInt(1, ordid);
					stat.setString(2, item.getProName());
					stat.setInt(3, item.getPrice());
					stat.setInt(4, item.getQty());
					int prototal = item.getPrice() * item.getQty();
					stat.setInt(5, prototal);
					stat.execute();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (stat != null)
						stat.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			session.invalidate();
			togo = "/confim.jsp";

		} else if (todo.equals("cancal")) {
			session.invalidate();
			togo = "/cancal.jsp";
		}
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(togo);
		requestDispatcher.forward(request, response);
	}

}
