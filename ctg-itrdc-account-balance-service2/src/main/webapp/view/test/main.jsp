<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String rootPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Title</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/themes/demo.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
	
</head>
 <body class="easyui-layout">
	<div data-options="region:'north'" style="height:50px"></div>
		<div data-options="region:'center',title:'Main Title',iconCls:'icon-ok',header:'false'">
			<div class="easyui-tabs">
				<div title="余额管理"  id="menuSearch" data-options="href:'<%=rootPath%>/test/showBalance.action',closable:false,cache:true" style="padding:10px;height: 100%;">
					<!-- <div class="easyui-layout" style="padding:10px;height: 550px;">
						<div data-options="region:'west',split:true" style="width: 20%;">
							<div class="easyui-accordion"  id="query">
								<div title="余额账本查询" style="">
									<ul type="none" style="font-size: 16px;">
										<li>查询</li>
										<li>查询</li>
										<li>查询</li>
									</ul>
								</div>
								<div title="专款专用配置"></div>
								<div title="余额类型管理"></div>
							</div>
						</div>
						<div data-options="region:'center',split:true" style="" >
							<div class=""></div>
						</div>
					</div> -->
				</div>
			</div>
		</div>
</body>

</html>