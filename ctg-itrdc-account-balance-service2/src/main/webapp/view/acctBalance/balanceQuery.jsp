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

 function submit_balanceQuery_form(){
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
    				fit:true,
    				singleSelect : true,
    				fitColumns : true,
    				loadMsg : '数据加载中请稍后……',
    				rownumbers : false,
    				pagination: true, 
    				toolbar: ["-", {
					            id: 'view_acctBalance_balanceQuery_update',
					            text: '抵扣',
					            iconCls: 'icon-edit',
					            handler: function () {
					            	var row = $('#view_acctBalance_balanceQuery_result').datagrid('getSelected'); 
					            	alert(row.ACCT_BALANCE_ID);
							        if(row){
							        	$.ajax({  
									        async:false,  
									        type:"POST",  
									        url:'${pageContext.request.contextPath}/acctBalance/deducBalance.action',  
									        dataType:"json",  
									        cache: false,
									        success:function(datas){
									        
									        }
								        });
							            
							        }
					            }
					        }, "-", {
					            id: '',
					            text: '删除',
					            iconCls: 'icon-remove',
					            handler: function () { }
					        }, "-", {
					            id: '',
					            text: '新增',
					            iconCls: 'icon-add',
					            handler: function () { 
					            	submit_Add();
					            
					            }
					        }, "-"],
    				columns : [ [
	    				{
	    					field:'checked',
	    					width:60,
	    					formatter:function(value,row,index){
								if(row.checked){
									return '<input type="checkbox" name="DataGridCheckbox" checked="checked">';
								}
								else{
									return '<input type="checkbox" name="DataGridCheckbox">';
								}
							}
						},
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
							title : '余额(元)',
							align : 'left',
							width : 300,
							formatter:function(val,rec){ 
									var balanceVal=val/100;
									if(balanceVal.toString().indexOf(".")>-1){
										if (balanceVal.toString().substring(balanceVal+"".indexOf("."),balanceVal.toString().length)==1){
											return balanceVal+"0";
										}else {
											return balanceVal+"";
										}
									}else {
										return balanceVal+".00";
									}
								} 
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
        	
        	var p = $('#view_acctBalance_balanceQuery_result').datagrid('getPager'); 
		    $(p).pagination({ 
		        pageSize: 10,//每页显示的记录条数，默认为10 
		        pageList: [5,10,15],//可以设置每页记录条数的列表 
		        beforePageText: '第',//页数文本框前显示的汉字 
		        afterPageText: '页    共 {pages} 页', 
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
		    }); 
        }
    }); 
 }

function submit_Add(){
		$('#view_acctBalance_balanceQuery_Add').dialog({  
            title:"余额存入",  
            width:1000,  
            height:400,  
            href:'${pageContext.request.contextPath}/acctBalance/balanceAddGo.action',  
            max:false,  
            min:false,
            lock:true
        });
}
</script>
<div> 
	<div style="height: 20%">
		<form id="view_acctBalance_balanceQuery_form" >
		<table style="padding: 10px;">
			<tr>
				<td width="10%">
				<input type="radio" name="radio" id="radio" value="0"/>
          设备号
          <input type="radio" name="radio" id="radio1" value="1"/>
          合同号
          &nbsp;&nbsp;<input id="acct_sub_id" name="acct_sub_id" value="" class="easyui-textbox" >
				</td>
				<td width="10%">
					<a href="#" class="easyui-linkbutton" onclick="submit_balanceQuery_form('');" style="width:65px" data-options="iconCls:'icon-search'">查询</a>
				</td>
			</tr>
		</table>
		</form>
		</div>
		<div id="view_acctBalance_balanceQuery_result" ></div>
		<div id="view_acctBalance_balanceQuery_Add" ></div>
		</div>
</body>
</html>