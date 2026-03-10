<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // 117-UI: EditStock.jsp loaded to modify existing record
    System.out.println("117-UI: Rendering EditStock.jsp for item ID: " +
        (request.getAttribute("dto") != null ? ((com.xworkz.stock.dto.StockDTO)request.getAttribute("dto")).getId() : "NULL"));
%>
<html>
<head>
    <title>Agri-Stock | Edit Item</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Professional Agriculture Palette */
        :root {
            --agri-green: #2D5A27; /* Forest Green */
            --agri-sage: #F1F8E9;  /* Light Sage Background */
            --agri-earth: #8D6E63; /* Soil Brown for accents */
        }

        body { background-color: var(--agri-sage); font-family: 'Segoe UI', Tahoma, sans-serif; }

        .card { border-radius: 12px; border: none; overflow: hidden; }

        /* Deep nature green header matching the brand identity */
        .card-header {
            background-color: var(--agri-green);
            color: white;
            padding: 1.5rem;
            border-bottom: 4px solid #1B3F17;
        }

        .form-label { font-weight: 600; color: #34495E; }

        /* Focus state in green to maintain brand consistency */
        .form-control:focus, .form-select:focus {
            border-color: var(--agri-green);
            box-shadow: 0 0 0 0.25rem rgba(45, 90, 39, 0.25);
        }

        /* Update button with flat, professional styling */
        .btn-update {
            background-color: var(--agri-green);
            color: white;
            border: none;
            padding: 12px;
            font-weight: bold;
            transition: background 0.3s;
        }

        .btn-update:hover { background-color: #1B3F17; color: #fff; }

        .btn-cancel { color: var(--agri-earth); text-decoration: none; font-weight: 500; }
        .btn-cancel:hover { color: #5D4037; }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6 shadow-lg p-0">
                <div class="card">
                    <div class="card-header text-center">
                        <h4 class="mb-0">Update Inventory Details</h4>
                        <small class="opacity-75">Modify current stock information for ID: ${dto.id}</small>
                    </div>
                    <div class="card-body p-4">
                        <% try { %>
                        <form action="${pageContext.request.contextPath}/stock/update" method="post">

                            <input type="hidden" name="id" value="${dto.id}">

                            <div class="mb-3">
                                <label class="form-label">Item Name</label>
                                <input type="text" name="itemName" class="form-control" value="${dto.itemName}" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Category</label>
                                <select name="category" class="form-select">
                                    <c:forEach var="cat" items="${categories}">
                                        <option value="${cat}" ${cat == dto.category ? 'selected' : ''}>
                                            ${cat.displayValue}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label class="form-label">Quantity</label>
                                    <input type="number" name="quantity" class="form-control" value="${dto.quantity}" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label class="form-label">Price (₹)</label>
                                    <input type="number" step="0.01" name="price" class="form-control" value="${dto.price}" required>
                                </div>
                            </div>

                            <div class="mb-4">
                                <label class="form-label">Supplier</label>
                                <input type="text" name="supplier" class="form-control" value="${dto.supplier}" required>
                            </div>

                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-update">Save Changes</button>
                                <div class="text-center mt-2">
                                    <a href="${pageContext.request.contextPath}/stock/view" class="btn-cancel">Discard Changes</a>
                                </div>
                            </div>
                        </form>
                        <% } catch (Exception e) {
                            System.err.println("118-UI: Error binding data to EditStock form: " + e.getMessage());
                        } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>