// ===========================删除=======================
/**
 * 	"删除"按钮绑定点击事件（传递参数：类型ID）
		1、弹出提示框询问用户是否确认删除
		2、如果是，发送ajax请求后台（类型ID）
			resultInfo对象
			如果删除失败	code=0
				提示用户失败，msg=xxx	
			如果删除成功	code=1
				移除指定tr记录
					给table元素设置id属性值
					判断是否有多条类型记录
						1、通过id属性值，得到表格对象
						2、得到表格对象的子元素tr的数量
						3、判断tr的数量是否等于
							如果等于2，表示只有一条类型集合，删除整个表格，并设置提示内容
							如果大于2，表示有多条类型记录，删除指定tr对象
 * @param typeId
 */

function deleteType(typeId){
	// 弹出提示框询问用户是否确认删除
	swal({ 
		  title: "",  // 标题
		  text: "<h3>您确认要删除该记录吗？</h3>", // 内容
		  type: "warning", // 图标  error	  success	info  warning
		  showCancelButton: true,  // 是否显示取消按钮
		  confirmButtonColor: "orange", // 确认按钮的颜色
		  confirmButtonText: "确定", // 确认按钮的文本
		  cancelButtonText: "取消" // 取消按钮的文本
	}).then(function(){
		// 如果是(确认删除)，发送ajax请求后台（类型ID）
		$.ajax({
			type:"post",
			url:"type",
			data:{
				actionName:"delete",
				typeId:typeId
			},
			success:function(result){
				// 判断是否删除成功
				if (result.code == 1) {
					// 提示成功用户
					swal("","<h3>删除成功！</h3>","success");
					// 执行删除成功之后的DOM操作
					deleteDom(typeId);
				} else {
					// 提示用户失败   swal("标题","内容","图标")
					swal("","<h3>"+result.msg+"</h3>","error");
				}
			}
		});
		
	});
}

/**
 * 删除的DOM操作
 	1、移除指定tr记录
		给table元素设置id属性值、给每个tr添加id属性值  （id="tr_类型ID"）
		判断是否有多条类型记录
			1、通过id属性值，得到表格对象
			2、得到表格对象的子元素tr的数量
			3、判断tr的数量是否等于2
				如果等于2，表示只有一条类型集合，删除整个表格，并设置提示内容
				如果大于2，表示有多条类型记录，删除指定tr对象

	2、删除左侧类型分组的导航栏列表项
		1、给li设置id属性值   （id="li_类型ID"）
		2、通过id选择器获取指定li元素，并移除
 * @param typeId
 */
function deleteDom(typeId) {
	/* 1、移除指定tr记录  */
	// 1.1 、通过id属性值，得到表格对象   1.2、得到表格对象的子元素tr的数量
	var myTable = $("#myTable");
	var trLength = $("#myTable tr").length; // 表格的子元素tr的数量
	// 判断tr的数量是否等于2
	if (trLength == 2) {
		// 如果等于2，表示只有一条类型集合，删除整个表格，并设置提示内容
		$("#myTable").remove();
		// 设置提示信息（设置父元素div的id）
		$("#myDiv").html("<h2>暂未查询到类型数据！</h2>");
	} else {
		// 如果大于2，表示有多条类型记录，删除指定tr对象
		$("#tr_" + typeId).remove();
	}
	
	/* 2、删除左侧类型分组的导航栏列表项 */
	// 1. 给 li 元素设置 id 属性值
	// 2. 通过 id 选择器获取指定的li元素，并移除
	$("#li_" + typeId).remove();
}


//===========================添加/修改=======================

/**
 * 打开添加模态框
 * 绑定"添加类型"按钮的点击事件
 */
$("#addBtn").click(function (){
	// 设置添加模态框的标题
	$("#myModalLabel").html("新增类型");
	// 清空模态框中文本框与隐藏域的值
	$("#typeId").val("");
	$("#typeName").val("");
	// 清空提示信息
	$("#msg").html("");
	// 打开模态框
	$("#myModal").modal("show");
});

/**
 * 打开修改模态框
 * 绑定"修改"按钮的点击事件
 * 设置模态框中的类型名称文本框的id属性值；设置类型id对应的隐藏域，并指定id属性值
 * @param typeId
 */
