<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>FreshHarvest | Inventory Dashboard</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f1f8e9; margin: 30px; }
        .container { background: white; padding: 30px; border-radius: 12px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); }
        h2 { color: #2e7d32; border-bottom: 2px solid #a5d6a7; padding-bottom: 10px; margin-top: 0; }

        /* Table Styling */
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th { background-color: #388e3c; color: white; padding: 15px; text-align: left; }
        td { padding: 15px; border-bottom: 1px solid #e8f5e9; color: #333; }
        tr:hover { background-color: #f9fbe7; }

        /* Status Badges */
        .badge { padding: 6px 12px; border-radius: 4px; font-size: 12px; font-weight: bold; }
        .status-fresh { background-color: #c8e6c9; color: #2e7d32; }
        .status-ripening { background-color: #fff9c4; color: #fbc02d; }
        .status-expiry { background-color: #ffccbc; color: #d84315; }

        /* Actions */
        .btn { text-decoration: none; padding: 8px 16px; border-radius: 4px; font-size: 13px; font-weight: bold; transition: 0.3s; }
        .btn-edit { background-color: #81c784; color: white; margin-right: 5px; }
        .btn-delete { background-color: #ef9a9a; color: white; }
        .btn-add { background-color: #4caf50; color: white; float: right; margin-bottom: 15px; }
        .btn:hover { opacity: 0.85; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }

        /* Notifications */
        .alert { padding: 15px; margin-bottom: 20px; border-radius: 6px; text-align: center; font-weight: bold; }
        .alert-success { background-color: #dcedc8; color: #33691e; border: 1px solid #c5e1a5; }
        .alert-danger { background-color: #ffcdd2; color: #b71c1c; border: 1px solid #ef9a9a; }
    </style>
</head>
<body>

<div class="container">
    <a href="<c:url value='/produce/register'/>" class="btn btn-add">+ Register New Harvest</a>
    <h2>📦 Organic Inventory Ledger</h2>

    <c:if test="${not empty msg}">
        <div class="alert alert-success">${msg}</div>
    </c:if>

    <c:if test="${not empty deleteMsg}">
        <div class="alert alert-danger">${deleteMsg}</div>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>Produce Name</th>
                <th>Category</th>
                <th>Stock</th>
                <th>Harvested On</th>
                <th>Shelf Life</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty produceList}">
                    <c:forEach var="item" items="${produceList}">
                        <tr>
                            <td><strong>${item.name}</strong></td>
                            <td>${item.category}</td>
                            <td>${item.quantity} ${item.unit}</td>
                            <td>${item.harvestDate}</td>
                            <td>${item.shelfLifeInDays} Days</td>
                            <td>
                                <span class="badge
                                    ${item.status == 'Fresh' ? 'status-fresh' : ''}
                                    ${item.status == 'Ripening' ? 'status-ripening' : ''}
                                    ${item.status == 'Near-Expiry' ? 'status-expiry' : ''}">
                                    ${item.status}
                                </span>
                            </td>
                            <td>
                                <a href="<c:url value='/produce/edit?id=${item.id}'/>" class="btn btn-edit">Edit</a>
                                <a href="<c:url value='/produce/delete?id=${item.id}'/>"
                                   class="btn btn-delete"
                                   onclick="return confirm('Remove this batch from inventory?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="7" style="text-align:center; padding: 50px; color: #999;">
                            The warehouse is empty. Register a new harvest to start tracking. 🚜
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>

    <div style="margin-top: 30px;">
        <a href="<c:url value='/'/>" style="color: #388e3c; text-decoration: none; font-weight: bold;">&larr; Warehouse Dashboard</a>
    </div>
</div>

</body>
</html>