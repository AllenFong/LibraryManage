<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%><html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
<title>管理员操作中心</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/operator.js"></script>
<script type="text/javascript" src="js/book.js"></script>
<script type="text/javascript" src="js/reader.js"></script>
<script type="text/javascript">
	function ShowMenu(tagId) {
		$('.mainMenu').css("backgroundColor", "RGB(216,216,216)");
		$('.sub').hide();
		$("#" + tagId).parent().css("backgroundColor", "#EEEEEE");
		;
		$("#" + tagId).show();
	}
	$(function() {
		$('.sub').hide();//初始时 隐藏二级菜单	
		$('.main').hide();//初始时 隐藏各功能面板
		$('#index').show();
		var options = {
			//target: '#output1',
			// 从服务传过来的数据显示在这个div内部 也就是ajax局部刷新
			beforeSubmit : checkInput,
			// ajax提交之前的处理
			success : function(responseText, statusText) {
				var data = JSON.parse(responseText);
				alert(data.msg);
			}
		// 处理之后的处理
		};
		$('#newOperForm').submit(function() {
			$(this).ajaxSubmit(options);
			return false;
			//非常重要，如果是false，则表明是不跳转
			//在本页上处理，也就是ajax，如果是非false，则传统的form跳转。
		});
		var optionQryOper = {
			success : function(responseText, statusText) {
				var result = JSON.parse(responseText);
				if (result.flag) {
					var data = result.list;
					if (data && data.length > 0) {
						var table = $("<table width='400px' border='0' cellspacing='1' cellpadding='1'></table>");
						var trTop = $("<tr><td width='10%'>编号</td><td width='20%'>姓名</td><td width='10%'>性别</td><td width='20%'>登录名</td><td width='20%'>电话</td><td>操作</td></tr>");
						trTop.appendTo(table);
						for (var i = 0; i < data.length; i++) {
							var str = JSON.stringify(data[i]);
							var tr = $("<tr><td>"
									+ data[i].operId
									+ "</td><td>"
									+ data[i].operName
									+ "</td><td>"
									+ data[i].gender
									+ "</td><td>"
									+ data[i].loginName
									+ "</td><td>"
									+ data[i].phone
									+ "</td><td><a href='#' onclick='DelOper("
									+ data[i].operId
									+ ")'>删除 </a>&nbsp; <a href='#' onclick='ModifyOper("
									+ str + ")'>修改 </a></td></tr>");
							$(tr).appendTo(table);
						}
						$('#qryOperResult').empty();
						$('#qryOperResult').css('background-color', '#e7f4f5');
						table.appendTo($('#qryOperResult'));
					}
				}
			}
		};
		$('#qryOperForm').submit(function() {
			$(this).ajaxSubmit(optionQryOper);
			return false;
		});

		var optionModOper = {
			beforeSubmit : checkModInput,
			success : function(responseText, statusText) {
				var data = JSON.parse(responseText);
				alert(data.msg);
			}
		};
		$('#modOperForm').submit(function() {
			$(this).ajaxSubmit(optionModOper);
			return false;
		});
		var optionNewBook = {
			beforeSubmit : checkBookInput,
			success : function(responseText, statusText) {
				var data = JSON.parse(responseText);
				alert(data.msg);
			}
		};
		$('#newBookForm').submit(function() {
			$(this).ajaxSubmit(optionNewBook);
			return false;
		});

		var optionQryBook = {
			success : function(responseText, statusText) {
				var result = JSON.parse(responseText);
				if (result.flag) {
					var data = result.list;
					if (data && data.length > 0) {
						var table = $("<table width='600px' border='0' cellspacing='1' cellpadding='1'></table>");
						var trTop = $("<tr><td width='15%'>ISBN</td><td width='20%'>书名</td><td>作者</td><td width='15%'>出版日期</td><td>定价</td><td>库存总量</td><td>当前库存数</td><td>操作</td></tr>");
						trTop.appendTo(table);
						for (var i = 0; i < data.length; i++) {
							var str = JSON.stringify(data[i]);
							var tr = $("<tr><td>"
									+ data[i].ISBN
									+ "</td><td>"
									+ data[i].bookName
									+ "</td><td>"
									+ data[i].author
									+ "</td><td>"
									+ data[i].publishDate.substr(0,10)
									+ "</td><td>"
									+ data[i].price
									+ "</td><td>"
									+ data[i].totalNum
									+ "</td><td>"
									+ data[i].currentNum
									+ "</td><td><a href='#' onclick='DelBook("
									+ data[i].id
									+ ")'>删除 </a>&nbsp; <a href='#' onclick='ModifyBook("
									+ str + ")'>修改 </a></td></tr>");
							$(tr).appendTo(table);
						}
						$('#qryBookResult').empty();
						$('#qryBookResult').css('background-color', '#e7f4f5');
						table.appendTo($('#qryBookResult'));
					}
				}
			}
		};
		$('#qryBookForm').submit(function() {
			$(this).ajaxSubmit(optionQryBook);
			return false;
		});

		var optionModBook = {
			success : function(responseText, statusText) {
				var data = JSON.parse(responseText);
				alert(data.msg);
			}
		};
		$('#modBookForm').submit(function() {
			$("#modbookTypeId").attr("disabled", false);
			$(this).ajaxSubmit(optionModBook);
			return false;
		});
		var optionNewReader = {
			beforeSubmit : checkReaderInput,
			success : function(responseText, statusText) {
				var data = JSON.parse(responseText);
				alert(data.msg);
			}
		};
		$('#newReaderForm').submit(function() {
			$(this).ajaxSubmit(optionNewReader);
			return false;
		});

		var optionQryReader = {
			success : function(responseText, statusText) {
				var result = JSON.parse(responseText);
				if (result.flag) {
					var data = result.list;
					if (data && data.length > 0) {
						var table = $("<table width='500px' border='0' cellspacing='1' cellpadding='1'></table>");
						var trTop = $("<tr><td width='15%'>读者卡号</td><td width='15%'>姓名</td><td width='15%'>读者类型</td><td width='10%'>性别</td><td>已借图书数</td><td width='15%'>电话</td><td width='15%'>操作</td></tr>");
						trTop.appendTo(table);
						for (var i = 0; i < data.length; i++) {
							var tr = $("<tr><td>"
									+ data[i].readerId
									+ "</td><td>"
									+ data[i].name
									+ "</td><td>"
									+ data[i].readerType
									+ "</td><td>"
									+ data[i].gender									
									+ "</td><td>"
									+ data[i].borrowNum
									+ "</td><td>"
									+ data[i].phone
									+ "</td><td><a href='#' onclick='DelReader("
									+ data[i].readerId
									+ ")'>删除 </a></td></tr>");
							$(tr).appendTo(table);
						}
						$('#qryReaderResult').empty();
						$('#qryReaderResult').css('background-color', '#e7f4f5');
						table.appendTo($('#qryReaderResult'));
					}
				}
			}
		};
		$('#qryReaderForm').submit(function() {
			$(this).ajaxSubmit(optionQryReader);
			return false;
		});
		var optionborrow = {
			beforeSubmit : checkBorrowInput,
			success : function(responseText, statusText) {
				var data = JSON.parse(responseText);
				$('#bookCode').val();
				alert(data.msg);
			}
		};
		$('#borrowBookForm').submit(function() {
			$(this).ajaxSubmit(optionborrow);
			return false;
		});

		var optionreturn = {
			beforeSubmit : checkReturnInput,
			success : function(responseText, statusText) {
				var data = JSON.parse(responseText);
				$('#retBookCode').val();
				alert(data.msg);
			}
		};
		$('#returnBookForm').submit(function() {
			$(this).ajaxSubmit(optionreturn);
			return false;
		});
	})
	function ShowDiv(divId) {
		$('.main').hide();
		if (divId == "qryOper")
			$('#qryOperResult').empty();
		if (divId == "qryBook")
			$('#qryBookResult').empty();
		if (divId == "qryReader")
			;$('#qryReaderResult').empty();
		$("#" + divId).show();
	}
	function showRequest(formData, jqForm, options) {
		//formData是数组，就是各个input的键值map数组  
		//通过这个方法来进行处理出来拼凑出来字符串。  
		//formData：拼凑出来的form字符串，比如name=hera&password，  
		//其实就是各个表单中的input的键值对，//如果加上method=XXXX，那也就是相当于ajax内的data。  
		var queryString = $.param(formData);
		alert(queryString + "======" + formData.length);
		for (var i = 0; i < formData.length; i++) {
			alert(formData[i].value + "===============" + formData[i].name);
		} //jqForm，jquery form对象  var formElement = jqForm[0];  alert($(formElement).attr("method"));  alert($(jqForm[0].name).attr("maxlength"));  //非常重要，返回true则说明在提交ajax之前你验证  //成功，则提交ajax form  //如果验证不成功，则返回非true，不提交  return true; } 
	}
