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
                    <form id="cricketProfileForm" action="/npl-auction/user-management/user/${cricketProfile.playerId}" method="GET">
                        <table width="100%">
                            <tbody>
                                <tr>
                                    <td colspan="2">
                                        <button type="submit" class="gray_button" value="< User Profile" />
                                        <button type-"submit" name="edit" class="blue_button" form="cricketProfileForm" formaction="/npl-auction/player-management/cricket-profile/${cricketProfile.id}" formmethod="GET" value="Edit Cricket Profile" />
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="2">
                                        <h2>Cricket Profile</h2>
                                    </th>
                                </tr>
                                <tr>
                                    <td rowspan="6" style="width:50%">
                                        <img src="data:image/jpg;base64,${cricketProfile.userImage.base64File}" style="border-radius: 50%;" width="100" height="100" />
                                    </td>
                                    <td><span style="font-weight: bold;">Full name: </span><span>${cricketProfile.playerName}</span></td>
                                </tr>
                                <tr>
                                    <td><span style="font-weight: bold;">Playing Role: </span><span>${cricketProfile.playingRole}</span></td>
                                </tr>
                                <tr>
                                    <td><span style="font-weight: bold;">Batting Style: </span><span>${cricketProfile.battingStyle}</span></td>
                                </tr>
                                <tr>
                                    <td><span style="font-weight: bold;">Bowling Style: </span><span>${cricketProfile.bowlingStyle}</span></td>
                                </tr>
                                <tr>
                                    <td><span style="font-weight: bold;">More Details: </span><span>${cricketProfile.moreDetails}</span></td>
                                </tr>
                                <tr>
                                    <td>
                                        <span style="font-weight: bold;">Teams: </span>
                                        <c:forEach items="${cricketProfile.teams}" var="team">
                                            <span>${team.teamName}</span>
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <th align="left" colspan="2">
                                        <h3 style="margin: 0;">Batting Statistics</h3>
                                    </th>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <table border="1" cellspacing="0">
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
                                                <td align="center">${cricketProfile.battingStats.matches}</td>
                                                <td align="center">${cricketProfile.battingStats.innings}</td>
                                                <td align="center">${cricketProfile.battingStats.notOuts}</td>
                                                <td align="center">${cricketProfile.battingStats.totalRuns}</td>
                                                <td align="center">${cricketProfile.battingStats.highestRuns}</td>
                                                <td align="center">${cricketProfile.battingStats.average}</td>
                                                <td align="center">${cricketProfile.battingStats.strikeRate}</td>
                                                <td align="center">${cricketProfile.battingStats.thirties}</td>
                                                <td align="center">${cricketProfile.battingStats.fifties}</td>
                                                <td align="center">${cricketProfile.battingStats.hundreds}</td>
                                                <td align="center">${cricketProfile.battingStats.fours}</td>
                                                <td align="center">${cricketProfile.battingStats.sixes}</td>
                                                <td align="center">${cricketProfile.battingStats.ducks}</td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <th align="left" colspan="2">
                                        <h3 style="margin: 0;">Bowling Statistics</h3>
                                    </th>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <table border="1" cellspacing="0">
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
                                                <td align="center">${cricketProfile.bowlingStats.matches}</td>
                                                <td align="center">${cricketProfile.bowlingStats.innings}</td>
                                                <td align="center">${cricketProfile.bowlingStats.overs}</td>
                                                <td align="center">${cricketProfile.bowlingStats.maidens}</td>
                                                <td align="center">${cricketProfile.bowlingStats.wickets}</td>
                                                <td align="center">${cricketProfile.bowlingStats.runs}</td>
                                                <td align="center">${cricketProfile.bowlingStats.bestBowling}</td>
                                                <td align="center">${cricketProfile.bowlingStats.threeWickets}</td>
                                                <td align="center">${cricketProfile.bowlingStats.fiveWickets}</td>
                                                <td align="center">${cricketProfile.bowlingStats.economy}</td>
                                                <td align="center">${cricketProfile.bowlingStats.strikeRate}</td>
                                                <td align="center">${cricketProfile.bowlingStats.average}</td>
                                                <td align="center">${cricketProfile.bowlingStats.wides}</td>
                                                <td align="center">${cricketProfile.bowlingStats.noBalls}</td>
                                                <td align="center">${cricketProfile.bowlingStats.dotBalls}</td>
                                                <td align="center">${cricketProfile.bowlingStats.fours}</td>
                                                <td align="center">${cricketProfile.bowlingStats.sixes}</td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <th align="left" colspan="2">
                                        <h3 style="margin: 0;">Fielding Statistics</h3>
                                    </th>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <table border="1" cellspacing="0">
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
                                                <td align="center">${cricketProfile.fieldingStats.matches}</td>
                                                <td align="center">${cricketProfile.fieldingStats.catches}</td>
                                                <td align="center">${cricketProfile.fieldingStats.caughtBehind}</td>
                                                <td align="center">${cricketProfile.fieldingStats.runOuts}</td>
                                                <td align="center">${cricketProfile.fieldingStats.stumpings}</td>
                                                <td align="center">${cricketProfile.fieldingStats.assistedRunOuts}</td>
                                                <td align="center">${cricketProfile.fieldingStats.byeRuns}</td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <th align="left" colspan="2">
                                        <h3 style="margin: 0;">Captaining Statistics</h3>
                                    </th>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <table border="1" cellspacing="0">
                                            <tr>
                                                <th>Matches</th>
                                                <th>Toss Wins</th>
                                                <th>Wins</th>
                                                <th>Losses</th>
                                                <th>No Results</th>
                                                <th>Ties</th>
                                            </tr>
                                            <tr>
                                                <td align="center">${cricketProfile.captainingStats.matches}</td>
                                                <td align="center">${cricketProfile.captainingStats.tossWins}</td>
                                                <td align="center">${cricketProfile.captainingStats.wins}</td>
                                                <td align="center">${cricketProfile.captainingStats.losses}</td>
                                                <td align="center">${cricketProfile.captainingStats.noResults}</td>
                                                <td align="center">${cricketProfile.captainingStats.ties}</td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <button type="submit" class="gray_button" value="< User Profile" />
                                        <button type-"submit" name="edit" class="blue_button" form="cricketProfileForm" formaction="/npl-auction/player-management/cricket-profile/${cricketProfile.id}" formmethod="GET" value="Edit Cricket Profile" />
                                    </td>
                                </tr>
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