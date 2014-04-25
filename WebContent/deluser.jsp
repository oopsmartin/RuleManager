<%@ page language="java" import="org.cncert.mtxrulemanager.utils.*,java.sql.Statement,java.sql.PreparedStatement,java.sql.ResultSet" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>DeleteUser</title>		
		<script language="javascript" >
		
		var req;
        window.onload=function(){}
                
        function change_select()
        {
        	document.getElementById('authorizedIP').setAttribute('value','');
        	document.getElementById('createTime').setAttribute('value','');
        	document.getElementById('effectTime').setAttribute('value','');
        	document.getElementById('expireTime').setAttribute('value','');
        	document.getElementById('lastLoginTime').setAttribute('value','');
        	document.getElementById('username').setAttribute('value','');
            var id=document.getElementById('ID').value;
            //alert(zhi+10);
            var url="DeleteUser?ID="+escape(id);
            if(window.XMLHttpRequest)
            {
                req=new XMLHttpRequest();
            }else if(window.ActiveXObject)
            {
                req=new ActiveXObject("Microsoft.XMLHTTP");
            }
            
            if(req)
            {
                req.open("GET",url,true);
                req.onreadystatechange=callback;
                req.send(null);
            }
        }
        
        function callback()
        {
            if(req.readyState == 4)
            {
                if(req.status == 200)
                {
                    parseMessage();
                }else{
                    alert("Not able to retrieve description"+req.statusText);
                }
            }
        }
        
        function parseMessage()
        {
            var xmlDoc=req.responseXML.documentElement;
            var xInputAuthorizedIP=xmlDoc.getElementsByTagName('authorizedIP');
            var xInputCreateTime=xmlDoc.getElementsByTagName('createTime');
            var xInputEffectTime=xmlDoc.getElementsByTagName('effectTime');
            var xInputExpireTime=xmlDoc.getElementsByTagName('expireTime');
            var xInputLastLoginTime=xmlDoc.getElementsByTagName('lastLoginTime');
            var xInputUsername=xmlDoc.getElementsByTagName('username');
            //alert(xInput[0].childNodes[0].nodeValue);
            document.getElementById('authorizedIP').setAttribute('value',xInputAuthorizedIP[0].childNodes[0].nodeValue);
            document.getElementById('createTime').setAttribute('value',xInputCreateTime[0].childNodes[0].nodeValue);
            document.getElementById('effectTime').setAttribute('value',xInputEffectTime[0].childNodes[0].nodeValue);
            document.getElementById('expireTime').setAttribute('value',xInputExpireTime[0].childNodes[0].nodeValue);
            document.getElementById('lastLoginTime').setAttribute('value',xInputLastLoginTime[0].childNodes[0].nodeValue);
            document.getElementById('username').setAttribute('value',xInputUsername[0].childNodes[0].nodeValue);
            
                           
         }
        
        
  	</script>	
	</head>
	<body>
		<form action="DeleteUser" method="post" id="deleteUser">
			<table>
			<tr>
				<td align="middle" width=81><font color="#000000">ID</td>
				<td width=81>
					<select name="ID" id="ID" onChange= "change_select()">
						<option value="default" selected="selected">序号</option>
<%
String sql = "select ID from user order by ID";
DBUtils db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
System.out.println("here");
Statement stmt = db.getConn().createStatement();
ResultSet rs = stmt.executeQuery(sql);
while(rs.next())
{
String ID = rs.getString( "ID");
%>
						<option value="<%= ID%>"> <%=ID%> </option>
<% 
} 
rs.close();
stmt.close();
db.getConn().close();
%>
					</select>
				</td>				
			</tr>
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
				<td><input maxlength=16 size=16 name="username" id="username" type="text"></td>
				<td><span class="rq">*</span></td>
				
			</tr>
			
			<tr>
				<td><input maxLength=16 size=16 value="删除" type="submit" />
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