<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

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
    <form action="saveHospitalDoctors" method="post" >
        <input type="hidden" value="${hospitalId}" name="hospitalId">
            <table border="1">
                <th>${hospitalName}</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Spelization</th>
                <c:forEach var="doctor" items="${doctors}">
                    <tr>
                <td><input type="checkbox" value="${doctor.id}" name="doctors" />
                        <td>${doctor.firstName}</td>
                        <td>${doctor.lastName}</td>
                        <td>${doctor.type}</td>
                   
                        <td><a href="/">Home</a></td>
                    </tr>

                </c:forEach>
            </table>
        <h5 align="center">
            <input type="submit" value="Assign" />
        </h5>
    </form>
        </div>

</body>
</html>
