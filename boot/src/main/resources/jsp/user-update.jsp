<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<%@ include file="page-header.jsp" %>
<body>
<div style="width: 100%">
    <table width="100%">
        <tbody>
            <%@ include file="app-header.jsp" %>
            <%@ include file="menu.jsp" %>
            <tr>
                <td>
                    <%@ include file="sub-menu.jsp" %>
                </td>
                <td colspan="2">
                    <form:form action="/npl-auction/user-management/user" method="POST" modelAttribute="user">
                        <table border="0" width="100%">
                            <tbody>
                                <tr>
                                    <th colspan="6">
                                        <h2>${pageTitle}</h2>
                                        <form:hidden path="id" />
                                    </th>
                                </tr>
                                <tr>
                                    <td><span style="font-weight: bold;">First name:</span></td><td><form:input path="firstName" required /><form:errors path="firstName" cssClass="error" /></td>
                                    <td><span style="font-weight: bold;">Middle name:</span></td><td><form:input path="middleName" /></td>
                                    <td><span style="font-weight: bold;">Last name:</span></td><td><form:input path="lastName" required /><form:errors path="firstName" cssClass="error" /></td>
                                </tr>
                                <c:if test="${empty user.id}">
                                    <tr>
                                        <td><span style="font-weight: bold;">Username:</span></td><td><form:input path="credentials.username" required /><form:errors path="credentials.username" cssClass="error" /></td>
                                        <td><span style="font-weight: bold;">Password:</span></td><td><form:password path="credentials.password" required /><form:errors path="credentials.password" cssClass="error" /></td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <td><span style="font-weight: bold;">E-Mail:</span></td><td><form:input path="email" required /><form:errors path="firstName" cssClass="error" /></td>
                                    <td><span style="font-weight: bold;">Phone Number:</span></td><td><form:input path="phoneNumber" required /><form:errors path="firstName" cssClass="error" /></td>
                                    <td><span style="font-weight: bold;">Birthdate:</span></td><td><form:input type="date" path="birthdate" required /><form:errors path="firstName" cssClass="error" /></td>
                                </tr>
                                <tr>
                                    <td><span style="font-weight: bold;">Gender:</span></td><td><form:radiobuttons path="gender" items="${genders}" /></td>
                                    <td><span style="font-weight: bold;">Role:</span></td><td><form:select path="roles" items="${roles}" itemValue="id" itemLabel="name" multiple="true" /><form:errors path="roles" cssClass="error" /></td>
                                    <td><span style="font-weight: bold;">Marital Status:</span></td><td><form:radiobuttons path="maritalStatus" items="${maritalStatuses}" /></td>
                                </tr>
                                <tr>
                                    <td colspan="6" align="center">
                                        <button class="green_button" type="submit" action="save" value="Save" />
                                        <button class="gray_button" type="reset" value="Reset" />
                                        <button class="red_button" type="submit" action="cancel" value="Cancel" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form:form>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <%@ include file="footer.jsp" %>
                </td>
            </tr>
        </tbody>
    </table>
</div>
</body>
</html>