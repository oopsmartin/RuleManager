/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cncert.mtxrulemanager.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.cncert.mtxrulemanager.utils.*;

/**
 *
 * @author WangChao
 * 
 */
public class MTxRuleGroups {
	private static DBUtils db;
	private PreparedStatement pstmt = null ;
	
	public MTxRuleGroups(){
		MTxRuleGroups.createTable();
	}
	
	static boolean createTable(){
		boolean flag = false;		
		String sql;
    	sql = "create table if not exists RuleGroup" +
    			"(ID INTEGER NOT NULL auto_increment," +
    			"GroupName VARCHAR(50) NOT NULL unique," +
    			"CreateTime DATETIME NOT NULL," +
    			"PRIMARY KEY(ID));";
    	
    		db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
    		flag = db.executeUpdate(sql);
    		db.finalize();
    	
        return flag;
	}
	
    public int insert(MTxRuleGroup o) {
    	String sql;
    	int status = -1;
    	sql = "insert into RuleGroup(GroupName,CreateTime) values(?,?)";
    	
    	try {
    		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
			pstmt = db.getConn().prepareStatement(sql);
			pstmt.setString(1, o.getGroupName());
			pstmt.setTimestamp(2, o.getCreateTime());
			
			status = pstmt.executeUpdate();
			/**db.executeUpdate(sql);
			 * 预编译使用pstmt
			 * db是执行时编译
			 */
			//set the autoIncrement key ID
			Map<String,Object> condition = new LinkedHashMap<String, Object>();
			List<MTxRuleGroup> tmpRuleGroups = new ArrayList<MTxRuleGroup>();
			int ID = -1;			
			condition.put("GroupName", o.getGroupName());			
			tmpRuleGroups = search(condition);			
			MTxRuleGroup tmpRuleGroup =  (MTxRuleGroup)tmpRuleGroups.get(0);
			ID = tmpRuleGroup.getID();			
			o.setID(ID);
						
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.finalize();
		}    	
    	
    	return status;
    }

    public int update(MTxRuleGroup o) {
        String sql;
        int status = -1;
        sql = "update RuleGroup set GroupName=? where ID=?";        
        try { 
        	db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
			pstmt = db.getConn().prepareStatement(sql);
			pstmt.setString(1, o.getGroupName());
			pstmt.setInt(2, o.getID());
			
			status = pstmt.executeUpdate();
			
			pstmt.close();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.finalize();
		}
    	return status;
    }

    public int remove(MTxRuleGroup o) {
        String sql;
        int status = -1;
        sql = "delete from RuleGroup where ID=?";
        try {
        	
        	db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
			pstmt = db.getConn().prepareStatement(sql);
			pstmt.setInt(1, o.getID());
			
			status = pstmt.executeUpdate();
			
			pstmt.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.finalize();
		}
        
    	return status;
    }
    
    public int getID(String groupName){
    	String sql = "select * from RuleGroup where GroupName=?;";
    	ResultSet rs = null;
    	int id = 0;
    	
    	try {
    		
    		db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
			pstmt = db.getConn().prepareStatement(sql);
			pstmt.setString(1, groupName);
			rs = db.executeQuery(sql);    	
			id = rs.getInt("ID");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.finalize();
		}
		
    	return id;
    }
    
  /**        
   * @param conditions
   * @return
   **/
    public ArrayList<MTxRuleGroup> search(Map<String,Object> condition) {
    	
    	List<MTxRuleGroup> list = new ArrayList<MTxRuleGroup>();        
    	String sql = new String("select * from RuleGroup where GroupName like ? or CreateTime like ?");
    	
    	db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
    	try {
    		
			pstmt = db.getConn().prepareStatement(sql.toString());
			if(condition.containsKey("GroupName"))
				pstmt.setString(1, "%"+(String)condition.get("GroupName")+"%");
			else
				pstmt.setObject(1, null);
			
			if (condition.containsKey("CreateTime"))				
				pstmt.setDate(2, (java.sql.Date)condition.get("CreateTime"));
			else
				pstmt.setObject(2, null);		
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				
				MTxRuleGroup group  = new MTxRuleGroup();
				group.setID(rs.getInt("ID"));
				group.setGroupName(rs.getString("GroupName"));
				group.setCreateTime(rs.getTimestamp("CreateTime"));
				list.add(group);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        
    	return (ArrayList<MTxRuleGroup>)list;
    }
    
public ArrayList<MTxRuleGroup> getAll() {
    	
    	List<MTxRuleGroup> list = new ArrayList<MTxRuleGroup>();        
    	String sql = new String("select * from RuleGroup order by ID");
    	
    	db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
    	try {
    		
			pstmt = db.getConn().prepareStatement(sql.toString());
								
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				
				MTxRuleGroup group  = new MTxRuleGroup();
				group.setID(rs.getInt("ID"));
				group.setGroupName(rs.getString("GroupName"));
				group.setCreateTime(rs.getTimestamp("CreateTime"));
				list.add(group);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        
    	return (ArrayList<MTxRuleGroup>)list;
    }
    
    public static void main(String[] args){
    	Date currentTime = new Date();
    	MTxRuleGroups groups = new MTxRuleGroups();
    	MTxRuleGroup group = new MTxRuleGroup();    	
    	group.setCreateTime(new java.sql.Timestamp(currentTime.getTime()));
    	group.setGroupName("hegh");
    	if(MTxRuleGroups.createTable())
    		groups.insert(group);
    	//group.setGroupName("modified");
    	//groups.update(group);
    	groups.remove(group);
    	System.out.printf("group ID is %d",group.getID()); 
    }
}
