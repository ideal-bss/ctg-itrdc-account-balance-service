<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <body>
 <script type="text/javascript">
 function add_form(){
    if(!checkParas()){
 		return false;
 	}
 	$.ajax({
        async:false,  
        type:"POST",  
        url:'${pageContext.request.contextPath}/balanceType/balanceTypeAdd.action',  
        dataType:"json",  
        cache: false,
        data:$('#view_balanceType_balanceTypeAdd_form').serialize(),  
        success:function(data){
        	$.messager.alert("提示", data);
        }
    }); 
 }
 
 function checkParas(){
 	var flag = false;
 	var balanceTypeName = $("#balanceTypeName_add"); 
 	var balanceTypeNameClass = $(".balanceTypeNameClass");
 	if(balanceTypeName.val() == ''){
 		balanceTypeNameClass.html('<font color="red">余额类型名称不能为空！</font>');
 		flag = false;
 	}else{
 		balanceTypeNameClass.html('');
 		flag = true;
 	}
 	return flag;
 }
 
 
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
 
	 
 </script>
<div align="center"> 
		<form id="view_balanceType_balanceTypeAdd_form" >
		<table style="padding: 10px 10px 10px 10px;">
			<tr>
				<td width="20%">余额类型优先级:</td>
				<td width="30%">
					<!-- <input id="priority" name="priority" value="" class="easyui-combo" > -->
					<select id="priority" name="priority" class="easyui-combobox" style="width: 150px;" data-options="editable:false" >
						<option value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
					</select>
				</td>
				<td width="20%">专款专用标识:</td>
				<td width="30%">
					<input id="spePaymentId" name="spePaymentId" value="" class="easyui-textbox" style="width: 150px;">
				</td>
			</tr>
			<tr>
				<td width="20%">余额类型名称:</td>
				<td width="30%">
					<input id="balanceTypeName_add" name="balanceTypeName" value="" class="easyui-textbox" style="width: 150px;">
					<span class="balanceTypeNameClass">&nbsp;</span>
				</td>
				<td width="20%">允许提取标志:</td>
				<td width="30%">
					<!-- <input id="allowDraw" name="allowDraw" value="" class="easyui-textbox" > -->
					<select id="allowDraw" name="allowDraw" class="easyui-combobox" style="width: 150px;" data-options="editable:false" >
						<option value="Y">是</option>
						<option value="N">否</option>
					</select>
				</td>
				
			</tr>
			<tr>
				<td width="20%">是否抵收入:</td>
				<td width="30%">
					<!-- <input id="ifEarning" name="ifEarning" value="" class="easyui-combo" > -->
					<select id="ifEarning" name="ifEarning" class="easyui-combobox" style="width: 150px;" data-options="editable:false" >
						<option value="Y">是</option>
						<option value="N">否</option>
					</select>
				</td>
				<td width="20%">是否抵旧欠:</td>
				<td width="30%">
					<!-- <input id="ifPayOld" name="ifPayOld" value="" class="easyui-combo" > -->
					<select id="ifPayOld" name="ifPayOld" class="easyui-combobox" style="width: 150px;" data-options="editable:false" >
						<option value="Y">是</option>
						<option value="N">否</option>
					</select>
				</td>
				
			</tr>
			<tr>
				<td width="20%">状态:</td>
				<td width="30%">
					<!-- <input id="statusCd" name="statusCd" value="" class="easyui-combo" > -->
					<select id="statusCd" name="statusCd" class="easyui-combobox" style="width: 150px;" data-options="editable:false" >
						<option value="Y">有效</option>
						<option value="N">无效</option>
					</select>
				</td>
				<td width="20%">状态时间:</td>
				<td width="30%">
					<input id="statusDate_add" name="statusDate" data-options="formatter:myformatter,parser:myparser,editable:false" value="" class="easyui-datebox" style="width: 150px;">
					<span class="statusDateClass"></span>
				</td>
			</tr>
			<tr>
				<td width="20%">提供发票标志:</td>
				<td width="30%">
					<!-- <input id="invOffer" name="invOffer" value="" class="easyui-combo" > -->
					<select id="invOffer" name="invOffer" class="easyui-combobox" style="width: 150px;" data-options="editable:false" >
						<option value="Y">是</option>
						<option value="N">否</option>
					</select>
				</td>
				<td width="20%">是否滚存:</td>
				<td width="30%">
					<!-- <input id="ifSaveBack" name="ifSaveBack" value="" class="easyui-combo" > -->
					<select id="ifSaveBack" name="ifSaveBack" class="easyui-combobox" style="width: 150px;" data-options="editable:false" >
						<option value="Y">是</option>
						<option value="N">否</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="20%">是否本金:</td>
				<td width="30%">
					<!-- <input id="ifPrincipal" name="ifPrincipal" value="" class="easyui-combo" > -->
					<select id="ifPrincipal" name="ifPrincipal" class="easyui-combobox" style="width: 150px;" data-options="editable:false" >
						<option value="Y">是</option>
						<option value="N">否</option>
					</select>
				</td>
				<td>&nbsp;</td><td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4"><a href="#" class="easyui-linkbutton" onclick="add_form('');" style="width:65px">新增</a></td>
			</tr>
		</table>
		</form>
		</div>
</body>