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
	<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/public/css/main.css"> --%>
	
</head>

 <body>
 <script type="text/javascript">
function balanceQuery(){
	if ($('#tt').tabs('exists', "余额账本查询")){
        $('#tt').tabs('select', "余额账本查询");
    } else {
    	$('#tt').tabs('add',{
            title:"余额账本查询",
            href:"<%=rootPath%>/test/queryBalance.action",
            closable:true
        });
    }
}
function balanceAdd(){
	if ($('#tt').tabs('exists', "余额存入")){
        $('#tt').tabs('select', "余额存入");
    } else {
    	$('#tt').tabs('add',{
            title:"余额存入",
            href:"<%=rootPath%>/acctBalance/balanceAddGo.action",
            closable:true
        });
    }
}
function balanceAdd(){
	if ($('#tt').tabs('exists', "余额存入")){
        $('#tt').tabs('select', "余额存入");
    } else {
    	$('#tt').tabs('add',{
            title:"余额存入",
            href:"<%=rootPath%>/acctBalance/balanceAddGo.action",
            closable:true
        });///view/acctBalance/balanceAdd.jsp  /acctBalance/balanceAddGo.action
    }
}
function specialQuery(){
	if ($('#tt').tabs('exists', "专款专用查询")){
        $('#tt').tabs('select', "专款专用查询");
    } else {
    	$('#tt').tabs('add',{
            title:"专款专用查询",
            href:"<%=rootPath%>/special/specialQueryGo.action",
            closable:true
        });
    }
}
function balanceType(){
	if ($('#tt').tabs('exists', "余额类型查询")){
        $('#tt').tabs('select', "余额类型查询");
    } else {
    	$('#tt').tabs('add',{
            title:"余额类型查询",
            href:"<%=rootPath%>/balanceType/balanceTypeQueryGo.action",
            closable:true
        });
    }
}
function specialAddGo(){
	if ($('#tt').tabs('exists', "专款专用新增")){
        $('#tt').tabs('select', "专款专用新增");
    } else {
    	$('#tt').tabs('add',{
            title:"专款专用新增",
            href:"<%=rootPath%>/special/specialAddGo.action",
            closable:true
        });
    }
}
function balanceTypeAddGo(){
	if ($('#tt').tabs('exists', "余额类型新增")){
        $('#tt').tabs('select', "余额类型新增");
    } else {
    	$('#tt').tabs('add',{
            title:"余额类型新增",
            href:"<%=rootPath%>/balanceType/balanceTypeAddGo.action",
            closable:true
        });
    }
}
</script>
 <div class="easyui-layout" style="padding:10px;height:550px;">
	<div data-options="region:'west',split:true" style="width: 20%;">
		<div class="easyui-accordion" id="query">
			<div id="" title="余额账本查询" style="">
				<ul type="none">
					<li><a href="javascript:balanceQuery();" style="font-size: 16px;text-decoration: none;color: black;">查询</a></li>
					<li><a href="javascript:balanceAdd();" style="font-size: 16px;text-decoration: none;color: black;">余额存入</a></li>
				</ul>
			</div>
			<div id="specialPayment" title="专款专用配置">
				<ul type="none" style="font-size: 16px;">
					<li><a href="javascript:specialQuery();" style="font-size: 16px;text-decoration: none;color: black;">专款专用查询</a></li>
					<li><a href="javascript:specialAddGo();" style="font-size: 16px;text-decoration: none;color: black;">专款专用新增</a></li>
				</ul>
			</div>
			<div id="balanceType" title="余额类型管理">
				<ul type="none" style="font-size: 16px;">
					<li><a href="javascript:balanceType();" style="font-size: 16px;text-decoration: none;color: black;">余额类型查询</a></li>
					<li><a href="javascript:balanceTypeAddGo();" style="font-size: 16px;text-decoration: none;color: black;">余额类型新增</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div data-options="region:'center',split:true" style="width: 20%;">
		<div id="tt" class="easyui-tabs">
		
		</div>
	</div>
</div>
</body>

</html>