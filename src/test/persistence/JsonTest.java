package persistence;

import model.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkPlayer(String name, String position, String country, int goals, int assists, Player player) {
        assertEquals(name, player.getName());
        assertEquals(country, player.getCountry());
        assertEquals(position, player.getPosition());
        assertEquals(goals, player.getGoals());
        assertEquals(assists, player.getAssists());
    }
}