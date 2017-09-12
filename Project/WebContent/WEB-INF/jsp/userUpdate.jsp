<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="model.User"%>
<%
	User u =(User)session.getAttribute("loginUser");
	User u2 = (User)request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ情報更新</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbarEexample1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="./UserListServlet">ユーザ管理システム</a>
			</div>
			<div class="collapse navbar-collapse" id="navbarEexample5">
				<a href="./LogoutServlet" class="btn btn-default navbar-btn navbar-right">Logout</a>
				<p class="navbar-text navbar-right"><%= u.getName()%>さん</p>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="rowHeightEm">
			<div class="col-xs-12" style="height: 5em;"></div>
		</div>
		<div class="pageTitle">
			<div class="col-xs-12" style="height: 9em;">
				<h1 class="text-center">ユーザ情報更新</h1>
			</div>
		</div>
		<div class="main">

		<%if(request.getAttribute("error")==null){
		}else if((boolean)request.getAttribute("error")){
			out.println("<div class='col-xs-8 col-sm-offset-2'><div class='alert alert-danger' role='alert'><strong>Error</strong>：　入力された内容は正しくありません</div></div>");
		}%>

			<div class="col-xs-12">
				<form class="form-horizontal" action="./UpdateServlet?login_id=<%=u2.getLogin_id()%>" method="post">

					<div class="form-group">
						<label class="col-sm-3 control-label" for="InputEmail">Login ID</label>
						<div class="col-sm-7"><p><%= u2.getLogin_id()%></p></div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="InputPassword">Password</label>
						<div class="col-sm-7">
							<input type="password" class="form-control" id="InputPassword" name="pass">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="InputPassword2">Password(確認)</label>
						<div class="col-sm-7">
							<input type="password" class="form-control" id="InputPassword2" name="pass2">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="InputUserName">User name</label>
						<div class="col-sm-7">
							<input class="form-control" id="InputUserName" placeholder="user name" value="<%= u2.getName()%>" name="name">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="InputUserName">Birthday</label>
						<div class="col-sm-7">
							<input type="date" class="form-control" id="InputBirthday" name="birth">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-7 col-md-offset-3">
							<button type="submit" class="btn btn-default btn-block">更新</button>
						</div>
					</div>

				</form>
			</div>
			<div class="col-xs-10 col-xs-offset-1">
				<hr>
				<a href="./UserListServlet" class="btn btn-default">Back</a>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>