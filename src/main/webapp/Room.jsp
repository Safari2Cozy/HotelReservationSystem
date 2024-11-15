<%@page import="com.mycompany.LLH.ServiceImpl.RoomServiceImpl"%>
<%@page import="com.mycompany.LLH.Service.RoomService"%>
<%@page import="com.mycompany.LLH.DaoImpl.RoomDaoImpl"%>
<%@page import="com.mycompany.LLH.Model.Room"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rooms Available</title>
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
            z-index: 1000; /* Ensure the nav bar stays on top */
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
        .container {
            text-align: center;
            padding-top: 10%;
        }
        h1 {
            font-size: 3rem;
            margin-bottom: 3rem;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
        }
        .room {
            max-width: 400px;
            margin: 0 auto;
            background-color: rgba(0, 0, 0, 0.8);
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            position: relative;
            overflow: hidden;
        }
        .room h3 {
            margin-top: 0;
        }
        .room p {
            margin: 5px 0;
        }
        .book-button, .view-gallery-button {
            display: block;
            margin: 10px auto;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            font-size: 14px;
        }
        .book-button:hover, .view-gallery-button:hover {
            background-color: #45a049;
        }
        .gallery {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: -1;
            transition: background-image 0.5s ease-in-out;
            background-size: cover;
            background-position: center;
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
            <a href="index.html">Log Out<a/>
            <a href="Adminlogin.jsp">Admin</a>
        </div>
    </div>
    <div class="container">
        <h1>Rooms Available</h1>
        <% 
            RoomService roomService = new RoomServiceImpl(new RoomDaoImpl());
            List<Room> rooms = roomService.getRooms();
            if (rooms != null) {
                for (Room room : rooms) {
        %>
        <div class="room" onmouseover="changeGalleryImage(event, <%= room.getId() %>)">
            <div class="gallery" id="gallery-<%= room.getId() %>"></div>
            <h3>Room Type: <%= room.getType() %></h3>
            <p>Room Number: <%= room.getId() %></p>
            <p>Occupants: <%= room.getOccupants() %></p>
            <p>Floor: <%= room.getFloor() %></p>
            <p>Rate per night: <%= room.getRate() %></p>
            <p>Status: <%= room.getStatus() %></p>
            <a class="book-button" href="Booking.jsp?roomId=<%= room.getId() %>">Book Now</a>
            <a class="view-gallery-button" href="RoomDetails.jsp?roomId=<%= room.getId() %>">View Gallery</a>
        </div>
        <% 
                }
            } else { 
        %>
        <p>No rooms available at the moment.</p>
        <% } %>
    </div>

    <script>
        const galleryImages = {
            1: ['images/room1-1.jpg', 'images/room1-2.jpg', 'images/room1-3.jpg'],
            2: ['images/room2-1.jpg', 'images/room2-2.jpg', 'images/room2-3.jpg'],
            3: ['images/room3-1.jpg', 'images/room3-2.jpg', 'images/room3-3.jpg']
            // Add more room images mapping here
        };

        function changeGalleryImage(event, roomId) {
            const galleryElement = document.getElementById(`gallery-${roomId}`);
            if (!galleryElement) return;

            const images = galleryImages[roomId] || [];
            if (images.length === 0) return;

            let currentImageIndex = 0;
            galleryElement.style.backgroundImage = `url(${images[currentImageIndex]})`;

            galleryElement.addEventListener('mousemove', () => {
                currentImageIndex = (currentImageIndex + 1) % images.length;
                galleryElement.style.backgroundImage = `url(${images[currentImageIndex]})`;
            });
        }
    </script>
</body>
</html>
