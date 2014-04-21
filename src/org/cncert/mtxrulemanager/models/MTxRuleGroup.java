/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cncert.mtxrulemanager.models;

import java.sql.Timestamp;

/**
 *
 * @author GaoSheng
 */
public class MTxRuleGroup {

	private int ID;
	
    private String GroupName;

    private Timestamp CreateTime;

    public int getID(){
    	return ID;
    }
    
    public void setID(int ID){
    	this.ID = ID;
    }
    /**
     * Get the value of CreateTime
     *
     * @return the value of CreateTime
     */
    public Timestamp getCreateTime() {
        return CreateTime;
    }

    /**
     * Set the value of CreateTime
     *
     * @param CreateTime new value of CreateTime
     */
    public void setCreateTime(Timestamp CreateTime) {
        this.CreateTime = CreateTime;
    }

    /**
     * Get the value of GroupName
     *
     * @return the value of GroupName
     */
    public String getGroupName() {
        return GroupName;
    }

    /**
     * Set the value of GroupName
     *
     * @param GroupName new value of GroupName
     */
    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }
    
}
