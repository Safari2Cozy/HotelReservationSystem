<%-- 
    Document   : BookingConfirmation
    Created on : Jun 21, 2024, 12:07:13 PM
    Author     : Train
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Booking Confirmation</title>
</head>
<body>
    <h1>Booking Confirmation</h1>
    <p>Your booking has been confirmed successfully!</p>
    
    <!-- Include option to send confirmation email -->
    <form action="EmailServlet" method="post">
        <input type="hidden" name="totalCost" value="<%= request.getParameter("totalCost") %>">
        <button type="submit">Send Confirmation Email</button>
    </form>
    
    <%-- JavaScript for redirecting to index.jsp after 30 seconds --%>
<script>
    setTimeout(function() {
        window.location.href = "index.html"; // Redirect after 30 seconds
    }, 30000); // 30 seconds delay in milliseconds (30 * 1000)
</script>
</body>
</html>

