<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <body>
 <script type="text/javascript">
 </script>
<div  align="center" > 
		<form id="view_balanceType_balanceTypeResult_result" >
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