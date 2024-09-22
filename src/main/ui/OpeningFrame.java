package ui;

import model.*;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//Class for opening frame. Takes users input and moves to main menu
public class OpeningFrame extends JFrame implements ActionListener {
    private static final String TEAM_JSON = "./data/team.json";
    private Team team;
    JsonReader jsonReader = new JsonReader(TEAM_JSON);

    JFrame frame1 = new JFrame("TEAM MANAGEMENT APPLICATION");
    JPanel panel1 = new JPanel(new GridLayout(2,1));
    JButton loadTeam = new JButton("LOAD TEAM");
    JButton newTeam = new JButton("NEW TEAM");

    JFrame frame2 = new JFrame("CREATE NEW TEAM");
    JPanel panel2 = new JPanel(new GridLayout(3, 1));
    JLabel teamName = new JLabel("TEAM NAME:");
    JLabel league = new JLabel("LEAGUE:");
    JTextField nameInput = new JTextField(20);
    JTextField leagueInput = new JTextField(20);
    JButton next = new JButton("Next");
    JLabel error = new JLabel("");

    //MODIFIES: this
    //EFFECTS: Constructs an opening frame object
    public OpeningFrame() {
        loadTeam.addActionListener(this);
        newTeam.addActionListener(this);
        frame1.setVisible(true);
        frame1.add(panel1);
        panel1.add(newTeam);
        panel1.add(loadTeam);
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame1.setSize(250,250);
        frame1.setLocationRelativeTo(null);

    }

    //MODIFIES: team
    //EFFECTS: creates a new team based on the user's inputs
    public void createNewTeam() {
        next.addActionListener(this);
        panel2.add(teamName);
        panel2.add(nameInput);
        panel2.add(league);
        panel2.add(leagueInput);
        panel2.add(next);
        frame2.setVisible(true);
        frame2.add(panel2);
        frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame2.setSize(250,250);
        frame2.setLocationRelativeTo(null);
    }

    //MODIFIES: this
    //EFFECTS: checks which actions are performed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newTeam) {
            frame1.dispose();
            createNewTeam();

        } else if (e.getSource() == loadTeam) {
            frame1.dispose();
            loadTeam();
            new GUIteam(team);

        } else if (e.getSource() == next) {
            if (nameInput.getText().equals("") || leagueInput.getText().equals("")) {
                error.setText("Empty Field!!");

            } else {
                frame2.dispose();
                new GUIteam(nameInput.getText(), leagueInput.getText());
            }
        }
    }

    //REQUIRES: a file with a saved team
    //EFFECTS: loads team from file
    private void loadTeam() {
        try {
            team = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Error reading from file");
        }
    }
}
