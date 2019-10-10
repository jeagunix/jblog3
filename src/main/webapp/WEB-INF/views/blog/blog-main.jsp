<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("newline", "\n");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogInfo.title }</h1>
			<c:import url="/WEB-INF/views/includes/blog-menu.jsp" />
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postInfo.title }</h4>
					<p>
						${postInfo.content }
					<p>
				</div>
				<ul class="blog-list">
					<c:choose>
						<c:when test="${empty postList }">
							<li>목록이 없습니다.</li>
						</c:when>
						<c:otherwise>
							<c:forEach var="post" items="${postList }">
								<li><a
									href="${pageContext.servletContext.contextPath }/${blogInfo.blogId }/${post.categoryNo }/${post.no }">${post.title }</a>
									<span>${post.writeDate }</span></li>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img
					src="${pageContext.request.contextPath }/assets/images/${blogInfo.logo }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach var="category" items="${categoryList }">
					<li><a
						href="${pageContext.servletContext.contextPath }/${blogInfo.blogId }/${category.no }">${category.name }</a></li>
				</c:forEach>
			</ul>
		</div>

		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>