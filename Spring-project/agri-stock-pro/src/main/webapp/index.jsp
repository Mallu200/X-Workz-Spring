<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // 122-UI: Application Entry Point - index.jsp loaded
    System.out.println("122-UI: User reached the Landing Page. Application is LIVE.");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agri-Stock Pro | Welcome</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Professional Agriculture Palette */
        :root {
            --agri-green: #2D5A27; /* Forest Green */
            --agri-sage: #F1F8E9;  /* Light Sage Background */
            --agri-dark: #1B3F17;  /* Deep Forest Accent */
            --agri-earth: #8D6E63; /* Soil Brown */
        }

        body {
            background-color: var(--agri-sage);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0;
        }

        .hero-section {
            background-color: #ffffff;
            padding: 60px;
            border-radius: 8px; /* Slightly rounded for professional feel */
            box-shadow: 0 10px 30px rgba(0,0,0,0.08);
            text-align: center;
            border-top: 8px solid var(--agri-green); /* Forest Green Accent */
            max-width: 650px;
            width: 90%;
        }

        .hero-title {
            color: var(--agri-dark);
            font-weight: 800;
            letter-spacing: -1px;
            margin-bottom: 20px;
            text-transform: uppercase;
        }

        .hero-subtitle {
            color: #4A5D4A;
            margin-bottom: 40px;
            line-height: 1.6;
            font-size: 1.1rem;
        }

        /* Professional button using the brand's primary green */
        .btn-enter {
            background-color: var(--agri-green);
            color: white;
            padding: 14px 40px;
            font-size: 1.1rem;
            font-weight: 600;
            border: none;
            border-radius: 4px;
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-block;
        }

        .btn-enter:hover {
            background-color: var(--agri-dark);
            color: #ffffff;
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(45, 90, 39, 0.3);
        }

        .brand-badge {
            background-color: var(--agri-sage);
            color: var(--agri-green);
            padding: 5px 15px;
            border-radius: 20px;
            font-size: 0.85rem;
            font-weight: bold;
            display: inline-block;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>

    <div class="hero-section">
        <% try { %>
            <div class="brand-badge">Inventory Management System</div>
            <h1 class="hero-title">AGRI-STOCK <span style="color: var(--agri-earth);">PRO</span></h1>

            <p class="hero-subtitle">
                Efficiently manage seeds, fertilizers, and pesticide stock levels.
                A professional solution built with <strong>Spring MVC</strong> and <strong>Hibernate</strong>
                to streamline agricultural supply chains.
            </p>

            <a href="stock/view" class="btn btn-enter">Enter Dashboard</a>

            <div class="mt-5">
                <hr style="border-top: 1px solid #e0e0e0;">
                <small class="text-muted">Part of the <strong>Learning-Spring-Framework</strong> Repository</small>
            </div>
        <% } catch (Exception e) {
            System.err.println("123-UI: Error rendering Index Landing Page: " + e.getMessage());
        } %>
    </div>

</body>
</html>