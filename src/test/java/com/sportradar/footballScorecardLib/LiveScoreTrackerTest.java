package com.sportradar.footballScorecardLib;

import com.sportradar.footballScorecardLib.model.Match;
import com.sportradar.footballScorecardLib.service.ILiveScoreTracker;
import com.sportradar.footballScorecardLib.service.LiveScoreTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class LiveScoreTrackerTest {

	private ILiveScoreTracker scoreTracker;
	private LinkedHashSet scoredCard;
	private static final Match MATCH_1 = new Match("Mexico","Canada",5,4);
	private static final Match MATCH_2 = new Match("Spain","Argentina",0,1);

	@BeforeEach
	void setup() {
		scoreTracker = new LiveScoreTracker();
		scoredCard = Stream.of(
				new Match("Mexico","Canada",5,4),
				new Match("Spain","Argentina",0,1),
				new Match("USA","Belgium",10,8),
				new Match("Morocco","India",2,3)
				).collect(Collectors.toCollection(LinkedHashSet::new));
	}

	@Test
	void shouldStartMatch() {
		// TODO: Implement
	}

}
