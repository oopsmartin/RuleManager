package org.cncert.mtxrulemanager.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cncert.mtxrulemanager.models.MTxRuleGroup;
import org.cncert.mtxrulemanager.models.MTxRuleGroups;

public class SelectServlet extends HttpServlet {

	MTxRuleGroups ruleGroups = null;
	List<MTxRuleGroup> groupList = null;
    /**
     * Constructor of the object.
     */
    public SelectServlet() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    /**
     * The doGet method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to get.
     * 
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control","no-cache");
        
        ruleGroups = new MTxRuleGroups();
        groupList = new ArrayList<MTxRuleGroup>();
        groupList = ruleGroups.getAll();
        
        String xml = "";
        int i;
        int ID;
        String groupName = new String();
        
        String targetId=request.getParameter("id").toString();
        for(i=0;i<groupList.size();i++)
        {
           ID = ((MTxRuleGroup)groupList.get(i)).getID();        	
           if (targetId.equals(new Integer(ID).toString()))
           {
        	   groupName = ((MTxRuleGroup)groupList.get(i)).getGroupName();
        	   break;
           }
        }
        xml = "<input title=groupname maxlength=16 size=16 name=groupname type=text value="+groupName+">";
        
        /**
        String xml_start="<selects>";
        String xml_end="</selects>";
        String xml="";
        if(targetId.equalsIgnoreCase("0")){
            xml="<select><value>0</value><text>Unbounded</text></select>";
        }else if(targetId.equalsIgnoreCase("1")){
            xml="<select><value>1</value><text>Mana Burn</text></select>";
            xml +="<select><value>2</value><text>Death Coil</text></select>";
            xml +="<select><value>3</value><text>Unholy Aura</text></select>";
            xml +="<select><value>4</value><text>Unholy Fire</text></select>";
        }else if(targetId.equalsIgnoreCase("2")){
            xml="<select><value>1</value><text>Corprxplode</text></select>";
            xml +="<select><value>2</value><text>Raise Dead</text></select>";
            xml +="<select><value>3</value><text>Brilliance Aura</text></select>";
            xml +="<select><value>4</value><text>Aim Aura</text></select>";
        }else{
            xml="<select><value>1</value><text>Rain of Chaos</text></select>";
            xml +="<select><value>2</value><text>Finger of Death</text></select>";
            xml +="<select><value>3</value><text>Bash</text></select>";
            xml +="<select><value>4</value><text>Summon Doom</text></select>";
        }
        **/
        
        response.getWriter().write(xml);
    }

    /**
     * The doPost method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to post.
     * 
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request,response);
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occure
     */
    public void init() throws ServletException {
        // Put your code here
    }

}