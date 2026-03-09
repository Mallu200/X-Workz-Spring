<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- CRITICAL: Ensure EL is not ignored so tags and variables work --%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FitTrack Pro | Welcome</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f7f6;
            margin: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
        .container {
            background: white;
            padding: 50px;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.1);
            max-width: 500px;
            width: 90%;
            text-align: center;
        }
        h1 { color: #2c3e50; margin-bottom: 10px; font-size: 2.5em; }
        p { color: #7f8c8d; margin-bottom: 30px; font-size: 1.1em; }
        .btn-group { display: flex; flex-direction: column; gap: 15px; }
        .btn {
            display: block;
            padding: 15px 25px;
            font-size: 18px;
            color: white;
            background-color: #3498db;
            border-radius: 8px;
            text-decoration: none;
            font-weight: bold;
            transition: all 0.3s ease;
            text-transform: uppercase;
            letter-spacing: 1px;
        }
        .btn-view { background-color: #2ecc71; }
        .btn:hover {
            filter: brightness(90%);
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        hr { border: 0; border-top: 2px solid #3498db; width: 50px; margin: 20px auto; }
    </style>
</head>
<body>

    <div class="container">
        <h1>FitTrack Pro</h1>
        <hr>
        <p>Your complete gym membership and fitness management solution.</p>

        <div class="btn-group">
            <%-- These URLs now match your @RequestMapping("/member") in the Controller --%>
            <a href="${pageContext.request.contextPath}/member/register" class="btn">
                Add New Member
            </a>

            <a href="${pageContext.request.contextPath}/member/viewAll" class="btn btn-view">
                View All Members
            </a>
        </div>
    </div>

</body>
</html>