<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<body>

	<script type="text/javascript">
		function submit_form_acctLog(){
			if (checkParams()) {
				return;
			}
		
			$.ajax({
				async:false,
				type:"post",
				url:"${pageContext.request.contextPath}/acctBalance/acctBalanceLog.action",
				cache:false,
				dataType:"json",
				data:$('#acct_balance_log_form').serialize(),
				success:function(datas){
		        	if(datas[0].errorInfo != null){
		        		$.messager.alert('提示框',datas[0].errorInfo,'info');
		        		datas = null;
		        	}
		        	
		        	$('#view_acctBalanceLog_query_result').datagrid({
		    				striped : true,
		    				height:450,
		    				singleSelect : true,
		    				fitColumns : false,
		    				loadMsg : '数据加载中请稍后……',
		    				rownumbers : true,
		    				pagination : true,
		    				columns : [ [
		    					{
		    						title : '<B>余额账本</B>',
		    						colspan : 5
		    					},{
		    						title : '<B>余额来源记录</B>',
		    						colspan : 8
		    					},{
		    						title : '<B>余额支出记录</B>',
		    						colspan : 8
		    					},{
		    						title : '<B>余额支出账目</B>',
		    						colspan : 2
		    					}],[{
									field : 'ACCT_ID',
									title : '<B>账户标识</B>',
									align : 'left',
									width : 150
								},{
									field : 'ACCT_BALANCE_ID',
									title : '<B>余额账本标识</B>',
									align : 'left',
									width : 150
								},{
									field : 'ACCT_BALANCE',
									title : '<B>账本余额（元）</B>',
									align : 'left',
									width : 150
								},{
									field : 'BALANCE_TYPE_ID',
									title : '<B>余额类型标识</B>',
									align : 'left',
									width : 150
								},
								{
									field : 'BALANCE_TYPE_NAME',
									title : '<B>余额类型名称</B>',
									align : 'left',
									width : 150
								},{
									field : 'OPER_INCOME_ID',
									title : '<B>来源操作流水</B>',
									align : 'left',
									width : 150
								},{
									field : 'SOURCE_OPER_TYPE',
									title : '<B>操作类型</B>',
									align : 'left',
									width : 150
								},{
									field : 'SOURCE_STAFF_ID',
									title : '<B>工号</B>',
									align : 'left',
									width : 150
								},{
									field : 'SOURCE_OPER_DATE',
									title : '<B>余额来源创建时间</B>',
									align : 'left',
									width : 150
								},{
									field : 'SOURCE_AMOUNT',
									title : '<B>余额来源原金额（元）</B>',
									align : 'left',
									width : 150
								},{
									field : 'CUR_AMOUNT',
									title : '<B>余额来源剩余金额（元）</B>',
									align : 'left',
									width : 150
								},{
									field : 'BALANCE_SOURCE_TYPE_ID',
									title : '<B>来源类型标识</B>',
									align : 'left',
									width : 150
								},{
									field : 'BALANCE_SOURCE_TYPE_ID_DESC',
									title : '<B>来源类型描述</B>',
									align : 'left',
									width : 150
								},{
									field : 'OPER_PAYOUT_ID',
									title : '<B>支出操作流水</B>',
									align : 'left',
									width : 150
								},{
									field : 'PAYOUT_AMOUNT',
									title : '<B>支出金额（元）</B>',
									align : 'left',
									width : 150
								},{
									field : 'BILL_ID',
									title : '<B>销账流水号</B>',
									align : 'left',
									width : 150
								},{
									field : 'EXT_SERIAL_ID',
									title : '<B>付款流水号</B>',
									align : 'left',
									width : 150
								},{
									field : 'PAYOUT_OPER_TYPE',
									title : '<B>操作类型</B>',
									align : 'left',
									width : 150
								},{
									field : 'PAYOUT_STAFF_ID',
									title : '<B>工号</B>',
									align : 'left',
									width : 150
								},{
									field : 'PAYOUT_OPER_DATE',
									title : '<B>余额支出时间</B>',
									align : 'left',
									width : 150
								},{
									field : 'PAYOUT_BALANCE',
									title : '<B>支出后余额（元）</B>',
									align : 'left',
									width : 150
								},{
									field : 'ACCT_ITEM_ID',
									title : '<B>账目标识</B>',
									align : 'left',
									width : 150
								},{
									field : 'ACCT_ITEM_BALANCE',
									title : '<B>使用余额（元）</B>',
									align : 'left',
									width : 150
								}
								
							] ]
						});
						$('#view_acctBalanceLog_query_result').datagrid('loadData', { total: 0, rows: [] });
						if(datas){
			        		for(var i=0; i<datas.length; i++){
			            		var data = datas[i];
			            		$('#view_acctBalanceLog_query_result').datagrid('appendRow', {
			            				ACCT_ID: data.acctId,
			            				ACCT_BALANCE_ID: data.acctBalanceId ,
			            				ACCT_BALANCE: data.acctBalance/100,
			            				BALANCE_TYPE_ID: data.balanceTypeId,
			            				BALANCE_TYPE_NAME: data.balanceTypeName,
			            				OPER_INCOME_ID: data.operIncomeId,
			            				SOURCE_OPER_TYPE: data.sourceOperType,
			            				SOURCE_STAFF_ID: data.sourceStaffId,
			            				SOURCE_OPER_DATE: data.sourceOperDate,
			            				SOURCE_AMOUNT: data.sourceAmount/100,
			            				CUR_AMOUNT: data.curAmount/100,
			            				BALANCE_SOURCE_TYPE_ID: data.balanceSourceTypeId,
			            				BALANCE_SOURCE_TYPE_ID_DESC: data.balanceSourceTypeIdDesc,
			            				PAYOUT_AMOUNT: data.payoutAmount/100,
			            				OPER_PAYOUT_ID: data.operPayoutId,
			            				BILL_ID: data.billId,
			            				EXT_SERIAL_ID: data.extSerialId,
			            				PAYOUT_OPER_TYPE: data.payoutOperType,
			            				PAYOUT_STAFF_ID: data.payoutStaffId,
			            				PAYOUT_BALANCE: data.payoutBalance/100,
			            				PAYOUT_OPER_DATE: data.payoutOperDate,
			            				ACCT_ITEM_ID: data.acctItemId,
			            				ACCT_ITEM_BALANCE: data.acctItemBalance/100
			                    });
			            	}
		        		}
				}
			});
		}
		
		function checkParams(){
			var flag = false;
			var acctId = $("#acctId").val();
			if (acctId==null || $.trim(acctId)=="" || acctId==0) {
				$("#acctIdHint").html("账户标识不能为空！");
				flag = true;
			} else {
				$("#acctIdHint").html("&nbsp;");
			}
			return flag;
		}
	</script>

  <div align="center">
  		<div>
  		<form id="acct_balance_log_form">
  			<table>
  				<tr><td>账户标识：</td>
  					<td><input id="acctId" name="acctId" class="easyui-numberbox"/></td>
  					<td>余额类型：</td>
  					<td>
	  					<select id="balanceTypeId" name="balanceTypeId" class="easyui-combobox" style="width: 150px">
					 	<option value="1">普通预存款</option>
					 	<option value="2">溢收款</option>
					 	<option value="3">赠送</option>
					 	</select>
				 </td>
  				</tr>
  				<tr>
  					<td>&nbsp;</td><td><font id="acctIdHint" color="red">&nbsp;</font></td><td></td><td></td>
  				</tr>
  				<tr>
  					<td></td><td><a href="#" class="easyui-linkbutton" style="width: 65px" onclick="javaScript:submit_form_acctLog();">查询</a></td>
  				</tr>
  			</table>
  		</form><br/><br/>
  		</div>
  		<div id="view_acctBalanceLog_query_result"></div>
  </div>
  
</body>
