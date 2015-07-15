<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<body>
  <script type="text/javascript">
  	if(document.readyState=="complete"){
  			setTimeout(function(){acctBalPayOutQuery();}, 500);//进入该网页之后等待0.5秒再执行提取数据函数
	}
  	function acctBalPayOutQuery(){
	  		$('#view_acctBalPayOut_query_result_list').datagrid({
		    	loadMsg : '数据加载中...',
		    	fit : true,
		    	url : '${pageContext.request.contextPath}/acctBalance/acctBalLogbalPayOutQuery.action',
		    	queryParams : {
		    		acctId : "${param.acctId }",
		    		acctBalanceId : "${param.acctBalanceId }",
		    		operIncomeId : "${param.operIncomeId }"
		    	}
		    });
		    
		    //设置分页控件
		    var pager = $('#view_acctBalPayOut_query_result_list').datagrid('getPager');
		    $(pager).pagination({
		    	pageNumber : 1,//初始化页码
		    	pageSize : 10, //每页显示的记录数  默认10条
		    	pageList: [5,10],
		    	beforePageText : '第',
		    	afterPageText: '页    共 {pages} 页',
				displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		    });
	  }
	  
  </script>
  <div id="view_acctBalPayOut_query_result">
  	<table class="easyui-datagrid" id="view_acctBalPayOut_query_result_list"
  		data-options="rownumbers:true,fitColumns:true,height:445,singleSelect:false,pagination:true,striped:true,showFooter:true,selectOnCheck:false">
  		<thead>
  			<tr>
  				<th data-options="field:'operPayoutId'">支出操作流水</th>
  				<th data-options="field:'altAmount'" align="right">补退金额（元）</th>
  				<th data-options="field:'billId'">销账流水号</th>
  				<th data-options="field:'extSerialId'">付款流水号</th>
  				<th data-options="field:'operType'">操作类型</th>
  				<th data-options="field:'staffId'">工号</th>
  				<th data-options="field:'operDate'">余额支出时间</th>
  				<th data-options="field:'balance'" align="right">支出后余额（元）</th>
  			</tr>
  		</thead>
  	</table>
  </div>
</body>