function openUpdateDialog(typeId) {
	// 设置修改模态框的标题
	$("#myModalLabel").html("修改类型");
	// 得到当前修改按钮对应的类型记录
	// 通过id选择器，获取当前的tr对象
	var tr = $("#tr_"+typeId);
	// 得到tr具体的单元格的值 （第二个td，下标是1）
	var typeName = tr.children().eq(1).text();
	// 将类型名称设置给模态框中的文本框
	$("#typeName").val(typeName);
	// 得到要修改的记录的类型ID （第一个td，下标是0）
	var typeId = tr.children().eq(0).text();
	// 将类型ID设置到模态框中的隐藏域中
	$("#typeId").val(typeId);
	// 清空提示信息
	$("#msg").html("");
	// 打开模态框
	$("#myModal").modal("show");
}



/**
 * 添加类型 或 修改类型
 模态框的"保存"按钮，绑定点击事件
 【添加类型 或 修改类型】
 */
$("#btn_submit").click(function () {
	// 1. 获取参数（文本框：类型名称；隐藏域：类型ID）
	var typeName = $("#typeName").val();
	// 如果是修改操作，则获取类型ID
	var typeId = $("#typeId").val();
	// 2. 判断参数是否为空（类型名称）
	if (isEmpty(typeName)) {
		// 如果为空，提示信息，并return
		$("#msg").html("类型名称不能为空！");
		return;
	}
	// 3. 发送ajax请求后台，执行添加或修改功能，返回ResultInfo对象
	$.ajax({
		type:"post",
		url:"type",
		data:{
			actionName:"addOrUpdate",
			typeName:typeName,
			typeId:typeId
		},
		success:function (result) {
			// 判断是否更新成功
			if (result.code == 1) { // 如果code=1，表示成功，执行DOM操作
				// 1. 关闭模态框
				$("#myModal").modal("hide");
				// TODO
				// 2. 判断类型ID是否为空
				if (isEmpty(typeId)) { // 如果为空，则表示执行添加的DOM操作
					// 添加类型的Dom操作
					addDom(typeName, result.result); // 后台返回的主键
					// 后台返回的主键

				} else { // 如果不为空，则表示执行修改的DOM操作
					// 修改类型的Dom操作
					updateDom(typeName, typeId);
				}
			} else { // 如果code=0，表示失败，提示用户失败
				$("#msg").html(result.msg);
			}
		}
	});
});


/**
 * 添加类型的DOM操作
	 1. 添加tr记录
	 2. 添加左侧类型分组导航栏的列表项
 * @param typeName
 * @param typeId
 */
function addDom(typeName, typeId) {
	/* 1. 添加tr记录 */
	// 1.1. 拼接tr标签
	var tr = '<tr id="tr_'+typeId+'"><td>'+typeId+'</td><td>'+typeName+'</td>';
	tr += '<td><button class="btn btn-primary" type="button" onclick="openUpdateDialog('+typeId+')">修改</button>&nbsp;';
	tr += '<button class="btn btn-danger del" type="button" onclick="deleteType('+typeId+')">删除</button></td></tr>';

	// 1.2. 通过id属性值，获取表格对象
	var myTable = $("#myTable");
	// 1.3. 判断表格对象是否存在 （长度是否大于0）
	if (myTable.length > 0) { // 如果length大于0，表示表格存在
		// 1.4. 将tr标签追加到表格对象中
		myTable.append(tr);
	} else { // 表示表格不存在
		// 拼接table标签及tr标签
		myTable = '<table id="myTable" class="table table-hover table-striped ">';
		// 拼接table标签及tr标签
		myTable += '<tbody> <tr> <th>编号</th> <th>类型</th> <th>操作</th> </tr>';
		myTable += tr + '</tbody></table>';
		// 追加到div中
		$("#myDiv").html(myTable);
	}

	/* 2. 添加左侧类型分组导航栏的列表项 */
	// 2.1. 拼接li元素
	var li = '<li id="li_'+typeId+'"><a href=""><span id="sp_'+typeId+'">'+typeName+'</span> <span class="badge">0</span></a></li>';
	// 2.2 设置ul标签的id属性值，将li元素追加到ul中
	$("#typeUl").append(li);
}


/**
 * 修改类型的DOM操作
 	1、修改指定tr记录
		a、通过id选择器获取tr记录
		b、修改指定单元格的文本值
	2、左侧类型分组导航栏的列表项		
		给左侧类型名添加span标签，并设置id属性值，修改该span元素的文本值	
 * @param typeId
 * @param typeName
 */
function updateDom(typeName, typeId) {
	/* 1、修改指定tr记录 */
	// a、通过id选择器获取tr记录
	var tr = $("#tr_"+typeId);
	// b、修改tr指定单元格的文本值
	tr.children().eq(1).text(typeName);
	/* 2、左侧类型分组导航栏的列表项 */
	// 	给左侧类型名添加span标签，并设置id属性值，修改该span元素的文本值
	$("#sp_"+typeId).html(typeName);
}


