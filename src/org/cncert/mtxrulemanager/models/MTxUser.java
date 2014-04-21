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
public class MTxUser {
   
	/**
	 * 主键，自增
	 */
	private int ID;
	/**
	 * unique
	 */
    private String UserName;
    
    private String Password;
    
    private Timestamp CreateTime;

    private Timestamp EffectTime;

    private Timestamp ExpireTime;
    
    private Timestamp LastLoginTime;
    
    private String AuthorizedIP;

    /**
     * Get the value of AuthorizedIP
     *
     * @return the value of AuthorizedIP
     */
    public String getAuthorizedIP() {
        return AuthorizedIP;
    }

    /**
     * Set the value of AuthorizedIP
     *
     * @param AuthorizedIP new value of AuthorizedIP
     */
    public void setAuthorizedIP(String AuthorizedIP) {
        this.AuthorizedIP = AuthorizedIP;
    }


    /**
     * Get the value of LastLoginTime
     *
     * @return the value of LastLoginTime
     */
    public Timestamp getLastLoginTime() {
        return LastLoginTime;
    }

    /**
     * Set the value of LastLoginTime
     *
     * @param LastLoginTime new value of LastLoginTime
     */
    public void setLastLoginTime(Timestamp LastLoginTime) {
        this.LastLoginTime = LastLoginTime;
    }


    /**
     * Get the value of ExpireTime
     *
     * @return the value of ExpireTime
     */
    public Timestamp getExpireTime() {
        return ExpireTime;
    }

    /**
     * Set the value of ExpireTime
     *
     * @param ExpireTime new value of ExpireTime
     */
    public void setExpireTime(Timestamp ExpireTime) {
        this.ExpireTime = ExpireTime;
    }

    /**
     * Get the value of EffectTime
     *
     * @return the value of EffectTime
     */
    public Timestamp getEffectTime() {
        return EffectTime;
    }

    /**
     * Set the value of EffectTime
     *
     * @param EffectTime new value of EffectTime
     */
    public void setEffectTime(Timestamp EffectTime) {
        this.EffectTime = EffectTime;
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
     * Get the value of UserName
     *
     * @return the value of UserName
     */
    public String getUserName() {
        return UserName;
    }

    /**
     * Set the value of UserName
     *
     * @param UserName new value of UserName
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    

    /**
     * Get the value of Password
     *
     * @return the value of Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * Set the value of Password
     *
     * @param Password new value of Password
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}
