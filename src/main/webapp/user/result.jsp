<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%@include file="/common/basicLib.jsp"%>
</head>
<body>
<c:choose>
	<c:when test="${result eq 1 }">
		<script>alert("사용자 등록에 성공헀습니다.");</script>
		<c:redirect url="/userPagingList"/>
	</c:when>
	<c:otherwise>
		<script>alert("사용자 등록에 실패했습니다.");</script>
		<c:redirect url="/userPagingList"/>
	</c:otherwise>
</c:choose>
</body>
</html>
