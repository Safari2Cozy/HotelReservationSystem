<%@page import="java.util.List"%>
<%@page import="com.mycompany.LLH.Model.Booking"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Bookings</title>
    <style>
        /* Your CSS styling */
    </style>
    <script>
        function confirmDelete(bookingId) {
            if (confirm("Are you sure you want to delete this booking?")) {
                document.getElementById("deleteForm-" + bookingId).submit();
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>All Bookings</h1>
        <% 
            String message = (String) request.getAttribute("message");
            String error = (String) request.getAttribute("error");
            if (message != null) { 
        %>
            <div class="message"><%= message %></div>
        <% 
            } else if (error != null) { 
        %>
            <div class="error"><%= error %></div>
        <% 
            } 
        %>
        <%
            List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
            if (bookings != null && !bookings.isEmpty()) {
        %>
           <table>
    <!-- Table header -->
    <thead>
        <tr>
            <th>ID</th>
            <th>Room</th>
            <th>Start</th>
            <th>End</th>
            <th>User ID</th>
            <th>Actions</th>
        </tr>
    </thead>
    <!-- Table body -->
    <tbody>
        <% for (Booking booking : bookings) { %>
            <tr>
                <td><%= booking.getId() %></td>
                <td><%= booking.getRoom() %></td>
                <td><%= booking.getStart() %></td>
                <td><%= booking.getEnd() %></td>
                <td><%= booking.getUserId() %></td>
                <td>
                    <form id="deleteForm-<%= booking.getId() %>" action="AdminServlet" method="post" style="display:inline;">
                        <input type="hidden" name="submit" value="deleteBooking">
                        <input type="hidden" name="bookingId" value="<%= booking.getId() %>">
                        <button type="button" onclick="confirmDelete(<%= booking.getId() %>)">Delete</button>
                    </form>
                </td>
            </tr>
        <% } %>
    </tbody>
</table>

<script>
    function confirmDelete(bookingId) {
        if (confirm("Are you sure you want to delete this booking?")) {
            document.getElementById("deleteForm-" + bookingId).submit();
        }
    }
</script>
        <% 
            } else { 
        %>
            <p>No bookings found.</p>
        <% 
            } 
        %>
    </div>
</body>
</html>
