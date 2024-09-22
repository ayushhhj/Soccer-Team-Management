package persistence;

import model.Team;
import model.Player;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderInvalidFile() {
        JsonReader reader = new JsonReader("./data/invalidFile.json");
        try {
            Team team = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testReaderEmptyTeam() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTeam.json");
        try {
            Team team = reader.read();
            assertEquals("Man Utd", team.getTeamName());
            assertEquals("Premier League", team.getLeague());
            assertEquals(0, team.getPlayers().size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderTeam() {
        JsonReader reader = new JsonReader("./data/testReaderTeam.json");
        try {
            Team team = reader.read();
            assertEquals("Man Utd", team.getTeamName());
            assertEquals("Premier League", team.getLeague());
            List<Player> players = team.getPlayers();
            assertEquals(2, players.size());
            checkPlayer("Ronaldo", "FWD", "Portugal", 0, 0, players.get(0));
            checkPlayer("Varane", "DEF", "France", 0, 0, players.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}