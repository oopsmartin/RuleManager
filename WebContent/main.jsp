<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>MainMenu</title>
<script language="javascript" >

	function perform(id)
	{
		alert(id);
		alert(document.getElementsByName("CreateBy"));
		document.getElementById('activity').setAttribute('value',id);
		document.getElementById('redirectmenu').submit();
	}
	
</script>
</head>
<body>
	<form action="RedirectServlet" method="post" id="redirectmenu">
	<table border="1">
		<tr>
			<th>Group</th>
			<th>Rule</th>
			<th>User</th>
		</tr>
		<tr>
			<td><input name="addGroup" id="addGroup" type="button" value="增加" onclick="perform(this.id)"/></td>
			<td><input name="addRule" id="addRule" type="button" value="增加" onclick="perform(this.id)"></td>
			<td><input name="addUser" id="addUser" type="button" value="增加" onclick="perform(this.id)"/></td>
		</tr>
		<tr>
			<td><input name="delGroup" id="delGroup" type="button" value="删除" onclick="perform(this.id)"/></td>
			<td><input name="delRule" id="delRule" type="button" value="删除" onclick="perform(this.id)"></td>
			<td><input name="delUser" id="delUser" type="button" value="删除" onclick="perform(this.id)"/></td>
		</tr>
		<tr>
			<td><input name="updateGroup" id="updateGroup" type="button" value="修改" onclick="perform(this.id)"/></td>
			<td><input name="updateRule" id="updateRule" type="button" value="修改" onclick="perform(this.id)"/></td>
			<td><input name="updateUser" id="updateUser" type="button" value="修改" onclick="perform(this.id)"/></td>
		</tr>
		<tr>
			<td><input name="searchGroup" id="searchGroup" type="button" value="查询" onclick="perform(this.id)"/></td>
			<td><input name="searchRule" id="searchRule" type="button" value="查询" onclick="perform(this.id)"/></td>
			<td><input name="searchUser" id="searchUser" type="button" value="查询" onclick="perform(this.id)"/></td>
		</tr>
	</table>
	<input type="hidden" name="activity" id="activity" value='no'>
<%
String username = request.getParameter("CreateBy");
System.out.printf("createBy is %s\n",username);
%>
	<input type="hidden" name="createBy" id="createBy" value="<%= username%>">	
	</form>
</body>
</html>