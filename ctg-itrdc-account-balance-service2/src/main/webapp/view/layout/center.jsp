<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	function addTab(data) {
		t = $('#view_layout_center_tabs');
		if (t.tabs('exists', data.title)) {
			t.tabs('select', data.title);
		} else {
			t.tabs('add', data);
		}
	}
</script>
<div id="view_layout_center_tabs" class="easyui-tabs" data-options="fit:true,border:false"></div>

