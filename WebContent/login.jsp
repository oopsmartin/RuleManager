<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<script type="text/javascript">
	function validate(){
		if(document.getElementById('username').getAttribute('value').length!=0 &&
			document.getElementById('password').getAttribute('value').length!=0)
			document.getElementById('Login').submit();
	}
</script>
</head>
<body>
	<form id="Login" action="Login" method="post">
		<table>
			<tr>
				<td align="middle" ><font color="#000000">Username</font>
				</td>
				<td><input maxlength=16 size=16 name="username" id="username" type="text"></td>
				<td><span class="rq">*</span></td>				
			</tr>
			<tr>
				<td align="middle"><font color="#000000">Password</font>
				</td>
				<td><input maxlength=16 size=16 name="password" id="password" type="text" onclick="validate()"></td>
				<td><span class="rq">*</span></td>
			</tr>
			<tr>
				<td>
					<input maxLength=16 size=16 value="登陆" type="button" onclick="validate()"/>
				</td>
				<td>
					<input maxLength=16 size=16 value="取消" type="reset" />
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>