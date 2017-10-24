<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<title>读者中心</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	<script type="text/javascript">
	
	function ShowDiv(divId){
		$('.main').hide();
		$("#"+divId).show();
	}
	function CheckInput(){
		if ($('#srcpwd').val()==""){
			alert("原密码 不能为空");
			return false;
		}
		if($('#newpwd').val()==""){
			alert("新密码 不能为空");
			return false;
		}
		if($('#newpwd2').val()==""){
			alert("确认新密码不能为空");
			return false;
		}
		if($('#newpwd').val()!=$('#newpwd2').val()){
			alert("两次密码不一致，请重新输入");
			return false;
		}
		return true;
	}
	function Renew(bookCode){
		$.ajax({
			url : "renewBook",
			type : "post",
			data : {"bookCode":bookCode},
			success :function(retVal){
				var result=$.parseJSON(retVal);
				if (result.flag){
					alert(result.msg);
				}
			}
		})
	}
	function myBorrowed(){
		$.ajax({
			url : "myBorrowed",
			type : "post",
			data : {},
			success : function(retVal){
				var result=$.parseJSON(retVal);
				if(result.flag){
					var data=result.list;
					if(data && data.length>0){
						var table=$("<table width='500px' border='0' cellspacing='1' cellpadding='1'></table>");
						var trTop=$("<tr><td width='5%'>序号</td><td width='15%'>图书编号</td><td width='15%'>借书日期</td><td width='15%'>应还日期</td><td width='15%'>是否续借</td><td width='15%'>操作</td></tr>");
						trTop.appendTo(table);
						for (var i=0;i<data.length;i++){
							var str="<a href='#' onclick=\"Renew('"+data[i].bookCode+"')\">续借</a>";
							var trstr="<tr><td>"
								+ (i+1)
								+ "</td><td>"
								+ data[i].bookCode
								+ "</td><td>"
								+ data[i].borrowDate.substr(0,10)
								+ "</td><td>"
								+ data[i].lastDate.substr(0,10)
								+ "</td><td>";
							if(data[i].isRenew==0){
								trstr=trstr+"否</td><td>";
								trstr=trstr+str;
							}
							else
								trstr=trstr+"是</td><td>";
							trstr=trstr+"</td><tr>";
							var tr=$(trstr);
							$(tr).appendTo(table);
						}
						$('#resrult').empty();
						$('#resrult').css('background-color','#e7f4f5');
					}
				}
			}
		});
	}
	$(function(){
		var result =${borrowedBook};
		var data = result.list;
		if (data && data.length>0){
			var table = $("<table width='500px' border='0' cellspacing='1' cellpadding='1'></table>");
			var trTop = $("<tr><td width='5%'>序号</td><td width='15%'>图书编号</td><td width='15%'>借书日期</td><td width='15%'>应还日期</td><td width='15%'>是否续借</td><td width='15%'>操作</td></tr>");
			trTop.appendTo(table);
			for (var i = 0; i < data.length; i++){
				var str="<a href='#' onclick=\"Renew('"+data[i].bookCode+"')\">续借</a>";
				var trstr = "<tr><td>"
					+ (i+1)
					+ "</td><td>"
					+ data[i].bookCode
					+ "</td><td>"
					+ data[i].borrowDate.substr(0,10)
					+ "</td><td>"
					+ data[i].lastDate.substr(0,10)
					+ "</td><td>"
					if(data[i].isRenew==0)	{
						trstr=trstr+"否</td><td>";
						trstr=trstr+str;							
					}else
						trstr=trstr+"是</td><td>";
				trstr =trstr+"</td></tr>";
				var tr=$(trstr);
				$(tr).appendTo(table);
			}
			$('#resrult').empty();
			$('#resrult').css('background-color', '#e7f4f5');
			table.appendTo($('#resrult'));
		}
		
		var options = {
				beforeSubmit : CheckInput,
				//ajax提交之前的处理
				success : function(responseText,statusText){
					var data = JSON.parse(responseText);
					alert(data.msg);
				}
		//处理之后的处理
		};
		$('#chgPwdForm').submit(function(){
			$(this).ajaxSubmit(options);
			return false;
		});
		
		var optionQryBook={
				success : function(responseText,statusText){
					var result=JSON.parse(responseText);
					if (result.flag){
						var data=result.list;
						if (data && data.length>0){
							var table = $("<table width='600px' border='0' cellspacing='1' cellpadding='1'></table>");
							var trTop = $("<tr ><td width='25%'>书名</td><td width='10'>作者</td><td width='10%'>出版社</td><td width='15%'>出版日期</td><td>定价</td><td>库存总量</td><td>当前库存数</td></tr>");
							trTop.appenTo(table);
							for (var i=0;i<data.length;i++){
								var str=JSON.stringify(data[i]);
								var tr=$("<tr><td>")
									+ data[i].bookName
									+ "</td><td>"
									+ data[i].author
									+ "</td><td>"
									+ data[i].publisherId
									+ "</td><td>"
									+ data[i].publishDate.substr(0,10)
									+ "</td><td>"
									+ data[i].price
									+ "</td><td>"
									+ data[i].totalNum
									+ "</td><td>"
									+ data[i].currentNum
									+ "</td></tr>");
								$(tr).appendTo(table);
							}
							$('#qryBookResult').empty();
							$('#qryBookResult').css('background-color','#e7f4f5');
							table.appendTo($('#qryBookResult'));
						}
					}
				}
		};
		$('#qryBookForm').submit(function(){
			$(this).ajaxSubmit(optionQryBook);
			return false;
		});
	})
	</script>
	<body>
		<div id="header">
			<img  src="image/logo.png"/>
			<table>
				<tr>
					<td width="60%" align="right">欢迎你，${sessionScope.currentReader.name}</td>
					<td width="4%"></td>
					<td align="right" width="10%"><a href="#" onclick="ShowDiv('index')">首页</a></td>
					<td width="10%"><a href="#" onclick="ShowDiv('chgpwd')">修改密码</a>
					</td>
					<td width="10%"><a href="#" onclick="ShowDiv('qryBook')">检索图书</a>
					</td>
					<td width="10%"><a href="logout">退出</a></td>
				</tr>
			</table>
		</div>
		<div id="content">
			<div id="index" calss="main">
				<h2>以下是您已经借阅的图书信息</h2>
				<a href="#" onclick="myBorrowed()">刷新</a>
				<div id="resrult"></div>
			</div>
			<div id="chgpwd" class="main">
				<h2>修改密码</h2>
				<form id="chgPwdForm" action="chgPwd" method="post">
					<table>
						<tr>
							<td>原密码：</td>
							<td><input type="password" id="srcpwd" name="srcpwd"/></td>
							<td>新密码：</td>
							<td><input type="password" id="newpwd" name="newpwd"/></td>
							<td>确认新密码：</td>
							<td><input type="password" id="newpwd2" name="newpwd2"/></td>
							<td></td>
							<td><input type="submit" value="提交"/></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>