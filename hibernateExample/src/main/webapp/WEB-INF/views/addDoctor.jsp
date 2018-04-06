<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title> Doctor </title>
</head>
<body>
<div align="center"><h2>Doctor Form</h2>
</div>
<div align="center">
    <form:form action="saveDoctor" method="post" modelAttribute="doctor">
        <table>
            <form:hidden path="hospital.id"/>
            <form:hidden path="id"/>
            <tr>
                <td>First Name:</td>
                <td><form:input type="text" name="firstName" path="firstName"/></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><form:input type="text" name="lastName" path="lastName"/></td>
            </tr>
            <tr>
                <td>Specialize In</td>
                <td><form:input type="text" name="type" path="type" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save" /></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
