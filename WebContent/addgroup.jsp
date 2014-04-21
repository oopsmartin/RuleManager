<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>AddGroup</title>
		<script language="javascript" >
		var req;
        window.onload=function(){}
                
        function groupname_validate()
        {
        	
            if(document.getElementById('groupname').getAttribute('value').length==0)
            	{
            	  alert('empty groupname!!!');
            	  location.reload();
            	}
            else
            	{
            	 var groupName=document.getElementById('groupname').value;            
            	 alert(groupName);
                 var url="AddGroup?groupname="+escape(groupName);
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
            var i;
            alert(xInput.length);
            var docInput = document.getElementById('groupname');
            for(i=0;i<xInput.length;i++)
            {
            	//alert(xInput[i].childNodes[0].nodeValue);
                if(docInput.value==xInput[i].childNodes[0].nodeValue)
            	   {
            	     alert('The groupname already exists. Please input another groupname!!!');
            	     document.getElementById('groupname').setAttribute('value','');
            	     location.reload();
            	     return;
            	   }
            }
            alert("submit");
            document.getElementById('addgroup').submit();
         }
	</script>
	</head>
	<body>
		<form action="AddGroup" method="post" id="addgroup">
			<table>
			<tr>
				<td align="middle" width=81>
					<font color="#000000">GroupName</font>
				</td>
				<td>
					<input id="groupname" maxlength=16 size=16 name="groupname" />
				</td>
			</tr>
			<tr>
				<td>
					<input maxLength=16 size=16 value="增加" type="button" onclick="groupname_validate()"/>
				</td>
				<td>
					<input maxLength=16 size=16 value="取消" type="reset" />
				</td>
			</tr>
			</table>			
		</form>
	</body>
</html>
