<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
 <script type="text/javascript">
function myformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function myparser(s){
	if (!s) return new Date();
	var ss = (s.split('-')); 
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	} else {
		return new Date();
	}
}	
 $(function () {
 	$('#objectType').combobox( {
		valueField : 'id',
		textField : 'text',
		data : [ {
			"id" : 1,
			"text" : "1"
		} ]
	}); 
	$('#shareRuleTypeId').combobox( {
		valueField : 'id',
		textField : 'text',
		data : [ {
			"id" : 1,
			"text" : "1"
		} ]
	}); 
	$('#shareRuleTypePri').combobox( {
		valueField : 'id',
		textField : 'text',
		data : [ {
			"id" : 1,
			"text" : "1"
		} ]
	}); 
	$('#shareRuleTypeId').combobox( {
		valueField : 'id',
		textField : 'text',
		data : [ {
			"id" : 1,
			"text" : "1"
		} ]
	}); 
 	$('#balanceTypeId').combobox( {
		valueField : 'id',
		textField : 'text',
		data : [ {
			"id" : 1,
			"text" : "1"
		} ]
	}); 
	$('#statusCd').combobox( {
		valueField : 'id',
		textField : 'text',
		data : [ {
			"id" : 1,
			"text" : "1"
		} ]
	}); 
	$('#cycleType').combobox( {
		valueField : 'id',
		textField : 'text',
		data : [ {
			"id" : 1,
			"text" : "1"
		} ]
	}); 
 });
 
 function submit_balanceAdd_form(){
 	$.ajax({  
        async:false,  
        type:"POST",  
        url:'${pageContext.request.contextPath}/acctBalance/balanceAdd.action',  
        dataType:"json",  
        cache: false,
        data:$('#view_acctBalance_balanceAdd_form').serialize(),  
        success:function(data){
        	$.messager.alert("提示", "存入成功!");
        	$('#view_acctBalance_balanceQuery_Add').dialog({closed:true});
        //	$('#view_acctBalance_balanceQuery_result').datagrid('reload');  
        }
    }); 
 }
 </script>
<div> 
		<form id="view_acctBalance_balanceAdd_form" >
		<table style="padding: 10px 10px 10px 10px;">
			<tr>
				
				<td width="10%">&nbsp;余额对象标识:</td>
				<td width="20%">
					<input id="objectId" name="objectId"  class="easyui-textbox" >
				</td>
				<td width="10%">&nbsp;余额对象类型:</td>
				<td width="20%">
					<input id="objectType" name="objectType" class="easyui-combo" >
				</td>
				
				<td width="10%">&nbsp;共享规则类型:</td>
				<td width="20%">
					<input id="shareRuleTypeId" name="shareRuleTypeId" class="easyui-combo" >
				</td>
			</tr>
			<tr>
				
				<td width="10%">&nbsp;共享规则优先级:</td>
				<td width="20%">
					<input id="shareRuleTypePri" name="shareRuleTypePri"  class="easyui-combo" >
				</td>
				<td width="10%">&nbsp;扣费上限:</td>
				<td width="20%">
					<input id="upperAmount" name="upperAmount" class="easyui-textbox" >
				</td>
				
				<td width="10%">&nbsp;扣费下限:</td>
				<td width="20%">
					<input id="lowerAmount" name="lowerAmount" class="easyui-textbox" >
				</td>
			</tr>
			
			<tr>
				
				<td width="10%">&nbsp;余额类型标识:</td>
				<td width="20%">
					<input id="balanceTypeId" name="balanceTypeId"  class="easyui-combo" >
				</td>
				<td width="10%">&nbsp;支付规则标识:</td>
				<td width="20%">
					<input id="paymentRuleId" name="paymentRuleId" class="easyui-textbox" >
				</td>
			</tr>
			<tr>
				<td width="10%">拥有子帐户标识:</td>
				<td width="20%">
					<input id="subAcctId" name="subAcctId" class="easyui-textbox" >
				</td>
				<td width="10%">&nbsp;账户标识:</td>
				<td width="20%">
					<input id="acctId" name="acctId" class="easyui-textbox" >
				</td>
				
			</tr>
			<tr>
				<td width="10%">&nbsp;生效时间:</td>
				<td width="20%">
					<input id="effDate" name="effDate" value="new Date()" data-options="formatter:myformatter,parser:myparser" class="easyui-datebox">
				</td>
				<td width="10%">&nbsp;失效时间:</td>
				<td width="20%">
					<input id="expDate" name="expDate" value="2099-01-01" data-options="formatter:myformatter,parser:myparser" class="easyui-datebox">
				</td>
			</tr> 
			<tr>
				<td width="10%">余额</td>
				<td width="20%">
					<input id="balance" name="balance" class="easyui-textbox" >
					</td>
				<td width="10%">&nbsp;预留余额:</td>
				<td width="20%">
					<input id="reserveBalance" name="reserveBalance" class="easyui-textbox" >
				</td>
				<td width="10%">&nbsp;扣费上限金额:</td>
				<td width="20%">
					<input id="cycleUpper" name="cycleUpper" class="easyui-textbox" >
				</td>
			</tr>
			<tr>
				<td width="10%">扣费下限金额</td>
				<td width="20%">
					<input id="cycleLower" name="cycleLower" class="easyui-textbox" >
					</td>
				
			</tr>
			<tr>
				<td width="10%">状态</td>
				<td width="20%">
					<input id="statusCd" name="statusCd" class="easyui-combo" >
					</td>
				<td width="10%">&nbsp;状态时间:</td>
				<td width="20%">
					<input id="statusDate" name="statusDate" value="new Date()" data-options="formatter:myformatter,parser:myparser" class="easyui-datebox">
				</td>
				<td width="10%">&nbsp;限额类型:</td>
				<td width="20%">
					<input id="cycleType" name="cycleType" class="easyui-combo" >
				</td>
			</tr>
			<tr>
				<td width="10%">备注</td>
				<td width="20%">
					<input id="remark" name="remark" class="easyui-textbox" >
					</td>
				<td width="10%">&nbsp;需要打印发票的金额:</td>
				<td width="20%">
					<input id="needInvoiceAmount" name="needInvoiceAmount" class="easyui-textbox" >
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" onclick="submit_balanceAdd_form('');" style="width:65px">存入</a></td>
			</tr>
		</table>
		</form>
		</div>
		
		<!-- $('#effDate').datebox({  
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
 	}); -->
</body>
</html>