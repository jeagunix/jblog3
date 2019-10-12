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
		var input_description = $("#description").val();
		
		if(input_name == null || input_description ==null){
			return null;
		}
		var allData = { name: input_name, description: input_description };
		// ajax 통신
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/${authUser.id}/admin/categoryInsert",
			type: "post",
			data: allData,
			success: function(categoryInfoList){
				
				$("#name").val("");
				$("#description").val("");
				$("#name").focus();
				
				$("#original_table").remove();
				$("#ajax_able").remove();
				
				var countList = categoryInfoList.length;
				var ajax_table = $("<tbody id='ajax_table'></tbody>");
				$(".admin-cat").append(ajax_table);
				
				for(let categoryInfo in categoryInfoList){
					ajax_table.append(
							"<tr>" +
						        "<td>" + countList + "</td>" +
						        "<td>" + categoryInfoList[categoryInfo].name + "</td>" +
						        "<td>" + categoryInfoList[categoryInfo].postCount + "</td>"+
						        "<td>" + categoryInfoList[categoryInfo].description + "</td>" +
						        "<td>" +
						        "<img src='${pageContext.servletContext.contextPath}/assets/images/delete.jpg' class='delete' id=" +
						        categoryInfoList[categoryInfo].no +
						        " />" +
						        "</td>" +
					        "</tr>"		
					);
					countList--;
				}
				alert(input_name+"이 카테고리로 추가되었습니다.");

			},
			error: function(xhr, error){
				console.log(err);
				alert("오류입니다.");
			}
		});
	});
	
	$(document).on('click', '.delete', function(){
		var input_categoryNo = $(this).attr('id');
		
		// ajax 통신
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/${authUser.id}/admin/categoryDelete",
			type: "post",
			data: {no: input_categoryNo },
			success: function(categoryInfoList){
				$("#original_table").remove();
				$("#ajax_table").remove();
				
				var countList = categoryInfoList.length;
				var ajax_table = $("<tbody id='ajax_table'></tbody>");
				$(".admin-cat").append(ajax_table);
				
				for(let categoryInfo in categoryInfoList){
					ajax_table.append(
							"<tr>" +
						        "<td>" + countList + "</td>" +
						        "<td>" + categoryInfoList[categoryInfo].name + "</td>" +
						        "<td>" + categoryInfoList[categoryInfo].postCount + "</td>"+
						        "<td>" + categoryInfoList[categoryInfo].description + "</td>" +
						        "<td>" +
						        "<img src='${pageContext.servletContext.contextPath}/assets/images/delete.jpg' class='delete' id=" +
						        categoryInfoList[categoryInfo].no +
						        " />" +
						        "</td>" +
					        "</tr>"		
					);
					countList--;
				}
				alert("카테고리가 삭제되었습니다.");
			},
			error: function(xhr, error){
				console.log(err);
				alert("오류입니다.");
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
					<tbody id="original_table">
						<c:forEach var="categoryInfo" items="${categoryInfoList }" varStatus="status">
							<tr>
								<td>
									${(fn:length(categoryInfoList) - status.count)+1 }
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
									<img src="${pageContext.servletContext.contextPath}/assets/images/delete.jpg" id="${categoryInfo.no}" class="delete" />
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
						<td><input type="text" id="description" name="description"></td>
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