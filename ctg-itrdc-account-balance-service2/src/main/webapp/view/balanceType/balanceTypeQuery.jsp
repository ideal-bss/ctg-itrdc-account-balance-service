<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
 <script type="text/javascript">
 function submit_form(){
 	$.ajax({  
        async:false,  
        type:"POST",  
        url:'${pageContext.request.contextPath}/balanceType/balanceTypeQuery.action',  
        dataType:"json",  
        cache: false,
        data:$('#view_balanceType_balanceTypeQuery_form').serialize(),  
        success:function(data){
        	$('#view_balanceType_balanceTypeQuery_result').datagrid({
    				striped : true,
    				height:200,
    				singleSelect : true,
    				fitColumns : true,
    				loadMsg : '数据加载中请稍后……',
    				rownumbers : true,
    				columns : [ [
						{
							field : 'priority',
							title : '余额类型优先级',
							align : 'left'
						},{
							field : 'spePaymentId',
							title : '专款专用标识',
							align : 'left'
						},{
							field : 'measureMethodId',
							title : '度量方法标识',
							align : 'left'
						},{
							field : 'balanceTypeName',
							title : '余额类型名称',
							align : 'left'
						},
						{
							field : 'allowDraw',
							title : '允许提取标志',
							align : 'left'
						},{
							field : 'invOffer',
							title : '提供发票标志',
							align : 'left'
						},{
							field : 'ifEarning',
							title : '是否抵收入',
							align : 'left'
						},{
							field : 'ifPayold',
							title : '是否抵旧欠',
							align : 'left'
						},{
							field : 'ifSaveback',
							title : '是否滚存',
							align : 'left'
						},{
							field : 'ifPrincipal',
							title : '是否本金',
							align : 'left'
						},{
							field : 'statusCd',
							title : '状态',
							align : 'left'
						},{
							field : 'statusDate',
							title : '状态时间',
							align : 'left'
						}
					] ]
				}); 
        }
    }); 
 }
 </script>
<div> 
		<form id="view_balanceType_balanceTypeQuery_form" >
		<table style="padding: 10px;">
			<tr>
				<td width="10%">余额类型标识:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-textbox" >
					</td>
				<td width="10%">&nbsp;余额类型优先级:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-combo" >
				</td>
				<td width="10%">&nbsp;专款专用标识:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-textbox" >
				</td>
			</tr>
			<tr>
				<td width="10%">余额类型名称:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-textbox" >
				</td>
				<td width="10%">状态:</td>
				<td width="20%">
					<input id="" name="" value="" class="easyui-combo" >
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" onclick="submit_form('');" style="width:65px">查询</a></td>
				<!-- <td><a href="#" class="easyui-linkbutton" onclick="balanceTypeAddGo('');" style="width:65px">新增</a></td> -->
			</tr>
		</table>
		</form>
		<div id="view_balanceType_balanceTypeQuery_result" ></div>
		</div>
</body>
</html>