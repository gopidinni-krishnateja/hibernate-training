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
    <title>Doctors</title>
</head>
<body>

<div align="center">
            <h1>Doctors List</h1>
    <div align="center">
        <form action="searchDoctor">
            <input class="form-control" type="text" placeholder="Search" aria-label="Search" name="firstName">
            <button class="btn aqua-gradient btn-rounded btn-sm my-0" type="submit">Search</button>
            <div class="row"> <a href="viewDoctors?id=${hospitalId}">Clear Search</a></div>
            <input type="hidden" value="${hospitalId}" name="hospitalId">
        </form>
    </div>
            <table border="1">
     
                <th>First Name</th>
                <th>Last Name</th>
                <th>Specialize In</th>
                <c:forEach var="doctor" items="${doctors}">
                    <tr>

                        <td>${doctor.firstName}</td>
                        <td>${doctor.lastName}</td>
                        <td>${doctor.type}</td>
                  

                        <td>
                            <a href="editDoctor?id=${doctor.id}">Edit Doctor</a>
                            <a href="deleteDoctor?id=${doctor.id}">Delete Doctor</a>
                            <a href="viewMyPatients?id=${doctor.id}">View My Appointments</a>
                            <a href="/index">Home</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
</body>
</html>
