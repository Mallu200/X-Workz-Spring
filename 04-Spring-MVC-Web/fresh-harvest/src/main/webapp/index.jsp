<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FreshHarvest | Organic Ledger</title>
<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #f1f8e9; /* Very light green */
        margin: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .warehouse-card {
        background: white;
        padding: 40px;
        border-radius: 15px;
        box-shadow: 0 8px 30px rgba(0,0,0,0.1);
        text-align: center;
        width: 460px;
        border-top: 10px solid #4caf50; /* Nature Green */
    }
    .icon-header {
        font-size: 50px;
        margin-bottom: 15px;
    }
    h1 {
        color: #2e7d32;
        margin-bottom: 10px;
        font-size: 30px;
    }
    .tagline {
        color: #558b2f;
        font-style: italic;
        margin-bottom: 30px;
        display: block;
    }
    .btn-container {
        display: flex;
        flex-direction: column;
        gap: 15px;
    }
    .btn {
        text-decoration: none;
        padding: 15px 20px;
        border-radius: 8px;
        font-weight: bold;
        font-size: 16px;
        transition: all 0.3s ease;
        display: block;
    }
    .btn-register {
        background-color: #4caf50;
        color: white;
        border: 2px solid #4caf50;
    }
    .btn-register:hover {
        background-color: #388e3c;
        box-shadow: 0 4px 12px rgba(76, 175, 80, 0.3);
    }
    .btn-inventory {
        background-color: transparent;
        color: #2e7d32;
        border: 2px solid #2e7d32;
    }
    .btn-inventory:hover {
        background-color: #2e7d32;
        color: white;
    }
    .footer-note {
        margin-top: 30px;
        font-size: 12px;
        color: #9e9e9e;
    }
</style>
</head>
<body>

    <div class="warehouse-card">
        <div class="icon-header">🚜</div>
        <h1>FreshHarvest</h1>
        <span class="tagline">Farm-to-Table Supply Chain Ledger</span>

        <div class="btn-container">
            <a href="<c:url value='/produce/register'/>" class="btn btn-register">New Harvest Entry</a>
            <a href="<c:url value='/produce/viewAll'/>" class="btn btn-inventory">View Organic Inventory</a>
        </div>

        <div class="footer-note">
            &copy; 2026 FreshHarvest Logistics | Raichur Regional Hub
        </div>
    </div>

</body>
</html>