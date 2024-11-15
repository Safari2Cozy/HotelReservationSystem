<%-- 
    Document   : Adminhom
    Created on : Jun 12, 2024, 10:10:13 AM
    Author     : Train
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Home</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Roboto', sans-serif;
            background: url('images/wp8610067.jpg') no-repeat center center fixed;
            background-size: cover;
            color: #000000;
            background-color: rgba(255, 255, 255, 0.9); /* Light theme */
            background-blend-mode: lighten;
        }
        .nav-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: rgba(255, 255, 255, 0.8);
            padding: 1rem 2rem;
            position: fixed;
            width: 100%;
            top: 0;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
            font-size: 0.9rem;
            z-index: 1000;
        }
        .nav-bar .logo {
            display: flex;
            align-items: center;
        }
        .nav-bar .logo img {
            height: 80px;
            margin-right: 1rem;
        }
        .nav-bar .nav-items {
            display: flex;
            justify-content: flex-end;
            flex-grow: 1;
        }
        .nav-bar .nav-items a {
            color: #000000;
            text-decoration: none;
            margin: 0 1rem;
            font-size: 1rem;
        }
        .nav-bar .nav-items a:hover {
            text-decoration: underline;
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
        input[type="text"],
        input[type="password"],
        input[type="email"],
        input[type="number"],
        input[type="submit"],
        button {
            width: calc(100% - 20px);
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }
        input[type="submit"],
        button {
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }
        input[type="submit"]:hover,
        button:hover {
            background-color: #45a049;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #000000;
            text-decoration: none;
            font-size: 0.9rem;
        }
        a:hover {
            text-decoration: underline;
        }
        .error-message {
            color: #ff0000;
            font-size: 14px;
            margin-top: 10px;
        }
        h2 {
            margin-top: 30px;
            margin-bottom: 10px;
        }
        @media screen and (max-width: 600px) {
            .nav-bar {
                padding: 0.5rem 1rem;
            }
            .nav-bar .logo img {
                height: 60px;
            }
            .container {
                padding-top: 15%;
            }
        }
    </style>
</head>
<body>
    <div class="nav-bar">
        <div class="logo">
            <a href="index.html">
                <img src="images/Llh-removebg-preview.png" alt="LLH Logo">
            </a>
        </div>
        <div class="nav-items">
            <a href="index.html">Home</a>
            <a href="Register.jsp">Register</a>
            <a href="Login.jsp">Login</a>
            <a href="Booking.jsp">Bookings</a>
            <a href="Adminlogin.jsp">Admin</a>
        </div>
    </div>
    <div class="container">
        <h1>Welcome Admin!</h1>
        
        <!-- Display messages -->
        <c:if test="${not empty message}">
            <p style="color: green;">${message}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>

        <!-- Form to add a new user -->
        <h2>Add User</h2>
        <form action="AdminServlet" method="post">
            <input type="hidden" name="submit" value="addUser">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
            <label for="surname">Surname:</label>
            <input type="text" id="surname" name="surname" required>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <button type="submit">Add User</button>
        </form>

        <!-- Form to delete a user -->
        <h2>Delete User</h2>
        <form action="AdminServlet" method="post">
            <input type="hidden" name="submit" value="deleteUser">
            <label for="userId">User ID:</label>
            <input type="number" id="userId" name="userId" required>
            <button type="submit">Delete User</button>
        </form>

        <!-- Form to add a new room -->
        <h2>Add Room</h2>
        <form action="AdminServlet" method="post">
            <input type="hidden" name="submit" value="addRoom">
            <label for="roomNumber">Room Number:</label>
            <input type="text" id="roomNumber" name="roomNumber" required>
            <label for="capacity">Capacity:</label>
            <input type="number" id="capacity" name="capacity" required>
            <label for="pricePerNight">Price per Night:</label>
            <input type="number" step="0.01" id="pricePerNight" name="pricePerNight" required>
            <button type="submit">Add Room</button>
        </form>

        <!-- View bookings -->
        <h2>Bookings</h2>
        <form action="AdminServlet" method="get">
            <button type="submit" name="submit" value="viewBookings">View Bookings</button>
        </form>

        <!-- View payments -->
        <h2>Payments</h2>
        <form action="AdminServlet" method="get">
            <button type="submit" name="submit" value="viewPayments">View Payments</button>
        </form>
    </div>
</body>
</html>
