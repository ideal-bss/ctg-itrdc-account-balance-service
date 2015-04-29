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
</head>

 <body>
 <script type="text/javascript">

 </script>
<div  align="center" > 
		<form id="form_select" >
		<table style="padding: 10px 10px 10px" class="easyui-datagrid" style="HEIGHT: auto"
				fit="false" fitColumns="true" singleSelect="true"
		  rownumbers="false" pagination="false" striped="true" loadMsg="数据加载中,请稍后..." remoteSort="false">
			<thead>
				  <tr><th field="priority">余额类型优先级</th>
				  <th field="spePaymentId">专款专用标识</th>
				  <th field="measureMethodId">度量方法标识</th>
				  <th field="balanceTypeName">余额类型名称</th>
				  <th field="allowDraw">允许提取标志</th>
				  <th field="invOffer">提供发票标志</th>
				  <th field="ifEarning">是否抵收入</th>
				  <th field="ifPayold">是否抵旧欠</th>
				  <th field="ifSaveback">是否滚存</th>
				  <th field="ifPrincipal">是否本金</th>
				  <th field="statusCd">状态</th>
				  <th field="statusDate">状态时间</th>
				  </tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</form>
		</div>
</body>
</html>