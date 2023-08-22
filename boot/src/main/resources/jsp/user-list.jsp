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
                    <form id="userForm" action="/npl-auction/user-management/user" method="GET">
                        <table width="100%" border="0" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>E-Mail</th>
                                    <th>Phone Number</th>
                                    <th>Birthdate</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${users}" var="user">
                                    <tr>
                                        <td>${user.id}</td>
                                        <td>${user.fullName}</td>
                                        <td>${user.email}</td>
                                        <td>${user.phoneNumber}</td>
                                        <td>${user.birthdate}</td>
                                        <td>
                                            <button type="submit" class="gray_button" name="view" form="userForm" formaction="/npl-auction/user-management/user/${user.id}" formmethod="GET">View</button>
                                            <button type="submit" class="blue_button" name="edit" form="userForm" formaction="/npl-auction/user-management/user/${user.id}" formmethod="GET">Edit</button>
                                            <button type="submit" class="red_button" form="userForm" formaction="/npl-auction/user-management/user/${user.id}" formmethod="DELETE" onclick="confirm('Are you sure about delete the Cricket Profile? Update Cricket Profile option is also available, please click Cancel and check it!')">Delete</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </form>
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