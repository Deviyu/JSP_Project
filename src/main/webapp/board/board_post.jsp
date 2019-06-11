<%@page import="kr.or.ddit.user.model.UserVO"%>
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
<style>
	.postTr:hover {cursor:pointer;}
	td .disabled { color : #c1c1c1; text-align: center;}
	tr td a:hover { text-decoration: none; }
</style>
<title>${boardVO.board_name }</title>

<%@ include file = "/common/basicLib.jsp" %>
<script>
	$(function() {
		$("#tb").on("click", ".postTr",function() {
			if($(this).find(".disabled").attr("class")!="disabled") {
				$("#post_Id").val($(this).attr("id"));
				$("#frm").submit();
			}
		})
		$("body").on("click", ".newPost", function() {
			
		})
	})
</script>
</head>

<body>
	<!-- header -->
	<%@include file = "/common/header.jsp"%>
	<div class="row">
		<!-- left -->
		<%@ include file = "/common/left.jsp" %>
		
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1>${boardVO.board_name }</h1>

			<div class="row">
				<div class="col-sm-10">
				<form action="${pageContext.request.contextPath }/postDetail" id="frm">
					<input type="hidden" id="post_Id" name="post_Id">
				</form>
				<table class="table table-hover" id="tb">
				    <thead>
				      <tr>
				        <th class="no">No.</th>
				        <th class="title">Title</th>
				        <th class="writer">Writer</th>
				        <th class="date">Date</th>
				      </tr>
				    </thead>
				    <tbody>
	    		<c:choose>
	    			<c:when test="${empty postList }">
	    				<tr>
	    					<td colspan="4">
	    						<a href="${pageContext.request.contextPath }/postForm?board_Id=${boardVO.board_id}">
	    							작성된 게시물이 없습니다. 새로운 게시물을 작성해보세요!
		    					</a>
	    					</td>
	    				</tr>
	    			</c:when>
	    			<c:otherwise>
				    	<c:forEach var="post" items="${postList }">
				    	<tr class="postTr" id="${post.post_Id }">
							    	<c:choose>
							    		<c:when test="${post.post_usable eq 'N' }">
							    			<td class = "disabled" colspan="4">	삭제된 게시물입니다.</td>
							    		</c:when>
							    		<c:otherwise>
							    			<td>${post.seq }</td>
							    			<td>
							    				<c:forEach begin="2" end="${post.lv }">
							    					Re:
							    				</c:forEach>
							    				${post.post_title }
							    			</td>
							    			<td>${post.userId }</td>
							    			<td>
							    				<fmt:formatDate value="${post.post_date }" pattern="yyyy-MM-dd HH:mm"/>
							    			</td>
							    		</c:otherwise>
							    	</c:choose>
								    	</tr>
							    	</c:forEach>
    			</c:otherwise>
	    		</c:choose>
				    </tbody>
				  </table>
				  <a class="btn btn-default pull-right newPost" href="${pageContext.request.contextPath }/postForm?board_Id=${boardVO.board_id}">게시글 등록</a>
				  <div class="container">
				  <div class="text-center">
				  	<ul class="pagination">
				  		
				  			<c:choose>
				  				<c:when test="${page eq 1 }">
									<li class="disabled"><span>≪</span></li>
									<li class="disabled"><span>＜</span></li>				  					
				  				</c:when>
				  				<c:otherwise>
									<li><a href="${pageContext.request.contextPath }/post?board_Id=${boardVO.board_id}&page=1">≪</a></li>				  					
									<li><a href="${pageContext.request.contextPath }/post?board_Id=${boardVO.board_id}&page=${page - 1}">＜</a></li>				  					
				  				</c:otherwise>
				  			</c:choose>
				  			
				  			<!-- 페이징 처리 부분 -->
				  			<c:forEach var="i" begin="1" end="${paginationSize }">
								<c:choose>
								<c:when test="${page eq i }">
									<li class="active"><span>${i }</span></li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.request.contextPath }/post?board_Id=${boardVO.board_id}&page=${i}">${i }</a></li>
								</c:otherwise>
								</c:choose>
				  			</c:forEach>
				  			
				  			<c:choose>
				  				<c:when test="${page eq paginationSize }">
									<li class="disabled"><span>＞</span></li>
									<li class="disabled"><span>≫</span></li>				  					
				  				</c:when>
				  				<c:otherwise>
									<li><a href="${pageContext.request.contextPath }/post?board_Id=${boardVO.board_id}&page=${page + 1}">＞</a></li>				  					
									<li><a href="${pageContext.request.contextPath }/post?board_Id=${boardVO.board_id}&page=${paginationSize}">≫</a></li>				  					
				  				</c:otherwise>
				  			</c:choose>
				  	</ul>
				  </div>
				  </div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
