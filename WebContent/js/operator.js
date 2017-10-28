function DelOper(opId){
	if(confirm("确定要删除该管理员吗？"))
		$.ajax({
			url : "delOper",
			type : "post",
			data : {
				"opId" :opId
			},
			success : function(result){
				var data =$.parseJSON(result);
				if (data.flag==false){
					alert(data.msg);
				}else
					alert("删除管理员成功");
			}
		});
}
function CheckLoginName(obj){
	var id=$(obj).attr("id");
	var opId=0;
	var val=$("#"+id).val();
	if (id=="modLoginName"){
		opId=$("#modOperId").val();
	}
	$.ajax({
		url : "checkLoginName",
		type : "post",
		data : {
			"loginName" : val,
			"opId" :opId
		},
		success : function(result){
			var data=$.parseJSON(result);
			if (data.flag==false){
				alert(data.msg);
				$("#"+id).val("");
			}
		}
	});
}
function checkInput(formData, jqForm, options) {
	if ($("#operName").val() == "") {
		alert("管理员姓名不能为空");
		return false;
	}
	if ($("#loginName").val() == "") {
		alert("登录名不能为空");
		return false;
	}
	if ($("#pwd").val() == "") {
		alert("密码不能为空");
		return false;
	}
	// 成功，则提交ajax form
	// 如果验证不成功，则返回非true，不提交
	return true;
}
function checkModInput(formData, jqForm, options) {
	if ($("#modOperName").val() == "") {
		alert("管理员姓名不能为空");
		return false;
	}
	if ($("modLoginName").val() == "") {
		alert("登录名不能为空");
		return false;
	}
	if ($("#modPwd").val() == "") {
		alert("密码不能为空");
		return false;
	}
	// 成功，则提交ajax form
	// 如果验证不成功，则返回非true，不提交
	return true;
}
function ModifyOper(oper){
	showDiv('modOper');
	$("#modOperId").val(oper.operId);
	$("#modOperName").val(oper.operName);
	if(oper.gender=="男")
		$("#modGender1").attr('checked',true);
	else
		$("#modGender2").attr('checked',true);
	$("#modLoginName").val(oper.loginName);
	$("#modPwd").val(oper.pwd);
	$("#modPhone").val(oper.phone);
}