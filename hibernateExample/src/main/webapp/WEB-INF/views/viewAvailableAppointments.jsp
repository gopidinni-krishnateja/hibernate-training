<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: semanticbits
  Date: 3/4/18
  Time: 3:41 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Appointments</title>
</head>
<body>
<div align="center">
            <h1>Appointments List</h1>
    <form action="fixAppointment" method="post">
            <table border="1">
                <th></th>
                <th>Patient Name</th>
                <th>Date</th>
                <th>Time</th>
                <c:forEach var="appointment" items="${appointments}">
                    <tr>
                        <%--<td><input type="checkbox" value="${patient.appointment.id}" name="patients" /></td>--%>
                        <td>${appointment.patient.firstName} ${appointment.patient.lastname}</td>
                        <td>${appointment.patient.appointment.date}</td>
                        <td>${appointment.patient.appointment.time}</td>
                        <td>
                            <input type="hidden" name="doctorId" value="${doctorId}" />
                            <input type="submit" value="Fix Appointment">
                          <a href="deleteAppointment?id=">Delete Appointment</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
    </form>
        </div>
</body>
</html>
