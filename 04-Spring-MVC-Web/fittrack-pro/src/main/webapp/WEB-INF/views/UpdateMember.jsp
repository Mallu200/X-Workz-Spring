<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FitTrack Pro | Update Member</title>
    <style>
        body { font-family: 'Segoe UI', Arial, sans-serif; background-color: #f4f7f6; padding: 40px; }
        .form-container { background: white; max-width: 500px; margin: auto; padding: 30px; border-radius: 12px; box-shadow: 0 8px 20px rgba(0,0,0,0.1); }
        h2 { color: #2c3e50; text-align: center; border-bottom: 3px solid #3498db; padding-bottom: 10px; margin-bottom: 20px;}

        .form-group { margin-bottom: 15px; }
        label { display: block; font-weight: bold; margin-bottom: 5px; color: #444; }
        input, select { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 6px; box-sizing: border-box; }
        .error { color: #e74c3c; font-size: 12px; font-weight: bold; margin-top: 5px; display: block; }

        .btn-update {
            background-color: #3498db; color: white; border: none; padding: 15px;
            width: 100%; font-size: 16px; font-weight: bold; border-radius: 6px;
            cursor: pointer; margin-top: 20px; text-transform: uppercase; transition: 0.3s;
        }
        .btn-update:hover { background-color: #2980b9; }
        .nav-link { display: block; text-align: center; margin-top: 15px; color: #3498db; text-decoration: none; }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Update Member Profile</h2>

    <%-- Action points back to /member/save which handles both Update and Insert in your Controller --%>
    <form:form action="${pageContext.request.contextPath}/member/save" method="POST" modelAttribute="dto">

        <%-- The ID is hidden but essential so the Service knows WHICH record to update --%>
        <form:hidden path="id" />

        <div class="form-group">
            <label>Full Name</label>
            <form:input path="name" placeholder="Enter name" />
            <form:errors path="name" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Email Address</label>
            <form:input path="email" type="email" />
            <form:errors path="email" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Phone Number</label>
            <form:input path="phoneNumber" />
            <form:errors path="phoneNumber" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Plan Type</label>
            <form:select path="planType">
                <form:option value="Monthly" label="Monthly" />
                <form:option value="Quarterly" label="Quarterly" />
                <form:option value="Yearly" label="Yearly" />
            </form:select>
            <form:errors path="planType" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Trainer Name</label>
            <form:input path="trainerName" />
            <form:errors path="trainerName" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Weight (kg)</label>
            <form:input path="weight" type="number" step="0.1" />
            <form:errors path="weight" cssClass="error" />
        </div>

        <button type="submit" class="btn-update">Save Changes</button>
    </form:form>

    <a href="${pageContext.request.contextPath}/member/viewAll" class="nav-link">← Cancel and Go Back</a>
</div>

</body>
</html>