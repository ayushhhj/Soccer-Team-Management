package ui;

import model.*;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Class for main menu of the application.
public class GUIteam extends JFrame implements ActionListener {

    private static final String TEAM_JSON = "./data/team.json";
    private Team team;
    private Player player;
    private JFrame frame = new JFrame("TEAM MANAGEMENT APPLICATION");
    private JTextArea textArea = new JTextArea();
    private JLabel imageLabel = new JLabel();
    private JLabel header = new JLabel();
    private JPanel titlePanel = new JPanel();
    private JPanel buttonPanel = new JPanel(new GridLayout(3,3));
    private JButton addPlayer = new JButton("Add Player");
    private JButton removePlayer = new JButton("Remove Player");
    private JButton displayPlayers = new JButton("Display Players");
    private JButton teamHistory = new JButton("Team History");
    private JButton boardExpectations = new JButton("Board Expectations");
    private JButton addGoals = new JButton("Add Goals");
    private JButton addAssists = new JButton("Add Assists");
    private JButton searchName = new JButton("Search by Name");
    private JButton quit = new JButton("Quit");
    private JLabel error = new JLabel("");

    //MODIFIES: this
    //EFFECTS: assigns team created by user to team object and runs gui code
    public GUIteam(String name, String league) {
        this.team = new Team(name, league);
        applicationWindow();
    }

    //MODIFIES: this
    //EFFECTS: assigns team loaded from file to team onject and runs gui code
    public GUIteam(Team team) {
        this.team = team;
        applicationWindow();
    }

    //EFFECTS: runs the set up methods for application menu
    public void applicationWindow() {
        setupButtons();
        setupTextArea();
        setupHeaderLabel();
        setupImagePanel();
        setupFrame();

    }

    //MODIFIES: this
    //EFFECTS: sets up the buttons in the menu
    public void setupButtons() {
        teamHistory.addActionListener(this);
        boardExpectations.addActionListener(this);
        addPlayer.addActionListener(this);
        removePlayer.addActionListener(this);
        addGoals.addActionListener(this);
        addAssists.addActionListener(this);
        displayPlayers.addActionListener(this);
        searchName.addActionListener(this);
        quit.addActionListener(this);
        teamHistory.setBounds(0,100,120,120);
        boardExpectations.setBounds(120, 100, 120,120);
        addPlayer.setBounds(240, 100, 120, 120);
        removePlayer.setBounds(0, 220, 120, 120);
        addGoals.setBounds(120, 220, 120, 120);
        addAssists.setBounds(240, 220, 120, 120);
        displayPlayers.setBounds(0, 340, 120, 120);
        searchName.setBounds(120,340, 120, 120);
        quit.setBounds(240, 340, 120, 120);
    }

    //MODIFIES: this
    //EFFECTS: sets up text area of menu
    public void setupTextArea() {
        textArea.setBackground(new Color(0,0,50));
        textArea.setEditable(false);
        textArea.setBounds(0, 460, 700, 340);
        textArea.setForeground(new Color(255, 223, 0));
    }

    //MODIFIES: this
    //EFFECTS: sets up the header label
    public void setupHeaderLabel() {
        header.setText("Football Team Manager");
        header.setForeground(new Color(255, 223, 0));
        header.setFont(new Font("Times New Roman", Font.BOLD, 50));
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setVerticalAlignment(JLabel.CENTER);
        titlePanel.add(header);
        titlePanel.setBounds(0,0,700,100);
        titlePanel.setBackground(new Color(0,0,50));

    }

    //MODIFIES: this
    //EFFECTS: sets up the image displayed
    public void setupImagePanel() {
        imageLabel.setBounds(370, 105, 320, 350);
        imageLabel.setBackground(new Color(0,0,50));
        imageLabel.setOpaque(true);
        ImageIcon imageIcon = new ImageIcon("/Users/ayush/IdeaProjects/project_i9h9l/src/main/ui/pitch.png");
        imageLabel.setIcon(imageIcon);
    }

    //MODIFIES: this
    //EFFECTS: sets up the entire menu incorporating all parts into one
    public void setupFrame() {
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(titlePanel);
        frame.add(teamHistory);
        frame.add(boardExpectations);
        frame.add(addPlayer);
        frame.add(removePlayer);
        frame.add(addGoals);
        frame.add(addAssists);
        frame.add(displayPlayers);
        frame.add(searchName);
        frame.add(quit);
        frame.add(imageLabel);
        frame.add(textArea);
        frame.setLayout(null);
        frame.setBackground(new Color(0,0,50));

    }

