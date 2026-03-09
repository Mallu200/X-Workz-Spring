<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AutoCare | Service Logs</title>
    <style>
        body { font-family: 'Segoe UI', Arial, sans-serif; background-color: #f4f7f6; padding: 40px; margin: 0; }
        .container {
            background: white;
            max-width: 1200px;
            margin: auto;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 8px 25px rgba(0,0,0,0.1);
        }
        h2 { color: #1a237e; text-align: center; border-bottom: 3px solid #1a237e; padding-bottom: 10px; margin-bottom: 25px; }

        /* Status Alerts */
        .alert { padding: 15px; margin-bottom: 20px; border-radius: 6px; text-align: center; font-weight: 500; }
        .alert-success { background-color: #e8f5e9; color: #2e7d32; border: 1px solid #c8e6c9; }
        .alert-danger { background-color: #ffebee; color: #c62828; border: 1px solid #ffcdd2; }

        /* Table Styling */
        table { width: 100%; border-collapse: collapse; margin-top: 10px; background: #fff; }
        th { background-color: #1a237e; color: white; padding: 15px; text-align: left; font-size: 13px; text-transform: uppercase; }
        td { padding: 12px; border-bottom: 1px solid #eee; font-size: 14px; color: #37474f; }
        tr:hover { background-color: #f8f9fa; }

        /* Action Buttons */
        .btn-action { padding: 8px 15px; text-decoration: none; border-radius: 4px; font-size: 12px; font-weight: bold; transition: 0.3s; }
        .btn-edit { background-color: #455a64; color: white; margin-right: 5px; }
        .btn-edit:hover { background-color: #263238; }
        .btn-delete { background-color: #d32f2f; color: white; }
        .btn-delete:hover { background-color: #b71c1c; }

        .btn-add { background-color: #1a237e; color: white; padding: 12px 20px; text-decoration: none; border-radius: 6px; display: inline-block; font-weight: bold; transition: 0.3s; }
        .btn-add:hover { background-color: #0d47a1; }

        .no-data { text-align: center; padding: 60px; color: #90a4ae; font-style: italic; }
        .tag { background: #e3f2fd; color: #1565c0; padding: 3px 8px; border-radius: 4px; font-size: 11px; font-weight: bold; }
    </style>
</head>
<body>

<div class="container">
    <h2>Vehicle Service Registry</h2>

    <c:if test="${not empty msg}">
        <div class="alert alert-success">${msg}</div>
    </c:if>

    <c:if test="${not empty deleteMsg}">
        <div class="alert alert-success">${deleteMsg}</div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}/vehicle/register" class="btn-add">+ Add New Service</a>
        <a href="${pageContext.request.contextPath}/vehicle/" style="color: #607d8b; text-decoration: none; font-weight: 600;">🏠 Dashboard</a>
    </div>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Owner Name</th>
                <th>License Plate</th>
                <th>Vehicle Model</th>
                <th>Mileage (km)</th>
                <th>Service Type</th>
                <th style="text-align: center;">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty vehicleList}">
                    <c:forEach items="${vehicleList}" var="v">
                        <tr>
                            <td>${v.id}</td>
                            <td>${v.ownerName}</td>
                            <td><strong>${v.licensePlate}</strong></td>
                            <td>${v.modelName}</td>
                            <td>${v.currentMileage} km</td>
                            <td><span class="tag">${v.serviceType}</span></td>
                            <td style="text-align: center; white-space: nowrap;">
                                <a href="${pageContext.request.contextPath}/vehicle/edit?id=${v.id}" class="btn-action btn-edit">Edit</a>
                                <a href="${pageContext.request.contextPath}/vehicle/delete?id=${v.id}"
                                   class="btn-action btn-delete"
                                   onclick="return confirm('Delete record for ${v.licensePlate}?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="7" class="no-data">
                            No service records found. Click "Add New Service" to begin.
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>

</body>
</html>