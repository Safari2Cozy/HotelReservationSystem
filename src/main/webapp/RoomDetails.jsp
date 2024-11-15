<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.LLH.ServiceImpl.RoomServiceImpl" %>
<%@ page import="com.mycompany.LLH.Service.RoomService" %>
<%@ page import="com.mycompany.LLH.DaoImpl.RoomDaoImpl" %>
<%@ page import="com.mycompany.LLH.Model.Room" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Get the room ID from the request
    int roomId = Integer.parseInt(request.getParameter("roomId"));

    // Fetch the room details using the room ID
    RoomService roomService = new RoomServiceImpl(new RoomDaoImpl());
    Room room = new Room();
    room.setId(roomId);
    Room fetchedRoom = roomService.getRoom(room);

    // Directly specifying the image URLs for each room type
    String villaImageUrl = "images/room3-3.jpg";
    String presidentialImageUrl = "images/room2-2.jpg";
    String standardImageUrl = "images/room1-2.jpg";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Room Details</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');

        body {
            margin: 0;
            padding: 0;
            font-family: 'Roboto', sans-serif;
            background: url('images/image11111.jpg') no-repeat center center fixed;
            color: #ffffff;
            background-color: rgba(0, 0, 0, 0.7); /* Fallback color */
            background-blend-mode: darken;
        }

        /* Importing nav-bar styling from Room.jsp */
        .nav-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: rgba(0, 0, 0, 0.8);
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
            height: 100px;
            margin-right: 1rem;
        }

        .nav-bar .nav-items {
            display: flex;
            justify-content: flex-end;
            flex-grow: 1;
        }

        .nav-bar .nav-items a {
            color: #ffffff;
            text-decoration: none;
            margin: 0 1rem;
            font-size: 1rem;
        }

        .nav-bar .nav-items a:hover {
            text-decoration: underline;
        }

        /* End of imported nav-bar styling */

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding-top: 100px;
        }

        .room-container {
            margin-top: 20px;
        }

        .room-type {
            border-radius: 5px;
            overflow: hidden;
            background-color: rgba(0, 0, 0, 0.8);
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            margin-bottom: 20px;
        }

        .room-type img {
            width: 100%; /* Image takes full width */
            height: auto; /* Maintain aspect ratio */
            display: block; /* Remove default inline display */
            border-radius: 5px 5px 0 0; /* Rounded corners for top */
        }

        .room-type-details {
            padding: 20px;
            text-align: left;
        }

        .room-type h3 {
            margin-top: 0;
            font-size: 1.5rem;
        }

        .room-type p {
            margin-bottom: 0.5rem;
        }

        .book-now-button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin-top: 10px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .book-now-button:hover {
            background-color: #45a049; /* Darker Green */
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
            <a href="Booking.jsp?roomId=1">Bookings</a> <!-- Adjusted link with roomId -->
            <a href="index.html">Log Out</a>
            <a href="Adminlogin.jsp">Admin</a>
        </div>
    </div>
    <div class="container">
        <div class="room-container">
            <!-- Villa Room Type -->
            <div class="room-type">
                <img src="<%= villaImageUrl %>" alt="Villa">
                <div class="room-type-details">
                    <h3>Villa</h3>
                    <p>Rate per night: R10,000</p>
                    <p>Accommodates: 6 people</p>
                    <p>Features: Swimming pool</p>
                    <a href="Booking.jsp" class="book-now-button">Book Now</a>
                </div>
            </div>
            
            <!-- Presidential Suite Room Type -->
            <div class="room-type">
                <img src="<%= presidentialImageUrl %>" alt="Presidential Suite">
                <div class="room-type-details">
                    <h3>Presidential Suite</h3>
                    <p>Rate per night: R6,000</p>
                    <p>Accommodates: 4 people</p>
                    <a href="Booking.jsp" class="book-now-button">Book Now</a>
                </div>
            </div>
            
            <!-- Standard Room Type -->
            <div class="room-type">
                <img src="<%= standardImageUrl %>" alt="Standard Room">
                <div class="room-type-details">
                    <h3>Standard Room</h3>
                    <p>Rate per night: R2,000</p>
                    <p>Accommodates: 2 people</p>
                    <a href="Booking.jsp" class="book-now-button">Book Now</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
