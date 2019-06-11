<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

<title>게시판 관리</title>
<%@include file="/common/basicLib.jsp"%>
<script>
	$(function() {
		$("#newRegBtn").on("click", function() {
			$("#newFrm").submit();			
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

				<h2 class="sub-header">게시판 생성</h2>
			<div class="row">
				<form id = "newFrm" action="${pageContext.request.contextPath }/insertBoard" method="post">
				  	<div class="form-group">
				  		<div class="col-sm-3">
	      					<input type="text" class="form-control" id="newBoard_name" name="board_name" placeholder="게시판 이름">
				  		</div>
      					<div class="col-sm-2">
							<select id = "board_usable" class="form-control" name="board_usable">
								<option value = "Y">사용</option>
								<option value = "N">미사용</option>
							</select>
						</div>
						<div class="col-sm-1">
								<a class="btn btn-default" id = "newRegBtn">등록</a>
						</div>
    				</div>
				</form>
			</div><br><br>
			
			<h2 class="sub-header">게시판 관리</h2>
				<c:forEach var="board" items="${boardList }">
			<div class="row">
					<form action="${pageContext.request.contextPath }/boardModify" method="post">
						<div class="form-group">
							<div class="col-sm-3">
								<input type="hidden" name="board_Id" value="${board.board_id }">
								<input class = "form-control" type="text" id="board_name" name="board_name" value="${board.board_name }"> 
							</div>
							<div class="col-sm-2">
								<select id = "board_usable" class="form-control" name="board_usable">
									<c:choose>
										<c:when test="${board.board_usable eq 'Y' }">
											<option value = "Y" selected>사용</option>
											<option value = "N">미사용</option>
										</c:when>
										<c:otherwise>
											<option value = "Y">사용</option>
											<option value = "N" selected>미사용</option>
										</c:otherwise>
									</c:choose>
								</select>
							</div>
							<div class="col-sm-1">
								<button class ="btn btn-default" type="submit">수정</button>
							</div>
						</div>
					</form>
			</div>
				</c:forEach>
		</div>
	</div>
</body>
</html>
<c:if test="${param.result eq 1 }">
	<script>alert("추가 / 수정이 완료되었습니다.")</script>
</c:if>