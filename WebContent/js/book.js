function DelBook(bookId) {
	if (confirm("确定要删除该图书信息吗？"))
		$.ajax({
			url : "delBook",
			type : "post",
			data : {
				"bookId" : bookId
			},
			success : function(result) {
				var data = $.parseJSON(result);
				if (data.flag == false) {
					alert(data.msg);
				} else
					alert("删除图书信息成功");
			}
		});
}
function ModifyBook(book) {
	ShowDiv('modBook');
	InitBookType('modBook',book.bookTypeId);
	InitPublisher('modBook',book.publisherId);
	$("#modId").val(book.id);
	$("#modISBN").val(book.ISBN);
	$("#modBookName").val(book.bookName);
	$("#modTotalNum").val(book.totalNum);
	$("#modCurrentNum").val(book.currentNum);
	if(book.totalNum!=book.currentNum){
		$("#modTotalNum").attr("readonly",true);
		$("#spanModTotalNum").html("该图书已借出 "+(book.totalNum-book.currentNum)+" 本,不能修改库存量");
		$("#modbookTypeId").attr("disabled",true);
		$("#spanModBookTypeId").html("该图书已借出 "+(book.totalNum-book.currentNum)+" 本,不能修改图书类型");
	}
	$("#modPublishDate").val(book.publishDate.substr(0,10));
	$("#modPrice").val(book.price);
	$("#modAuthor").val(book.author);
}
function checkBookInput() {
	if ($('#ISBN').val() == "") {
		alert("ISBN 不能为空");
		return false;
	}
	if ($('#bookName').val() == "") {
		alert("书名 不能为空");
		return false;
	}
	if ($('#author').val() == "") {
		alert("作者 不能为空");
		return false;
	}
	if ($('#price').val() == "") {
		alert("定价 不能为空");
		return false;
	}
	if ($('#totalNum').val() == "") {
		alert("入库数量 不能为空");
		return false;
	}
	return true;
}
function checkBorrowInput() {
	if ($('#borrowReaderId').val() == "") {
		alert("读者卡号 不能为空");
		return false;
	}
	if ($('#bookCode').val() == "") {
		alert("图书编号 不能为空");
		return false;
	}
	return true;
}
function checkReturnInput() {
	if ($('#retBookCode').val() == "") {
		alert("图书编号 不能为空");
		return false;
	}
	return true;
}
function InitBookType(divId,bookTypeId) {
	var pre = "";
	if (divId == 'qryBook')
		pre = "qry";
	if (divId == 'modBook')
		pre = "mod";
	$.ajax({
		url : "initBookType",
		type : "post",
		data : {},
		success : function(retVal) {
			var result = $.parseJSON(retVal);
			if (result.flag) {
				var data = result.list;
				if (data && data.length > 0) {
					$("#" + pre + "bookTypeId").empty();					
					for (var i = 0; i < data.length; i++) {
						var str = JSON.stringify(data[i]);
						var select = "";
						if(data[i].typeId==bookTypeId)
							select = "selected";
						var opt = "<option value=" + data[i].typeId + " "+ select +" >"
								+ data[i].typeName + "</option>";
						$("#" + pre + "bookTypeId").append(opt);
					}
				}
			}
		}
	});
}
function InitPublisher(divId,pubId) {
	var pre = "";
	if (divId == 'qryBook')
		pre = "qry";
	if (divId == 'modBook')
		pre = "mod";
	$.ajax({
		url : "initPublish",
		type : "post",
		data : {},
		success : function(retVal) {
			var result = $.parseJSON(retVal);
			if (result.flag) {
				var data = result.list;
				if (data && data.length > 0) {
					$("#" + pre + "publisherId").empty();
					for (var i = 0; i < data.length; i++) {
						var select = "";
						var str = JSON.stringify(data[i]);
						if(data[i].pubId==pubId)
							select="selected"
						var opt = "<option value=" + data[i].pubId + " " + select +" >"
								+ data[i].pubName + "</option>";
						$("#" + pre + "publisherId").append(opt);
					}
				}
			}
		}
	});
}
function InitBookDiv(divId) {
	ShowDiv(divId);
	InitBookType(divId);
	InitPublisher(divId);
}