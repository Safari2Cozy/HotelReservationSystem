<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');
        body {
            margin: 0;
            padding: 0;
            font-family: 'Roboto', sans-serif;
            background: url('images/night-dark-hotel-luxury.jpg') no-repeat center center fixed;
            background-size: cover;
            color: #ffffff;
            background-color: rgba(0, 0, 0, 0.7);
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
        .container {
            text-align: center;
            padding-top: 10%;
        }
        h1 {
            font-size: 3rem;
            margin-bottom: 3rem;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
        }
        form {
            max-width: 400px;
            margin: 0 auto;
            background-color: rgba(0, 0, 0, 0.8);
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            font-size: 0.9rem;
        }
        input[type="text"], input[type="password"], input[type="submit"] {
            width: calc(100% - 20px);
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #ffffff;
            text-decoration: none;
            font-size: 0.9rem;
        }
        a:hover {
            text-decoration: underline;
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
            <a href="Adminlogin.jsp">Admin</a>
        </div>
    </div>
    <div class="container">
        <h1>Registration</h1>
        <form method="post" action="UserServlet">
            <label for="name">Name:</label><br>
            <input type="text" id="name" name="name"><br>
            <label for="surname">Surname:</label><br>
            <input type="text" id="surname" name="surname"><br>
            <label for="email">Email:</label><br>
            <input type="text" id="email" name="email"><br>
            <label for="password">Password:</label><br>
            <input type="password" id="password" name="password"><br>
            <input type="submit" name="submit" value="Register">
        </form>
        <a href="Login.jsp">Login</a>
        <!-- Success message placeholder -->
        <div id="successMessage" style="display:none; color:green; margin-top:20px;">
            You have been successfully registered.
        </div>
    </div>
    <script>
        // Check if there is a query parameter indicating success
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.get('registered') === 'true') {
            document.getElementById('successMessage').style.display = 'block';
            setTimeout(() => {
                window.location.href = 'Login.jsp';
            }, 3000); // Redirect after 3 seconds
        }
    </script>
</body>
</html>
