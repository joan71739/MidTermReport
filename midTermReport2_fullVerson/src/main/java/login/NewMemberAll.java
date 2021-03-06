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

			String administrator = request.getParameter("userID");// ??????
			String password = request.getParameter("pws");// ??????
			String passwordck = request.getParameter("passwordck");// ??????
			String user = request.getParameter("name");// ??????
			String birthday = request.getParameter("birthday");// ???????????????
			String email = request.getParameter("email");// email
			//
			String vendor = request.getParameter("vendor");
			String principal = request.getParameter("principal");// ?????????
			String vat = request.getParameter("vat");// ????????????
			String address = request.getParameter("address");// ????????????
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
			// ????????????????????????
			if ("".equals(administrator)) {
				request.getRequestDispatcher("/NewMemberAll.jsp").forward(request, response);
				return;
			}
			// ??????????????????????????????
			boolean ckPws = false;
			if (password.equals(passwordck) && !"".equals(password)) {
				ckPws = true;
			}
			// ???sql????????????????????????
			SQLMethod sqlmethod = new SQLMethod();
			sqlmethod.createConn();
			ResultSet res = sqlmethod.searchMember(administrator);
			sqlmethod.closeConn();
			// ????????????????????????????????????
			if (res == null && ckPws) {
				request.getRequestDispatcher("/NewMemberChackAll.jsp").forward(request, response);
				return;
			} else {
				request.getRequestDispatcher("/NewMemberAll.jsp").forward(request, response);
				// session.setAttribute("ckUserID", "repeat");
				return;
			}
		} else if (submit.equals("submit")) {
			// ??????sql
			// a:???????????? b:???????????? c:?????? d:??????
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
