<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AutoCare | Vehicle Management</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
        .card {
            background: #ffffff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 15px 35px rgba(0,0,0,0.1);
            max-width: 450px;
            width: 90%;
            text-align: center;
            border-top: 5px solid #1a237e; /* Deep Navy accent */
        }
        h1 { color: #1a237e; margin-bottom: 5px; font-size: 2.2em; }
        .subtitle { color: #607d8b; margin-bottom: 30px; font-size: 1em; font-weight: 400; }

        .menu-options { display: flex; flex-direction: column; gap: 15px; }

        .btn {
            display: block;
            padding: 14px;
            font-size: 16px;
            color: white;
            background-color: #1a237e; /* Deep Navy */
            border-radius: 6px;
            text-decoration: none;
            font-weight: 600;
            transition: all 0.3s ease;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }
        .btn-secondary {
            background-color: #455a64; /* Slate Gray */
        }
        .btn:hover {
            filter: brightness(120%);
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.2);
        }
        hr { border: 0; border-top: 1px solid #e0e0e0; margin: 25px 0; }
    </style>
</head>
<body>

    <div class="card">
        <h1>AutoCare</h1>
        <div class="subtitle">Vehicle Record & Service Management</div>
        <hr>

        <div class="menu-options">
            <%-- Maps to VehicleController @GetMapping("/register") --%>
            <a href="${pageContext.request.contextPath}/vehicle/register" class="btn">
                Add New Vehicle
            </a>

            <%-- Maps to VehicleController @GetMapping("/viewAll") --%>
            <a href="${pageContext.request.contextPath}/vehicle/viewAll" class="btn btn-secondary">
                View Service Logs
            </a>
        </div>

        <p style="margin-top: 25px; font-size: 12px; color: #90a4ae;">
            Logged in as Administrator
        </p>
    </div>

</body>
</html>