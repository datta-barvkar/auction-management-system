<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<tr>
    <th rowspan="2" width="150" height="150">
        <c:if test="${not empty requestScope.appLogo}">
            <img src="data:image/jpg;base64,${requestScope.appLogo.base64File}" style="border-radius: 50%;" width="150" height="150" />
        </c:if>
    </th>
    <th><h1><c:out value="${requestScope.appName}" /></h1></th>
    <th align="right">
        <c:choose>
            <c:when test="${empty sessionScope.loggedInUser}">
                Hello Guest! Please <a href='/npl-auction/login'>login</a>
            </c:when>
            <c:otherwise>
                Hello <a href="/npl-auction/user-management/user/${sessionScope.loggedInUser.id}">${sessionScope.loggedInUser.fullName}</a>! <a href='/npl-auction/logout'>logout</a>
            </c:otherwise>
        </c:choose>
    </th>
</tr>