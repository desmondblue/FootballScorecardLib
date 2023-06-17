package com.sportradar.footballScorecardLib.service;

import com.sportradar.footballScorecardLib.model.Match;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LiveScoreTracker implements ILiveScoreTracker{

    private LinkedHashSet<Match> scoreboard = new LinkedHashSet();

    public void setScoreboard(LinkedHashSet<Match> scoreboard) {
        this.scoreboard = scoreboard;
    }

    @Override
    public String startMatch(String homeTeam, String awayTeam) {
        Match match = new Match(homeTeam, awayTeam, 0, 0);
        if(validateStartMatch(match)) {
            scoreboard.add(match);
            return "Match started "+homeTeam+ " vs " +awayTeam;
        } else {
            return "Failed to start match: Either or Both Team(s) are already playing";
        }
    }

    @Override
    public String finishMatch(Match match) {
//        TODO: TBD
        return "";
    }

    @Override
    public String updateMatchScore(Match match, Integer homeTeamScore, Integer awayTeamScore) {
//        TODO: TBD
        return "";
    }

    @Override
    public ArrayList<Match> getSummary() {
        return null;
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
