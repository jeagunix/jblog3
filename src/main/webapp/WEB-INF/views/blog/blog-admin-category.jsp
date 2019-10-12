<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript"></script>

<script>
$(function(){
	$("#addCategory").click(function(){
		var input_name = $("#name").val();
		var input_text = $("#text").val();
		alert(input_name);
		
		if(input_name == null || input_text ==null){
			return null;
		}
		var allData = { name: input_name, text: input_text };
		// ajax 통신
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/${authUser.id}/admin/category",
			type: "post",
			data: allData,
			success: function(result){
				$("#name").val("");
				$("#text").val("");
				$("#name").focus();
			
			},
			error: function(xhr, error){
				
			}
		});
	});
});
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/includes/blog-title.jsp" />
			<c:import url="/WEB-INF/views/includes/blog-menu.jsp" />
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a
						href="${pageContext.servletContext.contextPath }/${authUser.id }/admin/basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a
						href="${pageContext.servletContext.contextPath }/${authUser.id }/admin/write">글작성</a></li>
				</ul>
				<table class="admin-cat">
				  	<thead>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="categoryInfo" items="${categoryInfoList }" varStatus="status">
							<tr>
								<td>
									${fn:length(categoryList) - status.count }
								</td>
								<td>
									${categoryInfo.name }
								</td>
								<td>
									${categoryInfo.postCount }
								</td>
								<td>
									 ${categoryInfo.description }
								</td>
								<td>
									<img src="${pageContext.servletContext.contextPath}/assets/images/delete.jpg" id="${categoryInfo.no}">
								</td>
							</tr>
						</c:forEach>
					</tbody>	
				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<table id="admin-cat-add">
					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" id="name" name="name"></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" id="text" name="text"></td>
					</tr>
					<tr>
						<td class="s">&nbsp;</td>
						<td><input id="addCategory" type="submit" value="카테고리 추가"></td>
					</tr>
				</table>
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
	</div>
</body>
</html>