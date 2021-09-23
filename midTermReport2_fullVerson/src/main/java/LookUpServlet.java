

import java.util.List;
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

import empDao.MemberDAO;
import empJavaBean.EmpJavaBean;

/**
 * Servlet implementation class LookUpServlet
 */
@WebServlet("/LookUpServlet")
public class LookUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LookUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
		Connection conn = null;
    	
    	try {
    		InitialContext ctxt = new InitialContext();
			DataSource ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/EmployeeDB");  // for MS SQL Server
			conn = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	dao = new MemberDAO();
    	dao.setConnection(conn);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<EmpJavaBean> memberArrayList = dao.queryAll();
		request.setAttribute("all", memberArrayList);
		request.getRequestDispatcher("/LookUpMember.jsp").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	

}
