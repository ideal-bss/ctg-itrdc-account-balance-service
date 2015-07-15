<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<body>
	<script type="text/javascript">
		
		function submit_form_frozen(){
		    if (checkParamFrozenQuert()) {
				return;
			}
			$('#bal_frozen_list_data').datagrid({
		    	loadMsg : '数据加载中...',
		    	fit : true,
		    	url : '${pageContext.request.contextPath}/acctBalance/balanceFrozenQuery.action',
		    	queryParams : {
		    		acctId : $('#acctIdFrozen').val(),
		    		acctBalanceId : $('#acctBalanceIdFrozen').val()
		    	}
		    });
		    
		    //设置分页控件
		    var pager = $('#bal_frozen_list_data').datagrid('getPager');
		    $(pager).pagination({
		    	pageNumber : 1,//初始化页码
		    	pageSize : 10, //每页显示的记录数  默认10条
		    	pageList: [5,10],
		    	beforePageText : '第',
		    	afterPageText: '页    共 {pages} 页',
				displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		    });
		}
		
		
		function checkParamFrozenQuert(){
			var flag = false;
			var acctId = $("#acctIdFrozen").val();
			var acctBalanceIdFrozen = $("#acctBalanceIdFrozen").val();
			if (acctId == null || $.trim(acctId) == "" || acctId == 0) {
				flag = true;
			}
			if (acctBalanceIdFrozen == null || $.trim(acctBalanceIdFrozen) == "" || acctBalanceIdFrozen == 0) {
				flag = true;
				$("#acctBalanceIdFrozenHint").html("请输入余额账本标识！");
			}else{
				$("#acctBalanceIdFrozenHint").html("");
			}
			return flag;
		}
		
		/**余额解冻*/
		function bal_unfrozen(){
			var rows = $("#bal_frozen_list_data").datagrid("getSelections");
            if (rows == null || rows[0] == null) {
				$.messager.alert("提示","请选择要冻结的标识！");
			} else {
				 $.messager.confirm("操作提示", "您确定要执行操作吗？", function (data) {
		            if (data) {
		                var balFrozenId = null;
		                for(var i=0;i<rows.length;i++){
		                	if(i == 0){
		                		balFrozenId = rows[i].balanceFrozenId;
		                	} else {
		                		balFrozenId += "," + rows[i].balanceFrozenId;
		                	}
		                }
		                var url = "${pageContext.request.contextPath}/acctBalance/balanceUnFrozen.action?balFrozenId=" + balFrozenId;
		                $.post(url, rows, function(response){
					    		$.messager.alert("提示",response,'info');
						},'json');
		            }
		        });
				
			}
		
		}
	</script>
    <div id="balance_frozen_query_form_div" style="height:75px;width:557px">
    	<form id="balance_frozen_query_form" method="post">
	   		<table style="width: 100%;height: 100%;">
	   			<tr align="left">
	   				<th>账户标识：</th>
	   				<td><input id="acctIdFrozen" name="acctId" class="easyui-numberbox" value="${param.acctId }" data-options="editable:false"/></td>
	   				<th>余额账本标识：</th>
	   				<td><input id="acctBalanceIdFrozen" name="acctBalanceId" class="easyui-numberbox" value="${param.acctBalanceId }"/></td>
	   			</tr>
	   			<tr><td colspan="3">&nbsp;</td><td><font color="red" id="acctBalanceIdFrozenHint"></font></td></tr>
	   			<tr>
	   				<td colspan="4" align="center"><a href="#" class="easyui-linkbutton" onclick="javascript:submit_form_frozen();" style="width:65px">查询</a></td>
	   			</tr>
	   		</table>
	   	</form>
    </div>
    <div class="datagrid-toolbar" style="height:35px;width:557px;">
    	<table style="width: 100%;height: 100%;">
			<tr>
				<td class="datagrid-btn-separator"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="javascript:bal_unfrozen();">解冻</a></td>
			</tr>
		</table>
    </div>
    <div id="balance_frozen_query_result"  style="height:334px;width:561px;">
    	<table class="easyui-datagrid" id="bal_frozen_list_data"
			data-options="rownumbers:true,fitColumns:true,height:334,singleSelect:false,pagination:true,striped:true,showFooter:true">
			<thead>
				<tr><th data-options="field:'',checkbox:true"></th>
					<th data-options="field:'balanceFrozenId'">余额冻结标识</th>
					<th data-options="field:'frozenAmount'" align="right">冻结余额（元）</th>
				</tr>
			</thead>
		</table>
    </div>
</body>
