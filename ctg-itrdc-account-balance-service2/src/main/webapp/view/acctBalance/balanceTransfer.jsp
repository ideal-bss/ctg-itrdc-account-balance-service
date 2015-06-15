<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<body>
	<script type="text/javascript">
		function form_submit_transfer(){
			/*参数为空校验*/
			if (check()) {
				return;
			}
			$.ajax({
				async:false,
				type:"post",
				url:"${pageContext.request.contextPath}/acctBalance/balanceTransfer.action",
				cache:false,
				dataType:"json",
				data:$('#balance_transfer_view_form').serialize(),
				success:function(datas){
					$.messager.alert('提示',datas);
				}
			});
		}
		
		function check(){
			var origAcctId = $("#origAcctId").val();
			var requestId = $("#requestId").val();
			var origObjectId = $("#origObjectId").val();
			var amount = $("#amount").val();
			var acctId = $("#acctId").val();
			var objectId = $("#objectId").val();
			var flag = false;
			if (origAcctId == null || $.trim(origAcctId)=="" || origAcctId==0) {
				$("#origAcctIdHint").html("源账户标识不能为空！");
				flag = true;
			} else {
				$("#origAcctIdHint").html("");
			}
			if (origObjectId == null || $.trim(origObjectId)=="") {
				$("#origObjectIdHint").html("源余额对象不能为空！");
				flag = true;
			} else {
				$("#origObjectIdHint").html("");
			}
			if (acctId == null || $.trim(acctId)=="" || acctId==0) {
				$("#acctIdHint").html("目的账户标识不能为空！");
				flag = true;
			} else {
				$("#acctIdHint").html("");
			}
			if (objectId == null || $.trim(objectId)=="") {
				$("#objectIdHint").html("目的余额对象不能为空！");
				flag = true;
			} else {
				$("#objectIdHint").html("");
			}
			if (requestId == null || $.trim(requestId)=="" || requestId==0) {
				$("#requestIdHint").html("请求流水号不能为空！");
				flag = true;
			} else {
				$("#requestIdHint").html("");
			}
			if (amount == null || $.trim(amount)=="" || amount==0) {
				$("#amountHint").html("转账金额不能为空！");
				flag = true;
			} else {
				$("#amountHint").html("");
			}
			
			return flag;
		}
	</script>
	<div align="center" style="width: 1000px;height: 800px;">
		<form id="balance_transfer_view_form">
			<table>
				<tr>
					<td>源余额类型标识：</td>
					<td><!-- <input id="origBalanceTypeId" name="origBalanceTypeId" class="easyui-combo"/> -->
						<select id="origBalanceTypeId" name="origBalanceTypeId" class="easyui-combobox" style="width: 150px">
						 	<option value="1">普通预存款</option>
						 	<option value="2">溢收款</option>
						 	<option value="3">赠送</option>
						</select>
					</td>
					<td>源账户标识：</td>
					<td><input id="origAcctId" name="origAcctId" class="easyui-numberbox"/></td>
					<td>请求流水号：</td>
					<td><input id="requestId" name="requestId" class="easyui-numberbox"/></td>
				</tr>
				<tr align="right">
					<td colspan="2">&nbsp;<font id="origBalanceTypeIdHint" color="red"></font></td>
					<td colspan="2">&nbsp;<font id="origAcctIdHint" color="red"></font></td>
					<td colspan="2">&nbsp;<font id="requestIdHint" color="red"></font></td>
				</tr>
				<tr>
					<td>源余额对象类型：</td>
					<td><!-- <input id="origObjectType" name="origObjectType" class="easyui-combo"/> -->
						<select id="origObjectType" name="origObjectType" class="easyui-combobox" style="width: 150px;" data-options="editable:false">
							<option value="1">账户</option>
				 			<option value="2">设备</option>
						</select>
					</td>
					<td>源余额对象：</td>
					<td><input id="origObjectId" name="origObjectId" class="easyui-textbox"/></td>
					<td>转账金额：</td>
					<td><input id="amount" name="amount" class="easyui-numberbox"/></td>
				</tr>
				<tr align="right">
					<td colspan="2">&nbsp;<font id="origObjectTypeHint" color="red"></font></td>
					<td colspan="2">&nbsp;<font id="origObjectIdHint" color="red"></font></td>
					<td colspan="2">&nbsp;<font id="amountHint" color="red"></font></td>
				</tr>
				<tr>
					<td>目的余额类型标识：</td>
					<td><!-- <input id="balanceTypeId" name="balanceTypeId" class="easyui-combo"/> -->
						<select id="balanceTypeId" name="balanceTypeId" class="easyui-combobox" style="width: 150px">
						 	<option value="1">普通预存款</option>
						 	<option value="2">溢收款</option>
						 	<option value="3">赠送</option>
						</select>
					</td>
					<td>目的账户标识：</td>
					<td><input id="acctId" name="acctId" class="easyui-numberbox"/></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr align="right">
					<td colspan="2">&nbsp;<font id="balanceTypeIdHint" color="red"></font></td>
					<td colspan="2">&nbsp;<font id="acctIdHint" color="red"></font></td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>目的余额对象类型：</td>
					<td><!-- <input id="objectType" name="objectType" class="easyui-combo"/> -->
						<select id="objectType" name="objectType" class="easyui-combobox" style="width: 150px;" data-options="editable:false">
							<option value="1">账户</option>
				 			<option value="2">设备</option>
						</select>
					</td>
					<td>目的余额对象：</td>
					<td><input id="objectId" name="objectId" class="easyui-textbox"/></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr align="right">
					<td colspan="2">&nbsp;<font id="objectTypeHint" color="red"></font></td>
					<td colspan="2">&nbsp;<font id="objectIdHint" color="red"></font></td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
					<td colspan="2"><a href="#" class="easyui-linkbutton" onclick="form_submit_transfer();" style="width:65px">转账</a></td>
					<td colspan="2">&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>
</body>
