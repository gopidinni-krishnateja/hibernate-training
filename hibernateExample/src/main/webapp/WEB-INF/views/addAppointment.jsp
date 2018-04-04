<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title> Appointment </title>
</head>
<body>
<div align="center"><h2>Add Appointment</h2>
</div>
<div align="center">
    <form:form action="saveAppointment?id=${doctor.id}" method="post">
        <table>
            <tr>
                <td>Doctor Name:</td>
                <td><label>${doctor.firstName} ${doctor.lastName}</label></td>
            </tr>
            <tr>
                <td>Date</td>
                <td><input type="date" name="date" path="date"/></td>
            </tr>
            <tr>
                <td>Time</td>
                <td><input type="time" name="time" path="time" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save Appointment" /></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
