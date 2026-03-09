<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AutoCare | Update Service Record</title>
    <style>
        body { font-family: 'Segoe UI', Arial, sans-serif; background-color: #eceff1; padding: 40px; margin: 0; }
        .form-container {
            background: white;
            max-width: 550px;
            margin: auto;
            padding: 35px;
            border-radius: 12px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            border-top: 6px solid #455a64;
        }
        h2 { color: #455a64; text-align: center; margin-bottom: 25px; text-transform: uppercase; letter-spacing: 1px; }

        .alert-error {
            background-color: #ffebee; color: #c62828; padding: 15px;
            border-radius: 6px; border: 1px solid #ffcdd2; margin-bottom: 25px;
            font-size: 14px; text-align: center;
        }

        .form-group { margin-bottom: 20px; }
        label { display: block; font-weight: 600; margin-bottom: 8px; color: #37474f; font-size: 14px; }
        input, select {
            width: 100%; padding: 12px; border: 1px solid #cfd8dc;
            border-radius: 6px; box-sizing: border-box; font-size: 15px;
        }
        input:focus { border-color: #455a64; outline: none; }
        .error { color: #d32f2f; font-size: 12px; margin-top: 5px; font-weight: 500; }

        .btn-update {
            background-color: #455a64; color: white; border: none; padding: 16px;
            width: 100%; font-size: 16px; font-weight: bold; border-radius: 6px;
            cursor: pointer; margin-top: 10px; text-transform: uppercase;
        }
        .btn-update:hover { background-color: #263238; }
        .nav-link { display: block; text-align: center; margin-top: 20px; color: #1a237e; text-decoration: none; font-size: 14px; }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Update Service Record</h2>

    <c:if test="${not empty dbError}">
        <div class="alert-error">${dbError}</div>
    </c:if>

    <form:form action="${pageContext.request.contextPath}/vehicle/save" method="POST" modelAttribute="vehicleDto">

        <%-- Ensures the ID is passed back so the Service knows to Update --%>
        <form:hidden path="id" />

        <div class="form-group">
            <label>Owner Name</label>
            <form:input path="ownerName" />
            <form:errors path="ownerName" cssClass="error" />
        </div>

        <div class="form-group">
            <label>License Plate</label>
            <form:input path="licensePlate" />
            <form:errors path="licensePlate" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Vehicle Model</label>
            <form:input path="modelName" />
            <form:errors path="modelName" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Current Mileage (km)</label>
            <form:input path="currentMileage" type="number" />
            <form:errors path="currentMileage" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Service Type</label>
            <form:select path="serviceType">
                <form:option value="General Service" label="General Service" />
                <form:option value="Oil Change" label="Oil Change" />
                <form:option value="Brake Repair" label="Brake Repair" />
                <form:option value="Wheel Alignment" label="Wheel Alignment" />
                <form:option value="Body Wash & Polish" label="Body Wash & Polish" />
            </form:select>
            <form:errors path="serviceType" cssClass="error" />
        </div>

        <button type="submit" class="btn-update">Save Changes</button>
    </form:form>

    <a href="${pageContext.request.contextPath}/vehicle/viewAll" class="nav-link">← Cancel and Go Back</a>
</div>

</body>
</html>