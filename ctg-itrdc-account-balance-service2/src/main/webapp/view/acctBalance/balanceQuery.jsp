<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
<script type="text/javascript">
 function submit_form_query(){
 	 if(!checkParamBalQuery()){
 		return;
 	 }
 
 	 $('#acct_bal_list_data').datagrid({
    	loadMsg : '数据加载中...',
    	fit : true,
    	url : '${pageContext.request.contextPath}/acctBalance/balanceQuery.action',
    	queryParams : {
    		objectId : $('#objectIdQuery').val(),
    		balanceTypeId : $('#acctBalbalanceTypeIdQuery').combobox('getValue'),
    		ObjectIdType : $('#ObjectIdTypeQuery').combobox('getValue')
    	}
    });
 	//设置分页控件
    var pager = $('#acct_bal_list_data').datagrid('getPager');
    $(pager).pagination({
    	pageNumber : 1,//初始化页码
    	pageSize : 10, //每页显示的记录数  默认10条
    	pageList: [5,10],
    	beforePageText : '第',
    	afterPageText: '页    共 {pages} 页',
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
    });
 }
 
 function checkParamBalQuery(){
 	var flag = true;
 	var objectId = $("#objectIdQuery").val(); 
 	var objectIdClass = $(".objectIdClass");
 	if(objectId == null || $.trim(objectId) == ''){
 		objectIdClass.html('<font color="red">余额对象号码不能为空！</font>');
 		flag = false;
 	}else if(objectId.length>15){
 		objectIdClass.html('<font color="red">余额对象号码不能超过15位！</font>');
 		flag = false;
 	}else{
 		objectIdClass.html('');
 	}
 	return flag;
 	
 	return 
 }

  //加载余额类型下拉框
  $(function(){
  	  $('#acctBalbalanceTypeIdQuery').combobox({
		  	url : '${pageContext.request.contextPath}/balanceType/loadBalanceTypeSelect.action?allFlag=1',
		  	valueField : 'balanceTypeId',
		  	textField : 'balanceTypeName',
		  	value : 0
	  });
  });
  
  function formatterdate(val, row) {
  		if (val != null){
  			var date = new Date(val);
	    	return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
  		}
  }
 
  function myformatter(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
  }
  function myparser(s){
		if (!s) return new Date();
		var ss = (s.split('-')); 
		var y = parseInt(ss[0],10);
		var m = parseInt(ss[1],10);
		var d = parseInt(ss[2],10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
			return new Date(y,m-1,d);
		} else {
			return new Date();
		}
  }
  
  //余额支取
  function bal_draw(){
  		var rows = $("#acct_bal_list_data").datagrid("getSelections");
  		if(rows == null || rows == '' || rows.length == 0){
  			$.messager.alert('提示','请选择要支取余额的账本！','info');
  			return;
  		}
  		if(rows.length>1){
  			$.messager.alert('提示','支取时只能选择一个账本！','info');
  			return;
  		}
  		var acctBalanceId = '';
  		for(var i=0;i<rows.length;i++){
  			var abi = rows[i].acctBalanceId;
  			if(acctBalanceId == null || acctBalanceId == ''){
  				acctBalanceId = abi;
  			}else{
  				acctBalanceId = acctBalanceId + ',' + abi;
  			}
  		}
	  	$('#bal_draw_result').window({
	    	title : '余额支取',
	    	width : 550,
	    	height : 250,
	    	modal : false,
	    	href : "${pageContext.request.contextPath}/view/acctBalance/balanceDraw.jsp?acctId=" 
	    			+ rows[0].acctId + "&objectId=" + rows[0].objectId + "&acctBalanceId=" + acctBalanceId
	    });
  }
  
  //余额转账
  function bal_transfer(){
  		var rows = $("#acct_bal_list_data").datagrid("getSelections");
  		if(rows == null || rows == '' || rows.length == 0){
  			$.messager.alert('提示','请选择要转账的源账本！','info');
  			return;
  		}
  		if(rows.length>1){
  			$.messager.alert('提示','转账时只能选择一个源账本！','info');
  			return;
  		}
  		
	  	$('#bal_draw_result').window({
	    	title : '余额转账',
	    	width : 550,
	    	height : 250,
	    	modal : false,
	    	href : "${pageContext.request.contextPath}/view/acctBalance/balanceTransfer.jsp?acctBalanceId=" 
	    		+ rows[0].acctBalanceId + "&balanceTypeId="+rows[0].balanceTypeId + "&acctId=" + rows[0].acctId
	    });
  }
  
  //余额冻结查询
  function bal_FrozenQuery(){
  		var rows = $("#acct_bal_list_data").datagrid("getSelections");
  		if(rows == null || rows == '' || rows.length == 0){
  			$.messager.alert('提示','请选择要查询的账本！','info');
  			return;
  		}
  		if(rows.length>1){
  			$.messager.alert('提示','冻结查询时只能选择一个账本！','info');
  			return;
  		}
  		
	  	$('#bal_draw_result').window({
	    	title : '余额冻结解冻',
	    	width : 575,
	    	height : 483,
	    	modal : false,
	    	href : "${pageContext.request.contextPath}/view/acctBalance/balanceFrozenUnfronzen.jsp?acctBalanceId=" 
	    		+ rows[0].acctBalanceId + "&acctId=" + rows[0].acctId
	    });
  }
  
  //余额冻结
  function bal_frozen(){
  		var rows = $("#acct_bal_list_data").datagrid("getSelections");
  		if(rows == null || rows == '' || rows.length == 0){
  			$.messager.alert('提示','请选择要冻结的账本！','info');
  			return;
  		}
  		if(rows.length>1){
  			$.messager.alert('提示','冻结时只能选择一个账本！','info');
  			return;
  		}
  		
	  	$('#bal_draw_result').window({
	    	title : '余额冻结',
	    	width : 550,
	    	height : 250,
	    	modal : false,
	    	href : "${pageContext.request.contextPath}/view/acctBalance/balanceFrozen.jsp?acctBalanceId=" 
	    		+ rows[0].acctBalanceId + "&acctId=" + rows[0].acctId
	    });
  }
