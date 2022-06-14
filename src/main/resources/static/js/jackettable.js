$(function (){
	$('#table').bootstrapTable({
			locale:'zh-CN',
			ajax : function (request) {
				$.ajax({
					type : "post",
					url : "/jacket/GetJacket",
					contentType: "application/json;charset=utf-8",
					dataType : 'json',
					data : JSON.stringify(queryParams()),
					success : function (msg) {
						request.success({
							row : msg
						});
						$('#table').bootstrapTable('load', msg);
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
			pageSize: 25,
			pageList: [10, 25, 50, 100],
			striped: true,
			sortName:'jacketId',
			sortOrder: 'desc',
			height: 680,
			uniqueId: 'jacketId',
			cardView: false,
			searchOnEnterKey: true,
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
				field: 'jacketId',
				title: '样衣编号',
				sortable: true,
				searchable: true,
				sorter: function priceSorter(a, b) {
					var tmp1 = a.substring(0,2);
					var tmp2 = b.substring(0,2);
					if (tmp1 > tmp2) return 1;
					if (tmp1 < tmp2) return -1;
					a = a.substring(2,5);
					b = b.substring(2,5);

					var n1 = parseInt(a);
					var n2 = parseInt(b);
					if (!isNaN(n1) && !isNaN(n2))
					{
						if (n1 > n2) return 1;
						if (n1 < n2) return -1;
					}

					n1 = parseInt(a.substring(5));
					n2 = parseInt(a.substring(5));
					if (!isNaN(n1) && !isNaN(n2))
					{
						if (n1 > n2) return 1;
						if (n1 < n2) return -1;
					}
					return 0;
				}
			},
			{
				field: 'type',
				title: '样衣类型',
				sortable: false
			},
			{
				field: 'designer',
				title: '设计师',
				searchable: true,
				sortable: false
			},
			{
				field: 'editor',
				title: '版师',
				searchable: true,
				sortable: false
			},
			{
				field: 'tailor',
				title: '样衣师',
				searchable: true,
				sortable: false
			},
			{
				field: 'attribute',
				sortable: true,
				searchable: false,
				sorter: function(a,b){return a.localeCompare(b)},
				title: '性质'
			},
			{
				field: 'status',
				sortable: true,
				searchable: false,
				sorter: function(a,b){return a.localeCompare(b)},
				title: '状态'
			},
			{
				field: 'createDate',
				title: '入库时间',
				searchable: false,
				sortable: true,
                sorter: function(a,b){
                    var tmp1 = a.substring(0,4);
                    var tmp2 = b.substring(0,4);
                    if (tmp1 > tmp2) return 1;
                    if (tmp1 < tmp2) return -1;
                    n1 = a.substring(5,7);
                    n2 = b.substring(5,7);
                    if (n1 > n2) return 1;
                    if (n1 < n2) return -1;

                    n1 = a.substring(8);
                    n2 = b.substring(8);
                    if (n1 > n2) return 1;
                    if (n1 < n2) return -1;
                    return 0;
                }
			},
			{
				field: 'customer',
				title: '客户',
				editable: {
					type: 'text',
					title: '输入客户姓名：',
					validate: function (value) {
						   if ($.trim(value) == '') {
							   return '不能提交空的名称!';
						   }
					   }
			   },
				searchable: true
			},
			{
				field: 'saleDate',
				searchable: false,
				editable: {
					type: 'datetime',
					 format: 'yyyy-mm-dd',
					 viewformat: 'yyyy-mm-dd',
					 datetimepicker: {
							 language: 'zh-CN',
							 autoclose: true,
							 todayBtn: true,
							 minView: 'month',
							 pickerPosition: 'bottom-left'
						}

				 },
				title: '出库时间',
                sortable: true,
                sorter: function(a,b){
                    if (a == null) return -1;
                    if (b == null) return 1;
                    var tmp1 = a.substring(0,4);
                    var tmp2 = b.substring(0,4);
                    if (tmp1 > tmp2) return 1;
                    if (tmp1 < tmp2) return -1;
                    n1 = a.substring(5,7);
                    n2 = b.substring(5,7);
                    if (n1 > n2) return 1;
                    if (n1 < n2) return -1;

                    n1 = a.substring(8);
                    n2 = b.substring(8);
                    if (n1 > n2) return 1;
                    if (n1 < n2) return -1;
                    return 0;
                }

			},
			{
				field: 'comment',
				searchable: true,
				title: '备注',
				editable: {
					type: 'textarea',
					title: '输入备注内容：',
					validate: function (value) {
					   if ($.trim(value) == '') {
						   return '不能提交空的备注!';
					   }
					}
				 }
			}],
			onEditableSave: function (field, row, oldValue, $el) {
				$('#table').bootstrapTable('resetView');
				$.ajax({
					type: "post",
					url: "/jacket/UpdateJacket",
					contentType: "application/json;charset=utf-8",
					dataType : 'json',
					data:JSON.stringify(row) ,
					success: function (data, status) {
						if (status == "success") {
							alert('提交数据成功');
						}
					},
					error: function () {
						alert('编辑失败');
						console.log($el);
						console.log(oldValue);
						if ("undefined" === typeof oldValue) {
							console.log("oldValue is undefined");
							oldValue = '-';
						}
						$el[0].innerText = oldValue;

					},
					complete: function () {

					}

				});
			}
	});

	}
)
