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
			var origAcctBalanceId = $("#origAcctBalanceId").val();
			var amount = $("#transferAmount").val();
			var acctId = $("#goalAcctId").val();
			var objectId = $("#goalObjectId").val();
			var flag = false;
			if (origAcctBalanceId == null || $.trim(origAcctBalanceId)=="" || origAcctBalanceId==0) {
				$("#origAcctBalanceIdHint").html("源余额账本标识不能为空！");
				flag = true;
			} else {
				$("#origAcctBalanceIdHint").html("");
			}
			if (acctId == null || $.trim(acctId)=="" || acctId==0) {
				$("#goalAcctIdHint").html("目的账户标识不能为空！");
				flag = true;
			} else if(acctId.length>15){
				$("#goalAcctIdHint").html("账户标识不能超过15位！");
				flag = true;
			} else {
				$("#goalAcctIdHint").html("");
			}
			if (objectId == null || $.trim(objectId)=="") {
				$("#goalObjectIdHint").html("目的余额对象不能为空！");
				flag = true;
			} else if(objectId.length>15){
				$("#goalObjectIdHint").html("余额对象不能超过15位！");
				flag = true;
			} else {
				$("#goalObjectIdHint").html("");
			}
			if (amount == null || $.trim(amount)=="" || amount==0) {
				$("#transferAmountHint").html("转账金额不能为空！");
				flag = true;
			} else if(amount.length>10){
				$("#transferAmountHint").html("转账金额不能超过10位！");
				flag = true;
			} else {
				$("#transferAmountHint").html("");
			}
			
			return flag;
		}
		
		 //加载余额类型下拉框
	 	$(function(){
	 		  var origBalanceTypeId = $('#origBalanceTypeId').val();
		  	  $('#goalBalanceTypeId').combobox({
				  	url : '${pageContext.request.contextPath}/balanceType/loadBalanceTypeSelect.action',
				  	valueField : 'balanceTypeId',
				  	textField : 'balanceTypeName',
				  	value : origBalanceTypeId
			  });
		});
	</script>
	<div align="center">
		<form id="balance_transfer_view_form">
			<table style="padding: 5px;margin: 5px">
				<tr>
					<td>源余额账本标识：</td>
					<td><input id="origAcctBalanceId" name="acctBalanceId" value="${param.acctBalanceId }" data-options="editable:false" class="easyui-numberbox"/>
						<input id="origBalanceTypeId" name="origBalanceTypeId" value="${param.balanceTypeId }" hidden="true"/>
						<input id="origAcctId" name="origAcctId" value="${param.acctId }" hidden="true">
					</td>
					<td>转账金额(分)：</td>
					<td><input id="transferAmount" name="amount" validtype="length[1,10]" invalidMessage="有效长度1-10位数字" class="easyui-numberbox"/></td>
				</tr>
				<tr align="left">
					<td>&nbsp;</td><td><font id="origAcctBalanceIdHint" color="red">&nbsp;</font></td>
					<td>&nbsp;</td><td><font id="transferAmountHint" color="red">&nbsp;</font></td>
				</tr>
				<tr>
					<td>目的余额类型：</td>
					<td><input id="goalBalanceTypeId" name="balanceTypeId" data-options="editable:false,panelHeight:150,disabled:true"/>
					</td>
					<td>目的账户标识：</td>
					<td><input id="goalAcctId" name="acctId" validtype="length[1,15]" invalidMessage="有效长度1-15位数字" class="easyui-numberbox"/></td>
				</tr>
				<tr align="left">
					<td></td><td><font id="goalBalanceTypeIdHint" color="red">&nbsp;</font></td>
					<td></td><td><font id="goalAcctIdHint" color="red">&nbsp;</font></td>
				</tr>
				<tr>
					<td>目的余额对象类型：</td>
					<td><select id="goalObjectType" name="objectType" class="easyui-combobox" style="width: 150px;" data-options="editable:false,panelHeight:100">
							<option value="1">账户</option>
				 			<option value="2">设备</option>
						</select>
					</td>
					<td>目的余额对象：</td>
					<td><input id="goalObjectId" name="objectId" validtype="length[1,15]" invalidMessage="有效长度1-15位字符"  class="easyui-textbox"/></td>
				</tr>
				<tr align="left">
					<td></td><td><font id="goalObjectTypeHint" color="red">&nbsp;</font></td>
					<td></td><td><font id="goalObjectIdHint" color="red">&nbsp;</font></td>
				</tr>
				<tr>
					<td colspan="4" align="center"><a href="#" class="easyui-linkbutton" onclick="form_submit_transfer();" style="width:65px">转账</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
