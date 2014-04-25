<%@ page language="java" import="org.cncert.mtxrulemanager.utils.*,java.sql.Statement,java.sql.PreparedStatement,java.sql.ResultSet" pageEncoding="UTF-8"%>
<html>
	<head>
	<link type="text/css" rel="stylesheet" href="calendar.css" >
	<script type="text/javascript" src="calendar.js"></script>  
	<script type="text/javascript" src="calendar-zh.js"></script>
	<script type="text/javascript" src="calendar-setup.js" ></script>
	<script type="text/javascript">        
        window.onload=function(){}       
           
        function redirect(){
        	window.location.href = "listrules.jsp";
        }
    </script>
	
	<title>SearchRule</title>
	</head>
	<body>
		<form action="SearchRule" method="post">
			<table>
			<tr>
				<!-- 
				<td align="middle" width=81><font color="#000000">ID</td>
				<td width=81>
					<input maxlength=16 size=16 name="ID" id="ID" readonly />
				
				<select name="ID" id="ID" onChange= "change_select()">
						<option value="default" selected="selected">序号</option> 
<%--
String sql = "select ID from rule order by ID";
DBUtils db = new DBUtils("jdbc:mysql://127.0.0.1:3306/mtxrulemanager", "root", "8086W028C");
System.out.println("here");
Statement stmt = db.getConn().createStatement();
ResultSet rs = stmt.executeQuery(sql);
while(rs.next())
{
String ID = rs.getString( "ID");
--%> 
						<option value= "">  </option> 
<%-- 
} 
rs.close();
stmt.close();
db.getConn().close();
--%> 

					</select>
					-->
				
			</tr>
			<tr>
				<td align="middle"><font color="#000000">CreateBy</font></td>
				<td>
					<input maxlength=16 size=16 name="createBy" id="createBy" />
				</td>
			</tr>
			<tr>
				<td align="middle"><font color="#000000">DeployPlaces</font></td>
				<td>
					<input type="checkbox" name="deployNational" id="deployNational">国际
					<input type="checkbox" name="deployProvincial" id="deployProvincial">省际
					<input type="checkbox" name="deployGate" id="deployGate">关口
					<input type="checkbox" name="deployProvider" id="deployProvider">供应商
				</td>
			</tr>
			<tr>
				<td align="middle"><font color="#000000">EffectedTime</font></td>
				<td>
					<input type="text" id="effectedTime" name="effectedTime" onclick="return showCalendar('EffectedTime', 'y-mm-dd');" />				
					<select id="effectedHour" name="effectedHour" >
						<option value="00">00:00</option>
						<option value="01">01:00</option>
						<option value="02">02:00</option>
						<option value="03">03:00</option>
						<option value="04">04:00</option>
						<option value="05">05:00</option>
						<option value="06">06:00</option>
						<option value="07">07:00</option>
						<option value="08">08:00</option>
						<option value="09">09:00</option>
						<option value="10">10:00</option>
						<option value="11">11:00</option>
						<option value="12">12:00</option>
						<option value="13">13:00</option>
						<option value="14">14:00</option>
						<option value="15">15:00</option>
						<option value="16">16:00</option>
						<option value="17">17:00</option>
						<option value="18">18:00</option>
						<option value="19">19:00</option>
						<option value="20">20:00</option>
						<option value="21">21:00</option>
						<option value="22">22:00</option>
						<option value="23">23:00</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="middle"><font color="#000000">EventAction</font></td>
				<td>
					<textarea rows="4" cols="18" id="eventAction" name="eventAction"></textarea>
				</td>
			</tr>
			<tr>
				<td align="middle"><font color="#000000">EventType</font></td>
				<td>
					<select name="eventType">
						<option value="1">1</option>
  						<option value="2">2</option>
  						<option value="3">3</option>
  						<option value="4">4</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="middle"><font color="#000000">ExpiredTime</font></td>
				<td>
					<input type="text" id="expiredTime" name="expiredTime" onclick="return showCalendar('ExpiredTime', 'y-mm-dd');" >
					<select name="expiredHour" id="expiredHour">
						<option value="00">00:00</option>
						<option value="01">01:00</option>
						<option value="02">02:00</option>
						<option value="03">03:00</option>
						<option value="04">04:00</option>
						<option value="05">05:00</option>
						<option value="06">06:00</option>
						<option value="07">07:00</option>
						<option value="08">08:00</option>
						<option value="09">09:00</option>
						<option value="10">10:00</option>
						<option value="11">11:00</option>
						<option value="12">12:00</option>
						<option value="13">13:00</option>
						<option value="14">14:00</option>
						<option value="15">15:00</option>
						<option value="16">16:00</option>
						<option value="17">17:00</option>
						<option value="18">18:00</option>
						<option value="19">19:00</option>
						<option value="20">20:00</option>
						<option value="21">21:00</option>
						<option value="22">22:00</option>
						<option value="23">23:00</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="middle"><font color="#000000">Groups</font></td>
				<td>
					<input type="checkbox" name="group1" id="group1">1					
					<input type="checkbox" name="group2" id="group2">2					
					<input type="checkbox" name="group3" id="group3">3					
					<input type="checkbox" name="group4" id="group4">4
				</td>
			</tr>
			<tr>
				<td align="middle"><font color="#000000">IsEnabled</font></td>
				<td>
					<select name="isEnabled">
						<option value="true">true</option>
  						<option value="false">false</option>  						
					</select>
				</td>
			</tr>
			<tr>
				<td align="middle"><font color="#000000">MatchPattern</font></td>
				<td>
					<textarea rows="4" cols="18" id="matchPattern" name="matchPattern"></textarea>
				</td>
			</tr>
			<tr>
				<td align="middle"><font color="#000000">Protocol</font></td>
				<td>
					<select name="protocol" id="protocol">
						<option value="tcp">TCP</option>
  						<option value="http">http</option>
  						<option value="ftp">ftp</option>
  						<option value="udp">udp</option>
					</select>
				</td>
			</tr>
			<!-- 
			<tr>
				<td align="middle"><font color="#000000">RegTime</font></td>
				<td>
					<input maxlength=16 size=16 name="regTime" id="regTime">
				</td>
			</tr>
			-->
			<tr>
				<td align="middle"><font color="#000000">Remark</font></td>
				<td>
					<textarea rows="4" cols="18" id="remark" name="remark"></textarea>
				</td>
			</tr>
			<tr>
				<td align="middle"><font color="#000000">Remark1</font></td>
				<td>
					<textarea rows="4" cols="18" id="remark1" name="remark1"></textarea>
				</td>
			</tr>
			<tr>
				<td align="middle"><font color="#000000">Remark2</font></td>
				<td>
					<textarea rows="4" cols="18" id="remark2" name="remark2"></textarea>
				</td>
			</tr>
			<tr>
				<td align="middle"><font color="#000000">ReverseMatch</font></td>
				<td>
					<select name="reverseMatch" id="reverseMatch">
						<option value="true">true</option>
						<option value="false">false</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="middle"><font color="#000000">RuleName</font></td>
				<td>
					<input maxlength=16 size=16 name="ruleName" id="ruleName">
				</td>
			</tr>
			<tr>
				<td align="middle"><font color="#000000">RuleNumber</font></td>
				<td>
					<input maxlength=16 size=16 name="ruleNumber" id="ruleNumber">
				</td>
			</tr>
			<tr>
				<td align="middle"><font color="#000000">RuleType</font></td>
				<td>
					<select name="ruleType" id="ruleType">
						<option value="spec">专项任务</option>
  						<option value="trojan">远控木马</option>
  						<option value="botnet">僵尸网络</option>
  						<option value="worm">蠕虫</option>
					</select>
				</td>
			</tr>		
			
			<tr>
				<td>
					<input maxLength=16 size=16 value="查找" type="submit" />
					<input style="width: 60px; height: 23px;" value="所有" type="button" onclick="redirect()" />
				</td>
				<td><input maxLength=16 size=16 value="取消" type="reset" />
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