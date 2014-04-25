<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>SearchUser</title>		
		<script language="javascript" >
		
		var req;
        window.onload=function(){}  
        
        function redirect(){
        	window.location.href = "listusers.jsp";
        }
       
  	</script>	
	</head>
	<body>
		<form action="SearchUser" method="post" id="searchuser">
			<table>
			<tr>
				<td align="middle" ><font color="#000000">AuthorizedIP</font>
				</td>
				<td>
					<input maxlength=16 size=16 name="authorizedIP" id="authorizedIP" type="text">
				</td>								
			</tr>
			<tr>
				<td align="middle" ><font color="#000000">CreateTime</font>
				</td>
				<td>
					<input maxlength=16 size=16 name="createTime" id="createTime" type="text">
				</td>
								
			</tr>
			<tr>
				<td align="middle" ><font color="#000000">EffectTime</font>
				</td>
				<td>
					<input maxlength=16 size=16 name="effectTime" id="effectTime" type="text">
				</td>								
			</tr>
			<tr>
				<td align="middle" ><font color="#000000">ExpireTime</font>
				</td>
				<td>
					<input maxlength=16 size=16 name="expireTime" id="expireTime" type="text">
				</td>								
			</tr>
			<tr>
				<td align="middle" ><font color="#000000">LastLoginTime</font>
				</td>
				<td>
					<input maxlength=16 size=16 name="lastLoginTime" id="lastLoginTime" type="text">
				</td>								
			</tr>
			<tr>
				<td align="middle" ><font color="#000000">Username</font>
				</td>
				<td>
					<input maxlength=16 size=16 name="username" id="username" type="text">
				</td>
								
			</tr>	
			<tr>
				<td>
					<input maxLength=16 size=16 value="查看" type="submit"  />
					<input style="width: 60px; height: 23px;" value="所有" type="button" onclick="redirect()" />
				</td>
				<td><input maxlength=16 size=16 value="取消" type="reset" />
				</td>
			</tr>
			<tr>
				<td>
					<a href="main.jsp">返回</a>
				</td>
			</tr>
		</table>		

		</form>
	</body>
</html>