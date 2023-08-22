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
                    <div style="align:center;">
                        <form:form action="/npl-auction/login" method="POST" modelAttribute="credentials">
                            <table width="100%">
                                <tr>
                                    <th colspan="2">Please enter username and password!</th>
                                </tr>
                                <tr>
                                    <th>Username:</th>
                                    <td>
                                        <form:input path="username" /><form:errors path="username" cssClass="error" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>Password:</th>
                                    <td>
                                        <form:password path="password" /><form:errors path="password" cssClass="error" />
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="2">
                                        <input type="submit" value="Login" />
                                    </th>
                                </tr>
                            </table>
                        </form:form>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <%@ include file="footer.jsp" %>
                </td>
            </tr>
        </tbody>
    </table>
</div>
</body>
</html>