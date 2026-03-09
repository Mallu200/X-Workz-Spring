<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EduPortal | University Management</title>
<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #f4f7f6;
        margin: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .dashboard-card {
        background: white;
        padding: 40px;
        border-radius: 12px;
        box-shadow: 0 10px 25px rgba(0,0,0,0.1);
        text-align: center;
        width: 450px;
        border-top: 8px solid #2d5a27; /* Deep University Green */
    }
    h1 {
        color: #2d5a27;
        margin-bottom: 10px;
        font-size: 28px;
    }
    p {
        color: #666;
        margin-bottom: 30px;
    }
    .btn-container {
        display: flex;
        flex-direction: column;
        gap: 15px;
    }
    .btn {
        text-decoration: none;
        padding: 12px 20px;
        border-radius: 6px;
        font-weight: bold;
        transition: all 0.3s ease;
        display: block;
    }
    .btn-register {
        background-color: #2d5a27;
        color: white;
        border: 2px solid #2d5a27;
    }
    .btn-register:hover {
        background-color: #1e3d1a;
    }
    .btn-view {
        background-color: transparent;
        color: #2d5a27;
        border: 2px solid #2d5a27;
    }
    .btn-view:hover {
        background-color: #2d5a27;
        color: white;
    }
    .footer-text {
        margin-top: 25px;
        font-size: 12px;
        color: #999;
    }
</style>
</head>
<body>

    <div class="dashboard-card">
        <img src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png" alt="Edu Logo" width="80" style="margin-bottom: 15px;">
        <h1>EduPortal</h1>
        <p>University Student Information System</p>

        <div class="btn-container">
            <a href="<c:url value='/student/register'/>" class="btn btn-register">New Student Admission</a>
            <a href="<c:url value='/student/viewAll'/>" class="btn btn-view">Access Student Directory</a>
        </div>

        <div class="footer-text">
            &copy; 2026 EduPortal Management System | Powered by Spring MVC
        </div>
    </div>

</body>
</html>