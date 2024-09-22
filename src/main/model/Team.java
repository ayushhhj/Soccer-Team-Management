package model;

import org.json.JSONObject;
import org.json.JSONArray;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents the user's team.
public class Team implements Writable {
    private String teamName;               // Stores the team's name
    private String league;                 // Stores the league the team plays in
    private List<Player> players;          // Stores the players in the team


    /*  EFFECTS: constructs a team with teamName assigned to teamName,
     *           league assigned to league, and a new list to store players.
     */
    public Team(String teamName, String league) {
        this.teamName = teamName;
        this.league = league;
        this.players = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("New team was created"));
    }

    //REQUIRES: no duplicate players, every player has unique name.
    //MODIFIES: this
    //EFFECTS: adds the player to the club
    public void addPlayer(Player player) {
        if (!(players.contains(player))) {
            players.add(player);
        }
        EventLog.getInstance().logEvent(new Event(player.getName() + " was added"));
    }

    //REQUIRES: player must be in club.
    //MODIFIES: this
    //EFFECTS: removes the player from the club
    public void removePlayer(String name) {
        boolean b = false;
        Player remove = null;
        for (Player player : players) {
            if (player.getName().equals(name)) {
                b = true;
                remove = player;
            }
        }
        if (b) {
            players.remove(remove);
        }
        EventLog.getInstance().logEvent(new Event(remove.getName() + " was removed"));

    }

    //REQUIRES: num > 0
    //MODIFIES: goals scored by player
    //EFFECTS: adds num to goals scored by player
    public void addGoals(String name, int num) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                player.scoreGoal(num);
            }
        }
    }

    //REQUIRES: num > 0
    //MODIFIES: assists provided by player
    //EFFECTS: adds num to assists provided by player
    public void addAssists(String name, int num) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                player.giveAssist(num);
            }
        }
    }

    //EFFECTS: Displays the player and statistics with name if at the club.
    public String searchByName(String name) {
        EventLog.getInstance().logEvent(new Event("Player searched for by name"));
        String stats = "Player not in the club";
        for (Player player : players) {
            if (player.getName().equals(name)) {
                stats = " " + player.getName() + "      goals = " + player.getGoals()
                            + "      assists = " + player.getAssists();
            }
        }
        return stats;
    }

    //REQUIRES: Position must be one of "FWD", "MID", "DEF", "GK"
    //EFFECTS: returns the list of all players in the position
    public List<String> searchByPosition(String position) {
        EventLog.getInstance().logEvent(new Event("All players in position " + position + " displayed"));
        List<String> playerinpos = new ArrayList<>();
        for (Player player : players) {
            if (player.getPosition().equals(position)) {
                playerinpos.add(player.getName());
            }
        }
        if (playerinpos.isEmpty()) {
            System.out.println("NO PLAYERS IN THIS POSITION");
        }
        return playerinpos;
    }


    //EFFECTS: displays the statistics for each player
    public String teamStats() {
        EventLog.getInstance().logEvent(new Event("Team statistics displayed"));
        for (Player player : players) {
            System.out.println(player.getName() + "      goals = " + player.getGoals()
                    + "      assists = " + player.getAssists());
        }
        return "   ";
    }

    //EFFECTS: displays the board's expectations for the season
    public String boardExpectations() {
        EventLog.getInstance().logEvent(new Event("Board Expectations displayed"));
        return "The board's expectations for this season are:"
                +
                "\n 1. Domestic Expectations: Achieve Top 4."
                +
                "\n 2. Continental Expectations: Reach the Quarter-Finals."
                +
                "\n 3. Financial Expectations: Make a Profit of $2 Million.";
    }

    //EFFECTS: displays a brief history of the club
    public String clubHistory() {
        EventLog.getInstance().logEvent(new Event("Club History displayed"));
        return "The club was founded in 1923, with the expectations of becoming a footballing giant."
                +
                "\n The club has won 12 major titles over its length."
                +
                "\n The home colours are blue, red, white, and yellow."
                +
                "\n The crest consists of a dark gray shield with a double yellow outline along with a wolf-"
                +
                "\n-silhouette and the club's establishment year.";
    }

    //EFFECTS: returns the club name
    public String getTeamName() {
        return teamName;
    }

    //EFFECTS: returns the league the club plays in
    public String getLeague() {
        return league;
    }

    //EFFECTS: returns the list of the players at the club
    public List<Player> getPlayers() {
        return players;
    }

    //EFFECTS: returns this as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", teamName);
        json.put("league", league);
        json.put("players", playersToJson());
        return json;
    }

    //EFFECTS: returns players in this team as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Player player : players) {
            jsonArray.put(player.toJson());
        }
        return jsonArray;
    }
}
