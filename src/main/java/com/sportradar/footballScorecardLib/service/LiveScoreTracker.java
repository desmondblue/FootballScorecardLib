package com.sportradar.footballScorecardLib.service;

import com.sportradar.footballScorecardLib.model.Match;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LiveScoreTracker implements ILiveScoreTracker {

    private LinkedHashSet<Match> scoreboard = new LinkedHashSet<>();

    public void setScoreboard(LinkedHashSet<Match> scoreboard) {
        this.scoreboard = scoreboard;
    }

    @Override
    public String startMatch(String homeTeam, String awayTeam) {
        if (homeTeam == null || homeTeam.equals("") || awayTeam == null || awayTeam.equals("")) {
            return "Incorrect value passed for team names.";
        }
        Match match = new Match(homeTeam, awayTeam, 0, 0);
        if (validateStartMatch(match)) {
            scoreboard.add(match);
            return "Match started " + homeTeam + " vs " + awayTeam;
        } else {
            return "Failed to start match: Either or Both Team(s) are already playing";
        }
    }

    @Override
    public String finishMatch(Match match) {
        if (this.scoreboard.remove(match)) {
            return "Successfully finished match.";
        } else {
            return "Failed to finish match: Match does not exist.";
        }
    }

    @Override
    public String updateMatchScore(Match match, Integer homeTeamScore, Integer awayTeamScore) {
        if (this.scoreboard.contains(match)) {
            Match currentMatch = this.scoreboard.stream().filter(match::equals).findFirst().get();
            if (homeTeamScore != null && awayTeamScore != null
                    && currentMatch.getHomeTeamScore() <= homeTeamScore
                    && currentMatch.getAwayTeamScore() <= awayTeamScore
            ) {
                match.setAwayTeamScore(awayTeamScore);
                match.setHomeTeamScore(homeTeamScore);
                this.scoreboard.add(match);
                return "Successfully updated match score. " + match;
            } else {
                return "Failed to update match score: Scores cannot be less than existing values or null.";
            }
        } else {
            return "Failed to update match score: Match does not exist.";
        }
    }

    @Override
    public ArrayList<Match> getSummary() {
        ArrayList<Match> matches = new ArrayList<>(this.scoreboard);
        SortMatchByTotalScore sortByTotalScore = new SortMatchByTotalScore();
        Collections.sort(matches, sortByTotalScore);
        return matches;
    }

    private boolean validateStartMatch(Match startMatch) {
        if (this.scoreboard.contains(startMatch)) {
            return false;
        }
        HashSet<String> allTeamsPlaying = this.scoreboard.stream()
                .flatMap(match -> Stream.of(match.getAwayTeam(), match.getHomeTeam()))
                .collect(Collectors.toCollection(HashSet::new));
        return !allTeamsPlaying.contains(startMatch.getAwayTeam())
                && !allTeamsPlaying.contains(startMatch.getHomeTeam());
    }
}
