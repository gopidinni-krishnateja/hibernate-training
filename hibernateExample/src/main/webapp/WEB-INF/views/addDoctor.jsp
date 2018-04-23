<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <title> Doctor </title>
</head>
<style>
    .error {
        color: #ff0000;
    }

    .errorblock {
        color: #000;
        background-color: #ffEEEE;
        border: 1px solid #ff0000;
    }
</style>
<body>
<div align="center"><h2>Doctor Form</h2>
</div>
<div align="center">
    <form:form action="saveDoctor" method="post" modelAttribute="doctor">
        <table>
            <form:hidden path="hospital.id"/>
            <form:hidden path="id"/>
            <tr>
                <div class="form-group">
                <td>First Name:</td>
                <td><form:input class="form-control" type="text" name="firstName" path="firstName"/></td><br>
                    <td></td>
                </div>
            </tr>
                <td>
            <div class="row">
                <td><form:errors path="firstName" cssClass="errorblock"></form:errors></td>
            </div>
            </td>
            <tr>
                <div class="form-group">
                <td>Last Name:</td>
                <td><form:input type="text" class="form-control" name="lastName" path="lastName"/></td>
                </div>
            </tr>
                <td>
            <div class="row">
                <td><form:errors path="lastName" cssClass="errorblock"></form:errors></td>
            </div>
            </td>
            <tr>
                <div class="form-group">
                <td>Specialize In</td>
                <td><form:input type="text" class="form-control" name="type" path="type" /></td>
                </div>
            </tr>
            <td>
            <div class="row">
                <td><form:errors path="type" cssClass="errorblock"></form:errors></td>
            </div>
            </td>
            <tr>
                <td colspan="2" align="center"><input class="btn btn-primary" type="submit" value="Save" /></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
