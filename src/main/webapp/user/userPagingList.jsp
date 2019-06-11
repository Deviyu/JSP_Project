<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<style>
	.userTr:hover { cursor: pointer; }
</style>
<title>User List(userList.jsp / el+jstl)</title>
<%@include file="/common/basicLib.jsp"%>
<script>
	$(document).ready(function() {
		$(".userTr").on("click", function() {
			//userId를 획득하는 방법
			var userId = $(this).find(".userId").text();
// 			console.log($(this).data("userid"));
			$("#userId").val(userId);
			$("#frm").submit();
		})
	})
	</script>
</head>

<body>
	<!--header-->
	<%@include file="/common/header.jsp"%>
	
	<div class="row">
		<!-- left -->
		<%@include file = "/common/left.jsp"%>
		
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


			<div class="blog-header">
				<h1 class="blog-title">User Paging List</h1>
				<p class="lead blog-description">사용자 페이징 리스트</p>
			</div>

			<div class="row">
				<div class="col-sm-8 blog-main">
					<h2 class="sub-header">List</h2>
					
					<!-- 사용자 상세조회 페이지로 : userId -->
					<form id = "frm" action="${pageContext.request.contextPath }/user" method="get">
						<input type = "hidden" id = "userId" name="userId" />
					</form>
					
					<div class="table-responsive">
						<table class="table table-striped">
							<tr>
								<th>사용자 아이디(JSTL)</th>
								<th>사용자 이름</th>
								<th>사용자 별명</th>
								<th>등록일시</th>
							</tr>
							<c:forEach items="${pagingList}" var="user" varStatus="status">
								<tr class = "userTr" data-userid="${user.userId }">
									<td class="userId">${user.userId }</td>
									<td>${user.name }</td>
									<td>${user.alias }</td>
									<td></td>
								</tr>
							</c:forEach>
						</table>
					</div>

					<a class="btn btn-default pull-right" href = "${pageContext.request.contextPath }/userForm">사용자 등록</a>
					<!-- 
						사용자 수 : 105건
						출력될 페이지 수 : 11건
					 -->
					<div class="text-center">
						<ul class="pagination">
							<c:choose>
								<c:when test="${pageVO.page eq 1 }">
									<li class = "disabled"><span>≪</span></li>	
								</c:when>
								<c:otherwise>
									<li><a href = "${pageContext.request.contextPath}/userPagingList?page=${pageVO.page - 1 }&pageSize=${pageVO.pageSize }">≪</a></li>	
								</c:otherwise>
							</c:choose>
							
							<c:forEach var="i" begin="1" end="${paginationSize }">
								<c:choose>
									<c:when test="${pageVO.page eq i}">
										<li class = "active">
											<span>${i}</span>
										</li>
									</c:when>
									<c:otherwise>
										<li>
											<a href = "${pageContext.request.contextPath}/userPagingList?page=${i}&pageSize=${pageVO.pageSize }">${i}</a>
										</li>	
									</c:otherwise>
								</c:choose>
							</c:forEach>
							
							<c:choose>
								<c:when test="${pageVO.page eq paginationSize}">
									<li	class = "disabled">
										<span>≫</span>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<a href = "${pageContext.request.contextPath}/userPagingList?page=${pageVO.page + 1 }&pageSize=${pageVO.pageSize }">≫</a>
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
	<c:if test="${param.result eq 1 }">
		<script>alert("사용자 등록이 완료되었습니다.")</script>
	</c:if>
