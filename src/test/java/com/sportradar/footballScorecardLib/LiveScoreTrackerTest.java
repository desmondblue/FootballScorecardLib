package com.sportradar.footballScorecardLib;

import com.sportradar.footballScorecardLib.model.Match;
import com.sportradar.footballScorecardLib.service.ILiveScoreTracker;
import com.sportradar.footballScorecardLib.service.LiveScoreTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LiveScoreTrackerTest {

    private ILiveScoreTracker scoreTracker;
    private LinkedHashSet<Match> scoreBoard;
    private static final Match MATCH_1 = new Match("Mexico", "Canada", 5, 4);
    private static final Match MATCH_2 = new Match("Spain", "Moldova", 0, 1);

    @BeforeEach
    void setup() {
        scoreTracker = new LiveScoreTracker();
        scoreBoard = Stream.of(
                new Match("Mexico", "Canada", 5, 4),
                new Match("Spain", "Argentina", 0, 1),
                new Match("USA", "Belgium", 10, 8),
                new Match("Morocco", "India", 2, 3),
                new Match("Albania", "Armenia", 2, 3)
        ).collect(Collectors.toCollection(LinkedHashSet::new));
        scoreTracker.setScoreboard(scoreBoard);
    }

    @Test
    void shouldStartMatch() {
        String result = scoreTracker.startMatch("Italy", "Australia");
        assertEquals(result, "Match started Italy vs Australia");
        assertEquals(this.scoreBoard.size(), 6);
    }

    @Test
    void shouldFailToStartMatchIfEitherOfTheTeamIsAlreadyPlaying() {
        String result = scoreTracker.startMatch("Mexico", "Nepal");
        assertEquals(result, "Failed to start match: Either or Both Team(s) are already playing");
    }

    @Test
    void shouldFailToStartMatchIfEmptyString() {
        String result = scoreTracker.startMatch("Mexico", "");
        assertEquals(result, "Incorrect value passed for team names.");
    }

    @Test
    void shouldFailToStartMatchIfNull() {
        String result = scoreTracker.startMatch("Mexico", null);
        assertEquals(result, "Incorrect value passed for team names.");
    }

    @Test
    void shouldFinishMatch() {
        String result = scoreTracker.finishMatch(MATCH_1);
        assertEquals(result, "Successfully finished match.");
        assertEquals(this.scoreBoard.size(), 4);
    }

    @Test
    void shouldFailToFinishMatchIfNotExists() {
        String result = scoreTracker.finishMatch(MATCH_2);
        assertEquals(result, "Failed to finish match: Match does not exist.");
    }

    @Test
    void shouldUpdateMatchScore() {
        String result = scoreTracker.updateMatchScore(MATCH_1, 12, 14);
        assertEquals(result, "Successfully updated match score. Mexico 12 - Canada 14");
        assertEquals(this.scoreBoard.size(), 5);
    }

    @Test
    void shouldFailToUpdateMatchScore() {
        String result = scoreTracker.updateMatchScore(MATCH_2, 3, 5);
        assertEquals(result, "Failed to update match score: Match does not exist.");
    }

    @Test
    void shouldFailToUpdateMatchScoreWithNullValue() {
        String result = scoreTracker.updateMatchScore(MATCH_1, null, 5);
        assertEquals(result, "Failed to update match score: Scores cannot be less than existing values or null.");
    }

    @Test
    void shouldFailToUpdateMatchScoreWithNegativeValues() {
        String result = scoreTracker.updateMatchScore(MATCH_1, -2, 5);
        assertEquals(result, "Failed to update match score: Scores cannot be less than existing values or null.");
    }

    @Test
    void shouldFailToUpdateMatchScoreWithValuesLessThanCurrent() {
        String result = scoreTracker.updateMatchScore(MATCH_1, 2, 3);
        assertEquals(result, "Failed to update match score: Scores cannot be less than existing values or null.");
    }

    @Test
    void shouldGetSummary() {
        ArrayList<Match> result = scoreTracker.getSummary();
        assertEquals(result,
                Arrays.asList(
                        new Match("USA", "Belgium", 10, 8),
                        new Match("Mexico", "Canada", 5, 4),
                        new Match("Morocco", "India", 2, 3),
                        new Match("Albania", "Armenia", 2, 3),
                        new Match("Spain", "Argentina", 0, 1)
                ));
    }

    @Test
    void shouldReturnEmptyListAsSummaryWhenNoMatchesStarted() {
        this.scoreTracker.setScoreboard(new LinkedHashSet<>());
        ArrayList<Match> result = scoreTracker.getSummary();
        assertTrue(result.isEmpty());
    }

}
