/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cncert.mtxrulemanager.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cncert.mtxrulemanager.models.MTxRuleGroup;
import org.cncert.mtxrulemanager.models.MTxRuleGroups;
import org.cncert.mtxrulemanager.models.MTxUser;
import org.cncert.mtxrulemanager.models.MTxUsers;

/**
 *
 * @author WangChao
 */
public class UpdateGroup extends HttpServlet {

	MTxRuleGroups ruleGroups = null;
	List<MTxRuleGroup> groupList = null;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * 
     */
    protected void refreshInput(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	response.setContentType("text/xml");
            response.setHeader("Cache-Control","no-cache");
            //<?xml version="1.0" encoding="UTF-8"?>
            ruleGroups = new MTxRuleGroups();
            groupList = new ArrayList<MTxRuleGroup>();
            groupList = ruleGroups.getAll();
            
            String xml = "";
            int i;
            int ID;
            String groupName = new String();
            String xml_start = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
            String xml_desc = "<web-app xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:web=\"http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\" version=\"2.5\">";
            String xml_end = "\n</web-app>";
            String targetId = (String)request.getParameter("ID").toString();
            //System.out.printf("targetID is %s\n",targetId);
            for(i=0;i<groupList.size();i++)
            {
               ID = ((MTxRuleGroup)groupList.get(i)).getID();        	
               if (targetId.equals(new Integer(ID).toString()))
               {
            	   groupName = ((MTxRuleGroup)groupList.get(i)).getGroupName();
            	   break;
               }
            }
            //System.out.printf("groupName is %s\n",groupName);
            xml = "\n\t<groupname>"+groupName+"</groupname>";
            //System.out.printf("%s", xml_start+xml_desc+xml+xml_end);
            response.getWriter().write(xml_start+xml_desc+xml+xml_end);
            
            
        } finally {
            out.close();
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	String groupName = new String();
        	MTxRuleGroup group = new MTxRuleGroup();
        	groupName = (String)request.getParameter("changedGroupName");
        	System.out.printf("groupname now is %s\n", groupName);
        	String ID = (String)request.getParameter("ID");
        	String gName = (String)request.getParameter("groupname");
        	System.out.printf("ID is %s\n",ID);
        	System.out.printf("gName is %s\n", gName);
        	int IDint = Integer.parseInt(ID);
        	if(gName!=null){
        	   group.setID(IDint);
        	   group.setGroupName(gName);
        	}
        	if(ruleGroups==null)
        	{
        		ruleGroups = new MTxRuleGroups();
        	}
        	ruleGroups.update(group);
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
        refreshInput(request, response);
        processRequest(request,response);
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
        refreshInput(request, response);
        processRequest(request,response);
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
