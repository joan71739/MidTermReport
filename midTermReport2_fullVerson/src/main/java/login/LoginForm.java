package login;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginForm
 */
@WebServlet("/LoginForm")
public class LoginForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginForm() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
//	protected void service(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		if(true) {
//			//request.getRequestDispatcher("/WebContent/TestPage.html").forward(request, response);
//		}
//	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		Cookie userIDCookie = null;
		Cookie userRe = null;

		String rem_me = request.getParameter("remember_me");
		String submit = request.getParameter("sub");// button
		String sType = request.getParameter("type");// ??????type


		// ???SQL
		SQLMethod sqlmethod = DAOFactory.getSQLMemberDAO();
		sqlmethod.createConn();

//		Date dNow = new Date( );
//	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss");
//	    String ss = ft.format(dNow);
//	    System.out.println("Current Date: " + ft.format(dNow));
//	    System.out.println(ss);
		if (submit == null) {
			if (sType.equals("form-a")) {
				session.setAttribute("Type", "A");
			}
			if (sType.equals("form-b")) {
				session.setAttribute("Type", "B");
			}
			request.getRequestDispatcher("/ForgotPassword.jsp").forward(request, response);
			return;
		}

		if (submit.equals("login")) {
			// ??????member????????????
			String administrator = request.getParameter("userID");
			String password = request.getParameter("password");// ??????
			boolean ckackUser = false;
			ResultSet res = sqlmethod.searchMember(administrator);
			String pwsString = null;
			String classString = null;
			String vat = null;
			if (res != null) {
				// ???????????????????????????
				ckackUser = true;
				try {
					// SQLMethod????????????next??????
					pwsString = res.getString(2);// ??????
					classString = res.getString(3);// ???????????? a:??????
					// ?????????get????????????
					if (!classString.equals("a")) {
						vat = res.getString(4);// ????????????
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				if (ckackUser && password.equals(pwsString)) {
					// request.getRequestDispatcher("/TestPage.html").forward(request, response);
					session.setAttribute("UserID", administrator);// ??????
					session.setAttribute("ClassMember", classString);// ??????
					session.setAttribute("Vat", vat);// ?????? ????????????null
					session.setAttribute("Longin", "success");// ??????????????????

					if (rem_me != null) {
						userIDCookie = new Cookie("UserID", administrator);
						userRe = new Cookie("remember_me", rem_me);
						userIDCookie.setMaxAge(60 * 60 * 24); // Store cookie for 24hr
						userRe.setMaxAge(60 * 60 * 24); // Store cookie for 24hr
						response.addCookie(userIDCookie);
						response.addCookie(userRe);
					}
					if (rem_me == null) {
						userIDCookie = new Cookie("UserID", administrator);
						userRe = new Cookie("remember_me", rem_me);
						userIDCookie.setMaxAge(0);
						userRe.setMaxAge(0);
						response.addCookie(userIDCookie);
						response.addCookie(userRe);
					}
					// ???SQL
					sqlmethod.closeConn();
					if (classString.equals("fa")) {
						// ????????????????????? ??????
						session.setAttribute("Type", "C");
						request.getRequestDispatcher("ModifyMember.jsp").forward(request, response);
						return;
					} else if (classString.equals("fb")) {
						// ????????????????????? ??????
						session.setAttribute("Type", "C");
						request.getRequestDispatcher("ModifyMemberVendor.jsp").forward(request, response);
						return;
					}
					response.sendRedirect("homePage.html");
					return;
				} else {
					session.setAttribute("UserID", administrator);
					session.setAttribute("fal", "f");
					Cookie[] recook = request.getCookies();
					if (recook != null) {
						for (Cookie cookie : recook) {
							cookie.setValue("");
							cookie.setPath("/");
							cookie.setMaxAge(0);
							response.addCookie(cookie);
						}
					}
					userIDCookie = new Cookie("UserID", administrator);
					userRe = new Cookie("remember_me", rem_me);
					userIDCookie.setMaxAge(60 * 60 * 24);
					userRe.setMaxAge(60 * 60 * 24);
					response.addCookie(userIDCookie);
					response.addCookie(userRe);
					// ???SQL
					sqlmethod.closeConn();
					response.sendRedirect("LoginPage.jsp");
//					request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
					return;
				}

			} else {
				// ??????????????????
				session.setAttribute("UserID", administrator);
				session.setAttribute("fal", "f");
				sqlmethod.closeConn();// ???SQL
				request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
				return;
			}
		} else if (submit.equals("cancel")) {
			response.sendRedirect("homePage.html");
			return;
		} else if (submit.equals("longout")) {
			session.invalidate();
			response.sendRedirect("LongOut.html");
			return;
		}
		if (submit.equals("forgChack")) {
			String administrator = request.getParameter("userID");// ??????
			String birthday = request.getParameter("birthday");// ???????????????
			String email = request.getParameter("email");// email

			String principal = request.getParameter("principal");// ?????????
			String vat = request.getParameter("vat");// ????????????
			MailService sendMailService = new MailService();
			ResultSet res1 = null;
			ResultSet res2 = null;
			String nPws = "";// ?????????
			String tmp = "";
			for (int i = 0; i < 6; i++) {
				int n = (int) Math.floor(Math.random() * 3) % 3;
				if (n == 1) {
					tmp = String.valueOf((char) Math.floor((Math.random() * 26) + 65));// ??????
				} else if (n == 2) {
					tmp = String.valueOf((char) Math.floor((Math.random() * 26) + 97));// ??????
				} else {
					tmp = String.valueOf((int) Math.floor(Math.random() * 9));// ??????
				}
				nPws += tmp;
			}
			if (sType.equals("form-a")) {
				res1 = sqlmethod.searchMember(administrator);
				res2 = sqlmethod.searchMemberData(administrator);
				if (res1 == null) {
					session.setAttribute("adm", "err");
					request.getRequestDispatcher("/ForgotPassword.jsp").forward(request, response);
					return;
				} else if (res1 != null) {
					try {
						if (res2.getString(3).equals(birthday) && res2.getString(4).equals(email)) {
							sqlmethod.updateMember(administrator, nPws, "fa", null);
							sqlmethod.closeConn();
							sendMailService.sendMail(email, nPws);
							session.setAttribute("Type", "C");
							response.sendRedirect("LoginPage.jsp");
							return;
						}

					} catch (SQLException e) {
						e.printStackTrace();
					} 
				}

			}
			if (sType.equals("form-b")) {
				res1 = sqlmethod.searchMember(administrator);
				res2 = sqlmethod.searchMemberDataVendor(administrator);
				if (res1 == null) {
					session.setAttribute("adm", "err");
					request.getRequestDispatcher("/ForgotPassword.jsp").forward(request, response);
					return;
				} else if (res1 != null) {
					try {
						if (res1.getString(4).equals(vat) && res2.getString(3).equals(principal)
								&& res2.getString(5).equals(email)) {
							sqlmethod.updateMember(administrator, nPws, "fb", vat);
							sqlmethod.closeConn();
							sendMailService.sendMail(email, nPws);
							session.setAttribute("Type", "C");
							response.sendRedirect("LoginPage.jsp");
							return;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} 
				}
			}
		}
//		else {
//			request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
//			return;
//		}

	}

}
