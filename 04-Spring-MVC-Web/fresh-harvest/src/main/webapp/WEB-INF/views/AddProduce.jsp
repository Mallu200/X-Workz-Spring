<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>FreshHarvest | New Entry</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #f1f8e9; margin: 40px; }
        .form-card {
            background: white; padding: 35px; border-radius: 12px;
            max-width: 600px; margin: auto; box-shadow: 0 10px 25px rgba(0,0,0,0.05);
            border-left: 8px solid #4caf50;
        }
        h2 { color: #2e7d32; margin-top: 0; }
        .form-group { margin-bottom: 20px; }
        label { display: block; margin-bottom: 8px; font-weight: bold; color: #388e3c; }
        input, select, textarea {
            width: 100%; padding: 12px; border: 1px solid #c8e6c9;
            border-radius: 6px; box-sizing: border-box; background-color: #fafafa;
        }
        input:focus { outline: none; border-color: #4caf50; background-color: #fff; }
        .error { color: #d32f2f; font-size: 0.85em; margin-top: 5px; display: block; }
        .btn-submit {
            background-color: #4caf50; color: white; padding: 15px 25px;
            border: none; border-radius: 6px; width: 100%; cursor: pointer;
            font-size: 18px; font-weight: bold; transition: background 0.3s;
        }
        .btn-submit:hover { background-color: #388e3c; }
        .back-link { text-align: center; margin-top: 20px; }
        .back-link a { text-decoration: none; color: #689f38; font-weight: bold; }
    </style>
</head>
<body>

<div class="form-card">
    <h2>🚜 Register Fresh Harvest</h2>
    <p style="color: #81c784; margin-bottom: 25px;">Enter organic produce details for warehouse logging.</p>

    <form:form action="save" method="POST" modelAttribute="produceDto">

        <form:hidden path="id" />

        <div class="form-group">
            <label>Produce Name:</label>
            <form:input path="name" placeholder="e.g., Organic Alphonso Mango" />
            <form:errors path="name" cssClass="error" />
        </div>

        <div style="display: flex; gap: 20px;">
            <div class="form-group" style="flex: 1;">
                <label>Category:</label>
                <form:select path="category">
                    <form:option value="" label="-- Select --" />
                    <form:option value="Vegetables" label="Vegetables" />
                    <form:option value="Fruits" label="Fruits" />
                    <form:option value="Grains" label="Grains" />
                    <form:option value="Dairy" label="Dairy" />
                </form:select>
                <form:errors path="category" cssClass="error" />
            </div>
            <div class="form-group" style="flex: 1;">
                <label>Harvest Date:</label>
                <form:input path="harvestDate" type="date" />
                <form:errors path="harvestDate" cssClass="error" />
            </div>
        </div>

        <div style="display: flex; gap: 20px;">
            <div class="form-group" style="flex: 1;">
                <label>Quantity:</label>
                <form:input path="quantity" type="number" />
                <form:errors path="quantity" cssClass="error" />
            </div>
            <div class="form-group" style="flex: 1;">
                <label>Unit:</label>
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
                <form:input path="shelfLifeInDays" type="number" placeholder="e.g., 7" />
                <form:errors path="shelfLifeInDays" cssClass="error" />
            </div>
            <div class="form-group" style="flex: 1;">
                <label>Initial Status:</label>
                <form:select path="status">
                    <form:option value="Fresh" label="Fresh" />
                    <form:option value="Ripening" label="Ripening" />
                </form:select>
            </div>
        </div>

        <button type="submit" class="btn-submit">Add to Inventory</button>
    </form:form>

    <div class="back-link">
        <a href="<c:url value='/produce/viewAll'/>">&larr; Back to Inventory Library</a>
    </div>
</div>

</body>
</html>