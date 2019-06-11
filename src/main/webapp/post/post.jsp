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
	textarea{ resize : none; }
	.reply span{vertical-align: middle;}
	h4 {display : inline-block;}
	.pull-right { margin-left : 10px; }
</style>
<title>${boardVO.board_name }</title>

<%@ include file = "/common/basicLib.jsp" %>
<script>
	$(function() {
		$(".delPost").on("click", function(event){
			if(!(confirm("정말 글을 삭제하시겠습니까?"))) {
				event.preventDefault();
			}
		})
		$("form").on("click", ".delReply",function(event){
			if(!(confirm("정말 댓글을 삭제하시겠습니까?"))){
				event.preventDefault();
			}
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
				<div class="col-sm-8">
				<div class="sub-header">
				</div>
					<div class="col-sm-10 row">
					<h4><span class="label label-primary">제목</span></h4>
					<span>
						${postVO.post_title }
					</span>
					
					</div>
					<div class="col-sm-10 row">
					<h4><span class="label label-primary">
					작성일자
					</span></h4>
					<span>
						<fmt:formatDate value="${postVO.post_date }" pattern="yyyy-MM-dd hh:mm:ss"/>
					</span>			
					</div>
					<div class="col-sm-10 row">
						<h4><span class="label label-primary">
							글 내용
						</span></h4>
						<br>
						<div class="content">
							${postVO.post_content }
						</div>
					</div>
					<br>
					
					<div class="col-sm-10 row">
					<br>
						<a href="${pageContext.request.contextPath }/post?board_Id=${boardVO.board_id}" class="btn btn-default pull-right">목록</a>
					<c:if test="${USER_INFO ne null}">
						<a href="${pageContext.request.contextPath }/postForm?board_Id=${boardVO.board_id}&replyTo=${postVO.post_Id}" class="btn btn-default pull-right">답글</a>
					</c:if>
					<c:if test="${USER_INFO.userId eq postVO.userId}">
						<a href="${pageContext.request.contextPath }/postDel?post_Id=${postVO.post_Id}" class="btn btn-default pull-right delPost">삭제</a>
						<a href="${pageContext.request.contextPath }/postMdf?post_Id=${postVO.post_Id}" class="btn btn-default pull-right mdfPost">수정</a>
					</c:if>
					</div>
					<div class="row"></div>
					<label class="col-sm-1 control-label text-center label label-primary">
						<h6><strong>첨부파일</strong></h6>
					</label>
					<div class="col-sm-8">
					<c:forEach items="${appendList }" var="append">
						<a href="${pageContext.request.contextPath }/getAppend?append_id=${append.append_id }">${append.append_filename }</a>
						<br>
					</c:forEach>
					</div>
				<div class="row"><br></div>
				
				<!-- 댓글부분 -->
				<c:if test="${empty replyList }">
					<div class="text-center">
						<h5>작성된 댓글이 없습니다.</h5>
					</div>
				</c:if>
				<c:forEach var="reply" items="${replyList }">
				<br>
				<c:choose>
					<c:when test="${reply.reply_usable eq 'N' }">
					<br>
						<label class="col-sm-2 control-label text-center"></label>
					<div class="col-sm-8">
						<h5>삭제된 댓글입니다.</h5>
					</div>
					</c:when>
					<c:otherwise>
				<br>
				<form action="${pageContext.request.contextPath }/delReply">
					<div class="form-group">
							<label class="col-sm-2 control-label text-center">${reply.userid }</label>
							<div class="col-sm-7">${reply.reply_content }</div>
							<div class="col-sm-2">
								<fmt:formatDate value="${reply.reply_date }" pattern="yyyy-MM-dd HH:mm"/>
							</div>
							<c:if test="${reply.userid eq USER_INFO.userId }">
								<div class="col-sm-1">
									<input type="hidden" name="reply_id" value="${reply.reply_id }">
									<button type="submit" class="label label-danger delReply">삭제</button>
								</div>
							</c:if>
					</div>
				</form>
					</c:otherwise>
				</c:choose>
				</c:forEach>
				<br>
				<br>
				<div class = "sub-header"></div>
				<div class="row"></div>
					<form id="replyfrm"action="${pageContext.request.contextPath }/replyInsert" method="post">
						<div class="form-group">
								<label class="col-sm-2 control-label text-center">댓글</label>
							<div class="col-sm-8">
								<textarea id="reply_content" name="reply_content" cols=65 rows=6 maxlength="500"></textarea>
								<input type="hidden" name="post_Id" value="${postVO.post_Id }">
							</div>
							<div class="col-sm-1">
								<button type="submit" class="btn btn-lg pull-right btn-primary">작성</button>
							</div>
						</div>
					</form>
	    		</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
