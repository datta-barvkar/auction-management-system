<!DOCTYPE html>
<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <div>
                        Received an error while executing ${requestURL}
                        <span>Response Code :</span> ${responseCode}<br />
                        <span>Response Text :</span> ${responseText}<br />
                        <span>Error Message :</span> ${errorMessage}<br />
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