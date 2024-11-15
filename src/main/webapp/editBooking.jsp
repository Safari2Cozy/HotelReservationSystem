<%-- 
    Document   : editBooking
    Created on : 23 Jun 2024, 7:33:33 PM
    Author     : Jacqui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Booking</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Roboto', sans-serif;
            background: url('images/wp8610067.jpg') no-repeat center center fixed;
            background-size: cover;
            color: #000000;
            background-color: rgba(255, 255, 255, 0.9);
            background-blend-mode: lighten;
        }
        .container {
            text-align: center;
            padding-top: 10%;
        }
        h1 {
            font-size: 3rem;
            margin-bottom: 3rem;
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
        }
        form {
            max-width: 400px;
            margin: 0 auto;
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            color: #000000;
        }
        label {
            font-size: 0.9rem;
        }
        input[type="text"], input[type="number"], button {
            width: calc(100% - 20px);
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit Booking</h1>
        <form action="AdminServlet" method="post">
            <input type="hidden" name="submit" value="updateBooking">
            <input type="hidden" name="id" value="${param.id}">
            <label for="room">Room:</label>
            <input type="text" id="room" name="room" value="${param.room}" required>
            <br>
            <label for="start">Start:</label>
            <input type="text" id="start" name="start" value="${param.start}" required>
            <br>
            <label for="end">End:</label>
            <input type="text" id="end" name="end" value="${param.end}" required>
            <br>
            <label for="user_id">User ID:</label>
            <input type="number" id="user_id" name="user_id" value="${param.userId}" required>
            <br>
            <button type="submit">Update Booking</button>
        </form>
    </div>
</body>
</html>
