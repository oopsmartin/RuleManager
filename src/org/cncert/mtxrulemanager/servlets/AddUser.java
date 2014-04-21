/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cncert.mtxrulemanager.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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

import com.sun.net.httpserver.HttpContext;

/**
 *
 * @author Wangchao
 */
public class AddUser extends HttpServlet {
	
	MTxUsers users = null;
	List<MTxUser> userList = null;
	
	protected boolean validate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		PrintWriter out = response.getWriter(); 
		try {			   
        	response.setContentType("text/xml");
            response.setHeader("Cache-Control","no-cache");
            
            users = new MTxUsers();
            userList = new ArrayList<MTxUser>();
            userList = users.getAll();
            
            String xml = "";
            int i;            
            String userName = new String();
            String xml_start = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
            String xml_desc = "<web-app xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:web=\"http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\" version=\"2.5\">";
            String xml_end = "\n</web-app>";
            String inputUsername = request.getParameter("username");
            System.out.printf("inputUsername is %s\n",inputUsername);
            for(i=0;i<userList.size();i++)
            {
               userName = ((MTxUser)userList.get(i)).getUserName();
               xml += "\n\t<username>"+userName+"</username>";        	
               if (inputUsername.equals(userName))
               { 
            	   response.getWriter().write(xml_start+xml_desc+xml+xml_end);
            	   return false;
               }               
            }
                        
            if(request.getParameter("password")==null)
            	return false;
            
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
        	response.setContentType("text/xml");
            response.setHeader("Cache-Control","no-cache");
            
        	String userIP = new String();
        	MTxUser user = new MTxUser();
        	Date currentTime = new Date();            
        	users = new MTxUsers();
        	
        	 if (request.getHeader("x-forwarded-for") == null) {
        		   userIP = request.getRemoteAddr();
        		  }
        		  userIP = request.getHeader("x-forwarded-for");
        	user.setAuthorizedIP(userIP);        	
            user.setCreateTime(new java.sql.Timestamp(currentTime.getTime()));
            user.setEffectTime(new java.sql.Timestamp(currentTime.getTime()));
            user.setExpireTime(new java.sql.Timestamp(currentTime.getTime()));
            user.setLastLoginTime(new java.sql.Timestamp(currentTime.getTime()));
            user.setPassword(request.getParameter("password"));
            user.setUserName(request.getParameter("username"));
            
            users.insert(user);
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
    	if(validate(request, response))
    		processRequest(request, response);
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
