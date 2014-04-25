<%@ page language="java" import="org.cncert.mtxrulemanager.utils.*,java.sql.Statement,java.sql.PreparedStatement,java.sql.ResultSet" pageEncoding="UTF-8"%>
<html>
	<head>
	<script type="text/javascript">
        var req;
        window.onload=function(){}
        
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
            
         function redirect()
         {
        	 window.location.href = "listgroups.jsp";
         }
        
    </script>
	
		<title>ShowGroup</title>
	</head>
	<body>
		<form action="SearchGroup" method="post">
			<table>
			<tr>
				<td align="middle" width=81><font color="#000000">GroupName</td>
				<td>
					<input maxLength=16 size=16 type="text" name="groupname" id="groupname">
				</td>				
			</tr>			
			<tr>
				<td>
					<input maxLength=16 size=16 value="查看" type="submit" />
					<input style="width: 60px; height: 23px;" value="所有" type="button" onclick="redirect()" />
				</td>
				<td>
					<input maxLength=16 size=16 value="取消" type="reset" />
				</td>
			</tr>
			<tr>
				<td>
				
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