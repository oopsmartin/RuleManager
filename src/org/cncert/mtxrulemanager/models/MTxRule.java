/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cncert.mtxrulemanager.models;

import java.sql.Timestamp;
import java.util.logging.Logger;

/**
 *
 * @author GaoSheng
 */
public class MTxRule {

    public MTxRule() {
    }
    private static final Logger LOG = Logger.getLogger(MTxRule.class.getName());

    /**
     * 主键，自增
     */
    private int ID;
    /**
     * 事件编号(8位数字，形如00001111)
     */
    private String RuleNumber;

    /**
     * 事件名称（形如 木马-流量劫持-AdWare.Win32.Sahat.ee.0661E ）
     */
    private String RuleName;

    /**
     * 登记日期
     */
    private Timestamp RegTime;

    /**
     * 匹配表达式
     */
    private String MatchPattern;

    /**
     * 匹配动作（记录报文，上报事件及返回值）
     */
    private String EventAction;

    /**
     * 规则所属分组
     */
    private String[] Groups;

    /**
     * 规则类型（形如 专项任务、远控木马、僵尸网络等等）
     */
    private String RuleType;

    /**
     * 反向标志
     */
    private boolean ReverseMatch;

    /**
     * 部署位置（国际口、省级口、关口、MTx合作厂商等）
     */
    private String[] DeployPlaces;

    /**
     * 协议类型（TCP,UDP,HTTP等）
     */
    private String Protocol;

    /**
     * 是否启用
     */
    private boolean IsEnabled;

    /**
     * 事件类型（报文事件、特征事件）
     */
    private String EventType;

    /**
     * 生效日期
     */
    private Timestamp EffectedTime;

    /**
     * 失效日期
     */
    private Timestamp ExpiredTime;

    /**
     * 备注
     */
    private String Remark;

    /**
     * 备注1
     */
    private String Remark1;

    /**
     * 备注2
     */
    private String Remark2;
    
    /**
     * 创建人
     */
    private String CreateBy;

    /**
     * Get the value of CreateBy
     *
     * @return the value of CreateBy
     */
    public String getCreateBy() {
        return CreateBy;
    }

    /**
     * Set the value of CreateBy
     *
     * @param CreateBy new value of CreateBy
     */
    public void setCreateBy(String CreateBy) {
        this.CreateBy = CreateBy;
    }


    /**
     * Get the value of Remark2
     *
     * @return the value of Remark2
     */
    public String getRemark2() {
        return Remark2;
    }

    /**
     * Set the value of Remark2
     *
     * @param Remark2 new value of Remark2
     */
    public void setRemark2(String Remark2) {
        this.Remark2 = Remark2;
    }

    /**
     * Get the value of Remark1
     *
     * @return the value of Remark1
     */
    public String getRemark1() {
        return Remark1;
    }

    /**
     * Set the value of Remark1
     *
     * @param Remark1 new value of Remark1
     */
    public void setRemark1(String Remark1) {
        this.Remark1 = Remark1;
    }

    /**
     * Get the value of Remark
     *
     * @return the value of Remark
     */
    public String getRemark() {
        return Remark;
    }

    /**
     * Set the value of Remark
     *
     * @param Remark new value of Remark
     */
    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    /**
     * Get the value of ExpiredTime
     *
     * @return the value of ExpiredTime
     */
    public Timestamp getExpiredTime() {
        return ExpiredTime;
    }

    /**
     * Set the value of ExpiredTime
     *
     * @param ExpiredTime new value of ExpiredTime
     */
    public void setExpiredTime(Timestamp ExpiredTime) {
        this.ExpiredTime = ExpiredTime;
    }

    /**
     * Get the value of EffectedTime
     *
     * @return the value of EffectedTime
     */
    public Timestamp getEffectedTime() {
        return EffectedTime;
    }

    /**
     * Set the value of EffectedTime
     *
     * @param EffectedTime new value of EffectedTime
     */
    public void setEffectedTime(Timestamp EffectedTime) {
        this.EffectedTime = EffectedTime;
    }

    /**
     * Get the value of EventType
     *
     * @return the value of EventType
     */
    public String getEventType() {
        return EventType;
    }

    /**
     * Set the value of EventType
     *
     * @param EventType new value of EventType
     */
    public void setEventType(String EventType) {
        this.EventType = EventType;
    }

    /**
     * Get the value of IsEnabled
     *
     * @return the value of IsEnabled
     */
    public boolean isIsEnabled() {
        return IsEnabled;
    }

    /**
     * Set the value of IsEnabled
     *
     * @param IsEnabled new value of IsEnabled
     */
    public void setIsEnabled(boolean IsEnabled) {
        this.IsEnabled = IsEnabled;
    }

