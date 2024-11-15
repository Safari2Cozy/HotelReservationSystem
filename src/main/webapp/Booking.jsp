<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: url('images/image11111.jpg') no-repeat center center fixed;
            background-color: #f0f0f0;
            padding: 20px;
            margin: 0;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: rgba(0, 0, 0, 0.8);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            display: flex;
            flex-direction: column;
        }
        .form-group {
            margin-bottom: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .form-group label {
            font-weight: bold;
            color: #ffffff; /* White color */
            flex: 1;
        }
        .form-group input[type="date"] {
            width: calc(100% - 12px);
            padding: 6px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            flex: 3;
        }
        .form-group select, .form-group input[type="number"] {
            width: 100%;
            padding: 6px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            flex: 3;
        }
        .buttons {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
        }
        .buttons button {
            padding: 8px 16px; /* Smaller padding */
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px; /* Smaller font size */
            flex: 1;
            margin: 0 5px; /* Add margin for spacing */
        }
        /* Back button styling */
        .buttons .back-button {
            background-color: #dc3545; /* Red color */
        }
        .buttons .back-button:hover {
            background-color: #c82333; /* Darker red on hover */
        }
    </style>
    <script>
        function calculateCost() {
            var roomType = document.getElementById("roomType").value;
            var startDate = new Date(document.getElementById("startDate").value);
            var endDate = new Date(document.getElementById("endDate").value);
            var numberOfNights = Math.ceil((endDate - startDate) / (1000 * 60 * 60 * 24));
            var roomCost = 0;
            
            switch (roomType) {
                case "presidential_suite":
                    roomCost = 6000;
                    break;
                case "villa":
                    roomCost = 10000;
                    break;
                case "standard":
                    roomCost = 2000;
                    break;
                default:
                    roomCost = 0;
                    break;
            }
            
            var totalCost = roomCost * numberOfNights;
            
            document.getElementById("numberOfNights").value = numberOfNights;
            document.getElementById("totalCost").innerHTML = "Total Cost: R" + totalCost.toFixed(2);
            document.getElementById("totalCostHidden").value = totalCost.toFixed(2);
        }
        
        function confirmPayment() {
            var totalCost = document.getElementById("totalCost").innerText;
            var confirmed = confirm("Total Cost: " + totalCost + "\n\nProceed to payment?");
            return confirmed;
        }

        function goBack() {
            window.history.back();
        }
    </script>
</head>
<body>
    <div class="container">
        <h2 style="color: #ffffff;">Make a Reservation</h2>
        <form action="BookingServlet" method="post" onsubmit="return confirmPayment()">
            <div class="form-group">
                <label for="startDate">Start Date:</label>
                <input type="date" id="startDate" name="startDate" onchange="calculateCost()" required>
            </div>
            <div class="form-group">
                <label for="endDate">End Date:</label>
                <input type="date" id="endDate" name="endDate" onchange="calculateCost()" required>
            </div>
            <div class="form-group">
                <label for="roomType">Room Type:</label>
                <select id="roomType" name="roomType" onchange="calculateCost()" required>
                    <option value="">Select a room type</option>
                    <option value="presidential_suite">Presidential Suite (R6000 per night)</option>
                    <option value="villa">Villa (R10000 per night)</option>
                    <option value="standard">Standard (R2000 per night)</option>
                </select>
            </div>
            <div class="form-group">
                <label for="numberOfNights">Number of Nights:</label>
                <input type="number" id="numberOfNights" name="numberOfNights" min="1" readonly>
            </div>
            <div class="form-group">
                <p id="totalCost" style="color: #ffffff;">Total Cost:</p>
                <input type="hidden" id="totalCostHidden" name="totalCost">
            </div>
            <div class="form-group">
                <label for="paymentMethod">Payment Method:</label>
                <select id="paymentMethod" name="paymentMethod" required>
                    <option value="">Select payment method</option>
                    <option value="cash">Cash</option>
                    <option value="card">Credit Card</option>
                </select>
            </div>
            <div class="buttons">
                <!-- Back button -->
                <button type="button" class="back-button" onclick="goBack()">Back</button>
                <button type="submit">Proceed to Payment</button>
            </div>
        </form>
    </div>
</body>
</html>
