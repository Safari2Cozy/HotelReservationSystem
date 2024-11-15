<%-- 
    Document   : Userhome.jsp
    Created on : Jun 12, 2024, 9:17:41 AM
    Author     : Train
--%>


<%@page import="com.mycompany.LLH.Model.Room"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Room> rooms = (List<Room>)request.getAttribute("rooms");
    int unreserved = (Integer)request.getAttribute("unreserved");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rooms To Choose from</title>
    </head>
    <body>
        <%if(unreserved!=0){
            for(Room room:rooms){
            %>
                <div style="background-color: red;color:white;margin-bottom: 10px;height: 40px">
                    <a href="PerRoomServlet?rooms=<%=room.getId()%>">reserve room</a>
                    <div><%="room number : "+room.getId()%></div>
                    </div>
            <%}
               }%>

    </body>
</html>
