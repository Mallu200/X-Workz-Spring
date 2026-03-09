<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>WonderWand | Edit Production</title>
    <style>
        body { font-family: 'Comic Sans MS', cursive, sans-serif; background-color: #e0f7fa; margin: 40px; }
        .update-container {
            background: white; padding: 35px; border-radius: 25px;
            max-width: 550px; margin: auto; box-shadow: 0px 12px 30px rgba(0,0,0,0.15);
            border-top: 10px solid #0288d1; /* Blue for editing mode */
        }
        h2 { color: #01579b; text-align: center; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #0277bd; }
        input, select, textarea { width: 100%; padding: 12px; border: 2px solid #b3e5fc; border-radius: 12px; box-sizing: border-box; }
        .readonly-id { background-color: #f5f5f5; color: #78909c; cursor: not-allowed; }
        .error { color: #d32f2f; font-size: 0.85em; font-weight: bold; }
        .btn-update {
            background-color: #0288d1; color: white; padding: 15px;
            border: none; border-radius: 30px; width: 100%; cursor: pointer; font-size: 18px; font-weight: bold;
            box-shadow: 0 4px 0 #01579b; transition: 0.2s;
        }
        .btn-update:hover { background-color: #01579b; }
        .btn-update:active { transform: translateY(2px); box-shadow: 0 2px 0 #01579b; }
        .cancel-link { display: block; text-align: center; margin-top: 20px; text-decoration: none; color: #546e7a; font-weight: bold; }
    </style>
</head>
<body>

<div class="update-container">
    <h2>🛠️ Update Video Production</h2>
    <p style="text-align:center; color: #0288d1;">Editing: <strong>${videoDto.title}</strong></p>
    <hr style="border: 1px solid #e1f5fe; margin-bottom: 20px;">

    <form:form action="save" method="POST" modelAttribute="videoDto">

        <form:hidden path="id" />

        <div class="form-group">
            <label>YouTube Title:</label>
            <form:input path="title" />
            <form:errors path="title" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Educational Category:</label>
            <form:select path="category">
                <form:option value="Numbers" label="🔢 Numbers & Counting" />
                <form:option value="Alphabet" label="🔤 ABCs & Letters" />
                <form:option value="Moral" label="🌟 Moral Stories" />
                <form:option value="Colors" label="🎨 Learning Colors" />
            </form:select>
        </div>

        <div class="form-group">
            <label>Bamboo's Mascot Activity:</label>
            <form:input path="mascotActivity" />
            <form:errors path="mascotActivity" cssClass="error" />
        </div>

        <div class="form-group">
            <label>Video Script / Description:</label>
            <form:textarea path="description" />
            <form:errors path="description" cssClass="error" />
        </div>

        <div class="form-group" style="display: flex; gap: 15px;">
            <div style="flex: 1;">
                <label>Duration (Mins):</label>
                <form:input path="durationInMinutes" type="number" />
            </div>
            <div style="flex: 1;">
                <label>Production Status:</label>
                <form:select path="status">
                    <form:option value="Draft" label="Draft" />
                    <form:option value="In-Production" label="In-Production" />
                    <form:option value="Uploaded" label="✅ Uploaded to YouTube" />
                    <form:option value="Private" label="🔒 Private/Archive" />
                </form:select>
            </div>
        </div>

        <button type="submit" class="btn-update">Save Changes</button>
    </form:form>

    <a href="<c:url value='/video/viewLibrary'/>" class="cancel-link">Discard Changes & Return</a>
</div>

</body>
</html>