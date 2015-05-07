<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <body>
 <script type="text/javascript">
 function submit_form(){
 	$.ajax({  
        async:false,  
        type:"POST",  
        url:'${pageContext.request.contextPath}/acctBalance/balanceAdd.action',  
        dataType:"json",  
        cache: false,
        data:$('#view_balanceType_balanceTypeAdd_form').serialize(),  
        success:function(data){
        	$.messager.alert("提示", "存入成功!");
        }
    }); 
 }
 </script>
<div> 
		<form id="view_balanceType_balanceTypeAdd_form" >
		<table style="padding: 10px 10px 10px 10px;">
			<tr>
				<td width="10%">&nbsp;余额类型优先级:</td>
				<td width="20%">
					<input id="priority" name="priority" value="" class="easyui-combo" >
				</td>
				<td width="10%">&nbsp;专款专用标识:</td>
				<td width="20%">
					<input id="spePaymentId" name="spePaymentId" value="" class="easyui-textbox" >
				</td>
				<td width="10%">&nbsp;度量方法标识:</td>
				<td width="20%">
					<input id="measureMethodId" name="measureMethodId" value="" class="easyui-combo" >
				</td>
			</tr>
			<tr>
				<td width="10%">余额类型名称:</td>
				<td width="20%">
					<input id="balanceTypeName" name="balanceTypeName" value="" class="easyui-textbox" >
				</td>
				<td width="10%">允许提取标志:</td>
				<td width="20%">
					<input id="allowDraw" name="allowDraw" value="" class="easyui-textbox" >
				</td>
				<td width="10%">提供发票标志:</td>
				<td width="20%">
					<input id="invOffer" name="invOffer" value="" class="easyui-combo" >
				</td>
			</tr>
			<tr>
				<td width="10%">是否抵收入:</td>
				<td width="20%">
					<input id="ifEarning" name="ifEarning" value="" class="easyui-combo" >
				</td>
				<td width="10%">是否抵旧欠:</td>
				<td width="20%">
					<input id="ifPayold" name="ifPayold" value="" class="easyui-combo" >
				</td>
				<td width="10%">是否滚存:</td>
				<td width="20%">
					<input id="ifSaveback" name="ifSaveback" value="" class="easyui-combo" >
				</td>
			</tr>
			<tr>
				<td width="10%">是否本金:</td>
				<td width="20%">
					<input id="ifPrincipal" name="ifPrincipal" value="" class="easyui-combo" >
				</td>
				<td width="10%">状态:</td>
				<td width="20%">
					<input id="statusCd" name="statusCd" value="" class="easyui-combo" >
				</td>
				<td width="10%">状态时间:</td>
				<td width="20%">
					<input id="statusDate" name="statusDate" value="" class="easyui-datebox" >
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" onclick="submit_form('');" style="width:65px">新增</a></td>
			</tr>
		</table>
		</form>
		</div>
</body>
</html>