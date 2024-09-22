package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {

    Team team;
    Player player1;
    Player player2;

    @BeforeEach
    public void setup() {
        team = new Team("AFC Richmond", "Premier League");
        player1 = new Player("Messi", "FWD", "Argentina");
        player2 = new Player("Ronaldo", "FWD", "Portugal");

    }

    @Test
    public void testConstructor() {
        assertEquals("AFC Richmond", team.getTeamName());
        assertEquals("Premier League", team.getLeague());
        assertEquals(0, team.getPlayers().size());
    }

    @Test
    public void testAddPlayerOnce() {
        team.addPlayer(player1);
        assertEquals(1, team.getPlayers().size());
    }

    @Test
    public void testAddTwoDistinctPlayers() {
        team.addPlayer(player1);
        assertEquals(1, team.getPlayers().size());
        team.addPlayer(player2);
        assertEquals(2, team.getPlayers().size());
    }

    @Test
    public void testAddSamePlayerTwice() {
        team.addPlayer(player1);
        assertEquals(1, team.getPlayers().size());
        team.addPlayer(player1);
        assertEquals(1, team.getPlayers().size());
    }

    @Test
    public void testRemovePlayerOnce() {
        team.addPlayer(player1);
        team.removePlayer("Messi");
        assertEquals(0, team.getPlayers().size());
    }

    @Test
    public void testRemoveTwoDistinctPlayers() {
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.removePlayer("Messi");
        assertEquals(1, team.getPlayers().size());
        team.removePlayer("Ronaldo");
        assertEquals(0, team.getPlayers().size());
    }

    @Test
    public void testRemoveSamePlayerTwice() {
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.removePlayer("Messi");
        assertEquals(1, team.getPlayers().size());
        team.removePlayer("Messi");
        assertEquals(1, team.getPlayers().size());
    }

    @Test
    public void testaddGoal() {
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addGoals("Ronaldo", 3);
        assertEquals(3, player2.getGoals());
    }

    @Test
    public void testaddAssist() {
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addAssists("Ronaldo", 3);
        assertEquals(3, player2.getAssists());
    }

    @Test
    public void testaddGoalsMultipleTimes() {
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addGoals("Ronaldo", 3);
        assertEquals(3, player2.getGoals());
        team.addPlayer(player2);
        team.addGoals("Ronaldo", 3);
        assertEquals(6, player2.getGoals());
        team.addGoals("Ronaldo", 0);
        assertEquals(6, player2.getGoals());
    }

    @Test
    public void testaddAssistMultipleTimes() {
        team.addPlayer(player2);
        team.addAssists("Ronaldo", 3);
        assertEquals(3, player2.getAssists());
        team.addPlayer(player2);
        team.addAssists("Ronaldo", 3);
        assertEquals(6, player2.getAssists());
        team.addAssists("Ronaldo", 0);
        assertEquals(6, player2.getAssists());
    }

    @Test
    public void testsearchByName() {
        team.addPlayer(player2);
        team.addPlayer(player1);
        assertEquals(" Ronaldo      goals = 0      assists = 0", team.searchByName("Ronaldo"));

    }

    @Test
    public void testsearchByNamefalse() {
        team.addPlayer(player2);
        assertEquals("Player not in the club", team.searchByName("Messi"));
    }

    @Test
    public void testsearchByPosition() {
        team.addPlayer(player1);
        team.addPlayer(player2);
        assertEquals(2, team.searchByPosition("FWD").size());
        assertTrue(team.searchByPosition("FWD").contains("Messi"));
        assertTrue(team.searchByPosition("FWD").contains("Ronaldo"));
    }

    @Test
    public void testsearchByPositionfalse() {
        team.addPlayer(player1);
        team.addPlayer(player2);
        assertEquals(0, team.searchByPosition("DEF").size());
    }

    @Test
    public void testClubHistory() {
        assertEquals("The club was founded in 1923, with the expectations of becoming a footballing giant."
                +
                "\n The club has won 12 major titles over its length."
                +
                "\n The home colours are blue, red, white, and yellow."
                +
                "\n The crest consists of a dark gray shield with a double yellow outline along with a wolf-"
                +
                "\n-silhouette and the club's establishment year.", team.clubHistory());
    }

    @Test
    public void testBoardExpectations() {
        assertEquals("The board's expectations for this season are:"
                +
                "\n 1. Domestic Expectations: Achieve Top 4."
                +
                "\n 2. Continental Expectations: Reach the Quarter-Finals."
                +
                "\n 3. Financial Expectations: Make a Profit of $2 Million.", team.boardExpectations());
    }

    @Test
    public void testTeamStatsOnePlayer() {
        team.addPlayer(player1);
        assertEquals("   ", team.teamStats());
    }
}
