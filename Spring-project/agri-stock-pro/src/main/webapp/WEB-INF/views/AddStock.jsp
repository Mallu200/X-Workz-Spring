<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // 115-UI: AddStock.jsp loaded for user input
    System.out.println("115-UI: Rendering AddStock.jsp with agricultural theme.");
%>
<html>
<head>
    <title>Agri-Stock | Register Item</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Professional Agriculture Palette */
        :root {
            --agri-green: #2D5A27; /* Forest Green */
            --agri-sage: #F1F8E9;  /* Light Sage Background */
            --agri-earth: #8D6E63; /* Soil Brown for accents */
        }

        body { background-color: var(--agri-sage); font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }

        .card { border-radius: 12px; border: none; overflow: hidden; }

        /* Header uses a deep nature green */
        .card-header {
            background-color: var(--agri-green);
            color: white;
            padding: 1.5rem;
            border-bottom: 4px solid #1B3F17;
        }

        .form-label { font-weight: 600; color: #34495E; }

        /* Form inputs focus effect in green */
        .form-control:focus, .form-select:focus {
            border-color: var(--agri-green);
            box-shadow: 0 0 0 0.25rem rgba(45, 90, 39, 0.25);
        }

        /* Professional button without the 'pop' or checkmarks */
        .btn-save {
            background-color: var(--agri-green);
            color: white;
            border: none;
            padding: 12px;
            font-weight: bold;
            transition: background 0.3s;
        }

        .btn-save:hover { background-color: #1B3F17; color: #fff; }

        .btn-back { color: var(--agri-earth); text-decoration: none; font-weight: 500; }
        .btn-back:hover { color: #5D4037; }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6 shadow-lg p-0">
                <div class="card">
                    <div class="card-header text-center">
                        <h4 class="mb-0">Agri-Stock Inventory Registration</h4>
                        <small class="opacity-75">Fill in the details to add new farm supplies</small>
                    </div>
                    <div class="card-body p-4">
                        <% try { %>
                        <form action="add" method="post">
                            <div class="mb-3">
                                <label class="form-label">Item Name</label>
                                <input type="text" name="itemName" class="form-control" placeholder="e.g. Organic Urea" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Category</label>
                                <select name="category" class="form-select">
                                    <c:forEach var="cat" items="${categories}">
                                        <option value="${cat}">${cat.displayValue}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label class="form-label">Quantity</label>
                                    <input type="number" name="quantity" class="form-control" placeholder="0" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label class="form-label">Price (per unit)</label>
                                    <div class="input-group">
                                        <span class="input-group-text">₹</span>
                                        <input type="number" step="0.01" name="price" class="form-control" placeholder="0.00" required>
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Supplier Information</label>
                                <input type="text" name="supplier" class="form-control" placeholder="e.g. Karnataka Agro Traders" required>
                            </div>

                            <div class="mt-4">
                                <button type="submit" class="btn btn-save w-100">Register Stock</button>
                                <div class="text-center mt-3">
                                    <a href="view" class="btn-back">Cancel and Return to Dashboard</a>
                                </div>
                            </div>
                        </form>
                        <% } catch (Exception e) {
                            System.err.println("116-UI: Critical failure in JSP form rendering: " + e.getMessage());
                        } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>