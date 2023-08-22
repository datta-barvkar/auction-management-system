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
                    <table border="0" width="100%">
                        <tbody>
                            <tr>
                                <th colspan="3">
                                    <h1>${pageTitle}</h1>
                                </th>
                            </tr>
                            <tr>
                                <td rowspan="4">
                                    <img src="data:image/jpg;base64,${user.userImage.base64File}" style="border-radius: 50%;" width="175" height="235" />
                                    <br />
                                    <form id="imageForm" action="/npl-auction/resource-management/image/upload" method="GET">
                                        <input type="hidden" value="${user.id}" name="entityId" id="entityId" />
                                        <input type="hidden" value="user" name="type" id="type" />
                                        <c:choose>
                                            <c:when test="${empty user.userImage}">
                                                <button type-"submit" class="green_button" form="imageForm" formaction="/npl-auction/resource-management/image" formmethod="GET" value="Upload Picture" />
                                            </c:when>
                                            <c:otherwise>
                                                <button type-"submit" class="blue_button" form="imageForm" formaction="/npl-auction/resource-management/image/${user.userImage.id}" formmethod="GET" value="Change Picture" />
                                                <button type-"submit" class="red_button" form="imageForm" formaction="/npl-auction/resource-management/image/${user.userImage.id}" formmethod="DELETE" onclick="confirm('Are you sure about removing the user image? Change Picture option is also available, please click Cancel and check it!')" value="Remove Picture" />
                                            </c:otherwise>
                                        </c:choose>
                                    </form>
                                </td>
                                <td><span style="font-weight: bold;">First name: </span>${user.firstName}</td>
                                <td><span style="font-weight: bold;">Middle Name: </span>${user.middleName}</td>
                                <td><span style="font-weight: bold;">Last Name: </span>${user.lastName}</td>
                            </tr>
                            <tr>
                                <td><span style="font-weight: bold;">Full name: </span>${user.fullName}</td>
                                <td><span style="font-weight: bold;">E-Mail: </span>${user.email}</td>
                                <td><span style="font-weight: bold;">Phone Number: </span>${user.phoneNumber}</td>
                            </tr>
                            <tr>
                                <td><span style="font-weight: bold;">Birthdate: </span>${user.birthdate}</td>
                                <td><span style="font-weight: bold;">Gender: </span>${user.gender}</td>
                                <td>
                                    <span style="font-weight: bold;">Roles: </span>
                                    <c:forEach items="${user.roles}" var="role">
                                        <span>${role.name}</span>
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td><span style="font-weight: bold;">Marital Status: </span>${user.maritalStatus}</td>
                                <td>
                                    <span style="font-weight: bold;">Cricket Profile: </span>
                                    <form id="cricketProfileForm" action="/npl-auction/player-management/cricket-profile" method="GET">
                                        <input type="hidden" value="${user.id}" name="playerId" id="playerId" />
                                        <c:choose>
                                            <c:when test="${empty user.cricketProfile}">
                                                <button type-"submit" name="create" class="green_button" form="cricketProfileForm" formaction="/npl-auction/player-management/cricket-profile" formmethod="GET" value="Add Cricket Profile" />
                                            </c:when>
                                            <c:otherwise>
                                                <button type-"submit" name="view" class="blue_button" form="cricketProfileForm" formaction="/npl-auction/player-management/cricket-profile/${user.cricketProfile.id}" formmethod="GET" value="View Cricket Profile" />
                                            </c:otherwise>
                                        </c:choose>
                                    </form>
                                </c:if>
                            </tr>
                        </tbody>
                    </table>
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