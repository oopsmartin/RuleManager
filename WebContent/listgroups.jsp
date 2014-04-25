<%@page contentType="text/html" import="org.cncert.mtxrulemanager.utils.*,java.sql.Statement,java.sql.Timestamp,java.sql.ResultSet" pageEncoding="UTF-8"%>
<html>
    <head>        
        <title>ListGroups</title>
    </head>
    <body>
    <table border="1">
    	<tr>
    		<th name="columnID">ID&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnGroupName">GroupName&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnCreateTime">CreateTime</th>
    	</tr>
<%
String sql = "select ID,GroupName,CreateTime from rulegroup order by ID";
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
String groupName = rs.getString("GroupName");
Timestamp createTime = rs.getTimestamp("CreateTime");
%>
			<td name="ID"><%= ID%>&nbsp&nbsp&nbsp&nbsp</td>
			<td name="GroupName"><%= groupName%>&nbsp&nbsp&nbsp&nbsp</td>
			<td name="CreateTime"><%= createTime%></td>
        </tr>
        
<%
}
rs.close();
stmt.close();
db.getConn().close();
%>
		<tr>
			<td>
				<a href="main.jsp">返回</a>
			</td>
		</tr>
	</table>
    </body>
</html>
