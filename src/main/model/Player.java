package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a football (soccer) player
public class Player implements Writable {
    private String name;                    // stores the player's name
    private String position;                // stores the player's position
    private String country;                 // stores the player's country
    private int goals;                      // stores the goals scored by the player
    private int assists;                    // stores the assists provided by the player

    // creates a football player
    //REQUIRES: position must be one of "FWD", "MID", "DEF", and "GK"
    //EFFECTS: creates a football player with name, position and country. Goals and assists set to 0.
    public Player(String name, String position, String country) {
        this.name = name;
        this.position = position;
        this.country = country;
        goals = 0;
        assists = 0;

    }

    //EFFECTS: empty constructor
    public Player() {

    }

    //EFFECTS: creates a football player with all fields assigned, mainly for persistence.
    public Player(String name, String position, String country, int goals, int assists) {
        this.name = name;
        this.position = position;
        this.country = country;
        this.goals = goals;
        this.assists = assists;
    }

    // scores goal(s) with footballer
    //REQUIRES: num > 0
    //MODIFIES: this
    //EFFECTS: adds num to goals scored by player
    public void scoreGoal(int num) {
        goals += num;
        EventLog.getInstance().logEvent(new Event(getName() + " has scored " + num + " goals"));
    }

    //provides assist(s) with footballer
    //REQUIRES: num > 0
    //MODIFIES: this
    //EFFECTS: adds num to assists provided by player
    public void giveAssist(int num) {
        assists += num;
        EventLog.getInstance().logEvent(new Event(getName() + " has provided " + num + " assists"));
    }

    // returns player name
    public String getName() {
        return name;
    }

    // returns player's position
    public String getPosition() {
        return position;
    }

    // returns player's home country
    public String getCountry() {
        return country;
    }

    // returns player's goals
    public int getGoals() {
        return goals;
    }

    // returns player's assists
    public int getAssists() {
        return assists;
    }

    //EFFECTS: returns this as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("position", position);
        json.put("country", country);
        json.put("goals", goals);
        json.put("assists", assists);
        return json;
    }
}
