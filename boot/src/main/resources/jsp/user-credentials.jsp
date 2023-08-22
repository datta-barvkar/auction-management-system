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
                        <form action="/npl-auction/login" method="POST">
                            <table width="100%">
                                <tr>
                                    <th colspan="2">Change Password!</th>
                                </tr>
                                <tr>
                                    <th>Username:</th>
                                    <td>
                                        <input type="text" name="username" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>Old Password:</th>
                                    <td>
                                        <input type="password" name="oldPassword" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>New Password:</th>
                                    <td>
                                        <input type="password" name="newPassword" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>Repeat New Password:</th>
                                    <td>
                                        <input type="password" name="repeatPassword" />
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="2">
                                        <input type="submit" value="Login" />
                                    </th>
                                </tr>
                            </table>
                        </form>
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