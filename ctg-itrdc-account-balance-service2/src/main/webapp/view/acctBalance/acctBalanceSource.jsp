<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<body>
  <script type="text/javascript">
  	if(document.readyState=="complete"){
  			setTimeout(function(){acctBalSourceQuery();}, 500);//进入该网页之后等待0.5秒再执行提取数据函数
	}
  	function acctBalSourceQuery(){
	  		$('#view_acctBalSource_query_result_list').datagrid({
		    	loadMsg : '数据加载中...',
		    	fit : true,
		    	url : '${pageContext.request.contextPath}/acctBalance/acctBalLogbalSourceQuery.action',
       	        queryParams : {
		    		acctId : "${param.acctId }",
		    		acctBalanceId : "${param.acctBalanceId }"
		    	},
		    	onLoadSuccess:function(data){  
			        $('.editcls').linkbutton({text:'',plain:true,iconCls:'icon-search',width:57});  
			    }
		    });
		    
		    //设置分页控件
		    var pager = $('#view_acctBalSource_query_result_list').datagrid('getPager');
			    $(pager).pagination({
			    	pageNumber : 1,//初始化页码
			    	pageSize : 10, //每页显示的记录数  默认10条
			    	pageList: [5,10],
			    	beforePageText : '第',
			    	afterPageText: '页    共 {pages} 页',
					displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
			    });
	  }
	  
	  //检索余额支出记录
	  function acctBalPayOutQuery(value,rec,index){
	  	  var btn = '';
	  	  if(rec != null && rec.operIncomeId != null && rec.operIncomeId != '' && rec.operIncomeId>0){
	  	  	btn = '<a class="editcls" style="height:23px;" onclick="javascript:acctBalPayOutQueryGo(\''+rec.operIncomeId 
	  	  		+'\',\''+$("#acctBalSource_acctBalId").val() +'\',\''+$("#acctBalSource_acctId").val()+'\')" href="#"></a>';
	  	  }
          return btn;
	  }
	  function acctBalPayOutQueryGo(operIncomeId,acctBalanceId,acctId){
		  	$('#view_acctBalPayOut_query_request').window({
		    	title : '余额支出记录',
		    	width : 790,
		    	height : 483,
		    	modal : false,
		    	href : "${pageContext.request.contextPath}/view/acctBalance/acctBalancePayOut.jsp?acctBalanceId=" 
		    		+ acctBalanceId + "&acctId=" + acctId + "&operIncomeId=" + operIncomeId
		    });
	  }
	  
	  /*余额冲正*/
	  function bal_reverse(){
		  	var rows = $("#view_acctBalSource_query_result_list").datagrid("getSelections");
		  	var acctId = $("#acctBalSource_acctId").val();
		  	if(rows == null || rows == '' || rows.length == 0){
	  			$.messager.alert('提示','请选择冲正的余额来源！','info');
	  			return;
	  		}
	  		var operIncomeId = '';
	  		for(var i=0;i<rows.length;i++){
	  			var abi = rows[i].operIncomeId;
	  			if(operIncomeId == null || operIncomeId == ''){
	  				operIncomeId = abi;
	  			}else{
	  				operIncomeId = operIncomeId + '-' + abi;
	  			}
	  		}
	  		
	  		$.ajax({
				async:false,
				type:"post",
				url:"${pageContext.request.contextPath}/acctBalance/balanceReverse.action",
				cache:false,
				dataType:"json",
				//data:$('#balance_reverse_form').serialize(),
				data:{acctId:acctId,operIncomeId:operIncomeId},
				success:function(datas){
					$.messager.alert('提示',datas);
				}
			});
	  }
  </script>
  <div class="datagrid-toolbar">
		<table>
			<tr>
				<td class="datagrid-btn-separator"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="javascript:bal_reverse();">冲正</a></td>
			</tr>
		</table>
  </div>
  <div id="view_acctBalSource_query_result">
  	<form>
  		<input id="acctBalSource_acctId" name="acctId" hidden="true" value="${param.acctId }"/>
  		<input id="acctBalSource_acctBalId" name="acctBalanceId" hidden="true" value="${param.acctBalanceId }"/>
  	</form>
  	<table class="easyui-datagrid" id="view_acctBalSource_query_result_list"
  		data-options="rownumbers:true,fitColumns:true,height:400,singleSelect:false,pagination:true,striped:true,showFooter:true">
  		<thead>
  			<tr>
  				<th data-options="field:'',checkbox:true"></th>
  				<th data-options="field:'operIncomeId'">来源操作流水</th>
  				<th data-options="field:'operType'">操作类型</th>
  				<th data-options="field:'staffId'">工号</th>
  				<th data-options="field:'operDate'">余额来源创建时间</th>
  				<th data-options="field:'amount'" align="right">余额来源原金额（元）</th>
  				<th data-options="field:'curAmount'" align="right">余额来源剩余金额（元）</th>
  				<th data-options="field:'balanceSourceTypeId'">来源类型标识</th>
  				<th data-options="field:'balanceSourceTypeDesc'">来源类型描述</th>
  				<th data-options="field:' ',formatter:acctBalPayOutQuery" align="center">余额支出&nbsp;</th>
  			</tr>
  		</thead>
  	</table>
  </div>
  <div id="view_acctBalPayOut_query_request"></div>
</body>