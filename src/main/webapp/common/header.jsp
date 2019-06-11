<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath }/login">JSP/SPRING</a>
			<!--  사용자가 로그인하지 않고 메인화면(localhost/jsp/main.jsp)에 접속했을 때 -->
			<!--  '접속하지 않은 사용자입니다' 라는 문구를 표현 -->
			<!--  사용자가 정상적으로 로그인 했을 때 -->
			<!--  USER_INFO에 있는 name속성 출력 -->
			<c:choose>
				<c:when test="${USER_INFO.name eq null }">
					<a class="navbar-brand" href="${pageContext.request.contextPath }/login" title = "로그인해주세요.">로그인하지 않은 사용자입니다.</a>
				</c:when>
				<c:otherwise>
					<a class="navbar-brand" href="${pageContext.request.contextPath }/login">${USER_INFO.name }님 환영합니다.</a>
				</c:otherwise>
			</c:choose>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">Dashboard</a></li>
				<li><a href="#">Settings</a></li>
				<c:choose>
					<c:when test="${USER_INFO eq null }">
						<li><a href="#">Profile</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath }/user?userId=${USER_INFO.userId}">Profile</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${USER_INFO eq null}">
						<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search...">
			</form>
		</div>
	</div>
</nav><div class="container-fluid">
