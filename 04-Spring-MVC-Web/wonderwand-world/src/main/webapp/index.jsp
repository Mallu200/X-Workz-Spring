<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WonderWand World | Creator Studio</title>
<style>
    body {
        font-family: 'Comic Sans MS', 'Chalkboard SE', cursive, sans-serif;
        background-color: #e0f7fa; /* Light Sky Blue */
        margin: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .studio-card {
        background: white;
        padding: 40px;
        border-radius: 20px;
        box-shadow: 0 15px 35px rgba(0,0,0,0.1);
        text-align: center;
        width: 480px;
        border-bottom: 10px solid #ffca28; /* Golden Yellow Accent */
    }
    .logo-circle {
        background-color: #0288d1;
        width: 100px;
        height: 100px;
        border-radius: 50%;
        margin: 0 auto 20px;
        display: flex;
        justify-content: center;
        align-items: center;
        color: white;
        font-size: 40px;
        box-shadow: 0 5px 15px rgba(2, 136, 209, 0.3);
    }
    h1 {
        color: #01579b;
        margin-bottom: 5px;
        font-size: 32px;
    }
    .subtitle {
        color: #0288d1;
        font-weight: bold;
        margin-bottom: 30px;
        display: block;
    }
    .btn-group {
        display: flex;
        flex-direction: column;
        gap: 15px;
    }
    .btn {
        text-decoration: none;
        padding: 15px 25px;
        border-radius: 50px;
        font-weight: bold;
        font-size: 18px;
        transition: transform 0.2s, background-color 0.3s;
        display: block;
    }
    .btn:active {
        transform: scale(0.95);
    }
    .btn-add {
        background-color: #ffca28;
        color: #5d4037;
        border: 2px solid #ffb300;
    }
    .btn-add:hover {
        background-color: #ffb300;
    }
    .btn-library {
        background-color: #0288d1;
        color: white;
        border: 2px solid #01579b;
    }
    .btn-library:hover {
        background-color: #01579b;
    }
    .mascot-hint {
        margin-top: 30px;
        font-size: 14px;
        color: #78909c;
        font-style: italic;
    }
</style>
</head>
<body>

    <div class="studio-card">
        <div class="logo-circle">🪄</div>
        <h1>WonderWand World</h1>
        <span class="subtitle">Content Management Studio</span>

        <div class="btn-group">
            <a href="<c:url value='/video/create'/>" class="btn btn-add">✨ New Video Idea</a>
            <a href="<c:url value='/video/viewLibrary'/>" class="btn btn-library">📂 Content Library</a>
        </div>

        <div class="mascot-hint">
            Bamboo is waiting for new activities! 🐼
        </div>

        <p style="font-size: 10px; color: #b0bec5; margin-top: 20px;">
            &copy; 2026 WonderWand World Production | Powered by Spring MVC
        </p>
    </div>

</body>
</html>