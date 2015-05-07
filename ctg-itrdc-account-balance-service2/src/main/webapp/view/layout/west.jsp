<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$.ajax({  
        async:false,  
        type:"POST",  
        url:'${pageContext.request.contextPath}/system/menuAction.action',  
        dataType:"json",  
        cache: false,
        success:function(datas){
        	if(datas){
        		for(var i=0; i<datas.length; i++){
        			var data = datas[i];
        			$("#view_layout_west_accordion").accordion('add',{
			            title: data.attributesMenu.itemName,
			            selected: false,
			            content:"<ul id=\""+data.attributesMenu.itemId+"\"></ul>"
			        });
			        $("#"+data.attributesMenu.itemId+"").tree({
						data:data.listMenu,
						onClick : function(treeData) {
							if (treeData.attributes.url) {
								addTab({
									"title" : treeData.text,
									"href" : '${pageContext.request.contextPath}' + treeData.attributes.url
								});
							}
						}
					});
        		}
        	}
        }
    }); 
	});
</script>
<div class="easyui-panel" title="菜单" data-options="border:false,fit:true">
	<div id="view_layout_west_accordion" class="easyui-accordion" data-options="fit:true,border:false">
	</div>
</div>
