package com.sportradar.footballScorecardLib.service;

import com.sportradar.footballScorecardLib.model.Match;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class LiveScoreTracker implements ILiveScoreTracker{

    private LinkedHashSet<Match> scoreboard;

    @Override
    public String startMatch(String homeTeam, String awayTeam) {
//        TODO: TBD
        return "";
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
}
