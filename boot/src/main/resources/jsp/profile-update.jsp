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
                    <form:form id="cricketProfileForm" action="/npl-auction/player-management/cricket-profile" method="POST" modelAttribute="cricketProfile">
                        <form:hidden path="playerId" />
                        <table border="0" width="100%">
                            <tbody>
                                <tr>
                                    <th colspan="2">
                                        <h2>${pageTitle}</h2>
                                        <form:hidden path="id" />
                                    </th>
                                </tr>
                                <tr>
                                    <td rowspan="6" style="width:50%">
                                        <img src="data:image/jpg;base64,${userImage.base64File}" style="border-radius: 50%;" width="100" height="100" />
                                    </td>
                                    <td><span style="font-weight: bold;">Full name: </span><span>${cricketProfile.playerName}</span></td>
                                </tr>
                                <tr>
                                    <td><span style="font-weight: bold;">Playing Role: </span><form:select path="playingRole" items="${playingRoles}" /></td>
                                </tr>
                                <tr>
                                    <td><span style="font-weight: bold;">Batting Style: </span><form:select path="battingStyle" items="${battingStyles}" /></td>
                                </tr>
                                <tr>
                                    <td><span style="font-weight: bold;">Bowling Style: </span><form:select path="bowlingStyle" items="${bowlingStyles}" /></td>
                                </tr>
                                <tr>
                                    <td><span style="font-weight: bold;">More Details: </span><form:textarea path="moreDetails" /></td>
                                </tr>
                                <tr>
                                    <td>
                                        <span style="font-weight: bold;">Teams: </span>
                                        <c:forEach items="${cricketProfile.teams}" var="team">
                                            <span>${team.teamName} </span>
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="2" align="left">
                                        <h3 style="margin: 0;">Batting Statistics:</h3>
                                        <c:if test="${not empty cricketProfile.battingStats.id}">
                                            <form:hidden path="battingStats.id" />
                                        </c:if>
                                    </th>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <table cellspacing="0" border="1">
                                            <tr>
                                                <th>Matches</th>
                                                <th>Innings</th>
                                                <th>Not Outs</th>
                                                <th>Total Runs</th>
                                                <th>Highest Runs</th>
                                                <th>Average</th>
                                                <th>Strike Rate</th>
                                                <th>Thirties</th>
                                                <th>Fifties</th>
                                                <th>Hundreds</th>
                                                <th>Fours</th>
                                                <th>Sixes</th>
                                                <th>Ducks</th>
                                            </tr>
                                            <tr>
                                                <td align="center"><form:input path="battingStats.matches" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="battingStats.innings" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="battingStats.notOuts" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="battingStats.totalRuns" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="battingStats.highestRuns" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="battingStats.average" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="battingStats.strikeRate" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="battingStats.thirties" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="battingStats.fifties" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="battingStats.hundreds" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="battingStats.fours" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="battingStats.sixes" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="battingStats.ducks" cssStyle="width: 30px;" /></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="2" align="left">
                                        <h3 style="margin: 0;">Bowling Statistics:</h3>
                                        <c:if test="${not empty cricketProfile.bowlingStats.id}">
                                            <form:hidden path="bowlingStats.id" />
                                        </c:if>
                                    </th>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <table cellspacing="0" border="1">
                                            <tr>
                                                <th>Matches</th>
                                                <th>Innings</th>
                                                <th>Overs</th>
                                                <th>Maidens</th>
                                                <th>Wickets</th>
                                                <th>Runs</th>
                                                <th>Best Bowling</th>
                                                <th>Three Wickets</th>
                                                <th>Five Wickets</th>
                                                <th>Economy</th>
                                                <th>Strike Rate</th>
                                                <th>Average</th>
                                                <th>Wides</th>
                                                <th>No Balls</th>
                                                <th>Dot Balls</th>
                                                <th>Fours</th>
                                                <th>Sixes</th>
                                            </tr>
                                            <tr>
                                                <td align="center"><form:input path="bowlingStats.matches" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="bowlingStats.innings" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="bowlingStats.overs" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="bowlingStats.maidens" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="bowlingStats.wickets" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="bowlingStats.runs" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="bowlingStats.bestBowling" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="bowlingStats.threeWickets" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="bowlingStats.fiveWickets" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="bowlingStats.economy" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="bowlingStats.strikeRate" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="bowlingStats.average" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="bowlingStats.wides" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="bowlingStats.noBalls" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="bowlingStats.dotBalls" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="bowlingStats.fours" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="bowlingStats.sixes" cssStyle="width: 30px;" /></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="2" align="left">
                                        <h3 style="margin: 0;">Fielding Statistics:</h3>
                                        <c:if test="${not empty user.cricketProfile.fieldingStats.id}">
                                            <form:hidden path="fieldingStats.id" />
                                        </c:if>
                                    </th>
                                </tr>
                                <tr>
                                    <td colspan="6">
                                        <table cellspacing="0" border="1">
                                            <tr>
                                                <th>Matches</th>
                                                <th>Catches</th>
                                                <th>Caught Behind</th>
                                                <th>Run Outs</th>
                                                <th>Stumpings</th>
                                                <th>Assisted Run Outs</th>
                                                <th>Bye Runs</th>
                                            </tr>
                                            <tr>
                                                <td align="center"><form:input path="fieldingStats.matches" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="fieldingStats.catches" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="fieldingStats.caughtBehind" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="fieldingStats.runOuts" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="fieldingStats.stumpings" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="fieldingStats.assistedRunOuts" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="fieldingStats.byeRuns" cssStyle="width: 30px;" /></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="6" align="left">
                                        <h3 style="margin: 0;">Captaining Statistics:</h3>
                                        <c:if test="${not empty user.cricketProfile.captainingStats.id}">
                                            <form:hidden path="captainingStats.id" />
                                        </c:if>
                                    </th>
                                </tr>
                                <tr>
                                    <td colspan="6">
                                        <table cellspacing="0" border="1">
                                            <tr>
                                                <th>Matches</th>
                                                <th>Toss Wins</th>
                                                <th>Wins</th>
                                                <th>Losses</th>
                                                <th>No Results</th>
                                                <th>Ties</th>
                                            </tr>
                                            <tr>
                                                <td align="center"><form:input path="captainingStats.matches" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="captainingStats.tossWins" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="captainingStats.wins" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="captainingStats.losses" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="captainingStats.noResults" cssStyle="width: 30px;" /></td>
                                                <td align="center"><form:input path="captainingStats.ties" cssStyle="width: 30px;" /></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="6" align="center">
                                        <c:choose>
                                            <c:when test="${empty cricketProfile.id}">
                                                <button type="submit" class="green_button" value="Save" form="cricketProfileForm" formaction="/npl-auction/player-management/cricket-profile" formmethod="POST" />
                                                <button type="submit" class="gray_button" value="Reset"  form="cricketProfileForm" formaction="/npl-auction/player-management/cricket-profile" formmethod="GET" onclick="confirm('Are you sure?')" />
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" class="blue_button" value="Update" form="cricketProfileForm" formaction="/npl-auction/player-management/cricket-profile/${cricketProfile.id}" formmethod="PUT" />
                                                <button type="submit" name="edit" class="gray_button" value="Reset"  form="cricketProfileForm" formaction="/npl-auction/player-management/cricket-profile/${cricketProfile.id}" formmethod="GET" onclick="confirm('Are you sure?')" />
                                            </c:otherwise>
                                        </c:choose>
                                        <button type="submit" class="red_button" value="Cancel" form="cricketProfileForm" formaction="/npl-auction/user-management/user" formmethod="GET" onclick="confirm('Are you sure?')" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form:form>
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