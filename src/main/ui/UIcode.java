package ui;

import model.Player;
import model.Team;

import java.util.Scanner;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

// represents the user interface
public class UIcode {
    private static final String TEAM_JSON = "./data/team.json";
    private Team team;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    //EFFECTS: runs UI code
    public UIcode() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        jsonWriter = new JsonWriter(TEAM_JSON);
        jsonReader = new JsonReader(TEAM_JSON);
        System.out.println("DO YOU WANT TO LOAD PREVIOUS SAVE? Y/N");
        String choice = scan.nextLine();
        if (choice.equals("y") || choice.equals("Y")) {
            loadTeam();
            menuScreen();
        } else if (choice.equals("n") || choice.equals("N")) {
            createTeam();
            menuScreen();
        } else {
            System.out.println("PLEASE ENTER A VALID CHOICE");
            new UIcode();
        }
    }

    //EFFECTS: creates a new team
    private void createTeam() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter team name: ");
        String teamName = scan.nextLine();
        System.out.println("Enter the league your team plays in: ");
        String league = scan.nextLine();
        team = new Team(teamName, league);
        jsonWriter = new JsonWriter(TEAM_JSON);
        jsonReader = new JsonReader(TEAM_JSON);
    }

    //MODIFIES: this
    //EFFECTS: processes the user's input
    private void menuScreen() {
        Scanner scan = new Scanner(System.in);
        choices();
        System.out.println("Enter your choice: ");
        int choice = scan.nextInt();
        if (choice == 1) {
            choice1();
        } else if (choice == 2) {
            choice2();
        } else if (choice == 3) {
            choice3();
        } else if (choice == 4) {
            choice4();
        } else if (choice == 5) {
            choice5();
        } else if (choice == 6) {
            choice6();
        } else {
            choices7to11(choice);
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user's input
    private void choices7to11(int choice) {
        if (choice == 7) {
            choice7();
        } else if (choice == 8) {
            choice8();
        } else if (choice == 9) {
            choice9();
        } else if (choice == 10) {
            choice10();
        } else if (choice == 11) {
            choice11();
        } else {
            System.out.println("Please enter a valid choice");
            menuScreen();
        }
    }

    //EFFECTS: displays the choices a user can make
    private void choices() {
        System.out.println("WHAT DO YOU WANT TO DO?");
        System.out.println("1.  CHECK CLUB HISTORY");
        System.out.println("2.  CHECK BOARD EXPECTATIONS");
        System.out.println("3.  ADD PLAYER");
        System.out.println("4.  REMOVE PLAYER");
        System.out.println("5.  ADD GOALS FOR PLAYER");
        System.out.println("6.  ADD ASSISTS FOR PLAYER");
        System.out.println("7.  CHECK ALL PLAYERS IN THE CLUB");
        System.out.println("8.  SEARCH FOR PLAYER BY NAME");
        System.out.println("9.  SEARCH FOR ALL PLAYERS WHO PLAY A PARTICULAR POSITION");
        System.out.println("10. GET PLAYER STATISTICS");
        System.out.println("11. QUIT");
    }

    //EFFECTS: displays club history;
    private void choice1() {
        System.out.println(team.clubHistory());
        System.out.println();
        menuScreen();
    }

    //EFFECTS: displays board expectations
    private void choice2() {
        System.out.println(team.boardExpectations());
        System.out.println();
        menuScreen();
    }

    //EFFECTS: adds player to team
    private void choice3() {
        Scanner scan = new Scanner(System.in);
        System.out.println("ENTER THE NAME OF THE PLAYER YOU WANT TO ADD: ");
        String name = scan.nextLine();
        System.out.println(("ENTER THE COUNTRY THE PLAYER IS FROM: "));
        String country = scan.nextLine();
        System.out.println("ENTER THE POSITION OF THE PLAYER: ");
        String position = scan.nextLine();
        team.addPlayer(new Player(name, position, country));
        System.out.println();
        menuScreen();
    }

    //EFFECTS: removes player from team
    private void choice4() {
        Scanner scan = new Scanner(System.in);
        System.out.println("ENTER THE NAME OF THE PLAYER YOU WANT TO REMOVE: ");
        String name = scan.nextLine();
        team.removePlayer(name);
        System.out.println();
        menuScreen();
    }

    //EFFECTS: adds goals to player
    private void choice5() {
        Scanner scan = new Scanner(System.in);
        System.out.println("ENTER THE NAME OF THE PLAYER WHO SCORED: ");
        String name = scan.nextLine();
        System.out.println("ENTER NUMBER OF GOALS THE PLAYER SCORED: ");
        int num = scan.nextInt();
        team.addGoals(name, num);
        System.out.println();
        menuScreen();
    }

    //EFFECTS: adds assists to player
    private void choice6() {
        Scanner scan = new Scanner(System.in);
        System.out.println("ENTER THE NAME OF THE PLAYER WHO ASSISTED: ");
        String name = scan.nextLine();
        System.out.println("ENTER NUMBER OF ASSISTS THE PLAYER PROVIDED: ");
        int num = scan.nextInt();
        team.addAssists(name, num);
        System.out.println();
        menuScreen();
    }

    //EFFECTS: displays all players at the club
    private void choice7() {
        System.out.println("HERE IS THE LIST OF ALL PLAYERS AT THE CLUB: ");
        System.out.println(team.getPlayers());
        System.out.println();
        menuScreen();
    }

    //EFFECTS: displays the player searched for
    private void choice8() {
        Scanner scan = new Scanner(System.in);
        System.out.println("ENTER THE NAME OF THE PLAYER YOU WANT TO SEARCH: ");
        String name = scan.nextLine();
        System.out.println(team.searchByName(name));
        System.out.println();
        menuScreen();
    }

    //EFFECTS: displays the players who play the searched position
    private void choice9() {
        Scanner scan = new Scanner(System.in);
        System.out.println("WHICH POSITION DO YOU WANT TO FILTER: ");
        String position = scan.nextLine();
        System.out.println(team.searchByPosition(position));
        System.out.println();
        menuScreen();
    }

    //EFFECTS: displays statistics of the players at the club
    private void choice10() {
        System.out.println("HERE ARE THE STATS OF THE PLAYERS AT THE CLUB: ");
        team.teamStats();
        System.out.println();
        menuScreen();
    }

    //EFFECTS: asks user if they want to save before quitting
    private void choice11() {
        System.out.println("DO YOU WANT TO SAVE THE TEAM? Y/N");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if (choice.equals("y") || choice.equals("Y")) {
            saveTeam();
            quit();
        } else if (choice.equals("n") || choice.equals("N")) {
            quit();
        } else {
            System.out.println("PLEASE ENTER A VALID CHOICE");
            choice11();
        }
    }

    //EFFECTS: saves the team to file
    private void saveTeam() {
        try {
            jsonWriter.open();
            jsonWriter.write(team);
            jsonWriter.close();
            System.out.println("Saved " + team.getTeamName() + " to " + TEAM_JSON);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + TEAM_JSON);
        }
    }

    //MODIFIES:this
    //EFFECTS:loads team from file
    private void loadTeam() {
        try {
            team = jsonReader.read();
            System.out.println("Loaded " + team.getTeamName() + " from " + TEAM_JSON);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + TEAM_JSON);
        }
    }

    //EFFECTS: quits application
    private void quit() {

    }


}
