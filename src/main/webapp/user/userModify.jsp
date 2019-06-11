<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<title>사용자 등록</title>
<%@include file="/common/basicLib.jsp"%>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    $(document).ready(function() {
    	//주소 검색 버튼 클릭 이벤트 핸들러
	    $("#searchZip").on("click", function() {
		    new daum.Postcode({
		        oncomplete: function(data) {
		        	$("#zipcd").val(data.zonecode);
		        	$("#addr1").val(data.roadAddress);
		        }
		    }).open();
	    })
	    
	    $("#userMdfBtn").on("click", function() {
	    	console.log("userRegBtn Clicked!");
	    	//유효성 체크
	   
			$("#frm").submit();	    	
	    })
	    
	    $("#filename").on("change", function() {
	    	if(!($("#filename").val())){
	    		$("#changeImg").prop("checked", true);
	    	} else {
	    		$("#changeImg").prop("checked", false);
	    	}
	    })
    });
</script>
</head>

<body>
	<!--header-->
	<%@include file="/common/header.jsp"%>
	
	<div class="row">
		<!-- left -->
		<%@include file = "/common/left.jsp"%>
		
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<div class="row">
				<div class="col-sm-8 blog-main">
					<h2 class="sub-header">사용자 수정</h2>
					
					<form class="form-horizontal" id="frm" role="form" 
							action="${pageContext.request.contextPath }/userModify" 
							method="post" enctype="multipart/form-data">
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">프로필 사진</label>
						<div class="col-sm-10">
							<input type = "file" id="filename" name="filename" accept="image/*"/>
						</div>
						<div class="col-sm-3">
							<input type = "checkbox" id ="changeImg" name="changeImg" value = "changeImg" checked>
							<label for ="changeImg">이미지 변경 안함</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
								<label>${userVO.userId }</label>
								<input type = "hidden" name="userId" value="${userVO.userId }">
<%-- 								<input type="text" class="form-control" id="userId" name="userId" placeholder="사용자 아이디" value = "${userVO.userId }"> --%>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">비밀번호</label>
						<div class="col-sm-10">
								<input type="password" class="form-control" id="pass" name="pass" placeholder="비밀번호" value= "${userVO.pass }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="name" name="name" placeholder="사용자 이름" value = "${userVO.name }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">생일</label>
						<div class="col-sm-10">
							<fmt:formatDate value="${userVO.birth }" var="birth" pattern="yyyy-MM-dd"/>
							<input type="date" class="form-control" id="birth" name="birth" value = "${birth }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="alias" name="alias" placeholder="별명" value = "${userVO.alias }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="zipcd" name="zipcd" placeholder="우편번호" readonly value = ${userVO.zipcd }>
						</div>
						<div class="col-sm-1">
							<button type="button" class="btn btn-default" id="searchZip">주소 검색</button>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr1" name="addr1" placeholder="주소" readonly value = "${userVO.addr1 }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr2" name="addr2" placeholder="상세주소" value = "${userVO.addr2 }">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" id="userMdfBtn" class="btn btn-default">수정 완료</button>
						</div>
					</div>
				</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>