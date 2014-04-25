package org.cncert.mtxrulemanager.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cncert.mtxrulemanager.models.MTxRuleGroup;
import org.cncert.mtxrulemanager.models.MTxRuleGroups;

/**
 * Servlet implementation class SearchGroup
 */
public class SearchGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MTxRuleGroups groups = null; 
    List<MTxRuleGroup> groupList = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	groups = new MTxRuleGroups();
        	Map<String,Object> condition = new HashMap<String,Object>();
        	groupList = new ArrayList<MTxRuleGroup>();
        	
        	String groupName = request.getParameter("groupname");
        	
        	condition.put("GroupName", groupName);
        	
        	groupList = groups.search(condition);
        	
        	if(groupList.size()==0)
        	{
        		out.println("<html>");
        		out.println("<head>");
        		out.println("<title>Not Found</title>\n");        		
        		out.println("</head>");
        		out.println("<body>");
        		out.println("<p>Check the goupname</p>");
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
        		out.println("<th>GroupName");
        		out.println("</th>");
        		out.println("<th>CreateTime");
        		out.println("</th>");
        		out.println("</tr>");
        		int ID;
        		String GroupName;
        		Timestamp CreateTime;
        		for(int i=0;i<groupList.size();i++)
        		{
        			ID = groupList.get(i).getID();
        			GroupName = groupList.get(i).getGroupName();
        			CreateTime = groupList.get(i).getCreateTime();
        			            		
            		out.println("<tr>");
            		out.println("<td>"+ID);
            		out.println("</td>");
            		out.println("<td>"+GroupName);
            		out.println("</td>");
            		out.println("<td>"+CreateTime);
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
		
		System.out.println("doGet");
		processRequest(request,response);
	}

}
