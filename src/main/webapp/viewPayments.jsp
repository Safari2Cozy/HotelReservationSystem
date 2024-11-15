<%-- 
    Document   : viewPayments
    Created on : 24 Jun 2024, 10:39:09 PM
    Author     : Jacqui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Payments</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: #fff;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Payments</h2>
        <table>
            <tr>
                <th>Payment ID</th>
                <th>Room Type</th>
                <th>Number of Nights</th>
                <th>Total Cost</th>
            </tr>
            <c:forEach var="payment" items="${payments}">
                <tr>
                    <td>${payment.paymentId}</td>
                    <td>${payment.roomType}</td>
                    <td>${payment.numberOfNights}</td>
                    <td>R${payment.totalCost}</td>
                </tr>
            </c:forEach>
        </tbody>
        </table>
    </c:if>
    <c:if test="${empty payments}">
        <p>No payments found.</p>
    </c:if>
</body>
</html>