</script>
<div> 
		<form id="view_acctBalance_balanceQuery_form" method="post">
		<table style="padding: 10px;">
			<tr>
				<td width="10%">余额对象号码:</td>
				<td width="20%">
					<input id="objectIdQuery" name="objectId" validtype="length[1,15]" invalidMessage="有效长度1-15个字符" value="" class="easyui-textbox" style="width: 150px">
				</td>
				<td width="10%">余额类型:</td>
				<td width="20%">
				<input id="acctBalbalanceTypeIdQuery" name="balanceTypeId" value="" style="width: 150px" data-options="editable:false,panelHeight:150">
				</td>
			</tr>
			<tr><td>&nbsp;</td><td><span class="objectIdClass">&nbsp;</span></td><td colspan="2"></td></tr>
			<tr>
				<td width="10%">余额对象类型:</td>
				<td width="20%">
				 <select id="ObjectIdTypeQuery" name="ObjectIdType" class="easyui-combobox" style="width: 150px" data-options="editable:false,panelHeight:100">
				 	<option value="0">全部</option>
				 	<option value="1">账户</option>
				 	<option value="2">设备</option>
				 </select>
				</td>
				<td width="10%"></td>
				<td width="20%">
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center"><a href="#" class="easyui-linkbutton" onclick="submit_form_query();" style="width:65px">查询</a></td>
			</tr>
		</table>
		</form>
		</div>
		<div class="datagrid-toolbar">
			<table>
				<tr>
					<td class="datagrid-btn-separator"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="javascript:bal_draw();">支取</a></td>
					<td class="datagrid-btn-separator"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="javascript:bal_transfer();">转账</a></td>
					<td class="datagrid-btn-separator"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="javascript:bal_FrozenQuery();">冻结查询</a></td>
					<td class="datagrid-btn-separator"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="javascript:bal_frozen();">余额冻结</a></td>
				</tr>
			</table>
		</div>
		<div id="bal_draw_result"></div>
		<div id="view_acctBalance_balanceQuery_result" >
			<table class="easyui-datagrid" id="acct_bal_list_data"
				data-options="rownumbers:true,fitColumns:true,height:400,singleSelect:true,pagination:true,striped:true,showFooter:true">
				<thead>
					<tr><th data-options="field:'',checkbox:true"></th>
						<th data-options="field:'acctBalanceId'">余额帐本标识</th>
						<th data-options="field:'balanceTypeId'">余额类型标识</th>
						<th data-options="field:'balanceTypeName'">余额类型名称</th>
						<th data-options="field:'effDate'">生效时间</th>
						<th data-options="field:'expDate'">失效时间</th>
						<th data-options="field:'objectId'">余额对象号码</th>
						<th data-options="field:'balance'" align="right">单个账本余额（元）</th>
						<th data-options="field:'frozenAmount'" align="right">冻结余额（元）</th>
						<th data-options="field:'acctId'" hidden="true"></th>
					</tr>
				</thead>
			</table>
			
		</div>
</body>
</html>