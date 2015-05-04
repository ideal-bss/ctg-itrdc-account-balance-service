<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String rootPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>

 <body>
 <script type="text/javascript">
 (function($) {  
    function load(context) {  
        var nodeName = context.nodeName;  
        var href = $(context).attr("href");  
        console.log("nodeName=>" + nodeName + "; href=>" + href);  
        if (nodeName != "a" && nodeName != "A" && href != "") {  
            $(context).load(href);  
        }  
    }  
  
    $(".page").each(function() {  
        //load content  
        load(this);  
        //auto reload content when href changed  
        var self = this;  
        $(self).bind("hrefChange", function() {  
            load(self);  
        });  
    });  
})(jQuery);  
 function submit_form(){
 	$.ajax({  
        async:false,  
        type:"POST",  
        url:"<%=rootPath%>/balanceType/balanceTypeQuery.action",  
        dataType:"json",  
        cache: false,
        data:$('#form_select').serialize(),  
        success:function(data){
        	//$.messager.alert("提示", "查询成功!");
        	$('#balanceTypeQueryResult').datagrid({
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
		<form id="form_select" >
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
		<div id="balanceTypeQueryResult" ></div>
		</div>
</body>
</html>