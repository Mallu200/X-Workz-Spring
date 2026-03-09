<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Log Evidence | Secure-Vault</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .form-container { max-width: 600px; margin: 50px auto; background: white; padding: 30px; border-radius: 12px; }
        .error { color: #dc3545; font-size: 0.85rem; font-weight: bold; }
        .navbar { background-color: #1e293b; }
    </style>
</head>
<body class="bg-light">

<nav class="navbar navbar-dark mb-4">
    <div class="container">
        <a class="navbar-brand" href="<c:url value='/' />">🛡️ Secure-Vault Guard</a>
    </div>
</nav>

<div class="container">
    <div class="form-container shadow">
        <h2 class="mb-4 text-center">Register New Evidence</h2>
        <hr>

        <form:form action="save" method="post" modelAttribute="dto">
            <div class="mb-3">
                <label class="form-label">Case Number</label>
                <form:input path="caseNumber" class="form-control" placeholder="e.g., CASE-2026-X01"/>
                <form:errors path="caseNumber" cssClass="error" />
            </div>

            <div class="mb-3">
                <label class="form-label">Item Reference</label>
                <form:input path="itemRef" class="form-control" placeholder="e.g., Digital Drive / DNA Sample"/>
                <form:errors path="itemRef" cssClass="error" />
            </div>

            <div class="mb-3">
                <label class="form-label">Status</label>
                <form:select path="status" class="form-select">
                    <form:option value="SEIZED">SEIZED</form:option>
                    <form:option value="UNDER_ANALYSIS">UNDER ANALYSIS</form:option>
                    <form:option value="DISPOSED">DISPOSED</form:option>
                </form:select>
            </div>

            <div class="d-grid gap-2 mt-4">
                <button type="submit" class="btn btn-primary">Secure & Save Record</button>
                <a href="viewAll" class="btn btn-outline-secondary">Cancel</a>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>