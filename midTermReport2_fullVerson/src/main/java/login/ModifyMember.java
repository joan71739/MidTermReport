package login;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ModifyMember
 */
@WebServlet("/ModifyMember")
public class ModifyMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
//	public void init(ServletConfig config) throws ServletException {
//		// TODO Auto-generated method stub
//	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(true);
		String submit = request.getParameter("sub");//button
		String sType = request.getParameter("type");//form表單
		
		if (submit.equals("modifySubmit")) {
			
			String administrator = request.getParameter("userID");// 帳號
			String password = request.getParameter("pws");// 密碼
			String passwordck = request.getParameter("passwordck");// 密碼
			String user = request.getParameter("name");// 姓名
			String birthday = request.getParameter("birthday");// 出生年月日
			String email = request.getParameter("email");// email
			
			boolean ckPws = false;
			if(password.equals(passwordck)) {
				ckPws = true;
			}
			if(!ckPws) {
				request.getRequestDispatcher("/ModifyMember.jsp").forward(request, response);
				return;
			}
			SQLMethod sqlmethod = new SQLMethod();
			sqlmethod.createConn();
			sqlmethod.updateMember(administrator, password, "a" , null);
			sqlmethod.updateMemberData(administrator, user, birthday, email);
			sqlmethod.closeConn();
			response.sendRedirect("./homePage.html");
			return;
		}else if (submit.equals("modify")){
			response.sendRedirect("./ModifyMember.jsp");
			return;
		}else if (submit.equals("delete")){
			session.setAttribute("Delete", "delete");
			request.getRequestDispatcher("ModifyFirstMember.jsp").forward(request, response);
			//response.sendRedirect("./ModifyFirstMember.jsp");
			return;
		}else if(submit.equals("deleteCk")) {
			String administrator = request.getParameter("userID");// 帳號
			SQLMethod sqlmethod = new SQLMethod();
			sqlmethod.createConn();
			sqlmethod.deleteMember(administrator);
			sqlmethod.closeConn();
			session.invalidate();
			request.getRequestDispatcher("homePage.html").forward(request, response);
		}else if(submit.equals("cancel") || submit.equals("cancelCk")) {
			session.setAttribute("Delete", "");
			response.sendRedirect("./memberPage.html");
			return;
		}
		//企業
		if(submit.equals("modifyVendor")) {
			response.sendRedirect("./ModifyMemberVendor.jsp");
			return;
		}else if (submit.equals("modifyVendorSubmit")) {
			
			String vendor = request.getParameter("vendor");
			String administrator = request.getParameter("userID");// 帳號
			String password = request.getParameter("pws");// 密碼
			String passwordck = request.getParameter("passwordck");// 密碼
			String principal = request.getParameter("principal");// 負責人
			String vat = request.getParameter("vat");// 統一編號
			String address = request.getParameter("address");// 統一編號
			String email = request.getParameter("email");// email
	
			boolean ckPws = false;
			if(password.equals(passwordck)) {
				ckPws = true;
			}
			if(!ckPws) {
				request.getRequestDispatcher("/ModifyMemberVendor.jsp").forward(request, response);
				return;
			}
			SQLMethod sqlmethod = new SQLMethod();
			sqlmethod.createConn();
			sqlmethod.updateMember(administrator, password, "b", vat);
			sqlmethod.updateMemberDataVendor(administrator, vendor, principal, address, email);
			sqlmethod.closeConn();
			response.sendRedirect("./enterprise.html");
			return;
		}else if (submit.equals("deleteVendor")){
			session.setAttribute("Delete", "delete");
			request.getRequestDispatcher("ModifyFirstMemberVendor.jsp").forward(request, response);
			//response.sendRedirect("./ModifyFirstMember.jsp");
			return;
		}else if(submit.equals("deleteVendorCk")) {
			String administrator = request.getParameter("userID");// 帳號
			SQLMethod sqlmethod = new SQLMethod();
			sqlmethod.createConn();
			sqlmethod.deleteMemberVendor(administrator);
			sqlmethod.closeConn();
			session.invalidate();
			request.getRequestDispatcher("homePage.html").forward(request, response);
		}else if(submit.equals("cancelVendor") || submit.equals("cancelVenCk")) {
			session.setAttribute("Delete", "");
			response.sendRedirect("./enterprise.html");
			return;
		}
		
	}
}
