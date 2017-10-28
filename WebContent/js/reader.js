function DelReader(readerId){
	if(confirm("确定要删除该读者吗?"))
		$.ajax({
			url : "delReader",
			typr : "post",
			data : {
				"readerId" : readerId
			},
			success : function(result){
				var data = $.parseJSON(result);
				if(data.flag==false){
					alert(data.msg);
				}else
					alert("删除读者成功");
			}
		});
}
function getBorrowed(){
	var readerId=$("#borrowReaderId").val();
	if(readerId!=""){
		$.ajax({
			url : "getBorrowed",
			type : "post",
			data : {
				"readerrId" : readerId
			},
			success : function(retVal){
				var result=$.parseJSON(retVal);
				if(result.flag){
					var data=result.list;
					if(data && data.length>0){
						var table = $("<table width='600px' border='0' cellspacing='1' cellpadding='1'></table>");
						var trTop = $("<tr><td width='5%'>序号</td><td width='15%'>图书编号</td><td width='15%'>借书日期</td><td width='15%'>应还日期</td><td>是否续借</td></tr>");
						trTop.appendTo(table);
						for (var i = 0; i < data.length; i++) {
							var trstr = "<tr><td>"
								+ (i+1)
								+ "</td><td>"
								+ data[i].bookCode
								+ "</td><td>"
								+ data[i].borrowDate.substr(0,10)
								+ "</td><td>"
								+ data[i].lastDate.substr(0,10)
								+ "</td><td>";
							if(data[i].isRenew==0)	{
								trstr=trstr+"否</td><td>";				
							}else
								trstr=trstr+"是</td><td>";
							trstr =trstr+"</td></tr>";
							var tr=$(trstr);
							$(tr).appendTo(table);
						}
						$('#qryBorrowResult').empty();
						$('#qryBorrowResult').css('background-color', '#e7f4f5');
						table.appendTo($('#qryBorrowResult'));
					}
				}
			}
		});
	}
}
function checkReaderInput() {
	if ($('#readerId').val() == "") {
		alert("读者卡号 不能为空");
		return false;
	}
	if ($('#name').val() == "") {
		alert("读者姓名 不能为空");
		return false;
	}
	if ($('#identityNum').val() == "") {
		alert("身份证号码不能为空");
		return false;
	}
	if ($('#readerPwd').val() == "") {
		alert("密码 不能为空");
		return false;
	}
	return true;
}
function InitReaderType(divId,readerTypeId) {
	var pre = "";
	if (divId == 'qryReader')
		pre = "qry";
	$.ajax({
		url : "initReaderType",
		type : "post",
		data : {},
		success : function(retVal) {
			var result = $.parseJSON(retVal);
			if (result.flag) {
				var data = result.list;
				if (data && data.length > 0) {
					$("#" + pre + "readerType").empty();					
					for (var i = 0; i < data.length; i++) {
						var str = JSON.stringify(data[i]);
						var select = "";
						if(data[i].typeId==readerTypeId)
							select = "selected";
						var opt = "<option value=" + data[i].typeId + " "+ select +" >"
								+ data[i].typeName + "</option>";
						$("#" + pre + "readerType").append(opt);
					}
				}
			}
		}
	});
}
function InitReaderDiv(divId) {
	ShowDiv(divId);
	InitReaderType(divId);
}