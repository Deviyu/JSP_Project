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
	    
	    //사용자 등록 버튼 클릭 이벤트 핸들러
	    $("#userRegBtn").on("click", function() {
	    	console.log("userRegBtn Clicked!");
	    	//유효성 체크
	    	
	    	//여기까지 도달하면 유효성 검사 완료(submit)
			$("#frm").submit();	    	
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
					<h2 class="sub-header">사용자 등록</h2>
					
					<form class="form-horizontal" id="frm" role="form" action="${pageContext.request.contextPath }/userForm" method="post" enctype="multipart/form-data">
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">프로필 사진</label>
						<div class="col-sm-10">
							<input type = "file" id="filename" name="filename" value = "${param.filename }"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
								<input type="text" class="form-control" id="userId" name="userId" placeholder="사용자 아이디" value = "${param.userId }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">비밀번호</label>
						<div class="col-sm-10">
								<input type="password" class="form-control" id="pass" name="pass" placeholder="비밀번호">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="name" name="name" placeholder="사용자 이름" value = "${param.name }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">생일</label>
						<div class="col-sm-10">
							<input type="date" class="form-control" id="birth" name="birth" value = "${param.birth }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="alias" name="alias" placeholder="별명" value = "${param.alias }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="zipcd" name="zipcd" placeholder="우편번호" readonly value = ${param.zipcd }>
						</div>
						<div class="col-sm-1">
							<button type="button" class="btn btn-default" id="searchZip">주소 검색</button>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr1" name="addr1" placeholder="주소" readonly value = "${param.addr1 }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr2" name="addr2" placeholder="상세주소" value = "${param.addr2 }">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" id="userRegBtn" class="btn btn-default">사용자 등록</button>
						</div>
					</div>
				</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
	<c:if test="${duplId == 'true' }">
		<script>alert("이미 존재하는 아이디입니다.")</script>
	</c:if>
