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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/themes/demo.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
	
</head>

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
        url:"<%=rootPath%>/acctBalance/balanceQueryGo.action",  
        dataType:"json",  
        cache: false,
        data:$('#form_select').serialize(),  
        success:function(datas){
        	$('#balanceQueryResult').datagrid({
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
							title : '余额(分)',
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
				$('#balanceQueryResult').datagrid('loadData', { total: 0, rows: [] });
				if(datas){
        		for(var i=0; i<datas.length; i++){
            		var data = datas[i];
            		$('#balanceQueryResult').datagrid('appendRow', {
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
		<form id="form_select" >
		<table style="padding: 10px;">
			<tr>
				<td width="10%">设备号:</td>
				<td width="20%">
					<input id="deviceNo" name="deviceNo" value="" class="easyui-textbox" >
				</td>
				<td width="10%">账户标识:</td>
				<td width="20%">
				<input id="acctId" name="acctId" value="" class="easyui-combo" >
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" onclick="submit_form('');" style="width:65px">查询</a></td>
			</tr>
		</table>
		
		
		
		
		<!-- <table style="padding: 10px;">
			<tr>
				<td width="10%">余额帐本标识</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-textbox" >
					</td>
				<td width="10%">&nbsp;余额类型标识:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-combo" >
				</td>
				<td width="10%">&nbsp;账户标识:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-textbox" >
				</td>
			</tr>
			<tr>
				<td width="10%">拥有子账户标识</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-textbox" >
					</td>
				<td width="10%">&nbsp;状态:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-combo" >
				</td>
				<td width="10%">&nbsp;限额类型:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-combo" >
				</td>
			</tr>
			<tr>
				<td width="10%">生效时间</td>
				<td width="20%">
					<input id="effDate" name="effDate" value="" class="easyui-datebox" >
					</td>
				<td width="10%">&nbsp;失效时间:</td>
				<td width="20%">
					<input id="expDate" name="" value="" class="easyui-datebox" >
				</td>
				<td width="10%">&nbsp;状态时间:</td>
				<td width="20%">
					<input id="statusDate" name="statusDate" value="" class="easyui-datebox" >
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" onclick="submit_form('');" style="width:65px">查询</a></td>
			</tr>
		</table> -->
		</form>
		<div id="balanceQueryResult" ></div>
		</div>
</body>
</html>