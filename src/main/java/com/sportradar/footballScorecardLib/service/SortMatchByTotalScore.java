package com.sportradar.footballScorecardLib.service;

import com.sportradar.footballScorecardLib.model.Match;

import java.util.Comparator;

public class SortMatchByTotalScore implements Comparator<Match> {

    @Override
    public int compare(Match m1, Match m2) {
        return Integer.compare(m2.getHomeTeamScore()+m2.getAwayTeamScore(),m1.getAwayTeamScore()+m1.getHomeTeamScore());
    }
}
