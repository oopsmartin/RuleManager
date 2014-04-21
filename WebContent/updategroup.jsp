<%@ page language="java" import="org.cncert.mtxrulemanager.utils.*,java.sql.Statement,java.sql.PreparedStatement,java.sql.ResultSet" pageEncoding="UTF-8"%>
<html>
	<head>
	<script type="text/javascript">
        var req;
        window.onload=function(){}
        /*
        function show()
        {
        	var value=document.getElementById('ID').value;
        	alert(value+10);
        }
        */
        
        function change_select()
        {
            var zhi=document.getElementById('ID').value;
            //alert(zhi+10);
            var url="UpdateGroup?ID="+escape(zhi);
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
            var xInput=xmlDoc.getElementsByTagName('groupname');
            //alert(xInput[0].childNodes[0].nodeValue);
            var docInput = document.getElementById('groupname');
            docInput.setAttribute('value',xInput[0].childNodes[0].nodeValue);
                           
         }
            
         function dump()
         {
        	 var changedInput=document.getElementById('groupname');
        	 var hiddenInput=document.getElementById('changedGroupName');
        	 
        	 hiddenInput.setAttribute('value',changedInput);
        	 alert(hiddenInput.getAttribute('value'));
         }   
        
    </script>
	
		<title>UpdateGroup</title>
	</head>
	<body>
		<form action="UpdateGroup" method="post">
			<table>
			<tr>
				<td align="middle" width=81><font color="#000000">ID</td>
				<td width=81>
					<select name="ID" id="ID" onChange= "change_select()"> 
<%
String sql = "select ID from rulegroup order by ID";
DBUtils db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
System.out.println("here");
Statement stmt = db.getConn().createStatement();
ResultSet rs = stmt.executeQuery(sql);
while(rs.next())
{
String ID = rs.getString( "ID");
%> 
						<option value= "<%=ID%>"> <%=ID%> </option> 
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
				<td align="middle"><font color="#000000">GroupName</font></td>
				<td><input maxlength=16 size=16 name="groupname" id="groupname"></td>
			</tr>
			
			<tr>
				<td><input maxLength=16 size=16 value="update" onSubmit="dump()" type="submit" />
				</td>
				<td><input maxLength=16 size=16 value="cancel" type="reset" />
				</td>
			</tr>
			</table>
		<input type="hidden" name="changedGroupName" id="changedGroupName" />
		</form>
	
	</body>
</html>