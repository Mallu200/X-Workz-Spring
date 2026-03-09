<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>WonderWand | New Video Idea</title>
    <style>
        body { font-family: 'Comic Sans MS', cursive, sans-serif; background-color: #e0f7fa; margin: 40px; }
        .form-container {
            background: white; padding: 30px; border-radius: 20px;
            max-width: 550px; margin: auto; box-shadow: 0px 10px 25px rgba(0,0,0,0.1);
            border-top: 10px solid #ffca28;
        }
        h2 { color: #01579b; text-align: center; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #0288d1; }
        input, select, textarea { width: 100%; padding: 12px; border: 2px solid #b3e5fc; border-radius: 10px; box-sizing: border-box; }
        textarea { resize: vertical; height: 80px; }
        .error { color: #d32f2f; font-size: 0.85em; margin-top: 5px; font-weight: bold; }
        .btn-submit {
            background-color: #ffca28; color: #5d4037; padding: 15px;
            border: none; border-radius: 30px; width: 100%; cursor: pointer; font-size: 18px; font-weight: bold;
            box-shadow: 0 4px 0 #ffb300; margin-top: 10px;
        }
        .btn-submit:active { transform: translateY(2px); box-shadow: 0 2px 0 #ffb300; }
        .footer-links { text-align: center; margin-top: 20px; }
        .footer-links a { text-decoration: none; color: #0288d1; font-weight: bold; }
    </style>
</head>
<body>

<div class="form-container">
    <h2>✨ Create New Video Magic</h2>
    <hr style="border: 1px solid #e1f5fe;">

    <form:form action="save" method="POST" modelAttribute="videoDto">

        <form:hidden path="id" />

        <div class="form-group">
            <label>YouTube Title (SEO Friendly):</label>
            <form:input path="title" placeholder="e.g., Bamboo Learns the Alphabet" />
            <form:errors path="title" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Educational Category:</label>
            <form:select path="category">
                <form:option value="" label="-- Choose Category --" />
                <form:option value="Numbers" label="🔢 Numbers & Counting" />
                <form:option value="Alphabet" label="🔤 ABCs & Letters" />
                <form:option value="Moral" label="🌟 Moral Stories" />
                <form:option value="Colors" label="🎨 Learning Colors" />
            </form:select>
            <form:errors path="category" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Bamboo's Activity (Mascot):</label>
            <form:input path="mascotActivity" placeholder="e.g., Bamboo is eating a magical bamboo shoot" />
            <form:errors path="mascotActivity" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Description / Script Hook:</label>
            <form:textarea path="description" placeholder="Write a short summary of the video script..." />
            <form:errors path="description" cssClass="error" />
        </div>

        <div class="form-group" style="display: flex; gap: 10px;">
            <div style="flex: 1;">
                <label>Duration (Mins):</label>
                <form:input path="durationInMinutes" type="number" />
            </div>
            <div style="flex: 1;">
                <label>Initial Status:</label>
                <form:select path="status">
                    <form:option value="Draft" label="Draft" />
                    <form:option value="In-Production" label="In-Production" />
                </form:select>
            </div>
        </div>

        <button type="submit" class="btn-submit">Add to Production Library</button>
    </form:form>

    <div class="footer-links">
        <a href="<c:url value='/video/viewLibrary'/>">View Content Library &rarr;</a>
    </div>
</div>

</body>
</html>