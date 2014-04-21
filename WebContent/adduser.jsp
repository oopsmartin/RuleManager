<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>AddUser</title>
		<link type="text/css" rel="stylesheet" href="calendar.css" >
		<script type="text/javascript" src="calendar.js"></script>  
		<script type="text/javascript" src="calendar-zh.js"></script>
		<script type="text/javascript" src="calendar-setup.js" ></script>
		<script language="javascript" >
		
		var req;
        window.onload=function(){}
                
        function username_validate()
        {
        	
            if(document.getElementById('username').getAttribute('value').length==0)
            	{
            	  alert('empty username!!!');
            	  location.reload();
            	}
            else
            	{
            	 var username=document.getElementById('username').value;            
            	 alert(username);
                 var url="AddUser?username="+escape(username);
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
            var xInput=xmlDoc.getElementsByTagName('username');
            var i;
            alert(xInput.length);
            var docInput = document.getElementById('username');
            for(i=0;i<xInput.length;i++)
            {
            	//alert(xInput[i].childNodes[0].nodeValue);
                if(docInput.value==xInput[i].childNodes[0].nodeValue)
            	   {
            	     alert('The username already exists, please input another username');
            	     document.getElementById('username').setAttribute('value','');
            	     document.getElementById('username').focus();
            	     location.reload();
            	     break;
            	   }
            }
         }

  		function check()
  		{
  			username_validate();
  			
	  		if(document.getElementById('password').getAttribute('value').length!=0 
	  				&& document.getElementById('password1').getAttribute('value').length!=0)
	  		  {
	  			if (document.getElementById('password').value!=document.getElementById('password1').value)
		  	   	   {
	  				alert('different password');
	  				document.getElementById('password').setAttribute('value','');
	  				document.getElementById('password1').setAttribute('value','');
		  	   	   }
	  			else
	  			{  
	  			   alert('submit!');
		  		   document.getElementById('adduser').submit();
	  			}
	  		  }
	  		else
	  			{
	  			  alert('password cannot be null');
	  			  location.reload();
	  			}
	  
  		}
  </script>	
	</head>
	<body>
		<form action="AddUser" method="post" id="adduser">
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
				<td><input maxlength=16 size=16 name="password" id="password" type="text" onclick="username_validate()"></td>
				<td><span class="rq">*</span></td>
			</tr>
			<tr>
				<td align="middle"><font color="#000000">reinputPassword</font>
				</td>
				<td><input maxlength=16 size=16 name="password1" id="password1" type="text"></td>
				<td><span class="rq">*</span></td>
			</tr>
			<tr>
				<td><input maxLength=16 size=16 value="增加" type="button" onclick="check()"  />
				</td>
				<td><input maxlength=16 size=16 value="取消" type="reset" />
				</td>
			</tr>
		</table>		

		</form>
	</body>
</html>