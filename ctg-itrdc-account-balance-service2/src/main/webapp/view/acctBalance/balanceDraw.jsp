<%@ page language="java" pageEncoding="utf-8"%>

<body>
	<script type="text/javascript">
				
		/*余额支取表单递交*/
		function form_submit_draw(){
			//参数校验
			if(check()){
				return;
			}
			
			$.ajax({
				async : false,
				type : "post",
				url : "${pageContext.request.contextPath}/acctBalance/balanceDraw.action",
				cache : false,
				dataType : "json",
				data:$('#balance_draw_view').serialize(),  
		        success:function(data){
		        	$.messager.alert("提示", data);
		        }
			});
		}
		
		/*校验支取条件*/
		function check(){
			var acctId = $("#acctId").val();
			var objectType = $("#objectType").val();
			var drawAmount = $("#drawAmount").val();
			var requestId = $("#requestId").val();
			var objectId = $("#objectId").val();
			var flag = false;
			if(acctId == null || $.trim(acctId) == "" || acctId==0){
				$("#acctId_warn").html("请输入账户标识！");
				flag = true;
			}else{
				$("#acctId_warn").html("");
			}
			if(objectType == null || $.trim(objectType) == "" || objectType==0){
				$("#objectType_warn").html("请输入余额对象类型！");
				flag = true;
			}else{
				$("#objectType_warn").html("");
			}
			if(drawAmount == null || $.trim(drawAmount) == "" || drawAmount==0){
				$("#drawAmount_warn").html("请输入支取金额！");
				flag = true;
			}else{
				$("#drawAmount_warn").html("");
			}
			if(requestId == null || $.trim(requestId) == "" || requestId==0){
				$("#requestId_warn").html("请输入流水号！");
				flag = true;
			}else{
				$("#requestId_warn").html("");
			}
			if(objectId == null || $.trim(objectId) == "" || objectId==0){
				$("#objectId_warn").html("请输入余额对象标识！");
				flag = true;
			}else{
				$("#objectId_warn").html("");
			}
			return flag;
		}
	</script>
    <div align="center">
    	<form id="balance_draw_view">
    		<table style="padding: 10px 10px 10px 10px;">
    			<tr>
    				<td>账户标识：</td>
    				<td><input id="acctId" name="acctId" value="" class="easyui-numberbox"/>&nbsp;</td>
    				<td>余额对象类型：</td>
    				<td>
						<select id="objectType" name="objectType" class="easyui-combobox" style="width: 150px;" data-options="editable:false">
							<option value="1">账户</option>
				 			<option value="2">设备</option>
						</select>
					</td>
    			</tr>
    			<tr><td></td><td>&nbsp;<font id="acctId_warn" color="red"></font></td>
    				<td></td><td>&nbsp;<font id="objectType_warn" color="red"></font></td>
    			</tr>
    			<tr>
    				<td>支取金额：</td>
    				<td><input id="drawAmount" name="drawAmount" value="" class="easyui-numberbox"/>&nbsp;</td>
    				<td>余额对象标识：</td>
    				<td><input id="objectId" name="objectId" value="" class="easyui-textbox"/></td>
    			</tr>
    			<tr><td></td><td>&nbsp;<font id="drawAmount_warn" color="red"></font></td>
    				<td></td><td>&nbsp;<font id="objectId_warn" color="red"></font></td>
    			</tr>
    			<tr>
    				<td>流&nbsp;水&nbsp;号：</td>
    				<td colspan="3"><input id="requestId" name="requestId" value="" class="easyui-numberbox"/></td>
    			</tr>
    			<tr><td></td><td colspan="3">&nbsp;<font id="requestId_warn" color="red"></font></td></tr>
    			<tr align="center">
    				<td colspan="2"><a href="#" class="easyui-linkbutton" onclick="form_submit_draw();" style="width:65px">支取</a></td>
    			</tr>
    			
    		</table>
    	</form>
    </div>
</body>