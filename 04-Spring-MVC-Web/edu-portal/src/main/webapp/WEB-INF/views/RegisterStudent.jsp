<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>EduPortal | Student Admission</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f7f6; margin: 40px; }
        .form-container {
            background: white; padding: 30px; border-radius: 10px;
            max-width: 500px; margin: auto; box-shadow: 0px 0px 15px rgba(0,0,0,0.1);
            border-left: 5px solid #2d5a27;
        }
        h2 { color: #2d5a27; text-align: center; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #333; }
        input, select { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        .error { color: #d9534f; font-size: 0.85em; margin-top: 5px; font-weight: bold; }
        .btn-submit {
            background-color: #2d5a27; color: white; padding: 12px;
            border: none; border-radius: 5px; width: 100%; cursor: pointer; font-size: 16px;
        }
        .btn-submit:hover { background-color: #1e3d1a; }
        .back-link { display: block; text-align: center; margin-top: 15px; text-decoration: none; color: #666; }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Student Admission Form</h2>
    <hr>

    <form:form action="save" method="POST" modelAttribute="studentDto">

        <form:hidden path="id" />

        <div class="form-group">
            <label>Full Name:</label>
            <form:input path="name" placeholder="Enter student name" />
            <form:errors path="name" cssClass="error" />
        </div>

        <div class="form-group">
            <label>USN (University Seat Number):</label>
            <form:input path="usn" placeholder="e.g. 1PU21EC001" />
            <form:errors path="usn" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Email Address:</label>
            <form:input path="email" placeholder="example@university.edu" />
            <form:errors path="email" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Department:</label>
            <form:select path="department">
                <form:option value="" label="-- Select Department --" />
                <form:option value="Computer Science" label="Computer Science" />
                <form:option value="Electronics" label="Electronics" />
                <form:option value="Mechanical" label="Mechanical" />
                <form:option value="Civil" label="Civil" />
            </form:select>
            <form:errors path="department" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Current CGPA:</label>
            <form:input path="cgpa" type="number" step="0.01" placeholder="0.00 to 10.00" />
            <form:errors path="cgpa" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Current Semester:</label>
            <form:select path="semester">
                <form:option value="" label="-- Select Semester --" />
                <c:forEach var="i" begin="1" end="8">
                    <form:option value="${i} Semester" label="${i}th Semester" />
                </c:forEach>
            </form:select>
            <form:errors path="semester" cssClass="error" />
        </div>

        <button type="submit" class="btn-submit">Confirm Admission</button>
    </form:form>

    <a href="<c:url value='/student/viewAll'/>" class="back-link">View All Registered Students</a>
</div>

</body>
</html>