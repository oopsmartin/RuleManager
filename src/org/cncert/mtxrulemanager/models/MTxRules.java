/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cncert.mtxrulemanager.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.cncert.mtxrulemanager.utils.DBUtils;

/**
 *
 * @author GaoSheng
 */
public class MTxRules {

static DBUtils db;
private PreparedStatement pstmt = null ;

	public MTxRules(){
		MTxRules.createTable();
	}
	
	static boolean createTable(){
		String sql;
		boolean flag = false;
		
		db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
				
    	sql = "create table if not exists Rule" +
    			"(ID INTEGER NOT NULL auto_increment," +
    			"CreateBy VARCHAR(50) NOT NULL," +
    			"DeployPlaces VARCHAR(500) NOT NULL," +
    			"EffectedTime datetime,"+
    			"EventAction VARCHAR(50) NOT NULL,"+
    			"EventType VARCHAR(20) NOT NULL,"+
    			"ExpiredTime datetime,"+
    			"Groups VARCHAR(500),"+
    			"IsEnabled bool,"+
    			"MatchPattern VARCHAR(30) NOT NULL,"+
    			"Protocol VARCHAR(50) NOT NULL,"+
    			"RegTime datetime NOT NULL,"+
    			"Remark VARCHAR(200),"+
    			"Remark1 VARCHAR(200),"+
    			"Remark2 VARCHAR(200),"+
    			"ReverseMatch bool NOT NULL,"+
    			"RuleName VARCHAR(50) NOT NULL,"+
    			"RuleNumber VARCHAR(9) NOT NULL,"+
    			"RuleType VARCHAR(50) NOT NULL unique,"+
    			"PRIMARY KEY(ID));";
    	flag = db.executeUpdate(sql);
    	db.finalize();
    	
        return flag;
	}
	
