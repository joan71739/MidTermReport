package login;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class NewMember
 */
@WebServlet("/NewMemberAll")
public class NewMemberAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewMemberAll() {
		super();
	}

//	/**
//	 * @see Servlet#init(ServletConfig)
//	 */
	public void init(ServletConfig config) throws ServletException {

//		MemberDB.setUserID(null);
//		MemberDB.setPassword(null);
//		MemberDB.setPasswordck(null);
//		MemberDB.setName(null);
//		MemberDB.setBirthday(null);
//		MemberDB.setEmail(null);
//		MemberDB.setVendor(null);
//		MemberDB.setPrincipal(null);
//		MemberDB.setVat(null);
//		MemberDB.setAddress(null);

	}

//
//	/**
//	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(true);
		String submit = request.getParameter("sub");// button
		String sType = request.getParameter("type");

		if (submit == null) {
			if (sType.equals("form-a")) {
				session.setAttribute("Type", "A");
			}
			if (sType.equals("form-b")) {
				session.setAttribute("Type", "B");
			}
			request.getRequestDispatcher("/NewMemberAll.jsp").forward(request, response);
			return;
		}
		if(submit.equals("cancel")) {
			response.sendRedirect("./homePage.html");
			return;
		}
		if (submit.equals("res")) {
			MemberDB.setUserID(null);
			MemberDB.setPassword(null);
			MemberDB.setPasswordck(null);
			MemberDB.setName(null);
			MemberDB.setBirthday(null);
			MemberDB.setEmail(null);
			MemberDB.setVendor(null);
			MemberDB.setPrincipal(null);
			MemberDB.setVat(null);
			MemberDB.setAddress(null);

			if (sType.equals("form-a")) {
				session.setAttribute("Type", "A");
			}
			if (sType.equals("form-b")) {
				session.setAttribute("Type", "B");
			}
			response.sendRedirect("./NewMemberAll.jsp");
//			request.getRequestDispatcher("/NewMemberAll.jsp").forward(request, response);
			return;
		}
		if (submit.equals("modify")) {
			request.getRequestDispatcher("/NewMemberAll.jsp").forward(request, response);
			return;
		}
		else if (submit.equals("nextChack")) {

			String administrator = request.getParameter("userID");// 帳號
			String password = request.getParameter("pws");// 密碼
			String passwordck = request.getParameter("passwordck");// 密碼
			String user = request.getParameter("name");// 姓名
			String birthday = request.getParameter("birthday");// 出生年月日
			String email = request.getParameter("email");// email
			//
			String vendor = request.getParameter("vendor");
			String principal = request.getParameter("principal");// 負責人
			String vat = request.getParameter("vat");// 統一編號
			String address = request.getParameter("address");// 統一編號
			//
			MemberDB.setUserID(administrator);
			MemberDB.setPassword(password);
			MemberDB.setPasswordck(passwordck);
			MemberDB.setName(user);
			MemberDB.setBirthday(birthday);
			MemberDB.setEmail(email);
			//
			MemberDB.setVendor(vendor);
			MemberDB.setPrincipal(principal);
			MemberDB.setVat(vat);
			MemberDB.setAddress(address);
			// 確認帳號是否為空
			if ("".equals(administrator)) {
				request.getRequestDispatcher("/NewMemberAll.jsp").forward(request, response);
				return;
			}
			// 確認密碼兩次是否相同
			boolean ckPws = false;
			if (password.equals(passwordck) && !"".equals(password)) {
				ckPws = true;
			}
			// 進sql確認帳號是否重複
			SQLMethod sqlmethod = new SQLMethod();
			sqlmethod.createConn();
			ResultSet res = sqlmethod.searchMember(administrator);
			sqlmethod.closeConn();
			// 帳號不重複，密碼兩次相同
			if (res == null && ckPws) {
				request.getRequestDispatcher("/NewMemberChackAll.jsp").forward(request, response);
				return;
			} else {
				request.getRequestDispatcher("/NewMemberAll.jsp").forward(request, response);
				// session.setAttribute("ckUserID", "repeat");
				return;
			}
		} else if (submit.equals("submit")) {
			// 寫進sql
			// a:一般會員 b:企業會員 c:店長 d:員工
			SQLMethod sqlmethod = new SQLMethod();
			sqlmethod.createConn();
			if (sType.equals("form-a")) {
				sqlmethod.inisertMember(MemberDB.getUserID(), MemberDB.getPassword(), "a", MemberDB.getVat());
				sqlmethod.inisertMemberData(MemberDB.getUserID(), MemberDB.getName(), MemberDB.getBirthday(),
						MemberDB.getEmail());
				sqlmethod.closeConn();
			} else if (sType.equals("form-b")) {
				sqlmethod.inisertMember(MemberDB.getUserID(), MemberDB.getPassword(), "b", MemberDB.getVat());
				sqlmethod.inisertMemberDataVendor(MemberDB.getUserID(), MemberDB.getVendor(), MemberDB.getPrincipal(),
						MemberDB.getAddress(), MemberDB.getEmail());
				sqlmethod.closeConn();
			}
			response.sendRedirect("./homePage.html");
			return;
		}
	}
}
