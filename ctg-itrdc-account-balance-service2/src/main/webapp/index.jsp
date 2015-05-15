<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Title</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="余额,账务,帐务">
<meta http-equiv="description" content="余额管理界面">

<link rel="stylesheet" type="text/css" href="public/jquery-easyui-1.4.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="public/jquery-easyui-1.4.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="public/jquery-easyui-1.4.2/themes/demo.css">
<script type="text/javascript" src="public/jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="public/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:50px;"></div>
	<div data-options="region:'west',split:true" style="width:200px">
		<jsp:include page="/view/layout/west.jsp"></jsp:include>
	</div>
	<div data-options="region:'center'">
		<jsp:include page="/view/layout/center.jsp"></jsp:include>
	</div>
</body>
</html>