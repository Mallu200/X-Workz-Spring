<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
    <title>Secure-Vault | Home</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body { background-color: #f8f9fa; }
        .hero-section {
            background: linear-gradient(135deg, #1e293b 0%, #334155 100%);
            color: white;
            padding: 80px 0;
            border-bottom: 5px solid #0f172a;
        }
        .card-feature {
            border: none;
            border-top: 4px solid #1e293b;
            transition: transform 0.3s;
        }
        .card-feature:hover { transform: translateY(-5px); }
    </style>
</head>
<body>

<div class="hero-section">
    <div class="container text-center">
        <h1 class="display-4 fw-bold">🛡️ Secure-Vault Guard</h1>
        <p class="lead">Advanced Forensic Evidence Tracking & Security System</p>

        <div class="mt-4">
            <sec:authorize access="!isAuthenticated()">
                <a href="evidence/viewAll" class="btn btn-primary btn-lg px-5">Access the Vault</a>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <div class="alert alert-light d-inline-block p-3">
                    Welcome back, <strong><sec:authentication property="principal.username"/></strong>!
                </div>
                <div class="mt-3">
                    <a href="evidence/viewAll" class="btn btn-light mx-2">View Records</a>

                    <sec:authorize access="hasAnyRole('MANAGER', 'EDITOR')">
                        <a href="evidence/register" class="btn btn-warning mx-2">Log New Evidence</a>
                    </sec:authorize>

                    <form action="<c:url value='/logout' />" method="post" style="display:inline;">
                        <button type="submit" class="btn btn-outline-danger mx-2">Logout</button>
                    </form>
                </div>
            </sec:authorize>
        </div>
    </div>
</div>

<div class="container mt-5">
    <div class="row g-4">
        <div class="col-md-4">
            <div class="card card-feature h-100 p-3 shadow-sm">
                <h3>Role-Based Security</h3>
                <p class="text-muted">Module 5 implementation ensuring that Auditors, Analysts, and Managers have strictly defined permissions.</p>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card card-feature h-100 p-3 shadow-sm">
                <h3>Automatic Auditing</h3>
                <p class="text-muted">Every record automatically captures the active session's username and timestamp for a complete chain of custody.</p>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card card-feature h-100 p-3 shadow-sm">
                <h3>Data Integrity</h3>
                <p class="text-muted">Powered by Spring ORM and Hibernate to ensure forensic data is persisted reliably in MySQL.</p>
            </div>
        </div>
    </div>
</div>

</body>
</html>