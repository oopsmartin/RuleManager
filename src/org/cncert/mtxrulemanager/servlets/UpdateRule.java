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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cncert.mtxrulemanager.models.MTxRule;
import org.cncert.mtxrulemanager.models.MTxRuleGroup;
import org.cncert.mtxrulemanager.models.MTxRuleGroups;
import org.cncert.mtxrulemanager.models.MTxRules;

/**
 *
 * @author GaoSheng
 */
public class UpdateRule extends HttpServlet {

	MTxRules rules = null;
	List<MTxRule> ruleList = null;
	
	protected void refreshInput(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	response.setContentType("text/xml");
            response.setHeader("Cache-Control","no-cache");
            //<?xml version="1.0" encoding="UTF-8"?>
            rules = new MTxRules();
            ruleList = new ArrayList<MTxRule>();            
                       
            ruleList = rules.getAll(); 
            
            StringBuffer xml = new StringBuffer();                 
            
            String xml_start = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
            String xml_desc = "<web-app xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:web=\"http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\" version=\"2.5\">";
            String xml_end = "\n</web-app>";
            
            System.out.println("I'm here!!");
            System.out.printf("size is %d\n", ruleList.size());
            int ID = Integer.valueOf(request.getParameter("ID").toString());
            for(Iterator<MTxRule> iter=ruleList.iterator();iter.hasNext();)
            {
            	MTxRule tmpRule = iter.next();
            	
               if(tmpRule.getID()==ID){
            	   String effectedTime = "";
            	   String expiredTime = "";            	   
            	   String remark = "";
            	   String remark1 = "";
            	   String remark2 = "";
            	   int deployNational = 0;
            	   int deployProvincial = 0;
            	   int deployGate = 0;
            	   int deployProvider = 0;
            	   int group1 = 0;
            	   int group2 = 0;
            	   int group3 = 0;
            	   int group4 = 0;
            	   String effectedHour = "";
            	   String expiredHour = "";
            	   
            	String createBy = tmpRule.getCreateBy();
            	System.out.printf("refresh createBy is %s\n", createBy);
            	System.out.printf("deployPlaces are %s\n",tmpRule.getDeployPlaces(0));
            	System.out.printf("groups are %s\n",tmpRule.getGroups(0));
            	
            	String deployPlaces = tmpRule.getDeployPlaces(0);
            	if(deployPlaces.contains("国际"))
            		deployNational = 1;
            	if(deployPlaces.contains("省际"))
            		deployProvincial = 1;
            	if(deployPlaces.contains("关口"))
            		deployGate = 1;
            	if(deployPlaces.contains("供应商"))
            		deployProvider = 1;
            	
            	if(tmpRule.getEffectedTime()!=null){
            		System.out.printf("effected time is %s\n", tmpRule.getEffectedTime().toString().substring(0, 10));
            		
            		effectedTime = tmpRule.getEffectedTime().toString().substring(0, 10);
            		effectedHour = tmpRule.getEffectedTime().toString().substring(11,12);
            	}
            	String eventAction = tmpRule.getEventAction();
            	String eventType = tmpRule.getEventType();
            	if(tmpRule.getExpiredTime()!=null){
            		
            		expiredTime = tmpRule.getExpiredTime().toString().substring(0, 10);
            		expiredHour = tmpRule.getExpiredTime().toString().substring(11,12);
            	}
            	
            	String groups = tmpRule.getGroups(0);
            	if(groups.contains("group1")){
            		group1 = 1;
            		System.out.println("1");
            	}
            	if(groups.contains("group2")){
            		group2 = 1;
            		System.out.println("2");
            	}
            	if(groups.contains("group3")){
            		group3 = 1;
            		System.out.println("3");
            	}
            	if(groups.contains("group4")){
            		group4 = 1;           	
            		System.out.println("4");
            	}
            	
            	boolean isEnabled = tmpRule.isIsEnabled();
            	String matchPattern = tmpRule.getMatchPattern();
            	String protocol = tmpRule.getProtocol();
            	Timestamp regTime = tmpRule.getRegTime();
            	if(tmpRule.getRemark()!=null)
            		remark = tmpRule.getRemark();
            	if(tmpRule.getRemark1()!=null)
            		remark1 = tmpRule.getRemark1();
            	if(tmpRule.getRemark2()!=null)
            		remark2 = tmpRule.getRemark2();
            	boolean reverseMatch = tmpRule.isReverseMatch();
            	String ruleName = tmpRule.getRuleName();
            	String ruleNumber = tmpRule.getRuleNumber();
            	String ruleType = tmpRule.getRuleType();
            	
            	
            	xml.append("\n<createBy>"+createBy+"</createBy>");
            	
            	if(deployNational==1)
            		xml.append("\n<deployNational>1</deployNational>");
            	if(deployProvincial==1)
            		xml.append("\n<deployProvincial>1</deployProvincial>");
            	if(deployGate==1)
            		xml.append("\n<deployGate>1</deployGate>");
            	if(deployProvider==1)
            		xml.append("\n<deployProvider>1</deployProvider>");            	
            	
            	
            	xml.append("\n<eventAction>"+eventAction+"</eventAction>");
            	xml.append("\n<eventType>"+eventType+"</eventType>");
            	xml.append("\n<effectedHour>"+effectedHour+"</effectedHour>");
            	xml.append("\n<expiredHour>"+expiredHour+"</expiredHour>");
            	xml.append("\n<effectedTime>"+effectedTime+"</effectedTime>");
            	xml.append("\n<expiredTime>"+expiredTime+"</expiredTime>");
            	
            	if(group1==1)
            		xml.append("\n<group1>1</group1>");
            	if(group2==1){
            		xml.append("\n<group2>1</group2>");
            		System.out.println("<group2> setted");
            	}
            	if(group3==1)
            		xml.append("\n<group3>1</group3>");
            	if(group4==1)
            		xml.append("\n<group4>1</group4>");
            	
            	xml.append("\n<isEnabled>"+isEnabled+"</isEnabled>");
            	xml.append("\n<matchPattern>"+matchPattern+"</matchPattern>");
            	xml.append("\n<protocol>"+protocol+"</protocol>");
            	xml.append("\n<regTime>"+regTime+"</regTime>");
            	xml.append("\n<remark>"+remark+"</remark>");
            	xml.append("\n<remark1>"+remark1+"</remark1>");
            	xml.append("\n<remark2>"+remark2+"</remark2>");
            	xml.append("\n<reverseMatch>"+reverseMatch+"</reverseMatch>");
            	xml.append("\n<ruleName>"+ruleName+"</ruleName>");
            	xml.append("\n<ruleNumber>"+ruleNumber+"</ruleNumber>");
            	xml.append("\n<ruleType>"+ruleType+"</ruleType>");
            	break;  
               }
            }
            
            response.getWriter().write(xml_start+xml_desc+xml+xml_end);
            
            
        } finally {
            out.close();
        }
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
        	String[] deployPlaces = new String[4];
        	String[] groups = new String[4];
        	
