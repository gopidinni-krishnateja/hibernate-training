<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
<div align="center">
            <h1>Fixed Appointments List</h1>

            <table border="1">
                <th>Hospital Name</th>
                <th>Doctor Name</th>
                <th>Patient Name</th>
                <th>Appointment Date</th>
                <th>Appointment Time</th>
                <th>Actions</th>
                <c:forEach var="totalData" items="${totalData}">
                    <tr>

                       <td>${totalData.HospitalName}</td>
                        <td>${totalData.DoctorFirstName} ${totalData.DoctorLastName}</td>
                        <td>${totalData.PatientFirstName} ${totalData.PatientLastName} </td>
                        <td>${totalData.date}</td>
                        <td>${totalData.time}</td>
                        <td><a href="/">Home</a> </td>
                        <td><a href="editPatient?id=${totalData.PatientId}">Edit Patient</a></td>
                        <td><a href="deletePatient?id=${totalData.PatientId}">Delete Patient</a></td>
                    </tr>
                </c:forEach>
            </table>

        </div>
</body>
</html>
