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
			var drawAmount = $("#drawAmount").val();
			var flag = false;
			if(drawAmount == null || $.trim(drawAmount) == "" || drawAmount==0){
				$("#drawAmount_warn").html("请输入支取金额！");
				flag = true;
			}else if(drawAmount.length > 10){
				$("#drawAmount_warn").html("支取金额不能超过10位！");
				flag = true;
			}else if(isNaN(drawAmount)){
				$("#drawAmount_warn").html("支取金额只能是数字！");
				flag = true;
			}else{
				$("#drawAmount_warn").html("");
			}
			return flag;
		}
		
	</script>
	
    <div align="center">
    	<form id="balance_draw_view">
    		<table style="padding: 10px 10px 10px 10px;">
    			<tr>
    				<th>账&nbsp;&nbsp;户&nbsp;&nbsp;标&nbsp;&nbsp;识&nbsp;&nbsp;：</th>
    				<td><input id="acctId" name="acctId" data-options="editable:false" value="${param.acctId }" class="easyui-numberbox"/>&nbsp;</td>
    				<!-- <td>余额对象类型：</td>
    				<td>
						<select id="objectType" name="objectType" class="easyui-combobox" style="width: 150px;" data-options="editable:false,panelHeight:80">
							<option value="1">账户</option>
				 			<option value="2">设备</option>
						</select>
					</td> -->
					<th>余额账本标识：</th>
					<td>
						<input id="acctBalanceIdArray" name="acctBalanceIdArray" data-options="editable:false,multiline:true"
							style="width:150px;height:50px;" value="${param.acctBalanceId }" class="easyui-textbox"/>
					</td>
    			</tr>
    			<tr><td></td><td>&nbsp;<font id="acctId_warn" color="red"></font></td>
    				<td></td><td>&nbsp;<font id="acctBalId_warn" color="red"></font></td>
    			</tr>
    			<tr>
    				<th>支取金额（分）：</th>
    				<td><input id="drawAmount" name="drawAmount" validtype="length[1,10]" invalidMessage="有效长度1-10位数字" value="" class="easyui-numberbox"/>&nbsp;</td>
    				<th>余额对象标识：</th>
    				<td><input id="objectId" name="objectId" data-options="editable:false" value="${param.objectId }" class="easyui-textbox"/></td>
    			</tr>
    			<tr><td></td><td>&nbsp;<font id="drawAmount_warn" color="red"></font></td>
    				<td></td><td>&nbsp;<font id="objectId_warn" color="red"></font></td>
    			</tr>
    			<tr align="center">
    				<td colspan="4"><a href="#" class="easyui-linkbutton" onclick="form_submit_draw();" style="width:65px">支取</a></td>
    			</tr>
    			
    		</table>
    	</form>
    </div>
</body>