</script>
<body>
	<div id="header"><img src="images/logo.png"/></div>
	<div>
		<div id="menu">
			<ul>
				<li class="mainMenu"><a href="#" onclick="ShowDiv('index')">首页</a>
				</li>
				<li class="mainMenu"><a href="#" onclick="ShowMenu('operator')">管理员管理</a>
					<ul id="operator" class="sub">
						<li><a href="#" onclick="ShowDiv('newOper')">新增管理员</a></li>
						<li><a href="#" onclick="ShowDiv('qryOper')">查询与维护管理员</a></li>
					</ul></li>
				<li class="mainMenu"><a href="#"
					onclick="ShowMenu('bookManage')">图书管理</a>
					<ul id="bookManage" class="sub">
						<li><a href="#" onclick="InitBookDiv('newBook')">新书入库</a></li>
						<li><a href="#" onclick="InitBookDiv('qryBook')">查询与维护图书</a></li>
						<li><a href="#" onclick="ShowDiv('borrowBook')">借书</a></li>
						<li><a href="#" onclick="ShowDiv('returnBook')">还书</a></li>
					</ul></li>
				<li class="mainMenu"><a href="#" onclick="ShowMenu('reader')">读者管理</a>
					<ul id="reader" class="sub">
						<li><a href="#" onclick="InitReaderDiv('newReader')">新读者办卡</a></li>
						<li><a href="#" onclick="InitReaderDiv('qryReader')">查询读者</a></li>
					</ul></li>
			</ul>
		</div>
		<div id="content">
			<div id="index" class="main">
				<h2>欢迎你，${sessionScope.currentOperator.operName}</h2>
				<br>请从左侧菜单选择要执行的操作
				<hr>
			</div>
			<div id="newOper" class="main">
				<h3 align="center">新建管理员</h3>
				<h5>请填写如下注册信息,带*号为必填项</h5>
				<form:form id="newOperForm" action="newOper" method="post"
					commandName="operator">
					<table>
						<tr>
							<td>姓名：</td>
							<td><form:input path="operName" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>性别：</td>
							<td><form:radiobutton path="gender" value="男" label="男"
									checked="true" /> <form:radiobutton path="gender" value="女"
									label="女" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>登录名：</td>
							<td><form:input path="loginName"
									onblur="CheckLoginName(this)" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>密码：</td>
							<td><form:password path="pwd" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>电话：</td>
							<td><form:input path="phone" /></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="提交" /></td>
						</tr>
					</table>
				</form:form>
			</div>
			<div id="qryOper" class="main">
				<h3 align="center">查询管理员</h3>
				<h4>请输入要查询的条件(所有条件均支持模糊查询)</h4>
				<form id="qryOperForm" action="qryOper" method="post">
					<table>
						<tr>
							<td width="15%">姓名：</td>
							<td width="35%"><input id="qryOperName" name="qryOperName" /></td>
							<td width="15%">登录名：</td>
							<td><input id="qryLoginName" name="qryLoginName" /></td>
						</tr>
						<tr>
							<td>电话：</td>
							<td><input id="qryPhone" name="qryPhone" /></td>
							<td></td>
							<td><input type="submit" value="查询" /></td>
						</tr>
					</table>
				</form>
				<div id="qryOperResult"></div>
			</div>
			<div id="modOper" class="main">
				<h3 align="center">修改管理员</h3>
				<h5>带*号的选项不能为空</h5>
				<form:form id="modOperForm" action="modifyOper" method="post"
					commandName="operator">
					<form:hidden id="modOperId" path="operId" />
					<table>
						<tr>
							<td>姓名：</td>
							<td><form:input path="operName" id="modOperName" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>性别：</td>
							<td><form:radiobutton id="modGender1" path="gender"
									value="男" label="男" /> <form:radiobutton id="modGender2"
									path="gender" value="女" label="女" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>登录名：</td>
							<td><form:input id="modLoginName" path="loginName"
									onblur="CheckLoginName(this)" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>密码：</td>
							<td><form:password id="modPwd" path="pwd" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>电话：</td>
							<td><form:input id="modPhone" path="phone" /></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="提交" /></td>
						</tr>
					</table>
				</form:form>
			</div>
			<div id="newBook" class="main">
				<h3 align="center">图书入库</h3>
				<h5>请填写如下图书信息,带*号为必填项</h5>
				<form:form id="newBookForm" action="newBook" method="post"
					commandName="book">
					<table>
						<tr>
							<td>ISBN：</td>
							<td><form:input path="ISBN" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>书名：</td>
							<td><form:input path="bookName" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>图书类型：</td>
							<td><form:select path="bookTypeId" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>出版社：</td>
							<td><form:select path="publisherId" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>作者：</td>
							<td><form:input path="author" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>出版日期：</td>
							<td><form:input type="date" path="publishDate" /></td>
							<td></td>
						</tr>
						<tr>
							<td>定价：</td>
							<td><form:input path="price" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>入库数量：</td>
							<td><form:input path="totalNum" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="提交" /></td>
						</tr>
					</table>
				</form:form>
			</div>
			<div id="qryBook" class="main">
				<h3 align="center">查询与维护图书信息</h3>
				<h5>请输入查询条件,书名与作者支持模糊查询</h5>
				<form id="qryBookForm" action="qryBook" method="post">
					<table>
						<tr>
							<td>ISBN：</td>
							<td><input name="ISBN" /></td>
							<td>书名：</td>
							<td><input name="bookName" /></td>
						</tr>
						<tr>
							<td>图书类型：</td>
							<td><select id="qrybookTypeId" name="bookTypeId"></select></td>
							<td>出版社：</td>
							<td><select id="qrypublisherId" name="publisherId"></select></td>
						</tr>
						<tr>
							<td>作者：</td>
							<td><input name="author" /></td>
							<td></td>
							<td><input type="submit" value="提交" /></td>
						</tr>
					</table>
				</form>
				<div id="qryBookResult"></div>
			</div>
			<div id="modBook" class="main">
				<h3 align="center">修改图书信息</h3>
				<h5>带*号的选项不能为空</h5>
				<form:form id="modBookForm" action="modBook" method="post"
					commandName="book">
					<form:hidden id="modId" path="id" />
					<form:hidden id="modCurrentNum" path="currentNum" />
					<table>
						<tr>
							<td>ISBN：</td>
							<td><form:input id="modISBN" path="ISBN" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>书名：</td>
							<td><form:input id="modBookName" path="bookName" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>图书类型：</td>
							<td><form:select id="modbookTypeId" path="bookTypeId" /></td>
							<td><span id="spanModBookTypeId" style="color: red">*</span></td>
						</tr>
						<tr>
							<td>出版社：</td>
							<td><form:select id="modpublisherId" path="publisherId" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>作者：</td>
							<td><form:input id="modAuthor" path="author" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>出版日期：</td>
							<td><form:input id="modPublishDate" type="date"
									path="publishDate" /></td>
							<td></td>
						</tr>
						<tr>
							<td>定价：</td>
							<td><form:input id="modPrice" path="price" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>入库数量：</td>
							<td><form:input id="modTotalNum" path="totalNum" /></td>
							<td><span id="spanModTotalNum" style="color: red">*</span></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="提交" /></td>
						</tr>
					</table>
				</form:form>
			</div>
			<div id="borrowBook" class="main">
				<h3 align="center">读者借书</h3>
				<h4>请输入读者卡号 和 图书编号</h4>
				<form id="borrowBookForm" action="borrowBook" method="post">
					<table>
						<tr>
							<td width="15%">读者卡号：</td>
							<td width="35%"><input id="borrowReaderId" name="readerId" onblur="getBorrowed()" /></td>
							<td></td><td></td>
						</tr>
						<tr>
							<td width="15%">图书编号：</td>
							<td><input id="bookCode" name="bookCode" /></td>
							<td></td>
							<td><input type="submit" value="借书" /></td>
						</tr>
					</table>
				</form>
				<div id="qryBorrowResult"></div>
			</div>
			<div id="returnBook" class="main">
				<h3 align="center">还书</h3>
				<h4>请输入 图书编号</h4>
				<form id="returnBookForm" action="returnBook" method="post">
					<table>
						<tr>
							<td width="25%">图书编号：</td>
							<td><input id="retBookCode" name="bookCode" /></td>
							<td></td>
							<td><input type="submit" value="还书" /></td>
						</tr>
					</table>
				</form></div>
			<div id="newReader" class="main">
				<h3 align="center">新读者办卡</h3>
				<h5>请填写如下读者信息,带*号为必填项</h5>
				<form:form id="newReaderForm" action="newReader" method="post"
					commandName="reader">
					<table>
						<tr>
							<td>读者卡号：</td>
							<td><form:input path="readerId" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>姓名：</td>
							<td><form:input path="name" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>密码：</td>
							<td><form:password id="readerPwd" path="pwd" /></td>
							<td><span style="color: red">* 用于登录系统</span></td>
						</tr>
						<tr>
							<td>身份证号码：</td>
							<td><form:input path="identityNum" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>性别：</td>
							<td><form:radiobutton path="gender" value="男" label="男"
									checked="true" /> <form:radiobutton path="gender" value="女"
									label="女" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>读者类型：</td>
							<td><form:select path="readerType" /></td>
							<td><span style="color: red">*</span></td>
						</tr>
						<tr>
							<td>电话：</td>
							<td><form:input path="phone" /></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="提交" /></td>
						</tr>
					</table>
				</form:form>
			</div>
			<div id="qryReader" class="main">
				<h3 align="center">查询读者信息</h3>
				<h4>请输入要查询的条件(姓名和电话支持模糊查询)</h4>
				<form id="qryReaderForm" action="qryReader" method="post">
					<table>
						<tr>
							<td width="15%">读者卡号：</td>
							<td width="35%"><input id="qryReaderId" name="qryReaderId" /></td>
							<td width="15%">姓名：</td>
							<td><input id="qryName" name="qryName" /></td>
						</tr>
						<tr>
							<td width="15%">读者类型：</td>
							<td><select id="qryreaderType" name="qryReaderType"></select></td>
							<td width="15%">证件号码：</td>
							<td width="35%"><input id="qryIdentityNum" name="qryIdentityNum" /></td>							
						</tr>
						<tr>
							<td>电话：</td>
							<td><input id="qryReaderPhone" name="qryReaderPhone" /></td>
							<td></td>
							<td><input type="submit" value="查询" /></td>
						</tr>
					</table>
				</form>
				<div id="qryReaderResult"></div></div>
		</div>
	</div>
</body>
</html>