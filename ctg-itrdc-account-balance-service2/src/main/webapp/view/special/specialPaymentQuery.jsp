<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
 <script type="text/javascript">
  function submit_form(){
 	$.ajax({  
        async:false,  
        type:"POST",  
        url:'${pageContext.request.contextPath}/special/specialQuery.action',  
        dataType:"json",  
        cache: false,
        data:$('#view_special_specialPaymentQuery_form').serialize(),  
        success:function(data){
        	$.messager.alert("提示", "查询成功!");
        }
    }); 
 }
 </script>
<div  align="center" > 
		<form id="view_special_specialPaymentQuery_form" >
		<table style="padding: 10px;">
			<tr>
				<td width="10%">专款专用标识:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-textbox" >
					</td>
				<td width="10%">&nbsp;运营商:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-combo" >
				</td>
				<td width="10%">&nbsp;产品标识:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-textbox" >
				</td>
			</tr>
			<tr>
				<td width="10%">帐目组标识:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-textbox" >
					</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" onclick="submit_form('');" style="width:65px">查询</a></td>
			</tr>
		</table>
		</form>
		</div>
</body>
</html>