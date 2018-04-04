
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title> Patient Form </title>
</head>
<body>
<div align="center"><h2>Patient Form</h2>
</div>
<div align="center">
    <form:form  method="get">
        <table>
            <tr>
                <td>First Name:</td>
                <td><input type="text" name="firstName" path="firstName"/></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><input type="text" name="lastName" path="lastName"/></td>
            </tr>
            <tr>
                <td>Age:</td>
                <td><input type="number" name="age" path="age"/></td>
            </tr>
            <tr>
                <td>Gender</td>
                <td><input type="radio" name="gender" path="gender" value="male" checked> Male
                    <input type="radio" name="gender" path="gender" value="female"> Female
                </td>
            </tr>
            <tr>

            </tr>

        </table>
        <div>
                    <h3>Appointments List</h3>
                    <table border="1">
                        <th></th>
                        <th>Doctor</th>
                        <th>Specialized In</th>
                        <th>Date</th>
                        <th>Time</th>
                        <c:forEach var="appointment" items="${appointments}">
                            <tr>
                            <td><input type="radio" class="radio" name="appointmentId" value="${appointment.id}" /></td>
                                <td>${appointment.doctor.firstName} ${appointment.doctor.lastName}</td>
                                <td>${appointment.doctor.type}</td>
                                <td>${appointment.date}</td>
                                <td>${appointment.time}</td>

                            <td colspan="2" align="center"><input type="submit" formaction="savePatient?id=${appointment.id}" value="Save" /></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
    </form:form>
</div>
</body>
</html>
