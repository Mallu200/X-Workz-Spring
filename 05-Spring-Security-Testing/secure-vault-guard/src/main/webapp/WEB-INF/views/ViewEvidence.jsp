<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Evidence Ledger | Secure-Vault</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .table-container { margin-top: 50px; background: white; padding: 20px; border-radius: 8px; }
        .navbar { background-color: #1e293b; }
        .status-badge { font-size: 0.8rem; padding: 5px 10px; }
    </style>
</head>
<body class="bg-light">

<nav class="navbar navbar-dark shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="<c:url value='/' />">🛡️ Secure-Vault Guard</a>
        <span class="navbar-text text-white">
            User: <strong><sec:authentication property="principal.username"/></strong>
            (<sec:authentication property="authorities"/>)
        </span>
    </div>
</nav>

<div class="container table-container shadow-sm">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Forensic Evidence Ledger</h2>
        <sec:authorize access="hasAnyRole('MANAGER', 'EDITOR')">
            <a href="register" class="btn btn-primary">+ Log New Item</a>
        </sec:authorize>
    </div>

    <table class="table table-hover border">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Case Number</th>
                <th>Item Reference</th>
                <th>Status</th>
                <th>Recorded By</th>
                <sec:authorize access="hasRole('MANAGER')">
                    <th>Actions</th>
                </sec:authorize>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="evidence">
                <tr>
                    <td>${evidence.id}</td>
                    <td><strong>${evidence.caseNumber}</strong></td>
                    <td>${evidence.itemRef}</td>
                    <td><span class="badge bg-secondary status-badge">${evidence.status}</span></td>
                    <td><code class="text-primary">${evidence.recordedBy}</code></td>

                    <sec:authorize access="hasRole('MANAGER')">
                        <td>
                            <form action="delete" method="post" onsubmit="return confirm('Confirm deletion of forensic evidence?')">
                                <input type="hidden" name="id" value="${evidence.id}">
                                <button type="submit" class="btn btn-outline-danger btn-sm">Purge</button>
                            </form>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="mt-3">
        <a href="<c:url value='/' />" class="btn btn-link text-decoration-none">← Back to Dashboard</a>
    </div>
</div>

</body>
</html>