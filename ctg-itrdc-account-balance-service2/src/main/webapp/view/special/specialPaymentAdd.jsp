<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String rootPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/themes/demo.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
	
</head>

 <body>
 <script type="text/javascript">
  function submit_form(){
 	$.ajax({  
        async:false,  
        type:"POST",  
        url:"<%=rootPath%>/special/specialQuery.action",  
        dataType:"json",  
        cache: false,
        data:$('#form_select').serialize(),  
        success:function(data){
        	$.messager.alert("提示", "查询成功!");
        }
    }); 
 }
 </script>
<div  align="center" > 
		<form id="form_select" >
		<table style="padding: 10px;">
			<tr>
				
				<td width="10%">&nbsp;运营商:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-combo" >
				</td>
				<td width="10%">&nbsp;产品标识:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-textbox" >
				</td>
				<td width="10%">帐目组标识:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-textbox" >
				</td>
			</tr>
			<tr>
				<td width="10%">帐目组使用方式:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-combo" >
				</td>
				<td width="10%">状态:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-combo" >
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" onclick="submit_form('');" style="width:65px">新增</a></td>
			</tr>
		</table>
		</form>
		</div>
</body>
</html>