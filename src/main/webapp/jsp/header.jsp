<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="A simple ecommerce website.">
<link rel="icon"
	href="<c:url value="/assets/media/logo.png"></c:url>">
<title>Ecommerce Website</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,300;0,400;0,500;0,900;1,300;1,400;1,500;1,900&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value="/assets/css/bootstrap.min.css"></c:url>">
<link rel="stylesheet"
	href="<c:url value="/assets/css/styles.css"></c:url>">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
<script src="<c:url value="/assets/js/bootstrap.min.js"></c:url>"></script>
</head>
<body>
	<header>
		<c:import url="/jsp/topnav.jsp"></c:import>
		<c:import url="/jsp/navbar.jsp"></c:import>
	</header>