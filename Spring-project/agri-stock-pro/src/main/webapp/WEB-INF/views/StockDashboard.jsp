<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // 119-UI: Inventory Dashboard requested
    System.out.println("119-UI: Rendering StockDashboard.jsp with Forest Green theme.");
%>
<html>
<head>
    <title>Agri-Stock | Inventory Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Professional Agriculture Palette */
        :root {
            --agri-green: #2D5A27; /* Forest Green */
            --agri-sage: #F1F8E9;  /* Light Sage Background */
            --agri-earth: #8D6E63; /* Soil Brown */
            --agri-dark: #1B3F17;  /* Deep Forest */
        }

        body { background-color: var(--agri-sage); font-family: 'Segoe UI', Tahoma, sans-serif; }

        /* Navbar using the brand's primary Green */
        .navbar { background-color: var(--agri-green); border-bottom: 3px solid var(--agri-dark); }

        /* Table Header using Deep Forest Green */
        .table-thead { background-color: var(--agri-dark); color: white; }

        .btn-agri { background-color: var(--agri-green); color: white; border-radius: 4px; border: none; }
        .btn-agri:hover { background-color: var(--agri-dark); color: white; }

        .card { border: none; border-radius: 12px; box-shadow: 0 4px 15px rgba(0,0,0,0.05); overflow: hidden; }

        .badge-category { background-color: var(--agri-earth); color: white; font-weight: 500; }

        .table-hover tbody tr:hover { background-color: #E8F5E9; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark mb-4 shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/stock/view">
            AGRI-STOCK <span class="text-warning">PRO</span>
        </a>
        <a href="add" class="btn btn-light btn-sm fw-bold" style="color: var(--agri-green);">+ Add New Stock</a>
    </div>
</nav>

<div class="container">
    <div class="card mb-4 p-3">
        <form action="search" method="get" class="row g-2">
            <div class="col-md-10">
                <input type="text" name="itemName" class="form-control" placeholder="Search product name (e.g. Fertilizer)..." value="${searchQuery}">
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-agri w-100">Search</button>
            </div>
        </form>
    </div>

    <c:if test="${not empty message}">
        <div class="alert alert-success border-0 shadow-sm mb-4" style="border-left: 5px solid var(--agri-green) !important;">
            <strong>Success!</strong> ${message}
        </div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="alert alert-danger border-0 shadow-sm mb-4">
            <strong>Error:</strong> ${error}
        </div>
    </c:if>

    <div class="card">
        <div class="table-responsive">
            <% try { %>
            <table class="table table-hover mb-0">
                <thead class="table-thead">
                    <tr>
                        <th class="ps-4">ID</th>
                        <th>Item Name</th>
                        <th>Category</th>
                        <th>Quantity</th>
                        <th>Price (₹)</th>
                        <th>Supplier</th>
                        <th class="text-center">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="stock" items="${stocks}">
                        <tr>
                            <td class="ps-4 text-muted">#${stock.id}</td>
                            <td class="fw-bold" style="color: var(--agri-dark);">${stock.itemName}</td>
                            <td><span class="badge badge-category">${stock.category}</span></td>
                            <td>${stock.quantity}</td>
                            <td>${stock.price}</td>
                            <td>${stock.supplier}</td>
                            <td class="text-center">
                                <a href="edit/${stock.id}" class="btn btn-sm btn-outline-success me-1">Edit</a>
                                <a href="delete/${stock.id}" class="btn btn-sm btn-outline-danger"
                                   onclick="return confirm('Confirm deletion of ${stock.itemName}?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty stocks}">
                        <tr>
                            <td colspan="7" class="text-center p-5 text-muted">
                                <p class="mb-0">No stock items found in the inventory.</p>
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
            <% } catch (Exception e) {
                System.err.println("120-UI: Dashboard Table processing error: " + e.getMessage());
            } %>
        </div>
    </div>
</div>

<%
    // 121-UI: Dashboard rendering complete
    if(request.getAttribute("stocks") != null) {
        System.out.println("121-UI: Dashboard loaded with " + ((java.util.List)request.getAttribute("stocks")).size() + " records.");
    }
%>
</body>
</html>