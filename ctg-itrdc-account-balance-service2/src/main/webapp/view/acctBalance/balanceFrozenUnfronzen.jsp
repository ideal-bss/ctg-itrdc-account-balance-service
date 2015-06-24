<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<body>
	<script type="text/javascript">
		function submit_form_frozen(){
			if (checkParam()) {
				return;
			}
			$.ajax({
				async:false,  
        		type:"POST",  
				url:'${pageContext.request.contextPath}/acctBalance/balanceFrozenQuery.action',
				dataType:"json",
		        cache: false,
		        data:$('#balance_frozen_query_form').serialize(),
		        success:function(datas){
		        	if (datas[0].errorInfo != null) {
						$.messager.alert("提示",datas[0].errorInfo);
						datas = null;
					}
		            
		        	$('#balance_frozen_query_result').datagrid({
		        		striped : true,
	    				height:400,
	    				singleSelect : true,
	    				fitColumns : true,
	    				loadMsg : '数据加载中请稍后……',
	    				rownumbers : true,
	    				checkOnSelect : true,
	    				selectOnCheck : true,
	    				singleSelect : false,
	    				columns : [ [
	    					{
	    						field : '',
	    						title : '选择',
	    						checkbox : true,
	    						align : 'left'
	    					},{
	    						field : 'BALANCE_FROZEN_ID',
								title : '余额冻结标识',
								align : 'left',
								width : 300
	    					},{
	    						field : 'FROZEN_AMOUNT',
								title : '冻结金额（元）',
								align : 'left',
								width : 300
	    					}
	    				] ],
	    				toolbar:[
		                {text:"余额冻结",iconCls:"icon-add",handler:function(){
		                    $('#balance_frozen_id').window({
		                    	title : '新增余额类型',
		                    	width : 600,
		                    	height : 400,
		                    	modal : false,
		                    	href : "${pageContext.request.contextPath}/view/acctBalance/balanceFrozen.jsp"
		                    });
		                    
		                }},{text:"余额解冻",iconCls:"icon-remove",handler:function(){
		                    var rows = $("#balance_frozen_query_result").datagrid("getSelections");
		                    if (rows == null || rows[0] == null) {
								$.messager.alert("提示","请选择要冻结的标识！");
							} else {
								var balFrozenId = new Array();
			                    for(var i=0;i<rows.length;i++){
			                    	balFrozenId.push(rows[i].BALANCE_FROZEN_ID);
			                    }
			                    var url = "${pageContext.request.contextPath}/acctBalance/balanceUnFrozen.action?balFrozenId=" + balFrozenId;
		                		$.post(url, rows, function(response){
								    $.messager.alert("提示",response);
								}, 'json');
							}
		                    
	                	}}]
		        	});
		        	$('#balance_frozen_query_result').datagrid('loadData', { total: 0, rows: [] });
					if(datas){
		        		for(var i=0; i<datas.length; i++){
		            		var data = datas[i];
							$('#balance_frozen_query_result').datagrid('appendRow', {
		            				BALANCE_FROZEN_ID: data.balanceFrozenId,
		            				FROZEN_AMOUNT: data.frozenAmount/100
		                    });
		            		
		            	}
	        		}
		        }
				
			});
			
		}
		
		function checkParam(){
			var flag = false;
			var acctId = $("#acctIdFrozen").val();
			if (acctId == null || $.trim(acctId) == "" || acctId == 0) {
				$("#acctIdFrozenHint").html("账户标识不能为空！");
				flag = true;
			} else {
				$("#acctIdFrozenHint").html("&nbsp;");
			}
			return flag;
		}
	
	</script>
    <div align="center">
    	<form id="balance_frozen_query_form">
    		<table>
    			<tr><th>账户标识：</th>
    				<td><input id="acctIdFrozen" name="acctId" class="easyui-numberbox" /></td></tr>
    			<tr><td>&nbsp;</td>
    				<td><font id="acctIdFrozenHint" color="red"></font></td></tr>
    			<tr><td>&nbsp;</td>
    				<td><a href="#" class="easyui-linkbutton" style="width:65px;" onclick="javascript:submit_form_frozen();">查询</a></td></tr>
    		</table>
    	</form>
    </div>
    <div id="balance_frozen_id"></div>
    <div id="balance_unfrozen_id"></div>
    <div id="balance_frozen_query_result"></div>
</body>
