<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title> Hospital </title>
</head>
<body>
<div align="center"><h2>Hospital Form</h2>
</div>
<div align="center">
    <form action="saveHospital" method="post" >
        <table>
            <tr>
                <td>Hospital Name:</td>
                <td><input type="text" name="name" path="name"/></td>
            </tr>
            <tr>
                <td>City</td>
                <td><input type="text" name="cityName" path="cityName" />

                </td>
            </tr>

        </table>
        <div align="center">
                    <h1>Doctors List</h1>

                    <table border="1">
                        <th>Select Doctors</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                    <th>Specialize In</th>
                        <c:forEach var="doctor" items="${doctors}">
                            <tr>
                        <td><input type="checkbox" value="${doctor.id}" name="doctors" />
                        </td>
                                <td>${doctor.firstName}</td>
                                <td>${doctor.lastName}</td>
                                <td>${doctor.type}</td>
                            
                         
                            <%--<td><a href="viewMyPatients?id=${doctor.id}">View Doctor Appointments</a>
                                <a href="editDoctor?id=${doctor.id}">Edit Doctor</a>
                            </td>--%>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
        <h5 align="center">
            <input type="submit" value="Assign" />
        </h5>
    </form>


</div>
</body>
</html>
