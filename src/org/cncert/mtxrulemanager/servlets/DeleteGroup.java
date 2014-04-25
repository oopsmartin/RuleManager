package org.cncert.mtxrulemanager.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cncert.mtxrulemanager.models.MTxRuleGroup;
import org.cncert.mtxrulemanager.models.MTxRuleGroups;


public class DeleteGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MTxRuleGroups groups = null;
	List<MTxRuleGroup> groupList = null;
    
    public DeleteGroup() {
        super();
        
    }
    
    protected void refreshInput(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	response.setContentType("text/xml");
            response.setHeader("Cache-Control","no-cache");
            //<?xml version="1.0" encoding="UTF-8"?>
            groups = new MTxRuleGroups();
            groupList = new ArrayList<MTxRuleGroup>();
            groupList = groups.getAll();
            
            String xml = "";           
            String xml_start = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
            String xml_desc = "<web-app xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:web=\"http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\" version=\"2.5\">";
            String xml_end = "\n</web-app>";
            String targetId = (String)request.getParameter("ID").toString();
            String groupName = new String();
            Timestamp createTime = new Timestamp(new java.util.Date().getTime());
            //System.out.printf("targetID is %s\n",targetId);
            for(Iterator<MTxRuleGroup> iter=groupList.iterator();iter.hasNext();)
            {
               MTxRuleGroup tmpGroup = iter.next();        	
               if (targetId.equals(new Integer(tmpGroup.getID()).toString()))
               {
            	   groupName = tmpGroup.getGroupName();
            	   createTime = tmpGroup.getCreateTime();
            	   System.out.printf("groupName is %s\n",groupName);
            	   System.out.printf("createTime is %s\n",createTime);
            	   break;
               }
            }
            
            xml += "\n\t<groupname>"+groupName+"</groupname>";
            xml += "\n\t<createTime>"+createTime+"</createTime>";
            
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
        	
        	MTxRuleGroup group = new MTxRuleGroup();
        	group.setID(Integer.valueOf(request.getParameter("ID")));
        	group.setCreateTime(Timestamp.valueOf(request.getParameter("createTime")));
        	group.setGroupName(request.getParameter("groupname"));
        	System.out.printf("ID is %d\n", group.getID());
        	groups.remove(group);
        	
        } finally {
            out.close();
        }
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		refreshInput(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		refreshInput(request,response);
		processRequest(request,response);
	}

}
