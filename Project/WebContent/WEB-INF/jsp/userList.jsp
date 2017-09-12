<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="model.User"%>
<%@ page import="java.util.List"%>
<%
	List<User> userList = (List<User>) request.getAttribute("userList");
	User u =(User)session.getAttribute("loginUser");
	int i = u.getId();
	String formLogin_id = "";
	String formUserName = "";

	if(request.getAttribute("formWord")==null){
		formLogin_id = "LoginID";
		formUserName = "User name";
	}else{
		User form = (User)request.getAttribute("formWord");
		formLogin_id = form.getLogin_id();
		formUserName = form.getName();
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ユーザ一覧</title>
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
				<h1 class="text-center">ユーザ一覧</h1>
			</div>

		</div>
		<div class="main">
			<%if(request.getAttribute("noRecord")==null){
			}else if((boolean)request.getAttribute("noRecord")){
				out.println("<div class='col-xs-8 col-sm-offset-2'><div class='alert alert-danger' role='alert'><strong>No Record</strong>：　一致するレコードはありませんでした</div></div>");
			}%>

			<div class="col-xs-11 col-md-offset+1">
				<div class="btn-group pull-right" role="group">
					<a href="./UserCreateServlet" class="btn btn-default">新規登録</a>
				</div>
			</div>

			<div class="col-xs-12">
				<form class="form-horizontal" action="./SearchServlet" method="post">

					<div class="form-group">
						<label class="col-sm-3 control-label" for="InputEmail">Login ID</label>
						<div class="col-sm-7">
							<input class="form-control" id="InputLoginID" name="SearchLoginId" placeholder="LoginID" value="<%=formLogin_id%>">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="InputUserName">User name</label>
						<div class="col-sm-7">
							<input class="form-control" id="InputUserName" name="SearchName" placeholder="User name" value="<%=formUserName%>">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="InputUserName">Birthday</label>
						<div class="col-sm-3">
							<input type="date" class="form-control" id="InputBirthday" name="birth01" placeholder="年/月/日">
						</div>
						<div class="col-sm-1">
							<h4 class="text-center">～</h4>
						</div>
						<div class="col-sm-3">
							<input type="date" class="form-control" id="InputBirthday" name="birth02" placeholder="年/月/日">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-7 col-md-offset-3">
							<button type="submit" class="btn btn-default btn-block">Search</button>
						</div>
					</div>

				</form>
			</div>
			<div class="col-xs-10 col-xs-offset-1">
				<hr>

				<table class="table">
					<thead>
						<tr>
							<th>ID</th>
							<th>User name</th>
							<th>Birthday</th>
							<th></th>
						</tr>
					</thead>
					<tbody>

					<c:forEach var="User" items="${userList}">
						<tr>
							<th>${User.id}</th>
							<td>${User.name}</td>
							<td>${User.birth_date}</td>
							<td>
								<a href='./UserDetailServlet?id=${User.id}' class='btn btn-primary'>詳細</a>
								<c:if test="${loginUser.id == 1}">
									<a href='./UserUpdateServlet?id=${User.id}' class='btn btn-success'>更新</a>
									<a href='./UserDeleteServlet?id=${User.id}' class='btn btn-danger'>削除</a>
								</c:if>
								<c:if test="${loginUser.id == User.id}">
									<a href='./UserUpdateServlet?id=${User.id}' class='btn btn-success'>更新</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>