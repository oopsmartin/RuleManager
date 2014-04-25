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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.cncert.mtxrulemanager.models.MTxRules;
import org.cncert.mtxrulemanager.models.MTxRule;

/**
 * Servlet implementation class SearchRule
 */

public class SearchRule extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	MTxRules rules = null;
	List<MTxRule> ruleList = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchRule() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	rules = new MTxRules();
        	Map<String,Object> condition = new HashMap<String,Object>();
        	ruleList = new ArrayList<MTxRule>();
        	
        	String createBy = request.getParameter("createBy");
        	
        	String deployNational = request.getParameter("deployNational");
        	String deployProvincial = request.getParameter("deployProvincal");
        	String deployGate = request.getParameter("deployGate");
        	String deployProvider = request.getParameter("deployProvider");
        	
        	String group1 = request.getParameter("group1");
        	String group2 = request.getParameter("group2");
        	String group3 = request.getParameter("group3");
        	String group4 = request.getParameter("group4");
        	        	
        	String effectedTime = request.getParameter("effectedTime");
        	String eventAction = request.getParameter("eventAction");
        	String eventType = request.getParameter("eventType");
        	String expiredTime = request.getParameter("expiredTime");
        	String expiredHour = request.getParameter("expiredHour");
        	String effectedHour = request.getParameter("effectedHour");
        	boolean isEnabled = (request.getParameter("isEnabled")).equals("true")?true:false;
        	String matchPattern = request.getParameter("matchPattern");
        	String protocol = request.getParameter("protocol");
        	String regTime = request.getParameter("regTime");
        	String remark = request.getParameter("remark");
        	String remark1 = request.getParameter("remark1");
        	String remark2 = request.getParameter("remark2");
        	boolean reverseMatch = (request.getParameter("reverseMatch")).equals("true")?true:false;
        	String ruleName = request.getParameter("ruleName");
        	String ruleNumber = request.getParameter("ruleNumber");
        	String ruleType = request.getParameter("ruleType");
        	
        	condition.put("CreateBy", createBy);
        	if(effectedTime.length()==11)
        		condition.put("EffectedTime", Timestamp.valueOf(effectedTime+" "+effectedHour+":00:00"));
        	condition.put("EventAction", eventAction);
        	condition.put("EventType", eventType);
        	if(expiredTime.length()==11)
        		condition.put("ExpiredTime", Timestamp.valueOf(expiredTime+" "+expiredHour+":00:00"));
        	condition.put("IsEnabled", isEnabled);
        	condition.put("MatchPattern", matchPattern);
        	condition.put("Protocol", protocol);        	
        	condition.put("Remark", remark);
        	condition.put("Remark1", remark1);
        	condition.put("Remark2", remark2);
        	condition.put("ReverseMatch", reverseMatch);
        	condition.put("RuleName", ruleName);
        	condition.put("RuleNumber", ruleNumber);
        	condition.put("RuleType", ruleType);
        	
        	String deployPlaces = deployNational+"\\t"+deployProvincial+"\\t"+
        				deployGate+"\\t"+deployProvider+"\\t";
        	condition.put("DeployPlaces", deployPlaces);
        	
        	String groups = group1+"\\t"+group2+"\\t"+
    				group3+"\\t"+group4+"\\t";
        	condition.put("Groups", groups);
        	
        	ruleList = rules.search(condition);
        	
        	if(ruleList.size()==0)
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
        		out.println("<th>CreateBy");
        		out.println("</th>");
        		out.println("<th>DeployPlaces");
        		out.println("</th>");
        		out.println("<th>EffectedTime");
        		out.println("</th>");
        		out.println("<th>EventAction");
        		out.println("</th>");
        		out.println("<th>EventType");
        		out.println("</th>");
        		out.println("<th>ExpiredTime");
        		out.println("</th>");
        		out.println("<th>Groups");
        		out.println("</th>");
        		out.println("<th>IsEnabled");
        		out.println("</th>");
        		out.println("<th>MatchPattern");
        		out.println("</th>");
        		out.println("<th>Protocol");
        		out.println("</th>");
        		out.println("<th>RegTime");
        		out.println("</th>");
        		out.println("<th>Remark");
        		out.println("</th>");
        		out.println("<th>Remark1");
        		out.println("</th>");
        		out.println("<th>Remark2");
        		out.println("</th>");
        		out.println("<th>ReverseMatch");
        		out.println("</th>");
        		out.println("<th>RuleName");
        		out.println("</th>");
        		out.println("<th>RuleNumber");
        		out.println("</th>");
        		out.println("<th>RuleType");
        		out.println("</th>");
        		out.println("</tr>");
        		
        		for(Iterator<MTxRule> iter=ruleList.iterator();iter.hasNext();)
        		{
        			MTxRule tmpRule = iter.next();
        			out.println("<tr>");
            		out.println("<td>"+tmpRule.getID());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.getCreateBy());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.getDeployPlaces());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.getEffectedTime());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.getEventAction());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.getEventType());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.getExpiredTime());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.getGroups());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.isIsEnabled());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.getMatchPattern());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.getProtocol());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.getRegTime());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.getRemark());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.getRemark1());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.getRemark2());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.isReverseMatch());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.getRuleName());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.getRuleNumber());
            		out.println("</td>");
            		out.println("<td>"+tmpRule.getRuleType());
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
