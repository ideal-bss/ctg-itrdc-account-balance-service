<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String rootPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/themes/demo.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
	
</head>

 <body>
 <script type="text/javascript">
 $(function () {
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
 
 function submit_form(){
 	$.ajax({  
        async:false,  
        type:"POST",  
        url:"<%=rootPath%>/acctBalance/balanceAdd.action",  
        dataType:"json",  
        cache: false,
        data:$('#form_select').serialize(),  
        success:function(data){
        	$.messager.alert("提示", "存入成功!");
        }
    }); 
 }
 </script>
<div> 
		<form id="form_select" >
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
					<input id="effDate" name="effDate" class="easyui-datebox">
				</td>
				<td width="10%">&nbsp;失效时间:</td>
				<td width="20%">
					<input id="expDate" name="expDate" class="easyui-datebox">
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
					<input id="statusDate" name="statusDate" class="easyui-datebox">
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
				<td><a href="#" class="easyui-linkbutton" onclick="submit_form('');" style="width:65px">存入</a></td>
			</tr>
		</table>
		</form>
		</div>
</body>
</html>