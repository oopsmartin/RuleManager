<%@page contentType="text/html" import="org.cncert.mtxrulemanager.utils.*,java.sql.Statement,java.sql.Timestamp,java.sql.ResultSet" pageEncoding="UTF-8"%>
<html>
    <head>        
        <title>ListUsers</title>
    </head>
    <body>
    <table border="1">
    	<tr>
    		<th name="columnID">ID&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnAuthorizedIP">AuthorizedIP&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnCreateTime">CreateTime&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnEffectedTime">EffectedTime&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnExpiredTime">ExpiredTime&nbsp&nbsp&nbsp&nbsp</th>
    		<th name="columnLastLoginTime">LastLoginTime&nbsp&nbsp&nbsp&nbsp</th>    		
    		<th name="columnUsername">Username&nbsp&nbsp&nbsp&nbsp</th>
    	</tr>
<%
String sql = "select ID,AuthorizedIP,CreateTime,EffectTime,ExpireTime,LastLoginTime,UserName from user order by ID";
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
String AuthorizedIP = rs.getString("AuthorizedIP");
Timestamp CreateTime = rs.getTimestamp("CreateTime");
Timestamp EffectTime = rs.getTimestamp("EffectTime");
Timestamp ExpireTime = rs.getTimestamp("ExpireTime");
Timestamp LastLoginTime = rs.getTimestamp("LastLoginTime");
String UserName = rs.getString("UserName");
%>
			<td name="ID"><%= ID%>&nbsp&nbsp&nbsp&nbsp</td>
			<td name="AuthorizedIP"><%= AuthorizedIP%>&nbsp&nbsp&nbsp&nbsp</td>
			<td name="CreateTime"><%= CreateTime%>&nbsp&nbsp&nbsp&nbsp</td>
			<td name="EffectedTime"><%= EffectTime%>&nbsp&nbsp&nbsp&nbsp</td>
			<td name="ExpireTime"><%= ExpireTime%>&nbsp&nbsp&nbsp&nbsp</td>
			<td name="LastLoginTime"><%= LastLoginTime%>&nbsp&nbsp&nbsp&nbsp</td>
			<td name="UserName"><%= UserName%>&nbsp&nbsp&nbsp&nbsp</td>
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
