<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<body>

	<script type="text/javascript">
		function submit_form_acctLog(){
			if (checkParams()) {
				return;
			}
			
		    $('#acctBal_list_result_id').datagrid({
		    	loadMsg : '数据加载中...',
		    	fit : true,
		    	url : '${pageContext.request.contextPath}/acctBalance/acctBalanceLog.action',
		    	queryParams : {
		    		acctId : $('#acctBalacctId').val(),
		    		balanceTypeId : $('#acctBalbalanceTypeId').combobox('getValue')
		    	},
		    	onLoadSuccess:function(data){  
			        $('.editcls').linkbutton({text:'',plain:true,iconCls:'icon-search',width:57});  
			    }
		    });
		    //设置分页控件
		    var pager = $('#acctBal_list_result_id').datagrid('getPager');
		    $(pager).pagination({
		    	pageNumber : 1,//初始化页码
		    	pageSize : 10, //每页显示的记录数  默认10条
		    	pageList: [5,10,15],
		    	beforePageText : '第',
		    	afterPageText: '页    共 {pages} 页',
				displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		    });
		}
		
		function checkParams(){
			var flag = false;
			var acctId = $("#acctBalacctId").val();
			if (acctId==null || $.trim(acctId)=="" || acctId==0) {
				$("#acctIdHint").html("账户标识不能为空！");
				flag = true;
			} else {
				$("#acctIdHint").html("&nbsp;");
			}
			return flag;
		}
		
	  //加载余额类型下拉框
	  $(function(){
	  	  $('#acctBalbalanceTypeId').combobox({
			  	url : '${pageContext.request.contextPath}/balanceType/loadBalanceTypeSelect.action?allFlag=1',
			  	valueField : 'balanceTypeId',
			  	textField : 'balanceTypeName',
			  	value : 0
		  });
	  });
	  
	  //检索余额来源
	  function acctBalSourceQuery(value,rec,index){
	  	  var btn = '';
	  	  if(rec != null && rec.acctBalanceId != null && rec.acctBalanceId != '' && rec.acctBalanceId>0){
	  	  	btn = '<a class="editcls" style="height:23px;" onclick="javascript:acctBalSourceQueryGo(\''+rec.acctBalanceId+'\',\''+rec.acctId+'\')" href="#"></a>';
	  	  }
          return btn;
	  }
	  function acctBalSourceQueryGo(acctBalanceId,acctId){
		  	$('#view_acctBalSource_query_request').window({
		    	title : '余额来源记录',
		    	width : 965,
		    	height : 483,
		    	modal : false,
		    	href : "${pageContext.request.contextPath}/view/acctBalance/acctBalanceSource.jsp?acctBalanceId=" 
		    		+ acctBalanceId + "&acctId=" + acctId
		    });
		   
	  }
	</script>
	<div align="center">
	      <div style="width: 450px;height: 75px">
	  		<form id="acct_balance_log_form" method="post">
	  			<table>
	  				<tr><th>账户标识：</th>
	  					<td><input id="acctBalacctId" name="acctId" class="easyui-numberbox"/></td>
	  					<th>余额类型：</th>
	  					<td>
	  						<input id="acctBalbalanceTypeId" name="balanceTypeId" class="easyui-combobox" style="width: 150px">
					 	</td>
	  				</tr>
	  				<tr>
	  					<td>&nbsp;</td><td><font id="acctIdHint" color="red">&nbsp;</font></td><td></td><td></td>
	  				</tr>
	  				<tr align="center">
	  					<td colspan="4"><a href="#" class="easyui-linkbutton" style="width: 65px" onclick="javaScript:submit_form_acctLog();">查询</a></td>
	  				</tr>
	  			</table>
	  		</form>
	     </div>
	     <div id="view_acctBalanceLog_query_result">
			<table class="easyui-datagrid" id="acctBal_list_result_id"
				data-options="rownumbers:true,fitColumns:true,height:485,singleSelect:false,pagination:true,striped:true,showFooter:true,selectOnCheck:false">
				<thead>
					<tr>
						<th data-options="field:'acctId'">账户标识</th>
						<th data-options="field:'acctBalanceId'">余额账本标识</th>
						<th data-options="field:'balanceTypeId'">余额类型标识</th>
						<th data-options="field:'balanceTypeName'">余额类型名称</th>
						<th data-options="field:'acctBalance'" align="right">单个账本余额（元）</th>
						<th data-options="field:' ',formatter:acctBalSourceQuery" align="center">余额来源&nbsp;</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="view_acctBalSource_query_request"></div>
	</div>
</body>
