package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player;

    @BeforeEach
    public void setup() {
        player =  new Player("Cristiano Ronaldo", "FWD", "Portugal");
    }

    @Test
    public void testConstructor() {
        assertEquals("Cristiano Ronaldo", player.getName());
        assertEquals("FWD", player.getPosition());
        assertEquals("Portugal", player.getCountry());
        assertEquals(0, player.getGoals());
        assertEquals(0, player.getAssists());
    }

    @Test
    public void testEmptyConstructor() {
        Player player = new Player();
        assertNull(player.getName());
        assertNull(player.getPosition());
        assertNull(player.getCountry());
        assertEquals(0, player.getGoals());
        assertEquals(0, player.getAssists());
    }

    @Test
    public void testScoreGoal() {
        player.scoreGoal(4);
        assertEquals(4, player.getGoals());
    }

    @Test
    public void testScoreGoalMultipleTimes() {
        player.scoreGoal(3);
        assertEquals(3, player.getGoals());
        player.scoreGoal(5);
        assertEquals(8, player.getGoals());
    }

    @Test
    public void testGiveAssist() {
        player.giveAssist(2);
        assertEquals(2, player.getAssists());
    }

    @Test
    public void testGiveAssistMultipleTimes() {
        player.giveAssist(1);
        assertEquals(1, player.getAssists());
        player.giveAssist(3);
        assertEquals(4, player.getAssists());
    }

}