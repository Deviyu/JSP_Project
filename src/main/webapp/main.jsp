<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp</title>

<%@ include file = "/common/basicLib.jsp" %>
</head>

<body>
	<!-- header -->
	<%@include file = "/common/header.jsp"%>
	<div class="row">
		<!-- left -->
		<%@ include file = "/common/left.jsp" %>
		
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<div class="blog-header">
				<h1 class="blog-title">Main</h1>
				<p class="lead blog-description">Jsp / Spring.</p>
			</div>

			<div class="row">

				<div class="col-sm-8 blog-main">

					<div class="blog-post">
						<h2 class="blog-post-title">JSP</h2>
						<p class="blog-post-meta">2019.06.05 ~ 6.12</p>

						<p>JSP와 서블릿을 이용한 게시판 과제</p>
						<hr>

						<h3>상세내역</h3>
						<p>이 과제를 수행하며 이용한 것들</p>
						<ul>
							<li>JSP</li>
							<li>Servlet</li>
							<li>EL / JSTL</li>
							<li>JUnit</li>
							<li>Oracle</li>
							<li>MyBatis</li>
							<li title="살려줘"><strong>셀 수 없이 많은 에너지 드링크와 커피</strong></li>
						</ul>
					</div>
				</div>
				<!-- /.blog-main -->
			</div>
		</div>
	</div>
	</div>
</body>
</html>
