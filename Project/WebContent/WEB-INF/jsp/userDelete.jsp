<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<%
	User u =(User)session.getAttribute("loginUser");
	User deleteUser = (User)request.getAttribute("deleteUser");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ削除確認</title>
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
				<h1 class="text-center">ユーザ削除確認</h1>
			</div>
		</div>
		<div class="main">

			<div class="col-xs-10 col-xs-offset-1">
				<p>ログインID： <%=deleteUser.getLogin_id() %> を本当に削除してよろしいでしょうか？</p>
				<hr>

				<div class="btn-group btn-group-justified" role="group">
					<a href="./UserListServlet" class="btn btn-default" role="button">キャンセル</a>
					<a href="./DeleteServlet?id=<%=deleteUser.getId() %>"	class="btn btn-default" role="button">OK</a>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

</body>
</html>