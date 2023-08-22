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
                    <table width="100%" border="1" cellspacing="0">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Owners</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${teams}" var="team">
                                <tr>
                                    <td><a href="/npl-auction/team-management/team/${team.id}">${team.id}</a></td>
                                    <td>${team.teamName}</td>
                                    <td>
                                        <c:forEach items="${team.owners}" var="owner">
                                            <span>${owner.fullName}</span>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <a href="/npl-auction/team-management/team/${team.id}?mode=1">Edit</a>
                                        <a href="/npl-auction/team-management/team/${team.id}?mode=2">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
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