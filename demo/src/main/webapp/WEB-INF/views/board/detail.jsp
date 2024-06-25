<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/jquery-3.7.1.min.js"></script>   
<script>
$(document).ready(function(){
	
	location.href=${hash};
	
	$("#deletebtn").on('click', function(){
		let userpw = prompt("글암호를 입력하세요");
		//let dbpw = ${detaildto.pw };
		let dbpw = $("input:hidden").val();
		if(userpw == dbpw){
			location.href = "boarddelete?seq=${detaildto.seq }";
			//$("form").attr("action", "url");
			//$("form").submit();
		}
		else{
			alert("암호를 확인하세요");
		}
	});//on
	
	$("#updatebtn").on('click', function(){
		let userpw = prompt("글암호를 입력하세요");
		//let dbpw = ${detaildto.pw };
		let dbpw = $("input:hidden").val();
		if(userpw == dbpw){ //title, contents, seq, 
			//location.href = "boardupdate?seq=${detaildto.seq }"; //get방식만 전달
			$("form").attr("action", "boardupdate");
			$("form").attr("method", "post");// GET+POST 방식도 전달
			$("form").submit();//form태그 내부 모든 input name변수이름 전달
		}
		else{
			alert("암호를 확인하세요");
		}
	});//on
	
});//ready
</script>
</head>
<body>
<h1>상세게시물 보기 화면입니다.</h1>
<FORM action="" method="">
	<table border=3>
	<tr><td>번호</td><td><input type=text value=${detaildto.seq } readonly name="seq"></td></tr> 
	<tr><td>제목</td><td><input type=text name="title" value='${detaildto.title }' > </td></tr>
	<tr><td>내용</td><td>
	<textarea name="contents" rows=5 cols=50 >
	${detaildto.contents}
	</textarea>
	</td></tr>
	<tr><td>작성자</td><td><input type=text value=${detaildto.writer } readonly name="writer"></td></tr>
	<tr><td>조회수</td><td><input type=text value=${detaildto.viewcount } readonly name="viewcount"></td></tr>
	<tr><td>작성시간</td><td><input type=text value=${detaildto.writingtime } readonly name="writingtime"></td></tr>
	<tr><td colspan="2"><input type=button value="수정버튼" id="updatebtn"><input type=button value="삭제버튼" id="deletebtn"><td></tr>
	</table> 
	<input type=hidden name="pw" value=${detaildto.pw } >
</FORM>

<h1 id="1">상세게시물 보기 화면입니다.</h1>
<h1 id="2">상세게시물 보기 화면입니다.</h1>
<h1 id="3">상세게시물 보기 화면입니다.</h1>
<h1 id="4">상세게시물 보기 화면입니다.</h1>

</body>
</html>

<!-- TABLE + FORM양식 구성 <FORM><TABLE>   </TABLE> </FORM> -->


