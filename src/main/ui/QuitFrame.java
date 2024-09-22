package ui;

import model.*;
import model.Event;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

//Class for the quitting frame, takes user's input and saves team accordingly before quitting
public class QuitFrame extends JFrame implements ActionListener {
    private static final String TEAM_JSON = "./data/team.json";
    private Team team;
    JsonWriter jsonWriter = new JsonWriter(TEAM_JSON);

    JFrame frame = new JFrame("Quit");
    JPanel panel = new JPanel(new GridLayout(3,1));
    JTextField textField = new JTextField("              Save before quitting?");
    JButton save = new JButton("YES");
    JButton quit = new JButton("NO");

    //MODIFIES: this
    //EFFECTS: creates a new quit frame object
    public QuitFrame(Team team) {
        this.team = team;
        save.addActionListener(this);
        quit.addActionListener(this);
        frame.setVisible(true);
        frame.add(panel);
        panel.add(textField);
        textField.setEditable(false);
        panel.add(save);
        panel.add(quit);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(250,250);
        frame.setLocationRelativeTo(null);
    }

    //MODIFIES: this
    //EFFECTS: checks actions performed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            frame.dispose();
            saveTeam();
            printLog();

        } else if (e.getSource() == quit) {
            frame.dispose();
            printLog();
        }
    }

    //MODIFIES: team
    //EFFECTS: saves team to file
    public void saveTeam() {
        try {
            jsonWriter.open();
            jsonWriter.write(team);
            jsonWriter.close();
            System.out.println("Saved " + team.getTeamName() + " to " + TEAM_JSON);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + TEAM_JSON);
        }
    }

    //EFFECTS: prints all events in the event log to console
    public void printLog() {
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.toString() + "\n");
        }
        System.exit(0);
    }
}
