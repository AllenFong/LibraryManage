function DelBook(bookId){
	if (confirm("确定要删除该图书信息吗？"))
		$.ajax({
			url : "delBook",
			type : "post",
			data : {
				"bookId" : bookId
			},
			success : function(result){
				var data=$.parseJSON(result);
				if (data.flag == false){
					alert(data.msg);
				}else
					alert("删除图书信息成功")
			}
		});
}
function ModifyBook(book){
	showDiv('modbook');
	InitBookType('modBook',book.bookTypeId);
	InitPublisher('modbook',book.publisgerId);
	$("modId").val(book.id);
}