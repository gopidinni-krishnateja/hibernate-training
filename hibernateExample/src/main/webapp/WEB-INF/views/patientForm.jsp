
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <title> Patient Form </title>
</head>
<body>
<div align="center"><h2>Patient Form</h2>
</div>
<div align="center">
    <form:form action="savePatient" method="post" modelAttribute="patient">
        <table>
            <form:hidden path="id"/>
            <tr>
                <div class="form-group">
                <td>First Name:</td>
                <td><form:input class="form-control" type="text" name="firstName" path="firstName"/></td>
                </div>
            </tr>
            <tr>
                <div class="form-group">
                <td>Last Name:</td>
                <td><form:input class="form-control" type="text" name="lastname" path="lastname"/></td>
                </div>
            </tr>
            <tr>
                <div class="form-group">
                <td>Age:</td>
                <td><form:input class="form-control" type="number" name="age" path="age"/></td>
                </div>
            </tr>
            <tr>
                <div class="form-group">
                <td>Gender</td>
                <td><form:input class="form-control" type="text" name="gender" path="gender"/></td>
                </div>
                </td>
            </tr>
            <tr>
                <div class="form-group">
                <td>Date</td>
                <td>
                <form:input type="date" class="form-control" name="date" path="appointment.date" data-date="" data-date-format="YYYY-MM-DD" />
                </td>
                </div>
            </tr>
            <tr>
                <div class="form-group">
                <td>Time</td>
                <td><form:input type="time" class="form-control" name="time" path="appointment.time" /></td>
                </div>
            </tr>
            <tr>
                <div class="form-group">
                <td>Select Doctor Type</td>
                <td>
                    <form:select disabled="${editMode}" items="${doctors}"  class="form-control"  itemLabel="type" itemValue="id" path="appointment.doctor.id"></form:select>
                </td>
                </div>
            </tr>
            <td colspan="2" align="center"><input class="btn btn-primary" type="submit" value="Save" /></td>

        </table>
    </form:form>
</div>
<script>
    $(document).ready(function(){
        $("input").on("change", function() {
            console.log("on change attribute")
            this.setAttribute(
                "data-date",
                moment(this.value, "MM/DD/YYYY")
                    .format( this.getAttribute("data-date-format") )
            )
        }).trigger("change")
    })
</script>

<style>
    input.date {
        position: relative;
        width: 150px; height: 20px;
        color: white;
    }

    input.date:before {
        position: absolute;
        top: 3px; left: 3px;
        content: attr(data-date);
        display: inline-block;
        color: black;
    }

    input.date::-webkit-datetime-edit, input.date::-webkit-inner-spin-button, input.date::-webkit-clear-button {
        display: none;
    }

    input.date::-webkit-calendar-picker-indicator {
        position: absolute;
        top: 3px;
        right: 0;
        color: black;
        opacity: 1;
    }
</style>
</body>
</html>
