<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
<script type="text/javascript">
 function submit_form(){
 	$.ajax({
        async:false,  
        type:"POST",  
        url:'${pageContext.request.contextPath}/acctBalance/balanceQuery.action',  
        dataType:"json",  
        cache: false,
        data:$('#view_acctBalance_balanceQuery_form').serialize(),
        success:function(datas){
        	if(datas[0].errorInfo != null){
        		$.messager.alert('提示框',datas[0].errorInfo,'info');
        		datas = null;
        	}
        	
        	$('#view_acctBalance_balanceQuery_result').datagrid({
    				striped : true,
    				height:400,
    				singleSelect : true,
    				fitColumns : true,
    				loadMsg : '数据加载中请稍后……',
    				rownumbers : true,
    				columns : [ [
						{
							field : 'ACCT_BALANCE_ID',
							title : '余额帐本标识',
							align : 'left',
							width : 300
						},{
							field : 'BALANCE_TYPE_ID',
							title : '余额类型标识',
							align : 'left',
							width : 300
						},{
							field : 'BALANCE_TYPE_NAME',
							title : '余额类型名称',
							align : 'left',
							width : 300
						},{
							field : 'EFF_DATE',
							title : '生效时间',
							align : 'left',
							width : 300
						},
						{
							field : 'EXP_DATE',
							title : '失效时间',
							align : 'left',
							width : 300
						},{
							field : 'BALANCE',
							title : '单个账本余额',
							align : 'left',
							width : 300
						},{
							field : 'OBJECT_ID',
							title : '余额对象标识',
							align : 'left',
							width : 300
						},{
							field : 'FREEZE_BALANCE',
							title : '冻结余额',
							align : 'left',
							width : 300
						}
					] ]
				}); 
				$('#view_acctBalance_balanceQuery_result').datagrid('loadData', { total: 0, rows: [] });
				if(datas){
	        		for(var i=0; i<datas.length; i++){
	            		var data = datas[i];
	            		$('#view_acctBalance_balanceQuery_result').datagrid('appendRow', {
	            				ACCT_BALANCE_ID: data.acctBalanceId,
	            				BALANCE_TYPE_ID: data.balanceTypeId ,
	            				BALANCE_TYPE_NAME: data.balanceTypeName,
	            				EFF_DATE: data.effDate,
	            				EXP_DATE: data.expDate,
	            				BALANCE: data.balance,
	            				OBJECT_ID: data.objectId,
	            				FREEZE_BALANCE: data.freezeBalance
	                    });
	            	}
        		}
        }
    }); 
 }

</script>
<div align="center"> 
		<form id="view_acctBalance_balanceQuery_form" >
		<table style="padding: 10px;">
			<tr>
				<td width="10%">余额对象:</td>
				<td width="20%">
					<input id="objectId" name="objectId" value="" class="easyui-textbox" >
				</td>
				<td width="10%">余额类型:</td>
				<td width="20%">
				<!-- 
				<input id="balanceTypeId" name="balanceTypeId" value="" class="easyui-combo" data-options="valueField:'id',textField:'text'">
				 -->
				 <select id="balanceTypeId" name="balanceTypeId" class="easyui-combobox" style="width: 150px">
				 	<option value="0">全部</option>
				 	<option value="1">普通预存款</option>
				 	<option value="2">溢收款</option>
				 	<option value="3">赠送</option>
				 </select>
				</td>
			</tr>
			<tr>
				<td width="10%">余额对象类型:</td>
				<td width="20%">
					<!-- 
					<input id="ObjectIdType" name="ObjectIdType" value="" class="easyui-combo" >
					 -->
				 <select id="ObjectIdType" name="ObjectIdType" class="easyui-combobox" style="width: 150px">
				 	<option value="1">账户</option>
				 	<option value="2">设备</option>
				 </select>
				</td>
				<td width="10%"></td>
				<td width="20%">
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" onclick="submit_form('');" style="width:65px">查询</a></td>
			</tr>
		</table>
		</form>
		<div id="view_acctBalance_balanceQuery_result" ></div>
		</div>
</body>
</html>