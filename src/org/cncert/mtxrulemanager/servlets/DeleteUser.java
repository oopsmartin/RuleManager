/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cncert.mtxrulemanager.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.cncert.mtxrulemanager.models.MTxUser;
import org.cncert.mtxrulemanager.models.MTxUsers;

public class DeleteUser extends HttpServlet {

	MTxUsers users = null;
	List<MTxUser> userList = null;
	
	protected void refreshInput(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        PrintWriter out = response.getWriter();
        try {
        	response.setContentType("text/xml");
            response.setHeader("Cache-Control","no-cache");
            //<?xml version="1.0" encoding="UTF-8"?>
            users = new MTxUsers();
            userList = new ArrayList<MTxUser>();
            userList = users.getAll();
            
            String xml = "";            
            int ID;
            String username = "";
            String authorizedIP = "";
            String createTime = "";
            String effectTime = "";
            String expireTime = "";
            String lastLoginTime = "";
            
            String xml_start = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
            String xml_desc = "<web-app xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:web=\"http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\" version=\"2.5\">";
            String xml_end = "\n</web-app>";
            
            String targetId = (String)request.getParameter("ID").toString();
            
            for(Iterator<MTxUser> iter=userList.iterator();iter.hasNext();)
            {
            	MTxUser tmpUser = iter.next();
               ID = tmpUser.getID();        	
               if (targetId.equals(new Integer(ID).toString()))
               {
            	   authorizedIP = tmpUser.getAuthorizedIP();
            	   createTime = String.valueOf(tmpUser.getCreateTime());
            	   effectTime = String.valueOf(tmpUser.getEffectTime());
            	   expireTime = String.valueOf(tmpUser.getExpireTime());
            	   lastLoginTime = String.valueOf(tmpUser.getLastLoginTime());
            	   username = tmpUser.getUserName();
            	   break;
               }
            }
            
            xml += "\n\t<authorizedIP>"+authorizedIP+"</authorizedIP>";
            xml += "\n\t<createTime>"+createTime+"</createTime>";
            xml += "\n\t<effectTime>"+effectTime+"</effectTime>";
            xml += "\n\t<expireTime>"+expireTime+"</expireTime>";
            xml += "\n\t<lastLoginTime>"+lastLoginTime+"</lastLoginTime>";
            xml += "\n\t<username>"+username+"</username>";
            
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
        	
            MTxUser user = new MTxUser();
            String userIP = null;
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            int ID = Integer.valueOf(request.getParameter("ID"));
            
            user.setID(ID);
            if (request.getHeader("x-forwarded-for") == null) {
     		   userIP = request.getRemoteAddr();
     		  }
     		  userIP = request.getHeader("x-forwarded-for");
     		  user.setAuthorizedIP(userIP);            
            user.setUserName(username);
            user.setPassword(password);
            
            if(users==null)
            	users = new MTxUsers();
            users.remove(user);
            
            
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
    
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
