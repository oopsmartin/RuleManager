/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cncert.mtxrulemanager.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cncert.mtxrulemanager.models.MTxRule;
import org.cncert.mtxrulemanager.models.MTxRules;
import org.cncert.mtxrulemanager.models.MTxUser;
import org.cncert.mtxrulemanager.models.MTxUsers;

/**
 *
 * @author GaoSheng
 */
public class AddRule extends HttpServlet {

	MTxRules rules = null;
	List<MTxRule> ruleList = null;
    
	protected boolean validate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		PrintWriter out = response.getWriter(); 
		try {			   
        	response.setContentType("text/xml");
            response.setHeader("Cache-Control","no-cache");
            
            rules = new MTxRules();
            ruleList = new ArrayList<MTxRule>();
            ruleList = rules.getAll();
            
            String xml = "";
            int i;            
            String ruleType;
            String xml_start = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
            String xml_desc = "<web-app xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:web=\"http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\" version=\"2.5\">";
            String xml_end = "\n</web-app>";
            String inputRuleType = request.getParameter("RuleType");
            System.out.printf("inputUsername is %s\n",inputRuleType);
            for(i=0;i<ruleList.size();i++)
            {
               ruleType = ((MTxRule)ruleList.get(i)).getRuleType(); 
               xml += "\n\t<ruletype>"+ruleType+"</ruletype>";
               if (inputRuleType.equals(ruleType))
               {    
            	   response.getWriter().write(xml_start+xml_desc+xml+xml_end);
            	   return false;
               }
               
            }           
            
            System.out.printf("%s", xml_start+xml_desc+xml+xml_end);
            response.getWriter().write(xml_start+xml_desc+xml+xml_end);
			
            return true;
            
			} catch (IOException e) {				
				e.printStackTrace();
			} finally {
            out.close();            
        }
		return false;
	}
	/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            MTxRule rule = new MTxRule();
            rules = new MTxRules();
            
            System.out.printf("CreateBy is %s\n",request.getParameter("createBy"));
            if(request.getParameter("ExpiredTime")!=null)
            	rule.setCreateBy(request.getParameter("createBy"));
            
            System.out.printf("time is %s\n",request.getParameter("EffectedTime")+" "+request.getParameter("EffectedHour")+":00:00");
            if(request.getParameter("EffectedTime")!=null)
               rule.setEffectedTime(java.sql.Timestamp.valueOf((String)request.getParameter("EffectedTime")+" "+(String)request.getParameter("EffectedHour")+":00:00"));            
            	
            System.out.printf("EventAction is %s\n",request.getParameter("Eventaction"));
            if(request.getParameter("ExpiredTime")!=null)
            	rule.setEventAction(request.getParameter("Eventaction"));
            System.out.printf("EventType is %s\n",request.getParameter("EventType"));
            
            rule.setEventType(request.getParameter("EventType"));
            //System.out.printf("ExpiredTime is %s\n",java.sql.Date.valueOf(request.getParameter("ExpiredTime")));
            
            if(request.getParameter("ExpiredTime")!=null)
            	rule.setExpiredTime(java.sql.Timestamp.valueOf((String)request.getParameter("ExpiredTime")+" "+(String)request.getParameter("ExpiredHour")+":00:00"));
            System.out.printf("IsEnabled is %s\n",request.getParameter("IsEnabled"));
            if(request.getParameter("IsEnabled")!=null)
            	rule.setIsEnabled(request.getParameter("IsEnabled").equals("on")?true:false);
            System.out.printf("MatchPattern is %s\n",request.getParameter("MatchPattern"));
            if(request.getParameter("MatchPattern")!=null)
            	rule.setMatchPattern(request.getParameter("MatchPattern"));
            System.out.printf("Protocol is %s\n",request.getParameter("Protocol"));
            if(request.getParameter("Protocol")!=null)
            	rule.setProtocol(request.getParameter("Protocol"));
            Timestamp currentTime = new Timestamp(new Date().getTime());
            rule.setRegTime(currentTime);
            System.out.printf("Remark is %s\n",request.getParameter("Remark"));
            if(request.getParameter("Remark")!=null)
            	rule.setRemark(request.getParameter("Remark"));
            System.out.printf("Remark1 is %s\n",request.getParameter("Remark1"));
            if(request.getParameter("Remark1")!=null)
            	rule.setRemark1(request.getParameter("Remark1"));
            System.out.printf("Remark2 is %s\n",request.getParameter("Remark2"));
            if(request.getParameter("Remark2")!=null)
            	rule.setRemark2(request.getParameter("Remark2"));
            System.out.printf("ReverseMatch is %s\n",request.getParameter("ReverseMatch"));
            if(request.getParameter("ReverseMatch")!=null)
            	rule.setReverseMatch(request.getParameter("ReverseMatch").equals("true")?true:false);
            System.out.printf("RuleName is %s\n",request.getParameter("RuleName"));
            if(request.getParameter("RuleName")!=null)
            	rule.setRuleName(request.getParameter("RuleName"));
            System.out.printf("RuleNumber is %s\n",request.getParameter("RuleNumber"));
            if(request.getParameter("RuleNumber")!=null)
            	rule.setRuleNumber(request.getParameter("RuleNumber"));            
            System.out.printf("RuleType is %s\n",request.getParameter("RuleType"));
            if(request.getParameter("RuleType")!=null)
            	rule.setRuleType(request.getParameter("RuleType"));
            
            String[] Places = new String[4];
            Places[0] = request.getParameter("deployNational")!=null?"国际":"";
            Places[1] = request.getParameter("deployProvincial")!=null?"省际":"";
            Places[2] = request.getParameter("deployGate")!=null?"关口":"";
            Places[3] = request.getParameter("deployProvider")!=null?"供应商":"";
            rule.setDeployPlaces(Places);
            System.out.printf("places are %s\n", rule.getDeployPlaces());           
            
            String[] Groups = new String[4];
            Groups[0] = request.getParameter("group1")!=null?"group1":"";
            Groups[1] = request.getParameter("group2")!=null?"group2":"";
            Groups[2] = request.getParameter("group3")!=null?"group3":"";
            Groups[3] = request.getParameter("group4")!=null?"group4":"";
            rule.setGroups(Groups);
            System.out.printf("groups are %s\n", rule.getGroups());            
            
            System.out.printf("rule's RuleType is %s\n",rule.getRuleType());
            
            rules.insert(rule);
            
            
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("get");
    	validate(request, response);
    	//processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("post");
    	if(validate(request, response))
    		processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
