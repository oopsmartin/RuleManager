/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cncert.mtxrulemanager.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.cncert.mtxrulemanager.utils.DBUtils;

/**
 *
 * @author GaoSheng
 */
public class MTxUsers {
    
	private static DBUtils db;
	private PreparedStatement pstmt = null ;
	
	public MTxUsers(){
		MTxUsers.createTable();
	}
	
	static boolean createTable(){
		boolean flag = false;		
		String sql;
    	sql = "create table if not exists User" +
    			"(ID INTEGER NOT NULL auto_increment," +
    			"AuthorizedIP VARCHAR(40)," +
    			"CreateTime DATETIME NOT NULL," +
    			"EffectTime DATETIME,"+
    			"ExpireTime DATETIME,"+
    			"LastLoginTime DATETIME,"+
    			"Password VARCHAR(20) NOT NULL,"+
    			"UserName VARCHAR(20) NOT NULL unique,"+
    			"PRIMARY KEY(ID));";
    	
    		db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
    		flag = db.executeUpdate(sql);
    		db.finalize();
    	
        return flag;
	}

	public int insert(MTxUser o) {
		String sql;
    	sql = "insert into User(AuthorizedIP,CreateTime,EffectTime,ExpireTime,LastLoginTime,"+
    			"Password,UserName) values(?,?,?,?,?,?,?)";
    	
    	try {
    		
    		db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
			pstmt = db.getConn().prepareStatement(sql);
			pstmt.setString(1,o.getAuthorizedIP());
			pstmt.setTimestamp(2, o.getCreateTime());
			pstmt.setTimestamp(3, o.getEffectTime());
			pstmt.setTimestamp(4, o.getExpireTime());
			pstmt.setTimestamp(5, o.getLastLoginTime());
			pstmt.setString(6, o.getPassword());
			pstmt.setString(7, o.getUserName());
			pstmt.executeUpdate();
			
			//set the autoIncrement key ID
			Map<String,Object> condition = new LinkedHashMap<String, Object>();
			List<MTxUser> tmpUsers = new ArrayList<MTxUser>();
			int ID = -1;			
			condition.put("UserName", o.getUserName());			
			tmpUsers = search(condition);	
			MTxUser tmpUser =  (MTxUser)tmpUsers.get(0);
			ID = tmpUser.getID();			
			o.setID(ID);
						
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.finalize();
		}
    	
    	
    	return 0; 
    }

