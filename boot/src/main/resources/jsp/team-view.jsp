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
                                <th colspan="2">
                                    <c:choose>
                                        <c:when test="${mode == 'create'}">
                                            <h1>Team registration is successful!</h1>
                                        </c:when>
                                        <c:when test="${mode == 'edit'}">
                                            <h1>Team saved successfully!</h1>
                                        </c:when>
                                        <c:when test="${mode == 'get'}">
                                            <h1>Team Details</h1>
                                        </c:when>
                                        <c:otherwise>
                                            <h1>Team Details</h1>
                                        </c:otherwise>
                                    </c:choose>
                                </th>
                            </tr>
                            <tr>
                                <td>
                                    <img src="data:image/jpg;base64,${team.teamImage.base64File}" style="border-radius: 50%;" width="100" height="100" />
                                    <br />
                                    <c:choose>
                                        <c:when test="${empty team.teamImage}">
                                            <a href="/npl-auction/resource-management/image/upload/team?entityId=${team.id}">Upload Picture</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="/npl-auction/resource-management/image/upload/team?entityId=${team.id}">Change Picture</a>
                                            <br />
                                            <a href="/npl-auction/resource-management/image/delete/team?entityId=${team.id}">Remove Picture</a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><span style="font-weight:bold;">Team Name:</span>${team.teamName}</td>
                            </tr>
                            <tr>
                                <td>
                                    <span style="font-weight:bold;">Owners:</span>
                                    <c:forEach items="${team.owners}" var="owner">
                                        <br /><span>${owner.fullName}</span>
                                    </c:forEach>
                                </td>
                                <td>
                                    <span style="font-weight:bold;">Players:</span>
                                    <c:forEach items="${team.players}" var="player">
                                        <br /><span>${player.fullName}</span>
                                    </c:forEach>
                                </td>
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