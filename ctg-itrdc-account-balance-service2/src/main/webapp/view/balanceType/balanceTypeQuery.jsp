<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
 <script type="text/javascript">
 function bal_type_query_form(){
 	$("Element").removeAttr("#span_balType_result_id");
 	$.ajax({
        async:false,  
        type:"POST",  
        url:'${pageContext.request.contextPath}/balanceType/balanceTypeQuery.action',  
        dataType:"json",  
        cache: false,
        data:$('#view_balanceType_balanceTypeQuery_form').serialize(),  
        success:function(datas){
        	$('#view_balanceType_balanceTypeQuery_result').datagrid({
    				striped : true,
    				height:450,
    				fitColumns : false,
    				loadMsg : '数据加载中请稍后……',
    				rownumbers : true,
    				singleSelect : true,
    				columns : [ [{
    						field : '',
    						title : '选择',
    						checkbox : true,
    						align : 'left'
    					},{
							field : 'balanceTypeId',
							title : '余额类型标识',
							align : 'left',
							width : 150
						},{
							field : 'balanceTypeName',
							title : '余额类型名称',
							align : 'left',
							width : 150
						},{
							field : 'priority',
							title : '余额类型优先级',
							align : 'left',
							width : 150
						},{
							field : 'spePaymentId',
							title : '专款专用标识',
							align : 'left',
							width : 150
						},{
							field : 'spePaymentName',
							title : '专款专用描述',
							align : 'left',
							width : 150
						},
						{
							field : 'allowDraw',
							title : '允许提取标志',
							align : 'left',
							width : 150
						},{
							field : 'invOffer',
							title : '提供发票标志',
							align : 'left',
							width : 150
						},{
							field : 'ifEarning',
							title : '是否抵收入',
							align : 'left',
							width : 150
						},{
							field : 'ifPayold',
							title : '是否抵旧欠',
							align : 'left',
							width : 150
						},{
							field : 'ifSaveback',
							title : '是否滚存',
							align : 'left',
							width : 150
						},{
							field : 'ifPrincipal',
							title : '是否本金',
							align : 'left'
						},{
							field : 'statusCd',
							title : '状态',
							align : 'left',
							width : 150
						},{
							field : 'statusDate',
							title : '状态时间',
							align : 'left',
							width : 150,
							formatter : formatterdate 
						}
					] ]
			}); 
			
			$('#view_balanceType_balanceTypeQuery_result').datagrid('loadData', { total: 0, rows: [] });
			if(datas){
        		for(var i=0; i<datas.length; i++){
            		var data = datas[i];
            		$('#view_balanceType_balanceTypeQuery_result').datagrid('appendRow', {
            				balanceTypeId: data.balanceTypeId,
            				priority: data.priority,
            				spePaymentId: data.spePaymentId,
            				spePaymentName: data.spePaymentName,
            				balanceTypeName: data.balanceTypeName,
            				allowDraw: data.allowDraw,
            				invOffer: data.invOffer,
            				ifEarning: data.ifEarning,
            				ifPayold: data.ifPayold,
            				ifSaveback: data.ifSaveback,
            				ifPrincipal: data.ifPrincipal,
            				statusCd: data.statusCd,
            				statusDate: data.statusDate
                    });
            	}
       		}
        }
    }); 
 }
 
  function formatterdate(val, row) {
	    var date = new Date(val);
	    return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
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
  
  function bal_type_edit_form(){
  		if(!checkParas()){
	 		return false;
	 	}
  		$.ajax({
	        async:false,  
	        type:"POST",  
	        url:'${pageContext.request.contextPath}/balanceType/modifyBalanceType.action',  
	        dataType:"json",  
	        cache: false,
	        data:$('#edit_balanceType_form_view').serialize(),  
	        success:function(datas){
        		$.messager.alert('提示',datas);
        	}
        });
  }
  
   function checkParas(){
	 	var flag = false;
	 	var balanceTypeName = $("#balanceTypeNameEdit"); 
	 	var balanceTypeNameClass = $(".balanceTypeNameClassEdit");
	 	if($.trim(balanceTypeName.val()) == ''){
	 		balanceTypeNameClass.html('<font color="red">余额类型名称不能为空！</font>');
	 		flag = false;
	 	}else{
	 		balanceTypeNameClass.html('');
	 		flag = true;
	 	}
	 	return flag;
  }
  
  function bal_type_add(){
	  	$('#add_balanceType').window({
	    	title : '新增余额类型',
	    	width : 600,
	    	height : 280,
	    	modal : false,
	    	href : "${pageContext.request.contextPath}/view/balanceType/balanceTypeAdd.jsp"
	    });
  }
  
  function bal_type_edit(){
  		var rows = $("#view_balanceType_balanceTypeQuery_result").datagrid("getSelections");
        if (rows == null || rows[0] == null || rows.length == 0) {
			$.messager.alert("提示","请选择余额类型！");
			return;
		}else if(rows.length > 1 ){
			$.messager.alert("提示","只能选择一种余额类型！");
			return;
		}
		$('#balanceTypeIdEdit').textbox("setValue",rows[0].balanceTypeId);
		$('#priorityEdit').combobox("setValue",rows[0].priority);
		$('#spePaymentIdEdit').textbox("setValue",rows[0].spePaymentId);
		$('#balanceTypeNameEdit').textbox("setValue",rows[0].balanceTypeName);
		$('#allowDrawEdit').combobox("setValue",rows[0].allowDraw);
		$('#ifEarningEdit').combobox("setValue",rows[0].ifEarning);
		$('#ifPayOldEdit').combobox("setValue",rows[0].ifPayold);
		$('#statusCdEdit').combobox("setValue",rows[0].statusCd);
		$('#statusDateEdit').datebox("setValue",rows[0].statusDate);
		$('#invOfferEdit').combobox("setValue",rows[0].invOffer);
		$('#ifSaveBackEdit').combobox("setValue",rows[0].ifSaveback);
		$('#ifPrincipalEdit').combobox("setValue",rows[0].ifPrincipal);
		$(".balanceTypeNameClassEdit").html('');
		$('#edit_balanceType').window('open');
  }
  
  function balTypeImport(){
  	var balTypeFile = $("#balTypeFile").filebox("getValue");
  	if(balTypeFile == null || balTypeFile == ""){
  		$.messager.alert('提示',"请选择导入的文件！");
  	} else if(balTypeFile.split(".")[balTypeFile.split(".").length-1].toLowerCase() != "csv"){
  		$.messager.alert('提示',"请选择csv文件！");
  	} else {
  		var formData = new FormData($("#balance_type_file_save_form")[0]);
  		$.ajax({
	        async:true,  
	        type:"POST",  
	        url:'${pageContext.request.contextPath}/balanceType/importBalanceType.action',  
	        dataType:"json",
	        cache: false,
	        data:formData,
	        contentType: false,
    		processData: false,
	        success:function(datas){
	        	if(datas[1] == null || datas[3] != null){
	        		$.messager.alert('提示',datas[0]);
	        	}else{
	        		$.messager.defaults = { ok: "是", cancel: "否" };
	        		$.messager.confirm('提示',datas[0] + "是否导出失败清单？",function(result){
	        			if(result){
	        				window.location.href='${pageContext.request.contextPath}/balanceType/exportBalanceType.action?csvPath=' + datas[1]+'&csvName='+datas[2];
	        			}
	        		});
	        	}
	        	
        	}
        });
  	}
  	
  }
 </script>
<div> 
		<form id="view_balanceType_balanceTypeQuery_form" >
		<table style="padding: 10px;">
			<tr>
				<td width="10%">余额类型标识:</td>
				<td width="20%">
					<input id="balanceTypeId" name="balanceTypeId" value="" class="easyui-numberbox" >
					</td>
				<td width="10%">&nbsp;余额类型优先级:</td>
				<td width="20%">
					<!-- <input id="" name="" value="" class="easyui-combo" > -->
					<select id="priority" name="priority" class="easyui-combobox" style="width:150px;" data-options="editable:false,panelHeight:100">
						<option value="0">全部</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
					</select>
				</td>
				<td width="10%">&nbsp;专款专用标识:</td>
				<td width="20%">
					<input id="spePaymentId" name="spePaymentId" value="" class="easyui-numberbox" >
				</td>
			</tr>
			<tr>
				<td width="10%">余额类型名称:</td>
				<td width="20%">
					<input id="balanceTypeName" name="balanceTypeName" value="" class="easyui-textbox" >
				</td>
				<td width="10%">状态:</td>
				<td width="20%">
					<!-- <input id="" name="" value="" class="easyui-combo" > -->
					<select id="statusCd" name="statusCd" class="easyui-combobox" style="width: 150px;" data-options="editable:false,panelHeight:100">
						<option value="Q">全部</option>
						<option value="Y">有效</option>
						<option value="N">无效</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" onclick="bal_type_query_form('');" style="width:65px">查询</a></td>
			</tr>
		</table>
		</form>
		
	</div>
	<div class="datagrid-toolbar">
		<table>
			<tr>
				<td class="datagrid-btn-separator"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="javascript:bal_type_add();">新增</a></td>
				<td class="datagrid-btn-separator"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="javascript:bal_type_edit();">修改</a></td>
				<td class="datagrid-btn-separator">
					<form id="balance_type_file_save_form" enctype="multipart/form-data">
						<input id="balTypeFile" name="balType" class="easyui-filebox" data-options="prompt:'余额类型批量新增...',buttonText:'选择文件'" style="width:180px"/>
						<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="javascript:balTypeImport();">导入</a>
					</form>
				</td>
			</tr>
		</table>
	</div>
	<div id="add_balanceType"></div>
	<div id="edit_balanceType" class="easyui-window" title="余额类型修改" data-options="closed:true"
				style="width:600px;height:280px;padding:10px;" align="center">
		<form id="edit_balanceType_form_view">
			<table style="padding: 10px 10px 10px 10px;">
			<tr><td colspan="4" hidden="true"><input id="balanceTypeIdEdit" name="balanceTypeId" value="" class="easyui-numberbox" ></td></tr>
			<tr>
				<td width="20%">余额类型优先级:</td>
				<td width="30%">
					<!-- <input id="priority" name="priority" value="" class="easyui-combo" > -->
					<select id="priorityEdit" name="priority" class="easyui-combobox" style="width: 150px;" data-options="editable:false,panelHeight:80" >
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
					</select>
				</td>
				<td width="20%">专款专用标识:</td>
				<td width="30%">
					<input id="spePaymentIdEdit" name="spePaymentId" value="" class="easyui-numberbox" style="width: 150px;">
				</td>
			</tr>
			<tr>
				<td width="20%">余额类型名称:</td>
				<td width="30%">
					<input id="balanceTypeNameEdit" name="balanceTypeName" value="" class="easyui-textbox" style="width: 150px;">
					<span class="balanceTypeNameClassEdit">&nbsp;</span>
				</td>
				<td width="20%">允许提取标志:</td>
				<td width="30%">
					<!-- <input id="allowDraw" name="allowDraw" value="" class="easyui-textbox" > -->
					<select id="allowDrawEdit" name="allowDraw" class="easyui-combobox" style="width: 150px;" data-options="editable:false,panelHeight:80" >
						<option value="Y">Y</option>
						<option value="N">N</option>
					</select>
				</td>
				
			</tr>
			<tr>
				<td width="20%">是否抵收入:</td>
				<td width="30%">
					<!-- <input id="ifEarning" name="ifEarning" value="" class="easyui-combo" > -->
					<select id="ifEarningEdit" name="ifEarning" class="easyui-combobox" style="width: 150px;" data-options="editable:false,panelHeight:80" >
						<option value="Y">Y</option>
						<option value="N">N</option>
					</select>
				</td>
				<td width="20%">是否抵旧欠:</td>
				<td width="30%">
					<!-- <input id="ifPayOld" name="ifPayOld" value="" class="easyui-combo" > -->
					<select id="ifPayOldEdit" name="ifPayOld" class="easyui-combobox" style="width: 150px;" data-options="editable:false,panelHeight:80" >
						<option value="Y">Y</option>
						<option value="N">N</option>
					</select>
				</td>
				
			</tr>
			<tr>
				<td width="20%">状态:</td>
				<td width="30%">
					<!-- <input id="statusCd" name="statusCd" value="" class="easyui-combo" > -->
					<select id="statusCdEdit" name="statusCd" class="easyui-combobox" style="width: 150px;" data-options="editable:false,panelHeight:80" >
						<option value="Y">有效</option>
						<option value="N">无效</option>
					</select>
				</td>
				<td width="20%">状态时间:</td>
				<td width="30%">
					<input id="statusDateEdit" name="statusDate" data-options="formatter:myformatter,parser:myparser,editable:false" value="" class="easyui-datebox" style="width: 150px;">
					<span class="statusDateClassEdit"></span>
				</td>
			</tr>
			<tr>
				<td width="20%">提供发票标志:</td>
				<td width="30%">
					<!-- <input id="invOffer" name="invOffer" value="" class="easyui-combo" > -->
					<select id="invOfferEdit" name="invOffer" class="easyui-combobox" style="width: 150px;" data-options="editable:false,panelHeight:80" >
						<option value="Y">Y</option>
						<option value="N">N</option>
					</select>
				</td>
				<td width="20%">是否滚存:</td>
				<td width="30%">
					<!-- <input id="ifSaveBack" name="ifSaveBack" value="" class="easyui-combo" > -->
					<select id="ifSaveBackEdit" name="ifSaveBack" class="easyui-combobox" style="width: 150px;" data-options="editable:false,panelHeight:80" >
						<option value="Y">Y</option>
						<option value="N">N</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="20%">是否本金:</td>
				<td width="30%">
					<!-- <input id="ifPrincipal" name="ifPrincipal" value="" class="easyui-combo" > -->
					<select id="ifPrincipalEdit" name="ifPrincipal" class="easyui-combobox" style="width: 150px;" data-options="editable:false,panelHeight:80" >
						<option value="Y">Y</option>
						<option value="N">N</option>
					</select>
				</td>
				<td>&nbsp;</td><td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" align="center"><a href="#" class="easyui-linkbutton" onclick="bal_type_edit_form();" style="width:65px">修改</a></td>
			</tr>
		</table>
		</form>
	</div>
	<div id="view_balanceType_balanceTypeQuery_result"></div>
</body>
</html>