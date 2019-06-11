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
	h3{display : inline-block; }
	input {display : inline-block; }
	tr td {padding : 5px;}
	td:nth-child(0) {width : 250px;}
</style>
<title>${boardVO.board_name }</title>

<%@ include file = "/common/basicLib.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/SE2/js/HuskyEZCreator.js"></script>
<script>
	var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.
	$(function() {
		// Editor Setting
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors, // 전역변수 명과 동일해야 함.
			elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
			sSkinURI : "${pageContext.request.contextPath }/SE2/SmartEditor2Skin.html", // Editor HTML
			fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
			htParams : {
				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseToolbar : true,
				// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true,
				// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : true, 
			}
		});

		// 전송버튼 클릭이벤트
		$("#save").on("click", function(){
			if(confirm("저장하시겠습니까?")) {
				// id가 smarteditor인 textarea에 에디터에서 대입
				oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);

				// 이부분에 에디터 validation 검증
				if(validation()) {
					$("#frm").submit();
				}
			}
		})
		// 첨부파일 추가버튼 클릭시
			var cnt = 1;
		$("body").on("click", ".addFile", function() {
			var tr = document.createElement("tr");
			$(tr).prop("class","file"+cnt++);
			$(tr).append("<input type='file' name='file'/>");
			$(tr).append("<td><button class='btn btn-sm btn-danger delFile file"+ cnt +"' type='button'>삭제</button></td>");
			$(tr).children().find("input:file").prop("name", "file"+cnt);

			var table_len = $("table tr").length;
			
			if(table_len<5) {
				$("table").append(tr);
			}
		})
		
		$("table").on("click", ".delFile", function() {
			var del = $("#delFiles").val();
			var files = $(this).parent().parent().find("#files").prop("value");
			
			if(files != null) {
				
				if(!del){
					$("#delFiles").val(files);
				} else {
					$("#delFiles").val(del +", "+ files);
				} 
			}
			
			$(this).parent().parent().remove();
		})
	})
	
	// 필수값 Check
	function validation(){
		var contents = $.trim(oEditors[0].getContents());
		if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
			alert("내용을 입력하세요.");
			oEditors.getById['smarteditor'].exec('FOCUS');
			return false;
		}
	
		return true;
	}

</script>
</head>

<body>
	<!-- header -->
	<%@include file = "/common/header.jsp"%>
	<div class="row">
		<!-- left -->
		<%@ include file = "/common/left.jsp" %>
		
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1>게시글 수정</h1>
			<div class="sub-header"></div>
			<br>
			<div class="row">
			
				<form id="frm" method="post" action="${pageContext.request.contextPath }/postMdf" enctype="multipart/form-data">
				<div class="form-group row">
					<label class="col-sm-2 control-label text-center">게시판</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" value="${boardVO.board_name }" readonly>
						<input type="hidden" class="form-control" value="${postVO.post_Id }" name="post_id">
						<br>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label text-center">답글</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" value="${reply_PostVO.post_title }" readonly>
						<input type="hidden" class="form-control" value="${postVO.replyTo }" name="replyTo" readonly>
						<br>
					</div>
				</div>
				<div class="form-group row">
						<label class="col-sm-2 control-label text-center">제목</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="post_title" name="post_title" value="${postVO.post_title }">
					</div>
					<br>
				</div>
				<br>
				<div class="form-group row">
					<label class="col-sm-2 control-label text-center">내용</label>
					<div class="col-sm-8">
						<textarea name="smarteditor" id="smarteditor" rows="10" cols="100" style="width:745px; height:412px;">${postVO.post_content }</textarea>
						<br> 			
					</div>
				</div>
				<br>
				<div class="col-sm-12 row">
						<label class="col-sm-2 control-label text-center">첨부파일</label>
				<table>
					<c:forEach items="${appendList }" var="append" varStatus="status">
						<tr class="file${status.index }">
							<td><label>${append.append_filename }</label></td>
							<td><button class="btn btn-sm btn-danger delFile" type="button">삭제</button></td>
							<td><input type="hidden" id="files" name="files" value="${append.append_id }"/>
					</tr>
					</c:forEach>
				</table>
					<input type="hidden" id="delFiles" name="delFiles">
				</div>
					<div class="col-sm-5">
						<button class="btn btn-sm btn-success pull-right col-sm-7 addFile"type="button">첨부파일 추가</button>
					</div>
					<input type="hidden" id="board_Id" name="board_Id" value="${boardVO.board_id }">
				</form>
				<br>
				<br>
				<div class="col-sm-8">
				 	<a class="btn btn-default pull-right newPost" type="button" id="save">게시글 등록</a>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
