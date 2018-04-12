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
    <title>Hospitals</title>
</head>
<body>
<div align="center">
            <h1>Hospitals List</h1>
            <table border="1">
     
                <th>Hospital Name</th>
                <th>City Name</th>
                
                <c:forEach var="hospital" items="${hospitals}">
                    <tr>
     
                        <td>${hospital.name}</td>
                        <td>${hospital.cityName}</td>
                  
                        <td>
                        <a href="viewDoctors?id=${hospital.id}">View Doctors</a>
                        
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
</body>
</html>