    public int insert(MTxRule o) {
    	String sql;
    	int status = -1;
    	
    	sql = "insert into Rule(" +
    			"CreateBy,DeployPlaces,"+
    			"EffectedTime,EventAction,"+
    			"EventType,ExpiredTime,"+
    			"Groups,IsEnabled,"+
    			"MatchPattern,Protocol,"+
    			"RegTime,Remark,"+
    			"Remark1,Remark2,"+
    			"ReverseMatch,RuleName,"+
    			"RuleNumber,RuleType) values"+
    			"(?,?,?,?,?,?,?,?,?,"+
    			"?,?,?,?,?,?,?,?,?)";
    	
    	try {
    		
    		db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
			
    		pstmt = db.getConn().prepareStatement(sql);
			
			pstmt.setString(1, o.getCreateBy());
						
			StringBuffer deployPlaces = new StringBuffer();
			for(int i=0;i<o.getDeployPlaces().length;i++)
			{
				deployPlaces.append(o.getDeployPlaces(i));
				deployPlaces.append("\\t");
			}
			pstmt.setString(2, deployPlaces.toString());
			pstmt.setTimestamp(3, o.getEffectedTime());
			pstmt.setString(4, o.getEventAction());
			pstmt.setString(5, o.getEventType());
			pstmt.setTimestamp(6, o.getExpiredTime());
						
			StringBuffer groups = new StringBuffer();
			for(int i=0;i<o.getGroups().length;i++)
			{
				groups.append(o.getGroups(i));
				groups.append("\\t");
			}
			pstmt.setString(7, groups.toString());
			pstmt.setBoolean(8, o.isIsEnabled());
			pstmt.setString(9, o.getMatchPattern());
			pstmt.setString(10, o.getProtocol());
			pstmt.setTimestamp(11, o.getRegTime());
			pstmt.setString(12, o.getRemark());
			pstmt.setString(13, o.getRemark1());
			pstmt.setString(14, o.getRemark2());
			pstmt.setBoolean(15, o.isReverseMatch());
			pstmt.setString(16, o.getRuleName());
			pstmt.setString(17, o.getRuleNumber());
			pstmt.setString(18, o.getRuleType());
			
			status = pstmt.executeUpdate();
			
			//set the autoIncrement key ID
			Map<String,Object> condition = new LinkedHashMap<String,Object>();
			List<MTxRule> tmpRules = new ArrayList<MTxRule>();
			int ID = -1;
			condition.put("RuleType", o.getRuleType());
			tmpRules = search(condition);
			
			MTxRule tmpRule =  (MTxRule)tmpRules.get(0);
			ID = tmpRule.getID();			
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

    public int update(MTxRule o) {
    	String sql;
    	int status = -1;
    	sql = "update Rule set " +
    			"CreateBy=?,DeployPlaces=?,"+
    			"EffectedTime=?,EventAction=?,"+
    			"EventType=?,ExpiredTime=?,"+
    			"Groups=?,IsEnabled=?,"+
    			"MatchPattern=?,Protocol=?,"+
    			"RegTime=?,Remark=?,"+
    			"Remark1=?,Remark2=?,"+
    			"ReverseMatch=?,RuleName=?,"+
    			"RuleNumber=?,RuleType=? where ID=? ";
    	
    	try {
    		
    		db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
			pstmt = db.getConn().prepareStatement(sql);
			
			pstmt.setString(1, o.getCreateBy());
			
			StringBuffer deployPlaces = new StringBuffer();
			for(int i=0;i<o.getDeployPlaces().length;i++)
			{
				deployPlaces.append(o.getDeployPlaces(i));
				deployPlaces.append("\\t");
			}
			pstmt.setString(2, deployPlaces.toString());
			pstmt.setTimestamp(3, o.getEffectedTime());
			pstmt.setString(4, o.getEventAction());
			pstmt.setString(5, o.getEventType());
			pstmt.setTimestamp(6, o.getExpiredTime());
			
			StringBuffer groups = new StringBuffer();
			for(int i=0;i<o.getGroups().length;i++)
			{
				groups.append(o.getGroups(i));
				groups.append("\\t");
			}
			pstmt.setString(7, groups.toString());
			pstmt.setBoolean(8, o.isIsEnabled());
			pstmt.setString(9, o.getMatchPattern());
			pstmt.setString(10, o.getProtocol());
			pstmt.setTimestamp(11, o.getRegTime());
			pstmt.setString(12, o.getRemark());
			pstmt.setString(13, o.getRemark1());
			pstmt.setString(14, o.getRemark2());
			pstmt.setBoolean(15, o.isReverseMatch());
			pstmt.setString(16, o.getRuleName());
			pstmt.setString(17, o.getRuleNumber());
			pstmt.setString(18, o.getRuleType());
			pstmt.setInt(19, o.getID());
			
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

    public int remove(MTxRule o) {
    	String sql;
    	int status = -1;
        sql = "delete from Rule where ID=?";
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
    public ArrayList<MTxRule> search(Map<String,Object> condition) {
    	List<MTxRule> list = new ArrayList<MTxRule>();        
    	
    	StringBuffer sql = new StringBuffer("select * from Rule where CreateBy like ? or EffectedTime like ? ");
    	sql.append("or EventAction like ? or EventType like ? or ExpiredTime like ? or IsEnabled like ? or MatchPattern like ? ");
    	sql.append("or Protocol like ? or RegTime like ? or Remark like ? or Remark1 like ? or Remark2 like ? ");
    	sql.append("or ReverseMatch like ? or RuleName like ? or RuleNumber like ? or RuleType like ? ");
    	sql.append("or DeployPlaces like ? or Groups like ?");
    	db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
    	try {
    		
			pstmt = db.getConn().prepareStatement(sql.toString());
			
			if(condition.containsKey("CreateBy"))
				pstmt.setString(1, "%"+(String)condition.get("CreateBy")+"%");
			else
				pstmt.setObject(1, null);			
			if (condition.containsKey("EffectedTime"))				
				pstmt.setTimestamp(2, (Timestamp)condition.get("EffectedTime"));
			else
				pstmt.setObject(2, null);
			if(condition.containsKey("EventAction"))
				pstmt.setString(3, "%"+(String)condition.get("EventAction")+"%");
			else
				pstmt.setObject(3, null);
			if(condition.containsKey("EventType"))
				pstmt.setString(4, "%"+(String)condition.get("EventType")+"%");
			else
				pstmt.setObject(4, null);			
			if(condition.containsKey("ExpiredTime"))
				pstmt.setTimestamp(5, (Timestamp)condition.get("ExpiredTime"));
			else
				pstmt.setObject(5, null);
			if(condition.containsKey("IsEnabled"))
				pstmt.setBoolean(6, (boolean)condition.get("IsEnabled"));
			else
				pstmt.setObject(6, null);
			if(condition.containsKey("MatchPattern"))
				pstmt.setString(7, "%"+(String)condition.get("MatchPattern")+"%");
			else
				pstmt.setObject(7, null);
			if(condition.containsKey("Protocol"))
				pstmt.setString(8, "%"+(String)condition.get("Protocol")+"%");
			else
				pstmt.setObject(8, null);
			if(condition.containsKey("RegTime"))
				pstmt.setTimestamp(9, (Timestamp)condition.get("RegTime"));
			else
				pstmt.setObject(9, null);
			if(condition.containsKey("Remark"))
				pstmt.setString(10, "%"+(String)condition.get("Remark")+"%");
			else
				pstmt.setObject(10, null);
			if(condition.containsKey("Remark1"))
				pstmt.setString(11, "%"+(String)condition.get("Remark1")+"%");
			else
				pstmt.setObject(11, null);
			if(condition.containsKey("Remark2"))
				pstmt.setString(12, "%"+(String)condition.get("Remark2")+"%");
			else
				pstmt.setObject(12, null);
			if(condition.containsKey("ReverseMatch"))
				pstmt.setBoolean(13, (boolean)condition.get("ReverseMatch"));
			else
				pstmt.setObject(13, null);
			if(condition.containsKey("RuleName"))
				pstmt.setString(14, "%"+(String)condition.get("RuleName")+"%");
			else
				pstmt.setObject(14, null);
			if(condition.containsKey("RuleNumber"))
				pstmt.setString(15, "%"+(String)condition.get("RuleNumber")+"%");
			else
				pstmt.setObject(15, null);
			if(condition.containsKey("RuleType")){
				pstmt.setString(16, "%"+(String)condition.get("RuleType")+"%");
				System.out.printf("RuleType is %s", condition.get("RuleType"));
				
			}
			else
				pstmt.setObject(16, null);
			
			if(condition.containsKey("DeployPlaces"))
				pstmt.setString(17, "%"+(String)condition.get("DeployPlaces")+"%");
			else
				pstmt.setObject(17, null);
			if(condition.containsKey("Groups"))
				pstmt.setString(18, "%"+(String)condition.get("Groups")+"%");
			else
				pstmt.setObject(18, null);
			//condition.containsKey("ddd")?pstmt.setString(1, "%"+(String)condition.get("GroupName")+"%"):pstmt.setObject(1, null);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.printf("rs ID is %d\n",rs.getInt("ID"));
				
				MTxRule rule = new MTxRule();
				rule.setID(rs.getInt("ID"));
				rule.setCreateBy(rs.getString("CreateBy"));
				
				String deployPlaces = new String();			
				
				deployPlaces = rs.getString("deployPlaces");
				String regex = "\t";
				String[] deploy = deployPlaces.split(regex);
				for(String item:deploy)
				{
					System.out.printf("deploy places are: %s\n",item);
				}				
				rule.setDeployPlaces(deploy);
				
				rule.setEffectedTime(rs.getTimestamp("EffectedTime"));
				rule.setEventAction(rs.getString("EventAction"));
				rule.setExpiredTime(rs.getTimestamp("ExpiredTime"));
				
				String groups = new String();					
				groups = rs.getString("Groups");
				String[] group = groups.split(regex);
				for(String grp:group)
				{
					System.out.printf("groups are : %s\n", grp);
				}				
				rule.setGroups(group);
				
				rule.setIsEnabled(rs.getBoolean("IsEnabled"));
				rule.setMatchPattern(rs.getString("MatchPattern"));
				rule.setProtocol(rs.getString("Protocol"));
				rule.setRegTime(rs.getTimestamp("RegTime"));
				rule.setRemark(rs.getString("Remark"));
				rule.setRemark1(rs.getString("Remark1"));
				rule.setRemark2(rs.getString("Remark2"));
				rule.setReverseMatch(rs.getBoolean("ReverseMatch"));
				rule.setRuleName(rs.getString("RuleName"));
				rule.setRuleNumber(rs.getString("RuleNumber"));
				rule.setRuleType(rs.getString("RuleType"));
								
				list.add(rule);
			}
				
			}catch(SQLException e){
				e.printStackTrace();
			}
        
    	return (ArrayList<MTxRule>)list;
       
    }
 
    /**
     * get all data from rule table
     * @return
     */
    public ArrayList<MTxRule> getAll() {
    	List<MTxRule> list = new ArrayList<MTxRule>();        
    	String sql = new String("select * from Rule order by ID");
    	
    	db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
    	try {
    		
			pstmt = db.getConn().prepareStatement(sql.toString());
								
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				
				MTxRule rule  = new MTxRule();
				rule.setID(rs.getInt("ID"));
				rule.setCreateBy(rs.getString("CreateBy"));
				rule.setEffectedTime(rs.getTimestamp("EffectedTime"));
				rule.setEventAction(rs.getString("EventAction"));
				rule.setEventType(rs.getString("EventType"));
				rule.setExpiredTime(rs.getTimestamp("ExpiredTime"));
				rule.setIsEnabled(rs.getBoolean("IsEnabled"));
				rule.setMatchPattern(rs.getString("MatchPattern"));
				rule.setProtocol(rs.getString("Protocol"));
				rule.setRegTime(rs.getTimestamp("RegTime"));
				rule.setRemark(rs.getString("Remark"));
				rule.setRemark1(rs.getString("Remark1"));
				rule.setRemark2(rs.getString("Remark2"));
				rule.setReverseMatch(rs.getBoolean("ReverseMatch"));
				rule.setRuleName(rs.getString("RuleName"));
				rule.setRuleNumber(rs.getString("RuleNumber"));
				rule.setRuleType(rs.getString("RuleType"));
				
				String[] deployPlaces = new String[4];
				String regs = "\t";				
				deployPlaces = rs.getString("DeployPlaces").split(regs);				
				rule.setDeployPlaces(deployPlaces);
				
				String[] groups = new String[4];				
				groups = rs.getString("Groups").split(regs);				
				rule.setGroups(groups);
				
				list.add(rule);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        
    	return (ArrayList<MTxRule>)list;
    }
    
    public static void main(String[] args){
    	Date currentTime = new Date();
    	MTxRules rules = new MTxRules();
    	MTxRule rule = new MTxRule();
    	//List<MTxRule> list = new ArrayList<MTxRule>();
    	//Map<String,Object> condition = new LinkedHashMap<String,Object>();
    	/**
    	rule.setCreateBy("wc");
    	rule.setEffectedTime(new java.sql.Date(currentTime.getTime()));
    	rule.setEventAction("...");
    	rule.setEventType("...");
    	rule.setExpiredTime(new java.sql.Date(currentTime.getTime()));
    	rule.setIsEnabled(true);
    	rule.setMatchPattern("...");
    	rule.setProtocol("...");
    	rule.setRegTime(new java.sql.Date(currentTime.getTime()));
    	rule.setRemark("...");
    	rule.setRemark1("...");
    	rule.setRemark2("...");
    	rule.setReverseMatch(true);
    	rule.setRuleName("...");
    	rule.setRuleNumber("...");
    	rule.setRuleType("lh");
    	
    	String[] places = new String[3];
    	places[0] = "cn";    	
    	places[1] = "uk";
    	places[2] = "usa";
    	rule.setDeployPlaces(places);
    	rule.setGroups(places);
    	
    	if(MTxRules.createTable())
    		rules.insert(rule);
    	
    	rule.setID(3);
    	rule.setMatchPattern("wccc");
    	rules.remove(rule);   	
    	
    	condition.put("DeployPlaces", "cn");
    	list = rules.search(condition);
    	for(Iterator<MTxRule> iter = list.listIterator();iter.hasNext();){
    		rule = (MTxRule)iter.next();
    		System.out.printf("rule's RuleType is %s",rule.getRuleType());
    		System.out.println();
    	}**/
    	
    	System.out.printf("group ID is %d",rule.getID());
    }
    
    
}
