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
            <h1>My Patients List</h1>
            <table border="1">
                <th><%--<a href="deleteDoctor?id="></a>--%></th>
                <th>Doctor Name</th>
                <th>Patient Name</th>
                <th>Date</th>
                <th>Time</th>
                <c:forEach var="appointment" items="${appointments}">
                    <tr>
                     <td><input type="checkbox" value="${appointment.doctor.id}" name="appointments" /></td>
                        <td>${appointment.doctor.firstName} ${appointment.doctor.lastName}</td>
                        <td>${appointment.patient.firstName} ${appointment.patient.lastname}</td>
                        <td>${appointment.date}</td>
                        <td>${appointment.time} </td>
                       <td>
                         <input type="hidden" name="doctorId" value="${doctorId}" />
                            <a href="/index">Home</a>
                          <a href="editPatient?id=${appointment.patient.id}">Edit Patient</a>
                         </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
</body>
</html>
