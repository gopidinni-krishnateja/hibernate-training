<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <title> Hospital </title>
</head>
<body>
<div align="center"><h2>Hospital Form</h2>
</div>
<div align="center">
    <form action="saveHospital" method="post" >
        <table>
            <tr>
                <div class="form-group">
                <td>Hospital Name:</td>
                <td><input type="text" class="form-control" name="name" path="name"/></td>
                </div>
            </tr>
            <tr>
                <div class="form-group">
                <td>City</td>
                <td><input type="text" class="form-control" name="cityName" path="cityName" />
                </td>
                </div>
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
                        <td><input type="checkbox" class="myCheckBox" value="${doctor.id}" id="checkme" name="doctors" />
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
            <input type="submit" id="isChecked" class="btn btn-primary" value="Assign" />
        </h5>
    </form>


</div>
<script>
    var checkBoxes = $('td .myCheckBox');
    checkBoxes.change(function () {
        $('#isChecked').prop('disabled', checkBoxes.filter(':checked').length < 1);
    });
    $('tbody .myCheckBox').change();
</script>
</body>
</html>
