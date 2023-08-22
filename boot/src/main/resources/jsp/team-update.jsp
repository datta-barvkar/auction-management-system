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
                    <form:form action="/npl-auction/team-management/team/save" method="POST" modelAttribute="team">
                        <table border="0">
                            <tbody>
                                <tr>
                                    <th>
                                        <c:choose>
                                            <c:when test="${empty team.id}">
                                                <h1>Team Registration</h1>
                                            </c:when>
                                            <c:otherwise>
                                                <h1>Update Team</h1>
                                            </c:otherwise>
                                        </c:choose>
                                        <form:hidden path="id" />
                                    </th>
                                </tr>
                                <tr>
                                    <td>
                                        <h4 style="margin:auto;">Team Name:</h4>
                                        <form:input path="teamName" />
                                        <form:errors path="teamName" cssClass="error" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <h4 style="margin:auto;">Team Owners:</h4>
                                        <form:select path="owners" items="${owners}" itemValue="id" itemLabel="fullName" multiple="true" />
                                        <form:errors path="owners" cssClass="error" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <h4 style="margin:auto;">Players:</h4>
                                        <form:select path="players" items="${players}" itemValue="id" itemLabel="fullName" multiple="true" />
                                        <form:errors path="players" cssClass="error" />
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center"><input type="submit" value="Save" /></td>
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