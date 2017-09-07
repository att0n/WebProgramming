<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.user"%>
<%@ page import="java.util.ArrayList" %>
<%
	request.setCharacterEncoding("UTF-8");

	ArrayList<user> u = (ArrayList<user>)request.getAttribute("userList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ログイン画面</title>
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
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">ユーザ管理システム</a>
			</div>
			<div class="collapse navbar-collapse" id="navbarEexample5">
				<p class="navbar-text navbar-right">ようこそ ゲスト さん。</p>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
	<div class="rowHeightEm"><div class="col-xs-12" style="height: 5em;"></div></div>
	<div class="pageTitle">
		<div class="col-xs-12" style="height: 9em;"><h1 class="text-center">ログイン画面</h1></div>
	</div>
	<div class="main">
		<div class="col-xs-8 col-sm-offset-2">
			<div class="alert alert-danger" role="alert"><strong>Error</strong>：　ログインIDが存在しない　もしくは　ログインIDまたはパスワードの組み合わせが異なっています。</div>
		</div>
		<div class="col-xs-12">
			<form class="form-horizontal" action="./LoginSarvlet2" method="post">

				<div class="form-group">
					<label class="col-sm-2 control-label" for="InputEmail">Login ID</label>
					<div class="col-sm-8">
						<input class="form-control" id="InputLoginID" placeholder="LoginID" name="loginID">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="InputPassword">Password</label>
					<div class="col-sm-8">
						<input type="Password" class="form-control" id="InputPassword" placeholder="Password" name="password">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">送信</button>
					</div>
				</div>

			</form>
		</div>
	</div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>