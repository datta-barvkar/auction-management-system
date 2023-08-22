<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<tr>
    <th colspan="2">
        <form id="menuForm" action="/npl-auction/home" method="GET">
            <ul class="menu">
                <c:choose>
                    <c:when test="${not empty loggedInUser}">
                        <c:forEach items="${loggedInUser.menus}" var="menu">
                            <c:choose>
                                <c:when test="${menu.selected}">
                                    <li style="float: ${menu.direction};"><button name="${menu.name}" type="submit" form="menuForm" formaction="/npl-auction${menu.action}" formmethod="${menu.method}" class="active-menu">${menu.value}</button></li>
                                </c:when>
                                <c:otherwise>
                                    <li style="float: ${menu.direction};"><button name="${menu.name}" type="submit" form="menuForm" formaction="/npl-auction${menu.action}" formmethod="${menu.method}">${menu.value}</button></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${defaultMenus}" var="menu">
                            <c:choose>
                                <c:when test="${menu.selected}">
                                    <li style="float: ${menu.direction};"><button name="${menu.name}" type="submit" form="menuForm" formaction="/npl-auction${menu.action}" formmethod="${menu.method}" class="active-menu">${menu.value}</button></li>
                                </c:when>
                                <c:otherwise>
                                    <li style="float: ${menu.direction};"><button name="${menu.name}" type="submit" form="menuForm" formaction="/npl-auction${menu.action}" formmethod="${menu.method}">${menu.value}</button></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </ul>
        </form>
    </th>
</tr>