package org.cncert.mtxrulemanager.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cncert.mtxrulemanager.models.MTxRuleGroup;
import org.cncert.mtxrulemanager.models.MTxRuleGroups;

/**
 * Servlet implementation class Redirect
 */

public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	String id = request.getParameter("activity");
        	System.out.printf("redirect to %s\n", id);
        	
        	if(id.equals("addGroup"))        	
    			response.sendRedirect("addgroup.jsp");        	
    		else if(id.equals("addRule"))
    		{
    			String username = (String)request.getParameter("createBy");
    			System.out.printf("username ie CreateBy is %s\n",username);
    			response.sendRedirect("addrule.jsp?CreateBy="+username);
    		}
    		else if(id.equals("addUser"))
    			response.sendRedirect("adduser.jsp");
    		else if(id.equals("delGroup"))
    			response.sendRedirect("delgroup.jsp");
    		else if(id.equals("delRule"))
    			response.sendRedirect("delrule.jsp");
    		else if(id.equals("delUser"))
    			response.sendRedirect("deluser.jsp");
    		else if(id.equals("updateGroup"))
    			response.sendRedirect("updategroup.jsp");
    		else if(id.equals("updateRule"))
    			response.sendRedirect("updaterule.jsp");
    		else if(id.equals("updateUser"))
    			response.sendRedirect("updateuser.jsp");
    		else if(id.equals("searchGroup"))
    			response.sendRedirect("searchgroup.jsp");
    		else if(id.equals("searchRule"))
    			response.sendRedirect("searchrule.jsp");
    		else if(id.equals("searchUser"))
    			response.sendRedirect("searchuser.jsp");
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            out.close();
        }
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet");
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost");
		processRequest(request,response);
	}

}
