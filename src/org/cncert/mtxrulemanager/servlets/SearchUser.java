package org.cncert.mtxrulemanager.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cncert.mtxrulemanager.models.MTxRuleGroup;
import org.cncert.mtxrulemanager.models.MTxRuleGroups;
import org.cncert.mtxrulemanager.models.MTxUsers;
import org.cncert.mtxrulemanager.models.MTxUser;

/**
 * Servlet implementation class SearchUser
 */

public class SearchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	MTxUsers users = null;
	List<MTxUser> userList = null;
    
    public SearchUser() {
        super();
        
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	users = new MTxUsers();
        	Map<String,Object> condition = new HashMap<String,Object>();
        	userList = new ArrayList<MTxUser>();
        	
        	String authorizedIP = request.getParameter("authorizedIP");
        	String createTime = request.getParameter("createTime");
        	String effectTime = request.getParameter("effectTime");
        	String expireTime = request.getParameter("expireTime");
        	String lastLoginTime = request.getParameter("lastLoginTime");
        	String username = request.getParameter("username");
        	
        	condition.put("AuthorizedIP", authorizedIP);
        	if(createTime.length()==11)
        		condition.put("CreateTime", Timestamp.valueOf(createTime+" 00:00:00"));
        	if(effectTime.length()==11)
        		condition.put("EffectTime", Timestamp.valueOf(effectTime+" 00:00:00"));
        	if(expireTime.length()==11)
        		condition.put("ExpireTime", Timestamp.valueOf(expireTime+" 00:00:00"));
        	if(lastLoginTime.length()==11)
        		condition.put("LastLoginTime", Timestamp.valueOf(lastLoginTime+" 00:00:00"));
        	condition.put("UserName", username);
        	
        	userList = users.search(condition);
        	
        	if(userList.size()==0)
        	{
        		out.println("<html>");
        		out.println("<head>");
        		out.println("<title>Not Found</title>\n");        		
        		out.println("</head>");
        		out.println("<body>");        		
        		out.println("</body>");
        		out.println("</html>");
        		
        	}
        	else
        	{
        		out.println("<html>");
        		out.println("<head>");
        		out.println("<title>The Results</title>");        		
        		out.println("</head>");
        		out.println("<body>");
        		out.println("<table border=\"1\">");
        		out.println("<tr>");
        		out.println("<th>ID");
        		out.println("</th>");        		
        		out.println("<th>AuthorizedIP");
        		out.println("</th>");        		
        		out.println("<th>CreateTime");
        		out.println("</th>");
        		out.println("<th>EffectTime");
        		out.println("</th>");
        		out.println("<th>ExpireTime");
        		out.println("</th>");
        		out.println("<th>LastLoginTime");
        		out.println("</th>");
        		out.println("<th>UserName");
        		out.println("</th>");
        		out.println("</tr>");
        		
        		for(Iterator<MTxUser> iter=userList.iterator();iter.hasNext();)
        		{
        			MTxUser tmpUser = iter.next();
        			            		
            		out.println("<tr>");
            		out.println("<td>"+tmpUser.getID());
            		out.println("</td>");
            		out.println("<td>"+tmpUser.getAuthorizedIP());
            		out.println("</td>");
            		out.println("<td>"+tmpUser.getCreateTime());
            		out.println("</td>");
            		out.println("<td>"+tmpUser.getEffectTime());
            		out.println("</td>");
            		out.println("<td>"+tmpUser.getExpireTime());
            		out.println("</td>");
            		out.println("<td>"+tmpUser.getLastLoginTime());
            		out.println("</td>");
            		out.println("<td>"+tmpUser.getUserName());
            		out.println("</td>");
            		out.println("</tr>");            		
            		
        		}
        		out.println("</table>");
        		out.println("</body>");
        		out.println("</html>");
        	}
        	
        } finally {
            out.close();
        }
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		processRequest(request,response);
	}

}
