

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import empDao.MemberDAO;
import empJavaBean.EmpJavaBean;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    //3.建立dao 並且建立連線
    public void init() throws ServletException {
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
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 設定編碼
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //1.
        String name = request.getParameter("name");
        String job = request.getParameter("job");
        String hiredate =request.getParameter("hiredate");
        int sal = Integer.parseInt(request.getParameter("sal"));
        
        //2.
        EmpJavaBean javaBean = new EmpJavaBean();
        javaBean.setName(name);
        javaBean.setJob(job);
        javaBean.setHiredate(hiredate);
        javaBean.setSal(sal);
        
        //4.
        try {
			dao.addMember(javaBean);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
        //request.getRequestDispatcher("/LookUpMember.jsp").forward(request, response);
		//return;
		response.sendRedirect("LookUpServlet"); 
        
        
	}

}
