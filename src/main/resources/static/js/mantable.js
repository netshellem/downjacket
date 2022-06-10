$(function (){
	$('#ManTable').bootstrapTable({
			locale:'zh-CN',
			ajax : function (request) {
				$.ajax({
					type : "post",
					url : "/manpower/GetManPower",
					contentType: "application/json;charset=utf-8",
					dataType : 'json',
					success : function (msg) {
						request.success({
							row : msg
						});
						$('#ManTable').bootstrapTable('load', msg);
					},
					error:function(){
						//alert("错误");
					}
				});
			},

			cache:'false',
			pagination:'true',
			sidePagination: 'client',
			clickToSelect: true,
			pageNumber: 1,
			pageSize: 10,
			striped: true,
			uniqueId: 'name',
			cardView: false,
			detailView: false,
			columns: [
			{
				field: 'state',
				checkbox: true
			},
			{
				field: 'id',
				title: '序号',
				formatter: function(value, row,index)
				{
					return index +1;
				}
			},
			{
				field: 'name',
				title: '姓名'
			},
			{
				field: 'type',
				title: '职位'
			}]
	});

	}
)