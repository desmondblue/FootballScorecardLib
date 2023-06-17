package com.sportradar.footballScorecardLib.service;

import com.sportradar.footballScorecardLib.model.Match;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * A {@link ILiveScoreTracker} tracks live score for world cup football matches.
 * This class has 4 functionalities
 * <ol>
 *     <li>Start a match</li>
 *     <li>Finish a match</li>
 *     <li>Update a match score</li>
 *     <li>Get summary of all the matches</li>
 * </ol>
 */

public interface ILiveScoreTracker {
    String startMatch(String homeTeam, String awayTeam);
    String finishMatch(Match match);
    String updateMatchScore(Match match, Integer homeTeamScore, Integer awayTeamScore);
    ArrayList<Match> getSummary();
    void setScoreboard(LinkedHashSet<Match> scoreBoard);
}
