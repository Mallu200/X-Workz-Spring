<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>WonderWand | Content Library</title>
    <style>
        body { font-family: 'Comic Sans MS', cursive, sans-serif; background-color: #e0f7fa; margin: 30px; }
        .container { background: white; padding: 25px; border-radius: 20px; box-shadow: 0 10px 25px rgba(0,0,0,0.1); }
        h2 { color: #01579b; border-bottom: 3px solid #ffca28; padding-bottom: 10px; display: inline-block; }

        /* Table Styling */
        table { width: 100%; border-collapse: collapse; margin-top: 20px; background: #fff; }
        th { background-color: #0288d1; color: white; padding: 15px; text-align: left; border-radius: 5px 5px 0 0; }
        td { padding: 15px; border-bottom: 1px solid #e1f5fe; color: #333; }
        tr:hover { background-color: #f1f8ff; }

        /* Status Badges */
        .badge { padding: 5px 10px; border-radius: 20px; font-size: 12px; font-weight: bold; text-transform: uppercase; }
        .badge-draft { background-color: #cfd8dc; color: #455a64; }
        .badge-production { background-color: #fff9c4; color: #fbc02d; }
        .badge-uploaded { background-color: #c8e6c9; color: #2e7d32; }

        /* Action Buttons */
        .btn { text-decoration: none; padding: 8px 15px; border-radius: 25px; font-size: 13px; font-weight: bold; transition: 0.3s; }
        .btn-edit { background-color: #ffca28; color: #5d4037; margin-right: 5px; }
        .btn-delete { background-color: #ef9a9a; color: #b71c1c; }
        .btn-add { background-color: #0288d1; color: white; float: right; margin-bottom: 15px; }
        .btn:hover { opacity: 0.8; transform: scale(1.05); }

        /* Alerts */
        .alert { padding: 12px; margin-bottom: 20px; border-radius: 10px; font-weight: bold; text-align: center; }
        .alert-success { background-color: #dcedc8; color: #33691e; border: 1px solid #c5e1a5; }
        .alert-error { background-color: #ffcdd2; color: #b71c1c; }
    </style>
</head>
<body>

<div class="container">
    <a href="<c:url value='/video/create'/>" class="btn btn-add">✨ Add New Idea</a>
    <h2>YouTube Content Library</h2>

    <c:if test="${not empty msg}">
        <div class="alert alert-success">🎈 ${msg}</div>
    </c:if>

    <c:if test="${not empty deleteMsg}">
        <div class="alert alert-error">🗑️ ${deleteMsg}</div>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>YouTube Title</th>
                <th>Category</th>
                <th>Bamboo's Activity</th>
                <th>Duration</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty videoList}">
                    <c:forEach var="video" items="${videoList}">
                        <tr>
                            <td><strong>${video.title}</strong></td>
                            <td>${video.category}</td>
                            <td>🐼 ${video.mascotActivity}</td>
                            <td>${video.durationInMinutes} mins</td>
                            <td>
                                <span class="badge
                                    ${video.status == 'Draft' ? 'badge-draft' : ''}
                                    ${video.status == 'In-Production' ? 'badge-production' : ''}
                                    ${video.status == 'Uploaded' ? 'badge-uploaded' : ''}">
                                    ${video.status}
                                </span>
                            </td>
                            <td>
                                <a href="<c:url value='/video/edit?id=${video.id}'/>" class="btn btn-edit">Edit</a>
                                <a href="<c:url value='/video/delete?id=${video.id}'/>"
                                   class="btn btn-delete"
                                   onclick="return confirm('Delete this video idea from the library?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="6" style="text-align:center; padding: 40px; color: #90a4ae;">
                            No video ideas yet. Start by clicking "Add New Idea"! ✨
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>

    <div style="margin-top: 25px;">
        <a href="<c:url value='/'/>" style="color: #0288d1; text-decoration: none; font-weight: bold;">&larr; Studio Dashboard</a>
    </div>
</div>

</body>
</html>