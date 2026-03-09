<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>EduPortal | Student Directory</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #f4f7f6; margin: 30px; }
        .container { background: white; padding: 25px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); }
        h2 { color: #2d5a27; border-bottom: 2px solid #2d5a27; padding-bottom: 10px; }

        /* Table Styling */
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th { background-color: #2d5a27; color: white; padding: 12px; text-align: left; }
        td { padding: 12px; border-bottom: 1px solid #ddd; color: #333; }
        tr:hover { background-color: #f1f8f1; }

        /* Action Buttons */
        .btn { text-decoration: none; padding: 6px 12px; border-radius: 4px; font-size: 14px; font-weight: bold; }
        .btn-edit { background-color: #f0ad4e; color: white; margin-right: 5px; }
        .btn-delete { background-color: #d9534f; color: white; }
        .btn-add { background-color: #2d5a27; color: white; float: right; margin-bottom: 15px; }

        /* Alert Messages */
        .alert { padding: 10px; margin-bottom: 20px; border-radius: 4px; font-weight: bold; }
        .alert-success { background-color: #dff0d8; color: #3c763d; border: 1px solid #d6e9c6; }
        .alert-danger { background-color: #f2dede; color: #a94442; border: 1px solid #ebccd1; }
    </style>
</head>
<body>

<div class="container">
    <a href="<c:url value='/student/register'/>" class="btn btn-add">+ New Admission</a>
    <h2>Student Directory</h2>

    <c:if test="${not empty msg}">
        <div class="alert alert-success">${msg}</div>
    </c:if>

    <c:if test="${not empty deleteMsg}">
        <div class="alert alert-danger">${deleteMsg}</div>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>USN</th>
                <th>Student Name</th>
                <th>Department</th>
                <th>Semester</th>
                <th>CGPA</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty studentList}">
                    <c:forEach var="student" items="${studentList}">
                        <tr>
                            <td><strong>${student.usn}</strong></td>
                            <td>${student.name}</td>
                            <td>${student.department}</td>
                            <td>${student.semester}</td>
                            <td>${student.cgpa}</td>
                            <td>
                                <a href="<c:url value='/student/edit?id=${student.id}'/>" class="btn btn-edit">Edit</a>

                                <a href="<c:url value='/student/delete?id=${student.id}'/>"
                                   class="btn btn-delete"
                                   onclick="return confirm('Are you sure you want to remove this student record?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="6" style="text-align:center;">No student records found in the portal.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>

    <div style="margin-top: 20px;">
        <a href="<c:url value='/'/>" style="color: #2d5a27; text-decoration: none;">&larr; Back to Dashboard</a>
    </div>
</div>

</body>
</html>