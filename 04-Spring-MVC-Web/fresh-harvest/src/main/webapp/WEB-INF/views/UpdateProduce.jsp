<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>FreshHarvest | Update Inventory</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #f1f8e9; margin: 40px; }
        .update-card {
            background: white; padding: 35px; border-radius: 12px;
            max-width: 600px; margin: auto; box-shadow: 0 15px 35px rgba(0,0,0,0.1);
            border-top: 10px solid #2e7d32;
        }
        h2 { color: #1b5e20; text-align: center; margin-top: 0; }
        .form-group { margin-bottom: 20px; }
        label { display: block; margin-bottom: 8px; font-weight: bold; color: #388e3c; }
        input, select, textarea {
            width: 100%; padding: 12px; border: 1px solid #c8e6c9;
            border-radius: 6px; box-sizing: border-box; background-color: #fdfdfd;
        }
        .readonly-field { background-color: #f5f5f5; color: #757575; cursor: not-allowed; }
        .error { color: #d32f2f; font-size: 0.85em; font-weight: bold; }
        .btn-update {
            background-color: #2e7d32; color: white; padding: 15px;
            border: none; border-radius: 6px; width: 100%; cursor: pointer;
            font-size: 18px; font-weight: bold; transition: 0.3s;
        }
        .btn-update:hover { background-color: #1b5e20; box-shadow: 0 4px 12px rgba(0,0,0,0.2); }
        .cancel-link { display: block; text-align: center; margin-top: 20px; text-decoration: none; color: #616161; font-weight: bold; }
    </style>
</head>
<body>

<div class="update-card">
    <h2>🛠️ Update Inventory Batch</h2>
    <p style="text-align:center; color: #4caf50;">Modifying Record for: <strong>${produceDto.name}</strong></p>
    <hr style="border: 0; border-top: 1px solid #e8f5e9; margin-bottom: 25px;">

    <form:form action="save" method="POST" modelAttribute="produceDto">

        <form:hidden path="id" />

        <div class="form-group">
            <label>Produce Name:</label>
            <form:input path="name" />
            <form:errors path="name" cssClass="error" />
        </div>

        <div style="display: flex; gap: 20px;">
            <div class="form-group" style="flex: 1;">
                <label>Category:</label>
                <form:select path="category">
                    <form:option value="Vegetables" label="Vegetables" />
                    <form:option value="Fruits" label="Fruits" />
                    <form:option value="Grains" label="Grains" />
                    <form:option value="Dairy" label="Dairy" />
                </form:select>
            </div>
            <div class="form-group" style="flex: 1;">
                <label>Harvest Date:</label>
                <form:input path="harvestDate" type="date" />
            </div>
        </div>

        <div style="display: flex; gap: 20px;">
            <div class="form-group" style="flex: 1;">
                <label>Update Quantity:</label>
                <form:input path="quantity" type="number" />
                <form:errors path="quantity" cssClass="error" />
            </div>
            <div class="form-group" style="flex: 1;">
                <label>Unit Type:</label>
                <form:select path="unit">
                    <form:option value="KG" label="Kilograms (KG)" />
                    <form:option value="Liters" label="Liters (L)" />
                    <form:option value="Boxes" label="Boxes" />
                    <form:option value="Bunches" label="Bunches" />
                </form:select>
            </div>
        </div>

        <div style="display: flex; gap: 20px;">
            <div class="form-group" style="flex: 1;">
                <label>Shelf Life (Days):</label>
                <form:input path="shelfLifeInDays" type="number" />
            </div>
            <div class="form-group" style="flex: 1;">
                <label>Current Status:</label>
                <form:select path="status">
                    <form:option value="Fresh" label="Fresh" />
                    <form:option value="Ripening" label="Ripening" />
                    <form:option value="Near-Expiry" label="⚠️ Near-Expiry" />
                    <form:option value="Expired" label="❌ Expired" />
                </form:select>
            </div>
        </div>

        <button type="submit" class="btn-update">Save Inventory Changes</button>
    </form:form>

    <a href="<c:url value='/produce/viewAll'/>" class="cancel-link">Discard Changes & Return</a>
</div>

</body>
</html>