    /**
     * Get the value of Protocol
     *
     * @return the value of Protocol
     */
    public String getProtocol() {
        return Protocol;
    }

    /**
     * Set the value of Protocol
     *
     * @param Protocol new value of Protocol
     */
    public void setProtocol(String Protocol) {
        this.Protocol = Protocol;
    }

    /**
     * Get the value of DeployPlaces
     *
     * @return the value of DeployPlaces
     */
    public String[] getDeployPlaces() {
        return DeployPlaces;
    }

    /**
     * Set the value of DeployPlaces
     *
     * @param DeployPlaces new value of DeployPlaces
     */
    public void setDeployPlaces(String[] DeployPlaces) {
        this.DeployPlaces = DeployPlaces;
    }

    /**
     * Get the value of DeployPlaces at specified index
     *
     * @param index the index of DeployPlaces
     * @return the value of DeployPlaces at specified index
     */
    public String getDeployPlaces(int index) {
        return this.DeployPlaces[index];
    }

    /**
     * Set the value of DeployPlaces at specified index.
     *
     * @param index the index of DeployPlaces
     * @param DeployPlaces new value of DeployPlaces at specified index
     */
    public void setDeployPlaces(int index, String DeployPlaces) {
        this.DeployPlaces[index] = DeployPlaces;
    }

    /**
     * Get the value of ReverseMatch
     *
     * @return the value of ReverseMatch
     */
    public boolean isReverseMatch() {
        return ReverseMatch;
    }

    /**
     * Set the value of ReverseMatch
     *
     * @param ReverseMatch new value of ReverseMatch
     */
    public void setReverseMatch(boolean ReverseMatch) {
        this.ReverseMatch = ReverseMatch;
    }

    /**
     * Get the value of RuleType
     *
     * @return the value of RuleType
     */
    public String getRuleType() {
        return RuleType;
    }

    /**
     * Set the value of RuleType
     *
     * @param RuleType new value of RuleType
     */
    public void setRuleType(String RuleType) {
        this.RuleType = RuleType;
    }

    /**
     * Get the value of Groups
     *
     * @return the value of Groups
     */
    public String[] getGroups() {
        return Groups;
    }

    /**
     * Set the value of Groups
     *
     * @param Groups new value of Groups
     */
    public void setGroups(String[] Groups) {
        this.Groups = Groups;
    }

    /**
     * Get the value of Groups at specified index
     *
     * @param index the index of Groups
     * @return the value of Groups at specified index
     */
    public String getGroups(int index) {
        return this.Groups[index];
    }

    /**
     * Set the value of Groups at specified index.
     *
     * @param index the index of Groups
     * @param Groups new value of Groups at specified index
     */
    public void setGroups(int index, String Groups) {
        this.Groups[index] = Groups;
    }

    /**
     * Get the value of EventAction
     *
     * @return the value of EventAction
     */
    public String getEventAction() {
        return EventAction;
    }

    /**
     * Set the value of EventAction
     *
     * @param EventAction new value of EventAction
     */
    public void setEventAction(String EventAction) {
        this.EventAction = EventAction;
    }

    /**
     * Get the value of MatchPattern
     *
     * @return the value of MatchPattern
     */
    public String getMatchPattern() {
        return MatchPattern;
    }

    /**
     * Set the value of MatchPattern
     *
     * @param MatchPattern new value of MatchPattern
     */
    public void setMatchPattern(String MatchPattern) {
        this.MatchPattern = MatchPattern;
    }

    /**
     * Get the value of RuleName
     *
     * @return the value of RuleName
     */
    public String getRuleName() {
        return RuleName;
    }

    /**
     * Set the value of RuleName
     *
     * @param RuleName new value of RuleName
     */
    public void setRuleName(String RuleName) {
        this.RuleName = RuleName;
    }

    /**
     * Get the value of RegTime
     *
     * @return the value of RegTime
     */
    public Timestamp getRegTime() {
        return RegTime;
    }

    /**
     * Set the value of RegTime
     *
     * @param RegTime new value of RegTime
     */
    public void setRegTime(Timestamp RegTime) {
        this.RegTime = RegTime;
    }

    /**
     * Get the value of RuleNumber
     *
     * @return the value of RuleNumber
     */
    public String getRuleNumber() {
        return RuleNumber;
    }

    /**
     * Set the value of RuleNumber
     *
     * @param RuleNumber new value of RuleNumber
     */
    public void setRuleNumber(String RuleNumber) {
        this.RuleNumber = RuleNumber;
    }

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

}