    //MODIFIES: this
    //EFFECTS: checks actions performed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == teamHistory) {
            teamHistoryButton();
        } else if (e.getSource() == boardExpectations) {
            boardExpectationsButton();
        } else if (e.getSource() == addPlayer) {
            addPlayerButton();
        } else if (e.getSource() == removePlayer) {
            removePlayerButton();
        } else if (e.getSource() == addGoals) {
            addGoalsButton();
        } else if (e.getSource() == addAssists) {
            addAssistsButton();
        } else if (e.getSource() == displayPlayers) {
            displayPlayersButton();
        } else if (e.getSource() == searchName) {
            searchNameButton();
        } else if (e.getSource() == quit) {
            quitButton();
        }
    }

    //EFFECTS: displays a brief history of the club
    private void teamHistoryButton() {
        textArea.setText("The club was founded in 1923, with the expectations of becoming a footballing giant."
                +
                "\n The club has won 12 major titles over its length."
                +
                "\n The home colours are blue, red, white, and yellow."
                +
                "\n The crest consists of a dark gray shield with a double yellow outline along with a wolf-"
                +
                "\n-silhouette and the club's establishment year.");
    }

    //EFFECTS: displays the board's expectations for the season
    private void boardExpectationsButton() {
        textArea.setText("The board's expectations for this season are:"
                +
                "\n 1. Domestic Expectations: Achieve Top 4."
                +
                "\n 2. Continental Expectations: Reach the Quarter-Finals."
                +
                "\n 3. Financial Expectations: Make a Profit of $2 Million.");
    }

    //REQUIRES: no duplicate players, every player has unique name.
    //MODIFIES: this
    //EFFECTS: adds the player to the club
    private void addPlayerButton() {
        JTextField name = new JTextField();
        JTextField country = new JTextField();
        JTextField position = new JTextField();
        Object[] message = {
                "Name:", name,
                "Country:", country,
                "Position", position
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Add Player", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (name.getText().equals("") || country.getText().equals("") || position.getText().equals("")) {
                error.setText("Please fill all fields!");
            } else {
                player = new Player(name.getText(), position.getText(), country.getText());
                team.addPlayer(player);
                textArea.setText(" " + name.getText() + " was added successfully!");
            }
        } else {
            textArea.setText(" Signing canceled");
        }
    }

    //REQUIRES: player must be in club.
    //MODIFIES: this
    //EFFECTS: removes the player from the club
    private void removePlayerButton() {
        JTextField name = new JTextField();
        Object[] message = {
                "Name:", name,
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Remove Player", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (name.getText().equals("")) {
                error.setText("Please fill all fields!");
            } else {
                team.removePlayer(name.getText());
                textArea.setText(" " + name.getText() + " was removed successfully!");
            }
        } else {
            textArea.setText(" Termination canceled");
        }
    }

    //REQUIRES: num > 0
    //MODIFIES: goals scored by player
    //EFFECTS: adds num to goals scored by player
    private void addGoalsButton() {
        JTextField name = new JTextField();
        JTextField goals = new JTextField();

        Object[] message = {
                "Name:", name,
                "Goals:", goals,
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Add Goals", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (name.getText().equals("") || Integer.parseInt(goals.getText()) == 0) {
                error.setText(" Please fill all fields!");
            } else {
                team.addGoals(name.getText(), Integer.parseInt(goals.getText()));
                textArea.setText(" " + name.getText() + " scored " + Integer.parseInt(goals.getText()) + " goals!");
            }
        } else {
            textArea.setText(" Process canceled");
        }
    }

    //REQUIRES: num > 0
    //MODIFIES: assists provided by player
    //EFFECTS: adds num to assists provided by player
    private void addAssistsButton() {
        JTextField name = new JTextField();
        JTextField assists = new JTextField();

        Object[] message = {
                "Name:", name,
                "Assists:", assists,
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Add Assists", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (name.getText().equals("") || Integer.parseInt(assists.getText()) == 0) {
                error.setText(" Please fill all fields!");
            } else {
                team.addAssists(name.getText(), Integer.parseInt(assists.getText()));
                textArea.setText(" " + name.getText() + " provided "
                        + Integer.parseInt(assists.getText()) + " assists!");
            }
        } else {
            textArea.setText(" Process canceled");
        }
    }

    //EFFECTS: displays the statistics for each player
    private void displayPlayersButton() {
        textArea.setText("");
        for (Player player : team.getPlayers()) {
            textArea.append(" " + player.getName() + "      goals = " + player.getGoals()
                    + "      assists = " + player.getAssists() + "\n");
        }

    }

    //EFFECTS: Displays the player and statistics with name if at the club.
    private void searchNameButton() {
        JTextField name = new JTextField();

        Object[] message = {
                "Name:", name,
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Search By Name", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (name.getText().equals("")) {
                error.setText(" Please fill all fields!");
            } else {
                textArea.setText(team.searchByName(name.getText()));
            }
        } else {
            textArea.setText(" Process canceled");
        }
    }

    //EFFECTS: creates new quitframe and quits the application
    private void quitButton() {
        textArea.setText(" Application quitting...");
        new QuitFrame(team);
        frame.dispose();
    }
}