        	rule.setCreateBy(request.getParameter("createBy"));
        	
        	deployPlaces[0] = request.getParameter("deployNational")==null?"\\t":"国际\\t";
        	deployPlaces[1] = request.getParameter("deployProvincial")==null?"\\t":"省际\\t";
        	deployPlaces[2] = request.getParameter("deployGate")==null?"\\t":"关口\\t";
        	deployPlaces[3] = request.getParameter("deployProvider")==null?"\\t":"供货商\\t";
        	rule.setDeployPlaces(deployPlaces);
        	
        	System.out.printf("test time is %s\n", request.getParameter("effectedTime")+" "+request.getParameter("effectedHour")+":00:00");
        	if(request.getParameter("effectedTime")!=null)
        		rule.setEffectedTime(java.sql.Timestamp.valueOf(request.getParameter("effectedTime")+" "+request.getParameter("effectedHour")+":00:00.00"));
        	rule.setEventAction(request.getParameter("eventAction"));
        	rule.setEventType(request.getParameter("eventType"));
        	if(request.getParameter("expiredTime")!=null)
        		rule.setExpiredTime(java.sql.Timestamp.valueOf(request.getParameter("expiredTime")+" "+request.getParameter("expiredHour")+":00:00.00"));
        	
        	groups[0] = request.getParameter("group1")==null?"\\t":"group1\\t";
        	groups[1] = request.getParameter("group2")==null?"\\t":"group2\\t";
        	groups[2] = request.getParameter("group3")==null?"\\t":"group3\\t";
        	groups[3] = request.getParameter("group4")==null?"\\t":"group4\\t";
        	rule.setGroups(groups);
        	
        	if(request.getParameter("isEnabled")!=null)
        		rule.setIsEnabled(request.getParameter("isEnabled").equals("true")?true:false);
        	rule.setMatchPattern(request.getParameter("matchPattern"));
        	rule.setProtocol(request.getParameter("protocol"));
        	//rule.setRegTime(java.sql.Timestamp.valueOf(request.getParameter("regTime")));
        	if(request.getParameter("remark")!=null)
        		rule.setRemark(request.getParameter("remark"));
        	if(request.getParameter("remark1")!=null)
        		rule.setRemark1(request.getParameter("remark1"));
        	if(request.getParameter("remark2")!=null)
        		rule.setRemark2(request.getParameter("remark2"));
        	rule.setReverseMatch(request.getParameter("reverseMatch").equals("true")?true:false);
        	rule.setRuleName(request.getParameter("ruleName"));
        	rule.setRuleNumber(request.getParameter("ruleNumber"));
        	rule.setRuleType(request.getParameter("ruleType"));
        	
        	if(rules==null)
        	{
        		rules = new MTxRules();
        	}
        	rules.update(rule);
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
    	System.out.println("doGet");
    	refreshInput(request,response);
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
    	System.out.println("doPost");
    	refreshInput(request,response);
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
