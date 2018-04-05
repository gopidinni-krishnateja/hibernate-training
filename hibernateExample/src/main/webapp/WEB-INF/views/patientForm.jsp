
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title> Patient Form </title>
</head>
<body>
<div align="center"><h2>Patient Form</h2>
</div>
<div align="center">
    <form:form action="savePatient" method="post" modelAttribute="patient">
        <table>
            <tr>
                <td>First Name:</td>
                <td><input type="text" name="firstName" path="firstName"/></td>
            </tr>
            <tr>
                <td>Last Name:</td>

                <td><input type="text" name="lastname" path="lastname"/></td>
            </tr>
            <tr>
                <td>Age:</td>
                <td><input type="number" name="age" path="age"/></td>
            </tr>
            <tr>
                <td>Gender</td>
                <td><input type="radio" name="gender" path="gender" value="male" checked> Male
                    <input type="radio" name="gender" path="gender" value="female"> Female
                </td>
            </tr>
            <tr>
                <td>Date</td>
                <td>
                <form:input type="date" name="date" path="appointment.date" data-date="" data-date-format="YYYY-MM-DD" />
                </td>
            </tr>
            <tr>
                <td>Time</td>
                <td><form:input type="time" name="time" path="appointment.time" /></td>
            </tr>
            <tr>
                <td>Select Doctor</td>
                <td>
                    <form:select items="${doctors}"  itemLabel="firstName" itemValue="id" path="appointment.doctor.id"></form:select>
                </td>
            </tr>
            <td colspan="2" align="center"><input type="submit" value="Save" /></td>

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
