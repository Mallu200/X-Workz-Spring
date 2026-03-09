<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FitTrack Pro | Member List</title>
    <style>
        body { font-family: 'Segoe UI', Arial, sans-serif; background-color: #f4f7f6; padding: 40px; margin: 0; }
        .container { background: white; max-width: 1100px; margin: auto; padding: 30px; border-radius: 12px; box-shadow: 0 8px 20px rgba(0,0,0,0.1); }
        h2 { color: #2c3e50; text-align: center; border-bottom: 3px solid #2ecc71; padding-bottom: 10px; margin-bottom: 25px; }

        /* Alert Styling */
        .alert { padding: 15px; margin-bottom: 20px; border-radius: 6px; text-align: center; font-weight: 500; }
        .alert-success { background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
        .alert-danger { background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }

        /* Table Styling */
        table { width: 100%; border-collapse: collapse; margin-top: 10px; background: #fff; }
        th { background-color: #2ecc71; color: white; padding: 15px 12px; text-align: left; text-transform: uppercase; font-size: 13px; letter-spacing: 0.5px; }
        td { padding: 12px; border-bottom: 1px solid #eee; font-size: 14px; color: #34495e; }
        tr:hover { background-color: #fcfcfc; }

        /* Action Buttons */
        .btn-action { padding: 8px 14px; text-decoration: none; border-radius: 4px; font-size: 12px; font-weight: bold; transition: 0.3s; display: inline-block; }
        .btn-edit { background-color: #3498db; color: white; margin-right: 5px; }
        .btn-edit:hover { background-color: #2980b9; }
        .btn-delete { background-color: #e74c3c; color: white; }
        .btn-delete:hover { background-color: #c0392b; }

        .btn-add { background-color: #2ecc71; color: white; padding: 12px 20px; text-decoration: none; border-radius: 6px; display: inline-block; font-weight: bold; transition: 0.3s; }
        .btn-add:hover { background-color: #27ae60; transform: translateY(-1px); }

        .no-data { text-align: center; padding: 50px; color: #95a5a6; font-style: italic; font-size: 1.1em; }
    </style>
</head>
<body>

<div class="container">
    <h2>Active Gym Members</h2>

    <%-- Success Message from Save/Update --%>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">${msg}</div>
    </c:if>

    <%-- Success Message from Delete --%>
    <c:if test="${not empty deleteMsg}">
        <div class="alert alert-success">${deleteMsg}</div>
    </c:if>

    <%-- Error Message from Controller Try-Catch --%>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}/member/register" class="btn-add">+ Register New Member</a>
        <a href="${pageContext.request.contextPath}/member/" style="color: #7f8c8d; text-decoration: none; font-weight: 500;">🏠 Home</a>
    </div>



    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Full Name</th>
                <th>Email Address</th>
                <th>Phone</th>
                <th>Plan</th>
                <th>Trainer</th>
                <th>Weight</th>
                <th style="text-align: center;">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty memberList}">
                    <c:forEach items="${memberList}" var="member">
                        <tr>
                            <td>${member.id}</td>
                            <td><strong>${member.name}</strong></td>
                            <td>${member.email}</td>
                            <td>${member.phoneNumber}</td>
                            <td><span style="background: #ebf5fb; padding: 4px 8px; border-radius: 4px; color: #2980b9; font-weight: 600;">${member.planType}</span></td>
                            <td>${member.trainerName}</td>
                            <td>${member.weight} kg</td>
                            <td style="text-align: center; white-space: nowrap;">
                                <a href="${pageContext.request.contextPath}/member/edit?id=${member.id}" class="btn-action btn-edit">Edit</a>
                                <a href="${pageContext.request.contextPath}/member/delete?id=${member.id}"
                                   class="btn-action btn-delete"
                                   onclick="return confirm('Confirm removal of ${member.name}?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="8" class="no-data">
                            No records found. Click "Register New Member" to get started.
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>

</body>
</html>