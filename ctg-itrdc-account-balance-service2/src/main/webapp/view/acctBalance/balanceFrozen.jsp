<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<body>
  <script type="text/javascript">
  	function balanceFrozenForm(){
	  	if(!checkParas()){
	 		return false;
	 	}
	  	$.ajax({
		        async:false,  
		        type:"POST",  
		        url:'${pageContext.request.contextPath}/acctBalance/balanceFrozen.action',  
		        dataType:"json",  
		        cache: false,
		        data:$('#balance_frozen_form_id').serialize(),  
		        success:function(data){
		        	$.messager.alert("提示", data);
		        }
		 }); 
	 }
	 
	 function checkParas(){
	 	var flag = false;
	 	var acctId = $("#subAcctId").val(); 
	 	var acctIdHint = $("#subAcctIdHint");
	 	var acctBalanceId = $("#acctBalanceId").val(); 
	 	var acctBalanceIdHint = $("#acctBalanceIdHint");
	 	var frozenAmount = $("#frozenAmount").val(); 
	 	var frozenAmountHint = $("#frozenAmountHint");
	 	if(acctId == null || acctId == ''){
	 		acctIdHint.html('账户标识不能为空！');
	 		flag = false;
	 	}else{
	 		acctIdHint.html('&nbsp;');
	 		flag = true;
	 	}
	 	if(acctBalanceId == null || acctBalanceId == ''){
	 		acctBalanceIdHint.html('余额账本标识不能为空！');
	 		flag = false;
	 	}else{
	 		acctBalanceIdHint.html('&nbsp;');
	 		flag = true;
	 	}
	 	if(frozenAmount == null || frozenAmount == ''){
	 		frozenAmountHint.html('冻结金额不能为空！');
	 		flag = false;
	 	}else{
	 		frozenAmountHint.html('&nbsp;');
	 		flag = true;
	 	}
	 	return flag;
	 }
  </script>
  <div align="center">
  	<form id="balance_frozen_form_id">
  		<table>
  			<tr><td>账户标识：</td><td><input name="subAcctId" id="subAcctId" value="${param.acctId }" class="easyui-numberbox" data-options="editable:false"/></td>
  				<td>余额账本标识：</td><td><input name="acctBalanceId" id="acctBalanceId" value="${param.acctBalanceId }" class="easyui-numberbox" data-options="editable:false"/></td>
  			</tr>
  			<tr><td>&nbsp;</td><td><font color="red" id="subAcctIdHint"></font></td>
  				<td>&nbsp;</td><td><font color="red" id="acctBalanceIdHint"></font></td>
  			</tr>
  			<tr><td>冻结金额：</td><td><input name="frozenAmount" id="frozenAmount" class="easyui-numberbox"/></td>
  				<td colspan="2">&nbsp;</td>
  			</tr>
  			<tr><td>&nbsp;</td><td><font color="red" id="frozenAmountHint"></font></td>
  				<td colspan="2">&nbsp;</td>
  			</tr>
  			<tr align="center"><td colspan="4"><a href="#" class="easyui-linkbutton" style="width: 65px" onclick="javascript:balanceFrozenForm();">确定</a></td>
  			</tr>
  		</table>
  	</form>
  </div>
</body>