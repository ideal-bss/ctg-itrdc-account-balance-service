<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
<script type="text/javascript">
$(function(){
$('#effDate').datebox({  
		formatter: function(date){ 
			return date.getFullYear()+'-'+((date.getMonth()+1) < 10 ? ("0" + (date.getMonth()+1)) : (date.getMonth()+1))+'-'+((date.getDate()) < 10 ? ("0" + (date.getDate())) : (date.getDate())); 
		}
 	}); 
 	$('#expDate').datebox({  
		formatter: function(date){ 
			return date.getFullYear()+'-'+((date.getMonth()+1) < 10 ? ("0" + (date.getMonth()+1)) : (date.getMonth()+1))+'-'+((date.getDate()) < 10 ? ("0" + (date.getDate())) : (date.getDate())); 
		}
 	});
 	$('#statusDate').datebox({  
		formatter: function(date){ 
			return date.getFullYear()+'-'+((date.getMonth()+1) < 10 ? ("0" + (date.getMonth()+1)) : (date.getMonth()+1))+'-'+((date.getDate()) < 10 ? ("0" + (date.getDate())) : (date.getDate())); 
		}
 	});
});

 function submit_form(){
 	$.ajax({  
        async:false,  
        type:"POST",  
        url:'${pageContext.request.contextPath}/acctBalance/balanceQueryGo.action',  
        dataType:"json",  
        cache: false,
        data:$('#view_acctBalance_balanceQuery_form').serialize(),  
        success:function(datas){
        	$('#view_acctBalance_balanceQuery_result').datagrid({
    				striped : true,
    				height:200,
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
							field : 'PAYMENT_RULE_ID',
							title : '支付规则标识',
							align : 'left',
							width : 300
						},{
							field : 'SUB_ACCT_ID',
							title : '拥有子账户标识',
							align : 'left',
							width : 300
						},
						{
							field : 'ACCT_ID',
							title : '帐户标识',
							align : 'left',
							width : 300
						},{
							field : 'BALANCE',
							title : '余额',
							align : 'left',
							width : 300
						},{
							field : 'STATUS_CD',
							title : '状态',
							align : 'left',
							width : 300
						},{
							field : 'REMARK',
							title : '备注',
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
            				PAYMENT_RULE_ID: data.paymentRuleId,
            				SUB_ACCT_ID: data.subAcctId,
            				ACCT_ID: data.acctId,
            				BALANCE: data.balance,
            				STATUS_CD: data.statusCd,
            				REMARK: data.remark
                    });
            	}
        	}
        }
    }); 
 }

</script>
<div> 
		<form id="view_acctBalance_balanceQuery_form" >
		<table style="padding: 10px;">
			<tr>
				<td width="10%">设备号:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-textbox" >
				</td>
				<td width="10%">金额:</td>
				<td width="20%">
				<input id="" name="" value="" class="easyui-combo" >
				</td>
				<td width="10%">余额类型:</td>
				<td width="20%">
				<input id="" name="" value="" class="easyui-combo" >
				</td>
			</tr>
			<tr>
				<td width="10%">备注:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-textbox" >
				</td>
				<td width="10%"></td>
				<td width="20%">
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