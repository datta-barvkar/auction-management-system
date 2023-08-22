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
                    <form id="approvalForm" action="/npl-auction/approval-management/approval" method="GET">
                        <table width="100%" border="0" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Player Name</th>
                                    <th>Team Name</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${teamApprovals}" var="teamApproval">
                                    <tr>
                                        <td>${teamApproval.id}</td>
                                        <td>${teamApproval.playerName}</td>
                                        <td>${teamApproval.teamName}</td>
                                        <td>
                                            <a href="/npl-auction//approval/${teamApproval.id}">Approve</a>
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