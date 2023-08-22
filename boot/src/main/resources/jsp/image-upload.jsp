<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                    <form id="imageForm" action="/npl-auction/resource-management/image" method="POST" enctype="multipart/form-data">
                        <input type="hidden" id="entityId" name="entityId" value="${entityId}" />
                        <input type="hidden" id="type" name="type" value="${type}" />
                        <table>
                            <tr>
                                <th colspan="3">
                                    <h1>${pageTitle}</h1>
                                </th>
                            </tr>
                            <tr>
                                <td><span style="font-weight: bold;">Image Name:</span><input type="text" id="name" name="imageName" /></td>
                                <td><span style="font-weight: bold;">Image:</span><input id="imageFile" type="file" name="imageFile" accept="image/png, image/jpeg" /></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty imageId}">
                                            <button type-"submit" class="green_button" form="imageForm" formaction="/npl-auction/resource-management/image" formmethod="POST" value="Upload Picture" />
                                        </c:when>
                                        <c:otherwise>
                                            <button type-"submit" class="blue_button" form="imageForm" formaction="/npl-auction/resource-management/image/${imageId}" formmethod="PUT" value="Update Picture" />
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
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