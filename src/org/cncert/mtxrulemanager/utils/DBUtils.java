/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cncert.mtxrulemanager.utils;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.ResultSetMetaData;  
import java.sql.SQLException;  
import java.sql.Statement;  
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;  
import java.util.Date;
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  


  
  
public class DBUtils {  
    private Connection conn;  
    
    
    public DBUtils(String sWhere,String sAccount,String sPWD)  
    {  
        connect(sWhere,sAccount,sPWD);    
    }
    
    public void finalize()
    {  
        shutdown();   
    } 
    
    private void connect(String sWhere,String sAccount,String sPWD)  
    {  
        try  
        {  
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
          
        conn = DriverManager.getConnection(sWhere,sAccount,sPWD);
        
        }catch(Exception ex)  
        {
            ex.printStackTrace();  
        }  
    }  
      
    public boolean executeUpdate(String strSQL)  
    {  
        boolean bR=true;  
          
        try{  
            Statement stmt = conn.createStatement();  
            stmt.executeUpdate(strSQL);  
        }catch(SQLException ex)  
        {  
            ex.printStackTrace();  
            bR=false;  
        }  
          
        return bR;  
          
    }  
      
    public ResultSet executeQuery(String strSQL)  
    {  
        List<Map<String,String>> list=new ArrayList<Map<String,String>>();  
  
        ResultSet rs = null;  
          
        try{  
            Statement stmt = conn.createStatement();  
            rs = stmt.executeQuery(strSQL);  
        }catch(SQLException ex)  
        {  
            ex.printStackTrace();  
        }  
        return rs;      
    }
    
    public static Date StringToDate(String dateStr,String formatStr){
		DateFormat sdf=new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
      
    public void testResult(List<Map<String,String>> list)  
    {  
        for(Iterator<Map<String,String>> it=list.iterator();it.hasNext();)  
        {  
            System.out.println(it.next());
        }         
    }  
      
    private void shutdown()  
    {  
        try  
        {  
            conn.close();         
        }catch(SQLException ex)  
        {  
            ex.printStackTrace();  
        }  
    }  
    
    public Connection getConn()
    {
    	return conn;
    }
       
    
    public static void test(String args[])  
    {  
        try {  
            //step1:test open&close  
            DBUtils db=new DBUtils("jdbc:mysql://127.0.0.1:3306/callgraph", "root", "8086W028C");  
              
            //step2:test query with return  
            ResultSet list=db.executeQuery("select * from edgenetsky_a_winmain16_fg");             
            
            ResultSetMetaData rsmd = list.getMetaData();   
            int columnCount = rsmd.getColumnCount();   
            // 输出列名   
            for (int i=1; i<=columnCount; i++){   
                System.out.print(rsmd.getColumnName(i));   
                System.out.print("(" + rsmd.getColumnTypeName(i) + ")");   
                System.out.print(" | ");   
            }   
            System.out.println();   
            // 输出数据   
            while (list.next()){   
                for (int i=1; i<=columnCount; i++){   
                    System.out.print(list.getString(i) + " | ");   
                }   
                System.out.println();   
            }   
            list.close();   
               
              
        
            
        }catch(Exception ex)  
        {  
            ex.printStackTrace();             
        }  
    }
    /**
    public static void main(String args[])
    {
    	test(args);
    }
    **/
}
