<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            <h1>Fixed Appointments List</h1>
            <table border="1">
                <th>Doctor Name</th>
                <th>Date</th>
                <th>Time</th>
                <th>Patient Name</th>
                <c:forEach var="patient" items="${patients}">
                    <tr>
                        <td>${patient.appointment.doctor.firstName} ${patient.appointment.doctor.lastName}</td>
                        <td>${patient.appointment.date}</td>
                        <td>${patient.appointment.time}</td>
                        <td>${patient.firstName} ${patient.lastName}</td>
                        <%--<td>
                        <a href="viewAppointments?id=${doctor.id}">View Appointments</a>
                          <a href="deleteAppointment?id=${doctor.id}">Delete Appointment</a>
                        </td>--%>
                    </tr>
                </c:forEach>
            </table>
        </div>
</body>
</html>
