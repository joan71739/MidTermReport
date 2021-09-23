

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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 設定編碼
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //1.
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String job = request.getParameter("job");
        String hiredate =request.getParameter("hiredate");
        int sal = Integer.parseInt(request.getParameter("sal"));
        //2.
        EmpJavaBean javaBean = new EmpJavaBean();
        javaBean.setId(id);
        javaBean.setName(name);
        javaBean.setJob(job);
        javaBean.setHiredate(hiredate);
        javaBean.setSal(sal);
        
        //4.
        try {
			dao.updateById(javaBean);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
        response.sendRedirect("LookUpServlet");
        
	}

}
