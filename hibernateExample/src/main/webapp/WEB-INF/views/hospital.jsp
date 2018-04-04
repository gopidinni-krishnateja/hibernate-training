<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title> Hospital </title>
</head>
<body>
<div align="center"><h2>Hospital Form</h2>
</div>
<div align="center">
    <form:form action="saveHospital" method="post">
        <table>
            <tr>
                <td>Hospital Name:</td>
                <td><input type="text" name="name" path="name"/></td>
            </tr>
            <tr>
                <td>City</td>
                <td><input type="text" name="cityName" path="cityName" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Register" /></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
