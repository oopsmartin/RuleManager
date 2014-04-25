<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		
		<title>AddRule</title>
		<link type="text/css" rel="stylesheet" href="calendar.css" >
		<script type="text/javascript" src="calendar.js"></script>  
		<script type="text/javascript" src="calendar-zh.js"></script>
		<script type="text/javascript" src="calendar-setup.js" ></script>
		<script language="javascript" > 
		
		var req;
                        
        function ruletype_validate()
        {   
        	
        	var ruletype=document.getElementById('RuleType').value;            
        	alert(ruletype);
            var url="AddRule?RuleType="+escape(ruletype);
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
                return true;
              }
            return false;
        }
        
        
        function callback()
        {
            if(req.readyState == 4)
            {
                if(req.status == 200)
                {
                	alert("200");
                    var flag = parseMessage();
                    if(flag==false)
                    	return;                    
                }else{
                    alert("Not able to retrieve description"+req.statusText);
                }
            }
        }
        
        function parseMessage()
        {
        	alert("first");
            var xmlDoc=req.responseXML.documentElement;
            var xInput=xmlDoc.getElementsByTagName('ruletype');
            var i;
            alert(xInput.length);
            var docInput = document.getElementById('RuleType');
            for(i=0;i<xInput.length;i++)
            {
            	alert(xInput[i].childNodes[0].nodeValue);
                if(docInput.value==xInput[i].childNodes[0].nodeValue)
            	   {
            	     alert('The RuleType already exists, please select other ruletype');
            	     location.reload();
            	     break;
            	   }
            }
            
         }


  		function check()
  		{
  			alert("before validate");
  			if(ruletype_validate())
  			  {
  			
  				alert(document.getElementById('EffectedTime').getAttribute('value'));
  				alert("after validate");
  				 				
	  			if((document.getElementById('deployNational').getAttribute('value').length!=0 ||
	  				document.getElementById('deployProvincial').getAttribute('value').length!=0 ||
	  				document.getElementById('deployGate').getAttribute('value').length!=0 ||
	  				document.getElementById('deployProvider').getAttribute('value').length!=0 )&&
	  				(document.getElementById('EventAction').getAttribute('value').length!=0 &&
	  				document.getElementById('EventType').getAttribute('value').length!=0 &&
	  				document.getElementById('MatchPattern').getAttribute('value').length!=0 &&
	  				document.getElementById('Protocol').getAttribute('value').length!=0 &&
	  				document.getElementById('RuleName').getAttribute('value').length!=0 &&
	  				document.getElementById('RuleNumber').getAttribute('value').length!=0))
	  		  		{  	
	  					
	  			   		alert('submit!');
		  		   		document.getElementById('AddRule').submit();	  			
	  		  		}
	  			else	  			
	  			  	alert('please fill in the form as expected');  
  			  }
  		}
        
  		/**
		var $ = function (id) {
		    return "string" == typeof id ? document.getElementById(id) : id;
		};
		function addEventHandler(oTarget, sEventType, fnHandler) {
		    if (oTarget.addEventListener) {
		        oTarget.addEventListener(sEventType, fnHandler, false);
		    } else if (oTarget.attachEvent) {
		        oTarget.attachEvent("on" + sEventType, fnHandler);
		    } else {
		        oTarget["on" + sEventType] = fnHandler;
		    }
		};

		function Each(arrList, fun){
		    for (var i = 0, len = arrList.length; i < len; i++) { fun(arrList[i], i); }
		};
		function GetOption(val, txt) {
		    var op = document.createElement("OPTION");
		    op.value = val; op.innerHTML = txt;
		    return op;
		};
		var Class = {
		create: function() {
		    return function() {
		      this.initialize.apply(this, arguments);
		    }
		}
		}
		Object.extend = function(destination, source) {
		    for (var property in source) {
		        destination[property] = source[property];
		    }
		    return destination;
		}

		var CascadeSelect = Class.create();
		CascadeSelect.prototype = {
		//select集合，菜单对象
		initialize: function(arrSelects, arrMenu, options) {
		    if(arrSelects.length <= 0 || arrMenu.lenght <= 0) return;//菜单对象
		    
		    var oThis = this;
		    
		    this.Selects = [];//select集合
		    this.Menu = arrMenu;//菜单对象
		    
		    this.SetOptions(options);
		    
		    this.Default = this.options.Default || [];
		    
		    this.ShowEmpty = !!this.options.ShowEmpty;
		    this.EmptyText = this.options.EmptyText.toString();
		    
		    //设置Selects集合和change事件
		    Each(arrSelects, function(o, i){
		        addEventHandler((oThis.Selects[i] = $(o)), "change", function(){ oThis.Set(i); });
		    });
		    
		    this.ReSet();
		},
		//设置默认属性
		SetOptions: function(options) {
		    this.options = {//默认值
		        Default:    [],//默认值(按顺序)
		        ShowEmpty:    false,//是否显示空值(位于第一个)
		        EmptyText:    "请选择"//空值显示文本(ShowEmpty为true时有效)
		    };
		    Object.extend(this.options, options || {});
		},
		//初始化select
		ReSet: function() {

		    this.SetSelect(this.Selects[0], this.Menu, this.Default.shift());
		    this.Set(0);
		},
		//全部select设置
		Set: function(index) {
		    var menu = this.Menu;
		    
		    //第一个select不需要处理所以从1开始
		    for(var i=1, len = this.Selects.length; i < len; i++){
		        if(menu.length > 0){
		            //获取菜单
		            var value = this.Selects[i-1].value;
		            if(value!=""){
		                Each(menu, function(o){ if(o.val == value){ menu = o.menu || []; } });
		            } else { menu = []; }
		            
		            //设置菜单
		            if(i > index){ this.SetSelect(this.Selects[i], menu, this.Default.shift()); }
		        } else {
		            //没有数据
		            this.SetSelect(this.Selects[i], [], "");
		        }
		    }
		    //清空默认值
		    this.Default.length = 0;
		},
		//select设置
		SetSelect: function(oSel, menu, value) {
		    oSel.options.length = 0; oSel.disabled = false;
		    if(this.ShowEmpty){ oSel.appendChild(GetOption("", this.EmptyText)); }
		    if(menu.length <= 0){ oSel.disabled = true; return; }
		    
		    Each(menu, function(o){
		        var op = GetOption(o.val, o.txt ? o.txt : o.val); op.selected = (value == op.value);
		        oSel.appendChild(op);
		    });    
		},
		//添加菜单
		Add: function(menu) {
		    this.Menu[this.Menu.length] = menu;
		    this.ReSet();
		},
		//删除菜单
		Delete: function(index) {
		    if(index < 0 || index >= this.Menu.length) return;
		    for(var i = index, len = this.Menu.length - 1; i < len; i++){ this.Menu[i] = this.Menu[i + 1]; }
		    this.Menu.pop()
		    this.ReSet();
		}
		};

		window.onload=function(){
		    
		    var menuDeployPlaces = [		        
		        {'val': '1', 'txt': 'value'},		        
		        {'val':'国际', 'menu': [
		        {'val':'省际','menu': [
		        {'val':'关口','menu': [
		        {'val':'MTx合作厂商'}
		        ]}
		        ]}
		        ]},
		        {'val':'中国','menu': [
		        {'val':'北京','menu': [
		        {'val':'东城区','menu': [
		        {'val':'北三环'}
		        ]},
		        {'val':'西城区','menu': [
		        {'val':'北三环'}
		        ]},
		        {'val':'海淀区','menu': [
		        {'val':'北三环'}
		        ]}
		        ]}
		        ]}		        
		    ];
		    
		    var menuGroups = [		            
		        {'val':'Group1','menu': [
		        {'val':'Group2','menu': [
		        {'val':'Group3','menu': [
		        {'val':'Group4'}                       
		        ]}
		        ]},
		        {'val':'Group2-2','menu': [
		        {'val':'Group2-2-1','menu': [
		        {'val':'Group2-2-1-1'}
		        ]}
		        ]}
		        ]}
		    ];		    
		    
		    var selDeployPlaces=["sel1", "sel2", "sel3", "sel4"];
		    var selGroups=["sel5", "sel6", "sel7", "sel8"];	    
		    
		    var valDeployPlaces=["国际", "省际", "关口", "MTx合作厂商"];
		    var valGroups=["Group1", "Group2", "Group3", "Group4"];		    
		    		    
		    var csDeployPlaces = new CascadeSelect(selDeployPlaces, menuDeployPlaces, { Default: valDeployPlaces });
		 	
		    var csGroups = new CascadeSelect(selGroups, menuGroups, { Default: valGroups });
		    
		    
		    $("btnA").onclick=function(){cs.ShowEmpty=!cs.ShowEmpty;}
		    
		    $("btnB").onclick=function(){
		        cs.Add(
		            {'val': '5 ->', 'menu': [
		                {'val': '5_1 ->', 'menu': [
		                    {'val': '5_1_1 ->', 'menu': [
		                        {'val': '5_1_1_1 ->', 'menu': [
		                            {'val': '5_1_1_1_1'}
		                        ]}
		                    ]}
		                ]}
		            ]}
		        )
		    };
		    
		    $("btnC").onclick=function(){
		        cs.Delete(3)
		    };
		    
		}
	**/	
	
	</script>
	<style type="text/css">
	.sel select{ width:100px;}
	</style>	
	</head>
	<body>
		<form action="AddRule" method="post" id="AddRule">
			<table>
			<!-- 
			<tr>
				<td align="left" ><font color="#000000">CreateBy</font>
				</td>
				<td><input maxlength=16 size=16 name="CreateBy" type="text"></td>
			</tr>
			-->
			<tr>
				
				<td align="left"><span class="rq" style="color:red">*</span><font color="#000000">DeployPlaces</font>
				</td>
				<td>
					<input type="checkbox" name="deployNational" id="deployNational">国际
					<input type="checkbox" name="deployProvincial" id="deployProvincial">省际
					<input type="checkbox" name="deployGate" id="deployGate">关口
					<input type="checkbox" name="deployProvider" id="deployProvider">供应商
				</td>
				<!--
				<td>
				<div class="sel" id="deployplaces">				
					<select id="sel1" name="sel1"></select>
					<select id="sel2" name="sel2"></select>				
					<select id="sel3" name="sel3"></select>
					<select id="sel4" name="sel4"></select>							
				</div>
				</td>			
				
				<td>
					<input id="btnA" type="button" value="显示/不显示空值"/>
					<input id="btnB" type="button" value="添加菜单"/>
					<input id="btnC" type="button" value="减少菜单"/>
				</td>
				-->								
			</tr>
			
			<tr>
				
				<td align="left" ><font color="#000000">EffectedTime</font>
				</td>
				<td>
					<input type="text" id="EffectedTime" name="EffectedTime" onclick="return showCalendar('EffectedTime', 'y-mm-dd');" />				
					<select id="EffectedHour" name="EffectedHour" >
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
				
				<td align="left" ><span class="rq" style="color:red">*</span><font color="#000000">EventAction</font>
				</td>
				<td>					
					<!-- 
					<input name="EventAction" id="EventAction" type="text" style="width: 106; height: 40" />							
					-->
					<textarea rows="4" cols="18" id="Eventaction" name="Eventaction"></textarea>
				</td>
				
			</tr>
			<tr>
				
				<td align="left" ><span class="rq" style="color:red">*</span><font color="#000000">EventType</font>
				</td>
				<td>
					<select name="EventType">
						<option value="1">1</option>
  						<option value="2">2</option>
  						<option value="3">3</option>
  						<option value="4">4</option>
					</select>				
				</td>
				
			</tr>
			
			<tr>
				
				<td align="left" ><font color="#000000">ExpiredTime</font>
				</td>
				<td>
					<input type="text" id="ExpiredTime" name="ExpiredTime" onclick="return showCalendar('ExpiredTime', 'y-mm-dd');" >
					<select name="ExpiredHour" id="ExpiredHour">
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
				
				<td align="left" ><font color="#000000">Groups</font>
				</td>
				<td>
					<input type="checkbox" name="group1" id="group1">1					
					<input type="checkbox" name="group2" id="group2">2					
					<input type="checkbox" name="group3" id="group3">3					
					<input type="checkbox" name="group4" id="group4">4				
					
					<!--
					<div class="sel" id="groups">
						<select id="sel5" name="sel5"></select>
						<select id="sel6" name="sel6"></select>
						<select id="sel7" name="sel7"></select>
						<select id="sel8" name="sel8"></select>									
					</div>
					 
					<select name="Groups">
						<option value="1">1</option>
  						<option value="2">2</option>
  						<option value="3">3</option>
  						<option value="4">4</option>
					</select>
					-->				
				</td>				
			</tr>
			<tr>				
				<td align="left" ><font color="#000000">IsEnabled</font>
				</td>
				<td>					
					<select name="IsEnabled">
						<option value="true">true</option>
  						<option value="false">false</option>  						
					</select>
				</td>
									
			</tr>
			<tr>
				
				<td align="left" ><span class="rq" style="color:red">*</span><font color="#000000">MatchPattern</font>
				</td>
				<!--  
				<td><input maxlength=16 size=16 name="MatchPattern" type="text"></td>
				-->
				<td>
					<textarea rows="4" cols="18" id="MatchPattern" name="MatchPattern"></textarea>
				</td>
			</tr>
			<tr>
				
				<td align="left" ><span class="rq" style="color:red">*</span><font color="#000000">Protocol</font>
				</td>
				<td>
					<select name="Protocol">
						<option value="tcp">TCP</option>
  						<option value="http">http</option>
  						<option value="ftp">ftp</option>
  						<option value="udp">udp</option>
					</select>				
				</td>
				
			</tr>
			<!-- 
			<tr>
				<td align="left" ><font color="#000000">RegTime</font>
				</td>
				<td>
					<input type="text" id="RegTime" name="RegTime" onclick="return showCalendar('RegTime', 'y-mm-dd');"  />
				</td>
			</tr>
			-->
			
			<tr>
				
				<td align="left" ><font color="#000000">Remark</font>
				</td>
				<td>
				<!-- 
				<input maxlength=16 size=16 name="Remark" type="text" type="text" style="width: 106; height: 40" />
				-->
					<textarea rows="4" cols="18" id="Remark" name="Remark"></textarea>
				</td>
				
			</tr>
			<tr>
				
				<td align="left" ><font color="#000000">Remark1</font>
				</td>
				<td>
					<textarea rows="4" cols="18" id="Remark1" name="Remark1"></textarea>
				</td>
				
			</tr>
			<tr>
				
				<td align="left" ><font color="#000000">Remark2</font>
				</td>
				<td>
					<textarea rows="4" cols="18" id="Remark2" name="Remark2"></textarea>
				</td>
				
			</tr>
			<tr>
				
				<td align="left" ><span class="rq" style="color:red">*</span><font color="#000000">ReverseMatch</font>
				</td>
				<td>
					<select name="ReverseMatch">
						<option value="true">true</option>
						<option value="false">false</option>
					</select>
				</td>
				
			</tr>
			<tr>
				
				<td align="left" ><span class="rq" style="color:red">*</span><font color="#000000">RuleName</font>
				</td>
				<td><input maxlength=16 size=16 name="RuleName" type="text"></td>
				
			</tr>
			<tr>
				
				<td align="left" ><span class="rq" style="color:red">*</span><font color="#000000">RuleNumber</font>
				</td>
				<td><input maxlength=16 size=16 name="RuleNumber" type="text"></td>
				
			</tr>
			<tr>
				
				<td align="left" ><span class="rq" style="color:red">*</span><font color="#000000">RuleType</font>
				</td>
				<td>
					<select name="RuleType" id="RuleType">
						<option value="spec">专项任务</option>
  						<option value="trojan">远控木马</option>
  						<option value="botnet">僵尸网络</option>
  						<option value="worm">蠕虫</option>
					</select>
				</td>
								
			</tr>
<%
String username = request.getParameter("CreateBy");
System.out.printf("addrule username is %s\n",username);
%>
			
			<tr>
				
				<td >
					<input maxLength=16 size=16 value="增加" name="addrule" id="addrule" type="button" onclick="check()"/>
				</td>
				<td>
					<input maxlength=16 size=16 value="取消" type="reset" />
				</td>
				
			</tr>
			<tr>
				<td>
					<a href="main.jsp">返回</a>
				</td>
			</tr>
			</table>
			<input type="hidden" name="createBy" id="createBy" value="<%= username%>" />
			
		</form>
	</body>
</html>