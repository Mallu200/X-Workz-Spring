<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>EduPortal | Update Profile</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #f4f7f6; margin: 40px; }
        .update-container {
            background: white; padding: 30px; border-radius: 10px;
            max-width: 500px; margin: auto; box-shadow: 0px 4px 20px rgba(0,0,0,0.1);
            border-top: 5px solid #f0ad4e; /* Warning/Edit Orange */
        }
        h2 { color: #333; text-align: center; margin-bottom: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #555; }
        input, select { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; background-color: #fff; }
        .readonly-field { background-color: #e9ecef; cursor: not-allowed; color: #495057; }
        .error { color: #d9534f; font-size: 0.85em; font-weight: bold; }
        .btn-update {
            background-color: #f0ad4e; color: white; padding: 12px;
            border: none; border-radius: 5px; width: 100%; cursor: pointer; font-size: 16px; font-weight: bold;
        }
        .btn-update:hover { background-color: #ec971f; }
        .cancel-link { display: block; text-align: center; margin-top: 15px; text-decoration: none; color: #2d5a27; font-weight: bold; }
    </style>
</head>
<body>

<div class="update-container">
    <h2>Update Student Profile</h2>
    <p style="text-align:center; color: #777;">Modifying records for USN: <strong>${studentDto.usn}</strong></p>
    <hr>

    <form:form action="save" method="POST" modelAttribute="studentDto">

        <form:hidden path="id" />

        <div class="form-group">
            <label>Student Name:</label>
            <form:input path="name" />
            <form:errors path="name" cssClass="error" />
        </div>

        <div class="form-group">
            <label>USN (Read-Only):</label>
            <form:input path="usn" readonly="true" cssClass="readonly-field" />
        </div>

        <div class="form-group">
            <label>Institutional Email:</label>
            <form:input path="email" />
            <form:errors path="email" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Department:</label>
            <form:select path="department">
                <form:option value="Computer Science" label="Computer Science" />
                <form:option value="Electronics" label="Electronics" />
                <form:option value="Mechanical" label="Mechanical" />
                <form:option value="Civil" label="Civil" />
            </form:select>
        </div>

        <div class="form-group">
            <label>Current CGPA:</label>
            <form:input path="cgpa" type="number" step="0.01" />
            <form:errors path="cgpa" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Current Semester:</label>
            <form:select path="semester">
                <c:forEach var="i" begin="1" end="8">
                    <form:option value="${i} Semester" label="${i}th Semester" />
                </c:forEach>
            </form:select>
        </div>

        <button type="submit" class="btn-update">Update Record</button>
    </form:form>

    <a href="<c:url value='/student/viewAll'/>" class="cancel-link">Cancel and Return</a>
</div>

</body>
</html>