    public int update(MTxUser o) {
    	String sql;
        int status = -1;
        sql = "update User set AuthorizedIP=?,EffectTime=?,ExpireTime=?,"+
        		"LastLoginTime=?,Password=?,UserName=? where ID=?";        
        try { 
        	db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
			pstmt = db.getConn().prepareStatement(sql);
			pstmt.setString(1, o.getAuthorizedIP());
			pstmt.setTimestamp(2, o.getEffectTime());
			pstmt.setTimestamp(3, o.getExpireTime());
			pstmt.setTimestamp(4, o.getLastLoginTime());
			pstmt.setString(5, o.getPassword());
			pstmt.setString(6, o.getUserName());
			pstmt.setInt(7, o.getID());
			
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

    public int remove(MTxUser o) {
    	String sql;
    	int status = -1;
        sql = "delete from User where ID=?";
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
    /**
     *
     * @param conditions
     * @return
     */
    public ArrayList<MTxUser> search(Map<String,Object> condition) {
    	List<MTxUser> list = new ArrayList<MTxUser>();
    	
       	String sql = new String("select * from User where AuthorizedIP like ? "+
    			"or CreateTime like ? or EffectTime like ? or ExpireTime like ? "+
    			"or LastLoginTime like ? or Password like ? or UserName like ?");
    	
    	db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
    	try {
    		
			pstmt = db.getConn().prepareStatement(sql);
			
			if(condition.containsKey("AuthorizedIP"))
				pstmt.setString(1, "%"+(String)condition.get("AuthorizedIP")+"%");
			else
				pstmt.setObject(1, null);
			
			if (condition.containsKey("CreateTime"))				
				pstmt.setDate(2, (java.sql.Date)condition.get("CreateTime"));
			else
				pstmt.setObject(2, null);
			
			if (condition.containsKey("EffectTime"))				
				pstmt.setDate(3, (java.sql.Date)condition.get("EffectTime"));
			else
				pstmt.setObject(3, null);
			
			if (condition.containsKey("ExpireTime"))				
				pstmt.setDate(4, (java.sql.Date)condition.get("ExpireTime"));
			else
				pstmt.setObject(4, null);
			
			if (condition.containsKey("LastLoginTime"))				
				pstmt.setDate(5, (java.sql.Date)condition.get("LastLoginTime"));
			else
				pstmt.setObject(5, null);
			
			if (condition.containsKey("Password"))				
				pstmt.setString(6, "%"+(String)condition.get("Password")+"%");
			else
				pstmt.setObject(6, null);
			
			if (condition.containsKey("UserName"))				
				pstmt.setString(7, "%"+(String)condition.get("UserName")+"%");
			else
				pstmt.setObject(7, null);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				
				MTxUser user  = new MTxUser();
				user.setID(rs.getInt("ID"));
				user.setAuthorizedIP(rs.getString("AuthorizedIP"));
				user.setCreateTime(rs.getTimestamp("CreateTime"));
				user.setEffectTime(rs.getTimestamp("EffectTime"));
				user.setExpireTime(rs.getTimestamp("ExpireTime"));
				user.setLastLoginTime(rs.getTimestamp("LastLoginTime"));
				user.setPassword(rs.getString("Password"));
				user.setUserName(rs.getString("UserName"));
				
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	        
    	return (ArrayList<MTxUser>)list;
    }

    /**
     * get all data from user
     * @return
     */
    public ArrayList<MTxUser> getAll()
    {
List<MTxUser> list = new ArrayList<MTxUser>();
    	
       	String sql = new String("select * from user");
    	
    	db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
    	try {
    		pstmt = db.getConn().prepareStatement(sql);		
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				
				MTxUser user  = new MTxUser();
				user.setID(rs.getInt("ID"));
				user.setAuthorizedIP(rs.getString("AuthorizedIP"));
				user.setCreateTime(rs.getTimestamp("CreateTime"));
				user.setEffectTime(rs.getTimestamp("EffectTime"));
				user.setExpireTime(rs.getTimestamp("ExpireTime"));
				user.setLastLoginTime(rs.getTimestamp("LastLoginTime"));
				user.setPassword(rs.getString("Password"));
				user.setUserName(rs.getString("UserName"));
				
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	        
    	return (ArrayList<MTxUser>)list;
    }

    public static void main(String[] args){
    	Date currentTime = new Date();
    	MTxUsers users = new MTxUsers();
    	MTxUser user = new MTxUser(); 
    	Map<String,Object> condition = new LinkedHashMap<String,Object>();
    	List<MTxUser> list = new ArrayList<MTxUser>();
    	/**
    	user.setID(2);
    	user.setAuthorizedIP("1.1.1.1");
    	user.setCreateTime(new java.sql.Timestamp(currentTime.getTime()));
    	user.setEffectTime(new java.sql.Timestamp(currentTime.getTime()));
    	user.setExpireTime(new java.sql.Timestamp(currentTime.getTime()));
    	user.setLastLoginTime(new java.sql.Timestamp(currentTime.getTime()));
    	user.setPassword("hehe");
    	user.setUserName("wcxxx");
    	
    	if(MTxUsers.createTable())
    		users.insert(user);
    	**/
    	condition.put("UserName", "ds");
    	condition.put("Password", "ga");
    	list = users.search(condition);
    	for(Iterator<MTxUser> iter = list.listIterator();iter.hasNext();){
    		user = (MTxUser)iter.next();
    		System.out.printf("rule's UserName is %s",user.getUserName());
    		System.out.println();
    	}
    	
    	System.out.printf("user ID is %d",user.getID());
    }
}
