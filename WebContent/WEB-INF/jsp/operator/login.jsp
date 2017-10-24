<%@ page language="java" pageEncoding="utf-8"%><html>
<head>
<title>管理员登录</title>
</head>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if ("${msg}" != "" && "${msg}" != "success")
			alert("${msg}");
	});
</script>
<body>
	<form action="login" method="post">
		<table border=0 style="width: 100%; height: 100%;">
			<tr>
				<td style="width: 100%;" align="center" valign="middle">
					<table style="background-color: #6633ee; margin: auto">
						<tr>
							<td align="center"><span
								style="font-size: 24pt; color: #ff0066; font-family: 隶书">
									图书馆管理系统</span></td>
						</tr>
						<tr>
							<td style="width: 100%;">
								<table style="background-color: lightskyblue; margin: auto">
									<tr>
										<td align="center" colspan="2">管理员登录</td>
									</tr>
									<tr>
										<td width="64">登录名：</td>
										<td width="180"><input type="text" name="login" /></td>
									</tr>
									<tr>
										<td>密码：</td>
										<td><input type="password" name="pwd" /></td>
									</tr>
									<tr>
										<td></td>
										<td><input type="submit" name="Submit2" value="登录" /> <input
											type="reset" name="Submit" value="重置" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>