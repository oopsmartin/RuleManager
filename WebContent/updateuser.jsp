<%@ page language="java" import="org.cncert.mtxrulemanager.utils.*,java.sql.Statement,java.sql.PreparedStatement,java.sql.ResultSet" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>UpdateUser</title>		
		<script language="javascript" >
		
		var req;
        window.onload=function(){}
                
        function change_select()
        {
        	document.getElementById('password').setAttribute('value','');
        	document.getElementById('password1').setAttribute('value','');
            var id=document.getElementById('ID').value;
            //alert(zhi+10);
            var url="UpdateUser?ID="+escape(id);
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
            var xInput=xmlDoc.getElementsByTagName('username');
            //alert(xInput[0].childNodes[0].nodeValue);
            var docInput = document.getElementById('username');
            docInput.setAttribute('value',xInput[0].childNodes[0].nodeValue);
                           
         }
        
        function validate()
        {
        	if(document.getElementById('password').getAttribute('value')!=
        		document.getElementById('password1').getAttribute('value'))
        		{
        			alert("different password!!!");
        			document.getElementById('password').setAttribute('value','');
        			document.getElementById('password1').setAttribute('value','');
        			document.getElementById('password').focus();
        			
        		}
        	else
        		document.getElementById('updateUser').submit();
        }
  	</script>	
	</head>
	<body>
		<form action="UpdateUser" method="post" id="updateUser">
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
				<td align="middle" ><font color="#000000">Username</font>
				</td>
				<td><input maxlength=16 size=16 name="username" id="username" type="text"></td>
				<td><span class="rq">*</span></td>
				
			</tr>
			<tr>
				<td align="middle"><font color="#000000">Password</font>
				</td>
				<td><input maxlength=16 size=16 name="password" id="password" type="text" ></td>
				<td><span class="rq">*</span></td>
			</tr>
			<tr>
				<td align="middle"><font color="#000000">ReinputPassword</font>
				</td>
				<td><input maxlength=16 size=16 name="password1" id="password1" type="text"></td>
				<td><span class="rq">*</span></td>
			</tr>
			<tr>
				<td><input maxLength=16 size=16 value="更改" type="button" onclick="validate()"/>
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