<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	<div id="admin" class="row g-0">
		<aside class="col-1 col-lg-3 bg-primary p-5 z-2 d-flex flex-column">
			<c:import url="./adminMenu.jsp"></c:import>
		</aside>
		<main class="col-11 offset-1 col-lg-9 offset-lg-3">