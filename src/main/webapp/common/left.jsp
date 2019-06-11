<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li class="active"><a href="${pageContext.request.contextPath}/boardList">게시판 관리</a></li>
		<c:forEach items="${boardList }" var="vo">
				<c:if test="${vo.board_usable eq 'Y' }">
					<li class="active"><a href="${pageContext.request.contextPath}/post?board_Id=${vo.board_id}">${vo.board_name }</a></li>
				</c:if>
		</c:forEach>
	</ul>
</div>