package com.sportradar.footballScorecardLib.model;

/**
 * A {@link Match} refers to a football match between two football teams and consists of 4 members
 * <ul>
 *     <li>Home Team</li>
 *     <li>Away Team</li>
 *     <li>Home Team Score</li>
 *     <li>Away Team Score</li>
 * </ul>
 *  */
public class Match {

    private String homeTeam;
    private String awayTeam;
    private Integer homeTeamScore;
    private Integer awayTeamScore;

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(Integer homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public Integer getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(Integer awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public Match( String homeTeam, String awayTeam, Integer homeTeamScore, Integer
            awayTeamScore) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Match)) {
            return false;
        }
        if(obj == this) {
            return true;
        }
        Match match = (Match) obj;
        return match.getAwayTeam().equals(this.awayTeam)
                && match.getHomeTeam().equals(this.homeTeam);

    }

    @Override
    public int hashCode() {
        return 37 + homeTeam.hashCode() + awayTeam.hashCode();
    }

    @Override
    public String toString() {
        return this.homeTeam + " " + this.homeTeamScore + " - " + this.awayTeam + " " + this.awayTeamScore;
    }

}
