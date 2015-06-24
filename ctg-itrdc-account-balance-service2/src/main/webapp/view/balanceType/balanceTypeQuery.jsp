<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
 <script type="text/javascript">
 function query_form(){
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
    				singleSelect : false,
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
							field : 'balanceTypeName',
							title : '余额类型名称',
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
							width : 150
						}
					] ],
					toolbar:[
	                {text:"增加",iconCls:"icon-add",handler:function(){
	                    $('#add_balanceType').window({
	                    	title : '新增余额类型',
	                    	width : 600,
	                    	height : 400,
	                    	modal : false,
	                    	href : "${pageContext.request.contextPath}/view/balanceType/balanceTypeAdd.jsp"
	                    });
	                    
	                }},{text:"删除",iconCls:"icon-remove",handler:function(){
	                	var row = $("#view_balanceType_balanceTypeQuery_result").datagrid("getSelections");
	                	$.messager.alert("提示",row.length+"  "+row[1].priority+"  "+row[1].balanceTypeName);
	                }},{text:"修改",iconCls:"icon-edit",handler:function(){
	                    $('#edit_balanceType').window({
	                    	title : '修改余额类型',
	                    	width : 600,
	                    	height : 400,
	                    	modal : true
	                    });
                	}}]
			}); 
			
			$('#view_balanceType_balanceTypeQuery_result').datagrid('loadData', { total: 0, rows: [] });
			if(datas){
        		for(var i=0; i<datas.length; i++){
            		var data = datas[i];
            		$('#view_balanceType_balanceTypeQuery_result').datagrid('appendRow', {
            				balanceTypeId: data.balanceTypeId,
            				priority: data.priority,
            				spePaymentId: data.spePaymentId ,
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
	   
 </script>
<div> 
		<form id="view_balanceType_balanceTypeQuery_form" >
		<table style="padding: 10px;">
			<tr>
				<td width="10%">余额类型标识:</td>
				<td width="20%">
					<input id="balanceTypeId" name="balanceTypeId" value="" class="easyui-textbox" >
					</td>
				<td width="10%">&nbsp;余额类型优先级:</td>
				<td width="20%">
					<!-- <input id="" name="" value="" class="easyui-combo" > -->
					<select id="priority" name="priority" class="easyui-combobox" style="width: 150px;">
						<option value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
					</select>
				</td>
				<td width="10%">&nbsp;专款专用标识:</td>
				<td width="20%">
					<input id="spePaymentId" name="spePaymentId" value="" class="easyui-textbox" >
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
					<select id="statusCd" name="statusCd" class="easyui-combobox" style="width: 150px;">
						<option value="Y">有效</option>
						<option value="N">无效</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" onclick="query_form('');" style="width:65px">查询</a></td>
				<!-- <td><a href="#" class="easyui-linkbutton" onclick="balanceTypeAddGo('');" style="width:65px">新增</a></td> -->
			</tr>
		</table>
		</form>
		<div id="add_balanceType"></div>
		<div id="edit_balanceType"></div>
		<div id="view_balanceType_balanceTypeQuery_result" ></div>
	</div>
</body>
</html>