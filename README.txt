== Football Scorecard Library ==

A java application program to mimic Live Football World Cup Scoreboard library that shows all the ongoing matches and their scores.
The scoreboard supports the following operations:
1. Start a new match, assuming initial score 0 – 0 and adding it the scoreboard.
This should capture following parameters:
a. Home team
b. Away team
2. Update score. This should receive a pair of absolute scores: home team score and away
team score.
3. Finish match currently in progress. This removes a match from the scoreboard.
4. Get a summary of matches in progress ordered by their total score. The matches with the
same total score will be returned ordered by the most recently started match in the
scoreboard.

==Documentation==
Classes
1. Model class: Match
2. Service class: IliveScoreTracker Interface, LiveScoreTracker class, SortMatchByTotalScore class

Match class object will refer to each football match currently in play at the world cup having a home team, a away team and respective scores.
LiveScoreTracker class is responsible for all the functions related to the World Cup Scoreboard (i.e. start match, finish match, update score, get score)

Q: Why use LinkedHashSet to store scoreboard?
A: The operation of addition, removal and contains on LinkedHashSet takes O(1) time due to the internal HashMap implementation
further LinkedHashSet keeps the order of insertion.
The collection was useful to store match data as we could add a match, remove a match & check if a match already exists in O(1) time and since we wanted to sort the summary by total score and if score is equal by the order of insertion.

Q: Why use Comparator and not Comparable?
A: Since we wanted sorting by total score we could have used either of the interfaces but keeping in mind that in future we might need different sorting for ex. by team name. Then we can create another Comparator class to sort by team name ensuring SOLID principle and modularity.

Edge cases considered for testing:
1. Starting a match with null value or empty string.
2. Starting a match with either or both same team names.
3. Finishing a match if match does not exist.
4. Updating the score of match if match does not exist.
5. Updating the score with null values or negative values or values less than current value
