<%@page contentType="text/html" import="org.cncert.mtxrulemanager.utils.*,java.sql.Statement,java.sql.Timestamp,java.sql.ResultSet" pageEncoding="UTF-8"%>
<html>
    <head>        
        <title>ListRules</title>
    </head>
    <body>
    <table border="1">
    	<tr>
    		<th name="columnID">ID&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnCreateBy">CreateBy&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnDeployPlaces">DeployPlaces&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnEffectedTime">EffectedTime&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnEventAction">EventAction&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnEventType">EventType&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnExpiredTime">ExpiredTime&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnGroups">Groups&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnIsEnabled">IsEnabled&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnMatchPattern">MatchPattern&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnProtocol">Protocol&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnRegTime">RegTime&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnRemark">Remark&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnRemark1">Remark1&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnRemark2">Remark2&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnReverseMatch">ReverseMatch&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnRuleName">RuleName&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnRuleNumber">RuleNumber&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnRuleType">RuleType</th>
    	</tr>
<%
String sql = "select ID,CreateBy,DeployPlaces,EffectedTime,EventAction,EventType,"+
			"ExpiredTime,Groups,IsEnabled,MatchPattern,Protocol,RegTime,Remark,"+
			"Remark1,Remark2,ReverseMatch,RuleName,RuleNumber,RuleType from rule order by ID";
DBUtils db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
System.out.println("here");
Statement stmt = db.getConn().createStatement();
ResultSet rs = stmt.executeQuery(sql);
while(rs.next())
{
%>
        <tr>
<%
String ID = new Integer((int)rs.getInt("ID")).toString();
String CreateBy = rs.getString("CreateBy");
String DeployPlaces = rs.getString("DeployPlaces");
Timestamp EffectedTime = rs.getTimestamp("EffectedTime");
String EventAction = rs.getString("EventAction");
String EventType = rs.getString("EventType");
Timestamp ExpiredTime = rs.getTimestamp("ExpiredTime");
String Groups = rs.getString("Groups");
boolean IsEnabled = rs.getBoolean("IsEnabled");
String MatchPattern = rs.getString("MatchPattern");
String Protocol = rs.getString("Protocol");
Timestamp RegTime = rs.getTimestamp("RegTime");
String Remark = rs.getString("Remark");
String Remark1 = rs.getString("Remark1");
String Remark2 = rs.getString("Remark2");
boolean ReverseMatch = rs.getBoolean("ReverseMatch");
String RuleName = rs.getString("RuleName");
String RuleNumber = rs.getString("RuleNumber");
String RuleType = rs.getString("RuleType");
%>
			<td name="ID"><%= ID%>&nbsp&nbsp&nbsp</td>
    		<td name="CreateBy"><%= CreateBy%>&nbsp&nbsp&nbsp</td>
    		<td name="DeployPlaces"><%= DeployPlaces%>&nbsp&nbsp&nbsp</td>
    		<td name="EffectedTime"><%= EffectedTime%>&nbsp&nbsp&nbsp</td>
    		<td name="EventAction"><%= EventAction%>&nbsp&nbsp&nbsp</td>
    		<td name="EventType"><%= EventType%>&nbsp&nbsp&nbsp</td>
    		<td name="ExpiredTime"><%= ExpiredTime%>&nbsp&nbsp&nbsp</td>
    		<td name="Groups"><%= Groups%>&nbsp&nbsp&nbsp</td>
    		<td name="IsEnabled"><%= IsEnabled%>&nbsp&nbsp&nbsp</td>
    		<td name="MatchPattern"><%= MatchPattern%>&nbsp&nbsp&nbsp</td>
    		<td name="Protocol"><%= Protocol%>&nbsp&nbsp&nbsp</td>
    		<td name="RegTime"><%= RegTime%>&nbsp&nbsp&nbsp</td>
    		<td name="Remark"><%= Remark%>&nbsp&nbsp&nbsp</td>
    		<td name="Remark1"><%= Remark1%>&nbsp&nbsp&nbsp</td>
    		<td name="Remark2"><%= Remark2%>&nbsp&nbsp&nbsp</td>
    		<td name="ReverseMatch"><%= ReverseMatch%>&nbsp&nbsp&nbsp</td>
    		<td name="RuleName"><%= RuleName%>&nbsp&nbsp&nbsp</td>
    		<td name="RuleNumber"><%= RuleNumber%>&nbsp&nbsp&nbsp</td>
    		<td name="RuleType"><%= RuleType%></td>
        </tr>
        
<%
}
rs.close();
stmt.close();
db.getConn().close();
%>
	</table>
    </body>
</html>
