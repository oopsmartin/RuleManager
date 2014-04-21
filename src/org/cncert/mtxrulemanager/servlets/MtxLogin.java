package org.cncert.mtxrulemanager.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cncert.mtxrulemanager.models.MTxRuleGroup;
import org.cncert.mtxrulemanager.models.MTxRuleGroups;
import org.cncert.mtxrulemanager.models.MTxUsers;
import org.cncert.mtxrulemanager.models.MTxUser;

/**
 * Servlet implementation class Login
 */

public class MtxLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	MTxUsers users = null;
	List<MTxUser> userList = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MtxLogin() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	MTxUser tmpUser = new MTxUser();
        	Map<String,Object> condition = new HashMap<String,Object>();
        	userList = new ArrayList<MTxUser>();
        	users = new MTxUsers();
        	
            String username = request.getParameter("username");
            String password = request.getParameter("password");        	
            
            System.out.printf("username is %s\n", username);
            System.out.printf("password is %s\n", password);
            
            condition.put("UserName", username);
            condition.put("Password", password);
            System.out.printf("condition size is %d\n", condition.size());
            userList = users.search(condition);
        	for(Iterator<MTxUser> iter = userList.listIterator();iter.hasNext();){
        		tmpUser = (MTxUser)iter.next();
        		System.out.printf("UserName is %s",tmpUser.getUserName());
        		System.out.println();
        	}
        	/**
            if(users.search(condition)!=null)
            {
            	tmpUser = (MTxUser)users.search(condition).get(0);
            }
            **/
        	
            System.out.printf("checked is %s\n", request.getParameter("checked"));
            
                Cookie cookieuser = new Cookie("user",username+"-"+password); 
                //设定有效时间  以秒(s)为单位
                cookieuser.setMaxAge(60); 
                //设置Cookie路径和域名
                cookieuser.setPath("/") ;
                cookieuser.setDomain(".zl.org") ;  //域名要以“.”开头
                response.addCookie(cookieuser); 
                              
               if(tmpUser.getUserName()!="")
            	   response.sendRedirect("main.jsp");
               else
            	   response.setContentType("text/html;charset=UTF-8");               	   
               	   out.println("<html>");
               	   out.println("<head><title>set cookies </title></head>");
               	   out.println("<body>");
               	   out.println("<h2>invalid input</h2>");
               	   out.println("</body>");
               	   out.println("</html>");
               	   out.flush();
               	   out.close();
               
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
