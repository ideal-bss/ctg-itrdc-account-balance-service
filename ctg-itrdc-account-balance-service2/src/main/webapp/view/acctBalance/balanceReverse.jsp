<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<body>
	<script type="text/javascript">
		function submit_form_reverse(){
			if (checkParams()) {
				return;
			}
		
			$.ajax({
				async:false,
				type:"post",
				url:"${pageContext.request.contextPath}/acctBalance/balanceReverse.action",
				cache:false,
				dataType:"json",
				data:$('#balance_reverse_form').serialize(),
				success:function(datas){
					$.messager.alert('提示',datas);
				}
			});
		}
		
		function checkParams(){
			var flag = false;
			var operIncomeId = $("#operIncomeId").val();
			if (operIncomeId==null || $.trim(operIncomeId)=="" || operIncomeId==0) {
				$("#operIncomeIdHint").html("源账户标识不能为空！");
				flag = true;
			} else {
				$("#operIncomeIdHint").html("&nbsp;");
			}
			return flag;
		}
	</script>

  <div align="center" style="width: 1000px;height: 800px;">
  		<form id="balance_reverse_form">
  			<table>
  				<tr><td>余额来源操作流水：</td>
  					<td><input id="operIncomeId" name="operIncomeId" class="easyui-numberbox"/></td>
  				</tr>
  				<tr>
  					<td></td><td><font id="operIncomeIdHint" color="red">&nbsp;</font></td>
  				</tr>
  				<tr>
  					<td></td><td><a href="#" class="easyui-linkbutton" style="width: 65px" onclick="javaScript:submit_form_reverse();">提交</a></td>
  				</tr>
  			</table>
  		</form>
  </div>
</body>
