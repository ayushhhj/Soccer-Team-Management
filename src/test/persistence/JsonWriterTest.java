package persistence;

import model.Team;
import model.Player;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Team team = new Team("Man Utd", "Premier League");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTeam() {
        try {
            Team team = new Team("Man Utd", "Premier League");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTeam.json");
            writer.open();
            writer.write(team);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTeam.json");
            team = reader.read();
            assertEquals("Man Utd", team.getTeamName());
            assertEquals("Premier League", team.getLeague());
            assertEquals(0, team.getPlayers().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterTeam() {
        try {
            Team team = new Team("Man Utd", "Premier League");
            team.addPlayer(new Player("Ronaldo", "FWD", "Portugal", 0, 0));
            team.addPlayer(new Player("Varane", "DEF", "France", 0, 0));
            JsonWriter writer = new JsonWriter("./data/testWriterTeam.json");
            writer.open();
            writer.write(team);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterTeam.json");
            team = reader.read();
            assertEquals("Man Utd", team.getTeamName());
            assertEquals("Premier League", team.getLeague());
            List<Player> players = team.getPlayers();
            assertEquals(2, players.size());
            checkPlayer("Ronaldo", "FWD", "Portugal", 0, 0, players.get(0));
            checkPlayer("Varane", "DEF", "France", 0, 0, players.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
